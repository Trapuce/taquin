package jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener{
	
	private Puzzle puzzle;
	
	public Controle(Puzzle puzzle){
		this.puzzle=puzzle;
	}
		
	public void keyPressed(KeyEvent e){
		if (!this.puzzle.isFinished()){
			if (e.getKeyCode() == KeyEvent.VK_DOWN ){
				this.puzzle.move("s");
			}
			if (e.getKeyCode() == KeyEvent.VK_UP ){
				this.puzzle.move("z");
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT ){
				this.puzzle.move("q");
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
				this.puzzle.move("d");
			}
		}
		this.puzzle.toString();
	}
	
	public void keyReleased(KeyEvent e) { 
		
	}

    public void keyTyped(KeyEvent evt) {
		
	}

}
