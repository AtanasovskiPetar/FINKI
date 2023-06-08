using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FDraw
{
    [Serializable]
    public class Scene
    {
        public List<Rectangle> Rectangles {  get; set; }
        public Point LastPoint { get; set; }
        public Point CurrPoint { get; set; }
        public bool currSquare { get; set; } = false;
        public Rectangle SelectedRectangle { get; set; }
        public bool Selected { get; set; } = false;
        public bool drag { get; set; } = false;
        public int SelectedIndex { get; set; }
        public Color Color { get; set; }

        public Scene()
        {
            Rectangles = new List<Rectangle>();
            LastPoint = new Point();
            LastPoint = Point.Empty;
            CurrPoint = new Point();
            CurrPoint = Point.Empty;
            Color = Color.Pink;
        }
        public void Draw(Graphics g)
        {
            foreach (Rectangle rect in Rectangles) 
            {
                rect.Fill(g);
            }
            if (Selected && !drag)
            {
                SelectedRectangle.DrawSelected(g);
            }
            if (LastPoint == Point.Empty || CurrPoint == Point.Empty)
            {
                return;
            }
            Rectangle r = new Rectangle(LastPoint, CurrPoint, Color, currSquare);
            r.Draw(g);
            
        }
        public void AddRectangle(Point A, Point B, Color c)
        {
            if (Math.Max(A.X, B.X) - Math.Min(A.X, B.X) < 5 || Math.Max(A.Y, B.Y) - Math.Min(A.Y, B.Y) < 5)
            {
                return;
            }
            Rectangles.Add(new Rectangle(A, B, c, currSquare));
        }

        internal void Select(Point p)
        {
            for (int i= 0; i < Rectangles.Count; i++)
            {
                Rectangle r = Rectangles[i];
                if (p.X > r.PntA.X && p.X < r.PntA.X + r.Width && p.Y > r.PntA.Y & p.Y < r.PntA.Y + r.Height)
                {
                    Selected = true;
                    SelectedRectangle = r;
                    SelectedIndex = i;
                    return;
                }
            }
            Selected = false;
        }

        internal void MouseDown(Point location)
        {
            drag = !drag;
            if (drag)
            {
                if (!Selected)
                {
                    LastPoint = location;
                }
                else
                {
                    Select(location);
                }
            }
        }

        internal void MouseUp(Point location)
        {
            if (drag && LastPoint != Point.Empty)
            {
                AddRectangle(LastPoint, location, Color);
            }
            drag = false;
            LastPoint = Point.Empty;
            CurrPoint = Point.Empty;
        }

        internal void MouseMove(Point location)
        {
            if (Selected && drag)
            {
                int newX_1 = Math.Max(SelectedRectangle.PntA.X, location.X) - Math.Min(SelectedRectangle.PntA.X, location.X);
                int newY_1 = Math.Max(SelectedRectangle.PntA.Y, location.Y) - Math.Min(SelectedRectangle.PntA.Y, location.Y);
                int newX_2 = newX_1 + SelectedRectangle.Width;
                int newY_2 = newY_1 + SelectedRectangle.Height;
                Rectangles[SelectedIndex] = new Rectangle(new Point(newX_1, newY_1), new Point(newX_2, newY_2), SelectedRectangle.Clr, SelectedRectangle.square);
                //SelectedRectangle = Rectangles[SelectedIndex];
                return; 
            }
            if (!drag || LastPoint == Point.Empty)
            {
                return;
            }
            CurrPoint = location;
        }
    }
}
