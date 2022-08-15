package contentUpdate.Pages.Setup;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import application.SetupPage.Transmission;

public class TransmissionContent {

	public static void TransmissionUpdate(int setup_car) {
		if (Data.Car_setups[setup_car].getOnThrottle() != 0) {
			
			byte OnThrottle = Data.Car_setups[setup_car].getOnThrottle();
			Transmission.On_Throttle_waarde.setText(String.valueOf(OnThrottle) + "%");
			Transmission.On_Throttle_bar.setProgress((float)(OnThrottle - 50) / 50);
			
			byte OffThrottle = Data.Car_setups[setup_car].getOffThrottle();
			Transmission.Off_Throttle_waarde.setText(String.valueOf(OffThrottle) + "%");
			Transmission.Off_Throttle_bar.setProgress((float)(OffThrottle - 50) / 50);
		}
		try {
			Transmission.Track.setText(AppendicesData.TrackIDs.get(Data.Session.getTrackId()).textValue());
		} catch (Exception e) {
		}
	}
	
	public static void TransmissionDropwdownUpdate(int NumActiveCars, String[] PlayerNames) {
		String temp = Transmission.people.getValue();
		Transmission.people.getItems().clear();
	    for (int i = 0; i < NumActiveCars; i++) {
	    	Transmission.people.getItems().add(PlayerNames[i]);
	    }
	    Transmission.people.setValue(temp);
	}
}
