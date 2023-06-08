using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace airports
{
    public partial class Airports : Form
    {
        public Airports()
        {
            InitializeComponent();
        }

        private void Airports_Load(object sender, EventArgs e)
        {

        }

        private void btnAddAirport_Click(object sender, EventArgs e)
        {
            AddAirportForm addingForm = new AddAirportForm();
            if (addingForm.ShowDialog() == DialogResult.OK)
            {
                Airport addedAirport = addingForm.airport;
                lbAirports.Items.Add(addedAirport);
            }
        }

        private void btnRemoveAirport_Click(object sender, EventArgs e)
        {
            if (lbAirports.SelectedItems.Count > 0)
            {
                Airport toDelete = lbAirports.SelectedItem as Airport;
                DialogResult dr = MessageBox.Show("Дали си сигурен дека сакаш да го избришеш аеродромот","", MessageBoxButtons.OKCancel);
                if (dr == DialogResult.OK)
                {
                    lbAirports.Items.Remove(toDelete);
                }
                else
                {
                    lbAirports.SelectedItems.Clear();
                }
            }
        }

        private void btnAddDestination_Click(object sender, EventArgs e)
        {
            if (lbAirports.SelectedItems.Count > 0)
            {
                Airport airport = lbAirports.SelectedItem as Airport;
                NewDestination destinationForm = new NewDestination();
                if (destinationForm.ShowDialog() == DialogResult.OK)
                {
                    airport.addDestination(destinationForm.destination);
                    showDestinations(airport);
                    recalculateEverything();
                }
            }
            
        }

        private void lbAirports_SelectedIndexChanged(object sender, EventArgs e)
        {
            lbDestinations.Items.Clear();
            tbAverageDistance.Text = "";
            tbMostExpensive.Text = "";
            if (lbAirports.SelectedItems.Count > 0)
            {
                Airport selectedAirport = lbAirports.SelectedItem as Airport;
                if (selectedAirport.destinations.Count > 0)
                {
                    showDestinations(selectedAirport);
                }
            }
            recalculateEverything();
        }

        private void showDestinations(Airport selectedAirport)
        {
            lbDestinations.Items.Clear();
            foreach (Destination destination in selectedAirport.destinations)
            {
                lbDestinations.Items.Add(destination);
            }
        }

        private void recalculateEverything()
        {
            if (lbDestinations.Items.Count > 0)
            {
                float sumDistances = 0;
                Destination mostExpensive = lbDestinations.Items[0] as Destination;
                int mostExpensivePrice = mostExpensive.price;
                foreach (Destination destination in lbDestinations.Items)
                {
                    sumDistances += destination.distance;
                    if(destination.price > mostExpensivePrice)
                    {
                        mostExpensivePrice = destination.price;
                        mostExpensive = destination;
                    }
                }
                tbMostExpensive.Text = mostExpensive.ToString();
                float avg = sumDistances / (float)lbDestinations.Items.Count;
                tbAverageDistance.Text = avg.ToString();
            }
        }
    }
}
