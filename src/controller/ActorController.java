package controller;

import java.util.List;
import java.util.Optional;

import DataBase.DataBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Actor;
import view.ActorAddView;
import view.ActorEditView;
import view.ActorView;
import view.CategoryView;
import view.MainView;

public class ActorController {
Stage stage;
DataBase db;

public ActorController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
}

public ObservableList<Actor> getActorsList() {
	List<Actor> list = db.getAllActors();
	ObservableList<Actor> list1 = FXCollections.observableArrayList(list);
	return list1;
}

public Object getActorViewAction(Actor selectedItem) {
	// TODO Auto-generated method stub
	return null;
}

public void getActorAddAction() {
	ActorAddView actorAddView = new ActorAddView(stage, db);
}

public void getActorEditAction(Actor selectedItem) {
	ActorEditView actorView = new ActorEditView(stage, db, selectedItem);
}

public void getActorCencelAction() {
	MainView main = new MainView(stage, db);
}

public void getActorDeleteAction(Actor selectedItem) {
	Alert dialog = new Alert(AlertType.CONFIRMATION);
	dialog.setTitle("Kasacja Aktora");
	dialog.setHeaderText("Czy chcesz skasowaæ aktora");
	Platform.runLater(() ->{
		Optional<ButtonType> response = dialog.showAndWait();
		System.out.println(response.get().getText());
		if(response.get().getText().equals("OK")) {
			deleteActor(selectedItem);
			ActorView actorView = new ActorView(stage, db);
}
	});
}

private void deleteActor(Actor selectedItem) {
	db.deleteActorById(selectedItem.getId());
	
}

}
