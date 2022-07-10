package contentUpdate.Pages.Setup;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import application.SetupPage.Brakes;

public class Brake {

		public static void BrakeUpdate(int setup_car) {
			if (Data.Car_setups[setup_car].getBrakeBias() != 0) {
    			
    			byte BrakePressure = Data.Car_setups[setup_car].getBrakePressure();
	    		Brakes.Pressure_waarde.setText(String.valueOf(BrakePressure) + "%");
	    		Brakes.Pressure_bar.setProgress((BrakePressure - 50) / 50);
	    		
	    		byte BrakeBias = Data.Car_setups[setup_car].getBrakeBias();
	    		Brakes.Bias_waarde.setText(String.valueOf(BrakeBias) + "%");
	    		Brakes.Bias_bar.setProgress((BrakeBias - 50) * 0.05);
    		}
    		try {
    			Brakes.Track.setText(AppendicesData.TrackIDs.get(Data.Session.getTrackId()).textValue());
    		} catch (Exception e) {
    		}
		}
		
		public static void BrakeDropdownUpdate(int NumActiveCars, String[] PlayerNames) {
			String temp = Brakes.people.getValue();
			Brakes.people.getItems().clear();
		    for (int i = 0; i < NumActiveCars; i++) {
		    	Brakes.people.getItems().add(PlayerNames[i]);
		    }
		    Brakes.people.setValue(temp);
		}
}
