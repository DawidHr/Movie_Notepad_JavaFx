package view;

import DataBase.DataBase;
import controller.ActorAddController;
import controller.ActorEditController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Actor;

public class ActorEditView {
Stage stage;
DataBase db;
ActorEditController controller;
GridPane panel = new GridPane();

TextField textFieldName = new TextField();
TextField textFieldName2 = new TextField();
TextField textFieldPseudo = new TextField();
TextArea textAreaNote = new TextArea();
ComboBox<String> comboBoxSex = new ComboBox<>();
ObservableList<String> sexList = FXCollections.observableArrayList("Kobieta", "Mê¿czyzna");
ComboBox<String> comboBoxHairSize = new ComboBox<>();
ObservableList<String> hairSizeList = FXCollections.observableArrayList("D³ugie", "Œrednie", "Krótkie");
ComboBox<String> comboBoxHairColor = new ComboBox<>();
ObservableList<String> hairColorList = FXCollections.observableArrayList("Czarne", "Br¹zowe", "Rude", "Blond", "Siwe", "Inne");
Button buttonCencel = new Button("Wstecz");
Button buttonAdd = new Button("Zapisz");
Actor actor;

public ActorEditView(Stage stage, DataBase db, Actor actor) {
	this.stage=stage;
	this.db=db;
	this.actor=actor;
	controller = new ActorEditController(stage, db);
	main();
}

private void main() {
setComboBoxLists();	
setDefaultData();
setColumns();
setRowsSize();
setMarginToNodesInPanel();
addingToPanel();
setAction();
setActor();
setStage();
}

private void setActor() {
	textFieldName.setText(actor.getName());
	textFieldName2.setText(actor.getName2());
	textFieldPseudo.setText(actor.getPseudo());
	textAreaNote.setText(actor.getNote());
	String sex = actor.getSex();
	comboBoxSex.getSelectionModel().select(sex);
	String hairColor = actor.getHair_color();
	if(hairColor != "") 
	comboBoxHairColor.getSelectionModel().select(hairColor);
	String hairSize = actor.getHair_size();
	if(hairSize != "")
	comboBoxHairSize.getSelectionModel().select(hairSize);	
}

private void setComboBoxLists() {
	comboBoxSex.setItems(sexList);
	comboBoxHairSize.setItems(hairSizeList);
	comboBoxHairColor.setItems(hairColorList);
}
private void setDefaultData() {
	textFieldName.setPromptText("Imiê");
	textFieldName2.setPromptText("Nazwisko");
	textFieldPseudo.setPromptText("Pseudonim");
	comboBoxSex.setPromptText("P³eæ");
	comboBoxSex.setPrefWidth(Double.MAX_VALUE);
	comboBoxHairSize.setPromptText("D³ugoœæ w³osów");
	comboBoxHairSize.setPrefWidth(Double.MAX_VALUE);
	comboBoxHairColor.setPromptText("Kolor w³osów");
	comboBoxHairColor.setPrefWidth(Double.MAX_VALUE);
	textAreaNote.setPromptText("W³asna notatka");
	buttonCencel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
	buttonAdd.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
}

private void setColumns() {
	ColumnConstraints c1 = new ColumnConstraints();
	c1.setPercentWidth(50);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(50);
	panel.getColumnConstraints().addAll(c1, c2);
	
}
private void setRowsSize() {
	RowConstraints r1 = new RowConstraints();
	r1.setPercentHeight(20);
	RowConstraints r2 = new RowConstraints();
	r2.setPercentHeight(20);
	RowConstraints r3 = new RowConstraints();
	r3.setPercentHeight(20);
	RowConstraints r4 = new RowConstraints();
	r4.setPercentHeight(30);
	RowConstraints r5 = new RowConstraints();
	r5.setPercentHeight(10);
	panel.getRowConstraints().addAll(r1, r2, r3, r4, r5);
}
private void setMarginToNodesInPanel() {
	panel.setMargin(textFieldName, new Insets(20, 20, 20, 20));
	panel.setMargin(textFieldName2, new Insets(20, 20, 20, 20));
	panel.setMargin(textFieldPseudo, new Insets(20, 20, 20, 20));
	panel.setMargin(comboBoxSex, new Insets(20, 20, 20, 20));
	panel.setMargin(comboBoxHairSize, new Insets(20, 20, 20, 20));
	panel.setMargin(comboBoxHairColor, new Insets(20, 20, 20, 20));
	panel.setMargin(textAreaNote, new Insets(20, 20, 20, 20));
	panel.setMargin(buttonCencel, new Insets(20, 20, 20, 20));
	panel.setMargin(buttonAdd, new Insets(20, 20, 20, 20));
}


private void addingToPanel() {
	panel.setConstraints(textFieldName, 0, 0);
	panel.setConstraints(textFieldName2, 1, 0);
	panel.setConstraints(textFieldPseudo, 0, 1);
	panel.setConstraints(comboBoxSex, 1, 1);
	panel.setConstraints(comboBoxHairSize, 0, 2);
	panel.setConstraints(comboBoxHairColor, 1, 2);
	panel.setConstraints(textAreaNote, 0, 3, 2, 1);
	panel.setConstraints(buttonCencel, 0, 4);
	panel.setConstraints(buttonAdd, 1, 4);
	panel.getChildren().addAll(textFieldName, textFieldName2, textFieldPseudo, comboBoxSex, comboBoxHairSize, comboBoxHairColor, textAreaNote, buttonCencel, buttonAdd);
}

private void setAction() {
	buttonCencel.setOnAction(e-> controller.cencelAction());
	buttonAdd.setOnAction(e-> {
		String hairColor = "";
		String hairSize = "";
		
		
		
		if(comboBoxHairColor.getSelectionModel().getSelectedItem() != null) {
			hairColor = comboBoxHairColor.getSelectionModel().getSelectedItem().toString();
		}
		if(comboBoxHairSize.getSelectionModel().getSelectedItem() != null) {
			hairSize = comboBoxHairSize.getSelectionModel().getSelectedItem().toString();
		}
		
		Actor actorNew = new Actor(textFieldName.getText(), textFieldName2.getText(), textFieldPseudo.getText(), comboBoxSex.getSelectionModel().getSelectedItem().toString(), hairColor, hairSize, textAreaNote.getText());
		
		controller.editActorAction(actor, actorNew);
	});
}

private void setStage() {
	try {
		Scene scene = new Scene(panel, 400, 400);
		stage.setTitle("MOVIE NOTEPAD");
		stage.setScene(scene);
		System.out.println("yuu");
		stage.show();
		System.out.println("yuu");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
