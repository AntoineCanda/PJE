package model.classifieur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe representant un NGramme, c'est à dire un couple (tailleNGramme,
 * tableau de mot). La taille dans notre application sera 1 ou 2.
 * 
 * @author antoine
 *
 */
public class NGramme {

	/**
	 * Nombre de mots qui compose un N-Gramme
	 */
	private int tailleNGramme;

	/**
	 * Tableau de mot composant le N-Gramme
	 */
	private String[] mots;

	/**
	 * Constructeur d'un NGramme
	 * 
	 * @param taille
	 * @param mots
	 */
	public NGramme(int taille, String[] mots) {
		if (mots.length != taille) {
			throw new IllegalArgumentException("Le N-Gramme doit contenir " + taille + " mots");
		} else {
			this.tailleNGramme = taille;
			this.mots = mots;
		}
	}

	/**
	 * Retourne le tableau de mots du NGramme
	 * 
	 * @return tableau de Mots
	 */
	public String[] getMots() {
		return this.mots;
	}

	/**
	 * retourne la taille du NGramme
	 * 
	 * @return un entier representant la taille du Ngramme
	 */
	public int getTailleNGramme() {
		return this.tailleNGramme;
	}

	/**
	 * Teste l'egalite entre deux Ngrammes (utile pour BayesFrequences
	 * notamment)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		NGramme autre = (NGramme) obj;
		// Taille egale ?
		if (this.getTailleNGramme() != autre.getTailleNGramme()) {
			return false;
		}
		// Tableaux de mots egaux ?
		if (!Arrays.equals(this.getMots(), autre.getMots())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int res = 1;
		res = prime * res + this.getTailleNGramme();
		res = prime * res + Arrays.hashCode(this.getMots());
		return res;
	}

	/**
	 * Construit une liste de NGramme a partir de la taille d'un NGramme et d'un
	 * message
	 * 
	 * @param taille
	 * @param message
	 * @return la liste des NGramme
	 * @throws IllegalArgumentException
	 */
	public static List<NGramme> ConstruitNGramme(int taille, String message) throws IllegalArgumentException {
		String[] mots = message.split(" ");

		if (mots.length < taille) {
			return ConstruitNGramme(taille - 1, message);
		} else {
			List<NGramme> liste = new ArrayList<NGramme>();

			for (int i = 0; i <= mots.length - taille; i++) {
				String[] motsNGramme = new String[taille];

				for (int j = i; j < i + taille; j++) {
					motsNGramme[j - i] = mots[j];
				}
				NGramme nGramme = new NGramme(taille, motsNGramme);
				liste.add(nGramme);
			}

			return liste;
		}
	}
}
