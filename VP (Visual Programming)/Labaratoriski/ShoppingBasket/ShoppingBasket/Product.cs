using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ShoppingBasket
{
    public class Product
    {
        public string name {  get; set; }
        public string category { get; set; }
        public float price { get; set; }

        public Product(string name, string category, float price)
        {
            this.name = name;
            this.category = category;
            this.price = price;
        }
        public override string ToString()
        {
            return $"{name}";
        }
    }
}
