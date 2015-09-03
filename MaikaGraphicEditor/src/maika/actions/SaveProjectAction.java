package maika.actions;



import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import maika.application.Application;
import maika.gui.WorkspaceTree;
import maika.model.workspace.Project;

@SuppressWarnings("serial")
public class SaveProjectAction extends AbstractAction{
	public SaveProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/saveproject.png"));
		putValue(NAME, "Save diagram");
		putValue(SHORT_DESCRIPTION, "Save project");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//saveProject(Application.getCurrentProject().getProjectFile());
		//UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		//ud.setVisible(true);
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFilterFile());
		
		Project project=Application.getInstance().getWorkspaceTree().getCurrentProject();
		File projectFile=project.getProjectFile();
		
		if (!project.isChanged()){
			return;
		}
		
		if (project.getProjectFile()==null){
		         if(jfc.showSaveDialog(Application.getInstance())==JFileChooser.APPROVE_OPTION){
		                   projectFile=jfc.getSelectedFile();           	 
		        	 
		         }else{
		        	return; 
		         }
		         
		}     
	      
		         
	    ObjectOutputStream os;
		try {
			/*
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			os.writeObject(project);
			project.setProjectFile(projectFile);
			project.setChanged(false);
			*/
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			String newName = projectFile.getName().replace(".mihailo", "");
			project.setName(newName);
			os.writeObject(project);
			SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
			//os.writeObject(MainFrame.getActiveDiagram().getDiagramElements().get(0).getStroke());
			project.setProjectFile(projectFile);
			project.setChanged(false);
			os.flush();
			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	
		
	
		
	}
	
	public void saveProject(File projectFileSave){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFilterFile());
		
		if (Application.getCurrentProject() == null) {
			JOptionPane.showMessageDialog(Application.getInstance(), "Nema projekata za cuvanje!", "Error prilikom pokusaja cuvanja!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Project project = Application.getCurrentProject();
		File projectFile = projectFileSave;
		
		if (project.isChanged()){
			return;
		}
		
		if (project.getProjectFile() == null){
//		         if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
//		                   projectFile = jfc.getSelectedFile();           	 
//		        	 
//		         }else{
//		        	return; 
//		         }
			projectFile = new File(((WorkspaceTree)Application.getInstance().getWorkspaceModel().getRoot()).getCurrentWorkspaceFolder().getAbsoluteFile() + "\\" + project.getName());
		         
		}
		
		if (!projectFile.getName().contains(".")) {
			projectFile = new File (projectFile.getAbsolutePath() + ".mihailo");
		}
	      
		         
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			String newName = projectFile.getName().replace(".mihailo", "");
			project.setName(newName);
			os.writeObject(project);
			SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
			//os.writeObject(MainFrame.getActiveDiagram().getDiagramElements().get(0).getStroke());
			project.setProjectFile(projectFile);
			project.setChanged(true);
			os.flush();
			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	
		
	
		
	}
	
	public void saveProject(Project project) {
		if (project == null)
			return;
		
		File projectFile = project.getProjectFile();
		
		if (projectFile == null)
			projectFile = new File(((WorkspaceTree)Application.getInstance().getWorkspaceModel().getRoot()).getCurrentWorkspaceFolder().getAbsoluteFile() + "\\" + project.getName());
	         
		if (!projectFile.getName().contains(".")) {
			projectFile = new File (projectFile.getAbsolutePath() + ".mihailo");
		}
	      
		         
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			os.writeObject(project);
			project.setProjectFile(projectFile);
			project.setChanged(true);
			os.flush();
			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

	
	
	
	
	
