package controller;

import DataBase.DataBase;
import javafx.stage.Stage;
import model.Actor;
import view.ActorView;

public class ActorEditController {
Stage stage;
DataBase db;
	
	public ActorEditController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
	}

	public void cencelAction() {
	ActorView view = new ActorView(stage, db);
}

	public void editActorAction(Actor actor, Actor actorNew) {
		db.editActor(actor, actorNew);
		ActorView actorView = new ActorView(stage, db);
	}
	
	

}
