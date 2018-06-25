package controller;

import DataBase.DataBase;
import javafx.stage.Stage;
import model.Actor;
import view.ActorView;

public class ActorAddController {
Stage stage;
DataBase db;

public ActorAddController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
}


public void cencelAction() {
	ActorView view = new ActorView(stage, db);
}

public void addingActorAction(Actor actor) {
	boolean isOnDb = isOnDataBase(actor);
	actor.toString();
	
	
	if(!isOnDb)
		addActorToDb(actor);
	ActorView actorView = new ActorView(stage, db);
}
private boolean isOnDataBase(Actor actor) {
	return db.isActorInDb(actor.getName(), actor.getName2(), actor.getPseudo());
}
private void addActorToDb(Actor actor) {
	db.addActor(actor);
}
	
}
