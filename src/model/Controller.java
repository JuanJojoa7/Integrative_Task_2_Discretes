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

