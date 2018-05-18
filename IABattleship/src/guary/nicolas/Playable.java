package guary.nicolas;

public interface Playable {
	
	// Methods that are common to both Human and AI Player
	
	//Ability to initialize a Fleet with Ships
	public Ship createShip(FleetType fleetType);
	public Fleet initFleet();
	
	//Ability to shoot opponent's Ships
	public void setResShot(Coordinates c, int i);
	public int shot(Coordinates c);
	public Coordinates attack();
	
	//Getters
	public String getName();
	public Fleet getFleet();
	
}
