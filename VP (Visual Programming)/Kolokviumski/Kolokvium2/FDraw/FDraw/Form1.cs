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

namespace FDraw
{
    public partial class Form1 : Form
    {
        public bool drag { get; set; } = false;
        public Scene scene { get;set; }
        public Color Color { get; set; }
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            Color = Color.Green;
            this.DoubleBuffered = true;
            toolStripStatusLabel1.Text = $"Rects: {scene.Rectangles.Count}";
        }

        private void Form1_MouseDown(object sender, MouseEventArgs e)
        { 
            scene.MouseDown(e.Location);
            Invalidate();
        }

        private void Form1_MouseUp(object sender, MouseEventArgs e)
        {
            scene.MouseUp(e.Location);
            UpdateStatus();
            Invalidate();
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            scene.MouseMove(e.Location);
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.ControlKey)
            {
                scene.currSquare = true;
            }
        }

        private void Form1_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.ControlKey)
            {
                scene.currSquare = false;
            }
        }
        public void UpdateStatus()
        {
            toolStripStatusLabel1.Text = $"Rects: {scene.Rectangles.Count}";
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.Select(e.Location);
            Invalidate();
        }

        private void redToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Color = Color.Red;
            scene.Color = Color;
            redToolStripMenuItem.Checked = true;

            yellowToolStripMenuItem.Checked = false;
            blackToolStripMenuItem.Checked = false;
        }

        private void yellowToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Color = Color.Yellow;
            scene.Color = Color;
            yellowToolStripMenuItem.Checked = true;

            redToolStripMenuItem.Checked = false;
            blackToolStripMenuItem.Checked = false;
        }

        private void blackToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Color = Color.Black;
            scene.Color = Color;
            blackToolStripMenuItem.Checked = true;

            yellowToolStripMenuItem.Checked = false;
            redToolStripMenuItem.Checked = false;
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene = new Scene();
            drag = false;
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog saveFile = new SaveFileDialog();
            if (saveFile.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(saveFile.FileName, FileMode.OpenOrCreate);
                IFormatter formatter = new BinaryFormatter();
                formatter.Serialize(fs, scene);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                scene = (Scene) formatter.Deserialize(fs);
            }
        }
    }
}
