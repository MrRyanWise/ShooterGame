package Ecran;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import Acteurs.Ennemi;
import Acteurs.Joueur;
import Element.Bonus;
import Element.Niveau;
import Element.Sons;
import Element.meteorite;

//passe finder

public class ElementContainer {
	/// ------------Proprietes-----------//
	private ArrayList<Ennemi> Ennemis;// tableau d'ennemis
	private ArrayList<meteorite> meteorites;// tableau de meteorites
	private ArrayList<Bonus> bonus;// tableau de meteorites
	public Niveau niveau;
	private Background background;
	private Sons son2,son3;

	/// --------Initalisation des variables -----------//
	public void EnnemiContainer() {
	}

	public void init() throws SlickException {
		niveau = new Niveau();
		bonus = new ArrayList<Bonus>();
		bonus.add(new Bonus());

		Ennemis = new ArrayList<Ennemi>();
		Ennemis.add(new Ennemi());

		meteorites = new ArrayList<meteorite>();
		meteorites.add(new meteorite());
		meteorites.add(new meteorite());

		background = new Background();
		background.init();

		son2 = new Sons(2);
		son3 = new Sons();
	}

///--------methode dessiner des éléments -----------//
	public void render(Graphics g, Joueur j1) throws SlickException {
		background.render();

		for (Ennemi En : Ennemis) {
			if (!En.isCollision())
				En.setGraphic(g);
		}

		g.setColor(Color.orange);
		g.drawString("Niveau : ", 20, 90);
		g.drawString("" + niveau.getNiveau(), 100, 90);

		// ---------Dessiner les ennemis---------//
		for (Ennemi En : Ennemis) {
			if (!(En.isCollision())) {
				En.dessiner(g);
				En.dessinerVie(g);
			} else
				En.setDisparait(false);

			En.affichermissileE(g);
		}

		// ---------Dessiner les meteorites---------//
		for (meteorite mets : meteorites)
			mets.dessiner(g);

		// ---------Dessiner les Bonus---------//
		for (Bonus bo : bonus)
			bo.dessiner(g);
		niveau.render(g, j1);

	}

///--------mise a jour des elements -----------//	
	public void update(int delta, Joueur j1) throws SlickException {

		background.update(delta);
		// update du niveau
		niveau.setDelta(delta);
		niveau.update(j1);

		// ---------deplacer missilesE---------//
		for (Ennemi En : Ennemis)
			En.deplacermissileE(delta);

		// ---------deplacer bonus---------//
		for (Bonus bo : bonus)
			bo.deplacer(delta);

		// ---------Destruction de l'ennemi-----------------//
		for (int i = 0; i < Ennemis.size(); i++) {
			j1.destruction(Ennemis.get(i));
		}
		// ---------Destruction de la meteorite-----------------//

		for (int i = 0; i < meteorites.size(); i++) {
			j1.destructionMeteorite(meteorites.get(i));
		}

		// ---------ajouter l'ennemi-----------------//
		for (int i = 0; i < Ennemis.size(); i++) {

			//// ------------Ajout des ennemis-------------/
			if (Ennemis.size() < niveau.getNiveau() + 1)
				Ennemis.add(new Ennemi());


		}
		// ---------destruction de l'ennemi-----------------//
		for (int i = 0; i < Ennemis.size(); i++) {
			if (Ennemis.get(i).isCollision() && Ennemis.get(i).isDisparait()) {
				if (Ennemis.get(i).getVie() != 0) {
					Ennemis.get(i).setCollision(false);
				}

				if (Ennemis.get(i).getVie() == 0)
					bonus.add(new Bonus(Ennemis.get(i).getX(), Ennemis.get(i).getY(), 3));
					son2.son2.play();
			}
		}

		// ---------retirer l'ennemi-----------------//
		for (int i = 0; i < Ennemis.size(); i++) {
			if (Ennemis.get(i).getX() < 0 || Ennemis.get(i).getY() > 600 || Ennemis.get(i).getX() > 600) {
				Ennemis.remove(i);
			}
		}

		// ---------Deplacer l'ennemi-----------------//
		for (Ennemi En : Ennemis)
			En.deplacer(delta);

		// ---------Destruction du joueur par l'ennemi-----------------//
		for (int i = 0; i < Ennemis.size(); i++) {
			Ennemis.get(i).destruction(j1);
		}

		// ---------Deplacer les meteorites---------//
		for (meteorite mets : meteorites)
			mets.deplacer(delta);

		for (int i = 0; i < meteorites.size(); i++) {
			if (meteorites.get(i).testCollision(j1)) {
				meteorites.get(i).setCollision(true);
				meteorites.remove(meteorites.get(i));
				j1.setVie(-1);
			}
		}

		// ---------retirer meteorites-----------------//
		for (int i = 0; i < meteorites.size(); i++) {
			if (meteorites.get(i).getY() > 600 || meteorites.get(i).isCollision()) {
				meteorites.remove(i);
				son2.son2.play();

				// ----Ajoute 1 meteorites-///
				meteorites.add(new meteorite());

				 
			}
		}

		for (int i = 0; i < bonus.size(); i++) {
			bonus.get(i).testCollision(j1);
			
			if (bonus.get(i).isCollision() || bonus.get(i).getY() > 600)
				{
				bonus.remove(i);
				son3.son3.play();
				}
			
			
			if (bonus.size() < 2)
				bonus.add(new Bonus());
			
			
		}

		for (int i = 0; i < bonus.size(); i++) {
			if (bonus.get(i).getX() < 2) {
				bonus.remove(i);
				bonus.add(new Bonus());
			}

		}
	}

	public void reset() {
		bonus.clear();
		bonus.add(new Bonus());
		Ennemis.clear();
		Ennemis.add(new Ennemi());
		meteorites.clear();
		meteorites.add(new meteorite());
		niveau.setNiveau(1);
	}
}
