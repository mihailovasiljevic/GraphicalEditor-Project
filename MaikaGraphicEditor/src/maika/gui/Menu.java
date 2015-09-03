package maika.gui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import maika.application.Application;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	

	public Menu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		JMenu newMenu = new JMenu("New");
		newMenu.setMnemonic('N');
		newMenu.add(Application.getInstance().getActionManager().getNewProjectAction());
		newMenu.addSeparator();
		newMenu.add(Application.getInstance().getActionManager().getNewDiagramAction());
		
		JMenu openMenu = new JMenu("Open");
		openMenu.setMnemonic('O');
		openMenu.add(Application.getInstance().getActionManager().getOpenProjectAction());
		openMenu.addSeparator();
		openMenu.add(Application.getInstance().getActionManager().getOpenDiagramAction());
		
		JMenu saveMenu = new JMenu("Save");
		saveMenu.setMnemonic('S');
		saveMenu.add(Application.getInstance().getActionManager().getSaveProjectAction());
		saveMenu.addSeparator();
		saveMenu.add(Application.getInstance().getActionManager().getSaveDiagramAction());		
		
		JMenu closeMenu = new JMenu("Close/ remove");
		closeMenu.setMnemonic('C');
		closeMenu.add(Application.getInstance().getActionManager().getCloseProjectAction());
		closeMenu.addSeparator();
		closeMenu.add(Application.getInstance().getActionManager().getCloseDiagramAction());
		
		
		fileMenu.add(newMenu);
		fileMenu.addSeparator();
		fileMenu.add(openMenu);
		fileMenu.addSeparator();
		fileMenu.add(saveMenu);
		fileMenu.addSeparator();
		fileMenu.add(closeMenu);
		fileMenu.addSeparator();
		fileMenu.add(Application.getInstance().getActionManager().getPrintAction());
		fileMenu.addSeparator();
		fileMenu.add(Application.getInstance().getActionManager().getImportAction());
		fileMenu.addSeparator();
		fileMenu.add(Application.getInstance().getActionManager().getExportAction());

		add(fileMenu);
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		
		editMenu.add(Application.getInstance().getActionManager().getRotateRightAction());
		editMenu.addSeparator();
		editMenu.add(Application.getInstance().getActionManager().getRotateLeftAction());		
		editMenu.addSeparator();
		editMenu.add(Application.getInstance().getActionManager().getUndoAction());		
		editMenu.addSeparator();
		editMenu.add(Application.getInstance().getActionManager().getRedoAction());		
		
		add(editMenu);
		
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		viewMenu.add(Application.getInstance().getActionManager().getCascadeWindowsAction());
		viewMenu.addSeparator();
		viewMenu.add(Application.getInstance().getActionManager().getHorizontalWindowsAction());
		viewMenu.addSeparator();
		viewMenu.add(Application.getInstance().getActionManager().getVerticalWindowsAction());
		viewMenu.addSeparator();
		viewMenu.add(Application.getInstance().getActionManager().getPreviousWindowAction());
		viewMenu.addSeparator();
		viewMenu.add(Application.getInstance().getActionManager().getNextWindowAction());
		add(viewMenu);		
			
		JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic(KeyEvent.VK_T);
		toolsMenu.add(Application.getInstance().getActionManager().getSelectGTAction());
		toolsMenu.addSeparator();
		toolsMenu.add(Application.getInstance().getActionManager().getTriangleGTAction());
		toolsMenu.addSeparator();
		toolsMenu.add(Application.getInstance().getActionManager().getCircleGTAction());
		toolsMenu.addSeparator();
		toolsMenu.add(Application.getInstance().getActionManager().getRectangleGTAction());
		
		
		add(toolsMenu);		
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		helpMenu.add(Application.getInstance().getActionManager().getAboutAction());
		add(helpMenu);			
		
	}
	
}
