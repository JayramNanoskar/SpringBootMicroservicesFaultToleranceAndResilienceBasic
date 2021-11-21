package com.jayram.moviecatalogservice.models;

public class Movie {

	private String movieId;
	private String name;
	
	/*
	 * Default constructor requires in java to marshall & unmarshall
	 * Because java first creates an instance, then parses the string and then populate it one by one 
	 */
	public Movie() {
	}
	
	public Movie(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
