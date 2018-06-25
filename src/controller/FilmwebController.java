package controller;

import DataBase.DataBase;
import javafx.stage.Stage;
import view.MainView;

public class FilmwebController {
Stage stage;
DataBase db;
	
	public FilmwebController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
	}
	
	public void cencelAction() {
		MainView main = new MainView(stage, db);
	}
}
