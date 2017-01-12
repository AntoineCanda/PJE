package view.tweet;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

import controller.Controller;

/**
 * Classe etendant AffichageTweetView afin de permettre l'affichage des tweets
 * nettoyes et egalement de pouvoir editer la colonne de notation.
 * 
 * @author canda
 *
 */
@SuppressWarnings("serial")
public class AffichageTweetViewApprentissageMan extends AffichageTweetView {

	@SuppressWarnings("unchecked")
	public AffichageTweetViewApprentissageMan(Controller tweetControler) {
		super(tweetControler);

		this.setModel(tweetControler.getModel());
		this.getModel().addTableModelListener(this);

		TableColumn tableColonne3 = this.getColumnModel().getColumn(3);
		@SuppressWarnings("rawtypes")
		JComboBox comboNote = new JComboBox();
		comboNote.addItem("-1");
		comboNote.addItem("0");
		comboNote.addItem("2");
		comboNote.addItem("4");

		TableColumn tableColonne4 = this.getColumnModel().getColumn(4);
		@SuppressWarnings("rawtypes")
		JComboBox comboBase = new JComboBox();
		comboBase.addItem("false");
		comboBase.addItem("true");

		tableColonne3.setCellEditor(new DefaultCellEditor(comboNote));
		tableColonne4.setCellEditor(new DefaultCellEditor(comboBase));
	}
}
