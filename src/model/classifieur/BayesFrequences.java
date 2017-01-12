package model.classifieur;

import java.util.List;

import tools.BaseTweet;
import tools.Note;

/**
 * La classe represente la classification bayesienne en tenant compte de la
 * frequence
 * 
 * @author antoine
 *
 */
public class BayesFrequences extends BayesClassifieur {

	public BayesFrequences(BaseTweet base, Boolean simplifie, Degree degree) {
		super(base, simplifie, degree);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcule le nombre d'occurrence d'un NGramme dans un tweet.
	 * 
	 * @param ngramme
	 * @param message
	 * @return le nombre d'occurrence du ngramme dans le tweet
	 */
	private int nbOccurence(NGramme ngramme, String message) {
		int cpt = 0;

		for (NGramme ng : NGramme.ConstruitNGramme(ngramme.getTailleNGramme(), message)) {
			if (ng.equals(ngramme)) {
				cpt++;
			}
		}
		return cpt;
	}

	public double probaTweetNote(Note note, String message) {
		// TODO Auto-generated method stub
		double resultat = 1;
		List<NGramme> ngListe = this.getListNGramme(message);

		for (NGramme ng : ngListe) {
			int occurence = this.nbOccurence(ng, message);
			resultat *= Math.pow(this.probaNGrammeSelonNote(ng, note), occurence);
		}
		return resultat;
	}

}
