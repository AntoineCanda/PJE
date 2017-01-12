package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import controller.Controller;
import controller.action.AfficherRatioTweetAction;
import controller.action.ClassifierAction;
import view.button.ClassifierBoutton;
import view.button.MontrerRatioBoutton;
import view.tweet.AffichageTweetView;
import view.tweet.TweetView;

@SuppressWarnings("serial")
public class ApprentissageAutomatiquePanel extends JPanel {

	public ApprentissageAutomatiquePanel(Controller controler) {
		// TODO Auto-generated constructor stub

		AffichageTweetView affTweetView = new AffichageTweetView(controler);

		TweetView tweetView = new TweetView(affTweetView);
		tweetView.setPreferredSize(new Dimension(1200, 750));
		this.add(tweetView, BorderLayout.CENTER);

		ClassifierAction classeAction = new ClassifierAction("Classifier Tweets", controler);
		ClassifierBoutton classeBoutton = new ClassifierBoutton(classeAction);
		this.add(classeBoutton, BorderLayout.SOUTH);

		AfficherRatioTweetAction afficheAction = new AfficherRatioTweetAction(controler);
		MontrerRatioBoutton showBoutton = new MontrerRatioBoutton(afficheAction);

		this.add(showBoutton, BorderLayout.SOUTH);
	}

}
