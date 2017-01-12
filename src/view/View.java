package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import controller.Controller;
import view.panel.EvaluePanel;
import view.panel.ApprentissageAutomatiquePanel;
import view.panel.ApprentissageManuelPanel;
import view.panel.ReglagePanel;
import view.panel.SearchPanel;
import model.RechercheTwitter;

/**
 * Classe principale du dossier View permettant l'affichage de la fenêtre
 * graphique
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {

	private RechercheTwitter twitter;
	private Controller controler;

	public View(RechercheTwitter twitter, Controller controler) {
		this.twitter = twitter;
		this.controler = controler;

		build();
	}

	/**
	 * methode qui construit la fenêtre avec les parametres souhaites
	 */
	private void build() {
		setTitle("Analyse de comportement avec Twitter"); // On donne un titre
															// à l'application
		setSize(1240, 900); // On donne une taille à notre fenêtre
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		setResizable(false); // On autorise la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à
														// l'application de se
														// fermer lors du clic
														// sur la croix
		JTabbedPane pane = buildContentPane();
		setContentPane(pane);
	}

	/**
	 * Methode qui construit la table des onglets de notre fenêtre. A chaque
	 * onglet est associe un panel
	 * 
	 * @return
	 */
	public JTabbedPane buildContentPane() {

		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

		SearchPanel onglet1 = new SearchPanel(this.controler, this.twitter);
		onglet1.setPreferredSize(new Dimension(1200, 900));
		onglets.addTab("Recherche", onglet1);

		ApprentissageManuelPanel onglet2 = new ApprentissageManuelPanel(this.controler);
		onglet2.setPreferredSize(new Dimension(1200, 900));
		onglets.addTab("Annotation manuelle", onglet2);

		ApprentissageAutomatiquePanel onglet3 = new ApprentissageAutomatiquePanel(this.controler);
		onglet3.setPreferredSize(new Dimension(1200, 900));
		onglets.addTab("Annotation automatique", onglet3);

		EvaluePanel onglet4 = new EvaluePanel(this.controler);
		onglet4.setPreferredSize(new Dimension(1200, 900));
		onglets.addTab("Evaluation algorithme", onglet4);

		ReglagePanel onglet5 = new ReglagePanel(this.controler);
		onglet5.setPreferredSize(new Dimension(1200, 900));
		onglets.addTab("Reglages", onglet5);

		return onglets;
	}
}
