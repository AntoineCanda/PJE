package model.classifieur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tools.BaseTweet;
import tools.Note;
import tools.Tweet;

public class KNN extends ClassifieurBase {

	private int nbVoisin;

	public KNN(BaseTweet base, int nbVoisin) {
		super(base);
		this.nbVoisin = nbVoisin;
	}

	/**
	 * methode qui calcule la distance entre deux tweets en utilisant la . la
	 * methode utilise est le nombre total de mot des deux tweets moins le
	 * nombre de mots que les tweets ont en communs. On divise ce resultat par
	 * le nombre total de mots. Plus la distance sera proche de 0.5 plus les
	 * tweets seront proche (Deux tweets de taille n egaux auront en distance :
	 * (2*n-n)/(2*n) = n/2n = 1/2).
	 * 
	 * @param msgTweet1
	 * @param msgTweet2
	 * @return un flottant representant la distance entre deux mots.
	 */
	public float distance(String msgTweet1, String msgTweet2) {
		List<String> liste1 = Arrays.asList(msgTweet1.split(" "));
		List<String> liste2 = Arrays.asList(msgTweet2.split(" "));

		int NombreMot = liste1.size() + liste2.size();
		int MotCommun = 0;

		for (String mot : liste1) {
			if (liste2.contains(mot)) {
				MotCommun++;
			}
		}

		return (NombreMot - MotCommun) / NombreMot;
	}

	class CoupleDistanceTweet {
		/**
		 * Classe definissant le couple Distance/Tweet qui pour un tweet
		 * attribue la distance calcule avec la methode distance
		 */
		private float distance;
		private Tweet tweet;

		public CoupleDistanceTweet(float distance, Tweet tweet) {
			this.distance = distance;
			this.tweet = tweet;
		}

		/**
		 * methode retournant la distance du couple
		 * 
		 * @return la distance en float
		 */
		public float getDistance() {
			return this.distance;
		}

		/**
		 * methode retournant le tweet du couple
		 * 
		 * @return le tweet
		 */
		public Tweet getTweet() {
			return this.tweet;
		}
	}

	/**
	 * methode classifie implemente avec l'algorithme KNN fournie.
	 */
	public Note classifie(String message) {
		// TODO Auto-generated method stub
		int voisin = this.nbVoisin;
		List<Tweet> tweets = new ArrayList<Tweet>(this.base.getTweets());
		CoupleDistanceTweet[] voisins = new CoupleDistanceTweet[voisin];
		int indiceMax = 0;

		for (int i = 0; i < voisin; i++) {
			Tweet tweet = tweets.get(i);
			voisins[i] = new CoupleDistanceTweet(this.distance(message, tweet.getMessage()), tweet);
			if (voisins[i].getDistance() > voisins[indiceMax].getDistance()) {
				indiceMax = i;
			}
		}

		for (int i = voisin; i < tweets.size(); i++) {
			Tweet tweet = tweets.get(i);
			float distance = this.distance(message, tweet.getMessage());

			if (distance < voisins[indiceMax].getDistance()) {
				voisins[indiceMax] = new CoupleDistanceTweet(distance, tweet);

				for (int k = 0; k < voisins.length; k++) {
					if (voisins[k].getDistance() > voisins[indiceMax].getDistance()) {
						indiceMax = k;
					}
				}
			}
		}

		int cptPositif = 0;
		int cptNegatif = 0;
		int cptNeutre = 0;

		for (int i = 0; i < voisin; i++) {
			Note note = voisins[i].getTweet().getNote();

			if (note == Note.POSITIF) {
				cptPositif++;
			} else if (note == Note.NEGATIF) {
				cptNegatif++;
			} else {
				cptNeutre++;
			}
		}

		if ((cptNeutre >= cptPositif) && (cptNeutre >= cptNegatif)) {
			return Note.NEUTRE;
		} else if (cptPositif > cptNegatif) {
			return Note.POSITIF;
		} else {
			return Note.NEGATIF;
		}
	}

}
