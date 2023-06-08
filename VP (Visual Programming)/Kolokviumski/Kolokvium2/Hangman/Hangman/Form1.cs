using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Hangman
{
    public partial class Form1 : Form
    {
        public HangmanWord word { get; set; }
        public int timeLeft = 120;
        public string endMessage;
        public List<String> wordsList = new List<string>( new string [] { "bubachka", "argentina", "makedonija", "lefkada", "ubajna", "loveyou" });
        

        public Form1()
        {
            InitializeComponent();
            Random rnd = new Random();
            int rand = rnd.Next(wordsList.Count);
            string wrd = wordsList[rand].ToUpper();
            word = new HangmanWord(wrd, 5);
            lbWord.Text = word.getMaskedWord();
            lbGuessed.Text = word.getGuessedChars();
            timer1.Start();
            pbTimer.Value = timeLeft;
            lbTimer.Text = "02:00";
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            if (tbEntryChar.Text.Length <= 0)
            {
                return;
            }
            char entry = tbEntryChar.Text.ToUpper()[0];
            word.GuessChar(entry);
            lbWord.Text = word.getMaskedWord();
            lbGuessed.Text = word.getGuessedChars();
            tbEntryChar.Text = "";
            pbError.Value = (int)(100.0 * word.numTries / word.maxNumTries);
            if ((int) (100.0 * word.numTries / word.maxNumTries) == 100)
            {
                this.endMessage = "More tries than legal";
                MessageBox.Show(this.endMessage);
                this.Close();
            }
            if(this.word.LeftChars.Count == 0)
            {
                this.endMessage = "CONGRATS";
                MessageBox.Show(this.endMessage);
                this.Close();
            }
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            timeLeft--;
            pbTimer.Value = timeLeft;
            lbTimer.Text = $"{(int)(timeLeft / 60)}:{timeLeft%60}";
            if (timeLeft <= 0)
            {
                this.endMessage = "Time limit exceedede";
                MessageBox.Show(this.endMessage);
                this.Close();
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
