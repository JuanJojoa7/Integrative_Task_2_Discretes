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
            "2. Recibir recomendacion de peliculas\n"+
            "3. \n"+       
            "0. Salir del programa. \n"+
            "Opcion: ");
    }

    public void executeOption(int option){

        switch(option){

            case 1:

            break;

            case 2:

                System.out.println("Please select your favourite movie: \n"+                               
                    "1. The Dark Knight\n"+
                    "2. Mad Max: Fury Road\n"+
                    "3. John Wick\n"+
                    "4. Avengers: Endgame\n"+
                    "5. Die Hard\n"+
                    "6. Mission: Impossible - Fallout\n"+
                    "7. Gladiator\n"+
                    "8. Inception\n"+
                    "9. The Matrix\n"+
                    "10. The Raid\n"+
                    "11. Indiana Jones and the Raiders of the Lost Ark\n"+
                    "12. The Lord of the Rings: The Fellowship of the Ring\n"+
                    "13. Pirates of the Caribbean: The Curse of the Black Pearl\n"+
                    "14. Jurassic Park\n"+
                    "15. The Jungle Book\n"+
                    "16. The Goonies\n"+
                    "17. The Princess Bride\n"+
                    "18. Raiders of the Lost Ark\n"+
                    "19. Guardians of the Galaxy\n"+
                    "20. The Hobbit: An Unexpected Journey\n"+
                    "21. Blade Runner 2049\n"+
                    "22. The Matrix\n"+
                    "23. Interstellar\n"+
                    "24. The Fifth Element\n"+
                    "25. Ex Machina\n"+
                    "26. Inception\n"+
                    "27. The Truman Show\n"+
                    "28. E.T. the Extra-Terrestrial\n"+
                    "29. The Terminator\n"+
                    "30. The Martian\n"+
                    "31. The Hangover\n"+
                    "32. Superbad\n"+
                    "33. Bridesmaids\n"+
                    "34. Airplane!\n"+
                    "35. Anchorman: The Legend of Ron Burgundy\n"+
                    "36. Dumb and Dumber\n"+
                    "37. Mean Girls\n"+
                    "38. Ferris Bueller's Day Off\n"+
                    "39. The Big Lebowski\n"+
                    "40. Tropic Thunder\n"+
                    "41. The Shawshank Redemption\n"+
                    "42. The Godfather\n"+
                    "43. Schindler's List\n"+
                    "44. Forrest Gump\n"+
                    "45. Fight Club\n"+
                    "46. The Pianist\n"+
                    "47. American Beauty\n"+
                    "48. Good Will Hunting\n"+
                    "49. The Pursuit of Happyness\n"+
                    "50. La La Land\n");


                int movie = reader.nextInt();

                String recomandation = controller.generateRecomendation(movie);

                System.out.println(recomandation);

            break;

            case 0:
            
                System.out.println("\nGracias por hacer uso del programa, hasta luego.");

            break;

            default:
            
                System.out.println("\nHas introducido una opcion invalida, intenta nuevamente.");
            
            break;
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