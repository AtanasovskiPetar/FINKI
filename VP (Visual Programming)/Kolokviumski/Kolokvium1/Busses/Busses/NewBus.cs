using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics.Eventing.Reader;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Busses
{
    public partial class NewBus : Form
    {
        public Bus bus { get; set; }
        public NewBus()
        {
            InitializeComponent();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            bus = new Bus(tbBusName.Text, tbBusPlate.Text, rbIsLocal.Checked);
            this.DialogResult = DialogResult.OK;
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void tbBusName_Validating(object sender, CancelEventArgs e)
        {
            if (tbBusName.Text.Length == 0)
            {
                errorProvider1.SetError(tbBusName, "Не смее да е празно полето за име");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(tbBusName, null);
            }
        }

        private void tbBusPlate_Validating(object sender, CancelEventArgs e)
        {
            if(tbBusPlate.Text.Length != 4)
            {
                errorProvider1.SetError(tbBusPlate, "Должината мора да е 4");
                e.Cancel = true;
                return;
            }
            foreach (Char c in tbBusPlate.Text)
            {
                if (!char.IsDigit(c))
                {
                    errorProvider1.SetError(tbBusPlate, "Сите мора да се цифри");
                    e.Cancel = true;
                    return;
                }
            }
            errorProvider1.SetError(tbBusPlate, null);
        }
    }
}
