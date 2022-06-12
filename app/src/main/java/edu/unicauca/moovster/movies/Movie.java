package edu.unicauca.moovster.movies;

public class Movie {
    private int id;
    private String name;
    private double rate;
    private String urlImage;
    private String overview;

    public Movie() {
    }

    public Movie(int id, String name, double rate, String urlImage, String overview) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.urlImage = "https://image.tmdb.org/t/p/w500"+urlImage;
        this.overview = overview;

    }

    public void setUrlImage(String urlImage) {
        this.urlImage = "https://image.tmdb.org/t/p/w500"+urlImage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUrlImage() {
        return urlImage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


}
