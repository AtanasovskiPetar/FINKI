using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsSpawning
{
    public class Ball
    {
        public Point Center { get; set; }
        public int Radius;
        public List<int> PossibleRadiuses = new List<int>(){10, 15, 20, 25};
        public double Angle { get; set; }
        public double Velocity { get; set; }
        public double VelocityX { get; set; }
        public double VelocityY { get; set; }
        public Ball(Point center)
        {
            Center = center;
            Random rand1 = new Random();
            Radius = PossibleRadiuses[rand1.Next(0, 4)];

            Angle = rand1.NextDouble() * 2 * Math.PI;
            Velocity = 10;
            VelocityX = (double)(Math.Cos(Angle) * Velocity);
            VelocityY = (double)(Math.Sin(Angle) * Velocity);
        }
        public void Draw(Graphics g)
        {
            Brush brush = new SolidBrush(Color.Red);
            g.FillEllipse(brush, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2*Radius);
            brush.Dispose();

        }
        public void Move(int Height, int Width)
        {
            int nextX = (int) (this.Center.X + VelocityX);
            int nextY = (int) (this.Center.Y + VelocityY);

            if (nextX > Width - 25 || nextX < 25)
            {
                VelocityX = -VelocityX;
                nextX = (int)(this.Center.X + VelocityX);
            }
            if (nextY > Height - 25 || nextY < 25)
            {
                VelocityY = -VelocityY;
                nextY = (int)(this.Center.Y + VelocityY);
            }
            this.Center = new Point(nextX, nextY);
        }
    }
}
