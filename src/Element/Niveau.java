package Element;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import Acteurs.Joueur;

public class Niveau {
////------------------------------Propriétés---------------------------------////
	private int niveau;
	private int timer, delta;

////-----------------------------Constructeur---------------------------------////
	public Niveau() {
		niveau = 1;
		timer = 0;
	}

////----------------Methode qui permet d'afficher le niveau--------------------------------////

	public void render(Graphics g, Joueur j) {
		if(niveau ==1 )
		{
			if ((timer += delta) < 2000) {
				Font font = new Font("Verdana",Font.BOLD,30);
				TrueTypeFont ttf = new TrueTypeFont(font,true);
				//g.setColor(Color.white);
				ttf.drawString(200,300,"Niveau " + niveau,Color.white);
			} 
		}
		
		if(niveau ==2 )
		{
			if ((timer += delta) < 2000) {
				Font font = new Font("Verdana",Font.BOLD,30);
				TrueTypeFont ttf = new TrueTypeFont(font,true);
				//g.setColor(Color.white);
				ttf.drawString(200,300,"Niveau " + niveau,Color.white);
			} 
		}
		
		if(niveau ==5 )
		{
			if ((timer += delta) < 2000) {
				Font font = new Font("Verdana",Font.BOLD,30);
				TrueTypeFont ttf = new TrueTypeFont(font,true);
				//g.setColor(Color.white);
				ttf.drawString(200,300,"Niveau " + niveau,Color.white);
			} 
		}
		
		if(niveau ==4 )
		{
			if ((timer += delta) < 2000) {
				Font font = new Font("Verdana",Font.BOLD,30);
				TrueTypeFont ttf = new TrueTypeFont(font,true);
				//g.setColor(Color.white);
				ttf.drawString(200,300,"Niveau " + niveau,Color.white);
			} 
		}
		
		if(niveau ==5 )
		{
			if ((timer += delta) < 2000) {
				Font font = new Font("Verdana",Font.BOLD,30);
				TrueTypeFont ttf = new TrueTypeFont(font,true);
				//g.setColor(Color.white);
				ttf.drawString(200,300,"Niveau " + niveau,Color.white);
			} 
		}
	
		
	}

////---------------------------Qui  permet de changer le niveau-------------------------------////

	public void update(Joueur j) {
		if (j.getScore() > 25 && j.getScore() <= 50) {
			niveau=2;
		}
		if (j.getScore() > 50 && j.getScore() <=75) {
			niveau=3;
		}

		if (j.getScore() > 75 && j.getScore() <= 90) {
			niveau=4;
		}

		if (j.getScore() > 90) {
			niveau=5;
		}

	}

////---------------------Getters------------------------////
	public int getNiveau() {
		return niveau;
	}
////---------------------Setters------------------------////

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

}
