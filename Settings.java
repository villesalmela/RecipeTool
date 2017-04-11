package recipeTool;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.List;

/**
 * This class contains methods for writing settings to and reading settings from
 * a file.<br>
 * The file must be located in the same directory as this application.
 * 
 * @author Ville Salmela
 *
 */
public class Settings {

	/**
	 * This constructor is private, thus preventing the instantiation of this
	 * class.
	 */
	private Settings() {
	}

	/**
	 * This method will write settings into a file (settings.txt) and place it
	 * into the directory from which the application was started. Each setting
	 * is separated by new line.
	 * 
	 * @param settings
	 *            Any number of Strings containing the settings. Cannot be {@code null} or
	 *            empty.
	 * @throws IOException if writing fails.
	 * @throws IllegalArgumentException
	 *             if {@code settings} is {@code null} or empty.
	 */
	public static void writeSettings(String... settings) throws IOException {
		if (settings == null || settings.length == 0)
			throw new IllegalArgumentException();
		try {
			try (PrintWriter writer = new PrintWriter(Settings.getFolderPath() + "/settings.txt", "UTF-8")) {
				for (String i : settings) {
					writer.println(i);
				}
			}
		} catch (Exception exception) {
			throw new IOException();
		}

	}// end method writeSettings

	/**
	 * This method will read settings from a file named settings.txt, located in
	 * the directory from which the application was started.
	 * 
	 * 
	 * @return List of settings,<br>
	 *         or {@code null} if the reading failed.
	 * @see recipeTool.Settings#getFolderPath()
	 */
	public static List<String> readSettings() {

		List<String> settings;
		try {
			settings = Files.readAllLines(Paths.get(getFolderPath() + "/settings.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			return null;
		}

		return settings;
	}// end method readSettings

	/**
	 * This method will get the current runtime folder.
	 * 
	 * @return A path to the folder, from which the the jar-file (containing
	 *         this class) was launched.
	 * @throws URISyntaxException
	 *             if the path could not be parsed.
	 * @see recipeTool.Settings#getFolderPath()
	 */
	private static String getFolderPath() throws URISyntaxException {

		CodeSource codeSource = recipeTool.Settings.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());

		return jarFile.getParentFile().getPath();
	}// end method getFolderPath

}// end class Settings
