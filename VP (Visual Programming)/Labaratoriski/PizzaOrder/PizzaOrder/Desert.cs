using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PizzaOrder
{
    public class Desert
    {
        public string name { get; set; }
        public float price { get; set; }

        public Desert(string name, float price)
        {
            this.name = name;
            this.price = price;
        }
        public override string ToString() 
        {
            return $"{name}";
        }
    }
}
