package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.jimmc.jshortcut.JShellLink;


/**
 *
 */
public class Installer
{

	/**
	 *
	 */
	public static Utils utils;
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel lbFolder;
	private static JTextField tfFolder;
	private static String nameApp;
	private static String desktop;
	private static String source;
	private static String output;
	private static String fileJar;
	private static String fileIcon;
	private static String pathIcon;
	private static JButton btInstall;
	private static JButton btCancel;
	private static JTextArea txLog;
	private static JScrollPane spLog;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (final InstantiationException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (final IllegalAccessException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (final UnsupportedLookAndFeelException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		initialize("main/resources/properties/config.properties");

	}

	/**
	 * @param pathAndFileProperties
	 * @throws IOException
	 */
	public static final void initialize(final String pathAndFileProperties) throws IOException
	{
		frame = new JFrame();
		frame.setSize(400, 370);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Installer");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setVisible(true);

		lbFolder = new JLabel();
		lbFolder.setText("Folder:");
		lbFolder.setBounds(15, 10, 100, 20);

		final Properties props = Utils.loadProperties(pathAndFileProperties);
		if (props != null)
		{
			nameApp = props.getProperty("nameApp");
			output = props.getProperty("output");
			fileJar = props.getProperty("fileJar");
			fileIcon = props.getProperty("fileIcon");
			pathIcon = props.getProperty("pathIcon");
		}

		panel.add(lbFolder);
		panel.add(getTfFolder());
		panel.add(getBtInstall());
		panel.add(getBtCancel());
		panel.add(getSpLog());
		frame.getContentPane().add(panel);

		frame.setVisible(true);

	}

	private static void install()
	{

		if (utils == null)
		{
			utils = new Utils();
		}

		txLog.append(Utils.getDateAndTime() + " - starting installation...\n\n");

		final File file = new File(getTfFolder().getText());

		if (!file.exists())
		{
			file.mkdir();
		}

		desktop = JShellLink.getDirectory("desktop");
		source = System.getProperty("user.dir");
		final String pathAndFileJarSource = source + "\\" + fileJar;
		final String pathAndFileJarOutput = output + "\\" + fileJar;
		final String pathAndFileIconSource = pathIcon + fileIcon;
		final String pathAndFileIconOutput = output + "\\" + fileIcon;
		final String pathAndFileLinkOutput = desktop + "\\" + nameApp + ".lnk";
		final String pathAndFileInitOutput = output + "\\init.txt";

		txLog.append(Utils.getDateAndTime() + " - copy file: " + pathAndFileJarSource);
		txLog.append("\n" + Utils.getDateAndTime() + " - output: " + pathAndFileJarOutput);
		if (new File(pathAndFileJarSource).exists() && !source.equals(output))
		{
			utils.copyExternalFile(pathAndFileJarSource, pathAndFileJarOutput);
		}
		txLog.append("\n" + Utils.getDateAndTime() + " - ok: " + pathAndFileJarOutput);

		txLog.append("\n\n" + Utils.getDateAndTime() + " - extract: " + pathAndFileIconSource);
		txLog.append("\n" + Utils.getDateAndTime() + " - output: " + pathAndFileIconOutput);
		utils.copyInternalFile(pathAndFileIconSource, pathAndFileIconOutput);
		txLog.append("\n" + Utils.getDateAndTime() + " - ok: " + pathAndFileIconOutput);

		txLog.append("\n\n" + Utils.getDateAndTime() + " - shortcut: " + pathAndFileJarOutput);
		txLog.append("\n" + Utils.getDateAndTime() + " - output: " + pathAndFileLinkOutput);
		Shortcut.createDesktopShortcut(nameApp, pathAndFileJarOutput, pathAndFileIconOutput, 0);
		txLog.append("\n" + Utils.getDateAndTime() + " - ok: " + pathAndFileLinkOutput);

		txLog.append("\n\n" + Utils.getDateAndTime() + " - file init: " + pathAndFileInitOutput);
		txLog.append("\n" + Utils.getDateAndTime() + " - ok: " + pathAndFileInitOutput);
		txLog.append("\n\n" + Utils.getDateAndTime() + " - installation completed successfully!");

		txLog.setCaretPosition(txLog.getText().length());

		Utils.setFileInit(pathAndFileInitOutput, txLog.getText());

		getBtInstall().setVisible(false);
		getBtCancel().setText("Close");
		getBtCancel().setBounds(150, 290, 100, 30);

	}

	private static JTextField getTfFolder()
	{
		if (tfFolder == null)
		{
			tfFolder = new JTextField();
			tfFolder.setText(output);
			tfFolder.setBounds(15, 30, 350, 20);
		}
		return tfFolder;
	}

	private static JTextArea getTxLog()
	{
		if (txLog == null)
		{
			txLog = new JTextArea();
			txLog.setBounds(15, 70, 350, 200);
			txLog.setEditable(false);
		}
		return txLog;
	}

	private static JScrollPane getSpLog()
	{
		if (spLog == null)
		{
			spLog = new JScrollPane();
			spLog.setBounds(15, 70, 350, 200);
			spLog.setViewportView(getTxLog());
		}
		return spLog;
	}

	private static JButton getBtInstall()
	{
		if (btInstall == null)
		{
			btInstall = new JButton();
			btInstall.setBounds(75, 290, 100, 30);
			btInstall.setText("Install");
			btInstall.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(final MouseEvent event)
				{
					install();
				}
			});

		}
		return btInstall;
	}

	private static JButton getBtCancel()
	{
		if (btCancel == null)
		{
			btCancel = new JButton();
			btCancel.setBounds(205, 290, 100, 30);
			btCancel.setText("Cancel");
			btCancel.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(final MouseEvent event)
				{
					System.exit(0);
				}
			});
		}
		return btCancel;
	}

}
