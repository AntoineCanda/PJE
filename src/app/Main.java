package app;

import controller.Controller;

import model.RechercheTwitter;
import view.View;

/**
 * Fichier contenant le main de l'application
 * 
 * @author canda
 *
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Controller controler = new Controller();
		RechercheTwitter twitter = new RechercheTwitter();
		View view = new View(twitter, controler);
		view.setVisible(true);

	}

}
