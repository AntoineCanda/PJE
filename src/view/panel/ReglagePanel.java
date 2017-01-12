package view.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.Controller;

@SuppressWarnings("serial")
public class ReglagePanel extends JPanel {

	private Controller controler;
	private JRadioButton[] bouttonsRadio;
	private ButtonGroup group;

	public ReglagePanel(Controller controler) {
		super();

		this.setLayout(new GridLayout(20, 1));
		this.controler = controler;
		this.group = new ButtonGroup();
		this.bouttonsRadio = new JRadioButton[14];

		this.bouttonsRadio[0] = new JRadioButton("Dictionnaire");
		this.bouttonsRadio[1] = new JRadioButton("KNN");
		this.bouttonsRadio[2] = new JRadioButton("Bayes par presence non simplifie avec unigramme");
		this.bouttonsRadio[3] = new JRadioButton("Bayes par presence simplifie avec unigramme");
		this.bouttonsRadio[4] = new JRadioButton("Bayes par presence non simplifie avec bigramme");
		this.bouttonsRadio[5] = new JRadioButton("Bayes par presence simplifie avec bigramme");
		this.bouttonsRadio[6] = new JRadioButton("Bayes par presence non simplifie avec unigramme+bigramme");
		this.bouttonsRadio[7] = new JRadioButton("Bayes par presence simplifie avec unigramme+bigramme");
		this.bouttonsRadio[8] = new JRadioButton("Bayes par frequence non simplifie avec unigramme");
		this.bouttonsRadio[9] = new JRadioButton("Bayes par frequence simplifie avec unigramme");
		this.bouttonsRadio[10] = new JRadioButton("Bayes par frequence non simplifie avec bigramme");
		this.bouttonsRadio[11] = new JRadioButton("Bayes par frequence simplifie avec bigramme");
		this.bouttonsRadio[12] = new JRadioButton("Bayes par frequence non simplifie avec unigramme+bigramme");
		this.bouttonsRadio[13] = new JRadioButton("Bayes par frequence simplifie avec unigramme+bigramme");

		for (int i = 0; i < bouttonsRadio.length; i++) {
			this.group.add(this.bouttonsRadio[i]);
			this.bouttonsRadio[i].addActionListener(new RadioListener(i));
			this.add(this.bouttonsRadio[i]);
		}

		this.bouttonsRadio[this.controler.getClassifieurID()].setSelected(true);

	}

	class RadioListener implements ActionListener {

		private int numero;

		public RadioListener(int numero) {
			this.numero = numero;
		}

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ReglagePanel.this.controler.setClassifieur(this.numero);
		}
	}
}
