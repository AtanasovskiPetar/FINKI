using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamProblems
{
    public class Exam
    {
        public int year { get;set; }
        public string month { get;set; }
        public Problem problem1 { get;set; }
        public Problem problem2 { get;set; }

        public Exam(int year, string month)
        {
            this.year = year;
            this.month = month;
            this.problem1 = new Problem();
            this.problem2 = new Problem();
        }

        public Exam(int year, string month, Problem problem2)
        {
            this.year = year;
            this.month = month;
            this.problem1 = problem1;
            this.problem2 = problem2;
        }

        public override string ToString()
        {
            return $"{month} - {year}";
        }
        public void addProblem1(Problem p)
        {
            this.problem1 = p;
        }
        public void addProblem2(Problem p)
        {
            this.problem2 = p;
        }
    }
}
