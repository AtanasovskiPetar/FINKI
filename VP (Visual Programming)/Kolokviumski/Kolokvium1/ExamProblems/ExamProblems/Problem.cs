using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamProblems
{
    public class Problem
    {
        public string description { get; set; }
        public float points { get; set; }

        public Problem(string description="", float points=0)
        {
            this.description = description;
            this.points = points;
        }
    }
}
