using System;
using System.Text;

namespace AV3
{
	public class Album
	{
		string Name { get; set; }
		int ReleaseYear { get; set; }
		List<Song> Songs { get; set; }

		public Album(string Name, int ReleaseYear)
		{
			this.Name = Name;
			this.ReleaseYear = ReleaseYear;
			this.Songs = new List<Song>();
		}
		public void addSong(string name, int duration, float rating)
		{
			this.Songs.Add(new Song(name, duration, (decimal)rating));
		}
		public Song findSong(string name)
		{
			foreach (Song s in Songs)
			{
				if (s.getName().Equals(name, StringComparison.OrdinalIgnoreCase))
				{
					return s;
				}
			}
			throw new SongNotFoundException(name);

        }
        public override string ToString()
        {
			StringBuilder sb = new StringBuilder();
			sb.Append(this.Name + "\n");
            sb.Append(this.ReleaseYear.ToString() + "\n");
            sb.Append("Songs:"+ "\n");
			foreach (Song s in this.Songs)
			{
				sb.Append(s.ToString() + "\n");
			}
            return sb.ToString();
        }
    }
}

