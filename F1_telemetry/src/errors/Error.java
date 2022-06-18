package errors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import application.Main;
import Global_vars.Settings;

public class Error {
	
	private static String file;
	private static String[] severityText = new String[] {"Info", "Warning", "Critical"};
	
	public static void error(String error, int severity) {
		try {
			FileOutputStream write = new FileOutputStream(Main.dir + "errors/logs/" + file + ".txt", true);
			
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date(); 
		    
			String errorText = formatter.format(date) + " " + severityText[severity] + ": " + error + "\n";
		    Charset charset = StandardCharsets.UTF_16;
		    byte[] byteArrray = errorText.getBytes(charset);
		    
			write.write(byteArrray);
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createLogFile() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");  
	    Date date = new Date(); 
		
		File File = new File(Main.dir + "errors/logs/" + formatter.format(date) + ".txt");
		try {
			File.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		file = formatter.format(date);
		
	    File directoryPath = new File(Main.dir + "errors/logs/");
	    String contents[] = directoryPath.list();
	    long size = 0;
	    for (int i = 0; i < contents.length; i++) {
	    	Path path = Paths.get(Main.dir + "errors/logs/" + contents[i]);
	    	try {
				size = Files.size(path) + size;
			} catch (IOException e) {
			}
	    }
	    Settings.logFileSize = (float)size / 1024;
	}
	
	public static void deleteLogFiles() {
		File directoryPath = new File(Main.dir + "errors/logs/");
	    String contents[] = directoryPath.list();
	    for (int i = 0; i < contents.length; i++) {
	    	if (!contents[i].equals(file + ".txt")) {
	    		File myObj = new File(Main.dir + "errors/logs/" + contents[i]);
	    		try {
	    			myObj.delete();
	    		} catch (Exception e) {
	    			error("Failed to delete log file: " + contents[i], 1);
	    		}
	    	}
	    }
	    error("Done deleting log files", 0);
	}
}