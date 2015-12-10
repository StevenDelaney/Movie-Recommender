package models;


import com.google.common.base.Objects;
import utils.ToJsonString;

/*
 * @Author Steven Delaney
 * Rating class used to store information about what rating a user gives to a movie
 */
public class Rating 
{ 
		public Long movieid;
		public Long userid;
		public int rating;

  
		public Rating(Long movieid, Long userid, int rating)
			{
				this.rating      = rating;
				this.movieid  = movieid;
				this.userid  = userid;
			}
  
		public Long getmovieid()
			{
				return movieid;
			}
  
		public Long getuserid()
			{
				return userid;
			}
		
		public int getrating()
			{
				return rating;
			}
  
		@Override
		public String toString()
			{
				return new ToJsonString(getClass(), this).toString();
			}
  
		@Override  
		public int hashCode()  
			{  
				return Objects.hashCode(this.rating, this.movieid, this.userid);  
			} 
  
		@Override
		public boolean equals(final Object obj)
			{
				if (obj instanceof Rating)
					{
						final Rating other = (Rating) obj;
						return Objects.equal(rating, other.rating) 
								&& Objects.equal(movieid,  other.movieid)
								&& Objects.equal(userid,  other.userid);    
					}
				else
					{
						return false;
					}
			}
}