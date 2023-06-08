using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ExamProblems
{
    public partial class NewExam : Form
    {
        public Exam exam { get;set; }
        public NewExam()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            exam = new Exam((int)nudYear.Value, cbMonth.Text);
            this.DialogResult = DialogResult.OK;
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult= DialogResult.Cancel;
        }

        private void nudYear_Validating(object sender, CancelEventArgs e)
        {
            if(nudYear.Value < 0)
            {
                errorProvider1.SetError(nudYear, "Поголема од 0");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(nudYear, null);
            }
        }

        private void cbMonth_Validating(object sender, CancelEventArgs e)
        {
            if (cbMonth.Text.Length == 0)
            {
                errorProvider1.SetError(cbMonth, "Избери месец");
                e.Cancel = true;
            }
            else
            {
                errorProvider1.SetError(cbMonth, null);
            }
        }
    }
}
