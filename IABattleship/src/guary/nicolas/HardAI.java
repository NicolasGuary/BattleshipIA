package guary.nicolas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import fr.igpolytech.guary.exceptions.OverlapException;

public class HardAI implements Playable {
	public static final int BOARD_SIZE = 10;
	/* Garde en memoire les tirs sur l'adversaire
	code : A l'eau : 0 ; Touche : 1 ; Touché-Coulé : 2 */   
	private HashMap<Coordinates, Integer> opponentBoard;
	private Fleet fleet;
	private Stack<Coordinates> targets;
	private Coordinates previousShot;
	
	public HardAI() {
		System.out.println("Initialisation du joueur " + this.getName());
		this.fleet = initFleet();
		this.targets = new Stack<Coordinates>();
		this.previousShot = new Coordinates();
		opponentBoard = new HashMap<Coordinates, Integer>();
	}
 
	public String getName() {
		return "HardAI";
	}
	public Fleet getFleet() {
		return this.fleet;
	}
	
	//Ability to place Ships randomly
	public Coordinates placeShipStart() {
		//Generating random int 
		Random rand = new Random();
		int  n = rand.nextInt(BOARD_SIZE) + 1;
		//Generating random char
	    Random r = new Random();
	    int j = r.nextInt(BOARD_SIZE);
		char k=(char)('A'+j);
		Coordinates coordinates = new Coordinates(k,n);
		return coordinates;
	}
	
	public Coordinates placeShipEnd(Coordinates start, FleetType fleetType) {
		Integer fleetSize = fleetType.getSize();
		ArrayList<Coordinates> available = new ArrayList<Coordinates>();
		available = start.computeCoord(fleetSize, this);
		if(available.isEmpty()) {
		    start = placeShipStart();
		    available = start.computeCoord(fleetSize, this);  
		}
		int options = available.size();
		Random r = new Random();
		int  n = r.nextInt(options) + 1;
		Coordinates res = available.get(n-1);
		return res;
		}
	
	public Ship createShip(FleetType fleetType) {
		Coordinates coordStart = placeShipStart();
		Coordinates coordEnd = placeShipEnd(coordStart, fleetType);
		Ship ship = null;
		while (ship == null) {
			try {
				ship = new Ship(fleetType.toString(), coordStart.toString(), coordEnd.toString());
			} catch(Exception e) {
				System.out.println(e.toString());
				coordStart = placeShipStart();
				coordEnd = placeShipEnd(coordStart, fleetType);
				
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
	
	//Ability to attack with a strategy
	
	public Coordinates attack() {
		Coordinates coordinates = new Coordinates();
		//First turn: Hunt Mode
		if(previousShot.equals(new Coordinates())) {
			coordinates = hunt();
		}
		//Hunting for a new Ship to take down
		else if(targets.empty() && opponentBoard.get(previousShot)!=1) {
			coordinates = hunt();
		}
		else { //if (!targets.empty())
		//There is a Ship targeted to take down
			if(opponentBoard.get(previousShot)==1) {
				target(previousShot);
			}
			if(!(targets.empty())) {
				coordinates = targets.pop();
			}

			else{
				coordinates = hunt();
			}
		}
		previousShot=coordinates;
		return coordinates;
	}
	
	//Will shot only on odd Coordinates in order to find a Ship to sunk quicker
	private Coordinates hunt() {
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
	    Random r = new Random();
	    int j = r.nextInt(BOARD_SIZE);
		char k=(char)('A'+j);
		Coordinates coordinates = new Coordinates(k,n);
		while((this.opponentBoard.containsKey(coordinates) || !(Coordinates.isOdd(coordinates)))) { 
			//We won't pick a coordinate that as already been used
			rand = new Random();
			n = rand.nextInt(BOARD_SIZE) + 1;
		    r = new Random();
		    j = r.nextInt(BOARD_SIZE);
			k=(char)('A'+j);
			coordinates = new Coordinates(k,n);
		}
		return coordinates;
	}
	
	//Will update the stack containing - at max - the 4 surrounding Coordinates around c
	//If a Coordinate is already shot or not valid it is not added
	public void target(Coordinates c) {
		if(Coordinates.isValid(c.up(2).toString()) && !(this.opponentBoard.containsKey(c.up(2)))){
			this.targets.push(c.up(2));
		}
		if(Coordinates.isValid(c.down(2).toString()) && !(this.opponentBoard.containsKey(c.down(2)))){
			this.targets.push(c.down(2));
		}
		if(Coordinates.isValid(c.left(2).toString()) && !(this.opponentBoard.containsKey(c.left(2)))){
			this.targets.push(c.left(2));
		}
		if(Coordinates.isValid(c.right(2).toString()) && !(this.opponentBoard.containsKey(c.right(2)))){
			this.targets.push(c.right(2));
		}	
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
}
