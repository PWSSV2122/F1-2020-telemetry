package data_compute;

import java.text.DecimalFormat;

public class delta {
//	public static void main(String[] args) {
//		speed_of_players((float) 49.99, 0);	
//	}
//	
//	private static HashMap<Integer, HashMap<Integer, Short>> speed_table = new HashMap<Integer, HashMap<Integer, Short>>();
//	public static Short trackLength;
//	
//	public static void speed_of_players(float lapDistance, int car) {
//		HashMap<Integer, Short> temp_save = new HashMap<Integer, Short>();
//		temp_save.put((int) lapDistance, L1.speed[car]);
//		speed_table.put(car, temp_save);
//	}
//	
//	static DecimalFormat df = new DecimalFormat();
//	public static void delta_time() {
//		if (L1.sessionType == 10 || L1.sessionType == 11) {
//			df.setMaximumFractionDigits(3);
//			L1.Delta.put(0, "Leader");
//			for (byte i = 1; i < L1.numActiveCars; i++) {
//				int lapDistance_player1 = (int)L1.lapDistance[L1.car_positions.get((byte)(i - 1))];
//				int lapDistance_player2 = (int)L1.lapDistance[L1.car_positions.get((byte)i)];
//				
//				int speed = 0;
//				int speed_points = 1;
//				float gem_speed_mps = 0;
//
//				for (int o = 0; o < lapDistance_player1 - lapDistance_player2; o++) {
//					try {
//						speed = speed + speed_table.get((int)L1.car_positions.get((byte)i)).get(o + lapDistance_player2 + 1);
//						speed_points++;
//					} catch (Exception e) {
//					}
//				}
//				speed = speed + L1.speed[L1.car_positions.get((byte)i)];
//				gem_speed_mps = (float) (speed / speed_points / 3.6);
//				
//				
//				try {
//					L1.Delta.put((int)i, df.format((lapDistance_player1 - lapDistance_player2) / gem_speed_mps));
//				} catch (Exception e) {
//					L1.Delta.put((int) i, null);
//				}
//			}
//		}	
//	}
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@SuppressWarnings("null")
	public static int calculateDelta(int Car1, int Car2) {
		int TrackLength = Global_vars.Data.Session.getTrackLength();
		float Car1Percentage = Global_vars.Data.Lap_data[Car1].getLapDistance() / TrackLength * 100;
		float Car2Percentage = Global_vars.Data.Lap_data[Car2].getLapDistance() / TrackLength * 100;
		Car1Percentage = Float.valueOf(df.format(Car1Percentage));
		Car2Percentage = Float.valueOf(df.format(Car2Percentage));
		
		String TrackName = Global_vars.Appendices.AppendicesData.TrackIDs.get(Global_vars.Data.Session.getTrackId()).asText();
		int Car1Time = (Integer) null;
		int Car2Time = (Integer) null;
		try {
			Car1Time = (int) Global_vars.Delta.Delta.class.getField(TrackName).get(Car1Percentage);
			Car2Time = (int) Global_vars.Delta.Delta.class.getField(TrackName).get(Car2Percentage);
		} catch (Exception e) {
			//custom error message
			e.printStackTrace();
		}
		
		int Delta = Car1Time - Car2Time;
		return Delta;
	}
}