package Element;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sons {

////------------------------------Propriétés---------------------------------////
	public Sound son2;
	public Sound son1;
	public Sound son3;
	public Music music;

////---------------------------Constructeur---------------------------------////
	public Sons() throws SlickException {

		music = new Music("son/Shootsong.ogg");
		music.loop();
		son1 = new Sound("son/shoot.wav");
		son2 = new Sound("son/explosion.wav");
		son3 = new Sound("son/sonpiece.wav");
	}

	public Sons(int a) throws SlickException {
		if(a==2)
		son2 = new Sound("son/explosion.wav");
	}
}
