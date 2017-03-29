//Sara Bertrand et Marc-Antoine Dufresne
public class Cavalier extends Piece{

    public Cavalier(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){//Constructeur
        super(est_blanc, colonne, ligne, echiquier);
    }

    @Override
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){//Redéfinition de deplacement Valide selon ses mouvements autorisés

        int deltaX=Math.abs(nouvelle_colonne - this.getColonne());
        int deltaY=Math.abs(nouvelle_ligne - this.getLigne());

        boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
        boolean ValiditeMouvement=((deltaX == 2 && deltaY == 1)||(deltaX == 1 && deltaY == 2));

        return(ValiditeGenerale && ValiditeMouvement);//Si tout est vrai, le déplacement est valide
    }
    public String representationAscii(){return (estBlanc()?  "C":"c");}//Représentation Ascii
    public String representationUnicode(){return (estBlanc()?  "♘":"♞");}//Représentation Unicode
}