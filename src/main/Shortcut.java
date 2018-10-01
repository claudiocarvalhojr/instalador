package main;

import net.jimmc.jshortcut.JShellLink;

public class Shortcut {

	public static void createDesktopShortcut(String nameApp, String pathFileJar, String pathFileIcon) {
		try {
			JShellLink link = new JShellLink();
			link.setFolder(JShellLink.getDirectory("desktop"));
			link.setName(nameApp);
			link.setPath(JShellLink.getDirectory("") + pathFileJar);
			link.setIconLocation(pathFileIcon);
			link.setIconIndex(0);
			link.save();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}