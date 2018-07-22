package controller;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import DataBase.DataBase;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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

//Wyjście do menu
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
public void viewAction() {
WatchMovieController controller = new WatchMovieController(stage);	
}

//Filtrowanie filmów
public void filtrAction() {
	MovieFilterController controller = new MovieFilterController(stage, db);
}

//Pobieranie z bazy danych listy filmów i zwracanie jej
public ObservableList<Movie> getMovies() {
	return db.getAllMovies();
}
}
