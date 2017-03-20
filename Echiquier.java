public class Echiquier{
	private Piece[][]tableau_de_jeu = new Piece[8][8];
	private Piece [] blancs_captures = new Piece[16];
	private Piece [] noirs_captures = new Piece[16];
	public Echiquier(){

		//Pièces noires.
		this.tableau_de_jeu[0][0]= new Tour(false,0,7,this);
		this.tableau_de_jeu[0][1]= new Cavalier(false, 1,7,this);
		this.tableau_de_jeu[0][2]= new Fou(false, 2,7,this);
		this.tableau_de_jeu[0][3]= new Dame(false, 3,7,this);
		this.tableau_de_jeu[0][4]= new Roi(false, 4,7,this);
		this.tableau_de_jeu[0][5]= new Fou(false, 5,7,this);
		this.tableau_de_jeu[0][6]= new Cavalier(false, 6,7,this);
		this.tableau_de_jeu[0][7]= new Tour(false, 7,7,this);
		this.tableau_de_jeu[1][0]= new Pion(false, 0,6,this);
		this.tableau_de_jeu[1][1]= new Pion(false, 1,6,this);
		this.tableau_de_jeu[1][2]= new Pion(false, 2,6,this);
		this.tableau_de_jeu[1][3]= new Pion(false, 3,6,this);
		this.tableau_de_jeu[1][4]= new Pion(false, 4,6,this);
		this.tableau_de_jeu[1][5]= new Pion(false, 5,6,this);
		this.tableau_de_jeu[1][6]= new Pion(false, 6,6,this);
		this.tableau_de_jeu[1][7]= new Pion(false, 7,6,this);
		
		//Pièces blanches.		
		this.tableau_de_jeu[7][0]= new Tour(true,0,0,this);
		this.tableau_de_jeu[7][1]= new Cavalier(true, 1,0,this);
		this.tableau_de_jeu[7][2]= new Fou(true, 2,0,this);
		this.tableau_de_jeu[7][3]= new Dame(true, 3,0,this);
		this.tableau_de_jeu[7][4]= new Roi(true, 4,0,this);
		this.tableau_de_jeu[7][5]= new Fou(true, 5,0,this);
		this.tableau_de_jeu[7][6]= new Cavalier(true, 6,0,this);
		this.tableau_de_jeu[7][7]= new Tour(true, 7,0,this);
		this.tableau_de_jeu[6][0]= new Pion(true, 0,1,this);
		this.tableau_de_jeu[6][1]= new Pion(true, 1,1,this);
		this.tableau_de_jeu[6][2]= new Pion(true, 2,1,this);
		this.tableau_de_jeu[6][3]= new Pion(true, 3,1,this);
		this.tableau_de_jeu[6][4]= new Pion(true, 4,1,this);
		this.tableau_de_jeu[6][5]= new Pion(true, 5,1,this);
		this.tableau_de_jeu[6][6]= new Pion(true, 6,1,this);
		this.tableau_de_jeu[6][7]= new Pion(true, 7,1,this);
	}
	public boolean caseValide(int colonne, int ligne){
		return(ligne >= 0 && ligne < 8 && colonne >= 0 && colonne <8);
	}
	public Piece examinePiece(int colonne, int ligne){
		return tableau_de_jeu[ligne-1][colonne-1];//null si case vide, sinon, retourne la piece
//		if (tableau_de_jeu[ligne-1][colonne-1]== null)// vite vite je ne sais pas comment
//			return null;
//		else
//			return tableau_de_jeu[ligne-1][colonne-1];// Génère le tableau de 0 à 7, mais l'utilisateur entre de 1 à 8.
	}
	public Piece prendsPiece(int colonne, int ligne){
			Piece p = examinePiece(colonne,ligne);
			tableau_de_jeu[ligne-1][colonne-1]= null;
			return p;
	}
	public void posePiece(Piece p){
		tableau_de_jeu[p.getLigne()-1][p.getColonne()-1]= p;}
		
	public void capturePiece(int colonne, int ligne){
		if (examinePiece(colonne, ligne).estBlanc()){
			for(int i=0; i < blancs_captures.length; i++)
				if(blancs_captures[i]==null)
					blancs_captures[i]=prendsPiece(colonne, ligne);
		}else{
			for(int i=0; i < noirs_captures.length;i++)
				if(noirs_captures[i]==null)
					noirs_captures[i] = prendsPiece(colonne, ligne);
		}
	}
		
	public void afficheAscii(){
		String[] tab =new String[8];
		String  noirs_cap="";
		String  blancs_cap="";
		for(int i=0; i < 8;i++){
			for(int j=0;j < 8;j++){
				if(tab[i]==null){
					if(tableau_de_jeu[i][j]== null){
						tab[i]= "• ";
					}else {
						tab[i]= tableau_de_jeu[i][j].representationAscii()+" ";
					}
				}else{
					if(tableau_de_jeu[i][j]== null){
						tab[i]=tab[i]+ "• ";
					}else {
						tab[i]=tab[i]+ tableau_de_jeu[i][j].representationAscii()+" ";
					}
				}

			}
		};
		for(int k = 0;k < 14;k++){
			if (noirs_captures[k]==null)
				noirs_cap=noirs_cap+"";
			else
				noirs_cap=noirs_cap+ noirs_captures[k].representationAscii();
		}
		for(int l = 0;l < 14;l++){
			if (blancs_captures[l]==null)
				blancs_cap=blancs_cap+"";
			else
				blancs_cap=blancs_cap+ blancs_captures[l].representationAscii();
		};
		
		System.out.println("Les noirs ont capture:"+ noirs_cap+"\n"+"\n"+"\n");
		System.out.println("  a b c d e f g h  ");
		System.out.println("  ---------------  ");
		System.out.println("8| "+tab[7]+" |8"+"\n"+
						   "7| "+tab[6]+" |7"+"\n"+
						   "6| "+tab[5]+" |6"+"\n"+
						   "5| "+tab[4]+" |5"+"\n"+
						   "4| "+tab[3]+" |4"+"\n"+
						   "3| "+tab[2]+" |3"+"\n"+
						   "2| "+tab[1]+" |2"+"\n"+
						   "1| "+tab[0]+" |1"+"\n");
		System.out.println("  ---------------  ");
		System.out.println("  a b c d e f g h  "+"\n"+"\n"+"\n");
		System.out.println("Les blancs ont capture:"+ blancs_cap);
		
	}
	public void afficheUnicode() {
		String[] tab =new String[8];
		String  noirs_cap="";
		String  blancs_cap="";
		for(int i=0; i < 8;i++){
			for(int j=0;j < 8;j++){
				if(tab[i]==null){
					if(tableau_de_jeu[i][j]== null){
						tab[i]= " |";
					}else {
						tab[i]= tableau_de_jeu[i][j].representationUnicode()+" |";
					}
				}else{
					if(j==7){
						if(tableau_de_jeu[i][j]== null){
							tab[i]=tab[i]+ "    ";
						}else {
							tab[i]=tab[i]+ tableau_de_jeu[i][j].representationUnicode();
						}
					}else{
						if(tableau_de_jeu[i][j]== null){
							tab[i]=tab[i]+ "    |";
						}else {
							tab[i]=tab[i]+ tableau_de_jeu[i][j].representationUnicode()+"|";
						}
					}

				}

			}
		};

		for(int k = 0;k < 14;k++){
			if (noirs_captures[k]==null)
				noirs_cap=noirs_cap+"";
			else
				noirs_cap=noirs_cap+ noirs_captures[k].representationUnicode();
		}
		for(int l = 0;l < 14;l++){
			if (blancs_captures[l]==null)
				blancs_cap=blancs_cap+"";
			else
				blancs_cap=blancs_cap+ blancs_captures[l].representationUnicode();
		};

		System.out.println("Les noirs ont capture:"+ noirs_cap+"\n"+"\n"+"\n");
		System.out.println("  a b c d e f g h  ");
		System.out.println("  ---------------  ");
		System.out.println(" ┌───┬───┬───┬───┬───┬───┬───┬───┐"+"\n"+
				"8| "+tab[7]+" |8"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"7| "+tab[6]+" |7"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"6| "+tab[5]+" |6"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"5| "+tab[4]+" |5"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"4| "+tab[3]+" |4"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"3| "+tab[2]+" |3"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"2| "+tab[1]+" |2"+"\n"+
				" ├───┼───┼───┼───┼───┼───┼───┼───┤"+"\n"+
				"1| "+tab[0]+" |1"+"\n"+
				" └───┴───┴───┴───┴───┴───┴───┴───┘"+"\n");

		System.out.println("  ---------------  ");
		System.out.println("  a b c d e f g h  "+"\n"+"\n"+"\n");
		System.out.println("Les blancs ont capture:"+ blancs_cap);
	}
}