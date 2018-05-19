package guary.nicolas;
import java.util.Scanner;

public class Battleship {	
	static Scanner sc = new Scanner(System.in);;
	private static int scoreP1 = 0;
	private static int scoreP2 = 0;
	private static Playable p1 = null;
	private static Playable p2 = null;
	private static int end = 0;
	private static int rounds = 0;
	
	public static void main(String[] args) {	
		int gameMode = selectGameMode();
		
		
		//DEBUT DE LA PARTIE
		while ((end==0)) {
		Game game = startGame(gameMode);
		Playable winner = play(game, rounds);	
		if(winner==p1) {
			scoreP1 ++;
		}
		else {
			scoreP2++;
		}
		System.out.println("Le joueur " + winner.getName() + " a gagné "+"au tour "+game.getTurn()+"\n");
		System.out.println("Score P1 lv. " +p1.getName() +" : " +scoreP1 +"\n");
		System.out.println("Score P2 lv. " +p2.getName() +" : " +scoreP2 +"\n");
		
		System.out.println("Voulez vous continuer à jouer? Oui: 0, Non: 1");
		String result = sc.nextLine();
		end = Integer.parseInt(result);
		rounds ++;
		while (end!=0 && end!=1) {
			System.out.println("Choix non valide.");
			System.out.println("Voulez vous continuer à jouer? Oui: 0, Non: 1");
			result = sc.nextLine();
			end = Integer.parseInt(result);
			rounds ++;
		}
	}
}
	public static int selectGameMode() {
		//SELECTION MODE DE JEU + LEVEL AI
		System.out.println("Choisissez votre mode de jeu: \n"
				+ "1: Humain vs Humain \n" +
				"2: Humain vs CPU \n" +
				"3: CPU vs CPU \n");
		String choice = sc.nextLine();
		int gameMode = Integer.parseInt(choice);
		
		while(gameMode > 3 || gameMode < 1) {
			System.out.println("Mode non valide. Saisissez un mode disponible");
			System.out.println("Choisissez votre mode de jeu: \n"
					+ "1: Humain vs Humain \n" +
					"2: Humain vs CPU \n" +
					"3: CPU vs CPU \n");
			choice = sc.nextLine();
			gameMode = Integer.parseInt(choice);
		}
		return gameMode;
	}
	
	public static Game startGame(int gameMode) {
		Game game = null;
		switch (gameMode) {
		case 1:
			p1 = new Human("Player 1");
			p2= new Human("Player 2");
			game = new Game(p1, p2);
            break;
		case 2:
			p1= new Human("Player 1");
			p2=selectAI();
			game = new Game(p1, p2);
            break;
		case 3:
			p1 = selectAI();
			p2 = selectAI();
			game = new Game(p1, p2);
            break;
        default :
            	System.out.println("game mode not valid");
		}
		return game;
	}
	public static Playable play(Game game, int rounds) {
		System.out.println("Partie n° "+(rounds+1));
			game.setRound(rounds);
			while (!(game.gameIsOver())){ 
				System.out.println("Tour n° " + game.getTurn());
				System.out.println("C'est au tour de " + game.whosTurn().getName());
				if(game.whosTurn() instanceof Human) {
					afficherEtatJoueur((Human) game.whosTurn());
				}
					Coordinates coordShot = new Coordinates();
					coordShot = game.whosTurn().attack();
					System.out.println("L'adversaire tire en: " + coordShot.toString());
					int resShot = game.getOpponent().shot(coordShot);
					game.whosTurn().setResShot(coordShot, resShot);
					promptShot(resShot);	
					game.nextTurn();
				}
			return game.getWinner();
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
	
	public static void afficherEtatJoueur(Human p) {
		System.out.println("Voici le plateau ennemi : \n" + 
				p.opponentBoardString() + "\n");
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
			return p2;
		case 2:
			MediumAI p3 = new MediumAI();
			return p3;
		case 3:
			HardAI p4 = new HardAI();
			return p4;
		}
		return null;
	}
}