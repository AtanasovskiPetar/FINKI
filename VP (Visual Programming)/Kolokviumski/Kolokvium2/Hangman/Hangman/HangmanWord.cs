using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hangman
{
    public class HangmanWord
    {
        public string Word { get; set; }
        public HashSet<Char> LeftChars { get; set; }
        public HashSet<Char> GuessedChars { get; set; }
        public int numTries { get; set; }
        public int maxNumTries { get; set; }

        public HangmanWord(string word, int maxNumTries)
        {
            Word = word;
            this.maxNumTries = maxNumTries;
            this.GuessedChars = new HashSet<Char>();
            this.numTries = 0;
            this.LeftChars = new HashSet<Char>();
            foreach(Char c in this.Word)
            {
                LeftChars.Add(c);
            }
        }
        public void GuessChar(char c)
        {
            if (LeftChars.Contains(c))
            {
                //pogodok
                LeftChars.Remove(c);
                GuessedChars.Add(c);
            }
            else
            {
                if (!GuessedChars.Contains(c))
                {
                    //promash
                    GuessedChars.Add(c);
                    numTries++;
                }
            }
        }
        public string getMaskedWord()
        {
            StringBuilder stringBuilder = new StringBuilder();
            foreach(Char c in this.Word)
            {
                if (LeftChars.Contains(c))
                {
                    stringBuilder.Append("_");
                }
                else
                {
                    stringBuilder.Append(c);
                }
                stringBuilder.Append(' ');
            }
            return stringBuilder.ToString();
        }
        public string getGuessedChars()
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (Char c = 'A'; c<'Z'; c++)
            {
                if (GuessedChars.Contains(c))
                {
                    stringBuilder.Append(c);
                }
                else
                {
                    stringBuilder.Append("_");
                }
                stringBuilder.Append(' ');
            }
            return stringBuilder.ToString();
        }
    }
}
