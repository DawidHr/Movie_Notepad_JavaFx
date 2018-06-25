package controller;

import DataBase.DataBase;
import javafx.stage.Stage;
import view.ActorView;
import view.CategoryView;
import view.CinemaView;
import view.FilwebView;
import view.MovieView;
import view.PlantView;

public class MainController {
Stage stage;
DataBase db;
	
	public MainController(Stage stage, DataBase db) {
		this.stage=stage;
		this.db=db;
	}
	
	public void getProfileAction() {
		System.out.println("Profil");
	}
	public void getPlantAction() {
		PlantView plant = new PlantView(stage, db);
	}
	public void getActorAction() {
		ActorView actor = new ActorView(stage, db);
	}
	public void getMovieAction() {
		MovieView movieView = new MovieView(stage, db);
	}
	public void getCategoryAction() {
		CategoryView categoryView = new CategoryView(stage, db);
	}
	public void getCinemaAction() {
		CinemaView cinemaView = new CinemaView(stage, db);
	}
	public void getRSSAction() {
		FilwebView filmView = new FilwebView(stage, db);
	}
	public void getSettingsAction() {
		System.out.println("Ustawienia");
	}
	public void getLogOffAction() {
		System.out.println("wylogowywanie");
	}

}
