package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DataBase.DataBase;
import javafx.stage.Stage;
import model.Cinema;

public class CinemaController {
Stage stage;
DataBase db;

public CinemaController(Stage stage, DataBase db) {
	this.stage=stage;
	this.db=db;
}

public void getCityList() {
	List<Cinema> list = new LinkedList<>();
	  Connection connect = Jsoup.connect("https://www.helios.pl/3,Wroclaw/StronaGlowna/");
      Document document;
	try {
		document = connect.get();
		Elements listElements1 = document.select("select");
		Elements listElements2 = document.getElementsByClass("custom-select-more");
		for(Element elem: listElements1) {
	      //  System.out.println(elem.text());
	    list.add(new Cinema(elem.text(), "Helios"));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}
