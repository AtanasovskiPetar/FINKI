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
    public partial class NewDestination : Form
    {
        public Destination destination { get; set; } = null;
        public NewDestination()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (ValidateChildren())
            {
                destination = new Destination(tbDestName.Text, nudDistance.Value, nudPrice.Value);
                this.DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void tbDestName_Validating(object sender, CancelEventArgs e)
        {
            if (tbDestName.Text.Length == 0)
            {
                errorProvider1.SetError(tbDestName, "Не смее да биде празно");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbDestName, null);
            }
        }

        private void nudDistance_Validating(object sender, CancelEventArgs e)
        {
            if (nudDistance.Value <= 0)
            {
                errorProvider1.SetError(nudDistance, "Мора да е поголемо од 0");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(nudDistance, null);
            }
        }

        private void nudPrice_Validating(object sender, CancelEventArgs e)
        {
            if (nudPrice.Value <= 0)
            {
                errorProvider1.SetError(nudPrice, "Мора да е поголемо од 0");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(nudPrice, null);
            }
        }
    }
}
