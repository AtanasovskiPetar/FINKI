using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Busses
{
    public partial class Busses : Form
    {
        public Busses()
        {
            InitializeComponent();
        }

        private void btnAddBus_Click(object sender, EventArgs e)
        {
            NewBus newBusForm = new NewBus();
            if(newBusForm.ShowDialog() == DialogResult.OK)
            {
                Bus bus = newBusForm.bus;
                lbBusses.Items.Add(bus);
                lbBusses.SelectedItems.Clear();
                lbBusses.SelectedItem = bus;
            }
        }

        private void btnDeleteBus_Click(object sender, EventArgs e)
        {
            if (lbBusses.SelectedItems.Count != 1)
            {
                return;
            }
            Bus busToDelete = lbBusses.SelectedItem as Bus;
            if (busToDelete != null)
            {
                DialogResult dr = MessageBox.Show("Дали си сигурен?", "Сигурен?", MessageBoxButtons.YesNo);
                if (dr == DialogResult.OK)
                {
                    lbBusses.Items.Remove(busToDelete);
                }
                else
                {
                    lbBusses.SelectedItems.Clear();
                }
            }
        }

        private void btnAddLine_Click(object sender, EventArgs e)
        {
            if (lbBusses.SelectedItem == null)
            {
                return;
            }
            Bus bus = lbBusses.SelectedItem as Bus;
            NewLine newLineForm = new NewLine();
            if (newLineForm.ShowDialog() == DialogResult.OK) 
            {
                bus.addLine(newLineForm.line);
            }
            showBusses();
            recalculateEverything();
        }
        private void lbBusses_SelectedIndexChanged(object sender, EventArgs e)
        {
            showBusses();
            recalculateEverything();
        }

        private void showBusses()
        {
            lbLines.Items.Clear();
            if (lbBusses.SelectedItem == null)
            {
                return;
            }
            Bus bus = lbBusses.SelectedItem as Bus;
            if (bus.Lines.Count == 0)
            {
                return;
            }
            foreach (Line line in bus.Lines)
            {
                lbLines.Items.Add(line);
            }
        }

        private void recalculateEverything()
        {
            tbAverageLinePrice.Text = "";
            tbMostExpensiveLine.Text = "";
            if (lbLines.Items.Count == 0)
            {
                return;
            }
            float sumPrices = 0;
            Line expensive = lbLines.Items[0] as Line;
            foreach (Line line in lbLines.Items)
            {
                sumPrices += line.Price;
                if (line.Price > expensive.Price)
                {
                    expensive = line;
                }
            }
            float avg = sumPrices / (float)lbLines.Items.Count;
            tbAverageLinePrice.Text = avg.ToString();
            tbMostExpensiveLine.Text = expensive.ToString();
        }
    }
}
