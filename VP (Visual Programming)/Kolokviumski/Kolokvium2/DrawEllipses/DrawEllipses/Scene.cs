using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrawEllipses
{
    [Serializable]
    public class Scene
    {
        public List<Ellipse> EllipseList {  get; set; }
        public Point PreviousPoint { get; set; }
        public Point CurrentPoint { get; set; }
        public Color Color { get; set; }
        public Scene()
        { 
            EllipseList = new List<Ellipse>();
            PreviousPoint = new Point();
            CurrentPoint = new Point();
            PreviousPoint = Point.Empty;
            Color = Color.Pink;
            CurrentPoint = Point.Empty;
        }
        public void AddEllipse(Point A, Point B)
        {
            EllipseList.Add(new Ellipse(A, B, Color));
        }
        public void Draw(Graphics g)
        {
            foreach (Ellipse ellipse in EllipseList)
            {
                ellipse.Fill(g);
            }
            if (PreviousPoint != Point.Empty && CurrentPoint != Point.Empty)
            {
                Ellipse e = new Ellipse (PreviousPoint, CurrentPoint, Color.Black);
                e.DrawDashed(g);
            }
        }

        public void Click(Point location)
        {
            if (PreviousPoint  == Point.Empty)
            {
                PreviousPoint = location;
                return;
            }
            CurrentPoint = location;
            AddEllipse(PreviousPoint, CurrentPoint);
            PreviousPoint = Point.Empty;
            CurrentPoint = Point.Empty;
        }

        internal void Move(Point location)
        {
            if (PreviousPoint == Point.Empty)
            {
                return;
            }
            CurrentPoint = location;
        }

        internal void UpdateColors()
        {
            foreach (Ellipse ellipse in EllipseList)
            {
                ellipse.UpdateColor();
            }
        }
    }
}
