package cinema.service.imp;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.MovieRepository;
import cinema.persistence.entity.PersonRepository;
import cinema.service.IMovieService;

@Service
@Transactional
public class MovieService implements IMovieService
{

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(int idMovie) {
		return movieRepository.findById(idMovie);
	}

	@Override
	public Set<Movie> getMovieByPartialTitle(String partialTitle) {
		return movieRepository.findByTitleContainingIgnoreCase(partialTitle);
	}

	@Override
	public Set<Movie> getMovieByYear(Integer year1, Integer year2) {
		return movieRepository.findByYearBetween( year1, year2);
	}

	@Override
	public Set<Movie> getFindByDirector(int idDirector) {
		var directorOpt = personRepository.findById(idDirector);
		return directorOpt.map(d -> movieRepository.findByDirector(d)).orElseGet(()->Collections.emptySet());
	}

	@Override
	public Set<Movie> findByActorsIDPerson(int idActor) {
		return movieRepository.findByActorsIDPerson(idActor);
	}
	
}