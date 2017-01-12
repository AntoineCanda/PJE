package controller.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import controller.Controller;
import model.RechercheTwitter;
import tools.Tweet;

@SuppressWarnings("serial")
/**
 * Classe representant l'action de rechercher des tweets au declenchement de
 * celle ci
 * 
 * @author canda
 *
 */
public class RechercherAction extends AbstractAction {

	private JTextField champRecherche;
	private RechercheTwitter twitter;
	private Controller controler;

	public RechercherAction(String nomAction, JTextField champRecherche, RechercheTwitter twitter,
			Controller controler) {
		super(nomAction);
		this.champRecherche = champRecherche;
		this.twitter = twitter;
		this.controler = controler;
	}

	/**
	 * On fait appel a la methode de recherche de tweet de twitter puis on met
	 * à jour le model du controler.
	 */
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			String keyword = this.champRecherche.getText();
			List<Tweet> listeTweet = this.twitter.search(keyword);
			List<Tweet> listeClean = this.controler.cleanListeTweet(listeTweet);
			String path = "ressource/Tweets-" + keyword + ".csv";
			this.controler.writeCSV(path, listeClean);
			this.controler.updateTableModel(listeClean);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

}
