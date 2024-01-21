package jeu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class PuzzleGui extends JFrame {
	
	Puzzle puzzle;
	VuePuzzle pan;
	public static int DIM = 100;


	public PuzzleGui(Puzzle puzzle){
		this.puzzle=puzzle;
		this.pan = new VuePuzzle(this.puzzle);
		this.addMouseListener(pan);
		
		this.setTitle("Grille du puzzle ");
		this.setSize(DIM*this.puzzle.getLargeur()+15,DIM*this.puzzle.getHauteur()+40);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.setContentPane(pan);
		this.setVisible(true);
	}
		
	
}
//Check KeyBuinding
//javac -d "build" jeu/*.java l2.util.evmt/*.java
//java -cp "build" jeu.main

