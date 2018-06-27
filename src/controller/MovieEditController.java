package controller;

import java.io.File;
import java.util.List;

import DataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Actor;
import model.Category;
import model.Movie;
import view.MovieAddActorsView;
import view.MovieView;

public class MovieEditController {
Stage stage;
DataBase db;


TextField textFieldTitle;
ComboBox<String> comboBoxCategory;
ComboBox<String> comboBoxPlant;
TextArea textAreaNote;

Button buttonCencel;
Button buttonSava;
Button buttonFileChooser;
Button buttonActor;


File file=null;
List<Actor> listActor=null;

 public MovieEditController(Stage stage, DataBase db) {
	 this.stage=stage;
	 this.db=db;
 }
 
 
 
 public MovieEditController(Stage stage, DataBase db, TextField textFieldTitle, ComboBox<String> comboBoxCategory,
		ComboBox<String> comboBoxPlant, TextArea textAreaNote, Button buttonCencel, Button buttonSava,
		Button buttonFileChooser, Button buttonActor) {
	super();
	this.stage = stage;
	this.db = db;
	this.textFieldTitle = textFieldTitle;
	this.comboBoxCategory = comboBoxCategory;
	this.comboBoxPlant = comboBoxPlant;
	this.textAreaNote = textAreaNote;
	this.buttonCencel = buttonCencel;
	this.buttonSava = buttonSava;
	this.buttonFileChooser = buttonFileChooser;
	this.buttonActor = buttonActor;
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
	String filename = "";
	String fileUrl = "";
	String category = "";
	String plant = "";
	if(file != null) {
		filename = file.getName();
		fileUrl = file.getAbsolutePath();
	}
	if(comboBoxCategory.getSelectionModel().getSelectedItem() != null)
		category= comboBoxCategory.getSelectionModel().getSelectedItem();
	if(comboBoxPlant.getSelectionModel().getSelectedItem() != null)
		plant = comboBoxPlant.getSelectionModel().getSelectedItem();
	
	Movie movie = new Movie(textFieldTitle.getText(), filename, fileUrl, category, plant, textAreaNote.getText());
	MovieAddActorsView main = new MovieAddActorsView(stage, db, movie);
}



public void setData(Movie movie) {
	textFieldTitle.setText(movie.getTitle());
	textAreaNote.setText(movie.getNote());
	if(movie.getCategory() != "")
		comboBoxCategory.getSelectionModel().select(movie.getCategory());
	if(movie.getPlant() != "")
		comboBoxPlant.getSelectionModel().select(movie.getPlant());
	if(movie.getFile_url() != "")
		file = new File(movie.getFile_url());
	if(!movie.getListActor().isEmpty())
		listActor=movie.getListActor();
	
}



public void saveAction() {
	//Sprawdzenie czy pola nie sa puste
	if(textFieldTitle.getText().isEmpty())
		return;
	if(comboBoxCategory.getSelectionModel().getSelectedItem() == null)
		return;
	if(file == null)
		return;
	String filename = "";
	String fileUrl = "";
	String category = "";
	String plant = "";
	if(file != null) {
		filename = file.getName();
		fileUrl = file.getAbsolutePath();
	}
	if(comboBoxCategory.getSelectionModel().getSelectedItem() != null)
		category= comboBoxCategory.getSelectionModel().getSelectedItem();
	if(comboBoxPlant.getSelectionModel().getSelectedItem() != null)
		plant = comboBoxPlant.getSelectionModel().getSelectedItem();
	
	Movie movie = new Movie(textFieldTitle.getText(), filename, fileUrl, category, plant, textAreaNote.getText());
	movie.setListActor(listActor);
	db.addMovie(movie);
	MovieView view = new MovieView(stage, db);
}
 
 
}