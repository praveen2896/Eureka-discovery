package io.lucifier.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.lucifier.ratingsdataservice.model.Rating;
import io.lucifier.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings=Arrays.asList(new Rating("Transformers",4),new Rating("Titanic",5));
		UserRating userRating= new UserRating();
		userRating.setUseRating(ratings);
		return userRating;
	}
}
