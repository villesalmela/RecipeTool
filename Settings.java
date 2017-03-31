package recipeTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Settings {
	
	public static void writeSettings(ArrayList<String> settings) throws FileNotFoundException, UnsupportedEncodingException, URISyntaxException {
	   
		PrintWriter writer = new PrintWriter(Settings.getFolderPath()+"/settings.txt", "UTF-8");
	    
	    for (String i : settings){
	    	writer.println(i);
	    }
	    
	    writer.close();
	    
	}//end method writeSettings

	public static String[] readSettings() throws URISyntaxException, IOException{
		
		StringBuilder temp = new StringBuilder();
		FileReader reader=new FileReader(Settings.getFolderPath()+"/settings.txt");  
		int i;  
		
		while((i=reader.read())!=-1){
			temp.append((char)i);
		}
		
		reader.close();

		ArrayList<String> temp2 = new ArrayList<>(Arrays.asList(temp.toString().split("\n")));
		
		String[] settings = new String[temp2.size()];
		i=0;
		
		for (String j : temp2){
			settings[i]=j.trim();
			i++;
		}
		
		return settings;
	}//end method readSettings

	public static String getFolderPath() throws URISyntaxException{
		
		CodeSource codeSource = RecipeTool.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		
		return jarFile.getParentFile().getPath();
	}//end method getFolderPath

	public static String convertStreamToString(InputStream is) {
	    
		try(Scanner scanner = new Scanner(is).useDelimiter("\\A")){
	    	return scanner.hasNext() ? scanner.next() : "";
	    }
	}//end method convertStreamToString

}//end class Settings
