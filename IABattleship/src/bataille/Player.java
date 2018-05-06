package bataille;

import java.util.HashMap;

public abstract class Player {
	public static final int BOARD_SIZE = 10;
	/* Garde en memoire les tirs sur l'adversaire
	code : A l'eau : 0 ; Touche : 1 ; Touché-Coulé : 2 */   
	private HashMap<Coordinates, Integer> opponentBoard;
	
	private Fleet fleet;
	private String name;
	
	
	public Player(String name, Fleet fleet) {
		super();
		this.fleet = fleet;
		this.name = name;
		opponentBoard = new HashMap<Coordinates, Integer>();
	}
	
	
	public Fleet getFleet(){
		return this.fleet;
	}
	
	public int shot(Coordinates pos){
		return this.fleet.shot(pos);
	};
	
	@Override
	public String toString() {
		return "Player [fleet=" + fleet + "]";
	}
	
	public  String OpponentBoardString () {
		String res = "  ";
		for(int j=1;j<Player.BOARD_SIZE+2;j++){
			for(char i='A';i<'A'+Player.BOARD_SIZE;i++){
				// Première ligne on crée les lettres
				if (j == 1) {
					res += "  "+ i;
				} else {
					Coordinates c = new Coordinates(i,j);	
					res+= valueCell(c);	
				}
			}
			if (j != Player.BOARD_SIZE+1) {
				if(j<10) {
					res +="\n" + j +"  " ;
				}
				else {
					res +="\n" + j +" " ;
				}
				
			} else {
				res +="\n" ;
			}
		}
		return res;
	}
	
	public  String valueCell(Coordinates c) {
		String res="";
		
			if(opponentBoard.containsKey(c)) {
				if(opponentBoard.get(c)==0) {
					res+=" - ";
				}
				if(opponentBoard.get(c)==1) {
					res+=" x ";
				}
				if(opponentBoard.get(c)==2) {
					res+=" * ";
				}
			}
			else{
				res+=" ~ ";
		}
			
		return res;	
	}


	/*
	public String fleetString() {
		String res = "";
		for(int j=1;j<Player.BOARD_SIZE+1;j++){
			for(char i='A';i<'A'+Player.BOARD_SIZE;i++){
				Coordinates c=new Coordinates(i,j);
				res+= valueCellPlayer(c);			}
			res +="\n";
		}
		return res;
	}
	
	public String valueCellPlayer(Coordinates c) {
		String res="";
			if(this.getFleet().getShipList().getKeys().containsKey(c)) {
				if(opponentBoard.get(c)==0) {
					res+=" - ";
				}
				if(opponentBoard.get(c)==1) {
					res+=" x ";
				}
				if(opponentBoard.get(c)==2) {
					res+=" * ";
				}
			}
			else{
				res+=" ~ ";
		}
		return res;	
	}

*/
	public String getName() {
		return name;
	}

	// Update les tirs du joueurs selon le resulat
	public void setResShot(Coordinates coordShot, int resShot) {
		if (resShot <= 2 ) {
			this.opponentBoard.put(coordShot, resShot);
		}
	
		
	};
	
	
	

	
}
