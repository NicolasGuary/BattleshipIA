package fr.igpolytech.guary.battleship;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import fr.igpolytech.guary.exceptions.OverlapException;

public class EasyAI implements Playable {
	public static final int BOARD_SIZE = 10;
	/* Garde en memoire les tirs sur l'adversaire
	code : A l'eau : 0 ; Touche : 1 ; Touché-Coulé : 2 */   
	private HashMap<Coordinates, Integer> opponentBoard;
	private Fleet fleet;
	
	public EasyAI(Fleet fleet) {
		this.fleet=fleet;
		opponentBoard = new HashMap<Coordinates, Integer>();
	}
	public EasyAI() {
		super();
	}
 
	public String getName() {
		return "EasyAI";
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
	
	//Ability to attack at random Coordinates
	
	public Coordinates attack() {
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
	
	public int shot(Coordinates pos){
		return this.fleet.shot(pos);
	}
	
	// Update the board that records the shots
	public void setResShot(Coordinates coordShot, int resShot) {
		if (resShot <= 2 ) {
			this.opponentBoard.put(coordShot, resShot);
		}
	}
}
