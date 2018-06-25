package controller;

import java.util.List;
import java.util.Optional;

import DataBase.DataBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Category;
import view.CategoryView;
import view.MainView;
import view.PlantView;

public class PlantController {

	Stage stage;
	DataBase db;
	
	public PlantController(Stage stage, DataBase db) {
		this.stage=stage;
		this.db = db;
	}
	
	public ObservableList<String> setCategoryList() {
		List<String> list = getPlant();
		ObservableList<String> list1 = FXCollections.observableArrayList(list);
		return list1;
	}

	private List<String> getPlant() {
		return db.getAllPlant();
	}

	public void  viewPlantAction(String string) {
		
	}

	public void addingPlantAction() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Dodawana Wytwórnii");
		dialog.setHeaderText("Proszê podaæ nazwê wytwórni:");
		dialog.initOwner(stage);
		Platform.runLater(() ->{
			Optional<String> response = dialog.showAndWait();
			String categoryName= response.orElseGet(dialog::getDefaultValue);
			boolean isOnDb = isPlantInDb(categoryName);
			if(!isOnDb)
				addingToDb(categoryName);
			PlantView view = new PlantView(stage, db);
		});
	}

	private void addingToDb(String categoryName) {
		db.addPlant(categoryName);
		
	}

	private boolean isPlantInDb(String categoryName) {
		return db.isPlantOnDb(categoryName);
	}

	public void editingPlantAction(String string) {
		TextInputDialog dialog = new TextInputDialog(string);
		dialog.setTitle("Edytowanie nazwy Wytwornii:");
		dialog.setHeaderText("Zmiana nazwy wytworni:");
		dialog.initOwner(stage);
		Platform.runLater(() ->{
			Optional<String> response = dialog.showAndWait();
			String newName = response.orElseGet(dialog::getDefaultValue);
			editPlantName(string, newName);
			PlantView view = new PlantView(stage, db);
		});
	}

	private void editPlantName(String string, String newName) {
		db.editPlant(string, newName);
		
	}

	public void deletePlantAction(String text) {
		Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
		dialog.setTitle("Ostrzezenie przed kasacja");
		dialog.setHeaderText("Czy chcesz skasowac "+text+"");
		Platform.runLater(() ->{
			Optional<ButtonType> response = dialog.showAndWait();
			System.out.println(response.get().getText());
			if(response.get().getText().equals("OK")) {
				deletePlant(text);
				PlantView view = new PlantView(stage, db);}
		});
	}

	private void deletePlant(String text) {
		db.deletePlant(text);
		
	}

	public void cencelCategoryAction() {
		MainView main = new MainView(stage, db);
	}
}
