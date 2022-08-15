package manager;

import Global_vars.PageUpdate;
import Global_vars.Settings;
import Global_vars.Appendices.AppendicesLoader;
import Global_vars.Delta.DeltaJsonLoad;
import Inkoming.Inkoming;
import Saves.Temp_create;
import errors.Error;

public class program_manager {
	
	public static void main(String[] args) {
		Error.createLogFile();
		Error.error("Log file created", 0);
		Error.error("Log files folder size: " + Settings.logFileSize + "KB", 0);
		
		Temp_create.Temp();
		Error.error("Temp save space created", 0);
		
		AppendicesLoader.LoadAppendices();
		DeltaJsonLoad.LoadDeltaJson();
		Error.error("Json loaded", 0);
		
		PageUpdate.getPageNames();
		PageUpdate.getSceneNames();
		Error.error("Page Names loaded", 0);
		
		System.out.println("Startup done");
		Error.error("Startup done", 0);
		
		//Saves.decode.decode.runSave = true;
		//Saves.decode.decode.decode_save("temp");

		//Saves.json.jsonMain.jsonToSave(null, 60);
//		Error.deleteLogFiles();
//		Lap_data one = new Lap_data((float)0, (float)0, (short)0, (short)0, (float)0, (byte)0, (short)0, (short)0, (short)0, (short)0, (byte)0, (short)0, (byte)0, (short)0, (byte)0, (float)0, (float)0, (float)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0);
//		Lap_data two = new Lap_data((float)1, (float)1, (short)1, (short)1, (float)1, (byte)1, (short)1, (short)1, (short)1, (short)1, (byte)1, (short)1, (byte)1, (short)1, (byte)1, (float)1, (float)1, (float)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1);
//		long start = System.nanoTime();
//		for (int i = 0; i < 100000; i++) {
//			toSave.ToFile(one, two, 0, 1, "Lap_data/" + 0, structClassNames.Lap_data);
//		}
//		System.out.println("MS: " + (float)((System.nanoTime() - start)) / 1000000);
		
		new Thread() {
            @Override
            public void run() {
            	applicationNew.Main.main(args);
            	//Main.main(args);
            	Inkoming.recieve();
            }
        }.start();
	}
}