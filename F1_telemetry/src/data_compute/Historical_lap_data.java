package data_compute;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import file_system.L1;

public class Historical_lap_data {
	
	public static byte lap_num[] = new byte[22];
	public static int starting_lap = 0;
	static boolean first = true;
	
	public static void S1_and_S2() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		    	for (int i = 0; i < 22; i++) {
		    		if (L1.sector2TimeInMS[0] == 0) {
			    	} else {
			    		L1.S1_Times.put(null, null);
			    		L1.S2_Times.put(null, null);
			    	}
		    	}
		    }
		};
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateClass, 0, 10000, TimeUnit.MILLISECONDS);
	}
	
	public static void Lap_and_S3(int lap, int car) {
		if (first == true) {
			first = false;
			starting_lap = lap;
			System.out.println(starting_lap);
		}
		try {
			float S3 = L1.lastLapTime[car] * 1000 - L1.S1_Times.get(car).get(lap - 1) - L1.S2_Times.get(car).get(lap - 1);
			HashMap<Integer, Float> temp = new HashMap<Integer, Float>();
			temp.put(lap - 1, S3);
			L1.S3_Times.put(car, temp);
			
			temp.clear();
			temp.put(lap - 1, L1.lastLapTime[car]);
			L1.Lap_Times.put(car, temp);
		} catch (Exception e) {
			
		}
	}
}
