package tools;

import java.util.Date;
import twitter4j.Status;
import tools.Note;

/**
 * Classe cree qui un Tweet a  partir d'un Status ou des differentes
 * informations d'un tweet
 * 
 * @author canda
 *
 */
public class Tweet {

	// id du tweet
	private Long id;
	// pseudo de l'ï¿½metteur
	private String user;
	// contenu du tweet
	private String message;
	// date du tweet
	private Date date;
	// mot cle de la recherche associee
	private String queryWord;
	/*
	 * note du tweet: -1 Ã  traiter 0 negatif 2 neutre 4 positif
	 */
	private Note note;
	// Indicateur booleen de conservation du tweet dans la base d'apprentissage
	// ou non
	private boolean base;

	// Constructeur d'un tweet
	public Tweet(Long id, String user, String message, Date date, String queryWord, Note note, boolean base) {
		this.id = id;
		this.user = user;
		this.message = message;
		this.date = date;
		this.queryWord = queryWord;
		this.note = note;
		this.base = base;
	}

	// Constructeur d'un tweet ï¿½ partir d'un status (resultat d'une requete)
	public Tweet(Status status, String queryWord) {
		this.id = status.getId();
		this.user = status.getUser().getScreenName();
		this.message = status.getText();
		this.date = status.getCreatedAt();
		this.queryWord = queryWord;
		this.note = Note.NONTRAITE;
		this.base = false;
	}

	/**
	 * Methode retournant l'identifiant du tweet
	 * 
	 * @return l'identifiant
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Methode retournant l'auteur du tweet
	 * 
	 * @return l'auteur
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * Methode retournant le message du tweet
	 * 
	 * @return le message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Methode retournant la date du tweet
	 * 
	 * @return la date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Methode retournant le mot-cle ayant servir a obtenir ce tweet
	 * 
	 * @return le mot de la recherche
	 */
	public String getQueryWord() {
		return this.queryWord;
	}

	/**
	 * Methode retournant la note associee au tweet
	 * 
	 * @return la note
	 */
	public Note getNote() {
		return this.note;
	}

	/**
	 * Methode qui associe l'entier representant la note au tweet -1 = non note
	 * 0 = negatif 2 = neutre 4 = positif
	 * 
	 * @param note
	 */
	public void setNote(int note) {
		Note notation = Note.getNoteByValue(note);
		this.note = notation;
	}

	/**
	 * Methode qui associe une note a un tweet
	 * 
	 * @param note
	 */
	public void setNote(Note note) {
		this.note = note;
	}

	/**
	 * Retourne le booleen qui indique si on conserve ce tweet dans la base
	 * d'apprentissage ou pas
	 * 
	 * @return booleen true ou false
	 */
	public boolean getBase() {
		return this.base;
	}

	/**
	 * Change le booleen base indiquant si on conserve le tweet dans la base de
	 * donnee ou pas
	 * 
	 * @param base
	 */
	public void setBase(boolean base) {
		this.base = base;
	}

	/**
	 * Methode qui associe le message en parametre au message du tweet
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	// Redefinition fonction toString, equals et hashcode

	// On redefinit toString();
	public String toString() {
		return "----------\n Id du tweet : " + this.getId() + "\n Pseudo de l'user : " + this.getUser()
				+ "\n Message du tweet : " + this.getMessage() + "\n Date du tweet : " + this.getDate()
				+ "\n Recherche associee : " + this.getQueryWord() + "\n Note du tweet : " + this.getNote()
				+ "\n Conserve dans la base : " + this.getBase() + "\n ---------- \n";
	}

	// On redefinit equals
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Tweet)) {
			return false;
		}
		Tweet tweet = (Tweet) object;
		return this.getId() == tweet.getId();
	}

	// On redefinit hashCode
	public int hashCode() {
		int val = 13;
		int res = 1;

		res = val * res + ((this.getId() == null) ? 0 : this.getId().hashCode());

		return res;
	}
}
