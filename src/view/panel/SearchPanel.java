package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.Controller;
import controller.action.RechercherAction;
import view.button.RechercherBoutton;
import view.tweet.AffichageTweetView;
import view.tweet.TweetView;
import model.RechercheTwitter;

/**
 * Classe representant le panel de recherche de tweet Ce panel est composé d'un
 * champ de recherche compose d'un champ de saisie et d'un bouton de recherche,
 * et d'une zone d'affichage des tweets.
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class SearchPanel extends JPanel {

	private JRadioButton[] radiosBoutton;
	private ButtonGroup group;
	private RechercheTwitter twitter;

	public SearchPanel(Controller controler, RechercheTwitter twitter) {
		super();

		this.twitter = twitter;
		JPanel searchBar = new JPanel();
		JTextField textField = new JTextField();
		searchBar.setPreferredSize(new Dimension(1000, 50));
		textField.setPreferredSize(new Dimension(800, 50));
		searchBar.add(textField);
		RechercherAction searchAction = new RechercherAction("Rechercher", textField, twitter, controler);
		RechercherBoutton searchBoutton = new RechercherBoutton(searchAction);
		searchBar.add(searchBoutton);
		this.add(searchBar, BorderLayout.NORTH);

		AffichageTweetView affTweetView = new AffichageTweetView(controler);

		TweetView tweetView = new TweetView(affTweetView);
		tweetView.setPreferredSize(new Dimension(1200, 750));
		this.add(tweetView, BorderLayout.CENTER);

		this.radiosBoutton = new JRadioButton[5];
		this.group = new ButtonGroup();
		this.radiosBoutton[0] = new JRadioButton("25");
		this.radiosBoutton[1] = new JRadioButton("50");
		this.radiosBoutton[2] = new JRadioButton("75");
		this.radiosBoutton[3] = new JRadioButton("100");
		this.radiosBoutton[4] = new JRadioButton("250");

		for (int i = 0; i < radiosBoutton.length; i++) {
			this.group.add(this.radiosBoutton[i]);
			this.radiosBoutton[i].addActionListener(new RadioNbTweetListener(i));
			this.add(this.radiosBoutton[i], BorderLayout.SOUTH);
		}
		this.radiosBoutton[1].setSelected(true);

	}

	class RadioNbTweetListener implements ActionListener {

		private int numero;

		public RadioNbTweetListener(int numero) {
			this.numero = numero;
		}

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			SearchPanel.this.twitter.setNbTweets(numero);
		}
	}
}
