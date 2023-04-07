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
    public partial class AddAirportForm : Form
    {
        public Airport airport { get; set; } = null;
        public AddAirportForm()
        {
            InitializeComponent();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (ValidateChildren())
            {
                airport = new Airport(tbCity.Text, tbName.Text, tbAbbreviation.Text);
                this.DialogResult = DialogResult.OK;
            }
        }

        private void btnAbbort_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void tbCity_Validating(object sender, CancelEventArgs e)
        {
            if(tbCity.Text.Length == 0)
            {
                errorProvider1.SetError(tbCity, "Полето за град не смее да е празно");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbCity, null);
            }
        }

        private void tbName_Validating(object sender, CancelEventArgs e)
        {
            if (tbName.Text.Length == 0)
            {
                errorProvider1.SetError(tbName, "Полето за име не смее да е празно");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbName, null);
            }
        }

        private void tbAbbreviation_Validating(object sender, CancelEventArgs e)
        {
            if (tbAbbreviation.Text.Length != 3 || tbAbbreviation.Text != tbAbbreviation.Text.ToUpper())
            {
                errorProvider1.SetError(tbAbbreviation, "Наставката мора да е од точно 3 големи букви");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbAbbreviation, null);
            }
        }
    }
}
