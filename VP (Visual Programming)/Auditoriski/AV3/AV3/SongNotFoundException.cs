using System;
namespace AV3
{
	public class SongNotFoundException : Exception
	{
		public string Name { get; set; }
		public SongNotFoundException(string Name)
		{
			this.Name = Name;
		}
	}
}

