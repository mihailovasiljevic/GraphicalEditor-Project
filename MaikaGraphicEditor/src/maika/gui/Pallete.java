package maika.gui;

import maika.application.Application;

import javax.swing.BoxLayout;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Pallete extends JToolBar {
	public Pallete() {
		super(JToolBar.VERTICAL);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Application.getInstance().getActionManager().getSelectGTAction());
		addSeparator();
		add(Application.getInstance().getActionManager().getTriangleGTAction());
		addSeparator();
		add(Application.getInstance().getActionManager().getRectangleGTAction());
		addSeparator();
		add(Application.getInstance().getActionManager().getCircleGTAction());
		addSeparator();
		add(Application.getInstance().getActionManager().getStarGTAction());
	}
}
