package model.classifieur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.Controller;
import tools.BaseTweet;
import tools.Note;
import tools.Tweet;

public class Evaluateur {

	private BaseTweet base;
	private List<Tweet> unionEnsemble;
	private List<List<Tweet>> listeEnsemble;
	private Controller controler;

	public Evaluateur(BaseTweet base, Controller controler) {
		this.base = base;
		this.controler = controler;
		this.unionEnsemble = new ArrayList<Tweet>();
		this.listeEnsemble = new ArrayList<List<Tweet>>();
		this.diviserBaseEnsembleAleatoire();
	}

	/**
	 * divise la base en 3 ensembles de 51 tweets contenant chacun 17 tweets de
	 * chaque classe
	 */
	private void diviserBaseEnsembleAleatoire() {
		Random random = new Random();
		List<Tweet> ensemble1 = new ArrayList<Tweet>();
		List<Tweet> ensemble2 = new ArrayList<Tweet>();
		List<Tweet> ensemble3 = new ArrayList<Tweet>();
		
		List<Tweet> ensemblePos = new ArrayList<Tweet>();
		List<Tweet> ensembleNeg = new ArrayList<Tweet>();
		List<Tweet> ensembleNeu = new ArrayList<Tweet>();
		
		int negatif = 0;
		int positif = 0;
		int neutre = 0;
		
		for (Tweet tweet : this.base.getTweets()) {
			Note note = tweet.getNote();
			switch(note){
			case POSITIF:
				positif++;
				break;
			case NEGATIF:
				negatif++;
				break;
			case NEUTRE:
				neutre++;
				break;
			default:
				throw new IllegalArgumentException("Erreur");
			}
		}
		
		int nbtweetnote;
		if(positif < negatif){
			nbtweetnote = (positif < neutre )? positif/3 : neutre / 3;
		}
		else {
			nbtweetnote = (negatif < neutre )? negatif/3 : neutre / 3;
		}
		int negaL1 = 0;
		int negaL2 = 0;
		int negaL3 = 0;
		int posL1 = 0;
		int posL2 = 0;
		int posL3 = 0;
		int neuL1 = 0;
		int neuL2 = 0;
		int neuL3 = 0;
		for (Tweet tweet : this.base.getTweets()) {
			Note note = tweet.getNote();
			int number = random.nextInt(3);
			switch (number) {
			case 0:
				switch (note) {
				case POSITIF:
					if (posL1 < nbtweetnote) {
						ensemble1.add(tweet);
						posL1++;
					}
					else {
						ensemblePos.add(tweet);
					}
					break;
				case NEUTRE:
					if (neuL1 < nbtweetnote) {
						ensemble1.add(tweet);
						neuL1++;
					}
					else {
						ensembleNeu.add(tweet);
					}
					break;
				case NEGATIF:
					if (negaL1 < nbtweetnote) {
						ensemble1.add(tweet);
						negaL1++;
					}
					else {
						ensembleNeg.add(tweet);
					}
					break;
				default:
					throw new IllegalArgumentException("Erreur");
				}
				break;
			case 1:
				switch (note) {
				case POSITIF:
					if (posL2 < nbtweetnote) {
						ensemble2.add(tweet);
						posL2++;
					}
					else {
						ensemblePos.add(tweet);
					}
					break;
				case NEUTRE:
					if (neuL2 < nbtweetnote) {
						ensemble2.add(tweet);
						neuL2++;
					}
					else {
						ensembleNeu.add(tweet);
					}
					break;
				case NEGATIF:
					if (negaL2 < nbtweetnote) {
						ensemble2.add(tweet);
						negaL2++;
					}
					else {
						ensembleNeg.add(tweet);
					}
					break;
				default:
					throw new IllegalArgumentException("Erreur");
				}
				break;
			case 2:
				switch (note) {
				case POSITIF:
					if (posL3 < nbtweetnote) {
						ensemble3.add(tweet);
						posL3++;
					}
					else {
						ensemblePos.add(tweet);
					}
					break;
				case NEUTRE:
					if (neuL3 < nbtweetnote) {
						ensemble3.add(tweet);
						neuL3++;
					}
					else {
						ensembleNeu.add(tweet);
					}
					break;
				case NEGATIF:
					if (negaL3 < nbtweetnote) {
						ensemble3.add(tweet);
						negaL3++;
					}
					else {
						ensembleNeg.add(tweet);
					}
					break;
				default:
					throw new IllegalArgumentException("Erreur");
				}
				break;
			default:
				throw new IllegalArgumentException("Erreur");
			}
		}
				
		int i = 0 ;
		
		if(posL1 < nbtweetnote){
			while(posL1 < nbtweetnote){
				Tweet tweet = ensemblePos.get(i);
				ensemble1.add(tweet);
				ensemblePos.remove(tweet);
				posL1++;
			}
		}
		
		if(posL2 < nbtweetnote){
			while(posL2 < nbtweetnote){
				Tweet tweet = ensemblePos.get(i);
				ensemble2.add(tweet);
				ensemblePos.remove(tweet);
				posL2++;
			}
		}
		
		if(posL3 < nbtweetnote){
			while(posL3 < nbtweetnote){
				Tweet tweet = ensemblePos.get(i);
				ensemble3.add(tweet);
				ensemblePos.remove(tweet);
				posL3++;
			}
		}
		
		if(neuL1 < nbtweetnote){
			while(neuL1 < nbtweetnote){
				Tweet tweet = ensembleNeu.get(i);
				ensemble1.add(tweet);
				ensembleNeu.remove(tweet);
				neuL1++;
			}
		}
		if(neuL2 < nbtweetnote){
			while(neuL2 < nbtweetnote){
				Tweet tweet = ensembleNeu.get(i);
				ensemble2.add(tweet);
				ensembleNeu.remove(tweet);
				neuL2++;
			}
		}
		if(neuL3 < nbtweetnote){
			while(neuL3 < nbtweetnote){
				Tweet tweet = ensembleNeu.get(i);
				ensemble3.add(tweet);
				ensembleNeu.remove(tweet);
				neuL3++;
			}
		}
		if(negaL1 < nbtweetnote){
			while(negaL1 < nbtweetnote){				
				Tweet tweet = ensembleNeg.get(i);
				ensemble1.add(tweet);
				ensembleNeg.remove(tweet);
				negaL1++;
			}
		}
		if(negaL2 < nbtweetnote){
			while(negaL2 < nbtweetnote){
				Tweet tweet = ensembleNeg.get(i);
				ensemble2.add(tweet);
				ensembleNeg.remove(tweet);
				negaL2++;
			}
		}
		if(negaL3 < nbtweetnote){
			while(negaL3 < nbtweetnote){
				Tweet tweet = ensembleNeg.get(i);
				ensemble3.add(tweet);
				ensembleNeg.remove(i);
				negaL3++;
			}
		}

		this.listeEnsemble.add(ensemble1);
		this.listeEnsemble.add(ensemble2);
		this.listeEnsemble.add(ensemble3);
	}
	
	/**
	 * fonction d'affichage pour verifier l'equite dans chaque ensemble
	 */
	public void afficheStatDivision() {
		
		int negaL1 = 0;
		int negaL2 = 0;
		int negaL3 = 0;
		int posL1 = 0;
		int posL2 = 0;
		int posL3 = 0;
		int neuL1 = 0;
		int neuL2 = 0;
		int neuL3 = 0;

		for (Tweet tweet : this.listeEnsemble.get(0)) {
			Note note = tweet.getNote();
			switch (note) {
			case POSITIF:
				posL1++;
				break;
			case NEUTRE:
				neuL1++;
				break;
			case NEGATIF:
				negaL1++;
				break;
			default:
				throw new IllegalArgumentException("Erreur");
			}
		}

		for (Tweet tweet : this.listeEnsemble.get(1)) {
			Note note = tweet.getNote();
			switch (note) {
			case POSITIF:
				posL2++;
				break;
			case NEUTRE:
				neuL2++;
				break;
			case NEGATIF:
				negaL2++;
				break;
			default:
				throw new IllegalArgumentException("Erreur");
			}
		}

		for (Tweet tweet : this.listeEnsemble.get(2)) {
			Note note = tweet.getNote();
			switch (note) {
			case POSITIF:
				posL3++;
				break;
			case NEUTRE:
				neuL3++;
				break;
			case NEGATIF:
				negaL3++;
				break;
			default:
				System.out.println(note);
				throw new IllegalArgumentException("Erreur");
			}
		}

		System.out.println("Nombre de tweet positif : " + posL1 + ", neutre : " + neuL1 + ", negatif : " + negaL1
				+ " pour la liste 1.");
		System.out.println("Nombre de tweet positif : " + posL2 + ", neutre : " + neuL2 + ", negatif : " + negaL2
				+ " pour la liste 2.");
		System.out.println("Nombre de tweet positif : " + posL3 + ", neutre : " + neuL3 + ", negatif : " + negaL3
				+ " pour la liste 3.");

	}

	/**
	 * Creer la liste de tweet qui formera la base d'apprentissage a partir de
	 * deux ensemble
	 * 
	 * @param E1
	 * @param E2
	 */
	private void creerBaseUtilise(int E1, int E2) {
		if (E1 > 2 || E2 > 2) {
			throw new IllegalArgumentException("Ensemble inexistant");
		} else {
			this.unionEnsemble.clear();
			this.unionEnsemble.addAll(this.listeEnsemble.get(E1));
			this.unionEnsemble.addAll(this.listeEnsemble.get(E2));
		}
	}

	/**
	 * Creer le nouveau classifieur avec la bonne base
	 * 
	 * @return
	 */
	public Classifieur creerClassifieur() {
		BaseTweet base = new BaseTweet(this.unionEnsemble);
		int idClassifieur = this.controler.getClassifieurID();
		Classifieur classificateurs;
		switch (idClassifieur) {
		case 0:
			classificateurs = new Dictionnaire("ressource/positive.txt", "ressource/negative.txt");
			return classificateurs;
		case 1:
			classificateurs = new KNN(base, 5);
			return classificateurs;
		case 2:
			classificateurs = new BayesPresence(base, false, Degree.UNI);
			return classificateurs;
		case 3:
			classificateurs = new BayesPresence(base, true, Degree.UNI);
			return classificateurs;
		case 4:
			classificateurs = new BayesPresence(base, false, Degree.BI);
			return classificateurs;
		case 5:
			classificateurs = new BayesPresence(base, true, Degree.BI);
			return classificateurs;
		case 6:
			classificateurs = new BayesPresence(base, false, Degree.UNIBI);
			return classificateurs;
		case 7:
			classificateurs = new BayesPresence(base, true, Degree.UNIBI);
			return classificateurs;
		case 8:
			classificateurs = new BayesFrequences(base, false, Degree.UNI);
			return classificateurs;
		case 9:
			classificateurs = new BayesFrequences(base, true, Degree.UNI);
			return classificateurs;
		case 10:
			classificateurs = new BayesFrequences(base, false, Degree.BI);
			return classificateurs;
		case 11:
			classificateurs = new BayesFrequences(base, true, Degree.BI);
			return classificateurs;
		case 12:
			classificateurs = new BayesFrequences(base, false, Degree.UNIBI);
			return classificateurs;
		case 13:
			classificateurs = new BayesFrequences(base, true, Degree.UNIBI);
			return classificateurs;
		default:
			throw new IllegalArgumentException("Ce classificateur n'existe pas.");
		}
	}

	public void testFusionListe(int E1, int E2) {
		this.creerBaseUtilise(E1, E2);
		System.out.println(this.unionEnsemble.size());
		this.unionEnsemble.clear();
		System.out.println(this.unionEnsemble.size());
	}

	public int getIndiceEnsembleATester(int E1, int E2) {
		if (E1 == 0) {
			return (E2 == 1) ? 2 : 1;
		} else if (E1 == 1) {
			return (E2 == 0) ? 2 : 0;
		} else {
			return (E2 == 0) ? 1 : 0;
		}
	}

	public Map<Integer, Note> classifieEnsemble(int E1, int E2) {

		Map<Integer, Note> map = new HashMap<Integer, Note>();
		map.clear();
		this.creerBaseUtilise(E1, E2);
		Classifieur classifieur = this.creerClassifieur();
		int liste = this.getIndiceEnsembleATester(E1, E2);
		for (Tweet tweet : this.listeEnsemble.get(liste)) {
			String message = tweet.getMessage();
			Note note = classifieur.classifie(message);
			map.put(tweet.hashCode(), note);
		}

		return map;
	}

	public double calculTauxErreur(Map<Integer, Note> map, int liste) {
		List<Tweet> listeTweet = this.listeEnsemble.get(liste);
		int cpt = 0;

		for (Tweet tweet : listeTweet) {
			Note note = tweet.getNote();
			Note noteClassifieur = map.get(tweet.hashCode());
			if (note != noteClassifieur) {
				cpt++;
			}
		}

		return (double) cpt / (double) listeTweet.size();
	}

	public double evalueClassificateur() {
		Map<Integer, Note> mapE1 = this.classifieEnsemble(1, 2);
		Map<Integer, Note> mapE2 = this.classifieEnsemble(0, 2);
		Map<Integer, Note> mapE3 = this.classifieEnsemble(0, 1);

		double taux1 = this.calculTauxErreur(mapE1, 0);
		double taux2 = this.calculTauxErreur(mapE2, 1);
		double taux3 = this.calculTauxErreur(mapE3, 2);

		//System.out.println("Taux 1 : " + taux1 + ", taux 2 : " + taux2 + ", taux 3 : " + taux3);
		double res = ((taux1 + taux2 + taux3) / 3) * 100;
		return res;
	}
}