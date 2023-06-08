using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BallGame
{
    public partial class Form1 : Form
    {
        Scene scene;
        public int timerTicks { get; set; } = 0;
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            timer1.Start();
            this.DoubleBuffered = true;
            tsslScore.Text = $"Hits: {scene.Hits.ToString()} Misses: {scene.Misses.ToString()}";
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            timerTicks += 1;
            if (timerTicks % 10 == 0)
            {
                Random rnd = new Random();
                Point pnt = new Point(0 - Ball.Radius*2, rnd.Next(0 + Ball.Radius*4, this.Height - Ball.Radius*4));
                Ball b = new Ball(pnt, rnd.Next(0,3));
                scene.addBall(b);
            }
            scene.move(this.Width);
            updateHits();
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.Click(e.Location);
            updateHits();
            Invalidate();
        }
        public void updateHits()
        {
            tsslScore.Text = $"Hits: {scene.Hits.ToString()} Misses: {scene.Misses.ToString()}";
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene = new Scene();
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formater = new BinaryFormatter();
                scene = (Scene) formater.Deserialize(fs);
            }
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            if(saveFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(saveFileDialog.FileName, FileMode.OpenOrCreate);
                IFormatter formater = new BinaryFormatter();
                formater.Serialize(fs, scene);
            }
        }
    }
}
