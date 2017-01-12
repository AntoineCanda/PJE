package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import controller.action.EvaluerAction;
import view.button.EvaluerBoutton;

@SuppressWarnings("serial")
public class EvaluePanel extends JPanel {

	private Controller controler;
	public static JLabel affichageLabel;

	public EvaluePanel(Controller controler) {
		super();
		this.controler = controler;
		this.setPreferredSize(new Dimension(1200, 800));
		EvaluerAction evalueAction = new EvaluerAction(this.controler);
		EvaluerBoutton evalueBoutton = new EvaluerBoutton(evalueAction);
		this.add(evalueBoutton, BorderLayout.NORTH);


	}


}
