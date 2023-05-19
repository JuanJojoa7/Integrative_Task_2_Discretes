package model;
import java.util.*;



public class Vertex<v>{

    private ArrayList<Vertex <v>> ady;
    private v data;
    private Vertex<v> parent;

    //Constructor
    public Vertex(v data){
        ady = new ArrayList<>();
        this.data = data;
    }
    //Getters and Setters :b
    public ArrayList<Vertex<v>> getAdy() {
        return ady;
    }

    public void setAdy(ArrayList<Vertex<v>> ady) {
        this.ady = ady;
    }

    public v getData() {
        return data;
    }

    public void setData(v data) {
        this.data = data;
    }

    public Vertex<v> getParent() {
        return parent;
    }

    public void setParent(Vertex<v> parent) {
        this.parent = parent;
    }

    public void removeAdy(Vertex<v> vertice){
        ady.remove(vertice);
    }
   
}

