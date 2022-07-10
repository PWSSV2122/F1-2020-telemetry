package contentUpdate.Pages.Setup;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import application.SetupPage.Aerodynamics;

public class Aerodynamic {

	public static void AerodynamicsUpdate(int setup_car) {
		if (Data.Car_setups[setup_car].getFrontWing() != 0) {
			
			byte FrontWing = Data.Car_setups[setup_car].getFrontWing();
			Aerodynamics.Front_Wing_waarde.setText(String.valueOf(FrontWing));
			Aerodynamics.Front_Wing_bar.setProgress((float)(FrontWing - 1) / 10);
			
			byte RearWing = Data.Car_setups[setup_car].getRearWing();
			Aerodynamics.Rear_Wing_waarde.setText(String.valueOf(RearWing));
			Aerodynamics.Rear_Wing_bar.setProgress((float)(RearWing - 1) / 10);
		}
		try {
			Aerodynamics.Track.setText(AppendicesData.TrackIDs.get(Data.Session.getTrackId()).textValue());
		} catch (Exception e) {
		}
	}
	
	public static void AerodynamicsDropdownUpdate(int NumActiveCars, String[] PlayerNames) {
		String temp = Aerodynamics.people.getValue();
		Aerodynamics.people.getItems().clear();
	    for (int i = 0; i < NumActiveCars; i++) {
	    	Aerodynamics.people.getItems().add(PlayerNames[i]);
	    }
	    Aerodynamics.people.setValue(temp);
	}
}