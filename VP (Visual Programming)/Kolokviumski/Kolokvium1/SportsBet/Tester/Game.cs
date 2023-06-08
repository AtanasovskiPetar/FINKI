using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tester
{
    public class Game
    {
        public Team homeTeam { get; set; }
        public Team awayTeam { get; set; }
        public decimal one {get; set;}
        public decimal x { get; set; }
        public decimal two { get; set; }
        public string code { get; set; }
        public Game(Team homeTeam, Team awayTeam, decimal one, decimal x, decimal two, string code)
        {
            this.one = one;
            this.x = x;
            this.two = two;
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.code = code;
        }
        public override string ToString()
        {
            return $"{homeTeam.Name} - {awayTeam.Name} ({code})";
        }
    }
}
