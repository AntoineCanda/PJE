package model;

/**
 * Classe faisant la jointure avec l'API Twitter pour effectuer la recherche de tweet
 */
import java.util.ArrayList;
import java.util.List;

import tools.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class RechercheTwitter {
	private final static String consumerKey = "99kfHajQVQt2Vv4EsX7VKhljE";
	private final static String consumerSecret = "gFJIPUFdKPkNEci79yOOYHA9W7I0fTc2xS6tMrqXKjyDujkPRI";
	private final static String accessToken = "2981250321-ofDzaxNNTJTijnvYIyTXfFdobHAUD5Q7JWUcIgU";
	private final static String accessTokenSecret = "nScDdZW1nb6lD008vt9bQjt4aMDY1TsWDZweSYM3UY2Jb";

	private Twitter twitter;
	private int nbTweets;

	public RechercheTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);

		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitter = tf.getInstance();

		this.nbTweets = 50;
	}

	/**
	 * Retourne la limite actuelle en nombre de tweet obtenu par une recherche
	 * 
	 * @return nombre de tweet maximal obtenu par une recherche
	 */
	public int getNbTweets() {
		return this.nbTweets;
	}

	/**
	 * Change le nombre de tweet maximale que l'on peut avoir avec une recherche
	 * a partir du numero de bouton radio.
	 * 
	 * @param nb
	 */
	public void setNbTweets(int nb) {
		int cpt = 0;
		switch (nb) {
		case 0:
			cpt = 25;
			break;
		case 1:
			cpt = 50;
			break;
		case 2:
			cpt = 75;
			break;
		case 3:
			cpt = 100;
			break;
		case 4:
			cpt = 250;
			break;
		}
		this.nbTweets = cpt;
	}

	/**
	 * Methode qui effectue la recherche de tweet sur twitter avec en mot clef
	 * le String search On recherche des tweets en francais et on applique une
	 * limite au nombre de tweets pouvant être obtenue. De base on obtient des
	 * Status, on crée à la volée pour chaque Status un tweet afin de gagner
	 * en abstraction pour les autres étapes.
	 * 
	 * @param search
	 * @return la liste des tweets obtenues
	 */
	public List<Tweet> search(String search) {
		Query query = new Query(search);
		QueryResult result = null;
		List<Tweet> liste = new ArrayList<Tweet>();

		query.setLang("fr");
		query.setCount(this.nbTweets);

		try {
			result = this.twitter.search(query);

			while (result.hasNext() && (liste.size() < this.nbTweets)) {
				List<Status> list = result.getTweets();
				int i = 0;

				while (i < list.size() && liste.size() < this.nbTweets) {
					Status status = list.get(i);
					if (!status.isRetweet()) {
						Tweet tweet = new Tweet(status, search);
						liste.add(tweet);
					}
					i++;
				}

				query = result.nextQuery();
				result = this.twitter.search(query);
			}

		} catch (TwitterException e) {
			System.out.print("Erreur Twitter");
			e.printStackTrace();
			System.exit(0);
		}
		return liste;
	}

}
