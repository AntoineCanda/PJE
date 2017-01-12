package view.button;

import javax.swing.JButton;

import controller.action.EvaluerAction;

@SuppressWarnings("serial")
public class EvaluerBoutton extends JButton {

	public EvaluerBoutton(EvaluerAction evalueAction) {
		super(evalueAction);
	}
}
