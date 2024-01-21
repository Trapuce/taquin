package jeu;

import java.util.*;
import java.awt.Image;


public class Piece{
	
	protected int id;
	
	protected int actualx;
	protected int actualy;
	
	protected int finalx;
	protected int finaly;
	
		
	public Piece(int id, int finalx, int finaly, int actualx, int actualy){
		this.id = id;
		this.finalx = finalx;
		this.finaly = finaly;
		this.actualx = actualx;
		this.actualy = actualy;		
	}
		
	public int getID(){
		return this.id;
	}
	
	public int getFinalX(){
		return this.finalx;
	}
	
	public int getFinalY(){
		return this.finaly;
	}
	
	public int getX(){
		return this.actualx;
	}
	
	public int getY(){
		return this.actualy;
	}
		
	public void setID(int id){
		this.id = id;
	}
	
	public void setX(int x){
		this.actualx = x;
	}
	
	public void setY(int y){
		this.actualy = y;
	}
		
}
