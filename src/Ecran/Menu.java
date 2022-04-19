package Ecran;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import Acteurs.Joueur;

public class Menu {
	/*
	 * * SCRIPT UN hero a la recherche de sa fiancee prise en otage niveau 1 une
	 * image du boss qui le menace et part niveau 2 image du boss et sa fiancee donc
	 * le rythme augmente les ennemis aussi niveau 3 affrontement du boss faire
	 * jouer avec la souries aussi et la possibilite d'ajouter un deuxieme joueur
	 * avec son propre score
	 *
	 * 
	 */
////------------------------------Propriétés---------------------------------////

	private Image background2 = null; // background menu
	private Image background3 = null; // background game over
	private Image background4 = null; // logo game over
	private Image background5 = null; // image du parchemin
	private Image background6 = null; // image de la victoire
	private boolean choix;
	private boolean pause;

////-------------------------Constructeur--------------------------------////

	public Menu() throws SlickException {
		background2 = new Image("img/BackgroundMenu.jpg");
		background3 = new Image("img/BackgroundGameover.jpg");
		background4 = new Image("img/gameover.png");
		background5 = new Image("img/parchemin.png");
		background6 = new Image("img/victoire.png");
		choix = false;
		pause = false;
	}

//// ----------------------------Méthodes--------------------------------////

	public void dessinerMenu(Graphics g) throws SlickException {
		background2.draw(0, 0, 600, 600);

		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 430, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Jouer", 280, 440);

		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 500, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Sortir", 280, 510);

	}
////-----------------------Test des clics ------------------------------////

	// -Test des clics pour Joueur
	public boolean clic(float x, float y) {
		return (x >= 200 && x <= 200 + 200 && y >= 430 && y <= 450 + 40);
	}

	// -Test des clics pour sortir
	public boolean clic2(float x, float y) {
		return (x >= 200 && x <= 200 + 200 && y >= 500 && y <= 550 + 40);
	}

////------------------------choix pour rejouer--------------------------------////

	public void dessinerchoixGameOver(Graphics g, Joueur j, ElementContainer EC) throws SlickException {
		background3.draw(0, 0, 600, 600);
		background4.draw(200, 100, 300, 200);
		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 430, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Rejouer", 280, 440);

		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 500, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Sortir", 280, 510);

		background5.draw(0, 260, 200, 300);
		g.setColor(Color.yellow);
		g.drawString("HighScore : ", 30, 310);
		g.setColor(Color.white);
		g.drawString("" + Joueur.getHighScore(), 140, 310);
		g.setColor(Color.yellow);
		g.drawString("Score : ", 30, 360);
		g.setColor(Color.white);
		g.drawString(" " + j.getScore(), 140, 360);
		
		g.setColor(Color.yellow);
		g.drawString("Pieces : ", 30, 400);
		g.setColor(Color.white);
		g.drawString(" " + j.getCoin(), 140, 400);
		
		g.setColor(Color.yellow);
		g.drawString("Niveau: ", 30, 440);
		g.setColor(Color.white);
		g.drawString(" " + EC.niveau.getNiveau() , 140, 440);
	}

////------------------------Menu de Victoire--------------------------------////

	public void dessineVictory(Graphics g, Joueur j, ElementContainer EC) throws SlickException {
		background3.draw(0, 0, 600, 600);
		background6.draw(200, 100, 300, 200);
		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 430, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Rejouer", 280, 440);

		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 500, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Sortir", 280, 510);

		background5.draw(0, 260, 200, 300);
		g.setColor(Color.yellow);
		g.drawString("HighScore : ", 30, 310);
		g.setColor(Color.white);
		g.drawString("" + Joueur.getHighScore(), 140, 310);
		g.setColor(Color.yellow);
		g.drawString("Score : ", 30, 360);
		g.setColor(Color.white);
		g.drawString(" " + j.getScore(), 140, 360);
		
		g.setColor(Color.yellow);
		g.drawString("Pieces : ", 30, 400);
		g.setColor(Color.white);
		g.drawString(" " + j.getCoin(), 140, 400);
		
		g.setColor(Color.yellow);
		g.drawString("Niveau: ", 30, 440);
		g.setColor(Color.white);
		g.drawString(" " + EC.niveau.getNiveau() , 140, 440);
	}
	
	
	public boolean clicreJouer(float x, float y) {
		return (x >= 350 && x <= 350 + 100 && y >= 450 && y <= 455 + 50);
	}

	public boolean clicSortir(float x, float y) {
		return (x >= 350 && x <= 350 + 100 && y >= 450 && y <= 455 + 50);
	}

////----------------------Menu Pause-------------------------------////

	public void DessinerMenuPause(Graphics g) throws SlickException {
		Font font = new Font("Verdana",Font.BOLD,50);
		TrueTypeFont ttf = new TrueTypeFont(font,true);
		//g.setColor(Color.white);
		ttf.drawString(200,200,"PAUSE",Color.white);
		
		//g.drawString("PAUSE", 280, 200);
		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 430, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Rejouer", 280, 440);

		g.setColor(new Color(104, 141, 170));
		g.fillRoundRect(200, 500, 200, 40, 6);
		g.setColor(Color.white);
		g.drawString("Sortir", 280, 510);

	}

	public boolean clicpause(float x, float y) {
		return (x >= 520 && x <= 520 + 40 && y >= 10 && y <= 10 + 40);
	}

////-----------------------Getters--------------------------------////

	public boolean isChoix() {
		return choix;
	}
	
	public boolean isPause() {
		return pause;
	}

////-----------------------Setters------------------------------////

	public void setChoix(boolean choix) {
		this.choix = choix;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

}