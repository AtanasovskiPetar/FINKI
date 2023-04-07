using System;
using System.Collections.Generic;

namespace av1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int [] niza = { 1, 2, 3, 4 };
            int max = findMax(niza);
            Console.WriteLine(max);
            
            Song s1 = new Song("Bohemian Rapsody", 4, (decimal)1.9);
            Console.WriteLine(s1.ToString());
            Console.ReadKey();
        }
        static int findMax(int[] niza)
        {
            int max = niza[0];
            for(int i=0; i<niza.Length; i++)
            {
               if (niza[i] > max)
                {
                    max = niza[i];
                }
            }
            return max;
        }
        public class Song
        {
            public string Name { get; set; }
            public int Length { get; set; }
            public decimal Rating { get; set; }
            public Song (string Name, int Length, decimal Rating)
            {
                this.Name = Name;
                this.Length = Length;
                this.Rating = Rating;
            }
            public override string ToString()
            {
                return $"Song name {Name} with length {Length} and rating of {Rating}";
            }
        }
    }
}
