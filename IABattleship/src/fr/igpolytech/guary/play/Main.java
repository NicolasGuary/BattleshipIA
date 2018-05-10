package fr.igpolytech.guary.play;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);;

	public static void main(String[] args) {
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
		
		switch (gameMode) {
		case 1:
			HumanVersusHuman.play();
		case 2:
			AiVersusHuman.play();
		case 3:
			AiVersusAi.play();
		}

	}

}
