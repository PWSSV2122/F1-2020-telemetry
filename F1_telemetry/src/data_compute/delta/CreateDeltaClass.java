package data_compute.delta;

import java.util.concurrent.TimeUnit;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;

public class CreateDeltaClass {

	public static boolean CustomDelta = false;
	
	public static void CreateCustomDelta(int Track) {
		errors.Error.error("Starting Custom Delta Recording for: " + AppendicesData.TrackIDs.get(Track).asText(), 0);

		byte CurrentLapNum = Data.Lap_data[1].getCurrentLapNum();
		int PlayerCarIndex = Data.PlayerCarIndex;
		String TrackName = AppendicesData.TrackIDs.get(Track).asText();
		
		while(CustomDelta) {
			if (CurrentLapNum + 1 == Data.Lap_data[1].getCurrentLapNum()) {
				
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					//custom error message
				}
			}
		}
	}
}