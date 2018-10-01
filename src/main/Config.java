package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;


/**
 *
 */
public class Config
{

	/**
	 * @param pathAndFileSource
	 * @param pathAndFileDestiny
	 */
	public static void copyExternalFile(final String pathAndFileSource, final String pathAndFileDestiny)
	{
		try
		{
			if (new File(pathAndFileSource).exists())
			{
				final InputStream input = new FileInputStream(pathAndFileSource);
				final File output = new File(pathAndFileDestiny);
				FileUtils.copyInputStreamToFile(input, output);
			}
		}
		catch (final FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (final IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * @param pathAndFileSource
	 * @param pathAndFileDestiny
	 */
	public static void copyInternalFile(final String pathAndFileSource, final String pathAndFileDestiny)
	{
		try
		{
			//			InputStream input = getClass().getResourceAsStream(pathAndFileSource);
			final InputStream input = Instalador.class.getResourceAsStream(pathAndFileSource);
			final File output = new File(pathAndFileDestiny);
			FileUtils.copyInputStreamToFile(input, output);
		}
		catch (final FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (final IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * @param pathAndFileProperties
	 * @return Properties
	 */
	public static Properties loadProperties(final String pathAndFileProperties)
	{
		final Properties props = new Properties();
		try
		{
			props.load(Instalador.class.getClassLoader().getResourceAsStream(pathAndFileProperties));
		}
		catch (final IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return props;
	}

}
