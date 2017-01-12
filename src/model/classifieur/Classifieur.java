package model.classifieur;

import tools.Note;

/**
 * Classe abstraite de base des classifieurs elle contient la methode classifie
 * qui va attribuer une note a un tweet
 * 
 * @author canda
 *
 */
public abstract class Classifieur {

	/**
	 * Methode abstraite permettant de noter un tweet
	 * 
	 * @param message
	 * @return
	 */
	public abstract Note classifie(String message);

}
