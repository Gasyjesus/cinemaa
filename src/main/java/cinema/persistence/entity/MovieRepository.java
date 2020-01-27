package cinema.persistence.entity;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository <Movie, Integer> {
	Set<Movie> findByTitle(String title);
	Set<Movie> findByTitleContainingIgnoreCase(String title);
	Set<Movie> findByYear(Integer year);
//	Set<Movie> findByYearAfter(Integer year);
	Set<Movie> findByYearBetween(Integer year1, Integer year2);
	Set<Movie> findByTitleAndYear(String title, Integer year);
	Set<Movie> findByDirector(Person person);
	Set<Movie> findByDirectorNameEndingWith(String name); 
	Set<Movie> findByActors(Person actor);
	Set<Movie> findByActorsNameEndingWith(String title);
	Set<Movie> findByActorsIdPerson(int idActor);
}
