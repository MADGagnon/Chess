//Sara Bertrand et Marc-Antoine Dufresne

public class Fou extends Piece{

    public Fou(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){//Constructeur
        super(est_blanc, colonne, ligne, echiquier);
    }

    @Override
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {//Redéfinition de deplacementValide selon les mouvements autorisés du fou

        int deltaX = Math.abs(nouvelle_ligne - this.getLigne());
        int deltaY = Math.abs(nouvelle_colonne - this.getColonne());

        boolean ValiditeGenerale = super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
        boolean ValiditeMouvement = (deltaX == deltaY);//a bouge dans la bonne diagonale
        boolean ValiditeAucunePiece = true;

        //Vérification des diagonales. S'il ya une pièce qui bloque, ValiditeAucunePiece vaut false.
        if (this.getColonne() < nouvelle_colonne && this.getLigne() < nouvelle_ligne) {//déplacement diagonale droite bas
            for (int i = 1; i < nouvelle_colonne - this.getColonne(); i++) {
                if(this.getLigne()+i >=0 && this.getLigne()+i<8)
                    if (echiquier.examinePiece(this.getColonne() + i, this.getLigne() + i) != null)
                        ValiditeAucunePiece = false;
            }
        } else if (this.getColonne() < nouvelle_colonne && this.getLigne() > nouvelle_ligne) {//déplacement diagonale droite haut
            for (int i = 1; i < nouvelle_colonne - this.getColonne(); i++) {
                if(this.getLigne()-i >=0 && this.getLigne()-i<8)
                    if (echiquier.examinePiece(this.getColonne() + i, this.getLigne() - i) != null)
                        ValiditeAucunePiece = false;
            }
        } else if (this.getColonne() > nouvelle_colonne && this.getLigne() > nouvelle_ligne) {//Déplacement diagonale gauche haut
            for (int i = 1; i < this.getColonne() - nouvelle_colonne; i++) {
                if(this.getLigne()-i >=0 && this.getLigne()-i<8)
                    if (echiquier.examinePiece(this.getColonne() - i, this.getLigne() - i) != null) {
                        ValiditeAucunePiece = false;
                    }
            }
        } else if (this.getColonne() > nouvelle_colonne && this.getLigne() < nouvelle_ligne) {//Déplacement diagonale gauche bas
            for (int i = 1; i < this.getColonne() - nouvelle_colonne; i++) {
                if(this.getLigne()+i >=0 && this.getLigne()+i<8){
                    if (echiquier.examinePiece(this.getColonne() - i, this.getLigne() + i) != null) {
                        ValiditeAucunePiece = false;
                    }
                }
            }
        } else {
            ValiditeAucunePiece = true;
        }
        return (ValiditeGenerale && ValiditeMouvement && ValiditeAucunePiece);//Si tout est vrai, le déplacement est valide
    }

    public String representationAscii(){return (estBlanc()? "F": "f");}//Représentation Ascii
    public String representationUnicode(){return (estBlanc()? "♗" : "♝");}//Représentation Unicode
}