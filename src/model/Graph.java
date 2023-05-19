package model;
import java.util.HashMap;
import java.util.*;

public class Graph<v> {

    private HashMap<v, Vertex<v>> vertices;
    //Crea el grafo
    public Graph(){
        vertices = new HashMap<>();
    }
    
    //Añade el vertice
    public void addVertex(Vertex<v> vertex){
        vertices.put(vertex.getData(),vertex);
    }

    public void eliminateVertex(Vertex<v> vertice) {
        // Verificar si el vértice existe en el grafo
        if (!vertices.containsKey(vertice.getData())) {
            System.out.println("El vértice no existe en el grafo.");
            return;
        }
        // Eliminar las conexiones del vértice
        Vertex<v> v = vertices.get(vertice.getData());
        for (Vertex<v> adyacente : v.getAdy()) {
            adyacente.removeAdy(v);
        }
        // Eliminar el vértice del grafo
        vertices.remove(vertice.getData());
    }

    //Metodo que agrega una arista
    public void addEdge(Vertex<v> origin, Vertex<v> destiny){
        origin.getAdy().add(destiny);
    }

    //Metodo que elimina las aristas
    public void eliminateEdge(Vertex<v> origin, Vertex<v> destiny){
        if(origin.getAdy().contains(destiny)){
            origin.getAdy().remove(destiny);
            destiny.getAdy().remove(origin);
            return;
        }else{
            System.out.println("Its null");
            return;
        }
    }

    //Metodo DFS
    public String dfs(){
        String msj = "";
        HashSet<Vertex<v>> visited = new HashSet<>();
        for(Vertex<v> v : vertices.values()){
            if(!visited.contains(v)){
                msj = dfs(v, visited, msj);
            }
        }
        return msj;
    }

    private String dfs(Vertex<v> v, HashSet<Vertex<v>> visited, String msj) {
        Stack<Vertex<v>> stack = new Stack<>();
        stack.push(v);
        visited.add(v);
        
        while (!stack.isEmpty()) {
            Vertex<v> current = stack.pop();
            msj += current.getData() + " ";
            
            for (Vertex<v> adj : current.getAdy()) {
                if (!visited.contains(adj)) {
                    stack.push(adj);
                    visited.add(adj);
                }
            }
        }
        
        return msj;
    }

    //Metodo BFS
    public String bfs(Vertex<v> v) {
        String msj = "";
        Queue<Vertex<v>> queue = new LinkedList<>();
        Set<Vertex<v>> visited = new HashSet<>();
    
        v.setParent(null);
        queue.offer(v);
        visited.add(v);
    
        while (!queue.isEmpty()) {
            Vertex<v> current = queue.poll();
            msj += current.getData() + " ";
    
            for (Vertex<v> adj : current.getAdy()) {
                if (!visited.contains(adj)) {
                    adj.setParent(current);
                    queue.offer(adj);
                    visited.add(adj);
                }
            }
        }
        return msj;
    }
}