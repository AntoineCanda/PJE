package model.classifieur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import tools.Note;

/**
 * Classe etendant classificateur implementant la methode des dictionnaires
 * 
 * @author canda
 *
 */
public class Dictionnaire extends Classifieur {

	private String fichierPositif;
	private String fichierNegatif;
	private List<String> motPositif;
	private List<String> motNegatif;

	public Dictionnaire(String fichierPositif, String fichierNegatif) {
		this.fichierPositif = fichierPositif;
		this.fichierNegatif = fichierNegatif;
		this.setTab();
	}

	/**
	 * Methode privee qui a partir d'un fichier de mots positif ou negatif
	 * retourne un tableau de String contenant les mots du fichier
	 * 
	 * @param fichier
	 * @return tableau de String
	 */
	private List<String> fileToStringList(String fichier) {
		StringBuffer mot = new StringBuffer();
		File file = new File(fichier);

		if (file.exists() && !file.isDirectory()) {
			try {
				FileReader reader = new FileReader(fichier);
				BufferedReader buffer = new BufferedReader(reader);
				String line = "";
				while ((line = buffer.readLine()) != null) {
					mot.append(line);
				}
				buffer.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		String[] tableauMot;
		List<String> liste = new ArrayList<String>();
		tableauMot = mot.toString().split(",");
		for (String mots : tableauMot) {
			mots = mots.trim();
			liste.add(mots);
		}

		return liste;
	}

	/**
	 * Methode qui initialise les deux tableau de String pour les fichiers de
	 * mots positifs et negatifs
	 */
	private void setTab() {
		this.motPositif = this.fileToStringList(this.fichierPositif);
		this.motNegatif = this.fileToStringList(this.fichierNegatif);
	}

	/**
	 * Methode qui compte le nombre de mots en commun dans le tweet avec chaque
	 * mot des deux tableaux de String contenant les mots positif et negatifs.
	 * pour chaque mot positif present on incremente un compteur et pour chaque
	 * mot negatif present on le decremente.
	 * 
	 * @param message
	 * @return la valeur finale du compteur qui est un int
	 */
	private int compterNombreMot(String message) {
		int cpt = 0;
		String[] motsMessage = message.split(" ");
		for (String mot : motsMessage) {
			if (this.motPositif.contains((String) mot)) {
				cpt++;
			}
		}

		for (String mot : motsMessage) {
			if (this.motNegatif.contains((String) mot)) {
				cpt--;
			}
		}
		return cpt;
	}

	/**
	 * Methode classifie implemente avec la methode des dictionnaires. Elle fait
	 * appel à compterNombreMot puis en fonction du resultat du compteur
	 * attribut une Note au tweet (>0 Positif, <0 Negatif, 0 Neutre).
	 */
	public Note classifie(String message) {
		// TODO Auto-generated method stub
		int cpt = this.compterNombreMot(message);
		if (cpt > 0) {
			return Note.POSITIF;
		} else if (cpt < 0) {
			return Note.NEGATIF;
		} else {
			return Note.NEUTRE;
		}
	}

}
