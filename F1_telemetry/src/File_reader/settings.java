package File_reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import application.Main;

public class settings {
	static String[] names = new String[] {"Poort", "Send Rate"};
	public static void read() {
		File myObj = new File(Main.dir + "Settings/Settings.txt");
		Scanner myReader;
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String[] value = myReader.nextLine().split(":");
				if (value[0].equals("Poort")) {
					Settings.Settings_var.Poort = Integer.valueOf(value[1]);
				} else if (value[0].equals("Send Rate")) {
					Settings.Settings_var.send_rate = Integer.valueOf(value[1]);
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void write() {
		try {
			FileWriter fw = new FileWriter(Main.dir + "Settings/Settings.txt");
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
