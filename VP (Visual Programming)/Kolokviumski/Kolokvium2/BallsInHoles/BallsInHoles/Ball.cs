using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsInHoles
{
    [Serializable]
    public class Ball
    {
        public Point Center { get;set; }
        public int Radius { get; set; }
        public Color Color { get; set; }
        public double VelocityX { get; set; }
        public double VelocityY { get;set; }
        public double Velocity { get; set; }
        public double Angle { get; set; }

        public Ball(Point center, Color color, int radius)
        {
            Center = center;
            Radius = radius;
            Color = color;

            Velocity = 10;

            Random rand = new Random();
            Angle = rand.NextDouble() * 2 * Math.PI;

            VelocityX = (double)(Math.Cos(Angle) * Velocity);
            VelocityY = (double)(Math.Sin(Angle) * Velocity);
        }
        public void Draw (Graphics g)
        {
            Brush brush = new SolidBrush(Color);
            g.FillEllipse(brush, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
            brush.Dispose();
        }
        public void Move(int height, int width)
        {
            int nextX = (int)(Center.X + VelocityX);
            int nextY = (int)(Center.Y + VelocityY);

            if (nextX <= 25 || nextX >= (width - 25))
            {
                VelocityX = -VelocityX;
                nextX = (int)(Center.X + VelocityX);
            }
            if (nextY < 25 || nextY >= (height - 20))
            {
                VelocityY = -VelocityY;
                nextY = (int)(Center.Y + VelocityY);
            }

            Center = new Point(nextX, nextY);
        }
    }
}
