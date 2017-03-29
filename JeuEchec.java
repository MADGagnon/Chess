//Sara Bertrand et Marc-Antoine Dufresne
import java.util.Scanner;

public class JeuEchec {
    private static boolean tourBlanc = true;
    private static boolean checkingMove = false;
    private static boolean bypass=false;
    private static Piece dernierePieceBougee;

    public JeuEchec() {
    }

    public static void affiche(String val, Echiquier board) {//Permet d'afficher l'échiquier selon la demande de l'utilisateur
        if(val.equals("ascii")) {
            board.afficheAscii();
        } else {
            board.afficheUnicode();
        }

    }

    public static void aCapture(Piece p1, Piece p2, String position, String affichage){//Message d'affichage lorsqu'une pièce est capturée
        if(affichage.equals("ascii")) {
            System.out.println(p1.representationAscii()+" a capturé "+p2.representationAscii()+" en "+position);
        } else {
            System.out.println(p1.representationUnicode()+" a capturé "+p2.representationUnicode()+" en "+position);
        }
    }
    public static String Position(Piece p,int ligne, int colonne, String val){//Permet de créer un tableau des déplacements possibles
        if(p.deplacementValide(colonne,ligne)&& p.echiquier.examinePiece(colonne,ligne)==null)//Cas où il n'y a pas de pièce et deplacement valide.
            return "x";
        else if(p.deplacementValide(colonne,ligne)&& p.echiquier.examinePiece(colonne,ligne)!=null)//Cas où une pièce peut être capturé et déplacement valide.
            return"X";
        else if(p.echiquier.examinePiece(colonne,ligne)!=null && val.equals("ascii") ) {//Affichage des autres pièces en Ascii.
            return p.echiquier.examinePiece(colonne, ligne).representationAscii();
        }
        else if(p.echiquier.examinePiece(colonne,ligne)!=null && val.equals("unicode") ) {//Affichage des autres pièces en Unicode.
            return p.echiquier.examinePiece(colonne, ligne).representationUnicode();
        }else{
            return"•";
        }
    }

    public static  void deplacementsPossibles(Piece p, String val) {//Fonction qui retourne un tableau montrant tous les déplacements possibles d'une pièce
        String[][] tab = new String[8][8];
        int k = 8;
        String range;
        String tabAscii = "";
        String tabUni= "";
        String rangeUni;
        for (int i = 0; i < 8; i++) {
            range = " ";
            rangeUni=" ";
            for (int j = 0; j < 8; j++) {
                tab[i][j] = Position(p, i, j,val);
                range += tab[i][j]+" ";
                if(tab[i][j]!=null)
                    rangeUni+=tab[i][j] + "| ";
                else
                    rangeUni+=tab[i][j] + "  | ";

            }
            tabAscii += (k + "|" + range + "|" + k + "\n");
            tabUni+=(k + " |" + rangeUni + k + "\n"+"  ├──┼──┼──┼──┼──┼──┼──┼──┤"+"\n");
            k--;
        }
        if (val.equals("ascii")) {//Affichage Ascii
            System.out.println("    a b c d e f g h  ");
            System.out.println("    ---------------  ");
            System.out.println(tabAscii);
            System.out.println("    a b c d e f g h  ");
            System.out.println("    ---------------  ");
        } else {//Affichage Unicode
            System.out.println("    a  b  c  d  e  f  g  h  ");
            System.out.println("   ------------------------ ");
            System.out.println("  ┌──┬──┬──┬──┬──┬──┬──┬──┐");
            System.out.println(tabUni);
            System.out.println("   ------------------------ ");
            System.out.println("    a  b  c  d  e  f  g  h  ");

        }
    }

    public static void Jeu(Echiquier echiquier, String args0) {//Fonction qui sert de déroulement du jeu
        do {
            if(!checkingMove||bypass) {
                affiche(args0, echiquier);
                bypass=false;
            }
            while(true) {
                if(tourBlanc) {//Si c'est au tour du joueur blanc
                    System.out.println("\nJoueur Blanc?");
                } else {
                    System.out.println("\nJoueur Noir?");
                }
                Scanner sc = new Scanner(System.in);//Permet d'évaluer ce que l'utilisateur a entré
                String entree = sc.nextLine();
                if(entree.length() == 5) {//S'il entre 2 paramètres, c'est un déplacement. Conversion des paramètres entrés
                    if((Character.getNumericValue(entree.charAt(0))>=10)&&(Character.getNumericValue(entree.charAt(3))>=10)&&(Character.getNumericValue(entree.charAt(1))<9)&&(Character.getNumericValue(entree.charAt(4))<9)){
                        int ColsOne = entree.charAt(0) - 97;
                        int LigneOne = Math.abs(Character.getNumericValue(entree.charAt(1))-8);
                        int ColsTwo = entree.charAt(3) - 97;
                        int LigneTwo = Math.abs(Character.getNumericValue(entree.charAt(4))-8);

                        if(echiquier.examinePiece(ColsOne, LigneOne) != null) {//Vérification si c'est une case ayant un pièce
                            if(echiquier.examinePiece(ColsOne, LigneOne).estBlanc() == tourBlanc) {//Vérification si la pièce est la même couleur que le paramètre entré
                                if(echiquier.examinePiece(ColsOne, LigneOne).deplacementValide(ColsTwo, LigneTwo)) {
                                    if(echiquier.examinePiece(ColsTwo, LigneTwo)!=null){//Capture une pièce

                                        aCapture(echiquier.examinePiece(ColsOne, LigneOne),echiquier.examinePiece(ColsTwo, LigneTwo),entree.substring(3,5),args0);
                                    }
                                    echiquier.examinePiece(ColsOne, LigneOne).deplace(ColsTwo, LigneTwo);
                                    tourBlanc = !tourBlanc;//Au tour du prochain joueur
                                    checkingMove = false;
                                    echiquier.examinePiece(ColsTwo, LigneTwo).setPasPmvt();

                                    //Les lignes suivantes sont pour les règles de En Passant
                                    if(echiquier.examinePiece(ColsTwo, LigneTwo).getVEP()){
                                        if(tourBlanc){//si tour blanc
                                            echiquier.capturePiece(ColsTwo,LigneTwo-1);
                                        }else{//si tour noir
                                            echiquier.capturePiece(ColsTwo,LigneTwo+1);
                                        }
                                        echiquier.examinePiece(ColsTwo, LigneTwo).setVEP(false);
                                    }

                                    if(dernierePieceBougee!=null){
                                        dernierePieceBougee.setVDD(false);
                                    }
                                    dernierePieceBougee=echiquier.examinePiece(ColsTwo, LigneTwo) ;
                                    break;
                                }

                                System.out.print("Ce n'est pas un déplacement valide.");
                            } else {
                                System.out.print("Ce n'est pas un déplacement valide.(Couleur)");
                            }
                        }else{
                            System.out.print("Aucune piece sur cette case");
                        }
                    }else{
                        System.out.print("Ce n'est pas un déplacement valide.");
                    }

                } else if(entree.length() == 2) {//Un paramètre d'entrer. Affichage des déplacements possibles.
                    if((Character.getNumericValue(entree.charAt(0))>=10)&&(Character.getNumericValue(entree.charAt(1))<9)) {//Conversion du paramètre.
                        int ColsOne = entree.charAt(0) - 97;
                        int LigneOne = Math.abs(Character.getNumericValue(entree.charAt(1)) - 8);

                        if(echiquier.examinePiece(ColsOne, LigneOne) != null) {//Si la case contient un pièce, on affiche les déplacements possibles par rapport à elle.
                            deplacementsPossibles(echiquier.examinePiece(ColsOne, LigneOne), args0);
                            echiquier.examinePiece(ColsOne, LigneOne).setVDD(false);
                            echiquier.examinePiece(ColsOne, LigneOne).setVEP(false);
                            checkingMove = true;
                            break;
                        }else{
                            System.out.print("Aucune piece sur cette case");
                        }

                    }else{
                        System.out.print("Ce n'est pas une case valide.");
                    }
                } else {
                    if(entree.length() == 0) {
                        bypass = true;
                        break;
                    }
                    System.out.print("Veuillez entrer:\n     -un mouvement, avec la position initiale et finale d'une piece\n     -une piece, pour inspecter ses possibilites de mouvement");
                }
            }
        } while(echiquier.getPartie());//Si le roi se fait capturer la partie devient égal à faux et la boucle se termine. Fin du jeu
    }

    public static void main(String[] args) {
        Echiquier board = new Echiquier();
        String args0 = args[0];
        Jeu(board, args0);
    }
}