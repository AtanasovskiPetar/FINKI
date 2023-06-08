using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Security.Permissions;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PulsingBalls
{
    [Serializable]
    public class Ball
    {
        public Point Center { get; set; }
        public int Radius { get; set; }
        public Color Color { get; set; }
        public int UpperBound { get; set; } = 50;
        public int LowerBound { get; set; } = 10;
        public bool isEnlarging { get; set; }
        public bool isSelected { get; set; } = false;

        public Ball(Point center, int radius, Color color)
        {
            Center = center;
            Radius = radius;
            Color = color;
            Random rand = new Random();
            isEnlarging = rand.Next(2) == 0;
        }
        public void Fill(Graphics g)
        {
            Brush brush = new SolidBrush(Color);
            g.FillEllipse(brush, Center.X - Radius, Center.Y - Radius, 2*Radius, 2*Radius);
            brush.Dispose();
        }
        public void DrawDashed(Graphics g)
        {
            Pen pen = new Pen(Color.Black);
            pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
            g.DrawEllipse(pen, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
            pen.Dispose();
        }
        public void Pulse()
        {
            if (this.Radius >= UpperBound)
            {
                isEnlarging = false;
            }
            if (this.Radius <= LowerBound)
            {
                isEnlarging = true;
            }
            if (isEnlarging)
            {
                this.Radius = Radius + 5;
            }
            else
            {
                this.Radius = Radius - 5;
            }
        }

        internal void Move(Keys keyCode)
        {
            if (keyCode == Keys.Up)
            {
                this.Center = new Point(Center.X, Center.Y - 5);
            }
            else if (keyCode == Keys.Down)
            {
                this.Center = new Point(Center.X, Center.Y + 5);
            }
            else if (keyCode == Keys.Left)
            {
                this.Center = new Point(Center.X - 5, Center.Y);
            }
            else if (keyCode == Keys.Right)
            {
                this.Center = new Point(Center.X + 5, Center.Y);
            }
        }
    }
}
