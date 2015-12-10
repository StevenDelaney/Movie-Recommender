package models;


import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import com.google.common.base.Objects;

import utils.ToJsonString;

/*
 * @Author Steven Delaney
 * 
 * Class User is used for information relating directly to a user oof the LikeMoviesApi.
 * Holds
 */
public class User
{
		static Long counter = 0l;
		public Long userid;
		public String firstName;
		public String lastName;
		public String gender;
		public String age;
		public String occupation;
		public List<Rating> ratings = new ArrayList<Rating>();
		//private Map<Long,   User>   userIndex       = new HashMap<>();

		/**
		 * Constructor for the user class
		 * Used when adding new users to the system
		 * 
		 * @param firstName The user's first name
		 * @param lastName The user's last name
		 * @param gender The gender of the user
		 * @param age The user's age
		 * @param occupation The user's occupation
		 */
		
		public User(Long userid, String firstName, String lastName, String gender, String age, String occupation)
			{
				this.userid = userid;
				this.firstName = firstName;
				this.lastName = lastName;
				this.gender = gender;
				this.age = age;
				this.occupation = occupation;
	 	
				counter++;
			}
 
 		/*
 		 * Constructor used when loading information from file about a user
 		 * Constructor below is Not in use as CCSVLoader is not working.
 		 */
		public User(String firstName, String lastName, String gender, String age, String occupation)
			{
				this.userid = counter++;
				this.firstName = firstName;
				this.lastName = lastName;
				this.gender = gender;
				this.age = age;
				this.occupation = occupation;
	 	
			}
 
		public void addRating(Rating stars)
			{
				ratings.add(stars);
			}
 
		public String toString()
			{
				return new ToJsonString(getClass(), this).toString();
			}

		@Override
 		public int hashCode()
 			{
				return Objects.hashCode(this.firstName, this.lastName, this.gender, this.age, this.occupation);
 			}

		@Override
		public boolean equals(final Object obj)
			{
				if (obj instanceof User)
			{
					final User other = (User) obj;
					return Objects.equal(firstName, other.firstName)
	 				&& Objects.equal(lastName, other.lastName)
	 				&& Objects.equal(gender, other.gender)
	 				&& Objects.equal(age, other.age)
	 				&& Objects.equal(occupation, other.occupation)
	 				&& Objects.equal(ratings, other.ratings);
			}
				else
					{
						return false;
					}
			}
}