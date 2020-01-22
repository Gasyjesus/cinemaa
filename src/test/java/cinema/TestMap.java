package cinema;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class TestMap {

	@Test
	void test() {
		
		Map<Integer,Integer> nbFilmsParAnnee = new TreeMap<>();
		nbFilmsParAnnee.put(2019, 23);
		nbFilmsParAnnee.put(2017, 12);
		nbFilmsParAnnee.put(2014, 7);
		nbFilmsParAnnee.put(1992, 22);
		System.out.println(nbFilmsParAnnee);
		
		nbFilmsParAnnee.put(2014, 13);
		System.out.println(nbFilmsParAnnee);
		System.out.println("En 2017 : " + nbFilmsParAnnee.get(2017));
		
		for(var year: nbFilmsParAnnee.keySet())
		{
			System.out.println(" * year: " + year);
		}
		
		for(var year: nbFilmsParAnnee.values())
		{
			System.out.println("� nb de films : " + year);
		}
		
		
	}

}
