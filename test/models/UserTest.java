package models;

import static org.junit.Assert.*;
import org.junit.Test;


public class UserTest
{
  User homer = new User ("homer", "simpson", "male", "25", "Student");

  @Test
  public void testCreate()
  {
    assertEquals ("homer",               homer.firstName);
    assertEquals ("simpson",             homer.lastName);
    assertEquals ("male",   			 homer.gender);   
    assertEquals ("25",              	 homer.age);   
    assertEquals ("Student",             homer.occupation);
  }

  
  @Test
  public void testToString()
  {
    //assertEquals ("User{" + homer.userid + ", homer, simpson, male, 25, Student}", homer.toString());
   
  }
  
  @Test
  public void testEquals()
  {
    User homer2 = new User ("homer", "simpson", "male", "25", "Student"); 
    User bart   = new User ("bart", "simpson", "male",  "20", "Student"); 

    assertEquals(homer, homer);
    assertEquals(homer, homer2);
    assertNotEquals(homer, bart);
  } 
  
}