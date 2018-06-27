package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import DataBase.DataBase;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Actor;
import model.Movie;
import view.CategoryView;
import view.MainView;
import view.MovieAddView;
import view.MovieEditView;
import view.MovieView;

public class MovieController {
Stage stage;
DataBase db;

public MovieController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
}

public void cencelAction() {
	MainView main = new MainView(stage, db);
}

public void deleteAction(Movie movie) {
	Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
	dialog.setTitle("Ostrzezenie przed kasacja");
	dialog.setHeaderText("Czy chcesz skasowac "+movie.getTitle()+"");
	Platform.runLater(() ->{
		Optional<ButtonType> response = dialog.showAndWait();
		System.out.println(response.get().getText());
		if(response.get().getText().equals("OK")) {
			deleteMovie(movie);
			MovieView view = new MovieView(stage, db);}
	});
}

private void deleteMovie(Movie movie) {
	db.deleteMovie(movie);
}

public void  editAction(Movie movie) {
	List<Actor> list = new LinkedList<>();
	movie.setListActor(list);
	MovieEditView view = new MovieEditView(stage, db, movie);
}

public void addAction() {
	MovieAddView movie = new MovieAddView(stage, db);
}

public Object viewAction() {
	// TODO Auto-generated method stub
	return null;
}

public Object filtrAction() {
	// TODO Auto-generated method stub
	return null;
}

public ObservableList<Movie> getMovies() {
	return db.getAllMovies();
}
}
