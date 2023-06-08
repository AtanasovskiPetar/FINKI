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

namespace Polygon
{
    public partial class Form1 : Form
    {
        public Scene scene;
        public Color Color { get; set; }
        public int Thickness { get; set; } = 2;
        public Form1()
        {
            InitializeComponent();
            Color = Color.Green;
            scene = new Scene(Color);
            this.DoubleBuffered = true;
            redToolStripMenuItem.Checked = false;
            greenToolStripMenuItem.Checked = true;
            blackToolStripMenuItem.Checked = false;
            thinToolStripMenuItem.Checked = false;
            mediumToolStripMenuItem.Checked = true;
            thickToolStripMenuItem.Checked = false;
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.AddLine(e.Location);
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
            Invalidate();
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            scene.isClose(e.Location);
            scene.changeCurrMousePos(e.Location);
            Invalidate();
        }

        private void newToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            scene = new Scene(Color);
        }

        private void redToolStripMenuItem_Click(object sender, EventArgs e)
        {
            redToolStripMenuItem.Checked = true;

            greenToolStripMenuItem.Checked = false;
            blackToolStripMenuItem.Checked = false;
            Color = Color.Red;
            scene.Color = Color;
        }

        private void greenToolStripMenuItem_Click(object sender, EventArgs e)
        {
            greenToolStripMenuItem.Checked = true;

            redToolStripMenuItem.Checked = false;
            blackToolStripMenuItem.Checked = false;
            Color = Color.Green;
            scene.Color = Color;
        }

        private void blackToolStripMenuItem_Click(object sender, EventArgs e)
        {
            blackToolStripMenuItem.Checked = true;

            greenToolStripMenuItem.Checked = false;
            redToolStripMenuItem.Checked = false;
            Color = Color.Black;
            scene.Color = Color;
        }

        private void thinToolStripMenuItem_Click(object sender, EventArgs e)
        {
            thinToolStripMenuItem.Checked = true;

            mediumToolStripMenuItem.Checked = false;
            thickToolStripMenuItem.Checked = false;

            Thickness = 1;
            scene.Thickness = Thickness;
        }

        private void mediumToolStripMenuItem_Click(object sender, EventArgs e)
        {
            mediumToolStripMenuItem.Checked = true;

            thinToolStripMenuItem.Checked = false;
            thickToolStripMenuItem.Checked = false;

            Thickness = 2;
            scene.Thickness = Thickness;
        }

        private void thickToolStripMenuItem_Click(object sender, EventArgs e)
        {
            thickToolStripMenuItem.Checked = true;

            mediumToolStripMenuItem.Checked = false;
            thinToolStripMenuItem.Checked = false;

            Thickness = 4;
            scene.Thickness = Thickness;
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog sfd = new SaveFileDialog();
            if (sfd.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(sfd.FileName, FileMode.OpenOrCreate);
                IFormatter formatter = new BinaryFormatter();
                formatter.Serialize(fs, scene);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            if(ofd.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(ofd.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                scene = (Scene) formatter.Deserialize(fs);
            }
        }
    }
}
