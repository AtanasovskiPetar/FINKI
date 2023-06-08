using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Busses
{
    public class Line
    {
        public string Destination { get; set; }
        public int Hour { get; set; }
        public int Minute { get; set; }
        public float Price { get; set; }

        public Line(string destination, int hour, int minute, float price)
        {
            Destination = destination;
            Hour = hour;
            Minute = minute;
            Price = price;
        }
        public override string ToString()
        {
            return $"{Hour}:{Minute} - {Destination} - {Price} Ден.";
        }
    }
}
