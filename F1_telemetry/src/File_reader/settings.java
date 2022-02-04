package File_reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class settings {
	static String[] names = new String[] {"Poort", "Send Rate"};
	public static void read() {
		String[] data = new String[0];
		try {
			File myObj = new File("src/Settings/Settings.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String[] value = myReader.nextLine().split(":");
				if (value[0].equals("Poort")) {
					Settings.Settings_var.Poort = Integer.valueOf(value[1]);
				} else if (value[0].equals("Send Rate")) {
					Settings.Settings_var.send_rate = Integer.valueOf(value[1]);
				}
			}
			myReader.close();
			for(int i = 0; i < data.length; i++) {
				System.out.println(data[i]);
			}
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	}

	public static void write() {
		try {
			FileWriter fw = new FileWriter("src/Settings/Settings.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			String data = "Poort:" + Settings.Settings_var.Poort;
			bw.write(data);
			bw.newLine();
			data = "Send Rate:" + Settings.Settings_var.send_rate;
			bw.write(data);
		    bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
}
