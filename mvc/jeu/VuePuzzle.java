package jeu;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.Observer;
import java.util.Observable;

import java.util.Map;
import java.util.HashMap;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;

public class VuePuzzle extends JPanel implements Observer, MouseListener {
	
	Puzzle puzzle;
	public static int DIM = 100;
	public static int HEIGHT = 98;
	public static int WIDTH = 98;
	public static Map<Integer, Image> IMAGE = new HashMap<>();
	
	public VuePuzzle(Puzzle puzzle){
		this.puzzle=puzzle;
		this.puzzle.addObserver(this);
		this.imOpen();
	}
	

	public void paintComponent(Graphics g){
		for (int y = 0; y<puzzle.getHauteur(); y++) {
			for (int x = 0; x<puzzle.getLargeur(); x++){
				if (puzzle.getBoard(y,x)instanceof PieceVide){
					Color cc = new Color(255,255,255);
					g.setColor(cc);
					g.fillRect(x*DIM , y*DIM , HEIGHT, WIDTH);
				}else{
					g.drawImage(IMAGE.get(this.puzzle.getBoard(y,x).getID()),x*DIM , y*DIM , HEIGHT, WIDTH, this);
				}
			}
		}
	}
	
	public void imOpen(){
		try {
			BufferedImage img = ImageIO.read(new File("wizo.jpg"));
	        for (int j=0; j<this.puzzle.getHauteur(); j++){
				for (int i=0; i<this.puzzle.getLargeur(); i++){
		          int x=(img.getWidth(null)*i/this.puzzle.getLargeur());
		          int y=(img.getHeight(null)*j/this.puzzle.getHauteur());
		          int width=(img.getWidth(null)/this.puzzle.getLargeur());
		          int height=(img.getHeight(null)/this.puzzle.getHauteur());
		          System.out.println(this.puzzle.getBoard(j,i).getID());
		          IMAGE.put(this.puzzle.getBoard(j,i).getID(),img.getSubimage(x, y, width, height));
		        }
			}
	    }
	    catch(IOException e){
	        System.out.println("no image found");
	    }
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		if (!this.puzzle.isFinished()){
			int x =(e.getX()-15)/DIM;
			int y =(e.getY()-40)/DIM;
			for (int j=0; j<this.puzzle.getHauteur(); j++){
				for (int i=0; i<this.puzzle.getLargeur(); i++){
					if(this.puzzle.getBoard(j,i).getX() == x && this.puzzle.getBoard(j,i).getY() == y){
						this.puzzle.moveClic(this.puzzle.getBoard(j,i));
					}	
				}
			}
		}
	}
	
	public void mouseEntered(MouseEvent e){
	}
	
	public void mouseExited(MouseEvent e){
	}
	
	public void mousePressed(MouseEvent e){
	}
	
	public void mouseReleased(MouseEvent e){
	}
	
	
	public void update(Observable o, Object arg) {
		//ardoise.setPossedeDisque(modele.getExiste());
		this.repaint();
	}
}
