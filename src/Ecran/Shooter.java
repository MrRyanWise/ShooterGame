package Ecran;

import org.newdawn.slick.BasicGame; 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Acteurs.Joueur;
import Element.Sons;

/*
 * By TANKENG RYAN WISE
 */

public class Shooter extends BasicGame {
////------------------------------Propriétés---------------------------------////	
	private Joueur j1; // Represente le Joueur
	private Menu menu; // Represente le Menu
	private ElementContainer EContainer; // Represente le containeur d'élements
	private boolean clavier = true;
	private Sons son;

	@Override
	public void init(GameContainer gc) throws SlickException {
		menu = new Menu();
		j1 = new Joueur();
		EContainer = new ElementContainer();
		EContainer.init();
		 son = new Sons();

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (!menu.isChoix())
			menu.dessinerMenu(g);
		else if ((j1.getVie() != 0 && menu.isChoix())) {
			// dessin des elements
			EContainer.render(g, j1);

			j1.setGraphic(g);
			j1.affichage(g);

			// ---------Dessiner le joueur 1 ---------//
			if (j1.getVie() < 3) {
				if (j1.getDelta() % 4 == 0)
					j1.dessiner(g);
			} else
				j1.dessiner(g);

			// ---------Afficher les missiles---------//
			if (j1.isTirer()) {
				j1.affichermissile();
			}

			// ------Affichage de la pause------//
			if (menu.isPause())
				menu.DessinerMenuPause(g);
			 
			
		} else if (j1.getVie() == 0) {
			 son.music.stop();
			menu.dessinerchoixGameOver(g, j1, EContainer);
		}
		if (j1.getVie() == 0 && j1.getScore() > Joueur.getHighScore()) {
			menu.dessineVictory(g, j1, EContainer);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input inp = gc.getInput();
		if (inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (menu.clic(inp.getMouseX(), inp.getMouseY())) {
				menu.setChoix(true);
				if (j1.getVie() == 0) {
					if (j1.getScore() > Joueur.getHighScore())
						Joueur.setHighScore(j1.getScore());
					j1 = new Joueur();
					j1.setTirer(false);
					son.music.play();
					EContainer.reset();
					
				}
				if (menu.isPause()) {
					menu.setPause(false);
				}
			}

			if (menu.clicpause(inp.getMouseX(), inp.getMouseY())) {
				menu.setPause(true);
			}

			if (menu.clic2(inp.getMouseX(), inp.getMouseY())) {
				System.exit(0);
			}
		}

		if (menu.isChoix() && j1.getVie() != 0) {

			// ------------Quand le joueur est en vie-----------//
		
			if (!menu.isPause()) {

				EContainer.update(delta, j1);

				j1.setDelta(delta);
				j1.deplacermissile();

				if (clavier) {
					// -----------Pour manipuler le joueur---------//
					if (inp.isKeyDown(Input.KEY_UP))
						j1.Monter();
					if (inp.isKeyDown(Input.KEY_DOWN))
						j1.Descendre();
					if (inp.isKeyDown(Input.KEY_RIGHT))
						j1.Droite();
					if (inp.isKeyDown(Input.KEY_LEFT))
						j1.Gauche();
					
				} else {
					j1.setX(inp.getMouseX());
					j1.setY(inp.getMouseY());
				}
				
				if (inp.isKeyPressed(Input.KEY_SPACE)) {
					j1.tirer();
					j1.setTirer(true);
					son.son1.play();
				}
				// ---choix de la souris ou du clavier---//
				if (inp.isKeyPressed(Input.KEY_C)) {
					clavier = true;
				}
				if (inp.isKeyPressed(Input.KEY_S)) {
					clavier = false;
				}

			}

		}
	}

	// ---------Titre du jeu-------//
	public Shooter(String title) {
		super(title);
	}

}