package cinema.persistence.entity;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	Set<Person> findByName(String name);
	
	
//	@Query("select p from Person p where extract(year from p.birthdate) = ?")
//	Set<Person> findByBirthdateYear(int year);
//	
}
