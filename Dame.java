public class Dame extends Piece{
	public Dame(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){
		super(est_blanc, colonne, ligne, echiquier);
	}

	@Override
	public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){

		int deltaX=Math.abs(nouvelle_ligne - this.getLigne());
		int deltaY=Math.abs(nouvelle_colonne - this.getColonne());

		boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
		boolean ValiditeMouvement=((deltaX == deltaY)||deltaX == 0 && deltaY != 0||deltaX != 0 && deltaY == 0);
		if(ValiditeGenerale && ValiditeMouvement)
			return true;
		return false;


	}

	public String representationAscii(){
		return (estBlanc()? "D": "d");
	}
	public String representationUnicode(){return (estBlanc()? "♕": "♛");}
}
