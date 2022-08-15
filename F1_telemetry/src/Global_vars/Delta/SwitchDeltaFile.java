package Global_vars.Delta;

import java.io.FileOutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.Main;
import errors.Error;

public class SwitchDeltaFile {

	private static String[] Name = new String[] {"Default", "Custom"};
	
	public static void Switch(int TrackID, int customOrDefault) {
		if (TrackID > 0 && TrackID < 27) {
			if (customOrDefault == 0 || customOrDefault == 1) {
				Delta.CustomOrDefault.put(String.valueOf(TrackID), Name[customOrDefault]);
				
				ObjectMapper mapper = new ObjectMapper();
				String json = null;
				FileOutputStream write;
				try {
					json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Delta.CustomOrDefault);
					write = new FileOutputStream(Main.dir + "Saves/temp/temp.json");
					byte[] jsonOutput = json.getBytes();
					write.write(jsonOutput);
					write.close();
				} catch (Exception e) {
					Error.error("Could not save the delta file change on restart the change wilt be reverted", 2);
				}
			} else {
				errors.Error.error("Could not switch the delta file beceause the customOrDefault is out "
						+ "of range should be between 0 and 1 it was: " + customOrDefault, 2);
			}
		} else {
			errors.Error.error("Could not switch the delta file beceause the TrackID is out "
					+ "of range should be between 0 and 26 it was: " + TrackID, 2);
		}
	}
}