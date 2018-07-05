package view;

import DataBase.DataBase;
import controller.MovieEditController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Movie;

public class MovieEditView {
Stage stage;
DataBase db;

GridPane panel = new GridPane();

TextField textFieldTitle = new TextField();
ComboBox<String> comboBoxCategory = new ComboBox<>();
ComboBox<String> comboBoxPlant = new ComboBox<>();
TextArea textAreaNote = new TextArea();

Button buttonCencel = new Button("Wstecz");
Button buttonSava = new Button("Zapisz");
Button buttonFileChooser = new Button("Plik");
Button buttonActor = new Button("Aktorzy");

public MovieEditView(Stage stage, DataBase db) {
	super();
	this.stage = stage;
	this.db = db;
	main();
	setStage();
}

private void main() {
	textFieldTitle.setPromptText("Tytu� filmu");
	textAreaNote.setPromptText("W�asna notatka do filmu");
	comboBoxCategory.setPromptText("Kategoria");
	comboBoxPlant.setPromptText("Wytw�rnia");
	
	addNodesToPanel();

	setMarginsOnPanel();
	setColumnSize();
	setRowSize();
}

private void addNodesToPanel() {
	panel.setConstraints(textFieldTitle, 0, 0, 2, 1);
	panel.setConstraints(comboBoxCategory, 0, 1, 1, 1);
	panel.setConstraints(buttonFileChooser, 1, 1, 1, 1);
	panel.setConstraints(comboBoxPlant, 0, 2, 1, 1);
	panel.setConstraints(buttonActor, 1, 2, 1, 1);
	panel.setConstraints(textAreaNote, 0, 3, 2, 1);
	panel.setConstraints(buttonCencel, 0, 4, 1, 1);
	panel.setConstraints(buttonSava, 1, 4, 1, 1);
	panel.getChildren().addAll(textFieldTitle, comboBoxCategory, buttonFileChooser, comboBoxPlant, buttonActor, textAreaNote, buttonCencel, buttonSava);
}

public void setComboBoxCategory(ObservableList<String> observableListCategory) {
	comboBoxCategory.setItems(observableListCategory);
}

public ComboBox<String> getComboBoxCategory() {
	return comboBoxCategory;
}

public ComboBox<String> getComboBoxPlant() {
	return comboBoxPlant;
}

public void setComboBoxPlant(ObservableList<String> observableListPlant) {
	comboBoxPlant.setItems(observableListPlant);
}

private void setMarginsOnPanel() {
	panel.setMargin(textFieldTitle, new Insets(20, 20, 20, 20));
	panel.setMargin(comboBoxCategory, new Insets(20, 20, 20, 20));
	panel.setMargin(buttonFileChooser, new Insets(20, 20, 20, 20));
	panel.setMargin(comboBoxPlant, new Insets(20, 20, 20, 20));
	panel.setMargin(buttonActor, new Insets(20, 20, 20, 20));
	panel.setMargin(textAreaNote, new Insets(20, 20, 20, 20));
	panel.setMargin(buttonCencel, new Insets(20, 20, 20, 20));
	panel.setMargin(buttonSava, new Insets(20, 20, 20, 20));
}
private void setColumnSize() {
	ColumnConstraints c1 = new ColumnConstraints();
	c1.setPercentWidth(50);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(50);
	panel.getColumnConstraints().addAll(c1, c2);
}

private void setRowSize() {
	RowConstraints row1 = new RowConstraints();
	row1.setPercentHeight(15);
	RowConstraints row2 = new RowConstraints();
	row2.setPercentHeight(15);
	RowConstraints row3 = new RowConstraints();
	row3.setPercentHeight(15);
	RowConstraints row4 = new RowConstraints();
	row4.setPercentHeight(40);
	RowConstraints row5 = new RowConstraints();
	row5.setPercentHeight(15);
	panel.getRowConstraints().addAll(row1, row2, row3, row4, row5);
}



public TextField getTextFieldTitle() {
	return textFieldTitle;
}

public void setTextFieldTitle(TextField textFieldTitle) {
	this.textFieldTitle = textFieldTitle;
}

public TextArea getTextAreaNote() {
	return textAreaNote;
}

public void setTextAreaNote(TextArea textAreaNote) {
	this.textAreaNote = textAreaNote;
}


public Button getButtonCencel() {
	return buttonCencel;
}

public Button getButtonSava() {
	return buttonSava;
}

public Button getButtonFileChooser() {
	return buttonFileChooser;
}

public Button getButtonActor() {
	return buttonActor;
}

private void setStage() {
	Scene root = new Scene(panel, 400, 400);
	stage.setScene(root);
	stage.show();
}


}
