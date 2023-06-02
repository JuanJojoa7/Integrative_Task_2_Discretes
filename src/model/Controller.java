package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class Controller {

    public MovieGraphAM movieGraph;

    public Controller(){
        movieGraph = new MovieGraphAM();
        movieGraph.loadMoviesFromCSV("C:/Users/crist/Desktop/Integrative_Task_2_Discretes/src/lib/Peliculas.csv");

    }

    public String printRecomandation(int movie){
        String result = "";

        List<Movie> preResult = generateRecomandation(movie);

        for (Movie movi : preResult) {
            result += movi.getName() + "\n";
        }

        return result;
    }


    public List<Movie> generateRecomandation(int movie){

        List<Movie> recommandation = new ArrayList<>();

        String nameToSearch = searchMovieByInt(movie);
        
        recommandation = movieGraph.dijkstra(nameToSearch);

        return recommandation;
    }

    public String searchMovieByInt(int movie){
        String name = "";

        if (movie == 1) {
            name = "The Dark Knight";
        } else if (movie == 2) {
            name = "Mad Max: Fury Road";
        } else if (movie == 3) {
            name = "John Wick";
        } else if (movie == 4) {
            name = "Avengers: Endgame";
        } else if (movie == 5) {
            name = "Die Hard";
        } else if (movie == 6) {
            name = "Mission: Impossible - Fallout";
        } else if (movie == 7) {
            name = "Gladiator";
        } else if (movie == 8) {
            name = "Inception";
        } else if (movie == 9) {
            name = "The Matrix";
        } else if (movie == 10) {
            name = "The Raid";
        } else if (movie == 11) {
            name = "Indiana Jones and the Raiders of the Lost Ark";
        } else if (movie == 12) {
            name = "The Lord of the Rings: The Fellowship of the Ring";
        } else if (movie == 13) {
            name = "Pirates of the Caribbean: The Curse of the Black Pearl";
        } else if (movie == 14) {
            name = "Jurassic Park";
        } else if (movie == 15) {
            name = "The Jungle Book";
        } else if (movie == 16) {
            name = "The Goonies";
        } else if (movie == 17) {
            name = "The Princess Bride";
        } else if (movie == 18) {
            name = "Raiders of the Lost Ark";
        } else if (movie == 19) {
            name = "Guardians of the Galaxy";
        } else if (movie == 20) {
            name = "The Hobbit: An Unexpected Journey";
        } else if (movie == 21) {
            name = "Blade Runner 2049";
        } else if (movie == 22) {
            name = "The Matrix";
        } else if (movie == 23) {
            name = "Interstellar";
        } else if (movie == 24) {
            name = "The Fifth Element";
        } else if (movie == 25) {
            name = "Ex Machina";
        } else if (movie == 26) {
            name = "Inception";
        } else if (movie == 27) {
            name = "The Truman Show";
        } else if (movie == 28) {
            name = "E.T. the Extra-Terrestrial";
        } else if (movie == 29) {
            name = "The Terminator";
        } else if (movie == 30) {
            name = "The Martian";
        } else if (movie == 31) {
            name = "The Hangover";
        } else if (movie == 32) {
            name = "Superbad";
        } else if (movie == 33) {
            name = "Bridesmaids";
        } else if (movie == 34) {
            name = "Airplane!";
        } else if (movie == 35) {
            name = "Anchorman: The Legend of Ron Burgundy";
        } else if (movie == 36) {
            name = "Dumb and Dumber";
        } else if (movie == 37) {
            name = "Mean Girls";
        } else if (movie == 38) {
            name = "Ferris Bueller's Day Off";
        }else if (movie == 39) {
            name = "The Big Lebowski";
        } else if (movie == 40) {
            name = "Tropic Thunder";
        } else if (movie == 41) {
            name = "The Shawshank Redemption";
        } else if (movie == 42) {
            name = "The Godfather";
        } else if (movie == 43) {
            name = "Schindler's List";
        } else if (movie == 44) {
            name = "Forrest Gump";
        } else if (movie == 45) {
            name = "Fight Club";
        } else if (movie == 46) {
            name = "The Pianist";
        } else if (movie == 47) {
            name = "American Beauty";
        } else if (movie == 48) {
            name = "Good Will Hunting";
        } else if (movie == 49) {
            name = "The Pursuit of Happyness";
        } else if (movie == 50) {
            name = "La La Land";
        }

        return name;
    }
}

