package view;

import java.util.Optional;

import DataBase.DataBase;
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

public class MovieEditActorsView {
Stage stage;
DataBase db;

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


TableView<Actor> tableSelectedActors = new TableView<>();
TableColumn<Actor, String> col11 = new TableColumn<Actor, String>("Imiê");
TableColumn<Actor, String> col12 = new TableColumn<Actor, String>("Nazwisko");
TableColumn<Actor, String> col13 = new TableColumn<Actor, String>("Pseudonim");
TableColumn<Actor, String> col14 = new TableColumn<Actor, String>("P³eæ");



public MovieEditActorsView(Stage stage, DataBase db) {
	super();
	System.out.println("Movie edit actor view");
	this.stage = stage;
	this.db = db;
	main();
	setStage();
}

private void main() {
	setTable();
	setTable2();
	setColumnSize();
	setRowSize();
	setNodesToPanel();
}

public TableView<Actor> getTableAllActors() {
	return tableAllActors;
}

public void setTableAllActors(ObservableList<Actor> list1) {
	this.tableAllActors.setItems(list1);
}

public TableView<Actor> getTableSelectedActors() {
	return tableSelectedActors;
}

public void setTableSelectedActors(ObservableList<Actor> list) {
	this.tableSelectedActors.setItems(list);
}

public Button getButtonAddActor() {
	return buttonAddActor;
}

public Button getButtonDeleteActor() {
	return buttonDeleteActor;
}

public Button getButtonCencel() {
	return buttonCencel;
}

public Button getButtonSave() {
	return buttonSave;
}

private void setTable() {
	col1.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
	col2.setCellValueFactory(new PropertyValueFactory<Actor, String>("name2"));
	col3.setCellValueFactory(new PropertyValueFactory<Actor, String>("pseudo"));
	col4.setCellValueFactory(new PropertyValueFactory<Actor, String>("sex"));
	tableAllActors.getColumns().addAll(col1, col2, col3, col4);
}

private void setTable2() {
	col11.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
	col12.setCellValueFactory(new PropertyValueFactory<Actor, String>("name2"));
	col13.setCellValueFactory(new PropertyValueFactory<Actor, String>("pseudo"));
	col14.setCellValueFactory(new PropertyValueFactory<Actor, String>("sex"));
	tableSelectedActors.getColumns().addAll(col11, col12, col13, col14);
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

public void setStage() {
	Scene root = new Scene(panel, 400, 400);
	stage.setScene(root);
	stage.show();
}
}
