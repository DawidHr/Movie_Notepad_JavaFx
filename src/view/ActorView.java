package view;

import DataBase.DataBase;
import controller.ActorController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Actor;

public class ActorView {

	Stage stage;
	DataBase db;
	ActorController controller;
	
	Button buttonView = new Button("Przegl¹daj");
	Button buttonAdd = new Button("Dodaj");
	Button buttonEdit = new Button("Edytuj");
	Button buttonDelete = new Button("Kasuj");
	Button buttonCencel = new Button("Wstecz");
	
	TableView<Actor> table = new TableView<>();
	TableColumn<Actor, String> col1 = new TableColumn<Actor, String>("Imiê");
	TableColumn<Actor, String> col2 = new TableColumn<Actor, String>("Nazwisko");
	TableColumn<Actor, String> col3 = new TableColumn<Actor, String>("Pseudonim");
	TableColumn<Actor, String> col4 = new TableColumn<Actor, String>("P³eæ");
	
	BorderPane panel = new BorderPane();
	HBox panelHBox = new HBox();
	
	public ActorView(Stage stage, DataBase db) {
		this.stage=stage;
		this.db=db;
		controller = new ActorController(stage, db);
		main();
		setStage();	
	}
	
	private void setTable() {
		setTableColumn();
	}

	private void setTableColumn() {
		col1.setCellValueFactory(new PropertyValueFactory<Actor, String>("name"));
		col2.setCellValueFactory(new PropertyValueFactory<Actor, String>("name2"));
		col3.setCellValueFactory(new PropertyValueFactory<Actor, String>("pseudo"));
		col4.setCellValueFactory(new PropertyValueFactory<Actor, String>("sex"));
		table.getColumns().addAll(col1, col2, col3, col4);
		ObservableList<Actor> list  = controller.getActorsList();
		table.setItems(list);
	}
	private void main() {
		Font font1 = new Font("Times New Roman", 25);
		Label labelCategory = new Label("Aktorzy");
		labelCategory.setFont(font1);
		panel.setTop(labelCategory);
		addingToPanelHBox();
		panel.setCenter(table);
		BorderPane.setAlignment(labelCategory, Pos.TOP_CENTER);
		BorderPane.setMargin(labelCategory, new Insets(20,20,20,20));
		BorderPane.setAlignment(panelHBox, Pos.BOTTOM_CENTER);
		BorderPane.setMargin(panelHBox, new Insets(20,20,20,20));
		setTable();
		setActionButtons();
	}

	private void setActionButtons() {
	buttonView.setOnAction(e-> controller.getActorViewAction(table.getSelectionModel().getSelectedItem()));
	buttonAdd.setOnAction(e-> controller.getActorAddAction());	
	buttonEdit.setOnAction(e-> controller.getActorEditAction(table.getSelectionModel().getSelectedItem()));
	buttonDelete.setOnAction(e->controller.getActorDeleteAction(table.getSelectionModel().getSelectedItem()));
	buttonCencel.setOnAction(e->controller.getActorCencelAction());
	}
	

	private void addingToPanelHBox() {
		panelHBox.setAlignment(Pos.CENTER);
		panelHBox.getChildren().addAll(buttonCencel, buttonView, buttonAdd, buttonEdit, buttonDelete);
		panel.setBottom(panelHBox);
	}
	
	private void setStage() {
		try {
			Scene scene = new Scene(panel, 400, 400);
			stage.setTitle("MOVIE NOTEPAD");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
