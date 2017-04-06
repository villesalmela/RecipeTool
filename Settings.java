package recipeTool;

import java.io.File;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods for writing settings to a file and reading settings from a file.<br>
 * The file must be located in the same directory as this application.
 * @author Ville Salmela
 *
 */
public class Settings {
	
	/**
	 * This method will write settings into a file (settings.txt)
	 * and place it into the directory from which the application was started.
	 * Each setting is separated by new line.
	 * @param settings A list containing the settings. Cannot be {@code null} or empty.
	 * @return {@code true} if the writing was successful.<br>{@code false} if the writing did not succeed.
	 * @throws IllegalArgumentException if {@code settings} is {@code null} or empty.
	 */
	private Settings(){}
	
	public static boolean writeSettings(ArrayList<String> settings){
	   if (settings == null || settings.isEmpty()) throw new IllegalArgumentException();
		try{
		   try(PrintWriter writer = new PrintWriter(Settings.getFolderPath()+"/settings.txt", "UTF-8")){
			   for (String i : settings){
		    	writer.println(i);
			   }
			   return true;
		   }
	   } catch (Exception exception){
		   return false;
	   }
	    
	}//end method writeSettings

	/**
	 * This method will read settings from a file named settings.txt, located in the directory from which the application was started.
	 * @return List of settings,<br>or {@code null} if the reading failed.
	 */
	public static List<String> readSettings(){

		List<String> settings;
		try {
			settings = Files.readAllLines(Paths.get(getFolderPath()+"/settings.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			return null;
		}
		
		return settings;
	}//end method readSettings

	private static String getFolderPath() throws URISyntaxException{
		
		CodeSource codeSource = RecipeTool.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		
		return jarFile.getParentFile().getPath();
	}//end method getFolderPath

}//end class Settings
