//Sara Bertrand et Marc-Antoine Dufresne

public abstract class Piece {
    //Attributs
    private boolean est_blanc;
    private boolean est_capture;
    private int colonne;
    private int ligne;
    protected Echiquier echiquier;
    private boolean pasDeMouvement;
    private boolean vientDeDouble;
    private boolean vientEnPassant;

    //Constructeur
    protected Piece(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){
        this.est_blanc = est_blanc;
        this.colonne = colonne;
        this.ligne = ligne;
        this.echiquier = echiquier;
        this.est_capture = false;
        this.pasDeMouvement = true;
        this.vientDeDouble=false;
        this.vientEnPassant=false;
    }
    //Méthodes
    public boolean estBlanc(){return est_blanc;}
    public boolean estNoir(){return (!estBlanc());}
    public boolean estCapture(){return this.est_capture;}

    public int getLigne(){return this.ligne;}
    public int getColonne(){return this.colonne;}
    public boolean getPmvt(){return this.pasDeMouvement;}
    public boolean getVEP(){return this.vientEnPassant;}//vient de faire un En Passant, est seulement utilise pour pion
    public boolean getVDD(){return this.vientDeDouble;}//vient de faire un mouvement double vers l'avant, est seulement utilisé pour pion

    public void setVEP(boolean value){this.vientEnPassant=value;}
    public void setPasPmvt(){this.pasDeMouvement=false;};
    public void setVDD(boolean value){this.vientDeDouble=value;}

    public void meSuisFaitCapture(){this.est_capture=true;}
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {//Fonction qui vérifie si une pièce peut bouger à la case dédirée
        if (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne) == null){//S'il n'y a pas de pièce à cette case
            return (!estCapture() && this.echiquier.caseValide(nouvelle_colonne, nouvelle_ligne));
        }else {
            if (this.estBlanc()) {//Si la pièce est blanche.
                return (!estCapture() && this.echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) && echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne).estNoir());
            } else {//Si la pièce est noire.
                return (!estCapture() && this.echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) && (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne)).estBlanc());
            }
        }
    }
    public void deplace(int nouvelle_colonne, int nouvelle_ligne){//Fonction qui deplace la pièce à la case désirée et qui vérifie s'il y avait déjà une pièce
        if(this.echiquier.examinePiece(nouvelle_colonne,nouvelle_ligne)== null) {//S'il n'y avait pas de pièce.
            Piece p = this.echiquier.prendsPiece(this.getColonne(), this.getLigne());
            this.colonne = nouvelle_colonne;
            this.ligne = nouvelle_ligne;
            echiquier.posePiece(p);
        }else{//S'il y avait une pièce, on la capture.
            Piece p = echiquier.prendsPiece(this.getColonne(),this.getLigne());
            this.echiquier.capturePiece(nouvelle_colonne,nouvelle_ligne);
            this.colonne = nouvelle_colonne;
            this.ligne = nouvelle_ligne;
            this.echiquier.posePiece(p);
        }

    }
    public void finPartie(){//Fonction qui vérifie si c'est un roi qui est capturé. Si oui, c'est la fin de la partie.
        if(this.representationAscii().equals("r")){//Roi noir éliminé
            System.out.println("Le roi noir est éliminé. Les Blancs ont gagné!");
            echiquier.setPartie(false);
        }
        else if(this.representationAscii().equals("R")){//Roi blanc éliminé
            System.out.println("Le roi blanc est éliminé. Les Noirs ont gagné!");
            echiquier.setPartie(false);
        }
    }

    public String toString(){return ("Blancs?"+ est_blanc + "capturé?" + est_capture + "numéro de colonne" + colonne +"numéro de ligne"+ ligne);}
    public abstract String representationAscii();
    public abstract String representationUnicode();
}