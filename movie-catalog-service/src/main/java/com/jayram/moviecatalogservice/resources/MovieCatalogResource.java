package com.jayram.moviecatalogservice.resources;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.jayram.moviecatalogservice.models.CatalogItem;
import com.jayram.moviecatalogservice.models.Movie;
import com.jayram.moviecatalogservice.models.Rating;
import com.jayram.moviecatalogservice.models.UserRating;
import com.jayram.moviecatalogservice.services.MovieInfo;
import com.jayram.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating ratings = userRatingInfo.getUserRating(userId);

		return ratings.getUserRatings().stream()
				.map(rating -> { 
					return movieInfo.getCatalogItem(rating);
				})
				.collect(Collectors.toList());
		
	}
	
}
