using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows.Forms;

namespace BallsInHoles
{
    public partial class Form1 : Form
    {
        Scene scene;
        public bool Generated { get;set ; } = false;
        public Color Color { get; set; }
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            this.DoubleBuffered = true;
            Color = Color.Red;
            redToolStripMenuItem.Checked = true;
            timer1.Start();
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.AddBall(e.Location, Color);
            UpdateStatus();
            Invalidate();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            scene.Move(this.Height, this.Width);
            if (Generated)
            {
                scene.EatBalls();
            }
            UpdateStatus();
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void toolStripTextBox1_Click(object sender, EventArgs e)
        {
            scene.Generate(this.Height, this.Width);
            Generated = true;
            Invalidate();
        }
        public void UpdateStatus()
        {
            tsslStatus.Text = $"Momentalno ima: {scene.Balls.Count}";
        }

        private void redToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Color = Color.Red;
            redToolStripMenuItem.Checked = true;

            greenToolStripMenuItem.Checked = false;
            blueToolStripMenuItem.Checked = false;
        }

        private void greenToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Color = Color.Green;
            greenToolStripMenuItem.Checked = true;

            redToolStripMenuItem.Checked = false;
            blueToolStripMenuItem.Checked = false;
        }


        private void blueToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Color = Color.Blue;
            blueToolStripMenuItem.Checked = true;

            redToolStripMenuItem.Checked = false;
            greenToolStripMenuItem.Checked = false;
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene = new Scene();
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            if (saveFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(saveFileDialog.FileName, FileMode.OpenOrCreate);
                IFormatter formatter = new BinaryFormatter();
                formatter.Serialize(fs, scene);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if(openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                scene = (Scene) formatter.Deserialize(fs);
            }
        }
    }
}
