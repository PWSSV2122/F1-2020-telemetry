package contentUpdate;

import application.LapTimePage;
import file_system.L1;
import application.SetupPage.Brakes;
import application.SetupPage.Suspension_Geometry;
import application.SetupPage.Suspension;
import application.SetupPage.Transmission;
import application.SetupPage.Tyres;

public class SetupUpdate {
	public static Boolean Brakes_Boolean = false;
	public static Boolean Suspension_Geometry_Boolean = false;
	public static Boolean Suspension_Boolean = false;
	public static Boolean Transmission_Boolean = false;
	public static Boolean Tyres_Boolean = false;
	
	public static int setup_car = 0;
	
	public static String[] paginas = new String[] {"Brakes", "Suspension Geometry", "Suspension", "Transmission", "Tyres"};
	
	public static void Setup_update() {
		
	}
	
	public static void dropdown_update() {
		if (Brakes_Boolean == true) {
			String temp = Brakes.people.getValue();
			Brakes.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Brakes.people.getItems().add(L1.name[i]);
		    }
		    Brakes.people.setValue(temp);
		} else if (Suspension_Geometry_Boolean == true) {
			String temp = Suspension_Geometry.people.getValue();
			Suspension_Geometry.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Suspension_Geometry.people.getItems().add(L1.name[i]);
		    }
		    Suspension_Geometry.people.setValue(temp);
		} else if (Suspension_Boolean == true) {
			String temp = Suspension_Boolean.people.getValue();
			Suspension_Boolean.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Suspension_Boolean.people.getItems().add(L1.name[i]);
		    }
		    Suspension_Boolean.people.setValue(temp);
		} else if (Transmission_Boolean == true) {
			String temp = Transmission_Boolean.people.getValue();
			Transmission_Boolean.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Transmission_Boolean.people.getItems().add(L1.name[i]);
		    }
		    Transmission_Boolean.people.setValue(temp);
		} else if (Tyres_Boolean == true) {
			String temp = Tyres_Boolean.people.getValue();
			Tyres_Boolean.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Tyres_Boolean.people.getItems().add(L1.name[i]);

		    }
		    Tyres_Boolean.people.setValue(temp);
		}
	}
}
