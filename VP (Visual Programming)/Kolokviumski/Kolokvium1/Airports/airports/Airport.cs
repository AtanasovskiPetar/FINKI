using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace airports
{
    public class Airport
    {
        public string city { get; set; }
        public string name { get; set; } 
        public string abbreviation { get; set; }
        public List<Destination> destinations { get; set; }

        public Airport(string city, string name, string abbreviation)
        {
            this.city = city;
            this.name = name;
            this.abbreviation = abbreviation;
            this.destinations = new List<Destination>();
        }
        public void addDestination(Destination destination)
        {
            destinations.Add(destination);
        }
        public override string ToString()
        {
            return $"{abbreviation} - {name} - {city}";
        }
    }
}
