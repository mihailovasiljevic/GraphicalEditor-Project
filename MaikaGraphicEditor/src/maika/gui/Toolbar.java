package maika.gui;

import maika.application.Application;

import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar{

	public Toolbar(){
		super();
		add(Application.getInstance().getActionManager().getNewProjectAction());
		add(Application.getInstance().getActionManager().getNewDiagramAction());
		addSeparator();
		
		add(Application.getInstance().getActionManager().getOpenProjectAction());
		add(Application.getInstance().getActionManager().getOpenDiagramAction());
		addSeparator();
		
		add(Application.getInstance().getActionManager().getSaveProjectAction());
		add(Application.getInstance().getActionManager().getSaveDiagramAction());
		addSeparator();		
		
		add(Application.getInstance().getActionManager().getCloseProjectAction());
		add(Application.getInstance().getActionManager().getCloseDiagramAction());
		add(Application.getInstance().getActionManager().getDeleteSelectedElements());
		addSeparator();			
		
		add(Application.getInstance().getActionManager().getCascadeWindowsAction());
		add(Application.getInstance().getActionManager().getHorizontalWindowsAction());
		add(Application.getInstance().getActionManager().getVerticalWindowsAction());	
		add(Application.getInstance().getActionManager().getPreviousWindowAction());
		add(Application.getInstance().getActionManager().getNextWindowAction());
		addSeparator();	
		
		add(Application.getInstance().getActionManager().getRotateLeftAction());		
		add(Application.getInstance().getActionManager().getRotateRightAction());
		add(Application.getInstance().getActionManager().getUndoAction());	
		add(Application.getInstance().getActionManager().getRedoAction());
		add(Application.getInstance().getActionManager().getZoomInAction());	
		add(Application.getInstance().getActionManager().getZoomOutAction());
		addSeparator();	
		
		add(Application.getInstance().getActionManager().getAboutAction());
		
		
	}
	
}
