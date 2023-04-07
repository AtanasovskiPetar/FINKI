using System;
namespace AV3
{
	public class Song
	{
		string Name { get; set; }
		int Length { get; set; }
		decimal Rating { get; set; }
        public Song(string Name, int Length, decimal Rating)
		{
			this.Name = Name;
			this.Length = Length;
			this.Rating = Rating;
		}
        public override string ToString()
        {
			return $"Song Name: {Name} - {Length}";
        }
		public string getName()
		{
			return this.Name;
		}
    }
}

