package model;

public class Movie {

    private String movieName;
    private String genre;
    private String actor;
    private String director;
    
    public Movie(String movieName, String genre, String actor, String director) {
        this.movieName = movieName;
        this.genre = genre;
        this.actor = actor;
        this.director = director;
    }


    
    
    // Getters y setters
    
    public String getName() {
        return movieName;
    }

    public void setName(String movieName) {
        this.movieName = movieName;

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
