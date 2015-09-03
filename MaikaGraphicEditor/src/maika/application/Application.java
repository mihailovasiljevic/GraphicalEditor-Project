package maika.application;


import maika.gui.Menu;




import maika.gui.Pallete;
import maika.gui.StatusBar;
import maika.gui.WorkspaceTree;
import maika.model.workspace.Project;
import maika.model.workspace.WorkspaceModel;
import maika.view.CascadeDiagramView;

import maika.actions.ActionManager;
import maika.gui.Toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;




@SuppressWarnings("serial")
public class Application extends JFrame{

	private static Application instance=null;
	private JDesktopPane desktop;
	private JMenuBar menu;
	private Toolbar toolbar;
	private Pallete pallete;
	private StatusBar statusBar;
	//private JPanel panel;
    private WorkspaceModel workspaceModel;
    private WorkspaceTree workspaceTree;
    private boolean selectedFromTree;
    
	private ArrayList<CascadeDiagramView> diagramViews = new ArrayList<CascadeDiagramView>();
    
    private ActionManager actionManager;
	
	private Application() {
		
	}
	
	private void initialise(){
		
		actionManager=new ActionManager();
		initialiseWorkspaceTree();
		initialiseGUI();
		
		try {
			//.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

	
	
	private void initialiseGUI(){
		menu=new Menu();
		setJMenuBar(menu);
		
		toolbar = new Toolbar();
		getContentPane().add(toolbar,BorderLayout.NORTH);
		
		Color lightBlue = new Color(231,241,245);
		desktop=new JDesktopPane(){
			
			@Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                ImageIcon imgico = new ImageIcon("src/images/maikaBackground.png");
                final Image img = imgico.getImage();
				grphcs.drawImage(img , 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(desktop.getWidth(), desktop.getHeight());
            }
		};
		//desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		pallete = new Pallete();	
		getContentPane().add(pallete,BorderLayout.EAST);
		
		statusBar = new StatusBar();
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		JScrollPane scroll=new JScrollPane(workspaceTree);
		scroll.setMinimumSize(new Dimension(200,150));
		scroll.getViewport().setBackground(lightBlue);
		scroll.setOpaque(true);
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
		add(split,BorderLayout.CENTER);
		split.setDividerLocation(255);
		

		/*
		panel = new JPanel();
		
		add(panel,BorderLayout.SOUTH);
*/
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		Image img = kit.getImage("src/images/maika_graphic_editor_ico.png");
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MAIKA - the mother of all editors");		
		
	}
	
	private void initialiseWorkspaceTree(){
		workspaceTree=new WorkspaceTree();
		
		workspaceModel = new WorkspaceModel();
		
		//Workspace root = (Workspace) workspaceModel.getRoot();
		//workspaceTree.setRootVisible(false);
		
		
		workspaceTree.setModel(workspaceModel);
		ToolTipManager.sharedInstance().registerComponent(workspaceTree);

	}
	
	public ActionManager getActionManager(){
		return actionManager;
	}
	
	public static Application getInstance(){
		if (instance==null){
			instance=new Application();
			instance.initialise();
		}
		return instance;
	}

	public JDesktopPane getDesktop() {
		return desktop;
	}


	public WorkspaceTree getWorkspaceTree() {
		return workspaceTree;
	}


	public WorkspaceModel getWorkspaceModel() {
		return workspaceModel;
	}
	
	public void setSelectedFromTree(boolean selectedFromTree) {
		this.selectedFromTree = selectedFromTree;
	}

	public boolean isSelectedFromTree() {
		return selectedFromTree;
	}
	
	public void setDiagramViews(ArrayList<CascadeDiagramView> diagramViews) {
		this.diagramViews = diagramViews;
	}

	public ArrayList<CascadeDiagramView> getDiagramViews() {
		return diagramViews;
	}
	
	public static Project getCurrentProject() {
		if (getInstance().getWorkspaceTree().getSelectionPath().getPathCount() > 1)
			return (Project) getInstance().getWorkspaceTree().getSelectionPath().getPathComponent(1);
		else
			return null;

	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}


	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}
	
}
