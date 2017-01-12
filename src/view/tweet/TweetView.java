package view.tweet;

import javax.swing.JScrollPane;

/**
 * Classe permettant de creer l'affichage des tweets avec la creation de
 * scrollbar verticale et horizontale.
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class TweetView extends JScrollPane {

	public TweetView(AffichageTweetView affTweetView) {
		super(affTweetView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
}
