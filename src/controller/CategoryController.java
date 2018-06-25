package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import DataBase.DataBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Category;
import view.CategoryView;
import view.MainView;

public class CategoryController {
	DataBase db;
	Stage stage;
	public CategoryController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
	}
	
	public ObservableList<String> setCategoryList() {
		List<Category> list = getCategories();
		List<String> listString = getStringCategories(list);
		ObservableList<String> list1 = FXCollections.observableArrayList(listString);
		return list1;
	}
	private List<Category> getCategories() {
		return db.getAllCategories();
	}
	private List<String> getStringCategories(List<Category> list1) {
		List<String> list = new LinkedList<String>();
		for(Category c: list1) {
			list.add(c.getName());
		}
		return list;
	}
	
	public void addingCategoryAction() {
		 
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Dodawana Kategoria");
		dialog.setHeaderText("Proszê podaæ nazwê kategorii dodawanej:");
		dialog.initOwner(stage);
		Platform.runLater(() ->{
			Optional<String> response = dialog.showAndWait();
			String categoryName= response.orElseGet(dialog::getDefaultValue);
			boolean isOnDb = isCategoryInDb(categoryName);
			if(!isOnDb)
				addingToDb(categoryName);
			CategoryView view = new CategoryView(stage, db);
		});
	}
	private boolean isCategoryInDb(String text) {
		return db.isCategoryOnDb(text);
	}
	private void addingToDb(String text) {
		db.addCategory(text);
	}
	public void editingCategoryAction(String text) {
		TextInputDialog dialog = new TextInputDialog(text);
		dialog.setTitle("Edytowanie Kategorii");
		dialog.setHeaderText("Zmiana nazwy kategorii:");
		dialog.initOwner(stage);
		Platform.runLater(() ->{
			Optional<String> response = dialog.showAndWait();
			String newName = response.orElseGet(dialog::getDefaultValue);
			editCategoryName(text, newName);
			CategoryView view = new CategoryView(stage, db);
		});
	}
	private void editCategoryName(String oldName, String newName) {
		db.editCategory(oldName, newName);
	}
	
	public void deleteCategoryAction(String text) {
		System.out.println("jej");
		Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
		dialog.setTitle("Ostrzezenie przed kasacja");
		dialog.setHeaderText("Czy chcesz skasowac "+text+"");
		Platform.runLater(() ->{
			Optional<ButtonType> response = dialog.showAndWait();
			System.out.println(response.get().getText());
			if(response.get().getText().equals("OK")) {
				deleteCategory(text);
				CategoryView view = new CategoryView(stage, db);}
		});
		
	}
	private void deleteCategory(String text) {
		db.deleteCategory(text);
	}
	public void cencelCategoryAction() {
		System.out.println("tu jestem");
		MainView main = new MainView(stage, db);
	}

}
