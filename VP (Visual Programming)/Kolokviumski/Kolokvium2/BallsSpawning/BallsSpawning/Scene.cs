using System;
using System.Collections.Generic;
using System.Diagnostics.SymbolStore;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsSpawning
{
    public class Scene
    {
        public List<Ball> Balls { get; set; }

        public Scene(int height, int width)
        {
            Balls = new List<Ball>();
        }
        public void AddBall(Point pnt)
        {
            Balls.Add(new Ball(pnt));
        }
        public void Move(int Height, int Width) 
        {
            List < Ball> DeleteList = new List<Ball>();
            foreach (Ball b1 in Balls)
            {
                foreach (Ball b2 in Balls)
                {
                    if (!b1.Equals(b2))
                    {
                        double distance = Math.Sqrt(Math.Pow(b1.Center.X - b2.Center.X, 2) + Math.Pow(b1.Center.Y - b2.Center.Y, 2));
                        if (distance <= b1.Radius + b2.Radius)
                        {
                            //hit
                            DeleteList.Add(b1);
                            DeleteList.Add(b2);
                        }
                    }
                }
                b1.Move(Height, Width);
            }
            if (DeleteList.Count <= 0 || DeleteList.Count % 2 != 0) 
            {
                return;
            }
            for(int i = 0; i < DeleteList.Count; i+=2)
            {
                Ball b1 = DeleteList[i];
                Ball b2 = DeleteList[i + 1];
                Balls.Remove(b1);
                Balls.Remove(b2);
                int newX = Math.Abs((b2.Center.X - b1.Center.X) / 2) + Math.Min(b1.Center.X, b2.Center.X);
                int newY = Math.Abs((b2.Center.Y - b1.Center.Y) / 2) + Math.Min(b1.Center.Y, b2.Center.Y);
                Point newCenter = new Point(newX, newY);
                Ball newBall = new Ball(newCenter);
                newBall.VelocityX = 0;
                newBall.VelocityY = -newBall.Velocity;
                Balls.Add(newBall);
                DeleteList.RemoveAt(i);
                DeleteList.RemoveAt(i);
            }
        }
        public void Draw(Graphics g)
        {
            foreach(Ball b in Balls)
            {
                b.Draw(g);
            }
        }
    }
}
