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
    public partial class ExamProblems : Form
    {
        public ExamProblems()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            NewExam newExamForm = new NewExam();
            if(newExamForm.ShowDialog() == DialogResult.OK)
            {
                Exam exam = newExamForm.exam;
                lbExams.Items.Add(exam);
            }
        }

        private void lbExams_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(lbExams.SelectedItems.Count == 0)
            {
                return;
            }
            Exam exam = lbExams.SelectedItem as Exam;
            tbDescriptionProblem1.Text = exam.problem1.description;
            tbDescriptionProblem2.Text = exam.problem2.description;
            nudPointsProblem1.Value = (decimal)exam.problem1.points;
            nudPointsProblem2.Value = (decimal)exam.problem2.points;
        }

        private void btnSaveProblem1_Click(object sender, EventArgs e)
        {
            if(lbExams.SelectedItem == null)
            {
                return;
            }
            if (tbDescriptionProblem1.Text.Length == 0)
            {
                return;
            }
            Exam examToEdit = lbExams.SelectedItem as Exam;
            string text = tbDescriptionProblem1.Text;
            float points = (int)nudPointsProblem1.Value;
            examToEdit.addProblem1(new Problem(text, points));
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (lbExams.SelectedItem == null)
            {
                return;
            }
            lbExams.Items.Remove(lbExams.SelectedItem);
            lbExams.SelectedItems.Clear();
            tbDescriptionProblem1.Text = "";
            tbDescriptionProblem2.Text = "";
            nudPointsProblem1.Value = 0;
            nudPointsProblem2.Value = 0;
        }

        private void btnSaveProblem2_Click(object sender, EventArgs e)
        {
            if (lbExams.SelectedItem == null)
            {
                return;
            }
            if (tbDescriptionProblem2.Text.Length == 0)
            {
                return;
            }
            Exam examToEdit = lbExams.SelectedItem as Exam;
            string text = tbDescriptionProblem2.Text;
            int points = (int)nudPointsProblem2.Value;
            examToEdit.addProblem2(new Problem(text, points));
        }
    }
}
