using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PizzaOrder
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void rbSmall_CheckedChanged(object sender, EventArgs e)
        {
            if (rbSmall.Checked)
            {
                rbMedium.Checked = false;
                rbLarge.Checked = false;
            }
            recalculate();
        }

        private void rbMedium_CheckedChanged(object sender, EventArgs e)
        {
            if(rbMedium.Checked )
            {
                rbSmall.Checked = false;
                rbLarge.Checked = false;
            }
            recalculate();
        }

        private void rbLarge_CheckedChanged(object sender, EventArgs e)
        {
            if(rbLarge.Checked)
            {
                rbSmall.Checked = false;
                rbMedium.Checked = false;
            }
            recalculate();
        }
        

        private void tbSmall_TextChanged(object sender, EventArgs e)
        {
            recalculate();
        }

        private void tbCocaQuantity_TextChanged(object sender, EventArgs e)
        {
            recalculate();
        }
        private void recalculate()
        {
            tbToPayTotal.Text = "";
            float totalPrice = 0;

            float f = 0;
            float.TryParse(tbSmall.Text, out f);
            if (rbSmall.Checked)
            {
                totalPrice += f;
            }
            else if (rbMedium.Checked)
            {
                f = 0;
                float.TryParse(tbMedium.Text, out f);
                totalPrice += f;
            }
            else
            {
                f = 0;
                float.TryParse(tbLarge.Text, out f);
                totalPrice += f;
            }

            float totalDrinks = 0;
            float drink = 0;
            float.TryParse(tbTotalCocaPrice.Text, out drink);
            totalDrinks += drink;

            drink = 0;
            float.TryParse(tbTotalJuicePrice.Text, out drink);
            totalDrinks += drink;

            drink = 0;
            float.TryParse(tbTotalBeerPrice.Text, out drink);
            totalDrinks += drink;

            totalPrice += totalDrinks;

            if(checkBox1.Checked)
            {
                float fef = 0;
                float.TryParse(tbFeferonki.Text, out fef);
                totalPrice += fef;
            }

            if(checkBox2.Checked)
            {
                float fef = 0;
                float.TryParse(tbExtraCheese.Text, out fef);
                totalPrice += fef;
            }
            if(checkBox3.Checked)
            {
                float fef = 0;
                float.TryParse(tbKetchup.Text, out fef);
                totalPrice += fef;
            }

            float desertPrice = 0;
            float.TryParse(tbDeserPrice.Text, out desertPrice);

            totalPrice += desertPrice;

            tbToPayTotal.Text = totalPrice.ToString();
        }
        private void recalculateCoca()
        {
            float priceDrink = 0;
            int i = 0;
            float currDrinkPrice = 0;
            int.TryParse(tbCocaQuantity.Text, out i);
            float.TryParse(tbColaPrice.Text, out currDrinkPrice);
            priceDrink += i * currDrinkPrice;

            tbTotalCocaPrice.Text = priceDrink.ToString();
            recalculate();
        }
        private void recalculateJuice()
        {
            float priceDrink = 0;
            int i = 0;
            float currDrinkPrice = 0;
            int.TryParse(tbJuiceQuantity.Text, out i);
            float.TryParse(tbJuicePrice.Text, out currDrinkPrice);
            priceDrink += i * currDrinkPrice;

            tbTotalJuicePrice.Text = priceDrink.ToString();
            recalculate();
        }
        private void recalculateBeer()
        {
            float priceDrink = 0;
            int i = 0;
            float currDrinkPrice = 0;
            int.TryParse(tbBeerQuantity.Text, out i);
            float.TryParse(tbBeerPrice.Text, out currDrinkPrice);
            priceDrink += i * currDrinkPrice;

            tbTotalBeerPrice.Text = priceDrink.ToString();
            recalculate();
        }

        private void tbCocaQuantity_TextChanged_1(object sender, EventArgs e)
        {
            recalculateCoca();
        }

        private void tbJuiceQuantity_TextChanged(object sender, EventArgs e)
        {
            recalculateJuice();
        }

        private void tbBeerQuantity_TextChanged(object sender, EventArgs e)
        {
            recalculateBeer();
        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            recalculate();
        }

        private void checkBox2_CheckedChanged(object sender, EventArgs e)
        {
            recalculate();
        }

        private void checkBox3_CheckedChanged(object sender, EventArgs e)
        {
            recalculate();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            lbDeserts.Items.Clear();
            lbDeserts.Items.Add(new Desert("Овошна пита", 80));
            lbDeserts.Items.Add(new Desert("Сладолед", 120));
            lbDeserts.Items.Add(new Desert("Торта", 160));
        }

        private void lbDeserts_SelectedIndexChanged(object sender, EventArgs e)
        {
            tbDeserPrice.Text = "";
            if (lbDeserts.SelectedItems.Count == 0)
            {
                return;
            }
            float price = 0;
            foreach (Desert desert in lbDeserts.SelectedItems)
            {
                price += desert.price;
            }
            tbDeserPrice.Text = price.ToString();
        }

        private void tbDeserPrice_TextChanged(object sender, EventArgs e)
        {
            recalculate();
        }

        private void tbColaPrice_TextChanged(object sender, EventArgs e)
        {
            recalculateCoca();
        }

        private void tbJuicePrice_TextChanged(object sender, EventArgs e)
        {
            recalculateJuice();
        }

        private void tbBeerPrice_TextChanged(object sender, EventArgs e)
        {
            recalculateBeer();
        }

        private void tbGiven_TextChanged(object sender, EventArgs e)
        {
            calculateChange();
        }
        private void calculateChange()
        {
            tbChange.Text = "";

            float money = 0;
            float.TryParse(tbGiven.Text, out money);

            float totalPrice = 0;
            float.TryParse(tbToPayTotal.Text, out totalPrice);

            float change = money - totalPrice;

            if (change >= 0)
            {
                tbChange.Text = change.ToString();
            }
        }

        private void tbToPayTotal_TextChanged(object sender, EventArgs e)
        {
            calculateChange();
        }

        private void btnOrder_Click(object sender, EventArgs e)
        {
            if (tbChange.Text == "")
            {
                MessageBox.Show("Нема платено доволо пари", "грешна уплата", MessageBoxButtons.OK);
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (rbSmall.Checked)
            {
                sb.Append("Мала пица\n");
            }
            else if (rbLarge.Checked)
            {
                sb.Append("Голема пица\n");
            }
            else
            {
                sb.Append("Средна пица");
            }

            if (checkBox1.Checked || checkBox2.Checked || checkBox3.Checked)
            {
                sb.Append("Додатоци:\n");
            }
            if (checkBox1.Checked)
            {
                sb.Append("\tФеферони\n");
            }
            if (checkBox2.Checked)
            {
                sb.Append("\tЕкстра сирење\n");
            }
            if (checkBox3.Checked)
            {
                sb.Append("\tКечап\n");
            }

            int quantityCoca = 0;
            int quantityJuice = 0;
            int quantityBeer = 0;
            int.TryParse(tbCocaQuantity.Text, out quantityCoca);
            int.TryParse(tbJuiceQuantity.Text, out quantityJuice);
            int.TryParse(tbBeerQuantity.Text, out quantityBeer);
            if (quantityCoca != 0 || quantityJuice != 0 || quantityBeer != 0)
            {
                sb.Append("Пијалок:\n");
            }
            if(quantityCoca != 0)
            {
                sb.Append("\t"+quantityCoca + " Кока кола / Фанта / Спрајт\n");
            }
            if (quantityJuice != 0)
            {
                sb.Append("\t" + quantityJuice + " Сок од јаболко / портокал\n");
            }
            if (quantityBeer != 0)
            {
                sb.Append("\t" + quantityBeer + " Пиво\n");
            }

            if (lbDeserts.SelectedItems.Count > 0)
            {
                sb.Append("Десерт:\n");
                foreach (Desert desert in lbDeserts.SelectedItems)
                {
                    sb.Append("\t"+desert.name.ToString() + "\n");
                }
            }

            MessageBox.Show(sb.ToString(), "Вашата нарачка", MessageBoxButtons.OK);
        }
    }
}
