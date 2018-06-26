package view;

import DataBase.DataBase;
import controller.MovieController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Movie;

public class MovieView {
Stage stage;
DataBase db;
MovieController controller;

BorderPane panel = new BorderPane();
HBox panelHBox = new HBox();

Button buttonCencel = new Button("Wstecz");
Button buttonDelete = new Button("Kasuj");
Button buttonEdit = new Button("Edytuj");
Button buttonAdd = new Button("Dodaj");
Button buttonView = new Button("Przegladaj");
Button buttonFilt = new Button("Filtruj");

Label labelTitle = new Label("Filmy");

TableView<Movie> table = new TableView<>();



public MovieView(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
	controller = new MovieController(stage, db);
	main();
}

private void main() {
	
	setHBox();
	setPanel();
	setActionEvent();
	setStage();
}


private void setHBox() {
	panelHBox.setMargin(buttonCencel, new Insets(10));
	panelHBox.setMargin(buttonDelete, new Insets(10));
	panelHBox.setMargin(buttonEdit, new Insets(10));
	panelHBox.setMargin(buttonAdd, new Insets(10));
	panelHBox.setMargin(buttonView, new Insets(10));
	panelHBox.setMargin(buttonFilt, new Insets(10));
	addNodesToHBox();
}

private void addNodesToHBox() {
	panelHBox.getChildren().addAll(buttonCencel, buttonDelete, buttonEdit, buttonAdd, buttonView, buttonFilt);
}

private void setPanel() {
	panel.setTop(labelTitle);
	panel.setCenter(table);
	panel.setBottom(panelHBox);
}

private void setActionEvent() {
	buttonCencel.setOnAction(e-> controller.cencelAction());
	buttonDelete.setOnAction(e-> controller.deleteAction(table.getSelectionModel().getSelectedItem()));
	buttonEdit.setOnAction(e-> controller.editAction());
	buttonAdd.setOnAction(e-> controller.addAction());
	buttonView.setOnAction(e-> controller.viewAction());
	buttonFilt.setOnAction(e-> controller.filtrAction());
}

private void setStage() {
	Scene root = new Scene(panel, 400, 400);
	stage.setScene(root);
	stage.show();
}
}