package model.classifieur;

import java.util.List;

import tools.BaseTweet;
import tools.Note;

/**
 * Classe represente la classification bayesienne par presence
 * 
 * @author antoine
 *
 */
public class BayesPresence extends BayesClassifieur {

	public BayesPresence(BaseTweet base, Boolean simplifie, Degree degree) {
		super(base, simplifie, degree);
		// TODO Auto-generated constructor stub
	}

	public double probaTweetNote(Note note, String message) {
		// TODO Auto-generated method stub
		double resultat = 1;
		List<NGramme> ngListe = this.getListNGramme(message);

		for (NGramme ng : ngListe) {
			resultat *= this.probaNGrammeSelonNote(ng, note);
		}
		return resultat * this.probaNote(note);
	}

}
