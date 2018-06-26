package view;

import java.util.Optional;

import DataBase.DataBase;
import controller.MovieAddActorsController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Actor;
import model.Movie;

public class MovieAddActorsView {
Stage stage;
DataBase db;
MovieAddActorsController controller;
Movie movie;
GridPane panel = new GridPane();

Button buttonAddActor = new Button("Dodaj");
Button buttonDeleteActor = new Button("Kasuj");
Button buttonCencel = new Button("Wstecz");
Button buttonSave = new Button("Zapisz");

TableView<Actor> tableAllActors = new TableView<>();
TableColumn<Actor, String> col1 = new TableColumn<Actor, String>("Imiê");
TableColumn<Actor, String> col2 = new TableColumn<Actor, String>("Nazwisko");
TableColumn<Actor, String> col3 = new TableColumn<Actor, String>("Pseudonim");
TableColumn<Actor, String> col4 = new TableColumn<Actor, String>("P³eæ");
ObservableList<Actor> list = FXCollections.observableArrayList();

TableView<Actor> tableSelectedActors = new TableView<>();
TableColumn<Actor, String> col11 = new TableColumn<Actor, String>("Imiê");
TableColumn<Actor, String> col12 = new TableColumn<Actor, String>("Nazwisko");
TableColumn<Actor, String> col13 = new TableColumn<Actor, String>("Pseudonim");
TableColumn<Actor, String> col14 = new TableColumn<Actor, String>("P³eæ");
ObservableList<Actor> list2 = FXCollections.observableArrayList();


public MovieAddActorsView(Stage stage, DataBase db, Movie movie) {
	super();
	this.stage = stage;
	this.db = db;
	this.movie=movie;
	controller = new MovieAddActorsController(stage, db, movie, buttonAddActor, buttonDeleteActor, buttonCencel, buttonSave, tableAllActors, tableSelectedActors);
	main();
	setStage();
}

private void main() {
	setTable();
	setTable2();
	checkifListIsEmpty();
	checkIfList2IsEmpty();
	setColumnSize();
	setRowSize();
	setNodesToPanel();
	setButtons();
}

private void checkifListIsEmpty() {
	if(list.isEmpty()) {
		buttonAddActor.setDisable(true);
	}
	else {
		buttonAddActor.setDisable(false);
	}
}
private void checkIfList2IsEmpty() {
	if(list2.isEmpty()) {
		buttonDeleteActor.setDisable(true);
	}
	else {
		buttonDeleteActor.setDisable(false);
	}
}
private void setTable() {
	col1.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
	col2.setCellValueFactory(new PropertyValueFactory<Actor, String>("name2"));
	col3.setCellValueFactory(new PropertyValueFactory<Actor, String>("pseudo"));
	col4.setCellValueFactory(new PropertyValueFactory<Actor, String>("sex"));
	tableAllActors.getColumns().addAll(col1, col2, col3, col4);
	list = controller.getActors();
	if(list.isEmpty()) {
		System.out.println("jestem w alert");
		buttonAddActor.setDisable(true);
		buttonDeleteActor.setDisable(true);
		buttonSave.setDisable(true);
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Brak Listy Aktorow");
		dialog.setHeaderText("Lista Aktorow jest pusta");
		Platform.runLater(() ->{
			Optional<ButtonType> response = dialog.showAndWait();
		});
	}
	else {
	
		tableAllActors.setItems(list);
	}
}

private void setTable2() {
	col11.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
	col12.setCellValueFactory(new PropertyValueFactory<Actor, String>("name2"));
	col13.setCellValueFactory(new PropertyValueFactory<Actor, String>("pseudo"));
	col14.setCellValueFactory(new PropertyValueFactory<Actor, String>("sex"));
	tableSelectedActors.getColumns().addAll(col11, col12, col13, col14);
	tableSelectedActors.setItems(list2);
}


private void setColumnSize() {
	ColumnConstraints c1 = new ColumnConstraints();
	c1.setPercentWidth(40);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(20);
	ColumnConstraints c3 = new ColumnConstraints();
	c3.setPercentWidth(40);
	panel.getColumnConstraints().addAll(c1, c2, c3);
}

private void setRowSize() {
	RowConstraints r1 = new RowConstraints();
	r1.setPercentHeight(20);
	RowConstraints r2 = new RowConstraints();
	r2.setPercentHeight(20);
	RowConstraints r3 = new RowConstraints();
	r3.setPercentHeight(20);
	RowConstraints r4 = new RowConstraints();
	r4.setPercentHeight(20);
	RowConstraints r5 = new RowConstraints();
	r5.setPercentHeight(20);
	panel.getRowConstraints().addAll(r1, r2, r3, r4, r5);
}

private void setNodesToPanel() {
	panel.setConstraints(tableSelectedActors, 0, 0, 1, 4);
	panel.setConstraints(tableAllActors, 2, 0, 1, 4);
	panel.setConstraints(buttonAddActor, 1, 1, 1, 1);
	panel.setConstraints(buttonDeleteActor, 1, 2, 1, 1);
	panel.setConstraints(buttonCencel, 0, 4, 1, 1);
	panel.setConstraints(buttonSave, 2, 4, 1, 1);
	panel.getChildren().addAll(tableAllActors, tableSelectedActors, buttonAddActor, buttonCencel, buttonDeleteActor, buttonSave);
}

private void setButtons() {
	buttonAddActor.setOnAction(e-> {
		if(tableAllActors.getSelectionModel().getSelectedItem() != null) {
		controller.addActorToTable2(tableAllActors.getSelectionModel().getSelectedItem());
		tableAllActors.getItems().removeAll();
		list=null;
		list = controller.getActors();
		tableAllActors.setItems(list);
		tableSelectedActors.getItems().removeAll();
		list2=null;
		list2 = controller.getTable2List();
		tableSelectedActors.setItems(list2);
		checkifListIsEmpty();
		checkIfList2IsEmpty();
		}
	});
	
	buttonDeleteActor.setOnAction(e-> {
		if(tableSelectedActors.getSelectionModel().getSelectedItem() != null) { 
			controller.deleteActorFromTable2(tableSelectedActors.getSelectionModel().getSelectedItem());
			System.out.println("List2: "+list2.size()+" \nlist1: "+list.size());
			
			tableAllActors.getItems().removeAll();
			list=null;
			list = controller.getActors();
			tableAllActors.setItems(list);
			tableSelectedActors.getItems().removeAll();
			list2=null;
			list2 = controller.getTable2List();
			tableSelectedActors.setItems(list2);
			checkifListIsEmpty();
			checkIfList2IsEmpty();
			
		}
	});
	
	buttonCencel.setOnAction(e-> controller.cencelAction());
	buttonSave.setOnAction(e-> controller.saveAction());
}

public void setStage() {
	Scene root = new Scene(panel, 400, 400);
	stage.setScene(root);
	stage.show();
}
}
