package view.button;

import javax.swing.JButton;

import controller.action.ClassifierAction;

@SuppressWarnings("serial")
public class ClassifierBoutton extends JButton {

	public ClassifierBoutton(ClassifierAction classeAction) {
		super(classeAction);
	}
}
