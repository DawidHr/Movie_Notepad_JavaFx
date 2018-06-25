package model;

public class Cinema {
private int id;
private String city;
private String cinema;
public Cinema(int id, String city, String cinema) {
	super();
	this.id = id;
	this.city = city;
	this.cinema = cinema;
}
public Cinema(String city, String cinema) {
	super();
	this.city = city;
	this.cinema = cinema;
}
public int getId() {
	return id;
}
public String getCity() {
	return city;
}
public String getCinema() {
	return cinema;
}
public void setCity(String city) {
	this.city = city;
}
public void setCinema(String cinema) {
	this.cinema = cinema;
}

}
