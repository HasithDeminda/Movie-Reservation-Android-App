package com.example.blueskycinema.Hasith;

public class Model {
    String movieId, movieName, duration, year, genre, description, cast, trailer, startDate, endDate, tickets;

    public Model(String movieId, String movieName, String duration, String year, String genre, String description, String cast, String trailer, String startDate, String endDate, String tickets) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.cast = cast;
        this.trailer = trailer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tickets = tickets;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }
}
