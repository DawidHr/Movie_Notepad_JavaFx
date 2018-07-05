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
import view.MovieEditView;
import view.MovieView;

public class MovieController {
Stage stage;
DataBase db;

public MovieController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
}

//Wyjœcie do menu
public void cencelAction() {
	MainView main = new MainView(stage, db);
}

//Kasacja wybranego filmu
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
	db.deleteMovie_Actors(movie.getId());
}

//Edycja wybranego filmu
public void  editAction(Movie movie) {
	movie.setListActor(db.getMovie_Actors(movie.getId()));
	MovieEditController movie1 = new MovieEditController(stage, db, movie, 2);
}

//Dodawanie nowego filmu
public void addAction() {
	MovieEditController movie1 = new MovieEditController(stage, db, 1);
}

//Przeglad wybranego filmu
public Object viewAction() {
	// TODO Auto-generated method stub
	return null;
}

//Filtrowanie filmów
public Object filtrAction() {
	// TODO Auto-generated method stub
	return null;
}

//Pobieranie z bazy danych listy filmów i zwracanie jej
public ObservableList<Movie> getMovies() {
	return db.getAllMovies();
}
}
