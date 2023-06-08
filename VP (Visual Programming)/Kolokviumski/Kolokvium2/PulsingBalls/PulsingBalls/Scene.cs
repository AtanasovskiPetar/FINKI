using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PulsingBalls
{
    [Serializable]
    public class Scene
    {
        public List<Ball> BallsList { get; set; }
        public Color Color { get; set; }
        public Scene() 
        {
            BallsList = new List<Ball>();
            Color = Color.Yellow;
        }
        public void AddBall(Ball b)
        {
            BallsList.Add(b);
        }
        public void Pulse()
        {
            foreach (Ball b in BallsList)
            {
                if (!b.isSelected)
                {
                    b.Pulse();
                }
            }
        }
        public void Draw(Graphics g)
        {
            foreach(Ball b in BallsList)
            {
                b.Fill(g);
                if (b.isSelected)
                {
                    b.DrawDashed(g);
                }
            }
        }
        public void MouseClick(Point location)
        {
            bool select = false;
            foreach (Ball b in BallsList)
            {
                if (Math.Sqrt(Math.Pow(b.Center.X - location.X, 2) + Math.Pow(b.Center.Y - location.Y, 2)) < b.Radius)
                {
                    select = true;
                    b.isSelected = !b.isSelected;
                    break;
                }
            }
            if (!select)
            {
                AddBall(new Ball(location, 25, Color));
            }
        }

        internal void Move(Keys keyCode)
        {
            foreach (Ball b in BallsList)
            {
                if (b.isSelected)
                {
                    b.Move(keyCode);
                }
            }
        }
    }
}
