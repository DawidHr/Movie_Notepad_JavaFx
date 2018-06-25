package controller;

import java.util.List;

import DataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Actor;

public class MovieAddActorsController {
Stage stage;
DataBase db;

ObservableList<Actor> list1 = FXCollections.observableArrayList();
ObservableList<Actor> list2 = FXCollections.observableArrayList();

public MovieAddActorsController(Stage stage, DataBase db) {
	super();
	this.stage = stage;
	this.db = db;
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

public Object cencelAction() {
	// TODO Auto-generated method stub
	return null;
}


}
