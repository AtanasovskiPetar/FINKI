using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace BallGame
{
    [Serializable]
    public class Scene
    {
        public List<Ball> Balls {get;set;}
        public int Hits { get; set; } = 0;
        public int Misses { get; set; } = 0;   
        public Scene() 
        { 
            Balls = new List<Ball>();
        }
        public void addBall(Ball ball)
        {
            Balls.Add(ball);
        }
        public void Draw(Graphics g)
        {
            foreach (Ball b in Balls)
            {
                b.Draw(g);
            }
        }

        internal void move(int width)
        {
            if(Balls.Count <= 0)
            {
                return;
            }
            foreach(Ball ball in Balls)
            {
                if (ball.Center.X >= width)
                {
                    Misses++;
                    Balls.Remove(ball);
                    return;
                }
                ball.move();
            }
        }

        internal void Click(Point location)
        {
            if (Balls.Count <= 0)
            {
                return;
            }
            foreach(Ball b in Balls)
            {
                double dist = Math.Sqrt(Math.Pow(location.X - b.Center.X, 2) + Math.Pow(location.Y - b.Center.Y, 2));
                if (dist <= Ball.Radius)
                {
                    Hits += 1;
                    if (b.Type == 2)
                    {
                        Balls.Remove(b);
                        return;
                    }
                    else
                    {
                        b.hit();
                    }
                }
            }
        }
    }
}
