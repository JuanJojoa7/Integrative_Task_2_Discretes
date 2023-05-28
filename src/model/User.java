package model;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<Movie> favourites;
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public void addFavourite(Movie movie){
        favourites.add(movie);

    }

    public Movie searchMovie(Movie movie){
        String name = movie.getName();
        Movie result = null;
        for (Movie iterator : favourites) {
            if (iterator.getName().equals(name)) {
                result = iterator;
            }
        }

        return result;
    }

    public String deleateFavourite(Movie movie){
        String message = "";
        Movie toDeleate = searchMovie(movie);

        if(toDeleate == null){
            message = "La pelicula que desea eliminar no existe en su lista de favoritos.";
        }
        else{

            favourites.remove(toDeleate);
            message = "Pelicula eliminada con exito.";
        }

        return message;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
