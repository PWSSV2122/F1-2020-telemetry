package manager;

import application.Main;
import packet_struct.Motion.Motion_car;

public class program_manager {
	
	public static void main(String[] args) {
		new Thread() {
            @Override
            public void run() {
            	Main.main(args);
            }
        }.start();
        
        
	Motion_car[] test = new Motion_car[2];
	test[0] = new Motion_car((float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0);
	System.out.println(test[0].getRoll());
	}
}
