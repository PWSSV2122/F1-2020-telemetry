package contentUpdate;

import contentUpdate.Pages.Setup.Aerodynamic;
import contentUpdate.Pages.Setup.Brake;
import contentUpdate.Pages.Setup.SuspenionGeometry;
import contentUpdate.Pages.Setup.SuspensionContent;
import contentUpdate.Pages.Setup.TransmissionContent;
import contentUpdate.Pages.Setup.Tyre;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Global_vars.Data;

public class SetupUpdate {
	public static Boolean Brakes_Boolean = false;
	public static Boolean Suspension_Geometry_Boolean = false;
	public static Boolean Suspension_Boolean = false;
	public static Boolean Transmission_Boolean = false;
	public static Boolean Tyres_Boolean = false;
	public static Boolean Aerodynamics_Boolean = false;
	
	public static int setup_car = 0;
	
	public static String[] paginas = new String[] {"Brakes", "Suspension Geometry", "Suspension", "Transmission", "Tyres", "Aerodynamics"};
	
	public static void Setup_update() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		    	if (Brakes_Boolean == true) {
		    		Brake.BrakeUpdate(setup_car);
				} else if (Suspension_Geometry_Boolean == true) {
					SuspenionGeometry.SuspensionGeometryUpdate(setup_car);
				} else if (Suspension_Boolean == true) {
					SuspensionContent.SuspensionUpdate(setup_car);
				} else if (Transmission_Boolean == true) {
					TransmissionContent.TransmissionUpdate(setup_car);
				} else if (Tyres_Boolean == true) {
					Tyre.TyresUpdate(setup_car);
				} else if (Aerodynamics_Boolean == true) {
					Aerodynamic.AerodynamicsUpdate(setup_car);
				}
		    } 
		};
		
		ScheduledExecutorService Setup_update = Executors.newScheduledThreadPool(1);
		Setup_update.scheduleAtFixedRate(updateClass, 0, 700, TimeUnit.MILLISECONDS);
	}
	
	public static void dropdown_update() {
		int NumActiveCars = Data.Participants.getNumActivePlayers();
		String[] PlayerNames = new String[22];
		for (int i = 0; i < NumActiveCars; i++) {
			PlayerNames[i] = Data.Participants_players[i].getName();
		}
		
		if (Brakes_Boolean == true) {
			Brake.BrakeDropdownUpdate(NumActiveCars, PlayerNames);
		} else if (Suspension_Geometry_Boolean == true) {
			SuspenionGeometry.SuspensionGeometryDropdownUpdate(NumActiveCars, PlayerNames);
		} else if (Suspension_Boolean == true) {
			SuspensionContent.SuspensionDropwdownUpdate(NumActiveCars, PlayerNames);
		} else if (Transmission_Boolean == true) {
			TransmissionContent.TransmissionDropwdownUpdate(NumActiveCars, PlayerNames);
		} else if (Tyres_Boolean == true) {
			Tyre.TyreDropdownUpdate(NumActiveCars, PlayerNames);
		} else if (Aerodynamics_Boolean == true) {
			Aerodynamic.AerodynamicsDropdownUpdate(NumActiveCars, PlayerNames);
		}
	}
}