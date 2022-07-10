package Global_vars.Appendices;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import application.Main;

public class AppendicesLoader {

	public static void LoadAppendices() {
		ObjectMapper mapper = new ObjectMapper();
		String filePath = Main.dir + "Global_vars/Appendices/Json/";
		
		try {
			File from = new File(filePath + "ButtonFlagsPlaystation.json");
			AppendicesData.ButtonFlagsPlaystation = mapper.readTree(from);
			
			from = new File(filePath + "ButtonFlagsXbox.json");
			AppendicesData.ButtonFlagsXbox = mapper.readTree(from);
			
			from = new File(filePath + "DriverIDs.json");
			AppendicesData.DriverIDs = mapper.readTree(from);
			
			from = new File(filePath + "InfringementTypes.json");
			AppendicesData.InfringmentTypes = mapper.readTree(from);
			
			from = new File(filePath + "NationalityIDs.json");
			AppendicesData.NationalityIDs = mapper.readTree(from);
			
			from = new File(filePath + "PenaltyTypes.json");
			AppendicesData.PenaltyTypes = mapper.readTree(from);
			
			from = new File(filePath + "SurfaceTypes.json");
			AppendicesData.SurfaceTypes = mapper.readTree(from);
			
			from = new File(filePath + "TeamIDs.json");
			AppendicesData.TeamIDs = mapper.readTree(from);
			
			from = new File(filePath + "TrackIDs.json");
			AppendicesData.TrackIDs = mapper.readTree(from);

		} catch (IOException e) {
			System.out.println(":(");
			//custom error message
		}
	}
}