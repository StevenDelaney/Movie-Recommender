package models;

import static org.junit.Assert.*;
import org.junit.Test;



public class MovieTest
{ 
	Movie Simpsons = new Movie ("Simpsons", "2015", "www.simpsons.com");
  @Test
  public void testCreate()
  {
    assertEquals ("Simpsons",			Simpsons.title);
    assertEquals ("2015",				Simpsons.year);
    assertEquals ("www.simpsons.com",	Simpsons.url);
  }


  @Test
  public void testToString()
  {
 //   assertEquals ("Movie{" + Simpsons.movieid + ", Simpsons, 2015, www.simpsons.com}", Simpsons.toString());
  }
}