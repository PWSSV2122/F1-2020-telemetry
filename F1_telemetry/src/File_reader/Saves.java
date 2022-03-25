package File_reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import application.Main;

public class Saves {
	public static String[] read() {
		String[] data = new String[0];
		try {
			File myObj = new File(Main.dir + "Saves/Saves.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String[] data_temp = new String[data.length];
				for (int i = 0; i < data.length; i++) {
					data_temp[i] = data[i];
				}
				data = new String[data.length + 1];
				for (int i = 0; i < data_temp.length; i++) {
					data[i] = data_temp[i];
				}
				data[data_temp.length] = myReader.nextLine();
			}
			myReader.close();
			for(int i = 0; i < data.length; i++) {
			}
	    } catch (FileNotFoundException e) {
	    }
		return data;
	}

	public static void write(String[] save) {
		try {
			FileWriter fw = new FileWriter(Main.dir + "Saves/Saves.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < save.length; i++) {
				bw.write(save[i]);
			    bw.newLine();
			}
		    bw.close();
		} catch (IOException e) {
		}
	    
	}
}
