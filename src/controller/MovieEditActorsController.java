package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import DataBase.DataBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Actor;
import model.Movie;
import view.MovieEditActorsView;

public class MovieEditActorsController {
Stage stage;
DataBase db;
Movie movie;
//1 dodawanie, 2. edycja
int mode;

MovieEditActorsView view;
ObservableList<Actor> list1 = FXCollections.observableArrayList();
ObservableList<Actor> list2 = FXCollections.observableArrayList();

public MovieEditActorsController(Stage stage, DataBase db, Movie movie, int mode) {
	super();
	System.out.println("MovieEditActorsController");
	this.stage = stage;
	this.db = db;
	this.movie=movie;
	this.mode=mode;
	view = new MovieEditActorsView(stage, db);
	if(mode == 1)
		 MainForAdd();
	 else
		 MainForEdit();
}

private void MainForAdd() {
	getFromDBData();
	if(list1.isEmpty()) {
		view.getButtonAddActor().setDisable(true);
		view.getButtonDeleteActor().setDisable(true);
		view.getButtonSave().setDisable(true);
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Brak Listy Aktorow");
		dialog.setHeaderText("Lista Aktorow jest pusta");
		Platform.runLater(() ->{
			Optional<ButtonType> response = dialog.showAndWait();
		});
	}
	view.setTableAllActors(list1);
	view.setTableSelectedActors(list2);
	setActionsForButtons();
}

private void MainForEdit() {
	
	
	
	
	List<Actor> list11 = movie.getListActor();
	if(list11 != null)
	list2.addAll(list11);
	getFromDBData();
	//Kasacja Aktorów znajdujacych siê w tabeli 2 by sie nie powtarzali
	for(int i=0;i< list2.size();i++) {
		for(int j=0; j< list1.size();j++) {
			if(list1.get(j).getId() == list2.get(i).getId()) {
				list1.remove(j);
			}
		}
	}
	
	if(list1.isEmpty() && list2.isEmpty()) {
		view.getButtonAddActor().setDisable(true);
		view.getButtonDeleteActor().setDisable(true);
		view.getButtonSave().setDisable(true);
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Brak Listy Aktorow");
		dialog.setHeaderText("Lista Aktorow jest pusta");
		Platform.runLater(() ->{
			Optional<ButtonType> response = dialog.showAndWait();
		});
	}
	view.setTableAllActors(list1);
	view.setTableSelectedActors(list2);
	setActionsForButtons();
}


//Pobieranie wszystkich aktorow z bazy danych i przypisanie do List
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

private void setActionsForButtons() {
	Button buttonAdd = view.getButtonAddActor();
	Button buttonDelete = view.getButtonDeleteActor();
	Button buttonCencel = view.getButtonCencel();
	Button buttonSave = view.getButtonSave();
	
	buttonAdd.setOnAction(e-> addActorAction(view.getTableAllActors().getSelectionModel().getSelectedItem()));
	buttonDelete.setOnAction(e-> deleteActorAction(view.getTableSelectedActors().getSelectionModel().getSelectedItem()));
	buttonCencel.setOnAction(e-> cencelAction());
	buttonSave.setOnAction(e-> saveAction());
}

private void addActorAction(Actor selectedItem) {
	if(selectedItem != null) {
		addActorToTable2(selectedItem);
		checkifListIsEmpty();
		checkIfList2IsEmpty();
	}
}

private void checkifListIsEmpty() {
if(list1.isEmpty()) {
	view.getButtonAddActor().setDisable(true);
}
else {
	view.getButtonAddActor().setDisable(false);
}
}
private void checkIfList2IsEmpty() {
if(list2.isEmpty()) {
	view.getButtonDeleteActor().setDisable(true);
}
else {
	view.getButtonDeleteActor().setDisable(false);
}
}

public void addActorToTable2(Actor actor) {
	list1.remove(actor);
	list2.add(actor);
}

private void deleteActorAction(Actor selectedItem) {
	if(view.getTableSelectedActors().getSelectionModel().getSelectedItem() != null) { 
		deleteActorFromTable2(selectedItem);
		checkifListIsEmpty();
		checkIfList2IsEmpty();	
	}
}


public void deleteActorFromTable2(Actor selectedItem) {
	System.out.println("List2: "+list2.size()+" \nlist1: "+list1.size());
	list2.remove(selectedItem);
	list1.add(selectedItem);
	System.out.println("List2: "+list2.size()+" \nlist1: "+list1.size());
}

public void cencelAction() {
	movie.setListActor(new LinkedList<Actor>());
	MovieEditController m = new MovieEditController(stage, db, movie, mode);
}

public void saveAction() {
	
	movie.setListActor(list2);
	MovieEditController m = new MovieEditController(stage, db, movie, mode);
}


}
