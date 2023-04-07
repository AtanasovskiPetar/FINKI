// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AV3
{
    class Program
    {
        public static void Main(string[] args)
        {
            Album myAlbum1 = new Album("BEST_Album", 2023);
            myAlbum1.addSong("song1", 255, 9.9f);
            myAlbum1.addSong("song2", 234, 9.2f);
            Console.WriteLine(myAlbum1.ToString());
            Console.WriteLine("Find method....\n");
            string songToFind = Console.ReadLine();
            try
            {
                Song found = myAlbum1.findSong(songToFind);
                Console.WriteLine("Found: " + found.ToString());
            }
            catch (SongNotFoundException e)
            {
                Console.WriteLine("Cannot find the song: " + e.Name.ToString());
            }
            catch (Exception)
            {
                Console.WriteLine("You didn't enter a song");
            }
            Console.ReadKey();
        }
    }
    
}
