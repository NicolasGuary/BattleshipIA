package bataille;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import exception.*;

public class Split {

	public static void main(String[] args) {
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
		
		
		Fleet fleet = new Fleet();
		AIPlayer player = new AIPlayer("j1", fleet);
		Coordinates sCoordinates = new Coordinates();
		sCoordinates = player.placeShipStart();
		Coordinates eCoordinates = new Coordinates();
		FleetType fleetType;
		Ship s=player.createIAShip(fleetType.CRUISER);
		
		
		try{
		Ship s3 = new Ship("CRUISER","C6","A6");
		System.out.println(s3.toString());
		Ship s4 = new Ship("CRUISER","A3","A5");
		System.out.println(s4.toString());
		Coordinates a  = new Coordinates("H2");
		Coordinates b  = new Coordinates("F2");
		System.out.println(s3.checkSize(a, b));
		}
		catch( BadSizeException | OrientationException e){
			System.out.println(e);
			System.out.println("Please input a new Ship");
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