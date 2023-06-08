using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BallsSpawning
{
    public partial class Form1 : Form
    {
        public Scene scene { get; set; }
        public Form1()
        {
            InitializeComponent();
            timer1.Start();
            scene = new Scene(this.Height, this.Width);
            this.DoubleBuffered = true;
            UpdateStatus();
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.AddBall(e.Location);
            UpdateStatus();
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            scene.Move(this.Height, this.Width);
            UpdateStatus();
            Invalidate();
        }
        public void UpdateStatus()
        {
            tssNumBalls.Text = $"Number Balls: {scene.Balls.Count}";
        }
    }
}
