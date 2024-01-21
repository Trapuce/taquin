package jeu;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.util.Observable;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.Scanner;

import java.awt.Rectangle;
import java.awt.Point;

public class Puzzle extends Observable{
	
	private String DIRECTION[] = {"z","q","s","d"};
	private Piece board[][];
	private int hauteur;
	private int largeur;
	
	
	public Puzzle(int hauteur,int largeur){
		//Création de la grille.
		Piece board[][] = new Piece[hauteur][largeur];
		int compteur = 0;
		for (int y = 0; y < hauteur; y++){
			for (int x = 0; x < largeur; x++){
				if(compteur != hauteur*largeur-1){
					board[y][x] = new Piece(compteur++,x,y,x,y);
				}else{
					board[y][x] = new PieceVide(largeur-1,hauteur-1,x,y);
					compteur++;
				}
			}
		}
		//Definition des attributs de la classe.
		this.board = board;
		this.hauteur = hauteur;
		this.largeur = largeur;
		//this.imOpen();
	}
	
	public void moveClic(Piece piece){
		if(this.pieceVideProche(piece) != null){
			Point point = this.pieceVideProche(piece);
			Piece pieceVide = this.getPieceVide();
			if(point.getX() == -1){
				this.board[piece.getY()][piece.getX()] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
				piece.setX(piece.getX()+1);
				this.board[pieceVide.getY()][pieceVide.getX()] = piece;	
				this.setChanged();
				this.notifyObservers();
								
			}else if(point.getX() == 1){
				this.board[piece.getY()][piece.getX()] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
				piece.setX(piece.getX()-1);
				this.board[piece.getY()][piece.getX()] = piece;	
				this.setChanged();
				this.notifyObservers();
				return;
				
			}else if(point.getY() == -1){
				this.board[piece.getY()][piece.getX()] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
				piece.setY(piece.getY()+1);
				this.board[pieceVide.getY()][pieceVide.getX()] = piece;	
				this.setChanged();
				this.notifyObservers();
				
			}else{
				this.board[piece.getY()][piece.getX()] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
				piece.setY(piece.getY()-1);
				this.board[pieceVide.getY()][pieceVide.getX()] = piece;	
				this.setChanged();
				this.notifyObservers();
				return;
			}
		}
	}
	
	public Piece getPieceVide(){
		for (int j=0; j<this.hauteur; j++){
			for (int i=0; i<this.largeur; i++){
				if(board[j][i]instanceof PieceVide){
					return board[j][i];
				}
			}
		}
		return null;
	}
	
	public Point pieceVideProche(Piece piece){
		Piece pieceVide = this.getPieceVide();
		if(pieceVide == null){
			return null;
		}
		if(pieceVide.getX()-1 == piece.getX() && pieceVide.getY() == piece.getY()){
			return new Point(-1,0);
		}else if(pieceVide.getX()+1 == piece.getX() && pieceVide.getY() == piece.getY()){
			return new Point(1,0);
		}else if(pieceVide.getY()+1 == piece.getY() && pieceVide.getX() == piece.getX()){
			return new Point(0,1);
		}else if(pieceVide.getY()-1 == piece.getY() && pieceVide.getX() == piece.getX()){
			return new Point(0,-1);
		}else{
			return null;
		}
	}
		
	public void randomizer(int nombreCoup){
		Random random = new Random();
		String memoire = "s";
		while(nombreCoup > 0){
			int direction = random.nextInt(4);
			if(direction == 0){
				if(this.movePossible("z")){
					if(memoire.equals("z") == false){
						memoire = "s";
						move("z"); 
						nombreCoup--;
					}
				}
			}
			if(direction == 1){
				if(this.movePossible("q")){
					if(memoire.equals("q") == false){
						memoire = "d";
						move("q");
						nombreCoup--;
					}
				}
			}
			if(direction == 2){
				if(this.movePossible("s")){
					if(memoire.equals("s") == false){
						memoire = "z";
						move("s");
						nombreCoup--;
					}
				}
			}
			if(direction == 3){
				if(this.movePossible("d")){
					if(memoire.equals("d") == false){
						memoire = "q";
						move("d");
						nombreCoup--;
					}
				}
			}
		}	
	}
		

	
		
	public int compteCasesPlacees(){
		int compteur = 0;
		for (int y = 0; y < this.hauteur; y++){
			for (int x = 0; x < this.largeur; x++){
				if(this.board[y][x].getFinalX() == this.board[y][x].getX() && this.board[y][x].getFinalY() == this.board[y][x].getY()){
					compteur ++;
				}
			}
		}
		System.out.println(compteur);
		return compteur;
	}
	
	public Puzzle getMove(){
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		this.move(str);
		this.toString();
		return this;
	}	
	
	public boolean movePossible(String move){
		for (int y = 0; y < this.hauteur; y++){
			for (int x = 0; x < this.largeur; x++){
				if(this.board[y][x]instanceof PieceVide){
					if(move.equals("Z") || move.equals("z")){
						if(y > 0){
							return true;
						}else{
							return false;
						}
					}
					if(move.equals("S") || move.equals("s")){
						if(y < hauteur-1){
							return true;
						}else{
							return false;
						}
					}
					if(move.equals("Q") || move.equals("q")){
						if(x > 0){
							return true;
						}else{
							return false;
						}
					}
					if(move.equals("D") || move.equals("d")){
						if(x < largeur-1){
							return true;
						}else{
							return false;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void move(String move){
		for (int y = 0; y < this.hauteur; y++){
			for (int x = 0; x < this.largeur; x++){
				if(this.board[y][x]instanceof PieceVide){
					if(move.equals("Z") || move.equals("z")){
						if(this.movePossible(move)){
							Piece piece = this.board[y-1][x];
							this.board[y-1][x] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
							piece.setY(piece.getY()+1);
							this.board[y][x] = piece;
							//this.setChanged();
							//this.notifyObservers();
						}
					}
					if(move.equals("S") || move.equals("s")){
						if(this.movePossible(move)){
							Piece piece = this.board[y+1][x];
							this.board[y+1][x] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
							piece.setY(piece.getY()-1);
							this.board[y][x] = piece;
							//this.setChanged();
							//this.notifyObservers();
							return;
						}
					}
					if(move.equals("Q") || move.equals("q")){
						if(this.movePossible(move)){
							Piece piece = this.board[y][x-1];
							this.board[y][x-1] = new PieceVide( this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
							piece.setX(piece.getX()+1);
							this.board[y][x] = piece;
							//this.setChanged();
							//this.notifyObservers();
						}						
					}
					if(move.equals("D") || move.equals("d")){
						if(this.movePossible(move)){
							Piece piece = this.board[y][x+1];
							this.board[y][x+1] = new PieceVide(this.largeur-1, this.hauteur-1, piece.getX(), piece.getY());
							piece.setX(piece.getX()-1);
							this.board[y][x] = piece;
							//this.setChanged();
							//this.notifyObservers();
							return;
						}
					}
				}
			}
		}
	}
	
		
	public boolean isFinished(){
		if(this.compteCasesPlacees()!=(this.hauteur*this.largeur)){
			return false;
		}else{
			System.out.println("GG");
			return true;
		}
	}
	
	public void setXY(){
		for (int y = 0; y<this.getHauteur(); y++){
			for (int x = 0; x<this.getLargeur(); x++){
				this.board[y][x].setX(x);
				this.board[y][x].setY(y);
			}
		}
	}
	
	public String toString(){
		for (int y = 0; y < this.hauteur; y++){
			for (int x = 0; x < this.largeur; x++){
				if(this.board[y][x]instanceof PieceVide){
					System.out.print("     ");
				}else{
					System.out.print("[" + this.board[y][x].getX()+ " " + this.board[y][x].getY() + "]");
				}
            }
            System.out.println();
        } 
        return "";
	}
	
	public int getLargeur(){
		return this.largeur;
	}
	
	public int getHauteur(){
		return this.hauteur;
	}
	
	public Piece getBoard(int y, int x){
		return this.board[y][x];
	}
	
	
}
