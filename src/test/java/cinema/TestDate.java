package cinema;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class TestDate {

	@Test
	void testDate() 
	{
		String dateStr = "15/01/2020";
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(date);
		
		assertAll(
				() ->assertEquals(15, date.getDayOfMonth(), "day"),
				() ->assertEquals(01, date.getMonthValue(), "month"),
				() ->assertEquals(2020, date.getYear(), "year" ));
	}

	@Test
	void testFormatDate()
	{
		LocalDate date = LocalDate.now();
		var formats = List.of 
		(
				DateTimeFormatter.ofPattern("dd/MM/yyyy"),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy "),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy ", Locale.US),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy ", new Locale("es", "es")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy ", new Locale("lv", "lv")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy ", new Locale("ru", "ru")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy ", new Locale("ja", "jpn"))
		);
		
		formats.forEach(f -> System.out.println(date.format(f)));
		
	}
	
	@Test
	void testAllLocalesFromIndia()
	{	
		LocalDate date = LocalDate.now();
		var locales = Locale.getAvailableLocales();   		
								   //on peut commencer directement par				
		Arrays.stream(locales)    // Arrays.stream(Locale.getAvailableLocales())
        	.filter(l -> l.getCountry().equalsIgnoreCase("IN"))
        	.map(l -> date.format(DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", l)))
        	.forEach(System.out::println);
	}
	
	@Test
	void tourDumonde80jours()
	{
		var dtHere = LocalDateTime.now();
		System.out.println(dtHere);
		
		var dtNY = LocalDateTime.now(ZoneId.of("America/New_York"));
		System.out.println(dtNY);
		
//		System.out.println(ZoneId.SHORT_IDS);  montre tout les ID des pays
		
		var dtNZ = LocalDateTime.now(ZoneId.of("Pacific/Auckland"));
		System.out.println(dtNZ);
		
		var dtMidway = LocalDateTime.now(ZoneId.of("Pacific/Midway"));
		var fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(" on est le "+ dtMidway.format(fmt));
	}
	
	
}
