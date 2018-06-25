package view;

import DataBase.DataBase;
import controller.CategoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CategoryView {

	private CategoryController controller;
	Button buttonAdd = new Button("Dodaj");
	Button buttonEdit = new Button("Edytuj");
	Button buttonDelete = new Button("Kasuj");
	Button buttonCencel = new Button("Wstecz");
	GridPane panelGrid = new GridPane();
	VBox panelVBox = new VBox();
	Label labelCategory = new Label("KATEGORIE");
	ListView<String> list = new ListView<>();
	ObservableList<String> listString;
	Stage stage;
	DataBase db;

	public CategoryView(Stage stage, DataBase db) {
		this.stage = stage;
		this.db = db;
		controller = new CategoryController(stage, db);
		main();
		setStage();
	}

	private void setStage() {
		try {
			Scene scene = new Scene(panelGrid, 400, 400);
			stage.setTitle("MOVIE NOTEPAD");
			stage.setScene(scene);
			System.out.println("yuu");
			stage.show();
			System.out.println("yuu");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void main() {
		Font font1 = new Font("Times New Roman", 25);
		labelCategory.setFont(font1);

		setButtonSize();
		setColumnConstraints();
		setRowConstraints();
		setList();
		addToPanel();
		setActions();

	}

	private void setButtonSize() {
		buttonAdd.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		buttonEdit.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		buttonDelete.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		buttonCencel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	private void setColumnConstraints() {
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(30);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(70);
		panelGrid.getColumnConstraints().addAll(c1, c2);

	}

	private void setRowConstraints() {
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(20);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(60);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(20);
		panelGrid.getRowConstraints().addAll(row1, row2, row3);

	}

	private void addToPanel() {
		panelVBox.setFillWidth(true);
		panelVBox.getChildren().addAll(buttonAdd, buttonEdit, buttonDelete);
		panelGrid.setGridLinesVisible(true);
		panelGrid.setConstraints(panelVBox, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(20, 20, 20, 20));
		panelGrid.setConstraints(buttonCencel, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(20, 20, 20, 20));
		GridPane.setConstraints(labelCategory, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(20, 20, 20, 20));
		panelGrid.setConstraints(list, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(20, 20, 20, 20));
		panelGrid.setConstraints(list, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(20, 20, 20, 20));
		panelGrid.getChildren().addAll(panelVBox, buttonCencel, labelCategory, list);

	}

	private void setList() {
		listString = controller.setCategoryList();
		list.setItems(listString);
	}

	private void setActions() {
		try {
		buttonAdd.setOnAction(e -> controller.addingCategoryAction());
		buttonEdit.setOnAction(e -> controller.editingCategoryAction(list.getSelectionModel().getSelectedItem().toString())); 
		buttonDelete.setOnAction(e -> controller.deleteCategoryAction(list.getSelectionModel().getSelectedItem().toString()));
		buttonCencel.setOnAction(e -> controller.cencelCategoryAction());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
