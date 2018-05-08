package bataille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import exception.*;

public class Split {

	public static void main(String[] args) {
			Fleet fleet = new Fleet();
			MediumAI aiPlayer = new MediumAI("Test", fleet);
			System.out.println("On va tester ça mon pote");
			for(int i=0; i<50;i++) {
				Coordinates coordShot = aiPlayer.attack();
				System.out.println(coordShot.toString());
				aiPlayer.setResShot(coordShot, 1);
			}
			
			
			
			//Fleet fleet = aiPlayer.initFleet();
			Human human=new Human("J2",fleet);
			System.out.println(human.OpponentBoardString());
			System.out.println(fleet.toString());

		

		System.out.println("Et mercce \n");
		// TODO Auto-generated method stub
		/*String a = "A2";
		System.out.println(a.charAt(0));
		System.out.println(a.charAt(1));
		int ascii = (int) a.charAt(0);
		System.out.println(ascii);

		Ship bat = new Ship("A3","E3");
		System.out.println(bat.getSize());
		System.out.println(bat.isHorizontal());
		System.out.println(bat.isVertical());
		System.out.println("INPIT:");
		System.out.println(bat.inputSize());
		String [] arr = new String[bat.getSize()];
		arr = bat.getShip();
		System.out.println(bat.toString());
		Game game = new Game();

		System.out.println(game.whosTurn());
		*/
		

		AIPlayer player = new AIPlayer("j1", fleet);

		
		Coordinates coordStart;
		Coordinates coordEnd;
		Ship ships = null;
		while(ships == null) {	
			
		try{		
			
		Ship ship = new Ship("BATTLESHIP", "A4","A1");
		System.out.println(ship.toString());
		Ship s3 = new Ship("CRUISER","C6","A6");
		System.out.println(s3.toString());
		coordStart = player.placeShipStart();
		coordEnd = player.placeShipEnd(coordStart, FleetType.CARRIER);
		ships = new Ship("CARRIER", coordStart.toString(), coordEnd.toString());
		System.out.println("IA SHIP: "+ships.toString());
		ArrayList<Coordinates> ar = ships.getFirstCoordinate().computeCoord(5,player);
		System.out.println("Les coordonées possibles sont: " +ar.toString());
		player.getFleet().addShip(ship);
		player.getFleet().addShip(s3);
		player.getFleet().addShip(ships);

		System.out.println(player.getFleet().toString());
		//System.out.println(test.coordOverlap(player) +" TEST HERE");
		}
		catch(Exception e){
			e.printStackTrace();
			coordStart = player.placeShipStart();
			coordEnd = player.placeShipEnd(coordStart,FleetType.CARRIER);
			System.out.println(e);
			System.out.println("Please input a new Ship");

		}

		}
		/*
		System.out.println(s1.getFirstCoordinate());
		System.out.println(s1.getLastCoordinate());
		System.out.println(s1.checkSize(new Coordinates("a1"),new Coordinates("z1")));


		
		Coordinates c1 = new Coordinates("A3");
		Coordinates c2= new Coordinates("A3");
		
		int s = c1.getVert();
		int x = c2.getVert();
		System.out.println(s);
		System.out.println(x);
		System.out.println(c1.equals(c2));
		*/
	}
}