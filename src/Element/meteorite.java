package Element;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Acteurs.Joueur;

public class meteorite {
////------------------------------Propriétés---------------------------------////
	private float x, y, VitH = 8;
	private boolean collision;
	private int type;

////----------------------Constructeur--------------------------------////
	public meteorite() {
		this.x = (int) (Math.random() * 550);
		this.y = 10;
		collision = false;
		type =  (int) (Math.random() * 3);
	}

////----------------------------Methodes --------------------------------////
	public void dessiner(Graphics g) throws SlickException {
		Image imagex = null;
		switch (type) {
		case 0:
			imagex = new Image("img/meteorite.png");
			break;
		case 1:
			imagex = new Image("img/mars.png");
			break;
		case 2:
			imagex = new Image("img/terre.png");
			break;
		case 3:
			imagex = new Image("img/venus.png");
			break;
		}
		g.drawImage(imagex, x, y);
				
	}

////--------------------- methode pour deplacer la météorite---------------------------------////
	public void deplacer(int delta) {
		if (y < 600)
			y += VitH * delta / 100f;
	}

////--------------------Test Collision --------------------------------////
	public boolean testCollision(Joueur e) {
		if (this.x >= e.getX() && this.x <= e.getX() + 60 && this.y >= e.getY() && this.y <= e.getY() + 60)
			return true;
		else
			return false;
	}

////------------------------------Getters---------------------------------////

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public boolean isCollision() {
		return collision;
	}

////--------------------------Setters---------------------------------////
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

}
