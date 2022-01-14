package data_compute;

import java.text.DecimalFormat;
import java.util.HashMap;

import file_system.L1;

public class delta {
	public static void main(String[] args) {
		speed_of_players((float) 49.99, 0);	
	}
	
	private static HashMap<Integer, HashMap<Integer, Short>> speed_table = new HashMap<Integer, HashMap<Integer, Short>>();
	public static Short trackLength;
	
	public static void speed_of_players(float lapDistance, int car) {
		HashMap<Integer, Short> temp_save = new HashMap<Integer, Short>();
		temp_save.put((int) lapDistance, L1.speed[car]);
		speed_table.put(car, temp_save);
//		for (double i = 0; i < 0.1; i = i + 0.1) {
//			String output = "";
//			output += lap_percentage;
//			output += " : ";
//			try {
//				output += speed_table.get(car).get(lap_percentage);
//			} catch (Exception e) {
//				output += "null";
//			}
//			output += " : " + lap_percentage + " : " + car + " : " + L1.speed[car];
//			System.out.println(output);
//		}
	}
	
	static DecimalFormat df = new DecimalFormat();
	public static void delta_time() {
		if (L1.sessionType == 10 || L1.sessionType == 11) {
			df.setMaximumFractionDigits(3);
			L1.Delta.put(0, "Leader");
			for (byte i = 1; i < L1.numActiveCars; i++) {
				int lapDistance_player1 = (int)L1.lapDistance[L1.car_positions.get((byte)(i - 1))];
				int lapDistance_player2 = (int)L1.lapDistance[L1.car_positions.get((byte)i)];
				
				int speed = 0;
				int speed_points = 1;
				float gem_speed_mps = 0;

				for (int o = 0; o < lapDistance_player1 - lapDistance_player2; o++) {
					try {
						speed = speed + speed_table.get((int)L1.car_positions.get((byte)i)).get(o + lapDistance_player2 + 1);
						speed_points++;
					} catch (Exception e) {
					}
				}
				speed = speed + L1.speed[L1.car_positions.get((byte)i)];
				gem_speed_mps = (float) (speed / speed_points / 3.6);
				
				
				try {
					L1.Delta.put((int)i, df.format((lapDistance_player1 - lapDistance_player2) / gem_speed_mps));
				} catch (Exception e) {
					L1.Delta.put((int) i, null);
				}
			}
		}
//		} else {
//			float fastest_lap = L1.bestLapTime[(int)L1.car_positions.get((byte)1)];
//			L1.Delta.put(0, (float)0);
//			for (int i = 1; i < 22; i++) {
//				try {
//					L1.Delta.put(i, L1.bestLapTime[L1.car_positions.get((byte)(i))] - fastest_lap);
//				} catch (Exception e) {
//					L1.Delta.put(i, null);
//				}
//			}
//		}	
	}
}