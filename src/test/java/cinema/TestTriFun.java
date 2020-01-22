package cinema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import tool.TriFun;

class TestTriFun {

	@Test
	void test() 
	{
		 TriFun<String,Integer, Integer, Movie> f;
		 f = Movie::new;
		 var m = f.apply("Joker", 2019, 165);
		 System.out.println(m);
		 System.out.println(m.getClass());	 
	}

}
