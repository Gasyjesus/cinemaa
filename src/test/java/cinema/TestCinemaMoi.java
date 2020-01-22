package cinema;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.PersonMoi;


class TestCinemaMoi {
	
	private List<Movie> movies;
	private List<PersonMoi> persons;
	
	@BeforeEach 
	void initData()
	{
		persons = new ArrayList<>();
		Collections.addAll(persons,
				new PersonMoi ("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),  //0
				new PersonMoi ("Gerard Darmond", LocalDate.of(1948, 2, 29)),    //1
				new PersonMoi ("Todd Phillips", LocalDate.of(1970,  12,  20)),	//2
				new PersonMoi ("Clint EastWood", LocalDate.of(1930, 5, 31)),	//3	
				new PersonMoi ("Gene Hackman", LocalDate.of(1930, 1, 30)),		//4
				new PersonMoi ("Morgan Freeman", LocalDate.of(1937, 6, 1))		//5																		//4
				);			
		var clint = persons.get(3);
		var tod = persons.get(2);
		
		movies = new ArrayList<>();
		Collections.addAll(movies,
				new Movie ("Joker", 2019, 165, tod),								//0
				new Movie ("Parasite", 2019, 165),								//1
				new Movie ("Interstellar", 2014, 179),							//2
				new Movie ("Grand Torino", 2019, 116, clint),					//3
				new Movie ("Impitoyable", 1992, 131),							//4
				new Movie ("American Sniper", 2014, 133, clint),					//5
				new Movie ("Very Bad Trip", 2009, 100),						//6
				new Movie ("Avengers Infinity War", 2018, 160),
				new Movie ("Avengers Engame",2019, 182),
				new Movie ("Captain Marvel", 2019, 125),
				new Movie ("Avengers", 2012, 143),
				new Movie ("Avengers L'�re D'Ultron",2015, 142),
				new Movie ("Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Alien, Flesh Eating, Hellbound, Zombified Living Dead Part", 1991 )
				);
		
//				List.of( 
//				new PersonMoi ("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),
//				new PersonMoi ("Gerard Darmond", LocalDate.of(1948, 2, 29)),
//				new PersonMoi ("Todd Phillips"),
//				new PersonMoi ("Joaquin Phoenix", LocalDate.of(1930, 5, 31))
//				);
				
				movies.get(0).addActor(persons.get(0));
				movies.get(4).addAllActors(persons.get(3), persons.get(5), persons.get(5));
				var actorsParasite = List.of(
						new PersonMoi ("Kang-ho Song"),
						new PersonMoi ("Yeo-jeong Jo"),
						new PersonMoi ("Woo-Sick Choi"),
						new PersonMoi ("Jeong-eun Lee"));
				persons.addAll(actorsParasite);
				movies.get(1).addAllActors(actorsParasite);
				
				
				
	}
	
	
	@Test
	void test() 
	{
		System.out.println(persons);
		System.out.println(persons.getClass());
		persons.add(new PersonMoi("Bradley Cooper", LocalDate.of(1975, 1, 5 )));
		System.out.println(persons);
	}

	@Test
	void displayMoviesForEach()
	{
		System.out.println("***Movie List***");
		
		for(var movie: movies)										//boucle for sans besoin de cr�er i
		{
			System.out.println(movie + " directed by " + movie.getDirector());
		}
	}
	
	
	
	@Test
	void totalLengthOfMoviesDirectedByClint()
	{
		int totalDuration=0;
		for(var movie: movies)
		{
			if(movie.getDirector() == persons.get(3) )
			totalDuration = totalDuration + movie.getDuration(); 
		}
		System.out.println("Total Duration of movies directed by Clint EastWookd: " + totalDuration + "mn");
	}
	
	@Test
	void testSortedMovies()
	{
		SortedSet<Movie> sortedMovies = new TreeSet<>((m1,m2) -> -1);
		sortedMovies.addAll(movies);
		System.out.println(movies);
	}
	
	
	@Test
	void testSortMovies()
	{
		Collections.sort(movies, 
						 Comparator.comparing(Movie::getAnnee, Comparator.reverseOrder())
						       .thenComparing(Movie::getTitre));
//						 	   .thenComparing(m->m.getTitre()));
		System.out.println(movies);
	}
	
	@Test
	void totaldurationmoviebyclintstreaming()
	{
		var clint = persons.get(3);
		int totalDuration = movies.stream()
			.filter(m -> clint.equals(m.getDirector()))
			.mapToInt(Movie::getDuration)
		 // .forEach(System.out::println);
			.sum();
		System.out.println("TotalDuration de Clint = " + totalDuration);
	}
	
	@Test
	void testFirstYearAvengersMovie()
	{
		var premiereAnnee = movies.stream()
			  .filter(m -> m.getTitre().contains("Avengers"))
			  //.forEach(System.out::println)
			  .mapToInt(Movie::getAnnee)
			  .min();
	}
	
	@Test
	void testFirstLastYearAvengersMovie()
	{
		var stat = movies.stream()
				  .filter(m -> m.getTitre().contains("Avengers"))
				  //.forEach(System.out::println)
				  .mapToInt(Movie::getAnnee) 		// (MovieMoi::getAnnee)  <=>  (m -> m.getAnnee())
				  .summaryStatistics();         // donne les stats classiques 
		System.out.println(stat);
		System.out.println("First Year: " + stat.getMin());
		System.out.println("Last Year: " + stat.getMax());
	}
	
	@Test
	void testChronoAvengersMovie()
	{
		var avengersMovie = movies.stream()
		.filter(m -> m.getTitre().contains("Avengers"))
		.collect(Collectors.toCollection(() -> new TreeSet<>(
				Comparator.comparing(Movie::getAnnee)
						)));
		System.out.println(avengersMovie);
	}
	
	
	@Test
	void titresAvengersMovies()
	{
		var joinedTitles = movies.stream()
				.filter(m -> m.getTitre().contains("Avengers"))
				.map(Movie::getTitre)
				.collect(Collectors.joining(", "));
		System.out.println(joinedTitles);
	}
	
	@Test
	void tetLimites()
	{
		movies.stream()
		.filter((Movie m) -> m.getAnnee() > 1900)
		.limit(5)                             // prend les 5 premiers
		.forEach(System.out::println);;
	}
	
	
	@Test
	void testCbFilmPlusDe2h()
	{
		
		long totalFilmPlus22h = movies.stream()
			.filter(m -> m.getDuration() >= 120)
			.count();
	}
	
	
	@Test
	void testTitreLePlusLong()
	{
		 var maxLength = movies.stream()
				.map(Movie::getTitre)
				.mapToInt(String::length)
				//.forEach(System.out::println)
		 		.max()
		 		.getAsInt();
		 System.out.println(maxLength);
		 
		 var titre = movies.stream()
		 .map(Movie::getTitre)
		 .filter(t -> t.length() == maxLength)
		 .collect(Collectors.toList());
		 System.out.println(titre);		 
	}
	
	
	@Test
	void nbFilmParAnnee()
	{
		var res =movies.stream()
			.collect(Collectors.groupingBy(Movie::getAnnee, Collectors.counting()));   // nombre de ... => counting 
		System.out.println(res);
	}
	
	
	@Test
	void nbFilmParRealisateurV1()    
	{
		var res =movies.stream()
				.filter(m -> Objects.nonNull(m.getDirector()))
				.collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));   
			System.out.println(res);
	}
	
	
	@Test
	void nbFilmParRealisateurV2()
	{
		var res =movies.stream()
				.map(Movie::getDirector)
				.filter(Objects::nonNull)
				.collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));   
			System.out.println(res);
	}
	

	@Test
	void nbFilmParRealisateurV3()
	{
		var filmographies = movies.stream()
				.filter(m -> Objects.nonNull(m.getDirector()))
				.collect(Collectors.groupingBy(Movie::getDirector,
						Collectors.toCollection(
								() -> new TreeSet<>(Comparator.comparing(Movie::getAnnee,
										Comparator.reverseOrder()))
										)));
		
		System.out.println(filmographies);
	}
	
	@Test
	void reaParDecenie()
	{
		var personeParDecade = persons.stream()
				.collect(Collectors.groupingBy( p -> p.getBirthdate().getYear()/10));
		System.out.println(personeParDecade);
	}
	
	
	@Test
	void testParasite()
	{
		movies.stream()
			  .filter(m -> m.getTitre().equals("Parasite"))
			  .findFirst()
			  .ifPresent(System.out::println); 
	}
	
	@Test
	void testParasite2()
	{
		var movie = movies.get(1);
		var actorsText = movie.streamActors()
				.map(PersonMoi::getName)
				.collect(Collectors.joining(", "));
		System.out.println("Acteurs de Parasite: " + actorsText);		
	}
	
	@Test
	void testParasiteIterable()
	{
		var movie = movies.get(1);
		for (var it = movie.iteratorActors(); it.hasNext();)
		{
			var actor = it.hasNext();
			System.out.println(actor);
		}
	}
	
	
}
