package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController 
{
	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping
	@ResponseBody
	public List<Movie> allmovies()
	{
		return movieRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("id")int idMovie)
	{
		return movieRepository.findById(idMovie);
	}
	
	@PostMapping
	@ResponseBody
	public Movie addMovie(@RequestBody Movie movie)
	{
		Movie movieSaved = movieRepository.save(movie);
		movieRepository.flush();
		return movieSaved;
	}
	
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByPartialTitle(@RequestParam("t") String partialTitle)
	{
		return movieRepository.findByTitleContainingIgnoreCase(partialTitle);
	}
	
}
