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

namespace DrawEllipses
{
    public partial class Form1 : Form
    {
        public Scene scene {  get; set; }
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            toolStripStatusLabel1.Text = $"Num Ellipses: {scene.EllipseList.Count}";
            this.DoubleBuffered = true;
            timer1.Start();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.Click(e.Location);
            UpdateStatus();
            Invalidate();
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            scene.Move(e.Location);
            Invalidate();
        }
        public void UpdateStatus()
        {
            toolStripStatusLabel1.Text = $"Num Ellipses: {scene.EllipseList.Count}";
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            scene.UpdateColors();
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
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                scene = (Scene) formatter.Deserialize(fs);
            }
        }
    }
}
