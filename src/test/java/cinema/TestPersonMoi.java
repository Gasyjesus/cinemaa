package cinema;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cinema.data.PersonMoi;

class TestPersonMoi {

	@Test
	void testCreate() 
	{
		PersonMoi jp = new PersonMoi("Joaquim Phoenix", LocalDate.of(1974, 10, 28));
		PersonMoi tp = new PersonMoi("Todd Phillips");
		PersonMoi gd = new PersonMoi("Gerard Darmond", LocalDate.of(1948, 2, 29));
		System.out.println(jp + " : " + jp.getAge() );
		System.out.println(tp + " : " + tp.getAge() );
		System.out.println(gd + " : " + tp.getAge() );
	}
	

	@Test
	void testBirthdayFeburary()
	{
		// LocalDate birthdate = LocalDate.of(2019, 2, 29); ne marche pas
		   LocalDate birthdate = LocalDate.of(2020, 2, 29);
	}
	
	
	
	
}

