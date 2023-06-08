using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrawEllipses
{
    [Serializable]
    public class Ellipse
    {
        public Point A { get; set; }
        public Point B { get; set; }
        public int Height;
        public int Width;
        public Color Color { get; set; }
        public Ellipse(Point a, Point b, Color c)
        {
            A = new Point(Math.Min(a.X, b.X), Math.Min(a.Y, b.Y));
            B = new Point(Math.Max(a.X, b.X), Math.Max(a.Y, b.Y));
            Height = (B.Y - A.Y);
            Width = (B.X - A.X);
            Random random = new Random();
            Color = Color.FromArgb(random.Next(256), random.Next(256), random.Next(256));
        }
        public void Fill(Graphics g)
        {
            Brush brush = new SolidBrush(Color);
            g.FillEllipse(brush, A.X, A.Y, Width, Height);
            brush.Dispose();
        }
        public void DrawDashed(Graphics g)
        {
            Pen pen = new Pen(Color.Black);
            pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
            g.DrawEllipse(pen, A.X, A.Y, Width, Height);
            pen.Dispose();
        }
        public void UpdateColor()
        {
            this.Color = Color.FromArgb((Color.R + 5) %256, (Color.G + 10) %256, (Color.B + 15) %256);
        }
    }
}
