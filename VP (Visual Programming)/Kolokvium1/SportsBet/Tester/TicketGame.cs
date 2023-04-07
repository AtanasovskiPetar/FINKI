using Microsoft.SqlServer.Server;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tester
{
    public class TicketGame
    {
        Game ticketGame { get; set; }
        public decimal coef { get; set; }
        string coefType;

        public TicketGame(Game ticketGame, string coef)
        {
            this.ticketGame = ticketGame;
            this.coefType = coef;
            if (coef == "1")
            {
                this.coef = ticketGame.one;
            }
            else if (coef == "X") 
            {
                this.coef = ticketGame.x;
            }
            else
            {
                this.coef = ticketGame.two;
            }
        }
        public override string ToString()
        {
            return $"{ticketGame} {coefType}";
        }
    }
}
