public class Pion extends Piece{
	public Pion(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){
		super(est_blanc, colonne, ligne, echiquier);
	}

	@Override
	public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){

		int deltaX=Math.abs(nouvelle_ligne - this.getLigne());
		int deltaY=Math.abs(nouvelle_colonne - this.getColonne());

		boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
		boolean ValiditeMouvement=(deltaX == 2 && deltaY == 1||deltaX == 1 && deltaY == 2);
		if(ValiditeGenerale && ValiditeMouvement)
			return true;
		return false;


	}

	public String representationAscii(){
		return (estBlanc()? "P": "p");
	}
	public String representationUnicode(){return (estBlanc()? "♙": "♟");}
}