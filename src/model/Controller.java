package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Controller {

    private UsersMap users = new UsersMap();


    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = loadMoviesFromCSV("movies.csv");

        String startMovie = "A"; // Película inicial
        dijkstra(graph, startMovie);
    }

    public static Map<String, Map<String, Integer>> loadMoviesFromCSV(String filename) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String movie = parts[0];
                String genre = parts[1];
                String actor = parts[2];
                String director = parts[3];

                // Agregar la película al grafo
                graph.put(movie, new HashMap<>());

                // Asignar un peso (similitud) basado en la relevancia de género, actor y director
                int weight = calculateWeight(genre, actor, director);

                // Establecer la relación de género con el peso correspondiente
                graph.get(movie).put(genre, weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    public static int calculateWeight(String genre, String actor, String director) {
        
        int genreWeight = 1;    // Peso del género (puede ser ajustado según la relevancia)
        int actorWeight = 2;    // Peso del actor principal (puede ser ajustado según la relevancia)
        int directorWeight = 3; // Peso del director principal (puede ser ajustado según la relevancia)

        int weight = genreWeight + actorWeight + directorWeight;

        return weight;
    }

    public String generateRecomendation(int movie){
        String message = "";

        Movie movieF = searchMovie(movie);

        if(movieF == null){
            message = "Sorry, the movie is not available anymore, please go back to the menu and select a new one.";
        }
        else{
            message = generateRecomandationByDijkstra(movieF);
        }

        return message;
    }

    public Movie searchMovie(int movie){
        Movie result = null;

        String nameToSearch = searchMovieByInt(movie);

        Map<String, Map<String, Integer>> graph = loadMoviesFromCSV("movies.csv");

        graph.forEach((genre, movieMap) -> {
            movieMap.forEach((name, rating) -> {
                if (name.equals(nameToSearch)) {
                   result = movieMap;
                }
            });
        });


        return result;
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



    public String generateRecomandationByDijkstra(Movie movie){
        String result = "";


        return result;
    }

    public static void dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        for (String movie : graph.keySet()) {
            distances.put(movie, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        queue.offer(new AbstractMap.SimpleEntry<>(start, 0));

        while (!queue.isEmpty()) {
            Map.Entry<String, Integer> entry = queue.poll();
            String currentMovie = entry.getKey();
            int currentDistance = entry.getValue();

            if (currentDistance > distances.get(currentMovie)) {
                continue;
            }

            Map<String, Integer> neighbors = graph.get(currentMovie);
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String neighborMovie = neighbor.getKey();
                int weight = neighbor.getValue();
                int distance = currentDistance + weight;

                if (distance < distances.get(neighborMovie)) {
                    distances.put(neighborMovie, distance);
                    queue.offer(new AbstractMap.SimpleEntry<>(neighborMovie, distance));
                }
            }
        }

        System.out.println("Recommended movies:");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            String movie = entry.getKey();
            int distance = entry.getValue();

            if (!movie.equals(start)) {
                System.out.println(movie);
            }
        }
    }

    public String addNewUser (String name, String email, String password){
        String message = "";
        
        User newUser = new User(name, email, password);
        message = users.addUser(newUser);

        return message;
    }

    public String logIn(String email, String password) {
        User user = users.getUser(email);
        String message = "";
        
        if (user != null && user.getPassword().equals(password)) {
            message = "LogIn exitoso!";
        } 
        else if(user == null) {
            message = "El email ingresado no corresponde a ningun usuario.";
        }
        else{
            message = "Contraseña incorrecta.";
        }

        return message;
    }

}

