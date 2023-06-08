using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Polygon
{
    [Serializable]
    public class Scene
    {
        public List<Line> Lines { get; set; }
        public List<Polygon> Polygons { get; set; }
        public Point PreviousPoint { get; set; }
        public bool Closed { get; set; } = false;
        public Point FirstPoint { get; set; }
        public bool close { get; set; } = false;
        public Point currMouse { get; set; }
        public Color Color { get; set; }
        public int Thickness { get; set; }
        
        public Scene(Color color)
        {
            PreviousPoint = new Point();
            PreviousPoint = Point.Empty;
            FirstPoint = new Point();
            FirstPoint = Point.Empty;
            Lines = new List<Line>();
            Polygons = new List<Polygon>();
            currMouse = new Point();
            currMouse = Point.Empty;
            Color = color;
            Thickness = 2;
        }
        public void AddLine(Point p)
        {
            if (PreviousPoint != Point.Empty)
            {
                if (close)
                {
                    Lines.Add(new Line(PreviousPoint, FirstPoint, Color, Thickness));
                    List<Point> points = new List<Point>();
                    foreach(Line l in Lines)
                    {
                        points.Add(l.Point1);
                    }

                    Polygons.Add(new Polygon(points, Color, Thickness));
                    Lines = new List<Line>();
                    PreviousPoint = Point.Empty;
                    FirstPoint = Point.Empty;
                    close = false;
                }
                else
                {
                    Lines.Add(new Line(PreviousPoint, p, Color, Thickness));
                    PreviousPoint = p;
                }
                
            }
            else
            {
                PreviousPoint = new Point(p.X, p.Y);
            }
            if (FirstPoint == Point.Empty && Lines.Count > 1)
            {
                FirstPoint = Lines[0].Point1;
            }
        }
        public void Draw(Graphics g)
        {
            foreach(Polygon polygon in Polygons)
            {
                polygon.Draw(g);
            }
            foreach (Line line in Lines)
            {
                line.Draw(g);
            }
            if (PreviousPoint != Point.Empty && currMouse != Point.Empty)
            {
                Pen pen = new Pen(Color.Black);
                pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
                g.DrawLine(pen, currMouse, PreviousPoint);
                pen.Dispose();
            }
            if (close)
            {
                Pen pen = new Pen(Color.Black);
                g.DrawEllipse(pen, FirstPoint.X - 5, FirstPoint.Y - 5, 10, 10);
                pen.Dispose();
            }
        }

        public void isClose(Point p)
        {
            if (FirstPoint == Point.Empty)
            {
                return;
            }
            if (Math.Sqrt((Math.Pow(p.X - FirstPoint.X, 2) + Math.Pow(p.Y - FirstPoint.Y, 2))) <= 5)
            {
                close = true;
            }
            else
            {
                close = false;
            }
        }

        internal void changeCurrMousePos(Point location)
        {
            currMouse = location;
        }
    }
}
