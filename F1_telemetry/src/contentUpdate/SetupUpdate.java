package contentUpdate;

import file_system.L1;
import application.SetupPage.Brakes;
import application.SetupPage.Suspension_Geometry;
import application.SetupPage.Suspension;
import application.SetupPage.Transmission;
import application.SetupPage.Tyres;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.SetupPage.Aerodynamics;

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
		    		Brakes.Pressure_waarde.setText(String.valueOf(L1.brakePressure[setup_car]) + "%");
		    		Brakes.Pressure_bar.setProgress((L1.brakePressure[setup_car] - 50) / 50);
		    		
		    		Brakes.Bias_waarde.setText(String.valueOf(L1.brakeBias[setup_car]) + "%");
		    		Brakes.Bias_bar.setProgress((L1.brakeBias[setup_car] - 50) * 5);
				} else if (Suspension_Geometry_Boolean == true) {
					Suspension_Geometry.Front_camber_waarde.setText(String.valueOf(L1.frontCamber[setup_car]));
					Suspension_Geometry.Front_camber_bar.setProgress((L1.frontCamber[setup_car] + 3.50));
					
					Suspension_Geometry.Rear_camber_waarde.setText(String.valueOf(L1.rearCamber[setup_car]));
					Suspension_Geometry.Rear_camber_bar.setProgress(L1.rearCamber[setup_car] + 2);
					
					Suspension_Geometry.Front_toe_waarde.setText(String.valueOf(L1.frontToe[setup_car]));
					Suspension_Geometry.Front_camber_bar.setProgress((L1.frontToe[setup_car] - 0.05) / 0.1);
					
					Suspension_Geometry.Rear_toe_waarde.setText(String.valueOf(L1.rearToe[setup_car]));
					Suspension_Geometry.Rear_camber_bar.setProgress((L1.rearToe[setup_car] - 0.2) / 0.3);
				} else if (Suspension_Boolean == true) {
					Suspension.Front_Suspension_waarde.setText(String.valueOf(L1.frontSuspension[setup_car]));
					Suspension.Front_Suspension_bar.setProgress((L1.frontSuspension[setup_car] - 1) / 10);
					
					Suspension.Rear_Suspension_waarde.setText(String.valueOf(L1.rearSuspension[setup_car]));
					Suspension.Rear_Suspension_bar.setProgress((L1.rearSuspension[setup_car] - 1) / 10);
					
					Suspension.Front_Anti_Roll_bar_waarde.setText(String.valueOf(L1.frontAntiRollBar[setup_car]));
					Suspension.Front_Anti_Roll_bar_bar.setProgress((L1.frontAntiRollBar[setup_car] - 1) / 10);
					
					Suspension.Rear_Anti_Roll_bar_waarde.setText(String.valueOf(L1.rearAntiRollBar[setup_car]));
					Suspension.Rear_Anti_Roll_bar_bar.setProgress((L1.rearAntiRollBar[setup_car] - 1) / 10);
					
					Suspension.Front_Ride_Height_waarde.setText(String.valueOf(L1.frontSuspensionHeight[setup_car]));
					Suspension.Front_Ride_Height_bar.setProgress((L1.frontSuspensionHeight[setup_car] - 1) / 10);
					
					Suspension.Rear_Ride_Height_waarde.setText(String.valueOf(L1.rearSuspensionHeight[setup_car]));
					Suspension.Rear_Ride_Height_bar.setProgress((L1.rearSuspensionHeight[setup_car] - 1) / 10);
				} else if (Transmission_Boolean == true) {
					Transmission.On_Throttle_waarde.setText(String.valueOf(L1.onThrottle[setup_car]) + "%");
					Transmission.On_Throttle_bar.setProgress((L1.onThrottle[setup_car] - 50) / 50);
					
					Transmission.Off_Throttle_waarde.setText(String.valueOf(L1.offThrottle[setup_car]) + "%");
					Transmission.Off_Throttle_bar.setProgress((L1.offThrottle[setup_car] - 50) / 50);
				} else if (Tyres_Boolean == true) {
					Tyres.Front_Right_waarde.setText(String.valueOf(L1.frontRightTyrePressure[setup_car]));
					Tyres.Front_Right_bar.setProgress((L1.frontRightTyrePressure[setup_car] - 21) / 4);
					
					Tyres.Front_Left_waarde.setText(String.valueOf(L1.frontLeftTyrePressure[setup_car]));
					Tyres.Front_Left_bar.setProgress((L1.frontLeftTyrePressure[setup_car] - 21) / 4);
					
					Tyres.Rear_Right_waarde.setText(String.valueOf(L1.rearRightTyrePressure[setup_car]));
					Tyres.Rear_Right_bar.setProgress((L1.rearRightTyrePressure[setup_car] - 21) / 4);
					
					Tyres.Rear_Left_waarde.setText(String.valueOf(L1.rearLeftTyrePressure[setup_car]));
					Tyres.Rear_Left_bar.setProgress((L1.rearLeftTyrePressure[setup_car] - 21) / 4);
				} else if (Aerodynamics_Boolean == true) {
					Aerodynamics.Front_Wing_waarde.setText(String.valueOf(L1.frontWing[setup_car]));
					Aerodynamics.Front_Wing_bar.setProgress((L1.frontWing[setup_car] - 1) / 10);
					
					Aerodynamics.Rear_Wing_waarde.setText(String.valueOf(L1.rearWing[setup_car]));
					Aerodynamics.Rear_Wing_bar.setProgress((L1.rearWing[setup_car] - 1) / 10);
				}
		    } 
		};
		
		ScheduledExecutorService Setup_update = Executors.newScheduledThreadPool(1);
		Setup_update.scheduleAtFixedRate(updateClass, 0, 700, TimeUnit.MILLISECONDS);
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
			String temp = Suspension.people.getValue();
			Suspension.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Suspension.people.getItems().add(L1.name[i]);
		    }
		    Suspension.people.setValue(temp);
		} else if (Transmission_Boolean == true) {
			String temp = Transmission.people.getValue();
			Transmission.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Transmission.people.getItems().add(L1.name[i]);
		    }
		    Transmission.people.setValue(temp);
		} else if (Tyres_Boolean == true) {
			String temp = Tyres.people.getValue();
			Tyres.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Tyres.people.getItems().add(L1.name[i]);
		    }
		    Tyres.people.setValue(temp);
		} else if (Aerodynamics_Boolean == true) {
			String temp = Aerodynamics.people.getValue();
			Aerodynamics.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	Aerodynamics.people.getItems().add(L1.name[i]);
		    }
		    Aerodynamics.people.setValue(temp);
		}
	}
}
