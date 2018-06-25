package model;

public class Actor {

	private int id;
	private String name;
	private String name2;
	private String pseudo;
	private String sex;
	private String hair_color;
	private String hair_size;
	private String note;
	public Actor(int id, String name, String name2, String pseudo, String sex, String hair_color, String hair_size,
			String note) {
		super();
		this.id = id;
		this.name = name;
		this.name2 = name2;
		this.pseudo = pseudo;
		this.sex = sex;
		this.hair_color = hair_color;
		this.hair_size = hair_size;
		this.note = note;
	}
	
	public Actor(String name, String name2, String pseudo, String sex, String hair_color, String hair_size,
			String note) {
		super();
		this.name = name;
		this.name2 = name2;
		this.pseudo = pseudo;
		this.sex = sex;
		this.hair_color = hair_color;
		this.hair_size = hair_size;
		this.note = note;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getName2() {
		return name2;
	}
	public String getPseudo() {
		return pseudo;
	}
	public String getSex() {
		return sex;
	}
	public String getHair_color() {
		return hair_color;
	}
	public String getHair_size() {
		return hair_size;
	}
	public String getNote() {
		return note;
	}
	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}
	public void setHair_size(String hair_size) {
		this.hair_size = hair_size;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", name2=" + name2 + ", pseudo=" + pseudo + ", sex=" + sex
				+ ", hair_color=" + hair_color + ", hair_size=" + hair_size + ", note=" + note + "]";
	}
	
	
}
