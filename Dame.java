//Sara Bertrand et Marc-Antoine Dufresne
public class Dame extends Piece{

    public Dame(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){//Constructeur
        super(est_blanc, colonne, ligne, echiquier);
    }

    @Override
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {//Redefinition de DeplacementValide selon les mouvements autorisés de la Dame

        int deltaX = Math.abs(nouvelle_ligne - this.getLigne());
        int deltaY = Math.abs(nouvelle_colonne - this.getColonne());

        boolean ValiditeGenerale = super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
        boolean ValiditeMouvement = ((deltaX == deltaY) || deltaX == 0 && deltaY != 0 || deltaX != 0 && deltaY == 0);
        boolean ValiditeAucunePiece = true;

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
            else if (this.getColonne() < nouvelle_colonne)//droit
                for (int i = 1; i < nouvelle_colonne - this.getColonne(); i++) {
                    if (echiquier.examinePiece(this.getColonne() + i, nouvelle_ligne) != null)
                        ValiditeAucunePiece = false;
                }
        }
        else if (this.getColonne() < nouvelle_colonne && this.getLigne() < nouvelle_ligne) {//déplacement diagonale droite bas
            for (int i = 1; i < nouvelle_colonne - this.getColonne(); i++) {
                if(this.getLigne()+i >=0 && this.getLigne()+i<8) {
                    if (echiquier.examinePiece(this.getColonne() + i, this.getLigne() + i) != null)
                        ValiditeAucunePiece = false;
                }
            }
        } else if (this.getColonne() < nouvelle_colonne && this.getLigne() > nouvelle_ligne) {//déplacement diagonale droite haut
            for (int i = 1; i < nouvelle_colonne - this.getColonne(); i++) {
                if(this.getLigne()-i >=0 && this.getLigne()-i<8) {
                    if (echiquier.examinePiece(this.getColonne() + i, this.getLigne() - i) != null)
                        ValiditeAucunePiece = false;
                }
            }
        } else if (this.getColonne() > nouvelle_colonne && this.getLigne() > nouvelle_ligne) {//Déplacement diagonale gauche haut
            for (int i = 1; i < this.getColonne() - nouvelle_colonne; i++) {
                if(this.getLigne()-i >=0 && this.getLigne()-i<8) {
                    if (echiquier.examinePiece(this.getColonne() - i, this.getLigne() - i) != null) {
                        ValiditeAucunePiece = false;
                    }
                }
            }
        } else if (this.getColonne() > nouvelle_colonne && this.getLigne() < nouvelle_ligne) {//Déplacement diagonale gauche bas
            for (int i = 1; i < this.getColonne() - nouvelle_colonne; i++) {
                if(this.getLigne()+i >=0 && this.getLigne()+i<8) {
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

    public String representationAscii(){
        return (estBlanc()? "D": "d");
    }//Représentation Ascii
    public String representationUnicode(){return (estBlanc()? "♕": "♛");}//Représentation Unicode
}