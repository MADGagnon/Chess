/**
 * Created by Admin on 2017-03-06.
 */

import java.util.Scanner;

public class JeuEchec {

    public static void affiche(String val, Echiquier board){
        if(val=="ascii"||val=="test"){
            board.afficheAscii();
        }else {
            board.afficheUnicode();
        }
    }
//    public static string blancOuNoir(nbrTours){
//
//    }
//
//    public static void tour(int n){
//
//
//
//        if(n==0){//blanc
//            Scanner reader = new Scanner(System.in);  // Reading from System.in
//            String n = reader.nextLine();
//            if(){
//            }else{
//            }
//        }else{//noir
//            Scanner reader = new Scanner(System.in);  // Reading from System.in
//            String n = reader.nextLine();
//        }
//        System.out.println("Joueur Blanc: ");
//
//
//    }

    public static void main(String[] args){
        /*************************************
         *
         *
         *
         * Ne pas oublier de remplacer "test" par args[0] pour la version finale!
         *
         *
         *
         */
        Echiquier board = new Echiquier();
        while(true){
            affiche("test",board); //pour version finale.

                //nbrTours++;

        }


    }
}
