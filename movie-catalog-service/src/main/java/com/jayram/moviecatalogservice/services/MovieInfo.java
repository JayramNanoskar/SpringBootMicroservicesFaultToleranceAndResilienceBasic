package com.jayram.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayram.moviecatalogservice.models.CatalogItem;
import com.jayram.moviecatalogservice.models.Movie;
import com.jayram.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class); //Unmarshalling of response into Movie class instance
		return new CatalogItem(movie.getName(), movie.getDesciption(), rating.getRating());
	}

	private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}
}
