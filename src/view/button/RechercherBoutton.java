package view.button;

import javax.swing.JButton;

import controller.action.RechercherAction;

/**
 * Classe representant le bouton permettant de faire une recherche
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class RechercherBoutton extends JButton {

	public RechercherBoutton(RechercherAction searchAction) {
		super(searchAction);
	}
}
