package view;

import java.io.File;
import java.net.URI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class WatchMovie {

	Button buttonStart = new Button("Start");
	Button buttonPause = new Button("Pauza");
	Button buttonStop = new Button("Stop");
	Slider sliderVolume = new Slider(0, 100, 50);
	Slider sliderBass = new Slider(0, 100, 50);
	Label labelVolume = new Label("G³oœnoœæ");
	Label labelBass = new Label("Bass");
	GridPane panelGridPane = new GridPane();
	File f = new File("D:\\Muza+Teledyski\\Rapoholika - Pasja ( Official video ) prod. SwR.mp4");
	URI u = f.toURI();
	File file1 = new File("D:\\Muza+Teledyski\\Rapoholika - Pasja ( Official video ) prod. SwR.mp4");
	URI url1 = file1.toURI();
	Media media = new Media(url1.toString());
	MediaPlayer mediPlayer = new MediaPlayer(media);
	MediaView mediaView = new MediaView(mediPlayer);
	Stage stage;
	
	public WatchMovie(Stage stage) {
		this.stage=stage;
		mediPlayer.setAutoPlay(false);
		main();
		Rows();
		Columns();
		setActions();
		setStage();
	}
	
	private void main() {
		panelGridPane.setGridLinesVisible(true);
		panelGridPane.setConstraints(mediaView, 0, 0, 5, 3, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(labelBass, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(sliderBass, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(buttonStop, 1, 3, 1, 2, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(buttonStart, 2, 3, 1, 2, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(buttonPause, 3, 3, 1, 2, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(labelVolume, 4, 3, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.setConstraints(sliderVolume, 4, 4, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(20, 20, 20, 20));
		panelGridPane.getChildren().addAll(mediaView, labelBass, sliderBass, buttonStop, buttonStart, buttonPause, labelVolume, sliderVolume);
	}
	private void Rows() {
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(20);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(20);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(20);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(20);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(20);
		panelGridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5);
	}
	
	private void Columns() {
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(20);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(20);
		ColumnConstraints c3 = new ColumnConstraints();
		c3.setPercentWidth(20);
		ColumnConstraints c4 = new ColumnConstraints();
		c4.setPercentWidth(20);
		ColumnConstraints c5 = new ColumnConstraints();
		c5.setPercentWidth(20);
		panelGridPane.getColumnConstraints().addAll(c1, c2, c3, c4, c5);
	}
	
	private void setActions() {
		buttonStart.setOnAction(e-> {mediPlayer.play();});
		buttonPause.setOnAction(e-> {mediPlayer.stop();});
		buttonStop.setOnAction(e-> {mediPlayer.pause();});
	}
	
	private void setStage() {
		Stage stage2 = new Stage();
		Scene scene2 = new Scene(panelGridPane, 400, 400);
		stage2.setScene(scene2);
		stage2.initOwner(stage);
		stage2.show();
	}
}
