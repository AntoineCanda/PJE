package model.classifieur;

import tools.BaseTweet;

/**
 * Classe abstraite qui etend la classe abstraite Classificateur On y ajoute la
 * base d'apprentissage dont on aura besoin pour implementer d'autres methodes.
 * 
 * @author canda
 *
 */
public abstract class ClassifieurBase extends Classifieur {

	protected BaseTweet base;

	public ClassifieurBase(BaseTweet base) {
		this.base = base;
	}

	/**
	 * Methode permettant eventuellement de changer de base d'apprentissage
	 * 
	 * @param newBase
	 */
	public void setBase(BaseTweet newBase) {
		this.base = newBase;
	}

}
