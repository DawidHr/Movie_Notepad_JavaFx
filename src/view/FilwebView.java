package view;

import DataBase.DataBase;
import controller.FilmwebController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FilwebView {
	Stage stage;
	DataBase db;
	FilmwebController controller;
	BorderPane panel = new BorderPane();
	Button buttonCencel = new Button("Wstecz");
	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();
	
	public FilwebView(Stage stage, DataBase db) {
		this.stage=stage;
		this.db=db;
		controller = new FilmwebController(stage, db);
		main();
		setStage();
	}
	
	
	private void main() {
		setPanel();
		setButtonSize();
		setPanelMarginAndPosition();
		setWebEngineSite();
		setButtonAction();
	}

	private void setPanel() {
		panel.setBottom(buttonCencel);
		panel.setCenter(browser);
	}
	private void setButtonSize() {
		buttonCencel.setPrefSize(Double.MAX_VALUE, 10);
	}
	private void setPanelMarginAndPosition() {
		panel.setMargin(buttonCencel, new Insets(20, 20, 20, 20));
		BorderPane.setAlignment(buttonCencel, Pos.CENTER);
	}
	private void setWebEngineSite() {
		webEngine.load("http://www.filmweb.pl/");
	}
	private void setButtonAction() {
		buttonCencel.setOnAction(e-> controller.cencelAction());
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
