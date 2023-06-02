package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieGraph {
    private Map<String, Map<String, Integer>> graph;
    
    public MovieGraph() {
        graph = new HashMap<>();
    }
    
    public void loadMoviesFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] movieData = line.split(";");
                String movieName = movieData[0];
                String genre = movieData[3];
                String actor = movieData[1];
                String director = movieData[2];
                
                // Crear objeto Movie y agregarlo al grafo
                Movie movie = new Movie(movieName, genre, actor, director);
                addMovie(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addMovie(Movie movie) {
        String movieName = movie.getName();
        
        
        graph.put(movieName, new HashMap<>());
        
        for (String existingMovie : graph.keySet()) {
            if (!existingMovie.equals(movieName) && graph.get(existingMovie).containsKey(movie.getGenre())) {
                int weight = graph.get(existingMovie).get(movie.getGenre()) + 1;
                graph.get(existingMovie).put(movieName, weight);
                graph.get(movieName).put(existingMovie, weight);
            }
        }
    }
    
    public Map<String, Map<String, Integer>> getGraph() {
        return graph;
    }

    public void dijkstra(String start) {
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
}
