package bataille;

import java.util.ArrayList;


public class Coordinates {
	private int hor;
	private char vert;

	public Coordinates(String coord) {
		this.vert = Character.toUpperCase(coord.charAt(0));
		this.hor = Integer.parseInt(coord.substring(1));
	}
	
	public Coordinates(char vert, int hor) {
		this.vert = Character.toUpperCase(vert);
		this.hor = hor;
	}
	
	//EMPTY CONSTRUCTOR FOR NULL COORDINATES
	public Coordinates() {}
	
	public char getVert(){
		return this.vert;
	}
	
	public int getHor() {
		return this.hor;
	}

	public boolean isHorizontal(Coordinates coord) {
		return this.hor == coord.getHor();
	}
	
	public boolean isVertical(Coordinates coord) {
		return this.vert == coord.getVert();
	}
	
	public char incrementChar(char c, int v){
		int ascii = (int) c;
		char res = (char) (ascii+v);
		return res;
	}
	public char decrementChar(char c, int v){
		int ascii = (int) c;
		char res = (char) (ascii-v);
		return res;
	}
	public static boolean isValid(String coord)  {
		if (coord == null || coord.isEmpty() || coord.equalsIgnoreCase(null) || coord.length()<2) {
			  return false;
			}
		// Vérification de la lettre
		if (!Character.isLetter(coord.charAt(0))) {
			return false;
		}
		if (!Character.isDigit(coord.charAt(1))) {
			return false;
		}
		char ligne = Character.toUpperCase(coord.charAt(0));
		
		if ((int)ligne > ((int)'A' + Player.BOARD_SIZE-1) || (int)ligne < ((int)'A')) {
			return false;
		}
		// Vérification de la colonne
		try {
			int checkColonne = Integer.valueOf(coord.substring(1));
			if (checkColonne < 1 || checkColonne > Player.BOARD_SIZE) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public String computeCoordinates(Integer shipSize) {
		String res="";
		int y = this.getHor()-(shipSize-1);
		String a = incrementChar(this.getVert(), (shipSize-1)) + ""+this.getHor();
		String b = decrementChar(this.getVert(), (shipSize-1)) + ""+this.getHor();
		String c = this.getVert()+""+(this.getHor()+(shipSize-1));
		String d = this.getVert()+""+y;
		if(isValid(a)) {
			res +=a+" or ";
		}
		if(isValid(b)) {
			res +=b+" or ";
		}
		if(isValid(c)) {
			res +=c+" or ";
		}
		if(isValid(d)) {
			res +=d+" or ";
		}
		res = res.substring(0, res.length()-3);
		return res;
	}
	
	public boolean coordOverlap(Player p) {
		for(Ship shipFleet : p.getFleet().getShipList()){
		for(Coordinates c : shipFleet.getShipCase().keySet()){
			if (this.equals(c)){
				return true;
			}
		}
		}
		return false;
	}
	
	public ArrayList<Coordinates> computeCoord(Integer shipSize, Playable player) {
		ArrayList<Coordinates> res= new ArrayList<Coordinates>();
		
		Coordinates a = this.right(shipSize);
		Coordinates b = this.left(shipSize);
		Coordinates c = this.down(shipSize);
		Coordinates d = this.up(shipSize);
		
		if(isValid(a.toString())) {
			res.add(a);
		}
		if(isValid(b.toString())) {
			res.add(b);
		}
		if(isValid(c.toString())) {
			res.add(c);
		}
		if(isValid(d.toString())) {
			res.add(d);
		};
		return res;
	}
	// Redéfinition de hasCode et de equals pour la comparaison automatique de containsKey
	public boolean equals(Object o) {
		Coordinates coord = (Coordinates)o;
		return this.hor == coord.getHor() && this.vert == coord.getVert();
	}
	
	@Override
	   public int hashCode() {
	       return Integer.hashCode(this.vert);
	   }
	
	
	//RE-IMPLEMENTING EQUALS SO IT CAN COMPARE PROPERLY TWO COORDINATES INSTEAD OF COMPARING TWO OBJECTS MEMORY ADRESSES
	public boolean equals(Coordinates c){
		return this.vert == c.vert && this.hor== c.hor;
	}
	@Override
	public String toString() {
		return vert + "" + hor;
	}
	
	public Coordinates left(Integer size) {
		return new Coordinates(decrementChar(this.getVert(), (size-1)) + ""+this.getHor());
	}
	
	public Coordinates right(Integer size) {
		return new Coordinates(incrementChar(this.getVert(), (size-1)) + ""+this.getHor());
	}
	public Coordinates up(Integer size) {
		int y = this.getHor()-(size-1);
		return new Coordinates(this.getVert()+""+y);
	}
	public Coordinates down(Integer size) {
		return new Coordinates(this.getVert()+""+(this.getHor()+(size-1)));
	}
	
	
	
}
