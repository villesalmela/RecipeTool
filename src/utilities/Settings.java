package utilities;

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
	private Settings() {}

	/**
	 * This method will write settings into a file. The file is named settings.txt and placed
	 * into the directory from which the application was started. Each setting
	 * is separated by new line. If the file already exists, it will be replaced.
	 * 
	 * @param settings
	 *            Any number of Strings containing the settings.
	 * @throws IOException if writing fails.
	 * @see utilities.Settings#getFolderPath()
	 */
	public static void writeSettings(String... settings) throws IOException {
		
		// Validate settings
		if (settings == null || settings.length == 0)
			
			// If no settings are passed, create an empty string
			settings = new String[]{""};
		
		try {
			
			// Prepare writing to a file
			try (PrintWriter writer = new PrintWriter(Settings.getFolderPath() + "/settings.txt", "UTF-8")) {
				
				// Write each setting to a new line
				for (String i : settings) {
					writer.println(i);
				}
			}
		} catch (Exception exception) {
			
			// If writing fails, throw exception
			throw new IOException();
		}
	}// end method writeSettings

	/**
	 * This method will read settings from a file. The file is named settings.txt,
	 * and located in the directory from which the application was started.
	 * 
	 * @return List of settings,<br>
	 *         or {@code null} if the reading failed.
	 * @see utilities.Settings#getFolderPath()
	 */
	public static String[] readSettings() {

		// Prepare a list, which shall contain the settings.
		List<String> settings;
		try {
			
			// Read all lines to list
			settings = Files.readAllLines(Paths.get(getFolderPath() + "/settings.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			
			// If reading fails, return null
			return null;
		}

		// Return the list
		return settings.toArray(new String[0]);
	}// end method readSettings

	/**
	 * This method will get the current runtime folder.
	 * 
	 * @return A path to the folder, from which the the jar-file (containing
	 *         this class) was launched.
	 * @throws URISyntaxException
	 *             if the path could not be parsed.
	 */
	public static String getFolderPath() throws URISyntaxException {

		// Get JAR-file's path
		CodeSource codeSource = utilities.Settings.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());

		// Return the path to parent file (folder) of JAR-file
		return jarFile.getParentFile().getPath();
	}// end method getFolderPath
}// end class Settings
