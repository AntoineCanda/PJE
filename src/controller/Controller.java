package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Model;
import model.classifieur.BayesFrequences;
import model.classifieur.BayesPresence;
import model.classifieur.Classifieur;
import model.classifieur.Degree;
import model.classifieur.Dictionnaire;
import model.classifieur.KNN;
import tools.BaseTweet;
import tools.Tweet;

/**
 * Classe representant le controler de l'application.
 * 
 * @author canda
 *
 */
public class Controller {

	protected Model tweetModel;

	private BaseTweet tweetBase;

	private Classifieur[] classificateurs;

	protected Classifieur classificateurActuel;

	private int classifierId;

	public Controller() {
		this.tweetModel = new Model();
		this.tweetBase = new BaseTweet("ressource/BaseTweets.csv");
		
		//this.cleanBase();

		// Tableau contenant les differents classifieurs
		this.classificateurs = new Classifieur[14];

		// Initialisation des classifieurs

		// Dictionnaires avec mots clef
		this.classificateurs[0] = new Dictionnaire("ressource/positive.txt", "ressource/negative.txt");

		// KNN
		this.classificateurs[1] = new KNN(this.tweetBase, 5);

		// Bayes

		// Classificateur selon la presence, simplifie ou pas, avec unigramme,
		// bigramme et uni+bigramme
		this.classificateurs[2] = new BayesPresence(this.tweetBase, false, Degree.UNI);
		this.classificateurs[3] = new BayesPresence(this.tweetBase, true, Degree.UNI);
		this.classificateurs[4] = new BayesPresence(this.tweetBase, false, Degree.BI);
		this.classificateurs[5] = new BayesPresence(this.tweetBase, true, Degree.BI);
		this.classificateurs[6] = new BayesPresence(this.tweetBase, false, Degree.UNIBI);
		this.classificateurs[7] = new BayesPresence(this.tweetBase, true, Degree.UNIBI);

		this.classificateurs[8] = new BayesFrequences(this.tweetBase, false, Degree.UNI);
		this.classificateurs[9] = new BayesFrequences(this.tweetBase, true, Degree.UNI);
		this.classificateurs[10] = new BayesFrequences(this.tweetBase, false, Degree.BI);
		this.classificateurs[11] = new BayesFrequences(this.tweetBase, true, Degree.BI);
		this.classificateurs[12] = new BayesFrequences(this.tweetBase, false, Degree.UNIBI);
		this.classificateurs[13] = new BayesFrequences(this.tweetBase, true, Degree.UNIBI);

		// Arbitrairement je choisis de base le classificateur bayesien avec
		// presence simplifie en unigramme
		this.setClassifieur(3);
	}

	/**
	 * Classe ecrivant les tweets dans le fichier fourni par path en respectant
	 * un format CSV
	 * 
	 * @param path
	 */
	public void writeCSV(String path, List<Tweet> listeTweet) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path, false));

			for (Tweet tweet : listeTweet) {
				String msg = tweet.getMessage().trim();
				StringBuffer tweetText = new StringBuffer(tweet.getId() + ";" + tweet.getUser() + ";");

				if (msg.charAt(0) == '"' && msg.charAt(msg.length() - 1) == '"') {
					tweetText.append(tweet.getMessage());
				} else {
					tweetText.append("\"" + tweet.getMessage() + "\"");
				}

				tweetText.append(";" + tweet.getDate().getTime() + ";" + tweet.getQueryWord() + ";"
						+ tweet.getNote().getValue() + ";" + tweet.getBase());

				out.write(tweetText.toString());
				out.newLine();
			}

			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Methode mettant aï¿½ jour le model, ici la liste des tweets. Le booleen
	 * corresppond a mettre a jour la liste des tweets nettoyes si il vaut true.
	 * 
	 * Methode a ameliorer pour retirer cleaned
	 * 
	 * @param listeTweets
	 * @param cleaned
	 */
	public void updateTableModel(List<Tweet> listeTweets) {
		this.tweetModel.updateTableModel(listeTweets);
	}

	/**
	 * Methode qui retourne le modele actuellement utilise
	 * 
	 * @return le model
	 */
	public Model getModel() {
		return this.tweetModel;
	}

	/**
	 * Methode permettant de nettoyer un tweet (enlever les 'hashtags', les
	 * destinataires et ne garder que le corps du texte avec l'URL), via
	 * l'utilisation de regex
	 * 
	 * @param content
	 *            Le tweet non-nettoye
	 * @return Une chaine de caracteres qui contiendra le message du tweet
	 *         nettoye
	 */
	private String cleanTweet(String tweet) {
		Pattern p;
		Matcher m;
		String line;
	
		String patternURL="(https?://([-\\w\\.]+)+(/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?)";
		String patternArobase="@\\p{ASCII}[^\\p{Space}]*";
		String patternEspaceInsecable=" $|^ ";
		String patternEspace="  |\u00A0";
		String patternGuillement="\"\\s*\"";
		String patternSommes="[-#@$€\n()0-9+&@/%?=~_!:,\\.;\"*><^…]|RT";
		line = tweet.toLowerCase();
		
		p = Pattern.compile(patternEspace);
		m = p.matcher(line);
		StringBuffer sb =new StringBuffer("");
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		line = sb.toString();

		
		p = Pattern.compile(patternURL);
		m = p.matcher(line);

		sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		// SB contient la ligne a conserver sans les @

		line = sb.toString();
		
		
		p = Pattern.compile(patternArobase);
		m = p.matcher(line);

		sb =new StringBuffer("");
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		line = sb.toString();
		
		p = Pattern.compile(patternGuillement);
		m = p.matcher(line);

		sb =new StringBuffer("");
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		line = sb.toString();
		
		p = Pattern.compile(patternSommes);
		m = p.matcher(line);

		sb =new StringBuffer("");
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		line = sb.toString();
		
		p = Pattern.compile(patternEspaceInsecable);
		m = p.matcher(line);

		sb =new StringBuffer("");
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		line = sb.toString();
		
		return sb.toString();
	}
	
	public boolean testEmote(String message){
		List<Pattern> positif = new ArrayList<Pattern>();
		List<Pattern> negatif = new ArrayList<Pattern>();
		
		boolean pos = false;
		boolean neg = false;
		
		positif.add(Pattern.compile("=\\)"));
		positif.add(Pattern.compile(":\\)"));
		positif.add(Pattern.compile("=]"));
		positif.add(Pattern.compile("XD"));
		positif.add(Pattern.compile(";\\)"));
		positif.add(Pattern.compile("xD"));
		positif.add(Pattern.compile("=D"));
		positif.add(Pattern.compile(":D"));
		
		negatif.add(Pattern.compile("=\\("));
		negatif.add(Pattern.compile(":\\("));
		negatif.add(Pattern.compile("='\\("));
		negatif.add(Pattern.compile(":'\\("));
		negatif.add(Pattern.compile("T_T"));
		
		for(Pattern p : positif){
			Matcher m = p.matcher(message);
			if(m.find()){
				pos = true;
			}
		}
		
		for(Pattern p : negatif){
			Matcher m = p.matcher(message);
			if(m.find()){
				neg = true;
			}
		}
		
		if(neg == true && pos == true ){
			return false;
		}
		
		return true;
	}

	/**
	 * Methode qui nettoye les tweets de la liste
	 */
	public List<Tweet> cleanListeTweet(List<Tweet> listeTweet) {
		List<Tweet> listeClean = new ArrayList<Tweet>();
		for (Tweet tweet : listeTweet) {
			Tweet tweetCleaned = tweet;
			String tweetCleanedMessage = this.cleanTweet(tweet.getMessage());
			boolean valide = this.testEmote(tweetCleanedMessage);
			if(valide){
				tweetCleaned.setMessage(tweetCleanedMessage);
				listeClean.add(tweetCleaned);
			}
		}
		return listeClean;
	}
	
	public void cleanBase(){
		for(Tweet tweet: this.tweetBase.getTweets()){
			String message = tweet.getMessage();
			String messageClean = this.cleanTweet(message);
			tweet.setMessage(messageClean);
		}
		this.writeCSV("ressource/BaseTweets.csv", this.getBaseApprentissage());
	}

	/**
	 * Retourne la liste de tweet formant la base d'apprentissage
	 * 
	 * @return liste de tweet
	 */
	public List<Tweet> getBaseApprentissage() {
		List<Tweet> liste = new ArrayList<Tweet>();

		for (Entry<Integer, Tweet> entry : this.tweetBase.getBase().entrySet()) {
			Tweet tweet = entry.getValue();
			liste.add(tweet);
		}
		return liste;
	}

	/**
	 * Ajoute les tweets qui viennent d'ï¿½tre annotï¿½s manuellement ï¿½ la
	 * base
	 */
	public void setBaseApprentissage() {
		for (Tweet tweet : this.getModel().getListTweet()) {
			if (tweet.getBase() == true) {
				this.tweetBase.addTweet(tweet);
			}
		}
	}

	private Tweet classifie(Classifieur classificateur, Tweet tweet) {
		tweet.setNote(classificateur.classifie(tweet.getMessage()));
		return tweet;
	}

	public List<Tweet> classeListe(Classifieur classificateur) {
		List<Tweet> listeClasse = new ArrayList<Tweet>();
		for (Tweet tweet : this.getModel().getListTweet()) {
			Tweet tweetClasse = this.classifie(classificateur, tweet);
			listeClasse.add(tweetClasse);
		}
		return listeClasse;
	}

	public void setClassifieur(int id) {
		this.classifierId = id;
		this.classificateurActuel = this.getClassificateur(id);
	}

	public int getClassifieurID() {
		return this.classifierId;
	}

	public Classifieur getClassificateurActuel() {
		return this.classificateurActuel;
	}

	public Classifieur getClassificateur(int id) {
		System.out.println("id classificateur : " + id);
		switch (id) {
		case 0:
			return this.classificateurs[0];
		case 1:
			return this.classificateurs[1];
		case 2:
			return this.classificateurs[2];
		case 3:
			return this.classificateurs[3];
		case 4:
			return this.classificateurs[4];
		case 5:
			return this.classificateurs[5];
		case 6:
			return this.classificateurs[6];
		case 7:
			return this.classificateurs[7];
		case 8:
			return this.classificateurs[8];
		case 9:
			return this.classificateurs[9];
		case 10:
			return this.classificateurs[10];
		case 11:
			return this.classificateurs[11];
		case 12:
			return this.classificateurs[12];
		case 13:
			return this.classificateurs[13];
		default:
			throw new IllegalArgumentException("Ce classificateur n'existe pas.");
		}
	}


}
