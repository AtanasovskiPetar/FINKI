using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ColorRectangles
{
    [Serializable]
    public class Scene
    {
        public List<Rectangle> Rectangles { get; set; }
        public Point PreviousPoint { get; set; }
        public Point Pnt { get; set; }

        public Scene()
        {
            Rectangles = new List<Rectangle>();
            PreviousPoint = new Point();
            PreviousPoint = Point.Empty;
            Pnt = new Point();
            Pnt = Point.Empty;
        }
        public void AddRectangle(Point B, Color color)
        {
            if (PreviousPoint == Point.Empty)
            {
                PreviousPoint = B;
                return;
            }
            Rectangles.Add(new Rectangle(PreviousPoint, B, color));
            PreviousPoint = Point.Empty;
        }
        public void Draw(Graphics g)
        {
            foreach(Rectangle rect in Rectangles)
            {
                rect.FillRectangle(g);
            }
            if (PreviousPoint == Point.Empty || Pnt == Point.Empty)
            {
                return;
            }
            Rectangle rec = new Rectangle(PreviousPoint, Pnt, Color.Black);
            rec.DrawRectangle(g);
        }

        internal void Select(Point location)
        {
            foreach (Rectangle rect in Rectangles)
            {
                if (location.X > rect.A.X && location.X < rect.B.X && location.Y > rect.A.Y && location.Y < rect.B.Y)
                {
                    rect.Selected = !rect.Selected;
                    return;
                }
            }
        }

        internal void DeleteRectangles()
        {
            List<Rectangle> DeleteList = new List<Rectangle>();
            foreach(Rectangle rect in Rectangles)
            {
                if (rect.Selected == true)
                {
                    DeleteList.Add(rect);
                }
            }
            Rectangles.RemoveAll(rect => DeleteList.Contains(rect));
        }
    }
}
