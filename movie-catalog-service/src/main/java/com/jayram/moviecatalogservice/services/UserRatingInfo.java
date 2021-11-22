package com.jayram.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayram.moviecatalogservice.models.Rating;
import com.jayram.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+ userId, UserRating.class);
	}
	
	private UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("", 0)));
		return userRating;
	}
}
