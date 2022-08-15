package contentUpdate.Pages.Setup;

import Global_vars.Data;
import Global_vars.Appendices.AppendicesData;
import application.SetupPage.Suspension;

public class SuspensionContent {

	public static void SuspensionUpdate(int setup_car) {
		if (Data.Car_setups[setup_car].getFrontSuspension() != 0) {
			
			float FrontSuspension = Data.Car_setups[setup_car].getFrontSuspension();
			Suspension.Front_Suspension_waarde.setText(String.valueOf(FrontSuspension));
			Suspension.Front_Suspension_bar.setProgress((float)(FrontSuspension - 1) / 10);
			
			float RearSuspension = Data.Car_setups[setup_car].getRearSuspsnesion();
			Suspension.Rear_Suspension_waarde.setText(String.valueOf(RearSuspension));
			Suspension.Rear_Suspension_bar.setProgress((float)(RearSuspension - 1) / 10);
			
			float FrontAntiRollBar = Data.Car_setups[setup_car].getFrontAntiRollBar();
			Suspension.Front_Anti_Roll_bar_waarde.setText(String.valueOf(FrontAntiRollBar));
			Suspension.Front_Anti_Roll_bar_bar.setProgress((float)(FrontAntiRollBar - 1) / 10);
			
			float RearAntiRollBar = Data.Car_setups[setup_car].getRearAntiRollBar();
			Suspension.Rear_Anti_Roll_bar_waarde.setText(String.valueOf(RearAntiRollBar));
			Suspension.Rear_Anti_Roll_bar_bar.setProgress((float)(RearAntiRollBar - 1) / 10);
			
			byte FrontSuspsneionHeight = Data.Car_setups[setup_car].getFrontSuspnesionHeight();
			Suspension.Front_Ride_Height_waarde.setText(String.valueOf(FrontSuspsneionHeight));
			Suspension.Front_Ride_Height_bar.setProgress((float)(FrontSuspsneionHeight - 1) / 10);
			
			byte RearSuspensionHeight = Data.Car_setups[setup_car].getRearSuspensionHeight();
			Suspension.Rear_Ride_Height_waarde.setText(String.valueOf(RearSuspensionHeight));
			Suspension.Rear_Ride_Height_bar.setProgress((float)(RearSuspensionHeight - 1) / 10);
		}
		try {
			Suspension.Track.setText(AppendicesData.TrackIDs.get(Data.Session.getTrackId()).textValue());
		} catch (Exception e) {
		}
	}
	
	public static void SuspensionDropwdownUpdate(int NumActiveCars, String[] PlayerNames) {
		String temp = Suspension.people.getValue();
		Suspension.people.getItems().clear();
	    for (int i = 0; i < NumActiveCars; i++) {
	    	Suspension.people.getItems().add(PlayerNames[i]);
	    }
	    Suspension.people.setValue(temp);
	}
}