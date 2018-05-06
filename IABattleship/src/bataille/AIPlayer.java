package bataille;
import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

	public AIPlayer(String name, Fleet fleet) {
		super(name, fleet);
		// TODO Auto-generated constructor stub
	}
	public static Coordinates placeShipStart() {
		final String alphabet = "ABCDEFGHIJ";
	    final int N = alphabet.length();
	    Random r = new Random();
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		char k=alphabet.charAt(r.nextInt(N));
		Coordinates coordinates = new Coordinates(k,n);
		return coordinates;
	}
	
	public static Coordinates placeShipEnd(String start, Integer fleetType) {
		Coordinates s = new Coordinates(start);
		ArrayList<Coordinates> available = new ArrayList<Coordinates>();
		available = s.computeCoord(fleetType);
		int options = available.size();
		Random r = new Random();
		int  n = r.nextInt(options) + 1;
		Coordinates res = available.get(n);
		return res;
		}
	
	public Coordinates attack() {
		final String alphabet = "ABCDEFGHIJ";
	    final int N = alphabet.length();
	    Random r = new Random();
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		char k=alphabet.charAt(r.nextInt(N));
		Coordinates coordinates = new Coordinates(k,n);
		return coordinates;
	}
	
	
	// Permet de créer un bateau
		public Ship createIAShip(FleetType fleetType) {
			
			String coordStart = placeShipStart().toString();
			String coordEnd = placeShipEnd(coordStart, fleetType.getSize()).toString();
		
			Ship ship = null;
			while (ship == null) {
				// On repose les questions jusqu'à ce que ce soit valide
				try {
					ship = new Ship(fleetType.toString(), coordStart, coordEnd);
				} catch(Exception e) {
					System.out.println(e.toString());
					coordStart = placeShipStart().toString();
					coordEnd = placeShipEnd(coordStart, fleetType.getSize()).toString();
				}
			}
			
			return ship;	
		}
}
