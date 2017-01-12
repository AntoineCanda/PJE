package view.button;

import javax.swing.JButton;

import controller.action.AfficherRatioTweetAction;

@SuppressWarnings("serial")
public class MontrerRatioBoutton extends JButton {

	public MontrerRatioBoutton(AfficherRatioTweetAction action) {
		super(action);
	}
}
