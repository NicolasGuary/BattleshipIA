package fr.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import guary.nicolas.Coordinates;
import guary.nicolas.EasyAI;
import guary.nicolas.Game;
import guary.nicolas.HardAI;
import guary.nicolas.MediumAI;
import guary.nicolas.Playable;

public class TestIA {	
	private static Playable p1 = null;
	private static Playable p2 = null;
	private static int rounds = 0;
	private static final String fileName = "ai_proof.csv";

	
	public static void main(String[] args) {
		int easy1 = 0;
		int medium1 = 0;
		for(int i=0; i<100; i++) {
			rounds=i;
			p1 = new EasyAI();
			p2= new MediumAI();
			Game game = new Game(p1, p2);
			Playable winner = play(game, rounds);
			if(winner==p1) {
				easy1 ++;
			}
			else {
				medium1++;
			}
			System.out.println("Le joueur " + winner.getName() + " a gagné "+"au tour "+game.getTurn()+"\n");
			System.out.println("Score P1 lv. " +p1.getName() +" : " +easy1 +"\n");
			System.out.println("Score P2 lv. " +p2.getName() +" : " +medium1 +"\n");
		}
		int easy2 = 0;
		int hard1 = 0;
		for(int j=0; j<100; j++) {
			rounds=j;
			p1= new EasyAI();
			p2= new HardAI();
			Game game = new Game(p1, p2);
			Playable winner = play(game, rounds);
			if(winner==p1) {
				easy2 ++;
			}
			else {
				hard1 ++;
			}
			System.out.println("Le joueur " + winner.getName() + " a gagné "+"au tour "+game.getTurn()+"\n");
			System.out.println("Score P1 lv. " +p1.getName() +" : " +easy2 +"\n");
			System.out.println("Score P2 lv. " +p2.getName() +" : " +hard1 +"\n");
		}
		
		int medium2 = 0;
		int hard2 = 0;
		for(int k=0; k<100; k++) {
			rounds=k;
			p1= new MediumAI();
			p2= new HardAI();
			Game game = new Game(p1, p2);
			Playable winner = play(game, rounds);
			if(winner==p1) {
				medium2 ++;
			}
			else {
				hard2++;
			}
			System.out.println("Le joueur " + winner.getName() + " a gagné "+"au tour "+game.getTurn()+"\n");
			System.out.println("Score P1 lv. " +p1.getName() +" : " +medium2 +"\n");
			System.out.println("Score P2 lv. " +p2.getName() +" : " +hard2 +"\n");
		}
		
		
		System.out.println("Score Easy pour Easy vs Medium: "+ easy1);
		System.out.println("Score Medium pour Easy vs Medium: "+ medium1);
		System.out.println("Score Easy pour Easy vs Hard: "+ easy2);
		System.out.println("Score Hard pour Easy vs Hard: "+ hard1);
		System.out.println("Score Medium pour Hard vs Medium: "+ medium2);
		System.out.println("Score Hard pour Hard vs Medium: "+ hard2);
		
		 FileWriter fileWriter = null;
		        try {
		        	System.out.println("Wrote down results into: "+"."+File.separator+fileName);
		             fileWriter = new FileWriter(new File("."+File.separator+fileName));
		             //Write in CSV
		             String FILE_HEADER="AI Name;score;AI Name2;score2; \n";
		             fileWriter.append(FILE_HEADER.toString());
		             fileWriter.append("EasyAI;"+easy1+";");
		             fileWriter.append("MediumAI;"+medium1+"; \n");
		             fileWriter.append("EasyAI;"+easy2+";");
		             fileWriter.append("HardAI;"+hard1+"; \n");
		             fileWriter.append("MediumAI;"+medium2+";");
		             fileWriter.append("HardAI;"+hard2+"; \n");
		             
		        }catch (Exception e) {
		            System.out.println("Error writting down the file");
		            e.printStackTrace();
		        } finally {
		            try {
		                fileWriter.flush();
		                fileWriter.close();
		            } catch (IOException e) {
		                System.out.println("Error while flushing/closing fileWriter !");
		                e.printStackTrace();
		            }
		        }

		              

		
		
	}


	public static Playable play(Game game, int rounds) {
		System.out.println("Partie n° "+(rounds+1));
			game.setRound(rounds);
			while (!(game.gameIsOver())){ 
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
}