package manager;

import Inkoming.Inkoming;
import Saves.Save_system_startup;
import Saves.Temp_create;

public class program_manager {
	
	public static void main(String[] args) {		
		Save_system_startup.start();
	
		Temp_create.Temp();
		
		System.out.println("Startup done");
		
		new Thread() {
            @Override
            public void run() {
//            	Main.main(args);
            	Inkoming.recieve();
            }
        }.start();
	}
}