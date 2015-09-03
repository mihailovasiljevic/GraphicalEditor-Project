package maika.actions;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DiagramFilterFile extends FileFilter{

	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || 
                f.getName().toLowerCase().endsWith(".mihailo"));
	}

	@Override
	public String getDescription() {
		return ("MaikaEditor program files(*.mihailo)");
	}
	

}
