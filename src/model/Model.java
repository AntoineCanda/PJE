package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tools.Tweet;

@SuppressWarnings("serial")
public class Model extends AbstractTableModel implements Iterable<Tweet> {

	protected ArrayList<Tweet> listeTweet;
	private final String[] entete = { "Utilisateur", "Date", "Message", "Note",
			"Conserver dans la Base d'apprentissage" };

	public Model() {
		super();
		this.listeTweet = new ArrayList<Tweet>();
	}

	/**
	 * methode mettant a jour la table model qui ici est la liste de tweet
	 * 
	 * @param listeTweets
	 */
	public void updateTableModel(List<Tweet> listeTweets) {

		this.listeTweet = (ArrayList<Tweet>) listeTweets;
		fireTableDataChanged();
	}

	/**
	 * Methode qui retourne le nombre de colonne
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	/**
	 * Methode qui retourne le nombre de ligne
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeTweet.size();
	}

	/**
	 * Methode qui a partir d'un numero de colonne et de ligne va obtenir
	 * l'information associee du tweet Colonne 0 : Auteur Colonne 1 : Date
	 * Colonne 2 : Message du tweet Colonne 3 : note Colonne 4 : Conserve dans
	 * la base ?
	 */
	public Object getValueAt(int indiceLigne, int indiceColonne) {
		Tweet tweet = listeTweet.get(indiceLigne);
		switch (indiceColonne) {
		case 0:
			return tweet.getUser();
		case 1:
			return tweet.getDate();
		case 2:
			return tweet.getMessage();
		case 3:
			return tweet.getNote();
		case 4:
			return tweet.getBase();
		default:
			throw (new IllegalArgumentException());
		}
	}

	/**
	 * methode qui definit si une cellule est editable ou pas. Ici seul la
	 * colonne 3 (note) pourra l'Ãªtre
	 */
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		if (indiceColonne == 3 || indiceColonne == 4) {
			return true;
		}
		return false;
	}

	/**
	 * Methode qui associe une valeur a un cellule Ici on souhaite associe une
	 * note a un tweet
	 */
	public void setValueAt(Object object, int indiceLigne, int indiceColonne) {
		if (indiceColonne == 3) {
			listeTweet.get(indiceLigne).setNote(Integer.parseInt((String) object));
		} else if (indiceColonne == 4) {
			listeTweet.get(indiceLigne).setBase(Boolean.parseBoolean((String) object));
		} else {
			throw (new IllegalArgumentException());
		}
	}

	/**
	 * Methode qui retourne le nom de la colonne selon son indice
	 */
	public String getColumnName(int indiceColonne) {
		return this.entete[indiceColonne];
	}

	/**
	 * Methode qui retourne un iterateur sur la liste de tweet
	 */
	public Iterator<Tweet> iterator() {
		// TODO Auto-generated method stub
		return listeTweet.iterator();
	}

	/**
	 * Methode qui retourne le tweet a la position donnee par indice dans la
	 * liste
	 * 
	 * @param indice
	 * @return
	 */
	public Tweet getTweet(int indice) {
		return this.listeTweet.get(indice);
	}

	/**
	 * Retourne la liste des tweets
	 * 
	 * @return liste des tweets
	 */
	public List<Tweet> getListTweet() {
		return this.listeTweet;
	}
}
