package manager;

import Global_vars.structClassNames;
import Inkoming.Inkoming;
import Saves.Temp_create;
import Saves.toSave;
import application.Main;
import packet_struct.Lap_data;

public class program_manager {
	
	public static void main(String[] args) {			
		Temp_create.Temp();
		
		System.out.println("Startup done");
		Lap_data one = new Lap_data((float)0, (float)0, (short)0, (short)0, (float)0, (byte)0, (short)0, (short)0, (short)0, (short)0, (byte)0, (short)0, (byte)0, (short)0, (byte)0, (float)0, (float)0, (float)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0);
		Lap_data two = new Lap_data((float)1, (float)1, (short)1, (short)1, (float)1, (byte)1, (short)1, (short)1, (short)1, (short)1, (byte)1, (short)1, (byte)1, (short)1, (byte)1, (float)1, (float)1, (float)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1);
		long start = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			toSave.ToFile(one, two, 0, 1, "Lap_data/" + 0, structClassNames.Lap_data);
		}
		System.out.println("MS: " + (float)((System.nanoTime() - start)) / 1000000);
		
		new Thread() {
            @Override
            public void run() {
            	Main.main(args);
            	Inkoming.recieve();
            }
        }.start();
	}
}