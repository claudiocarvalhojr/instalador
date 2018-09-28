package main;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 */
public class Main
{

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel lbPath;
	private static JLabel lbIcon;
	private static JLabel lbLnk;
	private static JTextField tfPath;
	private static JTextField tfIcon;
	private static JTextField tfLnk;

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
			e.printStackTrace();
		}
		catch (final InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (final IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (final UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		initialize();

		final Properties props = loadProperties(false);

		//		System.out.println("PATH: " + props.getProperty("path"));
		//		System.out.println("ICON: " + props.getProperty("icon"));
		//		System.out.println("LNK: " + props.getProperty("lnk"));

	}

	private static final void initialize()
	{
		frame = new JFrame();
		frame.setSize(405, 302);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Instalador");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setVisible(true);

		lbPath = new JLabel();
		lbPath.setText("Caminho:");
		lbPath.setBounds(10, 10, 100, 20);

		lbIcon = new JLabel();
		lbIcon.setText("Ícone:");
		lbIcon.setBounds(10, 50, 100, 20);

		lbLnk = new JLabel();
		lbLnk.setText("Atalho:");
		lbLnk.setBounds(10, 100, 100, 20);

		tfPath = new JTextField("Blablabla");
		tfPath.setBounds(10, 30, 200, 20);

		tfIcon = new JTextField("");
		tfIcon.setBounds(10, 70, 200, 20);

		tfLnk = new JTextField("");
		tfLnk.setBounds(10, 120, 200, 20);

		panel.add(lbPath);
		panel.add(lbIcon);
		panel.add(lbLnk);
		panel.add(tfPath);
		panel.add(tfIcon);
		panel.add(tfLnk);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

	}

	/**
	 * @return Properties
	 * @throws IOException
	 */
	public static Properties loadProperties(final boolean print) throws IOException
	{
		final Properties props = new Properties();
		//		props.load(new FileInputStream("C:/config/config.properties"));
		props.load(Main.class.getClassLoader().getResourceAsStream("main/config.properties"));
		if (print)
		{
			for (final Enumeration elms = props.propertyNames(); elms.hasMoreElements();)
			{
				System.out.println("prop: " + props.getProperty((String) elms.nextElement()));
			}
		}
		return props;
	}

}
