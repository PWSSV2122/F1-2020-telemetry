package data_compute;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.TimingPage;
import file_system.L1;

public class Historical_lap_data {
	
	public static byte lap_num[] = new byte[22];
	public static int starting_lap = 0;
	static boolean first = true;
	
	public static void S1_and_S2() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		    	for (int i = 0; i < 22; i++) {
		    		if (L1.sector2TimeInMS[i] != 0) {
			    		try {
			    				HashMap<Integer, Short> temp = new HashMap<Integer, Short>();
			    				try {
			    					temp.putAll(L1.S1_Times.get(i));
			    				} catch (Exception e) {	
			    				}
			    				temp.put((int) lap_num[i], L1.sector1TimeInMS[i]);
			    				L1.S1_Times.put(i, temp);
			    				HashMap<Integer, Short> temp2 = new HashMap<Integer, Short>();
			    				try {
			    					temp2.putAll(L1.S2_Times.get(i));
			    				} catch (Exception e) {	
			    				}
			    				temp2.put((int) lap_num[i], L1.sector2TimeInMS[i]);
			    				L1.S2_Times.put(i, temp2);
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    		}
			    	}
		    	}
		    }
		};
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateClass, 0, 1000, TimeUnit.MILLISECONDS);
	}
	
	public static void Lap_and_S3(int lap, int car) {
		if (first == true || lap < starting_lap) {
			first = false;
			starting_lap = lap;
		}
		try {
			String S3 = TimingPage.MsTo_min_sec_ms(Math.round((L1.lastLapTime[car] * 1000) - (L1.S1_Times.get(car).get((int)lap_num[car])) - (L1.S2_Times.get(car).get((int)lap_num[car]))), 1);
			HashMap<Integer, String> temp = new HashMap<Integer, String>();
			try {
				temp.putAll(L1.S3_Times.get(car));
			} catch (Exception e) {
			}
			temp.put(lap - 1, S3);
			L1.S3_Times.put(car, temp);
			
			HashMap<Integer, Float> temp2 = new HashMap<Integer, Float>();
			try {
				temp2.putAll(L1.Lap_Times.get(car));	
			} catch (Exception e) {
			}
			temp2.put(lap - 1, L1.lastLapTime[car]);
			L1.Lap_Times.put(car, temp2);
		} catch (Exception e) {
			
		}
	}
}
