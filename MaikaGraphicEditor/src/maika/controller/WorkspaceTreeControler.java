package maika.controller;

import java.beans.PropertyVetoException;

import maika.application.Application;
import maika.model.workspace.Diagram;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;






import maika.model.workspace.Project;

import javax.swing.JInternalFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class WorkspaceTreeControler implements TreeSelectionListener{
	
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		TreePath path = e.getPath();
		for(int i=0; i<path.getPathCount(); i++){
			if(path.getPathComponent(i) instanceof Diagram){
				Diagram d=(Diagram)path.getPathComponent(i);
				JInternalFrame[] pom = new JInternalFrame[Application.getInstance().getDesktop().getAllFrames().length]; //napravim lokalne internal frameove
				for (int j = 0; j < pom.length; j++) {
					pom[j] = Application.getInstance().getDesktop().getAllFrames()[j];    //prepisem sve frameove u ove lokalne
					if(pom[j].getName() == d.getName()){
						try {
							pom[j].setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
					}
				}
				break;
			}
		}
		

		}
	

}
