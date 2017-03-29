//Sara Bertrand et Marc-Antoine Dufresne

public class Tour extends Piece{
    public Tour(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){//Constructeur
        super(est_blanc, colonne, ligne, echiquier);
    }

    @Override
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){//Redéfinition de deplacementValide selon les mouvements autorisés de la tour

        int deltaX=Math.abs(nouvelle_ligne - this.getLigne());
        int deltaY=Math.abs(nouvelle_colonne - this.getColonne());

        boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
        boolean ValiditeMouvement=((deltaX == 0 && deltaY != 0)||(deltaX != 0 && deltaY == 0));//ligne verticale ou horizontale
        boolean ValiditeAucunePiece=true;

        //Vérification des mouvements verticales et horizontales. S'il ya une pièce qui bloque, ValiditeAucunePiece vaut false.
        if( deltaX != 0 && deltaY ==0) { // Verticale
            if (nouvelle_ligne < this.getLigne())//haut
                for (int i = 1; i < this.getLigne() - nouvelle_ligne; i++) {
                    if (echiquier.examinePiece(nouvelle_colonne, this.getLigne()  - i) != null)
                        ValiditeAucunePiece = false;
                }
            else if (nouvelle_ligne > this.getLigne())//bas
                for (int i = 1; i < nouvelle_ligne - this.getLigne(); i++) {
                    if (echiquier.examinePiece(nouvelle_colonne, this.getLigne() + i) != null)
                        ValiditeAucunePiece = false;
                }
        }else if( deltaX == 0 && deltaY !=0) {//Horizontale
            if (this.getColonne() > nouvelle_colonne)//gauche
                for (int i = 1; i < this.getColonne() - nouvelle_colonne; i++) {
                    if (echiquier.examinePiece(this.getColonne() - i, nouvelle_ligne) != null)
                        ValiditeAucunePiece = false;
                }
            else if (this.getColonne() < nouvelle_colonne)//droite
                for (int i = 1; i < nouvelle_colonne - this.getColonne(); i++) {
                    if (echiquier.examinePiece(this.getColonne() + i, nouvelle_ligne) != null)
                        ValiditeAucunePiece = false;
                }
        }
        return(ValiditeGenerale && ValiditeMouvement && ValiditeAucunePiece);//Si tout est vrai, le déplacement est valide
    }
    public String representationAscii(){
        return (estBlanc()? "T": "t");
    }//Représentation Ascii
    public String representationUnicode(){return (estBlanc()? "♖": "♜");}//Représentation Unicode
}