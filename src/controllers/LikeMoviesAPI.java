package controllers;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

import utils.Serializer;
//import utils.CSVLoader;
import models.User;
import models.Movie;
import models.Rating;

/**
 * @Author Steven Delaney
 *LikeMovisApi class is used to store information needed in the main class methods.
 */

public class LikeMoviesAPI
	{
		private Serializer serializer;
		private Map<Long,   User>   userIndex       = new HashMap<>();
		private Map<String, Long>	usernameIndex 	= new HashMap<>();
		private Map<String, Rating> 	ratingsIndex 	= new HashMap<>();
		private Map<Long,   Movie>  movieIndex     	= new HashMap<>();
		private Map<String, Long>  movienameIndex     	= new HashMap<>();
		
		/**
		 * Constructor for LikeMoviesAPI 
		 * 
		 * @param serializer The serializer used to load/store data
		 * NOTE:Not in use as CSVLoader Not working correctly
		 */
		public LikeMoviesAPI(Serializer serializer)
			{
				this.serializer = serializer;
			}
  
		@SuppressWarnings("unchecked")
  		public void load() throws Exception
  			{
	  			serializer.read();
	  			ratingsIndex = (Map<String, Rating>) 		serializer.pop();
	  			movieIndex    = (Map<Long, Movie>)     	serializer.pop();
	  			userIndex    = (Map<Long, User>)     	serializer.pop();
	  			usernameIndex    = (Map<String, Long>)     	serializer.pop();
	  			movienameIndex    = (Map<String, Long>)     	serializer.pop();

  			}
  
  		public void store() throws Exception
  			{
				serializer.push(movienameIndex);
  				serializer.push(usernameIndex);
  				serializer.push(userIndex);
  				serializer.push(movieIndex);
  				serializer.push(ratingsIndex);
  				serializer.write(); 
  			}
  
  		/**
  		 * Method used in Main class to show all users in System.
  		 * @return All users stored in System
  		 */
  		public Collection<User> getUsers ()
  			{
  				return userIndex.values();
  			}
  
  		/**
  		 * Method used in Main class to show all Movies in System.
  		 * @return All movies stored in System
  		 */
  		public Collection<Movie> getMovies ()
  			{
  				return movieIndex.values();
  			}
  
  		/**
  		 * Method used in Main class to show all ratings in System.
  		 * @return All ratings stored in System
  		 */
  		public Collection<Rating> getRating ()
  			{
  				return ratingsIndex.values();
  			}
  
  		/**
  		 * Method used in Main class to delete user from System.
  		 * @return Userid of user deleted
  		 */
  		public User deleteUser(Long userid) 
  			{
  				return userIndex.remove(userid);
  			}
  
  		/**
  		 * Method used in Main class to delete movie from System.
  		 * @return deleted movie
  		 */
  		public Movie deleteMovie(Long movieid) 
  			{
  				return movieIndex.remove(movieid);
  			}
  
  		/**
  		 * Method used in Main class to Create user and add to system.
  		 * @return user
  		 */
  		public User createUser(String firstName, String lastName, String age, String gender, String occupation) 
  			{
  				User user = new User (firstName, lastName, gender, age, occupation);
  				userIndex.put(user.userid, user);
  				usernameIndex.put(user.firstName + " " + user.lastName,  user.userid);
  				return user;
  			}

  		/**
  		 * Method used in Main class to show a specific user in system.
  		 * @return user depending on userid
  		 */
  		public User getUser(Long userid) 
  			{
  				return userIndex.get(userid);
  			}


  		/**
  		 * Method used in Main class return rating for a specific movie
  		 * @return ratings
  		 */
  		public Rating getRating (Long id)
  			{
  				return ratingsIndex.get(id);
  			}
  
  		/**
  		 * Method used in Main class to create a movie and add to system
  		 * @return movie
  		 */
  		public Movie createMovie(String title, String year, String url) 
  			{
  				Movie movie = new Movie (title, year, url);
  				movieIndex.put(movie.movieid, movie);
  				movienameIndex.put(movie.title , movie.movieid);
  				return movie;
  			}

  		/**
  		 * Method used to create a rating for a movie by a user
  		 */
  		public Rating createRating(Long userid, Long movieid, int rating1) 
			{
				Rating rating = new Rating (userid, movieid, rating1);
				ratingsIndex.put(rating.userid + "," + rating.movieid , rating);//puts the users rating into rating index. Assigns it by user and movie id
				Movie movie = getMovie(movieid);
				movie.ratings.add(rating);//Adds rting to specific movie, dependant on movie id
				return rating;// retuns rating
			}

  		/**
  		 * A method to get users by full name, allows into to CLI of a  full name, not a user id
  		 * @param fullName
  		 * @return
  		 */
  		public User getUserByFullName(String fullName) 
			{
				return getUser(usernameIndex.get(fullName));
			}

  		/**
  		 * Method to return a movie id
  		 * @param movieid
  		 * @return
  		 */
  		public Movie getMovie(Long movieid) 
			{
				return movieIndex.get(movieid);
			}
  		/**
  		 * Method used to show a list of top ten movies in the system
  		 * @return
  		 */
  		public List<Movie> getTopTen()
  		{
  			List<Movie> list = new ArrayList <Movie>(movieIndex.values());// Gets ArrayList of all movies in system
  			Collections.sort(list);//Sorts list, gives lowest values first
  			Collections.reverse (list);// reverses order of list, gives highest rated movies first
  			return list.subList(0, Math.min(list.size(), 10)); // returns a list of ten movies, or lower if <10 movies in DB
  		}
  		
  		/**
  		 * A method used to return a lift of 5 recommended movies to the user,
  		 * basically uses a system where if the user has not rated a movie before,
  		 * it recommends it on a watch next list.
  		 * List populated by highest rated movie first.
  		 * @param userid
  		 * @return
  		 */
  		public List<Movie> getTopFiveRecommendations(Long userid)
  		{
  			List<Movie> list = new ArrayList <Movie>(movieIndex.values());//Takes a list of all movies in system
  			Collections.sort(list);// sorts list, lowest rating first
  			Collections.reverse (list);//reverses list, highest rating now first
  			List<Movie> recommendation = new ArrayList <Movie>();//new arraylist for recommended movies
  			for(Movie movie : list)
  			{
  				boolean ratedByUser = false;//preset to false
  				for (Rating rating : movie.ratings)
  					{
  						if(rating.userid == userid);// checks users id against pre-exsisting ratings
  							{
  									ratedByUser = true;// if movie has been rated by user aready, break, no action, next movie in array
  									break;
  							}
  					}
  						if (!ratedByUser)// if not rated by user
  							{
  									recommendation.add(movie);// add to arraylist for recommended movies
  									if(recommendation.size()==5)// only allow 5 movies in this recommended movies arraylist
  									{
  										break;
  									}
  							}
  			}
  			return recommendation;
  		}

	}
