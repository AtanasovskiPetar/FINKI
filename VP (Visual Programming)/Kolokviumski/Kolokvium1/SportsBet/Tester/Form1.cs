using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.Design;

namespace Tester
{
    public partial class Kladilnica : Form
    {
        public Kladilnica()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            cbType.Items.Add("1");
            cbType.Items.Add("X");
            cbType.Items.Add("2");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            TeamForm teamAddForm = new TeamForm();

            if(teamAddForm.ShowDialog() == DialogResult.OK)
            {
                Team team = teamAddForm.createdTeam;
                lbTeams.Items.Add(team);
            }
        }

        private void lbTeams_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnAddToBilten_Click(object sender, EventArgs e)
        {

            if (lbTeams.SelectedItems.Count == 2)
            {
                Team home = lbTeams.SelectedItems[0] as Team;
                Team away = lbTeams.SelectedItems[1] as Team;
                Game newGame = new Game(home, away, (decimal) nud1.Value, (decimal)nudX.Value, (decimal) nud2.Value, mtbCode.Text);
                lbBilten.Items.Add(newGame);
                lbTeams.SelectedItems.Clear();
                nud1.Value = 1;
                nudX.Value = 1;
                nud2.Value = 1;
                mtbCode.Clear();
            }
        }

        private void mtbCode_MaskInputRejected(object sender, MaskInputRejectedEventArgs e)
        {

        }

        private void tbGameCode_TextChanged(object sender, EventArgs e)
        {
            lbBilten.SelectedItems.Clear();
            string code = tbGameCode.Text;
            Game gameToAdd = null;
            if(code.Length == 4)
            {
                foreach (Game game in lbBilten.Items)
                {
                    if(game.code == code)
                    {
                        gameToAdd = game;
                    }
                }
            }
            if(gameToAdd != null)
            {
                lbBilten.SelectedItems.Add(gameToAdd);
                
            }
        }

        private void cbType_Click(object sender, EventArgs e)
        {
            
        }

        private void btnAddToTicket_Click(object sender, EventArgs e)
        {
            if(lbBilten.SelectedItems.Count == 1 && (cbType.Text == "1" || cbType.Text == "X") || cbType.Text == "2")
            {
                Game game = lbBilten.SelectedItem as Game;
                string coef = cbType.Text;
                TicketGame ticketGame = new TicketGame(game, coef);
                lbTicket.Items.Add(ticketGame);
                changeCоеfAndWin();
            }
            
        }

        private void changeCоеfAndWin()
        {
            decimal coef = (decimal) 1.0;
            foreach(TicketGame ticketGame in lbTicket.Items)
            {
                coef *= ticketGame.coef;
            }
            tbTotalCoef.Text = coef.ToString();
            tbDobivka.Text = (coef * nudUplata.Value).ToString();
        }

        private void nudUplata_ValueChanged(object sender, EventArgs e)
        {
            changeCоеfAndWin();
        }
    }
}
