package bataille;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import exception.OverlapException;

public class MediumAI implements Playable {
	public static final int BOARD_SIZE = 10;
	/* Garde en memoire les tirs sur l'adversaire
	code : A l'eau : 0 ; Touche : 1 ; Touché-Coulé : 2 */   
	private HashMap<Coordinates, Integer> opponentBoard;
	private Fleet fleet;
	private String name;
	public MediumAI(String name, Fleet fleet) {
		this.name = name;
		this.fleet=fleet;
		opponentBoard = new HashMap<Coordinates, Integer>();
	}
	public MediumAI() {
		super();
	}
 
	public String getName() {
		return this.name;
	}
	public Fleet getFleet() {
		return this.fleet;
	}
	//Ability to place Ships randomly
	
	public Coordinates placeShipStart() {
		//Generating random int 
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		//Generating random char
	    Random r = new Random();
	    int j = r.nextInt(10);
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
	
	//Ability to attack at random Coordinates with memory
	
	public Coordinates attack() {
		//Generating random int 
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		//Generating random char
	    Random r = new Random();
	    int j = r.nextInt(10);
		char k=(char)('A'+j);
		Coordinates coordinates = new Coordinates(k,n);
		
		while(this.opponentBoard.containsKey(coordinates)) { 
			//We won't pick a coordinate that as already been used
			rand = new Random();
			n = rand.nextInt(10) + 1;
		    r = new Random();
		    j = r.nextInt(10);
			k=(char)('A'+j);
			coordinates = new Coordinates(k,n);
		}
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
	
	//Methods used to display the board
	public  String OpponentBoardString () {
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
