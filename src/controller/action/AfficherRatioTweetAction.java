package controller.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import controller.Controller;
import tools.Note;
import tools.Tweet;

/**
 * Classe permettant l'affichage du diagramme (piechart) presentant le ratio de
 * tweet classe.
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class AfficherRatioTweetAction extends AbstractAction {
	private Controller controler;

	public AfficherRatioTweetAction(Controller controler) {
		super("Afficher Diagramme");
		this.controler = controler;
	}

	/**
	 * la methode va creer le graphique et l'afficher en pop-up
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		JDialog ratioTweet = new JDialog();
		ratioTweet.setTitle("Diagramme du ratio du classement des tweets");
		int nombreTweetNeutre = 0;
		int nombreTweetNegatif = 0;
		int nombreTweetPositif = 0;

		List<Tweet> listeTweet = this.controler.getModel().getListTweet();
		for (Tweet tweet : listeTweet) {
			Note note = tweet.getNote();
			switch (note) {
			case POSITIF:
				nombreTweetPositif++;
				break;
			case NEUTRE:
				nombreTweetNeutre++;
				break;
			case NEGATIF:
				nombreTweetNegatif++;
				break;
			default:
				throw new IllegalArgumentException(
						"La note n'existe pas ou vous n'avez pas encore annote les tweets et ils sont donc non notes !");
			}
		}

		final DefaultPieDataset pieDataSet = new DefaultPieDataset();
		pieDataSet.setValue("Tweet negatif", nombreTweetNegatif);
		pieDataSet.setValue("Tweet neutre", nombreTweetNeutre);
		pieDataSet.setValue("Tweet positif", nombreTweetPositif);

		final JFreeChart chart = ChartFactory.createPieChart("Diagramme du resultat de la classification", pieDataSet,
				true, true, true);

		final ChartPanel cPanel = new ChartPanel(chart);

		ratioTweet.add(cPanel, BorderLayout.CENTER);
		ratioTweet.pack();
		ratioTweet.setLocationRelativeTo(null);
		ratioTweet.setVisible(true);
	}

}
