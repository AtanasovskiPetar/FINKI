using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace airports
{
    public class Destination
    {

        public string name {  get; set; }
        public int distance { get; set; }
        public int price { get; set; }

        public Destination(string name, decimal distance, decimal price)
        {
            this.name = name;
            this.distance = (int) distance;
            this.price =  (int) price;
        }


        public override string ToString()
        {
            return $"{name} {distance}km - {price}EUR";
        }
    }
}
