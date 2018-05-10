package fr.igpolytech.guary.play;

import java.util.Scanner;

public class TournamentAI {
	
	static Scanner sc = new Scanner(System.in);;
	
	public static void main(String[] args) {
		System.out.println("Choisissez la compétition voulue: \n"
				+ "1: Easy AI vs Medium AI \n" +
				"2: Easy AI vs Hard AI \n" +
				"3: Medium AI vs Hard AI \n");
		String choice = sc.nextLine();
		int gameMode = Integer.parseInt(choice);
		
		while(gameMode > 3 || gameMode < 1) {
			System.out.println("Mode non valide. Saisissez un mode disponible");
			System.out.println("Choisissez la compétition voulue: \n"
					+ "1: Easy AI vs Medium AI \n" +
					"2: Easy AI vs Hard AI \n" +
					"3: Medium AI vs Hard AI \n");
			choice = sc.nextLine();
			gameMode = Integer.parseInt(choice);
		}
		
		switch (gameMode) {
		case 1:
			EasyVsMedium.main(null);
            break;
		case 2:
			HardvsEasy.main(null);
            break;
		case 3:
			MediumVsHard.main(null);
            break;
		}

	}

}
