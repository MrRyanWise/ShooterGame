package Ecran;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
////------------------------------Propriétés---------------------------------////

	private Image[] background2 = new Image[41];
	private Animation animateBg;
	

////-------------------------Constructeur--------------------------------////
	public Background() {
	}

////----------------------------Méthodes--------------------------------////			
	public void init() throws SlickException {
			for (int i = 0; i < 41; i++) {
				String text = "";
				if (i < 10)
					text = "background2/frame_apngframe0" + i + ".png";
				else
					text = "background2/frame_apngframe" + i + ".png";

				background2[i] = new Image(text);
			}

				animateBg = new Animation(background2, 100);

	}

	public void render() {
		animateBg.draw(0, 0);
	}

	public void update(int delta) {
		animateBg.update(delta);
	}

}