using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ColorRectangles
{
    [Serializable]
    public class Rectangle
    {
        public Point A { get; set; }
        public Point B { get; set; }
        public Color Color { get; set; }
        public bool Selected { get; set; } = false;

        public Rectangle(Point a, Point b, Color color)
        {
            A = new Point(Math.Min(a.X, b.X), Math.Min(a.Y, b.Y));
            B = new Point(Math.Max(a.X, b.X), Math.Max(a.Y, b.Y));
            Color = color;
        }
        public void FillRectangle(Graphics g)
        {
            Brush brush = new SolidBrush(Color);
            g.FillRectangle(brush, A.X, A.Y, (B.X - A.X), (B.Y - A.Y));
            brush.Dispose();
            
            if (Selected)
            {
                Pen pen = new Pen(Color.Red);
                pen.Width = 5;
                g.DrawRectangle(pen, A.X, A.Y, (B.X - A.X), (B.Y - A.Y));
                pen.Dispose();
            }
        }
        public void DrawRectangle(Graphics g)
        {
            Pen pen = new Pen(Color.Black);
            pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
            g.DrawRectangle(pen, A.X, A.Y, (B.X - A.X), (B.Y - A.Y));
            pen.Dispose();
        }
    }
}
