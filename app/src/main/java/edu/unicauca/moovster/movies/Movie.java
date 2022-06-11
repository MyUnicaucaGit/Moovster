package edu.unicauca.moovster.movies;

public class Movie {
    private int id;
    private String name;
    private double rate;
    private String urlImage;

    public String getUrlImage() {
        return urlImage;
    }

    public Movie(int id, String name, double rate, String urlImage) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.urlImage = "https://image.tmdb.org/t/p/w500"+urlImage;

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
