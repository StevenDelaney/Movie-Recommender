package models;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Objects;
import utils.ToJsonString;

/**
 * CLass Movie used to create a movie object which can be assigned
 * a movie id, title, year, and url.
 * @author Steven Delaney
 *
 */
public class Movie implements Comparable<Movie>
{
		static Long counter = 0l;
		public Long movieid;
		public String title;
		public String year;
		public String url;
 
		public List<Rating> ratings = new ArrayList<Rating>();
		
		public Movie(String title, String year, String url)
			{
				this.movieid = counter++;
				this.title = title;
				this.year = year;
				this.url = url;
			}

		public Movie (Long movieid, String title, String year, String url)
			{
				this.movieid = movieid;
				this.title = title;
				this.year = year;
				this.url = url;
	 
			}
		public Long getmovieid()
			{
				return movieid;
			}
 
		/**
		 * Returns an average rating for movies held in system
		 * @return
		 */
		public double getAverageRating()
			{
			if(ratings.isEmpty())//if the movie has no rating. ie.rating of 0
				{
					return 0; //return 0 as rating
				}
			else
				{
					double total = 0;
					for (Rating rating : ratings)
						{
							total += rating.rating;
						}
					return total / ratings.size(); // add all ratings together and divide by the number of ratings
				}
			}
		
		public void setmovieid(int movieid)
			{
				this.movieid = (long) movieid;
			}
 
		public String gettitle()
			{
				return title;
			}
		
		public void settitle(String title)
			{
				this.title = title;
			}
 
		public String getyear()
			{
				return year;
			}
		
		public void setyear(String year)
			{
				this.year = year;
			}
 
		public String geturl()
			{
				return url;
			}
		public void seturl(String url)
			{
				this.url = url;
			}

 
 
 @Override
 public String toString()
 	{
	 	return new ToJsonString(getClass(), this).toString();
 	}

 @Override
 	public int hashCode()
 	{
	 	return Objects.hashCode(this.movieid, this.title, this.year, this.url);
 	}

 @Override
 	public boolean equals(final Object obj)
 	{
	 	if (obj instanceof Movie)
	 	{
	 		final Movie other = (Movie) obj;
	 		return Objects.equal(title, other.title)
	 				&& Objects.equal(year, other.year)
	 				&& Objects.equal(url, other.url);
	 	}
	 	else
	 	{
	 		return false;
	 	}
 	}

 /**
  * Allows us to return a list with highest rated first
  */
public int compareTo(Movie other) 
{
	return Double.compare(this.getAverageRating(), other.getAverageRating());
}


}
