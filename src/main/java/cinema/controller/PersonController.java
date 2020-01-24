package cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

public class PersonController {

	@Autowired
	IPersonService personService;
	
	@GetMapping
	Person addPerson(Person person)
	{
		return person;
	}
	
	@GetMapping
	List<Person> getAllPersons()
	{
		return personService.getAllPersons();
	}
	
	
	
	
}
