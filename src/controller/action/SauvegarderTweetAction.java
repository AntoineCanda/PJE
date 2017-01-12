package controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controller.Controller;

@SuppressWarnings("serial")
/**
 * Classe implementant la classe AbstractAction pour l'action de sauvegarder les
 * tweets dans le fichier present en parametre
 * 
 * @author canda
 *
 */
public class SauvegarderTweetAction extends AbstractAction {

	private Controller controler;
	private String path;

	public SauvegarderTweetAction(String nomAction, Controller controler, String path) {
		super(nomAction);
		this.controler = controler;
		this.path = path;
	}

	/**
	 * On fait appel a l'ecriture des tweets dans le fichier csv fourni lors de
	 * la creation de l'action
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			controler.setBaseApprentissage();
			controler.writeCSV(this.path, this.controler.getBaseApprentissage());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

}
