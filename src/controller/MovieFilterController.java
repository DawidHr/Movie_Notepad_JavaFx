package controller;

import DataBase.DataBase;
import javafx.stage.Stage;
import view.MovieFilterView;

public class MovieFilterController {
Stage stage;
DataBase db;
MovieFilterView view;
public MovieFilterController(Stage stage, DataBase db) {
	super();
	this.stage = stage;
	this.db = db;
	view = new MovieFilterView(stage, db);
}


}
