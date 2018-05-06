package bataille;
import java.util.Random;

public class AIPlayer extends Player {

	public AIPlayer(String name, Fleet fleet) {
		super(name, fleet);
		// TODO Auto-generated constructor stub
	}
	public Coordinates placeShip() {
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		Random random = new Random();
		int m = random.nextInt(10)+1;
		char k = (char) m;
		Coordinates coordinates = new Coordinates(k,n);
		return coordinates;
	}
	
	public Coordinates attack() {
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		Random random = new Random();
		int m = random.nextInt(10)+1;
		char k = (char) m;
		Coordinates coordinates = new Coordinates(k,n);
		return coordinates;
	}
}
