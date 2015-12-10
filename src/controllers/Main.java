package controllers;


import java.io.File;
import java.util.Collection;
import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.Rating;
import models.User;


/**
 * @Author Steven Delaney
 * Main class is used to run the UserInterface Shell
 * All Commands shown to user are contained here.
 * 
 */

public class Main
	{
		public LikeMoviesAPI movieApi;

  
		/**
		 * Constructor for UserInterface Shell
		 * CommandShell is a user interface for
		 * the movie recommend application 
		 */
				public Main() throws Exception
				{
						File datastore = new File("datastore.xml");
						Serializer serializer = new XMLSerializer(datastore);
						movieApi = new LikeMoviesAPI(serializer);
						if (datastore.isFile())
							{
								movieApi.load();
							}
				}
  
				/**
				 * Main method for the LikeMoviesApi
				 * Starts the shell interface
				 * 
				 * @param args
				 * @throws Exception
				 */

				public static void main(String[] args) throws Exception
					{
						Main main = new Main();
						Shell shell = ShellFactory.createConsoleShell("LikeMovies", "Welcome to LikeMovies-console - ?help for instructions", main);
						shell.commandLoop();
						main.movieApi.store();
					}


				/**
				 * Adds a new user to the LikeMoviesApi
				 * 
				 * @param firstName The user's first name
				 * @param lastName The user's last name
				 * @param age The user's age
				 * @param gender The user's gender
				 * @param occupation The user's occupation
				 */
				@Command(description="Add a new User to the System")
				public void createUser (	@Param(name="first name") 	String firstName, 
		  					@Param(name="last name") 	String lastName, 
		  					@Param(name="gender")      	String gender, 
		  					@Param(name="Age")      	String age,
		  					@Param(name="occupation")  	String occupation)
					{
						movieApi.createUser(firstName, lastName, age, gender, occupation);
					}
  
				/**
				 * Returns All users In System
				 */
				@Command(description="Show all users Stored in System")
				public void getUsers ()
					{
						Collection<User> users = movieApi.getUsers();// takes the stored collection of users
						System.out.println(users);//prints users to screen
					}
  
				/**
				 * Deletes a user from the LikeMoviesApi
				 * 
				 * @param userid The user's ID
				 */
				@Command(description="Delete a User from the System")
				public void deleteUser (@Param(name="id") Long userid)
					{
						movieApi.deleteUser(userid);
					}
  
				/**
				 * Any information input by user is stored to XML File
				 */
				@Command(description="Store All Current Data to XML File")
				public void write() throws Exception
					{
						movieApi.store();
					}
  
				/**
				 * Information previously entered into XML file is loaded to LikeMovies Api
				 */
				@Command(description="Loads All Current Data Stored in XML File")
				public void load() throws Exception
					{
						movieApi.load();
					}
  
				/**
				 * Adds a movie to the LikeMoviesApi
				 * @param title The title of the movie
				 * @param year The year the movie was released
				 * @param Url The URL of the movie
				 */
				@Command(description="Add a new Movie to the System")
				public void createMovie (	@Param(name="title") 	String title, 
		  									@Param(name="year") 	String year, 
		  									@Param(name="url")      String url)
					{
						movieApi.createMovie(title, year, url);
					}
  
				/**
				 * Returns all Movies stored in system.
				 */
				@Command(description="Get all movie details in System")
				public void getMovies ()
					{
						Collection<Movie> movies = movieApi.getMovies();//Movies stroed in pre-populated collection
						System.out.println(movies);//print list to screen
					}
  
				/**
				 * Removes a Movie from LikeMoviesApi System
				 * @param movieid
				 */
				@Command(description="Delete a Movie from System")
				public void deleteMovie (@Param(name="id") Long movieid)
					{
						movieApi.deleteMovie(movieid);
					}
  
				/**
				 * Returns a rating for a specific Movie
				 * @param movieid
				 */
				@Command(description="Rating for Specific Movie")
				public void getRating (@Param(name=" Movieid") Long movieid)
					{
						Collection<Rating> ratings = movieApi.getRating();
						System.out.println(ratings);
					}

				/**
				 * Adds a rating, from a user, of a movie to the LikeMoviesApi
				 * 
				 * @param userId The ID of the user 
				 * @param movieId The ID of the movie 
				 * @param rating The rating of the movie
				 */
				@Command(description="Add a rating to a Movie")
				public void createRating (	@Param(name="fullName")  	String   fullName,
		  									@Param(name="movieid") 		Long movieid, 
		  									@Param(name="rating") 		int rating)
					{
						User user = movieApi.getUserByFullName(fullName);
						movieApi.createRating(user.userid, movieid, rating);	
					}
				
				@Command(description="Return top 10 movies in system")
				public void getTopTen ()
					{
						movieApi.getTopTen();
						System.out.print(movieApi.getTopTen());
					}
				
				@Command(description="Return top 5 recommended movies for a specific user")
				public void getTopFiveRecommendations (@Param(name="User id") Long userid)
				{
					movieApi.getTopFiveRecommendations(userid);
					System.out.print(movieApi.getTopFiveRecommendations(userid));
				}
	}