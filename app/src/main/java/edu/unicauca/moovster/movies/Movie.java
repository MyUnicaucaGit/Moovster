package edu.unicauca.moovster.movies;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Movie {
    private int id;
    private String name;
    private double rate;
    private String urlImage;
    private String overview;
    private Date realease_date;
    private int duration;

    public Movie() {
    }

    public Movie(int id, String name, double rate, String urlImage, String overview, String date) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.urlImage = "https://image.tmdb.org/t/p/w500"+urlImage;
        this.overview = overview;
        try {
            this.realease_date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getRealease_date() {
        return realease_date;
    }

    public void setRealease_date(String realease_date) {
        try {
        this.realease_date = new SimpleDateFormat("yyyy-MM-dd").parse(realease_date);
    } catch (ParseException e) {
        e.printStackTrace();
    }
        ;
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
