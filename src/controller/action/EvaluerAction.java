package controller.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import controller.Controller;
import model.classifieur.Evaluateur;
import tools.BaseTweet;

@SuppressWarnings("serial")
public class EvaluerAction extends AbstractAction {

	private Controller controler;

	public EvaluerAction(Controller controler) {
		super("Evaluer classifieur");
		this.controler = controler;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JDialog ratioErreur = new JDialog();
		ratioErreur.setTitle("Diagramme du ratio du taux d'erreurs des algorithmes");
		
		BaseTweet base = new BaseTweet("ressource/BaseTweets.csv");
		
		double valeur = 0;
		for(int i=0; i<5;i++){
			Evaluateur evaluateur = new Evaluateur(base, this.controler);
			valeur += evaluateur.evalueClassificateur();
		}
		valeur /= 5;
		
		final DefaultPieDataset pieDataSet = new DefaultPieDataset();
		pieDataSet.setValue("Evaluation incorrecte", valeur);
		pieDataSet.setValue("Evaluation correcte", 100-valeur);
		final JFreeChart chart = ChartFactory.createPieChart("Diagramme du resultat de l'evaluation de l'algorithme", pieDataSet,
				true, true, true);

		final ChartPanel cPanel = new ChartPanel(chart);

		ratioErreur.add(cPanel, BorderLayout.CENTER);
		ratioErreur.pack();
		ratioErreur.setLocationRelativeTo(null);
		ratioErreur.setVisible(true);
		System.out.println(valeur);
		
	}

}
