package Saves;

import java.io.File;
import java.io.IOException;

import application.Main;

public class Temp_create {
	
	public static void Temp() {
		//cre�ert de file struct

		File TempDir = new File(Main.dir + "Saves/temp");
		if (TempDir.exists()){
			//custom error message
		} else {
			TempDir.mkdir();
			
			File File = new File(Main.dir + "Saves/temp/temp.json");
			try {
				File.createNewFile();
			} catch (IOException e1) {
				//custom error message
				e1.printStackTrace();
			}
			
			//cre�ert de folders
			String[] Folders = new String[] {"Car_telemetry","Final_classification", "Lobby_info", "Motion", "Participants", "Car_setups", "Car_status", "Lap_data", "Session", "Event"};
			for (int i = 0; i < Folders.length; i++) {
				File Dir = new File(Main.dir + "Saves/temp/" + Folders[i]);
				Dir.mkdir();
			}
			
			//cre�ert de bestanden
			String[] Files = new String[] {"Car_telemetry", "Event", "Final_classification", "Lobby_info", "Motion", "Participants", "Session"};
			for (int i = 0; i < Files.length; i++) {
				File = new File(Main.dir + "Saves/temp/" + Files[i] + "/" + Files[i] + ".dec");
				try {
					File.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					//custom error message
				}
			}
			
			//cre�ert de bestanden voor de 22 auto's
			for (int i = 0; i < Folders.length - 2; i++) {
				for (int o = 0; o < 22; o++) {
					File Car_File = new File(Main.dir + "Saves/temp/" + Folders[i] + "/" + o + ".dec");
					try {
						Car_File.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
						//custom error message
					}
				}
			}
		}
	}
}