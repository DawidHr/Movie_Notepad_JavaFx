package view;

import DataBase.DataBase;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MovieFilterView {
Stage stage;
DataBase db;

Label labelFilter = new Label("FILTR");
TextField textFieldTitle = new TextField("Tytu³ filmu");
ComboBox<String> comboBoxCategory = new ComboBox<>();
ComboBox<String> comboBoxPlant = new ComboBox<>();
TextArea textAreaNote = new TextArea();

Label labelActor = new Label("AKTORZY");
Label labelAllActor = new Label("Wszyscy");
Label labelSelectedActor = new Label("Wybrani");
TableView<Object> tableViewAllActors = new TableView<>();
TableView<Object> tableViewSelectedActors = new TableView<>();
Button buttonAdd = new Button("Dodaj");
Button buttonDelete = new Button("Kasuj");

Button buttonCencel = new Button("Wstecz");
Button buttonClean = new Button("Czyœæ");
Button buttonSave = new Button("Zapisz");

GridPane gridPaneMovies = new GridPane();
GridPane gridPaneActors = new GridPane();

TitledPane titlePaneMovies = new TitledPane();
TitledPane titlePaneActors = new TitledPane();
Accordion panelAccordion = new Accordion(titlePaneMovies, titlePaneActors);
HBox panelHBox = new HBox();
BorderPane panel = new BorderPane();

public MovieFilterView(Stage stage, DataBase db) {
	super();
	this.stage = stage;
	this.db = db;
	main();
}
private void main() {
	setToolTipForNodes();
	setGridPaneMovies();
	setGridPaneActors();
	setTitlePane();
	setHBoxPanel();
	panel.setCenter(panelAccordion);
	panel.setBottom(panelHBox);
	setStage();
}


private void setHBoxPanel() {
	panelHBox.getChildren().addAll(buttonCencel, buttonClean, buttonSave);
	
}
//Przypisanie toolTip do wez³ów
private void setToolTipForNodes() {
	textFieldTitle.setTooltip(new Tooltip("Nale¿y wpisaæ tytu³ lub czêœæ tytu³u filmu"));
	comboBoxCategory.setTooltip(new Tooltip("Nale¿y wybraæ kategorie w jakich bêdziemy wyszukiwaæ filmów"));
	comboBoxCategory.setTooltip(new Tooltip("Nale¿y wybraæ wytwórnie dla szukanych filmów"));
	tableViewAllActors.setTooltip(new Tooltip("Wyœwietla wszystkich aktorów"));
	tableViewSelectedActors.setTooltip(new Tooltip("Tu bêda dodani aktorzy których szukamy"));
	
}

private void setGridPaneMovies() {
	gridPaneMovies.add(labelFilter, 0, 0, 2, 1);
	gridPaneMovies.add(textFieldTitle, 0, 1, 2, 1);
	gridPaneMovies.add(comboBoxCategory, 0, 2, 1, 1);
	gridPaneMovies.add(comboBoxPlant, 1, 2, 1, 1);
	gridPaneMovies.add(textAreaNote, 0, 3, 2, 1);
	
	gridPaneMovies.setMargin(labelFilter, new Insets(20, 20, 20, 20));
	gridPaneMovies.setMargin(textFieldTitle, new Insets(20, 20, 20, 20));
	gridPaneMovies.setMargin(comboBoxCategory, new Insets(20, 20, 20, 20));
	gridPaneMovies.setMargin(comboBoxPlant, new Insets(20, 20, 20, 20));
	gridPaneMovies.setMargin(textAreaNote, new Insets(20, 20, 20, 20));
	
	gridPaneMovies.setValignment(labelFilter, VPos.CENTER);
	gridPaneMovies.setValignment(textFieldTitle, VPos.CENTER);
	gridPaneMovies.setValignment(comboBoxCategory, VPos.CENTER);
	gridPaneMovies.setValignment(comboBoxPlant, VPos.CENTER);
	gridPaneMovies.setValignment(textAreaNote, VPos.CENTER);
	
	gridPaneMovies.setHalignment(labelFilter, HPos.CENTER);
	gridPaneMovies.setHalignment(textFieldTitle, HPos.CENTER);
	gridPaneMovies.setHalignment(comboBoxCategory, HPos.CENTER);
	gridPaneMovies.setHalignment(comboBoxPlant, HPos.CENTER);
	gridPaneMovies.setHalignment(textAreaNote, HPos.CENTER);
	

	RowConstraints r1= new RowConstraints();
	r1.setPercentHeight(20);
	RowConstraints r2= new RowConstraints();
	r2.setPercentHeight(20);
	RowConstraints r3= new RowConstraints();
	r3.setPercentHeight(20);
	RowConstraints r4= new RowConstraints();
	r4.setPercentHeight(40);
	gridPaneMovies.getRowConstraints().addAll(r1, r2, r3, r4);
	
	ColumnConstraints c1 = new ColumnConstraints();
	c1.setPercentWidth(50);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(50);
	gridPaneMovies.getColumnConstraints().addAll(c1, c2);
}

private void setGridPaneActors() {
	
	
	gridPaneActors.add(labelActor, 0, 0, 3, 1);
	gridPaneActors.add(labelSelectedActor, 0, 1, 1, 1);
	gridPaneActors.add(labelAllActor, 2, 1, 1, 1);
	gridPaneActors.add(tableViewSelectedActors, 0, 2, 1, 3);
	gridPaneActors.add(buttonAdd, 1, 2, 1, 1);
	gridPaneActors.add(tableViewAllActors, 2, 2, 1, 3);
	gridPaneActors.add(buttonDelete, 1, 3, 1, 1);
	
	gridPaneActors.setMargin(labelActor, new Insets(20, 20, 20, 20));
	gridPaneActors.setMargin(labelSelectedActor, new Insets(20, 20, 20, 20));
	gridPaneActors.setMargin(labelAllActor, new Insets(20, 20, 20, 20));
	gridPaneActors.setMargin(tableViewSelectedActors, new Insets(20, 20, 20, 20));
	gridPaneActors.setMargin(tableViewAllActors, new Insets(20, 20, 20, 20));
	gridPaneActors.setMargin(buttonAdd, new Insets(20, 20, 20, 20));
	gridPaneActors.setMargin(buttonDelete, new Insets(20, 20, 20, 20));
	
	gridPaneActors.setValignment(labelActor, VPos.CENTER);
	gridPaneActors.setValignment(labelSelectedActor, VPos.CENTER);
	gridPaneActors.setValignment(labelAllActor, VPos.CENTER);
	gridPaneActors.setValignment(tableViewSelectedActors, VPos.CENTER);
	gridPaneActors.setValignment(tableViewAllActors, VPos.CENTER);
	gridPaneActors.setValignment(buttonAdd, VPos.CENTER);
	gridPaneActors.setValignment(buttonDelete, VPos.CENTER);
	
	gridPaneActors.setHalignment(labelActor, HPos.CENTER);
	gridPaneActors.setHalignment(labelSelectedActor, HPos.CENTER);
	gridPaneActors.setHalignment(labelAllActor, HPos.CENTER);
	gridPaneActors.setHalignment(tableViewSelectedActors, HPos.CENTER);
	gridPaneActors.setHalignment(tableViewAllActors, HPos.CENTER);
	gridPaneActors.setHalignment(buttonAdd, HPos.CENTER);
	gridPaneActors.setHalignment(buttonDelete, HPos.CENTER);
	
	RowConstraints r1= new RowConstraints();
	r1.setPercentHeight(20);
	RowConstraints r2= new RowConstraints();
	r2.setPercentHeight(20);
	RowConstraints r3= new RowConstraints();
	r3.setPercentHeight(30);
	RowConstraints r4= new RowConstraints();
	r4.setPercentHeight(30);
	gridPaneActors.getRowConstraints().addAll(r1, r2, r3, r4);
	
	ColumnConstraints c1 = new ColumnConstraints();
	c1.setPercentWidth(45);
	ColumnConstraints c2 = new ColumnConstraints();
	c2.setPercentWidth(10);
	ColumnConstraints c3 = new ColumnConstraints();
	c3.setPercentWidth(45);
	gridPaneActors.getColumnConstraints().addAll(c1, c2, c3);
}

private void setTitlePane() {
	titlePaneMovies.setContent(gridPaneMovies);
	titlePaneMovies.setText("Filmy");
	titlePaneActors.setContent(gridPaneActors);
	titlePaneActors.setText("Aktorzy");
}


private void setStage() {
	Scene root = new Scene(panel, 400, 400);
	stage.setScene(root);
	stage.show();
}
}
