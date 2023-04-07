using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Automobiles
{
    public class Car
    {
        public Brand Brand {  get; set; }
        public string Model { get; set; }
        public decimal Consumption { get; set; }
        public decimal Price { get; set; }

        public Car(Brand brand, string model, decimal consumption, decimal price)
        {
            Brand = brand;
            Model = model;
            Consumption = consumption;
            Price = price;
        }
        public override string ToString()
        {
            return $"{Brand.Name} {Model} - Price: {Price}, Consumption: {Consumption}";
        }
        public bool EqualsCar(Car obj)
        {
            return (this.Model.Equals(obj.Model) && this.Brand.Name.Equals(obj.Brand.Name));
        }
    }
}
