package controller;

import javafx.stage.Stage;
import view.WatchMovie;

public class WatchMovieController {

	Stage stage;
	
	public WatchMovieController(Stage stage) {
		this.stage=stage;
		main();
	}
	
	private void main() {
		WatchMovie movie = new WatchMovie(stage);
	}
}
