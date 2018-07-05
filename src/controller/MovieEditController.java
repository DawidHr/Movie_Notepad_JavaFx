package controller;

import java.io.File;
import java.util.LinkedList;
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
import view.MovieEditView;
import view.MovieView;

public class MovieEditController {
Stage stage;
DataBase db;
MovieEditView view;
// Mode. 1 jeœli dodawanie nowych, 2. jesli edycja 
int mode;
File file=null;
List<Actor> listActor= new LinkedList<>();

 public MovieEditController(Stage stage, DataBase db, int mode) {
	 this.stage=stage;
	 this.db=db;
	 this.mode=mode;
	 view = new MovieEditView(stage, db);
	 if(mode == 1)
		 MainForAdd();
	 else
		 MainForEdit();
 }
 
 public MovieEditController(Stage stage2, DataBase db2, Movie movie, int mode2) {
	 this.stage=stage2;
	 this.db=db2;
	 this.mode=mode2;
	 view = new MovieEditView(stage, db);
	 if(mode == 1)
		 MainForAdd();
	 else
		 MainForEdit();
	 setData(movie);
}

private void MainForAdd() {
	 view.setComboBoxCategory(getCategoryList());
	 view.setComboBoxPlant(getPlantList());
	 setActionsForButtons();
 }
private void MainForEdit() {
	view.setComboBoxCategory(getCategoryList());
	 view.setComboBoxPlant(getPlantList());
	 setActionsForButtons();
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
 
 private void setActionsForButtons() {
	 Button buttonCencel = view.getButtonCencel();
	 Button buttonFileChooser = view.getButtonFileChooser();
	 Button buttonActors = view.getButtonActor();
	 Button buttonSave = view.getButtonSava();
	 
	 buttonCencel.setOnAction(e-> cencelAcion());
	 buttonFileChooser.setOnAction(e-> getSelectedFile());
	 buttonActors.setOnAction(e-> getActorsAction());
	 buttonSave.setOnAction(e-> saveAction());
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
	if(view.getComboBoxCategory().getSelectionModel().getSelectedItem() != null)
		category= view.getComboBoxCategory().getSelectionModel().getSelectedItem();
	if(view.getComboBoxPlant().getSelectionModel().getSelectedItem() != null)
		plant = view.getComboBoxPlant().getSelectionModel().getSelectedItem();
	System.out.println("mode");
	Movie movie = new Movie(view.getTextFieldTitle().getText(), filename, fileUrl, category, plant, view.getTextAreaNote().getText());
	movie.setListActor(listActor);
	MovieEditActorsController main = new MovieEditActorsController(stage, db, movie, mode);
}



public void setData(Movie movie) {
	view.getTextFieldTitle().setText(movie.getTitle());
	view.getTextAreaNote().setText(movie.getNote());
	if(movie.getCategory() != "")
		view.getComboBoxCategory().getSelectionModel().select(movie.getCategory());
	if(movie.getPlant() != "")
		view.getComboBoxPlant().getSelectionModel().select(movie.getPlant());
	if(movie.getFile_url() != "")
		file = new File(movie.getFile_url());
	/*if(!movie.getListActor().isEmpty())
		listActor=movie.getListActor();*/
	if(movie.getListActor() != null)
		listActor=movie.getListActor();
	
}



public void saveAction() {
	//Sprawdzenie czy pola nie sa puste
	if(view.getTextFieldTitle().getText().isEmpty())
		return;
	if(view.getComboBoxCategory().getSelectionModel().getSelectedItem() == null)
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
	if(view.getComboBoxCategory().getSelectionModel().getSelectedItem() != null)
		category= view.getComboBoxCategory().getSelectionModel().getSelectedItem();
	if(view.getComboBoxPlant().getSelectionModel().getSelectedItem() != null)
		plant = view.getComboBoxPlant().getSelectionModel().getSelectedItem();
	
	Movie movie = new Movie(view.getTextFieldTitle().getText(), filename, fileUrl, category, plant, view.getTextAreaNote().getText());
	movie.setListActor(listActor);
	if(mode==1) 
	db.addMovie(movie);
	else
	db.updateMovie(movie);	
	MovieView view = new MovieView(stage, db); 
}

 
}