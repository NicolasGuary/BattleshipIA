package fr.igpolytech.guary.play;
import java.util.Scanner;

import fr.igpolytech.guary.battleship.Coordinates;
import fr.igpolytech.guary.battleship.EasyAI;
import fr.igpolytech.guary.battleship.Fleet;
import fr.igpolytech.guary.battleship.Game;
import fr.igpolytech.guary.battleship.MediumAI;

public class EasyVsMedium {
	private static int scoreP1 = 0;
	private static int scoreP2 = 0;
	static Scanner sc = new Scanner(System.in);;

	public static void main(String[] args) {
		for (int i=0; i<100; i++) {
			play();
		}
	}
	
	public static void play() {
		EasyAI p1 = new EasyAI();
		p1 = initAIPlayer(p1);
		MediumAI p2 = new MediumAI();
		p2 = initMediumAI(p2);
		
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
		System.out.println("Score " + p1.getName() +" : " +scoreP1 +"\n");
		System.out.println("Score " + p2.getName() +" : " +scoreP2 +"\n");	
	}
	
	private static EasyAI initAIPlayer(EasyAI p) {
		System.out.println("Initialisation du joueur " + p.getName());
		Fleet fleet = p.initFleet();
		return new EasyAI(fleet);
	}
	
	private static MediumAI initMediumAI(MediumAI p) {
		System.out.println("Initialisation du joueur " + p.getName());
		Fleet fleet = p.initFleet();
		return new MediumAI(fleet);
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
}
