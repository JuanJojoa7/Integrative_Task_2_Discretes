package ui;
import model.*;
import java.util.Scanner;


public class Main {
    private Scanner reader;
    private Controller controller;

    public Main(){
        reader = new Scanner(System.in);
        controller = new Controller();
    }
    

    public static void main(String[] args) {

        Main main = new Main();

        main.txtReader();

        int option = -1;
        do{
            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);


    }

    public int getOptionShowMenu(){
        int option = 0;
        printMenu();
        option = validateIntegerOption();
        return option;
    }



    public void printMenu(){
        System.out.print(
            "\n<<<<< Bienvenido al sistema de recomendacion de peliculas >>>>>\n"+
            "1. Ingreso de info de las peliculas\n"+
            "2. Iniciar recomendacion de peliculas\n"+
            "3. \n"+       
            "0. Salir del programa. \n"+
            "Opcion: ");
    }

    public void executeOption(int option){

        switch(option){

            case 0-> System.out.println("\nGracias por hacer uso del programa, hasta luego.");

            default-> System.out.println("\nHas introducido una opcion invalida, intenta nuevamente.");
        }
    }

    public int validateIntegerOption(){
        int option = 0;
        if(reader.hasNextInt()){

            option = reader.nextInt();
        }
        else{
            reader.nextLine();
            option = -1;
        }
        return option;
    }

    public void txtReader(){
        System.out.println("Ingresa la direccion donde se encuentra la informacion de las peliculas");
        String dir = reader.nextLine();
        System.out.println("Cuantas peliculas van a ser: ");
        int numMovies = reader.nextInt();
    }


}