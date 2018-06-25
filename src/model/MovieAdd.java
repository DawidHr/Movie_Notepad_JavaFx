package model;

import java.io.File;
import java.util.List;

public class MovieAdd {
private String title;
private File file;
private String category;
private String plant;
private List<Actor> actors;
private String note;
public MovieAdd(String title, File file, String category, String plant, List<Actor> actors, String note) {
	super();
	this.title = title;
	this.file = file;
	this.category = category;
	this.plant = plant;
	this.actors = actors;
	this.note = note;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public File getFile() {
	return file;
}
public void setFile(File file) {
	this.file = file;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getPlant() {
	return plant;
}
public void setPlant(String plant) {
	this.plant = plant;
}
public List<Actor> getActors() {
	return actors;
}
public void setActors(List<Actor> actors) {
	this.actors = actors;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}



}
