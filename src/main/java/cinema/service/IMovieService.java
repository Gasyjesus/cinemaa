package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cinema.persistence.entity.Movie;

public interface IMovieService
	{
		List<Movie> getAllMovies();
		Optional<Movie> getMovieById(int idMovie);
		Set<Movie> getMovieByPartialTitle(String partialTitle);
		Set<Movie> getMovieByYear(Integer year1, Integer year2);
		Set<Movie> getFindByDirector(int idDirector);
		Set<Movie> getFindByactor(int idActor);
	}


