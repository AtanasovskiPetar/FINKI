using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallGame
{
    [Serializable]
    public class Ball
    {
        public Point Center { get; set; }
        public static int Radius { get; set; } = 20;
        public int Type { get; set; }


        public Ball(Point point, int type)
        {
            Center = point;
            Type = type;
        }

        public void Draw(Graphics g)
        {
            Brush b;
            switch (Type)
            {
                case 0: b = new SolidBrush(Color.Red); break;
                case 1: b = new SolidBrush(Color.Blue); break;
                case 2: b = new SolidBrush(Color.Green); break;
                default: b = new SolidBrush(Color.Black); break;
            }
            g.FillEllipse(b, Center.X -  Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
            b.Dispose();
        }

        internal void move()
        {
            Center = new Point(this.Center.X + 10, this.Center.Y);
        }

        internal void hit()
        {
            this.Type += 1;
        }
    }
}
