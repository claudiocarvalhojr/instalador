package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;


/**
 *
 */
public class Utils
{

	/**
	 * @param pathAndFileSource
	 * @param pathAndFileDestiny
	 */
	public void copyExternalFile(final String pathAndFileSource, final String pathAndFileDestiny)
	{
		InputStream input = null;
		File output;
		try
		{
			if (new File(pathAndFileSource).exists())
			{
				input = new FileInputStream(pathAndFileSource);
				output = new File(pathAndFileDestiny);
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
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (final IOException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

	/**
	 * @param pathAndFileSource
	 * @param pathAndFileDestiny
	 */
	public void copyInternalFile(final String pathAndFileSource, final String pathAndFileDestiny)
	{
		InputStream input = null;
		File output;
		try
		{
			input = Installer.class.getResourceAsStream(pathAndFileSource);
			output = new File(pathAndFileDestiny);
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
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (final IOException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
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
			props.load(Installer.class.getClassLoader().getResourceAsStream(pathAndFileProperties));
		}
		catch (final IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return props;
	}

	/**
	 * @param pathAndFileInitOutput
	 * @param text
	 */
	public static void setFileInit(final String pathAndFileInitOutput, final String text)
	{
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try
		{
			fileWriter = new FileWriter(pathAndFileInitOutput);
			printWriter = new PrintWriter(fileWriter);
			printWriter.printf(text);

		}
		catch (final IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally
		{
			if (fileWriter != null)
			{
				try
				{
					fileWriter.close();
				}
				catch (final IOException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			printWriter.close();
		}
	}

	/**
	 * @return Date
	 */
	public static String getDateAndTime()
	{
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

}
