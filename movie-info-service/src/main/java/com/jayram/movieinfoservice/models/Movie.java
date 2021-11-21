package com.jayram.movieinfoservice.models;

public class Movie {

	private String movieId;
	private String name;
	private String desciption;
	
	public Movie(String movieId, String name, String desciption) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.desciption = desciption;
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
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
}
