package maika.actions;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconResizer {
	String path;
	
	IconResizer(String path){
		this.path = path;
	}
	
	public void resizeImage(ImageIcon iconForResize, String path){
		iconForResize = new ImageIcon(path); 
		Image img = iconForResize.getImage();  
		Image newimg = img.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);  
		iconForResize = new ImageIcon(newimg);  
	}
}
