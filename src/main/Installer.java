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
	private static String source;
	private static String output;
	private static String fileJar;
	private static String fileIcon;
	private static String pathIcon;
	private static JButton btInstalar;
	private static JButton btCancelar;
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
		frame.setTitle("Instalador");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setVisible(true);

		lbFolder = new JLabel();
		lbFolder.setText("Pasta:");
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
		panel.add(getBtInstalar());
		panel.add(getBtCancelar());
		panel.add(getSpLog());
		frame.getContentPane().add(panel);

		frame.setVisible(true);

	}

	private static void instalar()
	{

		if (utils == null)
		{
			utils = new Utils();
		}

		txLog.append("iniciando...\n\n");

		final File file = new File(getTfFolder().getText());

		if (!file.exists())
		{
			file.mkdir();
		}

		source = System.getProperty("user.dir");

		final String pathAndFileJarSource = source + "\\" + fileJar;
		final String pathAndFileJarOutput = output + "\\" + fileJar;
		final String pathAndFileIconSource = pathIcon + fileIcon;
		final String pathAndFileIconOutput = output + "\\" + fileIcon;
		final String pathAndFileLinkOutput = JShellLink.getDirectory("desktop") + "\\" + nameApp + ".lnk";
		final String pathAndFileInitOutput = output + "\\init.txt";

		txLog.append("Copy file: " + pathAndFileJarSource);
		txLog.append("\nOutput: " + pathAndFileJarOutput);
		if (new File(pathAndFileJarSource).exists() && !source.equals(output))
		{
			utils.copyExternalFile(pathAndFileJarSource, pathAndFileJarOutput);
		}
		txLog.append("\nOK: " + pathAndFileJarOutput);

		txLog.append("\n\nExtract: " + pathAndFileIconSource);
		txLog.append("\nOutput: " + pathAndFileIconOutput);
		utils.copyInternalFile(pathAndFileIconSource, pathAndFileIconOutput);
		txLog.append("\nOK: " + pathAndFileIconOutput);

		txLog.append("\n\nShortcut: " + pathAndFileJarOutput);
		txLog.append("\nOutput: " + pathAndFileLinkOutput);
		Shortcut.createDesktopShortcut(nameApp, pathAndFileJarOutput, pathAndFileIconOutput, 0);
		txLog.append("\nOK: " + pathAndFileLinkOutput);

		txLog.append("\n\nFile Init: " + pathAndFileInitOutput);
		Utils.setFileInit(pathAndFileInitOutput, output);
		txLog.append("\nOK: " + pathAndFileInitOutput);

		getBtInstalar().setVisible(false);
		getBtCancelar().setText("Fechar");
		getBtCancelar().setBounds(150, 290, 100, 30);

		txLog.append("\n\nfinalizado com sucesso!");
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

	private static JButton getBtInstalar()
	{
		if (btInstalar == null)
		{
			btInstalar = new JButton();
			btInstalar.setBounds(75, 290, 100, 30);
			btInstalar.setText("Instalar");
			btInstalar.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(final MouseEvent event)
				{
					instalar();
				}
			});

		}
		return btInstalar;
	}

	private static JButton getBtCancelar()
	{
		if (btCancelar == null)
		{
			btCancelar = new JButton();
			btCancelar.setBounds(205, 290, 100, 30);
			btCancelar.setText("Cancelar");
			btCancelar.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(final MouseEvent event)
				{
					System.exit(0);
				}
			});
		}
		return btCancelar;
	}

}
