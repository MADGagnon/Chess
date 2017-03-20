public class Roi extends Piece{
	public Roi(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){
		super(est_blanc, colonne, ligne, echiquier);
	}

	@Override
	public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){

		int deltaX=Math.abs(nouvelle_ligne - this.getLigne());
		int deltaY=Math.abs(nouvelle_colonne - this.getColonne());

		boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
		boolean ValiditeMouvement=((deltaX == deltaY && deltaX == 1 && deltaY == 1)||(deltaX == 0 && deltaY == 1)||(deltaX == 1 && deltaY == 0));
		if(ValiditeGenerale && ValiditeMouvement)
			return true;
		return false;


	}

	public String representationAscii(){
		return (estBlanc()? "R": "r");
	}
	public String representationUnicode(){return (estBlanc()? "♔": "♚");}
}