package Element;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Acteurs.Ennemi;
import Acteurs.Joueur;

public class Missile {
	//// ------------------------------Propriétés---------------------------------////
	private float x, y;
	private float v;
	private int type;

	//// ---------------------Constructeur avec paramètres----------------////
	public Missile(float x, float y, int type) {
		this.x = x;  
		this.y = y;
		this.v = 12;
		this.type = type;
	}

	//// ----------------------------Méthodes--------------------------------////
	public void deplacer(Graphics g, int delta) throws SlickException {
		// ----------------Missile du joueur---------------//
		if (type == 0)
			y -= v * delta / 100f;

		if (type == 4) {
			x += v * delta / 100f;
			y -= v * delta / 100f;
		}

		if (type == 5) {
			x -= v * delta / 100f;
			y -= v * delta / 100f;
		}

		// ----------------Missile de l'ennemi---------------//
		if (type == 1)
			y += v * delta / 100f;
		if (type == 2) {
			x += v * delta / 100f;
			y += v * delta / 100f;
		}
		if (type == 3 || type == 6) {
			x -= v * delta / 100f;
			y += v * delta / 100f;
		}
	 
		
		if (type == 7) {
			x += v * delta / 100f;
			y += v * delta / 100f;
		}
		afficher(g);
	}

	public void afficher(Graphics g) throws SlickException {

		Image imagex = null;
		switch (type) {
//// ---------------------Affichage du missile du joueur-------------------------------////
		case 0:
			imagex = new Image("img/QuatriemeMissile.png");
			break;
			
		case 4:
			imagex = new Image("img/QuatriemeMissiled.png");
			break;
			
		case 5:
			imagex = new Image("img/QuatriemeMissileg.png");
			break;
	
//// ---------------------Affichage du missile de l'ennemi-------------------------------////
		case 1:
			imagex = new Image("img/PremierMissile.png");
			break;
		case 2:
			imagex = new Image("img/SecondMissiled.png");
			break;
		case 3:
			imagex = new Image("img/TroisiemeMissileg.png");
			break;

		case 6:
			imagex = new Image("img/SecondMissileg.png");
			break;
			
		case 7:
			imagex = new Image("img/TroisiemeMissiled.png");
			break;
	 
			
			
		}
		g.drawImage(imagex, x, y);

	}

////---------------Test Collision-----------------------------////
	public boolean testCollisionJoueur(Joueur j) {
		/// ----- Test Collision du missile l'ennemi avec le joueur-----///
		return (this.x >= j.getX() && this.x <= j.getX() + 60 && this.y >= j.getY() && this.y <= j.getY() + 60);
	}

	public boolean testCollisionEnnemi(Ennemi e) {
		if (this.x >= e.getX() && this.x <= e.getX() + 60 && this.y >= e.getY() && this.y <= e.getY() + 60)
			return true;
		else
			return false;
	}

	public boolean testCollisionMeteorite(meteorite e) {
		if (this.x >= e.getX() && this.x <= e.getX() + 60 && this.y >= e.getY() && this.y <= e.getY() + 60)
			return true;
		else
			return false;
	}

	/// -------------Getters--------------///
	public int getType() {
		return type;
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	/// -------------Setters--------------///
	public void setType(int type) {
		this.type = type;
	}
}