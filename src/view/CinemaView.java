package view;

import DataBase.DataBase;
import controller.CinemaController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CinemaView {

Stage stage;
DataBase db;
CinemaController controller;

BorderPane panel = new BorderPane();
VBox panelVBox = new VBox();
TabPane tabPane = new TabPane();
Tab tabMap = new Tab("Mapa");
Tab tabCinema = new Tab("Kino");

Button buttonCencel = new Button("Wstecz");
Label labelCity = new Label("Miasto");
ComboBox<String> comboBoxCity = new ComboBox<>();
ObservableList<String> observableListCity;
Label labelCinema = new Label("Kino");
ComboBox<String> comboBoxCinema = new ComboBox<>();
ObservableList<String> observableListCinema;


public CinemaView(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
	controller = new CinemaController(stage, db);
	main();
	setStage();
}
private void main() {
	setButtonSize();
	controller.getCityList();
	setTabs();
	setPanelVBox();
	setMainPanel();
}
private void setButtonSize() {
	buttonCencel.setPrefSize(Double.MAX_VALUE, 50);
	
}
private void setTabs() {
	tabPane.getTabs().addAll(tabMap, tabCinema);
}
private void setPanelVBox() {
	addToPanel();
}
private void addToPanel() {
	panelVBox.getChildren().addAll(labelCity, comboBoxCity, labelCinema, comboBoxCinema);
	
}
private void setMainPanel() {
	addBottomNodesAndMargins();
	addLeftNodesAndMargins();
	addCenterNodes();
	
}
private void addBottomNodesAndMargins() {
	panel.setBottom(buttonCencel);
	panel.setMargin(buttonCencel, new Insets(20, 20, 20, 20));
}
private void addLeftNodesAndMargins() {
	panel.setLeft(panelVBox);
}
private void addCenterNodes() {
	panel.setCenter(tabPane);
}
private void setStage() {
	try {
		Scene scene = new Scene(panel,400,400);
		stage.setScene(scene);
		stage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
}
}
