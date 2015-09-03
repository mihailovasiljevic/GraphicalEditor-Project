package maika.actions;



import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import maika.application.Application;
import maika.gui.WorkspaceTree;
import maika.model.workspace.Project;
import maika.model.workspace.Workspace;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class OpenProjectAction extends AbstractAction{

	public OpenProjectAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/openproject.png"));
		putValue(NAME, "Open project");
		putValue(SHORT_DESCRIPTION, "Open project");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		//ud.setVisible(true);
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFilterFile());
		
		if(jfc.showOpenDialog(Application.getInstance())==JFileChooser.APPROVE_OPTION){
			try {
				ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
				  
				Project p=null;
				try {
					p = (Project) os.readObject();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    
			      Application.getInstance().getWorkspaceTree().addProject(p);
				
				  for (int i=0;i<p.getDiagramCount();i++){
				    CascadeDiagramView view=new CascadeDiagramView();
				   // p.getDiagram(i).getDiagramModel().addUpdateListener();
				    view.setDiagram(p.getDiagram(i));
				    
				    Application.getInstance().getDesktop().add(view);
				    Application.getInstance().getDesktop().validate();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			
		
		
	}
	
		//openProject(null);
	}
	
	public void openProject(File projectOpenFile){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFilterFile());
		boolean pomFlag = false;
		try {
			ObjectInputStream os;
			if (projectOpenFile == null) {
				if(jfc.showOpenDialog(Application.getInstance()) == JFileChooser.APPROVE_OPTION)
					os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
				else
					return;
			}
			else {
				os = new ObjectInputStream(new FileInputStream(projectOpenFile));
				pomFlag = true;
			}
			
			Project p = null;
			try {
				p = (Project) os.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    boolean flag = true;

		    Workspace workspace = (Workspace) Application.getInstance().getWorkspaceModel().getRoot();
		    int i = 0;
		    
		    for (i = 0; i < workspace.getProjects().size(); i++) {
		    	if (workspace.getProjects().get(i).getName().equals(p.getName())) {
		    		flag = false;
		    		break;
		    	}
		    }
		    if (pomFlag) {
		    	p.setChanged(true);
		    }
		    if (flag) {
		      workspace.addProject(p);
		      SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
		      CascadeDiagramView.openFrameCount += p.getDiagrams().size();
		    } else {
		    	if (JOptionPane.showConfirmDialog(Application.getInstance(), "A project with the same name already exists. Replace?") == JOptionPane.YES_OPTION) {
			    	for (int j = 0; j < workspace.getProjects().get(i).getDiagrams().size(); j++) {
			    		workspace.getProjects().get(i).getDiagrams().get(j).getDiagramModel().close();
			    	}
			    	workspace.getProjects().set(i, p);
			    	SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
			    	CascadeDiagramView.openFrameCount += p.getDiagrams().size();
		    	}
		    }
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}


}
