public class Fou extends Piece{
	public Fou(boolean est_blanc, int colonne, int ligne, Echiquier echiquier){
		super(est_blanc, colonne, ligne, echiquier);
	}

	@Override
	public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne){

		int deltaX=Math.abs(nouvelle_ligne - this.getLigne());
		int deltaY=Math.abs(nouvelle_colonne - this.getColonne());

		boolean ValiditeGenerale=super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
		boolean ValiditeMouvement=(deltaX==deltaY);//a bouge dans la bonne diagonale

		if(ValiditeGenerale && ValiditeMouvement)
			return true;
		return false;

	}

	public String representationAscii(){
		return (estBlanc()? "F": "f");
	}
	public String representationUnicode(){return (estBlanc()? "♗" : "♝");}
}

