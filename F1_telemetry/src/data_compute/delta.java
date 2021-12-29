package data_compute;

import java.text.DecimalFormat;
import java.util.HashMap;

import file_system.L1;

public class delta {
	public static void main(String[] args) {
		speed_of_players((float) 49.99, 0);	
	}
	
	private static final DecimalFormat deciamalen_2 = new DecimalFormat("0.00");
	private static HashMap<Integer, HashMap<Float, Short>> speed_table = new HashMap<Integer, HashMap<Float, Short>>();
	public static Short trackLength;
	
	public static void speed_of_players(float lapDistance, int car) {
		HashMap<Float, Short> temp_save = new HashMap<Float, Short>();
		float lap_percentage = Float.valueOf(deciamalen_2.format(lapDistance / trackLength * 100));
		temp_save.put(lap_percentage, L1.speed[car]);
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
	
	public static void delta_time() {
		if (L1.sessionType == 11 || L1.sessionType == 12) {
			L1.Delta.put(0, (float) 0);
			//System.out.println("Position " + (1) + " : " + L1.Delta.get(0));
			for (byte i = 1; i < 22; i++) {
				float Percentage_player1 = 0;
				float Percentage_player2 = 0;
				try {
					Percentage_player1 = Float.valueOf(deciamalen_2.format(L1.lapDistance[L1.position.get((byte)i)] / trackLength * 100));
					Percentage_player2 = Float.valueOf(deciamalen_2.format(L1.lapDistance[L1.position.get((byte)(i + 1))] / trackLength * 100));
				} catch (Exception e){ }
				int speed = 0;
				int speed_points = 1;
				float gem_speed_mps = 0;
				for (double o = 0; o < Percentage_player1 - Percentage_player2; o = o + 0.1) {
					try {
						speed = speed + speed_table.get(L1.position.get((byte)(i + 1))).get((float)o + Percentage_player2);
						speed_points++;
					} catch (Exception e) { }
				}
				//System.out.println(speed_table.get(L1.position.get(i + 1)).get((float)Percentage_player2));
				gem_speed_mps = (float) (speed / speed_points / 3.6);
				try {
					L1.Delta.put(i + 1, (L1.lapDistance[L1.position.get((byte)i)] - L1.lapDistance[L1.position.get((byte)(i + 1))]) / gem_speed_mps);
				} catch (Exception e) {
					L1.Delta.put((int) i, null);
				}
				//System.out.println("Position " + (i + 1) + " : " + L1.Delta.get(i+ 1));
			}
		} else {
			float fastest_lap = L1.bestLapTime[(int)L1.position.get((byte)1)];
			L1.Delta.put(0, (float)0);
			for (int i = 1; i < 22; i++) {
				L1.Delta.put(i, L1.bestLapTime[L1.position.get((byte)(i))] - fastest_lap);
			}
		}	
	}
}