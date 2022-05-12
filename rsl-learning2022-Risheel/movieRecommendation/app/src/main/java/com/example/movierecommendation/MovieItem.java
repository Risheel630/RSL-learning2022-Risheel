package com.example.movierecommendation;

public class MovieItem {

    private String movieName , movieCategory , movieThumbUrl;

    private int like ;

    public MovieItem(String movieName, String movieCategory, String movieThumbUrl, int like) {
        this.movieName = movieName;
        this.movieCategory = movieCategory;
        this.movieThumbUrl = movieThumbUrl;
        this.like = like;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getMovieThumbUrl() {
        return movieThumbUrl;
    }

    public void setMovieThumbUrl(String movieThumbUrl) {
        this.movieThumbUrl = movieThumbUrl;
    }

    public int isLiked() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

}
