//Sara Bertrand et Marc-Antoine Dufresne

public class Pion extends Piece{

    public Pion(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){//Constructeur
        super(est_blanc, colonne, ligne, echiquier);

    }

    public boolean decisionCouleurDirection(int  deltaY, boolean couleur){//Fonction interdisant le pion de reculer dependemment de la couleur.
        return ((couleur)?(deltaY>0):(deltaY<0));
    }

    public boolean decision(int deltaY, int deltaX, int nouvelle_colonne, int nouvelle_ligne, double direction, boolean couleur){
        if (decisionCouleurDirection(deltaY,couleur)) {//un pion blanc ne peut aller que vers le haut
            return false;
        } else {
            if(deltaX!=0){//capture
                if(Math.abs(deltaY)==1){
                    if(this.echiquier.examinePiece(nouvelle_colonne,nouvelle_ligne)==null){//on verifie si la case de capture est vide
                        if(this.echiquier.examinePiece(nouvelle_colonne,(int)(nouvelle_ligne + direction))!=null){
                            if(this.echiquier.examinePiece(nouvelle_colonne,(int)(nouvelle_ligne + direction)).getVDD()) {//En Passant
                                this.setVEP(true);
                                return true;
                            }else {
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return (this.echiquier.examinePiece(nouvelle_colonne,nouvelle_ligne).estBlanc()!=couleur);
                    }
                }else{
                    return false;
                }
            }else{//mouvement incluant les en passant
                if(Math.abs(deltaY)==2){
                    if((this.echiquier.examinePiece(nouvelle_colonne,nouvelle_ligne)==null)&&(this.echiquier.examinePiece(nouvelle_colonne,(int)(nouvelle_ligne + direction))==null)){
                        if(this.getPmvt()){
                            this.setVDD(true);
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return ((this.echiquier.examinePiece(nouvelle_colonne,nouvelle_ligne))==null);//rien devant la piece
                }
            }
        }
    }
    @Override
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){//Redéfinition des déplacements valides selon les mouvements autorisés du pion.

        int deltaX=Math.abs(nouvelle_colonne - this.getColonne());
        int deltaY=nouvelle_ligne - this.getLigne();
        boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
        boolean ValiditeMouvement;

        if(deltaX>1||Math.abs(deltaY)>2||Math.abs(deltaY)==0){//mouvement trop grand ou sur place
            ValiditeMouvement=false;
        }else {
            if (this.estBlanc()) {//la piece est blanche
                ValiditeMouvement=decision(deltaY,deltaX,nouvelle_colonne,nouvelle_ligne,+1,this.estBlanc());

            } else {//la piece est noire
                ValiditeMouvement=decision(deltaY,deltaX,nouvelle_colonne,nouvelle_ligne,-1,this.estBlanc());
            }
        }
        return (ValiditeGenerale && ValiditeMouvement);
    }

    public String representationAscii(){return (estBlanc()?  "P":"p");}//Représentation Ascii
    public String representationUnicode(){return (estBlanc()?  "♙":"♟");}//Représentation Unicode
}