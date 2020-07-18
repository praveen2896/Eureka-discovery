package io.lucifier.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.lucifier.moviecatalogservice.Exception.MovieException;
import io.lucifier.moviecatalogservice.model.CatalogItem;
import io.lucifier.moviecatalogservice.model.Movie;
import io.lucifier.moviecatalogservice.model.Rating;
import io.lucifier.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate; //used for calling any Rest calls
	
	@RequestMapping("/{userId}")
	public ResponseEntity<Object> getCatalog(@PathVariable("userId") String userId){
		
		if(userId.equals("1"))
			 throw new MovieException();
		
		//get all rated movieIds from rating-data-service
		//for each movieId's ,call movie info in movie-info-service
		//use instance name instead of localhost url,the library goes to the eureka and fetch the service location and pass the message
		UserRating userRating=restTemplate.getForObject("http://rating-service/rating/users/"+userId,UserRating.class);
		//put all them together
		//using for loop
		List<CatalogItem> ci= new  ArrayList<>();
		for(int i=0;i<userRating.getUseRating().size();i++)
		{
			Rating ratings=new Rating();
			ratings= userRating.getUseRating().get(i);
			Movie movie=restTemplate.getForObject("http://info-service/movies/"+ratings.getMovieId(),Movie.class);//calling the API's and unmarsahlling the objects
			CatalogItem ct= new CatalogItem(movie.getName(),"test", ratings.getRating());
			ci.add(ct);
		}
		return new ResponseEntity<Object>(ci, HttpStatus.OK);
		/*return ratings.stream().map(rating->{
			Movie movie=restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
			return new CatalogItem(movie.getName(), "test",rating.getRating());
			})
			.collect(Collectors.toList());	*/
	}
	
	@RequestMapping(value="/getWeather")
	public String getWeatherData()
	{
		String response=restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=450ee50d9135a960e2dd90bda120d4dd",String.class);
		return response;
	}
}
