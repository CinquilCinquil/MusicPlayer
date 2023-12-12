package entity;

public class Song
{
	private int id;
	private String name, artist, filepath;

	public Song(int id, String name, String artist, String filepath)
	{
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.filepath = filepath;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}