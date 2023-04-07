using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Automobiles
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnAddBrand_Click(object sender, EventArgs e)
        {
            BrandForm brandFrom = new BrandForm();
            if(brandFrom.ShowDialog() == DialogResult.OK)
            {
                lbBrands.Items.Add(brandFrom.brand);
                cbBrand.Items.Add(brandFrom.brand);
            }
        }

        private void btnAddCar_Click(object sender, EventArgs e)
        {
            Brand brand = cbBrand.SelectedItem as Brand;
            string model = tbModel.Text;
            decimal price = nudPrice.Value;
            decimal consumption = nudConsumption.Value;
            if (brand != null && model != "" && price > 0 && consumption > 0)
            {
                lbCars.Items.Add(new Car(brand, model, consumption, price));
                cbBrand.Text = "";
                tbModel.Text = "";
                nudPrice.Value = (decimal)0;
                nudConsumption.Value = (decimal)0;
            }
            recalculateEverything();
        }

        private void recalculateEverything()
        {
            tbAverageConsumption.Text = "";
            tbMostEconomical.Text = "";
            tbMostExpensive.Text = "";
            if (lbCars.Items.Count > 0)
            {
                float sumConsumption = 0;
                Car MostExpensive = lbCars.Items[0] as Car;
                Car MostEconomica = lbCars.Items[0] as Car;
                foreach(Car car in lbCars.Items)
                {
                    sumConsumption += (float) car.Consumption;
                    if (car.Price > MostExpensive.Price)
                    {
                        MostExpensive = car;
                    }
                    if(car.Consumption < MostEconomica.Consumption)
                    {
                        MostEconomica = car;
                    }
                }
                float average = sumConsumption / (float) lbCars.Items.Count;
                tbAverageConsumption.Text = average.ToString();
                tbMostExpensive.Text = MostExpensive.ToString();
                tbMostEconomical.Text = MostEconomica.ToString();
            }
        }

        private void btnDeleteCar_Click(object sender, EventArgs e)
        {
            Brand brand = cbBrand.SelectedItem as Brand;
            string model = tbModel.Text;
            decimal price = nudPrice.Value;
            decimal consumption = nudConsumption.Value;
            if (brand != null && model != "" && price > 0 && consumption > 0)
            {
                Car carToDelete = new Car(brand, model, consumption, price);
                int index = 0;
                bool isCarAvailable = false;
                int i = 0;
                if (lbCars.Items.Count == 0)
                {
                    return;
                }
                foreach(Car car in lbCars.Items)
                {
                    if (carToDelete.EqualsCar(car))
                    {
                        index = i;
                        isCarAvailable = true;
                    }
                    i++;
                }
                if (isCarAvailable)
                {
                    lbCars.Items.RemoveAt(index);
                    recalculateEverything();
                    cbBrand.Text = "";
                    tbModel.Text = "";
                    nudPrice.Value = (decimal)0;
                    nudConsumption.Value = (decimal)0;
                }

            }
        }
    }
}
