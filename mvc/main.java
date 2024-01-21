package jeu;

import java.util.Scanner;


public class  main{
	
	public static void main (String[] args) {	
			
		Puzzle puzzle = new Puzzle(3,3);
		Controle controle = new Controle(puzzle);
		PuzzleGui puzzleGui = new PuzzleGui(puzzle);
		puzzleGui.addKeyListener(controle);
		puzzle.randomizer(2);
		puzzle.toString();
		/*while(!puzzle.isFinished()){
			puzzle = puzzle.getMove();
		}*/

	 }
 }
