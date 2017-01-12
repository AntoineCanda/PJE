package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe qui represente la classe et les methodes de la base d'apprentissage
 * 
 * @author canda
 *
 */
public class BaseTweet {

	private Map<Integer, Tweet> base;
	private String path;

	public BaseTweet(String path) {
		this.base = new HashMap<Integer, Tweet>();
		this.path = path;
		this.readCSVFile();
	}

	public BaseTweet(List<Tweet> liste) {
		this.base = new HashMap<Integer, Tweet>();
		this.path = "";
		for (Tweet tweet : liste) {
			this.addTweet(tweet);
		}
	}

	/**
	 * Methode retournant la collection de tweet de la base
	 * 
	 * @return la collection de tweet de la base
	 */
	public Collection<Tweet> getTweets() {
		return this.base.values();
	}

	/**
	 * methode qui lit le fichier CSV de la base de tweet afin de creer la base
	 */
	public void readCSVFile() {
		File file = new File(this.path);
		if (file.exists() && !file.isDirectory()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line = "";

				while ((line = br.readLine()) != null) {
					String[] elem = line.split(";");

					Tweet tweet = new Tweet(Long.parseLong(elem[0]), elem[1],
							elem[2].substring(1, elem[2].length() - 1).trim(), /*new Date(Long.parseLong(elem[3]))*/new Date(),
							elem[4], Note.getNoteByValue(Integer.parseInt(elem[5])), Boolean.parseBoolean(elem[6]));

					this.base.put(tweet.hashCode(), tweet);
				}

				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error: file " + path + " not found");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Error: Long cast failed");
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Methode qui ajoute un tweet a la base
	 * 
	 * @param tweet
	 */
	public void addTweet(Tweet tweet) {
		this.base.put(tweet.hashCode(), tweet);
	}

	/**
	 * Methode qui retire un tweet de la base
	 * 
	 * @param tweet
	 */
	public void removeTweet(Tweet tweet) {
		this.base.remove(tweet);
	}

	/**
	 * Methode qui vide la base
	 */
	public void clearBase() {
		this.base.clear();
	}

	/**
	 * Retourne la map representant la base
	 * 
	 * @return map<interger,tweet>
	 */
	public Map<Integer, Tweet> getBase() {
		return this.base;
	}
}
