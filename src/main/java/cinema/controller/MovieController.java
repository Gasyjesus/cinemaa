package cinema.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.MovieRepository;
import cinema.persistence.entity.Person;
import cinema.persistence.entity.PersonRepository;
import cinema.service.IMovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController 
{
	@Autowired
	IMovieService movieService;
	
	@GetMapping
	@ResponseBody
	public List<Movie> getAllMovies()
	{
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("id")int idMovie)
	{
		return movieService.getMovieById(idMovie);
	}

	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByPartialTitle(@RequestParam("t") String partialTitle)
	{
		return movieService.getMovieByPartialTitle(partialTitle);
	}
	
	
	@GetMapping("/betweenYear")
	@ResponseBody
	public Set<Movie> movieByYear(@RequestParam("y1") Integer year1, @RequestParam("y2") Integer year2)
	{
		return movieService.getMovieByYear(year1, year2);
	}
	

	@GetMapping("/byDirector")
	@ResponseBody
	public Set<Movie> findByDirector(@RequestParam("d") int idDirector)    //sur le site ça donne api/movies/byDirector?d=
	{
		return movieService.getFindByDirector(idDirector);	
	}
	
	

	
	@GetMapping("/byActor")
	@ResponseBody
	public Set<Movie> findByactor(@RequestParam("a") int idActor)
	{
		return movieService.findByActorsIDPerson(idActor);	
	}
	
//	
//	@PostMapping
//	@ResponseBody
//	public Movie addMovie(@RequestBody Movie movie)
//	{
//		Movie movieSaved = movieService.save(movie);
//		movieService.flush();
//		return movieSaved;
//	}
//	
//	
	
	
//	@PutMapping("/modify")
//	@ResponseBody
//	public Optional<Movie> modifyMovie(@RequestBody Movie movie)
//	{
//		var optMovie = movieService.findById(movie.getId_movie());
//		// TODO : anywhere else
//		optMovie.ifPresent(m -> {
//									m.setTitle(movie.getTitle());
//									m.setYear(movie.getYear());
//									m.setDuration(movie.getDuration());
//									m.setDirector(movie.getDirector());
//							   });
//		//
//		movieService.flush();		
//		return optMovie;
//	}
//	
//	@PutMapping("/addActor")
//	public Optional<Movie> addActor(@RequestParam("a") int idActor, @RequestParam("m") int idMovie)
//	{//TODO : anywhere else
//		var movieOpt = movieService.findById(idMovie);
//		var actorOpt = personRepository.findById(idActor);
//		if (movieOpt.isPresent() && actorOpt.isPresent())
//		{
//			movieOpt.get().getActors().add(actorOpt.get());
//			movieService.flush();
//		}
//		return movieOpt;
//	}
//	
//	
//	@DeleteMapping("/{id}")
//	@ResponseBody
//	public Optional<Movie> deleteMovie(@PathVariable("id") int idMovie)
//	{
//		var movieToDelete = movieService.findById(idMovie);
//		movieToDelete.ifPresent(m -> 
//									 {
//										movieRepository.delete(m);
//										movieRepository.flush();
//									 }
//								);
//		return movieToDelete;
//	}
//	
//	@PutMapping("/setDirector")
//	public Optional<Movie> setDirector(@RequestParam("d") int idDirector, @RequestParam("m") int idMovie)
//	{  //TODO : anywhere else
//		
//		var movieOpt     = movieService.findById(idMovie);
//		var directorOpt = personRepository.findById(idDirector);
//		
//		if (movieOpt.isPresent() && directorOpt.isPresent())
//		{
//			movieOpt.get().setDirector(directorOpt.get());
//			movieService.flush();
//		}
//		return movieOpt;
//	}
//	
	
	
	
}
