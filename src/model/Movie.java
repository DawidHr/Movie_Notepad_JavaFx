package model;

import java.util.List;

public class Movie {
private int id;
private String title;
private String file_title;
private String file_url;
private String category;
private String plant;
private String note;
private List<Actor> listActor;
public Movie(int id, String title, String file_title, String file_url, String category, String plant, String note) {
	super();
	this.id = id;
	this.title = title;
	this.file_title = file_title;
	this.file_url = file_url;
	this.category = category;
	this.plant = plant;
	this.note=note;
}
public Movie(String title, String file_title, String file_url, String category, String plant, String note) {
	super();
	this.title = title;
	this.file_title = file_title;
	this.file_url = file_url;
	this.category = category;
	this.plant = plant;
	this.note=note;
}
public int getId() {
	return id;
}
public String getTitle() {
	return title;
}
public String getFile_title() {
	return file_title;
}
public String getFile_url() {
	return file_url;
}
public String getCategory() {
	return category;
}
public String getPlant() {
	return plant;
}
public void setTitle(String title) {
	this.title = title;
}
public void setFile_title(String file_title) {
	this.file_title = file_title;
}
public void setFile_url(String file_url) {
	this.file_url = file_url;
}
public void setCategory(String category) {
	this.category = category;
}
public void setPlant(String plant) {
	this.plant = plant;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public List<Actor> getListActor() {
	return listActor;
}
public void setListActor(List<Actor> listActor) {
	this.listActor = listActor;
}


}
