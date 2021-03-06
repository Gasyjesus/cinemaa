package cinema.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	
	private Integer idPerson;
	private String name;
	private LocalDate birthdate;
	
	public Person()
	{
		super();
	}
	
	public Person(String name)
	{
		this(null, name, null);
	}
	
	public Person(String name, LocalDate birthdate)
	{
		this(null, name, birthdate);
	}
	
	
	
	public Person(Integer idPerson, String name, LocalDate birthdate) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.birthdate = birthdate;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Person")
	public Integer getidPerson() {
		return idPerson;
	}

	public void setidPerson(Integer iD) {
		this.idPerson = iD;
	}
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder(name);
		return builder.append(" #")
				.append(idPerson)
				  .toString();
	}
	
	
	
	
	
}

