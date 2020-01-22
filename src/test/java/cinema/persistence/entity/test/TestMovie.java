package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.MovieRepository;
import cinema.persistence.entity.Person;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
class TestMovie {

	@Autowired
	MovieRepository repoMovie;

	@Autowired
	EntityManager entityManager;

	@Test
	void test() 
	{
		Movie movie = new Movie("Joker", 2019);
		repoMovie.save(movie);
		var id = movie.getId_movie();
		System.out.println("iD =" + movie.getId_movie());
		assertNotNull(id);
	}

	@Test
	void testSelectAll()
	{
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite", 2019, 132),
				new Movie("Interstellar", 2014),
				new Movie("Gran Torino", 2008, 116)
				);
		data.forEach(entityManager::persist);

		//when
		var dataRead = repoMovie.findAll();

		//then
		dataRead.forEach(System.out::println);
		assertEquals(data.size(), dataRead.size());

		assertTrue(dataRead.stream()
				.map(Movie::getTitle)
				.allMatch(tr -> data.stream()
						.map(Movie::getTitle)
						.anyMatch(th -> th.equals(tr))
						));
	}


	@Test
	void testFindByAny() 
	{
		//given
		Movie movie = new Movie("Joker", 2019);
		entityManager.persist(movie);
		var id = movie.getId_movie();

		//when 
		var movieReadOpt = repoMovie.findById(id);
		System.out.println(movieReadOpt);

		assertAll(
				() -> assertTrue(movieReadOpt.isPresent()),
				() -> assertEquals(movie.getTitle(), movieReadOpt.get().getTitle()));
	}

	@Test
	void testFindByTitle()
	{
		//given
		String title = "Le Roi Lion";
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie(title, 2019),
				new Movie(title, 1994)
				);
		data.forEach(entityManager::persist);

		//when
		var dataRead = repoMovie.findByTitle(title);
		System.out.println(dataRead);
	}

	@Test
	void testFindByYear()
	{
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite", 2019, 132),
				new Movie("Interstellar", 2014),
				new Movie("Gran Torino", 2008, 116),
				new Movie("Le Roi Lion",1994)
				);
		data.forEach(entityManager::persist);

		//when
		var dataRead = repoMovie.findByYear(2019);

		//then
		System.out.println(dataRead);


	}

	//	
	//	@Test
	//	void testFindByYearAfter()
	//	{
	//		List<Movie> data = List.of(
	//				new Movie("Joker", 2019),
	//				new Movie("Parasite", 2019, 132),
	//				new Movie("Interstellar", 2014),
	//				new Movie("Gran Torino", 2008, 116),
	//				new Movie("Le Roi Lion",1994)
	//				);
	//		data.forEach(entityManager::persist);
	//		
	//	}
	//	

	@Test 
	void testFindByYeartBetween()
	{
		//given
		int year1 = 1995;
		int year2 = 2015;
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Le Roi Lion", 1994, 132),
				new Movie("Seven", year1),
				new Movie("Gran Torino", 2008, 116),
				new Movie("Mad Max Fury Road",year2)
				);
		data.forEach(entityManager::persist);

		//when 
		var dataRead = repoMovie.findByYearBetween(year1, year2);

		//then 
		System.out.println(dataRead);
		assertAll(
				() -> assertEquals(3, dataRead.size()),
				() -> assertTrue(dataRead.stream().mapToInt(Movie::getYear)
						.allMatch(y -> (y>= year1) && (y<=year2))));
	}

	@Test
	void testFindByTitleAndYear()
	{
		//given
		int annee = 1994;
		String titre = "Le Roi Lion";
		List<Movie> data = List.of(
				new Movie("Joker", 2019),
				new Movie("Parasite", 2019, 132),
				new Movie("Interstellar", 2014),
				new Movie("Gran Torino", 2008, 116),
				new Movie("Le Roi Lion",1994),
				new Movie("Le Roi Lion",2019)
				);
		data.forEach(entityManager::persist);

		//when
		var dataRead = repoMovie.findByTitleAndYear(titre, annee);

		//then
		System.out.println(dataRead);
	}

	@Test
	void testSaveWithDirector() 
	{
		//given
		Person person = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));
		Movie movie = new Movie("Joker", 2019);
		entityManager.persist(person);

		//when
		repoMovie.save(movie);

		//Then
		System.out.println(movie);
		System.out.println(person);




	}



}
