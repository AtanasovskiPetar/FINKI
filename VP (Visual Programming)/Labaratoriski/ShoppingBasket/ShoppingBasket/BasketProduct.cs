using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ShoppingBasket
{
    public class BasketProduct
    {
        public Product Product { get; set; }
        public int Qunatity { get; set; }

        public BasketProduct(Product product, int qunatity)
        {
            Product = product;
            Qunatity = qunatity;
        }
        public float getPrice()
        {
            return Product.price * Qunatity;
        }
        public override string ToString()
        {
            return String.Format("{0} {1:0.0} x {2:0.0} = {3:0.00}", Product.name, Qunatity, Product.price, getPrice());
        }
    }
}
