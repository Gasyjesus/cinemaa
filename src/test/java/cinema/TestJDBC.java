package cinema;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import cinema.data.Movie;
import cinema.data.PersonMoi;

class TestJDBC 
{
	static DataSource ds;
	
	@BeforeAll
	static void initDataSource()
	{
		PGSimpleDataSource pgds = new PGSimpleDataSource();
		String host = "localhost";       
		String dbName = "postgres";
		int port = 5432;
		String user = "cinema";
		String password = "password";
		pgds.setURL("jdbc:postgresql://"  + host + ":" + port + "/" + dbName);
		pgds.setUser(user);
		pgds.setPassword(password);
//		System.out.println(pgds.getUrl());
		ds = pgds;
	}

	@Test
	void test() throws SQLException
	{
		String sql = "select * from film";
		var movies = new TreeSet<Movie>(Comparator.comparing(Movie::getTitre).thenComparing(Movie::getAnnee));
		
		try (Connection conn = ds.getConnection();
			 Statement request  = conn.createStatement();
			 ResultSet res = request.executeQuery(sql);)
		{
			while(res.next())
			{
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
				movies.add(new Movie(title, year, duration));
				//System.out.println(title + " " + year + " "  + duration);	
				
	//			movies.add(new MovieMoi(title, year, duration));
				
			}
		}//		conn.close();
		
//		System.out.println(movies);
		int dureeTotal = 0;
		for (var m: movies)
		{
			System.out.println(m.getTitre() + " de durée " + m.getDuration() + "mn");
			dureeTotal += m.getDuration();
		}
		System.out.println("La durée total est de " + dureeTotal + " mn");
	}

	@Test
	void testLireFilmFiltre() throws SQLException
	{
		var movies = new TreeSet<Movie>(Comparator.comparing(Movie::getTitre).thenComparing(Movie::getAnnee));
		String sql = "select * from film where duree >= ?";
		int durationThreshold = 120;
//		sql += durationThreshold; pas bien
//		System.out.println(sql);  
		
		try (
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql);
			 )   {
					   request.setInt(1,  durationThreshold);
					   try (ResultSet res = request.executeQuery())
					   {				   
						   while(res.next())
						   {
							 String title = res.getString("titre");
							 int year = res.getInt("annee");
							 int duration = res.getInt("duree");
							 movies.add(new Movie(title, year, duration));
						   }
					   }
			     }
		System.out.println(movies);
		// check all movies duration >= durationThreshold
		assertAll(movies.stream()
			.map(m -> () -> assertTrue(m.getDuration() >= durationThreshold)));
	}
	
	@Test
	void testLireFilmFiltre2()  throws SQLException
	{
		String sql = "select * from film where duree >= '";
		String sql2 = "'";
		String title = "Joker";
		sql += title + sql2 ;    // c'est pas bien de faire ça
		System.out.println(sql);  
	}
	
	
	@Test
	void testAddPerson() throws SQLException
	{
		var persons = List.of(
				new PersonMoi ("Gene Hackman", LocalDate.of(1930, 1, 30)),		//4
				new PersonMoi ("Morgan Freeman", LocalDate.of(1937, 6, 1)),
				new PersonMoi ("Bradley Cooper", LocalDate.of(1975, 1, 5))
		);
		
		String sql = "insert into individu (prenom, nom, date_naissance) values (?,?,?)";
		try ( 
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql);)
		
			{
				for (var person: persons)
				{
					String fullname = person.getName();
					var parts = fullname.split(" ");
					System.out.println(Arrays.toString(parts));
					request.setString(1, parts[0]);
					request.setString(2, parts[1]);
					request.setDate(3, Date.valueOf(person.getBirthdate()));
					request.executeUpdate();
				}
			}
		
				
	}
}
