package Acteurs;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import Element.Missile;

public class Ennemi {
	// ------------------------------ proprietes----------------------------------//
	private float x; // position horizontale
	private float y; // position verticale
	private int vitesseE; // Vitesse de l'ennemi
	private int type; // type d'ennemi
	private int time; // timer
	private Graphics g;
	private boolean disparait; // Nous nous declare si l'ennemi est affiché ou non
	private boolean direction; // d: sens true droite false gauche
	private boolean collision; // verifie que l'ennemi est en collision avec le joueur
	private ArrayList<Missile> missilesE = new ArrayList<>();// tableau de missile ennemis
	private int vie; // Nombre de vie
	private boolean directiongauche; // position du boss
 
	// -----------------------------Constructeur----------------------------------//

	public Ennemi() {
		vitesseE = 5;
		time = 0;
		collision = false;
		disparait = false;
		this.y = 10;
		this.type = (int) (Math.random() * 3);
		this.x = (int) (Math.random() * 550);
		direction = (Math.random() > 0.5);
		if (!direction && type == 1)
			type = 3;

		if (type == 0)
			vie = 2;
		if (type == 1 || type == 3)
			vie = 3;
		if (type == 2)
			vie = 4;

		directiongauche = true;
	}

	// -----------------------------Methodes----------------------------------//

	public void dessiner(Graphics g) throws SlickException {
		Image imagex = null;
		switch (type) {
		case 0:
			imagex = new Image("img/Ennemi1.png");
			break;
		// Ennemi qui va vers la droite
		case 1:
			imagex = new Image("img/Ennemi2.png");
			break;
		case 2:
			imagex = new Image("img/Ennemi3.png");
			break;
		// Ennemi qui va vers la gauche
		case 3:
			imagex = new Image("img/Ennemi2g.png");
			break;

		}
		g.drawImage(imagex, x, y);
	}

	public void dessinerVie(Graphics g) throws SlickException {
		Image imagex = null;
		if (type == 0) {
			if (vie == 2)
				imagex = new Image("img/Ennemi0Vie2.png");
			if (vie == 1)
				imagex = new Image("img/Ennemi0Vie1.png");

			g.drawImage(imagex, x, y + 50);
		}

		if (type == 1 || type == 3) {
			if (vie == 3)
				imagex = new Image("img/Ennemi1Vie3.png");
			if (vie == 2)
				imagex = new Image("img/Ennemi1Vie2.png");
			if (vie == 1)
				imagex = new Image("img/Ennemi1Vie1.png");

			g.drawImage(imagex, x, y + 50);
		}

		if (type == 2) {
			if (vie == 4)
				imagex = new Image("img/Ennemi2Vie4.png");
			if (vie == 3)
				imagex = new Image("img/Ennemi2Vie3.png");
			if (vie == 2)
				imagex = new Image("img/Ennemi2Vie2.png");
			if (vie == 1)
				imagex = new Image("img/Ennemi2Vie1.png");

			g.drawImage(imagex, x, y + 50);
		}

	}

// ----Methode qui permet de deplacer l'ennemi-------------------------------//
	public void deplacer(int delta) {
		if (y < 610) {
			if (type == 0) {

				y += vitesseE * delta / 100f;

				if (directiongauche) {
					x -= vitesseE * delta / 100f;
				} else if (!directiongauche)
					x += vitesseE * delta / 100f;

				if (x >= 320)
					directiongauche = true;
				if (x <= 50)
					directiongauche = false;
			}

			if (type == 1 && direction) {
				x += vitesseE * delta / 100f;
				y += vitesseE * delta / 100f;
			}
			if (type == 3 && !direction) {
				x -= vitesseE * delta / 100f;
				y += vitesseE * delta / 100f;
			}
			if (type == 2) {
				y += vitesseE * delta / 100f;
			}

		}
	}

// ------------------Destruction du joueur----------------------------------//
	public void destruction(Joueur j) {
		for (int i = 0; i < missilesE.size(); i++) {
			if (missilesE.get(i).testCollisionJoueur(j)) {
				missilesE.remove(i);
				j.setVie(-1);
				j.setCollision(true);
			}
		}
	}

////--------------------Test Collision de l'ennemi avec le joueur --------------------------------////
	public boolean testCollision(Joueur e) {
		if (this.x >= e.getX() && this.x <= e.getX() + 60 && this.y >= e.getY() && this.y <= e.getY() + 60)
			return true;
		else
			return false;
	}

// -----------------Deplacement des les missiles de l'ennemi----------------------------------//
	public void deplacermissileE(int delta) throws SlickException {
		if (!collision) {
			if ((time += delta) > 1300) {
				time = 0;
				if (type == 0)
					missilesE.add(new Missile(x + 20, y + 60, type + 1));
				if (type == 1 && direction) {
					missilesE.add(new Missile(x + 50, y + 40, 2));
				}
				if (type == 3 && !direction) {
					missilesE.add(new Missile(x + 20, y + 40, 6));
				}
				if (type == 2) {
					missilesE.add(new Missile(x + 5, y + 38, 3));
					missilesE.add(new Missile(x + 5, y + 38, 7));
				}

			}

		}

		for (Missile m : missilesE) {
			m.deplacer(g, delta);
		}

	}

// ------------------Affichage des missiles de l'ennemi----------------------------------//
	public void affichermissileE(Graphics g) throws SlickException {
		for (Missile m : missilesE)
			m.afficher(g);

		if (collision) {
			for (Missile m : missilesE)
				m.afficher(g);
		}
	}

// --------------Methode qui supprime l'ennemi----------------------------------//
	public void retirerE() {
		for (int i = 0; i < missilesE.size(); i++)
			if (missilesE.get(i).getY() > 580 || missilesE.get(i).getX() > 580)
				missilesE.remove(i);
	}

	// --------Getters------//
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isCollision() {
		return collision;
	}

	public ArrayList<Missile> getMissilesE() {
		return missilesE;
	}

	public boolean isDisparait() {
		return disparait;
	}

	public int getVie() {
		return vie;
	}

	public int getType() {
		return type;
	}

	// --------Setters------//
	public void setGraphic(Graphics g) {
		this.g = g;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public void setMissilesE(ArrayList<Missile> missilesE) {
		this.missilesE = missilesE;
	}

	public void setDisparait(boolean disparait) {
		this.disparait = disparait;
	}

	public void setVie(int vie) {
		this.vie += vie;
	}

	public void setType(int type) {
		this.type = type;
	}

}