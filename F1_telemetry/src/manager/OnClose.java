package manager;

import java.io.File;

import applicationNew.Save_file;

public class OnClose {

	public static void ProgramClose() {
		File_reader.settings.write();
		File file = new File("Saves/temp/Lap_Data.dec");
		if (file.length() == 0) {
		} else {
			Save_file.display("Save File");
		}
		System.exit(0);
	}
}
