package view.tweet;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
//import javax.swing.table.TableColumn;

import controller.Controller;

/**
 * classe implementant tableModelListener et etendant JTable. C'est la classe
 * qui va afficher les tweets et se mettre a jour de maniere automatique grâce
 * aux listeners presents.
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class AffichageTweetView extends JTable implements TableModelListener {

	public AffichageTweetView(Controller tweetControler) {
		super();

		this.setModel(tweetControler.getModel());
		this.getModel().addTableModelListener(this);

		// TableColumn tableColonne4 = this.getColumnModel().getColumn(4);
		// tableColonne4.setMinWidth(0);
		// tableColonne4.setMaxWidth(0);

	}
}
