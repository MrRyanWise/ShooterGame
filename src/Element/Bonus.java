package Element;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Acteurs.Joueur;

public class Bonus {
////------------------------------Propriétés---------------------------------////
	private float x, y, VitH = 10;
	private int type;
	private boolean collision;

////-------------------------Constructeur--------------------------------////
	public Bonus() {
		this.x = (int) (Math.random() * 550);
		this.y = 10;
		this.type = (int) (Math.random() * 3);
		collision = false;
	}

	public Bonus(float x, float y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		collision = false;
	}

//// ----------------------------Méthodes--------------------------------////

	public void dessiner(Graphics g) throws SlickException {
		Image imagex = null;
		switch (type) {
		case 0:
			imagex = new Image("img/Vie.png");// augmente la vie
			break;
		case 1:
			imagex = new Image("img/DoublePoints.png");// double les points
			break;
		case 2:
			imagex = new Image("img/AugmentationBalles.png");// augmente les balles
			break;

		case 3:
			imagex = new Image("img/coin.png");// augmente les pieces
			break;
		}
		g.drawImage(imagex, x, y);

	}

////--------------- methode pour deplacer le Bonus-------------------------////
	public void deplacer(int delta) {
	//	if (type != 3) {
			if (y < 600)
				y += VitH * delta / 100f;
		//}

	}

////----------- methode pour dessiner le bonus de piece-----------------------////
	public void BonusCoin(Graphics g, float x, float y) throws SlickException {
		g.drawImage(new Image("img/coin.png"), x, y);
	}

	// -------Test de collision avec le Joueur-------///
	public void testCollision(Joueur e) {
		if (this.x >= e.getX() && this.x <= e.getX() + 60 && this.y >= e.getY() && this.y <= e.getY() + 60) {
			collision = true;
			if (type == 0)
				e.setVie(2);
			if (type == 1)
				e.setBoostScore(5);
			if (type == 2) {
				e.setBoostshoot(1);
			}
			if (type == 3)
				e.setCoin(1);
		}
	}

	/// -------------Getters--------------///
	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public boolean isCollision() {
		return collision;
	}

	public int getType() {
		return type;
	}

	

	/// -------------Setters--------------///
	public void setType(int type) {
		this.type = type;
	}
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