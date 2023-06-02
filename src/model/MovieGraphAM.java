package model;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;


public class MovieGraphAM {
    private Map<String, Integer> movieIndices;
    private int[][] adjacencyMatrix;
    private List<Movie> movies;

    public MovieGraphAM() {
        movieIndices = new HashMap<>();
        adjacencyMatrix = new int[0][0];
        movies = new ArrayList<>();
    }

    public void loadMoviesFromCSV(String filename) {

        File file = new File(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] movieData = line.split(";");
                String movieName = movieData[0];
                String genre = movieData[3];
                String actor = movieData[1];
                String director = movieData[2];

                // Crear objeto Movie y agregarlo a la lista
                Movie movie = new Movie(movieName, genre, actor, director);
                movies.add(movie);

                // Actualizar la matriz de adyacencia y los índices de las películas
                int newIndex = movies.size() - 1;
                int[][] newMatrix = new int[newIndex + 1][newIndex + 1];
                for (int i = 0; i < newIndex; i++) {
                    for (int j = 0; j < newIndex; j++) {
                        newMatrix[i][j] = adjacencyMatrix[i][j];
                    }
                }
                adjacencyMatrix = newMatrix;
                movieIndices.put(movieName, newIndex);

                // Actualizar las relaciones de similitud en la matriz de adyacencia
                updateSimilarities(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSimilarities(Movie movie) {

        int movieIndex = movieIndices.get(movie.getName());

        for (Movie otherMovie : movies) {

            int valueDirector = 0;
            int valueActor = 0;
            int valueGenre = 0;

            if (!otherMovie.equals(movie)) {
                if(otherMovie.getActor().equals(movie.getActor())){
                    valueActor = 1;
                }
                if(otherMovie.getGenre().equals(movie.getGenre())){
                    valueGenre = 3;
                }
                if(otherMovie.getDirector().equals(movie.getDirector())){
                    valueDirector = 2;
                }
                int otherMovieIndex = movieIndices.get(otherMovie.getName());
                int similarity = calculateSimilarity(movie, otherMovie, valueDirector, valueActor, valueGenre);
                adjacencyMatrix[movieIndex][otherMovieIndex] = similarity;
                adjacencyMatrix[otherMovieIndex][movieIndex] = similarity;
            }
        }
    }

    private int calculateSimilarity(Movie movie1, Movie movie2, int valD, int valA, int valG) {
        int genreWeight = 3;
        int actorWeight = 1;
        int directorWeight = 2;
    
        int weight = valG * genreWeight + valA * actorWeight + valD * directorWeight;
    
        return weight;
    }


    public List<Movie> dijkstraUno(String movieToRefer) {
        int numMovies = movies.size();
        if(movieToRefer != null){
        }
    
        int startMovieIndex = movieIndices.get(movieToRefer);
        int[] distances = new int[numMovies];
        boolean[] visited = new boolean[numMovies];
        Movie[] parents = new Movie[numMovies];
    
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
    
        distances[startMovieIndex] = 0;
    
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < numMovies - 1; i++) {
    
            for (int j = 0; j < numMovies; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    minIndex = j;
                }
            }
    
            visited[minIndex] = true;
    
            for (int j = 0; j < numMovies; j++) {
                if (!visited[j] && adjacencyMatrix[minIndex][j] != 0 &&
                        distances[minIndex] != Integer.MAX_VALUE &&
                        distances[minIndex] + adjacencyMatrix[minIndex][j] < distances[j]) {
                    distances[j] = distances[minIndex] + adjacencyMatrix[minIndex][j];
                    parents[j] = movies.get(minIndex);
                }
            }
        }
    
        List<Movie> recommendedMovies = new ArrayList<>();
        for (int i = 0; i < numMovies; i++) {
            if (i != startMovieIndex) {
                recommendedMovies.add(movies.get(i));
            }
        }
    
        return recommendedMovies;
    }
    

    public List<Movie> dijkstra(String startMovie) {
        int startMovieIndex = movieIndices.get(startMovie);

        int numMovies = movies.size();
        int[] distances = new int[numMovies];
        boolean[] visited = new boolean[numMovies];
        Movie[] parents = new Movie[numMovies];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        distances[startMovieIndex] = 0;

        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        
        for (int i = 0; i < numMovies - 1; i++) {
   

            for (int j = 0; j < numMovies; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    minIndex = j;
                }
            }

            visited[minIndex] = true;

            for (int j = 0; j < numMovies; j++) {
                if (!visited[j] && adjacencyMatrix[minIndex][j] != 0 &&
                        distances[minIndex] != Integer.MAX_VALUE &&
                        distances[minIndex] + adjacencyMatrix[minIndex][j] < distances[j]) {
                    distances[j] = distances[minIndex] + adjacencyMatrix[minIndex][j];
                    parents[j] = movies.get(minIndex);
                }
            }
        }

        List<Movie> recommendedMovies = new ArrayList<>();
        for (int i = 0; i < numMovies; i++) {
            if (i != startMovieIndex) {
                recommendedMovies.add(movies.get(i));
            }
        }

        return recommendedMovies;
    }

    public List<Movie> floydWarshall() {
        int numMovies = movies.size();
        
        // Inicializar la matriz de distancias con valores infinitos
        int[][] distances = new int[numMovies][numMovies];
        for (int i = 0; i < numMovies; i++) {
            for (int j = 0; j < numMovies; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // Inicializar la matriz de rutas intermedias con valores -1
        int[][] next = new int[numMovies][numMovies];
        for (int i = 0; i < numMovies; i++) {
            for (int j = 0; j < numMovies; j++) {
                next[i][j] = -1;
            }
        }
        
        // Actualizar la matriz de distancias y rutas intermedias con las similitudes existentes
        for (int i = 0; i < numMovies; i++) {
            for (int j = 0; j < numMovies; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    distances[i][j] = adjacencyMatrix[i][j];
                    next[i][j] = j;
                }
            }
        }
        
        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < numMovies; k++) {
            for (int i = 0; i < numMovies; i++) {
                for (int j = 0; j < numMovies; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE &&
                        distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        
        // Generar recomendaciones de películas basadas en las distancias más cortas
        List<Movie> recommendedMovies = new ArrayList<>();
        for (int i = 0; i < numMovies; i++) {
            for (int j = 0; j < numMovies; j++) {
                if (i != j) {
                    int sourceIndex = i;
                    int destinationIndex = j;
                    List<Movie> path = reconstructPath(sourceIndex, destinationIndex, next);
                    recommendedMovies.addAll(path);
                }
            }
        }
        
        // Eliminar duplicados y películas repetidas
        Set<Movie> uniqueMovies = new LinkedHashSet<>(recommendedMovies);
        recommendedMovies.clear();
        recommendedMovies.addAll(uniqueMovies);
        
        // Devolver las películas recomendadas
        return recommendedMovies;
    }
    
    
    private List<Movie> reconstructPath(int source, int destination, int[][] next) {
        List<Movie> path = new ArrayList<>();
        int current = source;
        while (current != destination) {
            path.add(movies.get(current));
            current = next[current][destination];
            if (current == -1) {
                // No hay ruta disponible
                return new ArrayList<>();
            }
        }
        path.add(movies.get(destination));
        return path;
    }
    
}
    

                


