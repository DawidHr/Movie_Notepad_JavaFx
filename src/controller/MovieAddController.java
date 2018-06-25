package controller;

import java.io.File;
import java.util.List;

import DataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Category;
import view.MovieAddActorsView;
import view.MovieView;

public class MovieAddController {
Stage stage;
DataBase db;

File file=null;

 public MovieAddController(Stage stage, DataBase db) {
	 this.stage=stage;
	 this.db=db;
 }
 
 public ObservableList<String> getCategoryList() {
	  List<Category> list = db.getAllCategories();
	  ObservableList<String> listString = FXCollections.observableArrayList();
	  for(Category c : list) {
		  listString.add(c.getName());
	  }
	  return listString;
 }
 
 public ObservableList<String> getPlantList() {
	List<String> list = db.getAllPlant();
	ObservableList<String> listString = FXCollections.observableArrayList(list);
	return listString;
 }
 
 public void getSelectedFile() { 	
	 	FileChooser fileCh = new FileChooser();
		ExtensionFilter filter = new ExtensionFilter("Video","*.mp4", "*.avi", "*.mkv", "*.flv");
		fileCh.getExtensionFilters().addAll(filter);
		File selectedFile = fileCh.showOpenDialog(null); 
		if( selectedFile != null) {
		file = selectedFile;
		}
 }
 
 public void cencelAcion() {
	 MovieView movie = new MovieView(stage, db);
 }

public void getActorsAction() {
	MovieAddActorsView main = new MovieAddActorsView(stage, db);
}
 
 
}