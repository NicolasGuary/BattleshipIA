package bataille;
import java.util.Scanner;
import java.util.jar.Pack200;

import exception.OverlapException;

public class Main {
	
	static Scanner sc = new Scanner(System.in);;

	public static void main(String[] args) {
		
		HumanPlayer p1 = initPlayer("Player 1");
		AIPlayer p2 = initAI("Player 2");
		
		Game game = new Game (p1, p2);
		
		while (!game.gameIsOver()){ 
			System.out.println("C'est au tour de " + game.whosTurn().getName());
			afficherEtatJoueur(game.whosTurn());
			
			if(game.whosTurn() == p1) {
				Coordinates coordShot = new Coordinates(askCoordinates("Où souhaitez vous tirer ?"));
				int resShot = game.getOpponent().shot(coordShot);
				game.whosTurn().setResShot(coordShot, resShot);
				promptShot(resShot);	
				game.nextTurn();
			}
			else if(game.whosTurn() == p2){
				Coordinates coordShot = new Coordinates();
				coordShot = AICoordinates(p2);
				System.out.println("L'adversaire tire en: " + coordShot.toString());
				int resShot = game.getOpponent().shot(coordShot);
				game.whosTurn().setResShot(coordShot, resShot);
				promptShot(resShot);	
				game.nextTurn();
			}

		}
		
		System.out.println("Le joueur " + game.getWinner().getName() + " a gagné." );
	}
	
	

	private static HumanPlayer initPlayer(String name) {
		System.out.println("Initialisation du joueur " + name);
		Fleet fleet = initFleet();
		return new HumanPlayer(name, fleet);
	}
	
	private static AIPlayer initAI(String name) {
		System.out.println("Initialisation du joueur " + name);
		Fleet fleet = initFleet();
		return new AIPlayer(name, fleet);
	}

	private static Fleet initFleet() {
		Fleet fleet = new Fleet();
		
		// Création de tout les bateaux
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
	
	private static Fleet initIAFleet(AIPlayer p) {
		Fleet fleet = new Fleet();
		
		// Création de tout les bateaux
		for(FleetType fleetType: FleetType.values() ) {
			for (int i = 0; i < fleetType.getAmount(); i++) {
				boolean insert = false;
				while (!insert) {
					try {
						fleet.addShip(p.createIAShip(fleetType));
						insert = true;
					} catch (OverlapException e) {
						System.out.println(e);
					}
				}	
			}
		}
		return fleet;
	}
	
	// Permet de créer un bateau
	private static Ship createShip(FleetType fleetType) {
		// On commence par afficher quel bateau on veut créer
		
		String coordStart = askCoordinates("Coordonnée de départ du bateau : "  
				+ fleetType.toString() 
				+ " de taille " + fleetType.getSize()
				+ " ? ex : \"A1\" ");
		Coordinates startingPoint = new Coordinates(coordStart);
		String coordEnd = askCoordinates("Et ses coordonnées de fin ? Coordonnées possibles : "+startingPoint.computeCoordinates(fleetType.getSize()));
		
		Ship ship = null;
		while (ship == null) {
			// On repose les questions jusqu'à ce que ce soit valide
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
	
	
	
	// Demande des coodonnées à l'utilisateur en posant une question
	private static String askCoordinates(String message) {
		System.out.println(message);
		String coord = sc.nextLine();
		
		while (!(Coordinates.isValid(coord))) {
			System.out.println("Coordinates aren't valid.");
			System.out.println(message);
			coord = sc.nextLine();
		}
		
		return coord;
	}
	
	private static Coordinates AICoordinates(AIPlayer ai) {
		return ai.attack();
	}
	
	// Permet d'afficher le résultat d'un tir
	private static void promptShot (int codePrompt) {
		switch (codePrompt) {
		case 0 :
			System.out.println("A l'eau !");
			break;
		case 1 :
			System.out.println("Touché !");
			break;
		case 2 :
			System.out.println("Touché-coulé !");
			break;
		case 3 :
			System.out.println("Déjà touché ce bateau à cet endroit !");
			break;
		}	
	}
	
	public static void afficherEtatJoueur(Player p) {
		System.out.println("Voici le plateau ennemi : \n" + 
				p.OpponentBoardString() + "\n");
				//+ p.fleetString()
	}
}
