package model.classifieur;

import java.util.ArrayList;
import java.util.List;

import tools.BaseTweet;
import tools.Note;
import tools.Tweet;

public abstract class BayesClassifieur extends ClassifieurBase {
	/**
	 * Taille minimale accepte d'un mot quand on utilise la version simplifiee
	 * de l'aglorithme
	 */
	protected final int TAILLE_MOT_MIN = 3;

	/**
	 * booleen indiquant si on a initialise les listes et ngramme ou pas pour la
	 * base actuelle
	 */
	protected boolean initialise;

	/**
	 * Indique si on utilise la version simplifie ou non de l'algorithme, c'est
	 * a dire que l'on applique celle ci uniquement au mot qui ont plus de 3
	 * lettres.
	 */
	protected Boolean simplifie;

	/**
	 * Le degree a utilise (pour NGramme : Uni ? Bi ? Uni+Bi ?)
	 */
	protected Degree degree;

	/**
	 * Nombre total de tweet present dans la base
	 */
	protected int totalTweet;

	/**
	 * Liste des Tweets de la base classe selon leur note
	 */
	protected List<Tweet> listePositif;
	protected List<Tweet> listeNeutre;
	protected List<Tweet> listeNegatif;

	/*
	 * Nombre de mots present dans les tweets positifs, neutre et negatifs.
	 *
	 * protected int nombreMotPositif; protected int nombreMotNegatif; protected
	 * int nombreMotNeutre;
	 */

	/**
	 * Nombre de ngramme present dans les tweets positifs, neutre et negatifs.
	 */
	protected int nombreNGrammePositif;
	protected int nombreNGrammeNegatif;
	protected int nombreNGrammeNeutre;

	public BayesClassifieur(BaseTweet base, Boolean simplifie, Degree degree) {
		super(base);
		this.initialise = false;
		this.totalTweet = 0;
		this.listePositif = new ArrayList<Tweet>();
		this.listeNeutre = new ArrayList<Tweet>();
		this.listeNegatif = new ArrayList<Tweet>();

		this.degree = degree;
		this.simplifie = simplifie;

		this.nombreNGrammePositif = 0;
		this.nombreNGrammeNeutre = 0;
		this.nombreNGrammeNegatif = 0;
		// TODO Auto-generated constructor stub

	}

	/**
	 * initialise les liste de tweet positif neutre et negatif a partir de la
	 * base de tweet
	 */
	public void initialiseListe() {
		for (Tweet tweet : this.base.getTweets()) {
			if (tweet.getNote() == Note.POSITIF) {
				this.listePositif.add(tweet);
			} else if (tweet.getNote() == Note.NEUTRE) {
				this.listeNeutre.add(tweet);
			} else {
				this.listeNegatif.add(tweet);
			}
		}
	}

	/**
	 * Retourne la probabilite de rencontrer un tweet de la note note dans la
	 * base d'apprentissage
	 * 
	 * @param note
	 * @return la probabilite d'apparition de la note dans la base
	 */
	public double probaNote(Note note) {
		double cpt = 0;

		switch (note) {
		case POSITIF:
			cpt = this.listePositif.size();
			break;
		case NEUTRE:
			cpt = this.listeNeutre.size();
			break;
		case NEGATIF:
			cpt = this.listeNegatif.size();
			break;
		default:
			System.out.print("La note passe en parametre n'existe pas");
			cpt = 0;
			break;
		}

		return cpt / (float) this.totalTweet;
	}

	/**
	 * Initialise le nombre de NGramme pour chaque Note
	 * 
	 * @param liste
	 * @return
	 */
	private int initialiseNbreNGrammeParNote(List<Tweet> liste) {
		int cptNgramme = 0;
		for (Tweet tweet : liste) {
			String message = tweet.getMessage().trim();
			List<NGramme> listeNgramme = this.getListNGramme(message);
			cptNgramme += listeNgramme.size();
		}
		return cptNgramme;
	}

	/**
	 * Initialise les compteurs de NGramme de chaque classe
	 */
	protected void initialiseCompteurNGramme() {
		this.nombreNGrammePositif = this.initialiseNbreNGrammeParNote(this.listePositif);
		this.nombreNGrammeNeutre = this.initialiseNbreNGrammeParNote(this.listeNeutre);
		this.nombreNGrammeNegatif = this.initialiseNbreNGrammeParNote(this.listeNegatif);

	}

	/**
	 * Donne la liste des NGrammes que l'on va devoir etudier dans la
	 * classification. On utilise pour cela le degree souhaite lors de
	 * l'execution de la methode
	 * 
	 * @param message
	 * @return la liste des NGrammes obtenu via le message du tweet
	 */
	protected List<NGramme> getListNGramme(String message) {
		List<NGramme> liste = new ArrayList<NGramme>();

		switch (this.degree) {
		case UNI:
			liste.addAll(NGramme.ConstruitNGramme(1, message));
			break;
		case BI:
			liste.addAll(NGramme.ConstruitNGramme(2, message));
			break;
		case UNIBI:
			liste.addAll(NGramme.ConstruitNGramme(1, message));
			liste.addAll(NGramme.ConstruitNGramme(2, message));
		}

		return liste;
	}


	/**
	 * Compte le nombre d'occurence du ngramme dans les ng des tweets de la
	 * classe note
	 * 
	 * @param ng
	 * @param note
	 * @param liste
	 * @return le nombre d'occurence
	 */
	protected int nombreOccurenceNGrammeSelonNote(NGramme ng, List<Tweet> liste) {
		int cpt = 0;

		for (Tweet tweet : liste) {
			for (NGramme ngramme : NGramme.ConstruitNGramme(ng.getTailleNGramme(), tweet.getMessage())) {
				if (ngramme.equals(ng)) {
					cpt++;
				}
			}
		}
		return cpt;
	}

	/**
	 * Compte le nombre d'occurence du ng dans la liste des ng de tout les
	 * tweets de la liste de tweet selon la note
	 * 
	 * @param ng
	 * @param note
	 * @return le nombre d'occurence
	 */
	protected int nombreOccurenceNGrammeSelonNote(NGramme ng, Note note) {
		int cpt = 0;
		switch (note) {
		case POSITIF:
			cpt = this.nombreOccurenceNGrammeSelonNote(ng, this.listePositif);
			break;
		case NEUTRE:
			cpt = this.nombreOccurenceNGrammeSelonNote(ng, this.listeNeutre);
			break;
		case NEGATIF:
			cpt = this.nombreOccurenceNGrammeSelonNote(ng, this.listeNegatif);
			break;
		default:
			System.out.print("La note passe en parametre n'existe pas");
			cpt = 0;
			break;
		}

		return cpt;
	}

	/**
	 * Methode qui simplifie le message du tweet si on utilise la classification
	 * simplifie c'est a dire sans les mots <3 lettres
	 * 
	 * @param message
	 * @return le message simplifie
	 */
	protected String simplifierTweet(String message) {
		StringBuilder messageSimplifie = new StringBuilder();
		for (String mot : message.split(" ")) {
			if (mot.length() > this.TAILLE_MOT_MIN) {
				messageSimplifie.append(mot);
				messageSimplifie.append(" ");
			}
		}
		return messageSimplifie.toString().trim();
	}

	/**
	 * Retourne la probabilite que le ngramme soit de la note note
	 * 
	 * @param ng
	 * @param note
	 * @return la probabilite du ngramme selon la note
	 */
	public double probaNGrammeSelonNote(NGramme ng, Note note) {
		double nombreTotalNGrammeNote = 0;
		switch (note) {
		case POSITIF:
			nombreTotalNGrammeNote = (double) this.nombreNGrammePositif;
			break;
		case NEUTRE:
			nombreTotalNGrammeNote = (double) this.nombreNGrammeNeutre;
			break;
		case NEGATIF:
			nombreTotalNGrammeNote = (double) this.nombreNGrammeNegatif;
			break;
		default:
			String msg = "La note passe en parametre n'existe pas";
			throw new IllegalArgumentException(msg);
		}

		return this.nombreOccurenceNGrammeSelonNote(ng, note) + 1 / (nombreTotalNGrammeNote + (this.nombreNGrammeNegatif + this.nombreNGrammeNeutre + this.nombreNGrammePositif));
	}

	/**
	 * Methode calculant la probabilite qu'un tweet ait la meme note que la note
	 * passe en parametre.
	 * 
	 * @param note
	 * @param message
	 * @return un flottant representant la probabilite calculee
	 */
	public abstract double probaTweetNote(Note note, String message);

	/**
	 * Calcule la note attribue a un tweet
	 */

	public Note classifie(String message) {
		// TODO Auto-generated method stub

		if (!this.initialise) {
			this.totalTweet = this.base.getBase().size();
			this.initialiseListe();
			this.initialiseCompteurNGramme();
			this.initialise = true;
		}

		if (this.simplifie) {
			message = this.simplifierTweet(message);
		}

		double probaNegative = this.probaTweetNote(Note.NEGATIF, message);
		double probaPositive = this.probaTweetNote(Note.POSITIF, message);
		double probaNeutre = this.probaTweetNote(Note.NEUTRE, message);

		if ((probaNeutre > probaPositive) && (probaNeutre > probaNegative)) {
			return Note.NEUTRE;
		} else if (probaPositive > probaNegative) {
			return Note.POSITIF;
		} else {
			return Note.NEGATIF;
		}
	}

}
