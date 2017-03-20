public abstract class Piece {
	private boolean est_blanc;
	private boolean est_capture;
	private int colonne;
	private int ligne;
	private Echiquier echiquier;
	
	public boolean estBlanc(){return est_blanc;}

	public boolean estNoir(){return (!estBlanc());}

	public boolean estCapture(){return est_capture;}
	
	public int getLigne(){return ligne;}
	
	public int getColonne(){return colonne;}

	protected Piece(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){
		this.est_blanc = est_blanc;
		this.colonne = colonne;
		this.ligne = ligne;
		this.echiquier = echiquier;
		est_capture = false;
	}
	public void meSuisFaitCapture(){est_capture=true;}
	public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){
		if(this.estBlanc())
			return(!estCapture() && echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) && (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne)).estBlanc());
		else 
			return(!estCapture() && echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) && (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne)).estNoir());
	}
	public void deplace(int nouvelle_colonne, int nouvelle_ligne){//pas complet

		if(echiquier.examinePiece(nouvelle_colonne,nouvelle_ligne)== null) {
			Piece p = echiquier.prendsPiece(this.getColonne(), this.getLigne());
			this.colonne = nouvelle_colonne;
			this.ligne = nouvelle_ligne;
			echiquier.posePiece(p);
		}else{
			Piece p = echiquier.prendsPiece(this.getColonne(),this.getLigne());
			echiquier.capturePiece(nouvelle_colonne,nouvelle_ligne);
			this.colonne = nouvelle_colonne;
			this.ligne = nouvelle_ligne;
			echiquier.posePiece(p);
		}

	}
	public String toString(){return ("Blancs?"+ est_blanc + "capturé?" + est_capture + "numéro de colonne" + colonne +"numéro de ligne"+ ligne);}
	public abstract String representationAscii();
	public abstract String representationUnicode();
	
}