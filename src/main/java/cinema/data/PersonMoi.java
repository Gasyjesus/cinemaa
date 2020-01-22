package cinema.data;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;
import java.util.OptionalInt;

public class PersonMoi {
	
	private String name;
	private LocalDate birthdate;
	
	public PersonMoi(String name, LocalDate birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}

	public PersonMoi(String name) 									//public Person(String name) 
	{																//{
		this(name, null);											//	super();
	}																//	this.name = name;
																	//} 	

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
	public String toString() {
		return name + " (" + Objects.toString(birthdate, "unknown") + ")";
	}
									
	public OptionalInt getAge() {
		if (Objects.isNull(birthdate)) {
			return OptionalInt.empty();
		}
		LocalDate todayFull = LocalDate.now();
		MonthDay birthday = MonthDay.of(
				birthdate.getMonthValue(), 
				birthdate.getDayOfMonth());
		MonthDay today = MonthDay.now();
		int deltaYear = todayFull.getYear() - birthdate.getYear();
		if (today.compareTo(birthday) < 0) {
			deltaYear--;
		}
		return OptionalInt.of(deltaYear);
	}							

}
