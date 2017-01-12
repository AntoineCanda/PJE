package controller.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import controller.Controller;
import tools.Tweet;

@SuppressWarnings("serial")
public class ClassifierAction extends AbstractAction {

	private Controller controler;

	public ClassifierAction(String nomAction, Controller controler) {
		super(nomAction);
		this.controler = controler;
	}

	/**
	 * On fait appel a la methode de recherche de tweet de twitter puis on met a
	 * jour le model du controler.
	 */
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			List<Tweet> listeClasse = this.controler.classeListe(this.controler.getClassificateurActuel());
			this.controler.updateTableModel(listeClasse);
			String keyword = listeClasse.get(0).getQueryWord();
			String path = "ressource/Tweets-" + keyword + "-annote.csv";
			this.controler.writeCSV(path, listeClasse);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}
