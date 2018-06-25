package view;

import DataBase.DataBase;
import controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainView {

	MainController controller;
	private GridPane panel;
	private Button bt1 = new Button("Profil");
	private Button bt2 = new Button("Wytwórnie");
	private Button bt3 = new Button("Aktorzy");
	private Button bt4 = new Button("Filmy");
	private Button bt5 = new Button("Kategorie");
	private Button bt6 = new Button("Kina");
	private Button bt7 = new Button("RSS");
	private Button bt8 = new Button("Ustawienia");
	private Button bt9 = new Button("Wyloguj");
	Stage stage;
	DataBase db;

	public MainView(Stage stage, DataBase db) {
		this.stage = stage;
		this.db = db;
		controller = new MainController(stage, db);
		panel = new GridPane();
		main();
		setStage();

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

	private void main() {
		setMinButtonSize();
		setPrefButtonSize();
		setMaxButtonSize();
		addingButtonsToPanel();
		setMarginsOnPanel();
		setColumnSize();
		setRowSize();
		setActionsForButtons();
	}

	private void setMinButtonSize() {
		bt1.setMinSize(50, 50);
		bt2.setMinSize(50, 50);
		bt3.setMinSize(50, 50);
		bt4.setMinSize(50, 50);
		bt5.setMinSize(50, 50);
		bt6.setMinSize(50, 50);
		bt7.setMinSize(50, 50);
		bt8.setMinSize(50, 50);
		bt9.setMinSize(50, 50);
	}

	private void setPrefButtonSize() {
		bt1.setPrefSize(100, 100);
		bt2.setPrefSize(100, 100);
		bt3.setPrefSize(100, 100);
		bt4.setPrefSize(100, 100);
		bt5.setPrefSize(100, 100);
		bt6.setPrefSize(100, 100);
		bt7.setPrefSize(100, 100);
		bt8.setPrefSize(100, 100);
		bt9.setPrefSize(100, 100);
	}

	private void setMaxButtonSize() {
		bt1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt6.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt7.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt8.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bt9.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	private void addingButtonsToPanel() {
		panel.setConstraints(bt1, 0, 0, 1, 1);
		panel.setConstraints(bt2, 1, 0, 1, 1);
		panel.setConstraints(bt3, 2, 0, 1, 1);
		panel.setConstraints(bt4, 0, 1, 1, 1);
		panel.setConstraints(bt5, 1, 1, 1, 1);
		panel.setConstraints(bt6, 2, 1, 1, 1);
		panel.setConstraints(bt7, 0, 2, 1, 1);
		panel.setConstraints(bt8, 1, 2, 1, 1);
		panel.setConstraints(bt9, 2, 2, 1, 1);
		panel.getChildren().addAll(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9);
	}

	private void setMarginsOnPanel() {
		panel.setMargin(bt1, new Insets(20, 20, 20, 20));
		panel.setMargin(bt2, new Insets(20, 20, 20, 20));
		panel.setMargin(bt3, new Insets(20, 20, 20, 20));
		panel.setMargin(bt4, new Insets(20, 20, 20, 20));
		panel.setMargin(bt5, new Insets(20, 20, 20, 20));
		panel.setMargin(bt6, new Insets(20, 20, 20, 20));
		panel.setMargin(bt7, new Insets(20, 20, 20, 20));
		panel.setMargin(bt8, new Insets(20, 20, 20, 20));
		panel.setMargin(bt9, new Insets(20, 20, 20, 20));
	}

	private void setColumnSize() {
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(33);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(33);
		ColumnConstraints c3 = new ColumnConstraints();
		c3.setPercentWidth(33);
		panel.getColumnConstraints().addAll(c1, c2, c3);
	}

	private void setRowSize() {
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(33);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(33);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(33);
		panel.getRowConstraints().addAll(row1, row2, row3);
	}

	private void setActionsForButtons() {
		bt1.setOnAction(e -> controller.getProfileAction());
		bt2.setOnAction(e -> controller.getPlantAction());
		bt3.setOnAction(e -> controller.getActorAction());
		bt4.setOnAction(e -> controller.getMovieAction());
		bt5.setOnAction(e -> controller.getCategoryAction());
		bt6.setOnAction(e -> controller.getCinemaAction());
		bt7.setOnAction(e -> controller.getRSSAction());
		bt8.setOnAction(e -> controller.getSettingsAction());
		bt9.setOnAction(e -> controller.getLogOffAction());

	}

}
