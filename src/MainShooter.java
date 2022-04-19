import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import Ecran.Shooter;

public class MainShooter {
 
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Shooter("Shooter Game"));
		app.setShowFPS(false);
		//app.setTargetFrameRate(120);
		app.setDisplayMode(600, 600, false);
		app.setAlwaysRender(true);
		app.start();
		// app.setIcon("img/coin.png");
	} 

}
      