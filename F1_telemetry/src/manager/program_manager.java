package manager;

import java.util.concurrent.TimeUnit;

import Inkoming.Inkoming;
import application.Main;
import packet_struct.Lap_data;
import packet_struct.Motion.Motion_car;

public class program_manager {
	
	public static void main(String[] args) {
		Lap_data test = new Lap_data((float)0, (float)0, (short)0, (short)0, (float)0, (byte)0, (short)0, (short)0, (short)0, (short)0, (byte)0, (short)0, (byte)0, (short)0, (byte)0, (float)0, (float)0, (float)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0);
		Lap_data test2 = new Lap_data((float)0, (float)0, (short)0, (short)0, (float)0, (byte)0, (short)0, (short)0, (short)0, (short)0, (byte)0, (short)0, (byte)0, (short)0, (byte)0, (float)0, (float)0, (float)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)1);

		for (int i = 0 ; i < 30; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Lap_data.compaire(test, test2);
		}
		new Thread() {
            @Override
            public void run() {
//            	Main.main(args);
            	Inkoming.recieve();
            }
        }.start();
//        
//        
//	Motion_car[] test = new Motion_car[2];
//	test[0] = new Motion_car((float)0, (float)0, (float)0, (float)0, (float)0, (float)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (float)0, (float)0, (float)0, (float)0, (float)0, (float)0);
//	System.out.println(test[0].getRoll());
		
	}
}
