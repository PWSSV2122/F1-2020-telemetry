package Global_vars.Delta;

import java.io.FileOutputStream;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import Inkoming.Inkoming;
import application.Main;
import errors.Error;

public class CreateDeltaClass {

	public static boolean CustomDelta = false;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	private static byte CurrentLapNum;
	private static int PlayerCarIndex;
	private static int TrackLength;
	private static String TrackName;
	
	private static ObjectNode CustomDeltaData;
	
	public static void CreateCustomDelta(int Track) {
		Error.error("Starting Custom Delta Recording for: " + AppendicesData.TrackIDs.get(Track).asText(), 0);

		CurrentLapNum = Data.Lap_data[Data.PlayerCarIndex].getCurrentLapNum();
		PlayerCarIndex = Data.PlayerCarIndex;
		TrackLength = Data.Session.getTrackLength();
		TrackName = AppendicesData.TrackIDs.get(Track).asText();
		Inkoming.SaveData = false;
		CustomDelta = true;
		
		Error.error("Ready to start recieving data for custom delta file", 0);
	}
	
	public static void StoreCustomDelta() {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		FileOutputStream write;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(CustomDeltaData);
			write = new FileOutputStream(Main.dir + "Saves/temp/temp.json");
			byte[] jsonOutput = json.getBytes();
			write.write(jsonOutput);
			write.close();
		} catch (Exception e) {
			Error.error("Could not save the custom delta file please try again", 2);
		}
		//save
		Error.error("Custom delta file for track: " + TrackName + " saved", 0);
	}
	
	public static void AddDataPoint() {
		if (CurrentLapNum + 1 == Data.Lap_data[PlayerCarIndex].getCurrentLapNum()) {
			float CurrentLapTime = Data.Lap_data[PlayerCarIndex].getCurrentLapTime();
			float ID = (Data.Lap_data[PlayerCarIndex].getLapDistance() / TrackLength) * 100;
			df.format(ID);
			CustomDeltaData.put(String.valueOf(ID), CurrentLapTime * 1000);
		} else if (CurrentLapNum + 2 == Data.Lap_data[PlayerCarIndex].getCurrentLapNum()) {
			StoreCustomDelta();
			CustomDelta = false;
			Error.error("Done recieving data for custom delta file", 0);
			Error.error("Recorded " + CustomDeltaData.size() + " data points for a health custom delta file there should be 1000 data points", 0);
			Error.error("Waiting for the custom delta file to be saved", 0);
		}
	}
}