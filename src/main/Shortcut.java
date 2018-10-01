package main;

import net.jimmc.jshortcut.JShellLink;


/**
 *
 */
public class Shortcut
{

	/**
	 * @param nameApp
	 * @param pathFileJar
	 * @param pathFileIcon
	 */
	public static void createDesktopShortcut(final String nameApp, final String pathFileJar, final String pathFileIcon)
	{
		try
		{
			final JShellLink link = new JShellLink();
			link.setFolder(JShellLink.getDirectory("desktop"));
			link.setName(nameApp);
			link.setPath(JShellLink.getDirectory("") + pathFileJar);
			link.setIconLocation(pathFileIcon);
			link.setIconIndex(0);
			link.save();
		}
		catch (final Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
