package view.button;

import javax.swing.JButton;

import controller.action.SauvegarderTweetAction;

/**
 * Classe representant le bouton permettant de sauvegarder la liste de tweet
 * dans un fichier
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class SauvegarderBoutton extends JButton {

	public SauvegarderBoutton(SauvegarderTweetAction saveAction) {
		super(saveAction);
	}
}
