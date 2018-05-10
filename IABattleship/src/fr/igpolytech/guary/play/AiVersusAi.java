package fr.igpolytech.guary.play;
import java.util.Scanner;

import fr.igpolytech.guary.battleship.Coordinates;
import fr.igpolytech.guary.battleship.EasyAI;
import fr.igpolytech.guary.battleship.Fleet;
import fr.igpolytech.guary.battleship.Game;
import fr.igpolytech.guary.battleship.MediumAI;
import fr.igpolytech.guary.battleship.Playable;

public class AiVersusAi {
	private static int scoreP1 = 0;
	private static int scoreP2 = 0;
	static Scanner sc = new Scanner(System.in);;

	public static void main(String[] args) {
			play();
	}
	
	public static void play() {
		Playable p1 = selectAI();
		Playable p2 = selectAI();
		
		Game game = new Game(p1, p2);
		while (!game.gameIsOver()){ 
			System.out.println("Tour n° " + game.getTurn());
			System.out.println("C'est au tour de " + game.whosTurn().getName());
				Coordinates coordShot = new Coordinates();
				coordShot = game.whosTurn().attack();
				System.out.println("L'adversaire tire en: " + coordShot.toString());
				int resShot = game.getOpponent().shot(coordShot);
				game.whosTurn().setResShot(coordShot, resShot);
				promptShot(resShot);	
				game.nextTurn();

		}
		if(game.getWinner()==p1) {
			scoreP1 ++;
		}
		else {
			scoreP2++;
		}
		System.out.println("Le joueur " + game.getWinner().getName() + " a gagné "+"au tour "+game.getTurn()+"\n");
		System.out.println("Score Player1: "+scoreP1 +"\n");
		System.out.println("Score Player2: "+scoreP2 +"\n");	
	}
	
	private static EasyAI initEasyAI(Playable p) {
		System.out.println("Initialisation du joueur " + p.getName());
		Fleet fleet = p.initFleet();
		return new EasyAI(fleet);
	}
	
	private static MediumAI initMediumAI(Playable p) {
		System.out.println("Initialisation du joueur " + p.getName());
		Fleet fleet = p.initFleet();
		return new MediumAI(fleet);
	}
	
	private static HardAI initHardAI(Playable p) {
		System.out.println("Initialisation du joueur " + p.getName());
		Fleet fleet = p.initFleet();
		return new HardAI(fleet);
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
	
	public static Playable selectAI() {
		System.out.println("Choisissez le niveau de difficulté de l'IA: \n"
				+ "1: Easy \n" +
				"2: Medium \n" +
				"3: Hard \n");
		String choice = sc.nextLine();
		int level = Integer.parseInt(choice);
		while(level > 3 || level < 1) {
			System.out.println("Niveau non valide! \n");
			System.out.println("Choisissez le niveau de difficulté de l'IA: \n"
					+ "1: Easy \n" +
					"2: Medium \n" +
					"3: Hard \n");
			choice = sc.nextLine();
			level = Integer.parseInt(choice);
		}

		switch (level) {
		case 1:
			EasyAI p2 = new EasyAI();
			p2 = initEasyAI(p2);
			return p2;
		case 2:
			MediumAI p3 = new MediumAI();
			p3 = initMediumAI(p3);
			return p3;
		case 3:
			HardAI p4 = new HardAI();
			p4 = initHardAI(p4);
			return p4;
		}
		return null;
	}
}
