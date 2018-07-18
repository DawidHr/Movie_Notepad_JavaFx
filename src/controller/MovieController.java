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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
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
public void viewAction() {
	try {
		File f = new File("D:\\Muza+Teledyski\\Rapoholika - Pasja ( Official video ) prod. SwR.mp4");
		URI u = f.toURI();
		Media media = new Media(u.toString());
		MediaPlayer mediPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView();
		Pane panel2 = new Pane();
		panel2.getChildren().add(mediaView);
		Stage stage2 = new Stage();
		Scene scene2 = new Scene(panel2, 400,400);
		stage2.setScene(scene2);
		stage2.initOwner(stage);
		stage2.show();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
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
