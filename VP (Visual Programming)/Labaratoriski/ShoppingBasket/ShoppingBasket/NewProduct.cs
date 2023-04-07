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
    public partial class NewProduct : Form
    {
        public Product product { get; set; } = null;
        public NewProduct()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            float price = 0;
            float.TryParse(tbPrice.Text, out price);
            product = new Product(tbName.Text, tbCategory.Text, price);
            this.DialogResult = DialogResult.OK;
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void tbName_Validating(object sender, CancelEventArgs e)
        {
            if (tbName.Text.Length == 0)
            {
                errorProvider1.SetError(tbName, "Не смее да е празно");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbName, null);
            }
        }

        private void tbCategory_Validating(object sender, CancelEventArgs e)
        {
            if (tbCategory.Text.Length == 0)
            {
                errorProvider1.SetError(tbCategory, "Не смее да е празно");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbCategory, null);
            }
        }

        private void tbPrice_Validating(object sender, CancelEventArgs e)
        {
            float price = 0;
            float.TryParse(tbPrice.Text, out price);
            if(price == 0)
            {
                errorProvider1.SetError(tbPrice, "Мора да е број > 0");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbPrice, null);
            }
        }
    }
}
