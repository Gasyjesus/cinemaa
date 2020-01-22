package cinema;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.PersonMoi;


class TestMovieMoi {
	
	@Test
	void test() {
		
		
		Movie movie = new Movie("Joker", 2019, 165);
		Movie movie2 = new Movie("Parasite", 2019, 132);
		
		
		List<Movie> movies = List.of(movie, movie2);
		
		System.out.println(movie);
		System.out.println(movie2);
		
		System.out.println("Movies: " + movies);
		
		Movie oneMovie = movies.get(0);
		System.out.println("Movie tonight: " + oneMovie);
			
	}

	
	
	@Test
	void testEquals1()
	{
		Movie movieJ = new Movie("Joker", 2019, 165);
		Movie movieP = new Movie("Parasite", 2019, 132);
		Movie movie = movieJ;
		
		System.out.println(movieJ == movieP);
		System.out.println(movieJ == movieJ);
		System.out.println(movie == movieJ);
	}
	
	
	@Test
	void testEquals2()
	{
		Movie movieChaosI = new Movie("Chaos", 2005, 0);
		Movie movieChaosII = new Movie("Chaos", 2005, 0);
		System.out.println(movieChaosI == movieChaosII);
	}
	

	@Test 
	void testMovieAsObject()
	{
		Movie movieChaosI = new Movie("Chaos", 2005, 0);
		Object obj = movieChaosI;
		Movie movie = (Movie) obj;
		System.out.println(movie.getTitre());
		
		//add directors
	    PersonMoi tp = new PersonMoi("Todd Phililips");
		movie.setDirector(tp);;
		System.out.println(movie + "r�alis� par " + movie.getDirector());
		System.out.println(movie.getTitre() + " r�alis� par "
						+ movie.getDirector().getName());
		//Clint EastWood
		PersonMoi clint = new PersonMoi("Clint Eastwood", LocalDate.of(1930, 5, 31));
		System.out.println(clint + " a " + clint.getAge().getAsInt() + " ans ");
		Movie movieGT = new Movie("Gran Torino", 1992, 116, clint);
		Movie movieI = new Movie("Impitoyable", 1992, clint);
		
	}
	

	@Test
	void testEquals() {
		Movie movieChaosI = new Movie("Chaos", 2005);
		Movie movieChaosII = new Movie("Chaos", 2005);
		System.out.println(movieChaosI == movieChaosII);         // retourne false parceque ce ne sont pas les m�mes objets 
		System.out.println(movieChaosI.equals(movieChaosII));    // retourne true parceque contient les m�mes donn�es 
	}
	
	
	
	
	
	
	
	
}
