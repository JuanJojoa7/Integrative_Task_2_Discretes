package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieRecommendation {
    public static void dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        // Implementación del método Dijkstra
        // ...
    }
    
    public static void main(String[] args) {
        MovieGraph movieGraph = new MovieGraph();

        
        String startMovie = "A";
        dijkstra(movieGraph.getGraph(), startMovie);
    }
    
    public static void loadMoviesFromCSV(String filename, Map<String, Map<String, Integer>> graph) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] movieData = line.split(",");
                String movie = movieData[0];
                String genre = movieData[1];
                String actor = movieData[2];
                String director = movieData[3];
                
                // Agregar la película al grafo y establecer las relaciones de género
                graph.put(movie, new HashMap<>());
                for (String existingMovie : graph.keySet()) {
                    if (!existingMovie.equals(movie) && graph.get(existingMovie).containsKey(genre)) {
                        int weight = graph.get(existingMovie).get(genre) + 1;
                        graph.get(existingMovie).put(movie, weight);
                        graph.get(movie).put(existingMovie, weight);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
