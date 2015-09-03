package maika.view;

import maika.model.workspace.Diagram;
import maika.model.workspace.Element;
import maika.model.workspace.Project;



import java.awt.Color;
//import java.awt.Color;
import java.awt.Component;
//import java.net.URL;



//import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer{
	public WorkspaceTreeCellRenderer() {
		
		// TODO Auto-generated constructor stub
	}
	
	  public Component getTreeCellRendererComponent(
              JTree tree,
              Object value,
              boolean sel,
              boolean expanded,
              boolean leaf,
              int row,
              boolean hasFocus) {
                  super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
                  
             
             if (value instanceof Diagram ) {
                 /*URL imageURL = getClass().getResource("src/images/tdiagram.png");
                 Icon icon = null;
                 if (imageURL != null)                       
                     icon = new ImageIcon(imageURL);*/
            	 ImageIcon icon = new ImageIcon("src/images/tdiagram.png");
                 setIcon(icon);
 
             } else if (value instanceof Project ) {
            	 /*
                 URL imageURL = getClass().getResource("src/images/tproject.png");
                 Icon icon = null;
                 if (imageURL != null)                       
                     icon = new ImageIcon(imageURL);
                     */
            	 ImageIcon icon = new ImageIcon("src/images/tproject.png");
                 setIcon(icon);
                   
            } else if (value instanceof Element){
           	 ImageIcon icon = new ImageIcon("src/images/tdiagram.png");
             setIcon(icon);
            }
             setBackgroundNonSelectionColor(new Color(255, 255, 255));
            return this;
}

}
