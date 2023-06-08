using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Busses
{
    public class Bus
    {
        public string Name { get; set; }
        public string Plate { get; set; }
        public bool isLocal { get; set; }
        public List<Line> Lines { get; set; }
        public Bus(string name, string plate, bool isLocal)
        {
            Name = name;
            Plate = plate;
            this.isLocal = isLocal;
            this.Lines = new List<Line>();
        }
        public override string ToString() 
        {
            string local = "";
            if (isLocal) local = "L";
            else local = "M";
            return $"{Name} - {Plate} - {local}";
        }
        public void addLine(Line line)
        {
            Lines.Add(line);
        }
    }
}
