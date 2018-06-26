package controller;

import java.util.List;

import DataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Actor;
import model.Movie;
import view.MovieAddView;

public class MovieAddActorsController {
Stage stage;
DataBase db;
Movie movie;

Button buttonAddActor;
Button buttonDeleteActor;
Button buttonCencel;
Button buttonSave;
TableView<Actor> tableAllActors;
TableView<Actor> tableSelectedActors;

ObservableList<Actor> list1 = FXCollections.observableArrayList();
ObservableList<Actor> list2 = FXCollections.observableArrayList();

public MovieAddActorsController(Stage stage, DataBase db, Movie movie, Button buttonAddActor, Button buttonDeleteActor, Button buttonCencel, Button buttonSave, TableView<Actor> tableAllActors, TableView<Actor> tableSelectedActors) {
	super();
	this.stage = stage;
	this.db = db;
	this.movie=movie;
	this.buttonAddActor=buttonAddActor;
	this.buttonDeleteActor=buttonDeleteActor;
	this.buttonCencel=buttonCencel;
	this.buttonSave=buttonSave;
	this.tableAllActors=tableAllActors;
	this.tableSelectedActors=tableSelectedActors;
	main();
}

private void main() {
	getFromDBData();
}

private void getFromDBData() {
	List<Actor> list = db.getAllActors();
	list1.addAll(list);
}

public ObservableList<Actor> getActors() {
	return list1;
}
public ObservableList<Actor> getTable2List() {
	return list2;
}

public void addActorToTable2(Actor actor) {
	list1.remove(actor);
	list2.add(actor);
}

public void deleteActorFromTable2(Actor selectedItem) {
	System.out.println("List2: "+list2.size()+" \nlist1: "+list1.size());
	list2.remove(selectedItem);
	list1.add(selectedItem);
	System.out.println("List2: "+list2.size()+" \nlist1: "+list1.size());
}

public void cencelAction() {
	MovieAddView m = new MovieAddView(stage, db, movie);
}

public void saveAction() {
	
	movie.setListActor(list2);
	MovieAddView m = new MovieAddView(stage, db, movie);
}


}
