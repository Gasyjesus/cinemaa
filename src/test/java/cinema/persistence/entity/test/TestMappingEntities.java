package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistence.entity.Person;
import cinema.persistence.entity.PersonRepository;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.MovieRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMappingEntities {

	@Autowired
	PersonRepository repoPersons;
	
	@Autowired
	MovieRepository repoMovies;
	
	@Rollback(false)
	@Test
	void testSaveData() 
	{
			var joaq =	new Person ("Joaquin Phoenix", LocalDate.of(1974, 10, 28));  //0
			var gege =	new Person ("Gerard Darmond", LocalDate.of(1948, 2, 29)) ;  //1
			var todd =	new Person ("Todd Phillips", LocalDate.of(1970,  12,  20));	//2
			var clint =	new Person ("Clint EastWood", LocalDate.of(1930, 5, 31));	//3	
			var gene = 	new Person ("Gene Hackman", LocalDate.of(1930, 1, 30));		//4
			var morgan= new Person ("Morgan Freeman", LocalDate.of(1937, 6, 1));		//5																		//4
			var persons = List.of(joaq, gege, todd, clint, gene, morgan);			
			persons.forEach(repoPersons::save);
			
			
				var joker =				new Movie ("Joker", 2019, 165, todd);								//0
				var parasite =			new Movie ("Parasite", 2019, 165);							//1
				var interstellar =		new Movie ("Interstellar", 2014, 179);							//2
				var granTorino =		new Movie ("Grand Torino", 2019, 116, clint);					//3
				var impitoyable=		new Movie ("Impitoyable", 1992, 131);							//4
				var americanSniper=		new Movie ("American Sniper", 2014, 133, clint);					//5
				var vbd =				new Movie ("Very Bad Trip", 2009, 100);			//6
				var avengersInfinityWar=new Movie ("Avengers Infinity War", 2018, 160);
				var avengersEngame =	new Movie ("Avengers Engame",2019, 182);
				var captainMarvel =		new Movie ("Captain Marvel", 2019, 125);
				var avengers =			new Movie ("Avengers", 2012, 143);
				var avengersLere =		new Movie ("Avengers L'�re D'Ultron",2015, 142);
				var night =				new Movie ("Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Alien, Flesh Eating, Hellbound, Zombified Living Dead Part", 1991 );
					
			var movies = List.of(joker, parasite, interstellar, granTorino, impitoyable, americanSniper, vbd,
					             avengersInfinityWar, avengersEngame, captainMarvel, avengers, avengersLere, night );
			movies.forEach(repoMovies::save);
		
	}
	
	
	@Rollback(false)
	@Test
	void testRajouterChrisEnDirectordInterstellar()
	{
		var movies = repoMovies.findByTitle("Interstellar");
		var chris =	new Person ("Christopher Nolan", LocalDate.of(1970, 07, 30));
		
		if (movies.size()>0)
		{
			var interstellar = movies.stream().findFirst().get();
			repoPersons.save(chris);
			interstellar.setDirector(chris);
		}
	}
	
//	
//	@Rollback(false)
//	@Test
//	void testselectMovieWithDirector()
//	{
//		var movies = repoMovies.findByTitle("Interstellar");
//		var chris =	new Person ("Christopher Nolan", LocalDate.of(1970, 07, 30));
//		
//		if (movies.size()>0)
//		{
//			var interstellar = movies.stream().findFirst().get();
//			var director = interstellar.getDirector();
//			interstellar.setDirector(chris);
//		}
//	}
	
	@Rollback(false)
	@Test
	void testAjouterBatManDK()
	{
		// add movie from front action
		Movie bDk =	new Movie ("Batman The Dark Knight", 2008, 152);
		
		// add movie in the repository
		repoMovies.save(bDk);
		repoMovies.flush();
		
		//load chris nolan from database
		var nolan = repoPersons.findByName("Christopher Nolan").stream().findFirst().get();
		bDk.setDirector(nolan);

	}

	@Test
	void scenario5SelectByDirector()
	{
		var data1 = repoMovies.findByDirectorNameEndingWith("Eastwood");
		var nolan = repoPersons.findByName("Christopher Nolan").stream().findFirst().get();
		var data2 = repoMovies.findByDirector(nolan);
	}
	
	@Rollback(false)
	@Test
	void scenarCreerLienActeur()
	{
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		var clint = repoPersons.findByName("Gene Hackman").stream().findFirst().get();
		var gene = repoPersons.findByName("Clint Eastwood").stream().findFirst().get();
		impitoyable.setActors(List.of(clint,gene));
		repoMovies.flush();
	}
	
	@Test
	void testLazyActors()
	{
		// read a movie : elec the movie + its director
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		//read actors
		var actors = impitoyable.getActors();
		
		
		
	}
	
	@Rollback(false)
	@Test
	void scenar8MovieAddActor()
	{
		var impitoyable = repoMovies.findByTitle("Impitoyable").parallelStream().findFirst().get();
		var morgan = repoPersons.findByName("Morgan Freeman").stream().findFirst().get();
		impitoyable.getActors().add(morgan);
		repoMovies.flush();
	}
	
	@Rollback()
	@Test
	void ajouterLesPleinsPouvoirEtrajouterClintEtGeneEnActeur()
	{
		var lPP =	new Movie ("Les Pleins Pouvoirs", 1997, 121);
		
		// add movie in the repository
		repoMovies.save(lPP);
		repoMovies.flush();
		
		//load clint & gene from database
		var gene = repoPersons.findByName("Gene Hackman").stream().findFirst().get();
		var clint = repoPersons.findByName("Clint Eastwood").stream().findFirst().get();
		
		lPP.getActors().add(gene);
		lPP.getActors().add(clint);
		repoMovies.flush();
		
	}
}
