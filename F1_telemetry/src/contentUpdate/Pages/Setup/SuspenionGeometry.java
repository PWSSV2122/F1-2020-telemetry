package contentUpdate.Pages.Setup;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import application.SetupPage.Suspension_Geometry;

public class SuspenionGeometry {

	public static void SuspensionGeometryUpdate(int setup_car) {
		if (Data.Car_setups[setup_car].getFrontCamber() != 0) {
			
			float FrontCamber = Data.Car_setups[setup_car].getFrontCamber();
			Suspension_Geometry.Front_camber_waarde.setText(String.valueOf(FrontCamber));
			Suspension_Geometry.Front_camber_bar.setProgress((FrontCamber + 3.50));
			
			float RearCamber = Data.Car_setups[setup_car].getRearCamber();
			Suspension_Geometry.Rear_camber_waarde.setText(String.valueOf(RearCamber));
			Suspension_Geometry.Rear_camber_bar.setProgress(RearCamber + 2);
			
			float FrontToe = Data.Car_setups[setup_car].getFrontToe();
			Suspension_Geometry.Front_toe_waarde.setText(String.valueOf((float)Math.round(FrontToe * 100) / 100));
			Suspension_Geometry.Front_camber_bar.setProgress((FrontToe - 0.05) / 0.1);
			
			float RearToe = Data.Car_setups[setup_car].getRearToe();
			Suspension_Geometry.Rear_toe_waarde.setText(String.valueOf((float)Math.round(RearToe * 100) / 100));
			Suspension_Geometry.Rear_camber_bar.setProgress((RearToe - 0.2) / 0.3);
		}
		try {
			Suspension_Geometry.Track.setText(AppendicesData.TrackIDs.get(Data.Session.getTrackId()).textValue());
		} catch (Exception e) {
		}
	}
	
	public static void SuspensionGeometryDropdownUpdate(int NumActiveCars, String[] PlayerNames) {
		String temp = Suspension_Geometry.people.getValue();
		Suspension_Geometry.people.getItems().clear();
	    for (int i = 0; i < NumActiveCars; i++) {
	    	Suspension_Geometry.people.getItems().add(PlayerNames[i]);
	    }
	    Suspension_Geometry.people.setValue(temp);
	}
}
