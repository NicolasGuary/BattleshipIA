package fr.igpolytech.guary.battleship;

import java.util.HashMap;
import java.util.Scanner;

import fr.igpolytech.guary.exceptions.OverlapException;

public class Human implements Playable {
	public static final int BOARD_SIZE = 10;
	/* Garde en memoire les tirs sur l'adversaire
	code : A l'eau : 0 ; Touche : 1 ; Touché-Coulé : 2 */   
	private HashMap<Coordinates, Integer> opponentBoard;
	private Fleet fleet;
	private String name;
	
	
	public Human(String name, Fleet fleet) {
		this.fleet = fleet;
		this.name = name;
		opponentBoard = new HashMap<Coordinates, Integer>();
	}
	
	public Human() {
	}
	
	public Fleet getFleet(){
		return this.fleet;
	}

	public String getName() {
		return name;
	}
	
	//Method used to ask an Human player to input Coordinates
	static Scanner sc = new Scanner(System.in);;
	public String askCoordinates(String message) {
		System.out.println(message);
		String coord = sc.nextLine();
		
		while (!(Coordinates.isValid(coord))) {
			System.out.println("Coordinates aren't valid.");
			System.out.println(message);
			coord = sc.nextLine();
		}
		
		return coord;
	}
	
	//Ability to initialize a Fleet manually via Scanners
	
	public Ship createShip(FleetType fleetType) {	
		String coordStart = askCoordinates("Coordonnée de départ du bateau : "  
				+ fleetType.toString() 
				+ " de taille " + fleetType.getSize()
				+ " ? ex : \"A1\" ");
		Coordinates startingPoint = new Coordinates(coordStart);
		String coordEnd = askCoordinates("Et ses coordonnées de fin ? Coordonnées possibles : "+startingPoint.computeCoordinates(fleetType.getSize()));	
		Ship ship = null;
		while (ship == null) {
			try {
				ship = new Ship(fleetType.toString(), coordStart, coordEnd);
			} catch(Exception e) {
				System.out.println(e.toString());
				coordStart = askCoordinates("Coordonnée de départ du bateau : "  
						+ fleetType.toString() 
						+ " de taille " + fleetType.getSize()
						+ " ? ex : \"A1\" ");
				coordEnd = askCoordinates("Et ses coordonnées de fin ex : "+startingPoint.computeCoordinates(fleetType.getSize()));
			}
		}		
		return ship;		
	}

	public Fleet initFleet() {
		Fleet fleet = new Fleet();
		for(FleetType fleetType: FleetType.values() ) {
			for (int i = 0; i < fleetType.getAmount(); i++) {
				boolean insert = false;
				while (!insert) {
					try {
						fleet.addShip(createShip(fleetType));
						insert = true;
					} catch (OverlapException e) {
						System.out.println(e);
					}
				}	
			}
		}
		return fleet;
	}
	
	//Ability to shoot opponent's with inputed Coordinates
	public Coordinates attack() {
		Coordinates coordShot = new Coordinates(askCoordinates("Où souhaitez vous tirer ?"));
		return coordShot;
	}
	
	public int shot(Coordinates pos){
		return this.fleet.shot(pos);
	}
	
	// Update the board that records the shots
	public void setResShot(Coordinates coordShot, int resShot) {
		if (resShot <= 2 ) {
			this.opponentBoard.put(coordShot, resShot);
		}
	}
	
	//Methods used to display the board for Human players
	public  String opponentBoardString () {
		String res = "  ";
		for(int j=0;j<Human.BOARD_SIZE+1;j++){
			for(char i='A';i<'A'+Human.BOARD_SIZE;i++){
				if (j == 0) {
					res += "  "+ i;
				} else {
					Coordinates c = new Coordinates(i,j);	
					res+= valueCell(c);	
				}
			}
			if (j != Human.BOARD_SIZE) {
				if(j<9) {
					res +="\n" + (j+1) +"  " ;
				}
				else {
					res +="\n" + (j+1) +" " ;
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
					res+=" 0 ";
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
	
	@Override
	public String toString() {
		return "Player [fleet=" + fleet + "]";
	}

}
