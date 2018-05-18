package guary.nicolas;

public class Game {
	private int turn;
	private Playable j1;
	private Playable j2;
	private int round;

	public Game(Playable j1, Playable j2) {
		this.turn = 0;
		this.j1 = j1;
		this.j2=j2;
		this.round=0;
	}
	
	public Game() {
		this.turn=0;
	}
	
//GETTERS & SETTERS
	public int getTurn() {
		return turn;
	}
	
	public int playerTurn() {
		//0 pour Tour J1 et 1 pour Tour J2
		return turn % 2;
	}
	
	public Playable whosTurn() {
		if(round%2==0) {
			if(playerTurn()==0) {
				return j1;
			}
			else {
				return j2;
			}	
		}
		else {
			if(playerTurn()==0) {
				return j2;
			}
			else {
				return j1;
			}
		}	
	}
	
	public void setRound(int r) {
		this.round=r;
	}
public Playable getOpponent() {
	if(round%2==0) {
		if(playerTurn()==0) {
			return j2;
		}
		else {
			return j1;
		}	
	}
	else {
		if(playerTurn()==0) {
			return j1;
		}
		else {
			return j2;
		}
	}
}

public void nextTurn(){
	this.turn++;
}

public boolean gameIsOver(){
	return getOpponent().getFleet().shipsRemaining() == 0 
			|| whosTurn().getFleet().shipsRemaining() == 0;
}

public Playable getWinner() {
	if (getOpponent().getFleet().shipsRemaining() == 0) {
		return whosTurn();
	} else if(whosTurn().getFleet().shipsRemaining() == 0) {
		return getOpponent();
	}
	return null;
	
}


}