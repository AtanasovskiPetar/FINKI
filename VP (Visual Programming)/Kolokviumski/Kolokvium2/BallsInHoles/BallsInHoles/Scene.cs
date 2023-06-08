using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsInHoles
{
    [Serializable]
    public class Scene
    {
        public List<Ball> Balls { get; set; }
        public List<Ball> BlackHoles { get; set; }
        public Scene() 
        {
            Balls = new List<Ball>();
            BlackHoles = new List<Ball>();
        }
        public void AddBall(Point pnt, Color color)
        {
            Balls.Add(new Ball(pnt, color, 25));
        }
        public void Draw(Graphics g)
        {
            foreach (Ball b in Balls)
            {
                b.Draw(g);
            }
            foreach (Ball b in BlackHoles)
            {
                b.Draw(g);
            }
        }
        public void Move(int height, int width)
        {
            foreach (Ball b in Balls)
            {
                b.Move(height, width);
            }
        }
        public void Generate(int height, int width)
        {
            int newX, newY;
            Random rnd = new Random();
            List<Point> Centers = new List<Point>();
            while(Centers.Count < 5)
            {
                newX = rnd.Next(60, width-60);
                newY = rnd.Next(60, height-60);
                Point NewCenter = new Point (newX, newY);
                bool flag = true;
                foreach(Point p in Centers)
                {
                    if ((Math.Sqrt(Math.Pow(p.X - NewCenter.X, 2) + Math.Pow(p.Y - NewCenter.Y, 2))) < 30)
                    {
                        flag = false;
                    }
                }
                if (flag)
                {
                    Centers.Add(NewCenter);
                }
            }

            foreach(Point c in Centers)
            {
                BlackHoles.Add(new Ball(c, Color.Black, 30));
            }
        }

        internal void EatBalls()
        {
            List<Ball> DeleteList = new List<Ball>();
            foreach (Ball b in Balls)
            {
                foreach (Ball hole in BlackHoles)
                {
                    if ((Math.Sqrt(Math.Pow(b.Center.X - hole.Center.X, 2) + Math.Pow(b.Center.Y - hole.Center.Y, 2))) < 25)
                    {
                        DeleteList.Add(b);
                    }
                }
            }
            Balls.RemoveAll(b => DeleteList.Contains(b));
        }
    }
}
