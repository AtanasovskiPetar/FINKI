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
    public partial class BrandForm : Form
    {
        public Brand brand { get; set; } = null;
        public BrandForm()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            brand = new Brand(tbName.Text, tbCode.Text);
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
                errorProvider1.SetError(tbName, "Мора да има име");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbName, null);
            }
        }

        private void tbCode_Validating(object sender, CancelEventArgs e)
        {
            if (tbCode.Text.Length == 0)
            {
                errorProvider1.SetError(tbCode, "Мора да има код");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbCode, null);
            }
        }
    }
}
