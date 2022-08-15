package contentUpdate.Pages.Setup;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import application.SetupPage.Tyres;

public class Tyre {

	public static void TyresUpdate(int setup_car) {
		if (Data.Car_setups[setup_car].getFrontRightTyrePressure() != 0) {
			
			float FrontRightTyrePressure = Data.Car_setups[setup_car].getFrontRightTyrePressure();
			Tyres.Front_Right_waarde.setText(String.valueOf(FrontRightTyrePressure));
			Tyres.Front_Right_bar.setProgress((float)(FrontRightTyrePressure - 21) / 4);
			
			float FrontLeftTyrePressure = Data.Car_setups[setup_car].getFrontLeftTyrePressure();
			Tyres.Front_Left_waarde.setText(String.valueOf(FrontLeftTyrePressure));
			Tyres.Front_Left_bar.setProgress((float)(FrontLeftTyrePressure - 21) / 4);
			
			float RearRightTyrePressure = Data.Car_setups[setup_car].getRearRightTyrePressue();
			Tyres.Rear_Right_waarde.setText(String.valueOf(RearRightTyrePressure));
			Tyres.Rear_Right_bar.setProgress((float)(RearRightTyrePressure - 19.5) / 4);
			
			float RearLeftTyrePressure = Data.Car_setups[setup_car].getRearLeftTyrePressure();
			Tyres.Rear_Left_waarde.setText(String.valueOf(RearLeftTyrePressure));
			Tyres.Rear_Left_bar.setProgress((float)(RearLeftTyrePressure - 19.5) / 4);
		}
		try {
			Tyres.Track.setText(AppendicesData.TrackIDs.get(Data.Session.getTrackId()).textValue());
		} catch (Exception e) {
		}
	}
	
	public static void TyreDropdownUpdate(int NumActiveCars, String[] PlayerNames) {
		String temp = Tyres.people.getValue();
		Tyres.people.getItems().clear();
	    for (int i = 0; i < NumActiveCars; i++) {
	    	Tyres.people.getItems().add(PlayerNames[i]);
	    }
	    Tyres.people.setValue(temp);
	}
}
