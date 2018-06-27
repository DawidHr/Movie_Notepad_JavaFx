package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Actor;
import model.Category;
import model.Movie;

public class DataBase {
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:Movie_Notepad_JavaFX.s3db";
	private Statement stat;

	Connection conn;

	public DataBase() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
			System.out.println("Po³aczono");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*Category*/
	
	//Pobieranie wszystkich kategorii
	public List<Category> getAllCategories() {
		List<Category> list = new LinkedList<>();
		String query = "Select * from Category";
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				list.add(new Category(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Sprawdzanie czy nazwa kategorii znajduje sie w bazie danych
	public boolean isCategoryOnDb(String text) {
		boolean isOnDb = false;
		String query = "select * from Category where name=?";
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement(query);
			stat.setString(1, text);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				isOnDb = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isOnDb;
	}
//Dodawanie Kategorii do bazy danych
	public void addCategory(String text) {
		String query = "insert into Category(name) values(?)";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, text);
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
// Edycja nazwy kategorii
	public void editCategory(String oldName, String newName) {
		String query = "Update Category set name=? where name=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, newName);
			stat.setString(2, oldName);
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
// Kasacja kategorii
	public void deleteCategory(String text) {
		String query = "delete from Category where name=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, text);
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getIdCategoryByTitle(String category) {
		String query = "select id from Category where name=?";
		int id=0;
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, category);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				id=rs.getInt("id");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	public String getStringCategoryByTitle(int id) {
		String name="";
		String query = "select name from Category where id=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				name= rs.getString("name");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	/* WYTWORNIE */
	
	//Pobieranie wszystkich wytworni
	public List<String> getAllPlant() {
		List<String> list = new LinkedList<String>();
		String query = "Select * from Plant";
		try {
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		while(rs.next()) {
			list.add(rs.getString("name"));
		}
		} catch(Exception e) {
		e.printStackTrace();
		}
		return list;
	}

	//Sprawdzanie czy wytwornia jest w bazie danych
	public boolean isPlantOnDb(String categoryName) {
		boolean isPlantOnDB = false;
		try {
		String query ="select * from Plant where name=?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, categoryName);
		ResultSet rs = stat.executeQuery();
		while(rs.next()) {
			isPlantOnDB = true;
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isPlantOnDB;
	}

	// Dodawanie do bazy danych wytworni
	public void addPlant(String categoryName) {
		try {
		String query = "insert into Plant(name) values(?)";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, categoryName);
		stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
// Edycja wytwornii
	public void editPlant(String string, String newName) {
		String query = "Update Plant set name=? where name=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, newName);
			stat.setString(2, string);
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//kasacja wytwornii
	public void deletePlant(String text) {
		String query = "delete from Plant where name=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, text);
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getIdPlantByTitle(String plant) {
		String query = "select id from Plant where name=?";
		int id=0;
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, plant);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				id=rs.getInt("id");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getStringPlantByTitle(int id) {
		String plant="";
		String query = "select name from Plant where id=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				plant=rs.getString("name");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return plant;
	}
/*Aktorzy*/
	
	//Pobieranie wszytkich aktorów
	public List<Actor> getAllActors() {
		List<Actor> list = new LinkedList<>();
		String query = "select * from Actor";
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				list.add(new Actor(rs.getInt("id"), rs.getString("name"), rs.getString("name2"), rs.getString("pseudo"), rs.getString("sex"), rs.getString("hair_color"), rs.getString("hair_size"), rs.getString("note")));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//kasacja aktora
	public void deleteActorById(int id) {
		String query = "delete from Actor where id=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
// Sprawdzanie czy Aktor istnieje w bazie danych
	public boolean isActorInDb(String name, String name2, String pseudo) {
		boolean isOnDb = false;
		String query = "select * from Actor where name = ? and name2 = ? and pseudo = ?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, name);
			stat.setString(2, name2);
			stat.setString(3, pseudo);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				isOnDb = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isOnDb;
	}
 // Dodawanie aktora do bazy danych
	public void addActor(Actor actor) {
		String query = "insert into Actor(name, name2, pseudo, sex, hair_color, hair_size, note) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, actor.getName());
			stat.setString(2, actor.getName2());
			stat.setString(3, actor.getPseudo());
			stat.setString(4, actor.getSex());
			stat.setString(5, actor.getHair_color());
			stat.setString(6, actor.getHair_size());
			stat.setString(7, actor.getNote());
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//Edycja aktora
	public void editActor(Actor actor, Actor actorNew) {
		String query = "update Actor set name=?, name2=?, pseudo=?, sex=?, hair_color=?, hair_size=?, note=? where id=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, actorNew.getName());
			stat.setString(2, actorNew.getName2());
			stat.setString(3, actorNew.getPseudo());
			stat.setString(4, actorNew.getSex());
			stat.setString(5, actorNew.getHair_color());
			stat.setString(6, actorNew.getHair_size());
			stat.setString(7, actorNew.getNote());
			stat.setInt(8, actor.getId());
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* Filmy */
	
	//Kasacja filmu
	public void deleteMovie(Movie movie) {
		String query = "delete from Movie where id =?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, movie.getId());
			stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addMovie(Movie movie) {
		String query = "insert into Movie(title, file_title, file_url, id_category, id_Plant) values(?, ?, ?, ?, ?)";
		try {
			int id_category = getIdCategoryByTitle(movie.getCategory());
			int id_Plant = getIdPlantByTitle(movie.getPlant());
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, movie.getTitle());
			stat.setString(2, movie.getFile_title());
			stat.setString(3, movie.getFile_url());
			stat.setInt(4, id_category);
			stat.setInt(5, id_Plant);
			stat.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public ObservableList<Movie> getAllMovies() {
		ObservableList<Movie> list = FXCollections.observableArrayList();
		String query = "select * from Movie";
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				String category = getStringCategoryByTitle(rs.getInt("id_category"));
				String Plant = getStringPlantByTitle(rs.getInt("id_Plant"));
				list.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getString("file_title"), rs.getString("file_url"), category, Plant, rs.getString("note")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Pobieranie id filmu
	public int getIdMovie(Movie movie) {
		int id=0;
		String query = "select id from Movie where title=? and file_title=?";
		try {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, movie.getTitle());
			stat.setString(2, movie.getFile_title());
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				id=rs.getInt("id");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public void addActorsToMovie(int id_movie, List<Actor> listActor) {
		String query = "insert into Movie_Actor(id_movie, id_actor) values(?, ?)";
		try {
			for(int i=0;i<listActor.size();i++) {
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setInt(1, id_movie);
			stat.setInt(2, listActor.get(i).getId());
			stat.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}