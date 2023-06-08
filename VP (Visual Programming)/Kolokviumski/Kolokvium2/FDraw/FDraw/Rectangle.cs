using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FDraw
{
    [Serializable]
    public class Rectangle
    {
        public Point PntA { get;set; }
        public Point PntB { get;set; }
        public int Height { get; set; }
        public int Width { get; set; }
        public Color Clr { get; set; }
        public bool square { get; set; } = false;

        public Rectangle(Point pntA, Point pntB, Color clr, bool s)
        {
            square = s;
            PntA = new Point(Math.Min(pntA.X, pntB.X), Math.Min(pntA.Y, pntB.Y));
            PntB = new Point(Math.Max(pntA.X, pntB.X), Math.Max(pntA.Y, pntB.Y));
            Height = (PntB.Y - PntA.Y);
            Width = (PntB.X - PntA.X);
            if (square)
            {
                Height = Math.Max(Height, Width);
                Width = Math.Max(Height, Width);
                PntB = new Point(Math.Max(PntA.X + Width, PntA.Y + Height));
            }
            Clr = clr;
        }
        public void Fill(Graphics g)
        {
            Brush brush = new SolidBrush(Clr);
            g.FillRectangle(brush, PntA.X, PntA.Y, Width, Height);
            brush.Dispose();
        }
        public void Draw(Graphics g)
        {
            Pen pen = new Pen(Color.Black);
            pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dot;
            g.DrawRectangle(pen, PntA.X, PntA.Y, Width, Height);
            pen.Dispose();
        }
        public void DrawSelected(Graphics g)
        {
            Pen pen = new Pen(Color.Orange);
            pen.Width = 2;
            g.DrawRectangle(pen, PntA.X, PntA.Y, Width+1, Height+1);
            pen.Dispose();
        }
    }

}
