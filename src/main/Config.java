package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class Config {

	public static void copyExternalFile(String pathAndFileSource, String pathAndFileDestiny) {
		try {
			if (new File(pathAndFileSource).exists()) {
				InputStream input = new FileInputStream(pathAndFileSource);
				File output = new File(pathAndFileDestiny);
				FileUtils.copyInputStreamToFile(input, output);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public static void copyInternalFile(String pathAndFileSource, String pathAndFileDestiny) {
		try {
//			InputStream input = getClass().getResourceAsStream(pathAndFileSource);
			InputStream input = Instalador.class.getResourceAsStream(pathAndFileSource);
		    File output = new File(pathAndFileDestiny);
			FileUtils.copyInputStreamToFile(input, output);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public static Properties loadProperties(boolean isPrintConsole, String pathAndFileProperties) {
		Properties props = new Properties();
		try {
			props.load(Instalador.class.getClassLoader().getResourceAsStream(pathAndFileProperties));
			if (isPrintConsole) {
				for (final Enumeration<?> elms = props.propertyNames(); elms.hasMoreElements();) {
					System.out.println("prop: " + props.getProperty((String) elms.nextElement()));
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return props;
	}

}
