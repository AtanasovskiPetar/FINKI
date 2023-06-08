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

namespace ColorRectangles
{
    public partial class Form1 : Form
    {
        Scene scene;
        public Color color { get; set; }
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            color = Color.Red;
            redToolStripMenuItem.Checked = true;
            this.DoubleBuffered = true;
            UpdateStatus();
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                scene.AddRectangle(e.Location, color);
            }
            if (e.Button == MouseButtons.Right)
            {
                scene.Select(e.Location);
            }
            UpdateStatus();
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            scene.Pnt = e.Location;
            Invalidate();
        }

        private void redToolStripMenuItem_Click(object sender, EventArgs e)
        {
            color = Color.Red;
            redToolStripMenuItem.Checked = true;

            blueToolStripMenuItem.Checked = false;
            greenToolStripMenuItem.Checked = false;
        }

        private void greenToolStripMenuItem_Click(object sender, EventArgs e)
        {
            color = Color.Green;
            greenToolStripMenuItem.Checked = true;

            blueToolStripMenuItem.Checked = false;
            redToolStripMenuItem.Checked = false;
        }

        private void blueToolStripMenuItem_Click(object sender, EventArgs e)
        {
            color = Color.Blue;
            blueToolStripMenuItem.Checked = true;

            greenToolStripMenuItem.Checked = false;
            redToolStripMenuItem.Checked = false;
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode != Keys.Delete)
            {
                return;
            }
            scene.DeleteRectangles();
            UpdateStatus();
            Invalidate();
        }

        public void UpdateStatus()
        {
            tsslStatus.Text = $"Number of Rectangles: {scene.Rectangles.Count}";
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

        private void openToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                scene = (Scene)formatter.Deserialize(fs);
            }
        }
    }
}
