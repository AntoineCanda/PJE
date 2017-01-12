package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import controller.action.SauvegarderTweetAction;
import view.button.SauvegarderBoutton;
import view.tweet.AffichageTweetViewApprentissageMan;
import view.tweet.TweetView;

/**
 * Classe permettant la creation du panel d'annotation manuelle des tweets
 * permettant de creer la base d'apprentissage. Ce panel est composé d'un zone
 * d'affichage de tweets avec la colonne note editable et d'un bouton de
 * sauvegarde.
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class ApprentissageManuelPanel extends JPanel {
	public ApprentissageManuelPanel(Controller controler) {
		super();

		AffichageTweetViewApprentissageMan affTweetView = new AffichageTweetViewApprentissageMan(controler);

		TweetView tweetView = new TweetView(affTweetView);
		tweetView.setPreferredSize(new Dimension(1200, 750));
		this.add(tweetView, BorderLayout.CENTER);
		SauvegarderTweetAction saveAction = new SauvegarderTweetAction("Sauvegarder Tweets", controler,
				"ressource/BaseTweets.csv");
		SauvegarderBoutton saveBoutton = new SauvegarderBoutton(saveAction);
		this.add(saveBoutton, BorderLayout.SOUTH);

	}
}
