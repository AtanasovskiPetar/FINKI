using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ShoppingBasket
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnEmptyList_Click(object sender, EventArgs e)
        {
            DialogResult dr = MessageBox.Show("Дали си сигурен дека сакаш да ја избришиш целата листа?", "Бришење на цела листа", MessageBoxButtons.YesNo);
            if(dr == DialogResult.Yes)
            {
                lbProducts.Items.Clear();
            }
        }

        private void btnAddNewProduct_Click(object sender, EventArgs e)
        {
            NewProduct newProdutForm = new NewProduct();
            if(newProdutForm.ShowDialog() == DialogResult.OK)
            {
                Product product = newProdutForm.product;
                lbProducts.Items.Add(product);
            }
        }

        private void lbProducts_SelectedIndexChanged(object sender, EventArgs e)
        {
            tbName.Text = "";
            tbCategory.Text = "";
            tbPrice.Text = "";
            if (lbProducts.SelectedItem != null)
            {
                Product product = lbProducts.SelectedItem as Product;
                tbName.Text = product.name;
                tbCategory.Text = product.category;
                tbPrice.Text = product.price.ToString();
            }
        }

        private void btnAddToBasket_Click(object sender, EventArgs e)
        {
            if(lbProducts.SelectedItem != null)
            {
                int quantity = (int) nudProductQuantity.Value;
                Product productToAdd = lbProducts.SelectedItem as Product;
                lbBasket.Items.Add(new BasketProduct(productToAdd, quantity));
                recalculate();
                nudProductQuantity.Value = 1;
            }
        }

        private void recalculate()
        {
            tbTotal.Text = "";
            if (lbBasket.Items.Count == 0)
            {
                return;
            }
            float totalSum = 0;
            foreach(BasketProduct produuct in lbBasket.Items)
            {
                totalSum += produuct.getPrice();
            }

            tbTotal.Text = String.Format("{0:0.00}", totalSum);
        }

        private void btnDeletefromBasket_Click(object sender, EventArgs e)
        {
            if (lbBasket.SelectedItems.Count == 0)
            {
                return;
            }
            lbBasket.Items.Remove(lbBasket.SelectedItem);
            recalculate();
        }

        private void btnDeleteProduct_Click(object sender, EventArgs e)
        {
            if (lbProducts.SelectedItems.Count == 0)
            {
                return;
            }
            lbProducts.Items.Remove(lbProducts.SelectedItem);
        }

        private void btnEmptyBasket_Click(object sender, EventArgs e)
        {
            DialogResult dr = MessageBox.Show("Дали си сигурен дека сакаш да ја избришиш целата кошничка?", "Бришење на цела кошничка", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                lbBasket.Items.Clear();
                recalculate();
            }
        }
    }
}
