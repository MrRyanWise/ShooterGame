package Acteurs;

import java.util.ArrayList; 

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Element.Missile;
//import Element.Sons;
import Element.meteorite;

public class Joueur { 
	// ------------------------------ proprietes----------------------------------//
	private float x;									    // position horizontale
	private float y;										// position verticale
	private int v;											// Vitesse du joueur
	private int delta;										// integration du delta
	private Graphics g;										// integration du graphics
	private int score;										// Affiche le score du joueur
	private int Coin;										// Nombre de pieces
	private static int HighScore = 50;						// Affiche le Meilleur score du joueur
	private int vie = 6;									// Nombre de vie
	private int BoostScore;									// boost de score
	private int Boostshoot;
	private boolean collision;
	private ArrayList<Missile> missiles = new ArrayList<>();// tableau de missile
	private boolean tirer; 									//Boolean qui indique qu'on a tirer un missile
 
	// -----------------------------Constructeur----------------------------------//
	public Joueur() throws SlickException {
		this.x = (int) (Math.random() * 560);
		this.y = 510;
		this.v = 25;
		BoostScore = 1; 
		Boostshoot=1;
		tirer=false;
		collision=false;
	}
	
	// ----------------------------Methodes ---------------------------------//
	public void dessiner(Graphics g) throws SlickException {
			g.drawImage(new Image("img/vaisseauJoueur.png"), x, y);
	}

	
	// ---------------tirer un missile-------------------------//
	public void tirer() throws SlickException {
		//if (missiles.size() < 50) {
		if(Boostshoot==1)
			missiles.add(new Missile(x+17 , y -30, 0));
		
		if(Boostshoot==2)
		{
			missiles.add(new Missile(x+20 , y -35, 0));
			missiles.add(new Missile(x+2 , y -35, 0));
		}

		if(Boostshoot>=3)
		{
			missiles.add(new Missile(x+17 , y -35, 0));
			missiles.add(new Missile(x+20 , y -35, 4));
			missiles.add(new Missile(x+2 , y -35, 5));
		}
		
		//}
		retirer();
		for (Missile m : missiles)
			m.deplacer(g, delta);
	}

	// Destruction des ennemis
		public void destruction(Ennemi e) {
			for (int i = 0; i < missiles.size(); i++) {
				if (missiles.get(i).testCollisionEnnemi(e) && !e.isCollision()) {
					e.setCollision(true);
					e.setDisparait(true);
					e.setVie(-1);
					missiles.remove(i);
					score += BoostScore;
				}
			}
		}

		// Destruction des meteorites
		public void destructionMeteorite(meteorite M) {
			for (int i = 0; i < missiles.size(); i++) {
				if (missiles.get(i).testCollisionMeteorite(M)) {
					M.setCollision(true);
					missiles.remove(i);
				}
			}
		}

 
	public void deplacermissile() throws SlickException {
		for (Missile m : missiles)
			m.deplacer(g, delta);
	}

	public void affichermissile() throws SlickException {
		for (Missile m : missiles)
			m.afficher(g);
	}

	public void retirer() {
		for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).getY() < 1 ||missiles.get(i).getX() < 1||missiles.get(i).getX() > 600 ) {
				missiles.remove(i);
			}
		}

	}

	// -----------------Vérification des mouvements-------------------------------//
		public boolean peutMonter() {
			return !(this.y < 2);
		}
		public boolean peutDescendre() {
			return !(this.y > 510);
		}
		public boolean peutGauche() {
			return !(this.x < 2);
		}
		public boolean peutDroite() {
			return !(this.x > 540);
		}

		// -----------------Application des mouvements-------------------------------//
		public void Monter() {
			if (peutMonter()) {
				this.y -= v * delta / 100f;
			}
		}
		public void Descendre() {
			if (peutDescendre()) {
				this.y += v * delta / 100f;
			}
		}
		public void Gauche() {
			if (peutGauche()) {
				this.x -= v * delta / 100f;
			}
		}
		public void Droite() {
			if (peutDroite()) {
				this.x += v * delta / 100f;
			}
		}

		
	//Affichage de l'HighScore //  le score  // la vie 
	public void affichage(Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("HighScore ", 500, 80);
		g.drawString("" + HighScore, 530, 110);
		g.setColor(Color.yellow);
		g.drawString("score:", 200, 25);
		g.drawString("" + score, 275, 25);
		g.drawImage(new Image("img/pause.png"), 520, 10);
		
		g.drawImage(new Image("img/logoCoin.png"), 20, 60);
		g.setColor(Color.orange);
		g.drawString("" + Coin, 60, 67);
		
	 
		if(vie>=6)
			g.drawImage(new Image("img/vie1.png"), 0, 0);
		
		if(vie==5)
			g.drawImage(new Image("img/vie2.png"), 0, 0);
		
		if(vie==4)
			g.drawImage(new Image("img/vie3.png"), 0, 0);
		
		if(vie==3)
			g.drawImage(new Image("img/vie4.png"), 0, 0);
		
		if(vie==2)
			g.drawImage(new Image("img/vie5.png"), 0, 0);
		
		if(vie==1)
			g.drawImage(new Image("img/vie6.png"), 0, 0);
			
	}

	// -----------------Getters-------------------------------//
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getVie() {
		return vie;
	}

	public int getBoostScore() {
		return BoostScore;
	}

	public int getV() {
		return v;
	}

	public int getDelta() {
		return delta;
	}

	public static int getHighScore() {
		return HighScore;
	}

	public int getScore() {
		return score;
	}

	public int getCoin() {
		return Coin;
	}

	
	public boolean isTirer() {
		return tirer;
	}

	
	public int getBoostshoot() {
		return Boostshoot;
	}

	
	public boolean isCollision() {
		return collision;
	}



	// -----------------Setters-------------------------------//
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVie(int vie) {
		if(vie<=6)
		{
			this.vie += vie;
		}
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public void setGraphic(Graphics g) {
		this.g = g;
	}

	public void setBoostScore(int boostScore) {
		BoostScore += boostScore;
	}

	public void setV(int v) {
		this.v += v;
	}

	public static void setHighScore(int highScore) {
		HighScore = highScore;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setCoin(int coin) {
		Coin += coin;
	}
	
	public void setTirer(boolean tirer) {
		this.tirer = tirer;
	}
	public void setBoostshoot(int boostshoot) {
		Boostshoot += boostshoot;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}