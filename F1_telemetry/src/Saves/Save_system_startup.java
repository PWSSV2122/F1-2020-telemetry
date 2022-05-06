package Saves;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Save_system_startup {
	
	public static Method[][] Methods = new Method[23][];
	
	public static String[] classes = new String[] {"Lap_data", "Car_status", "Car_setups", "Session.Session", "Session.Session_Weather", "Session.Session_Marshal", "Participants.Participants", "Participants.Participants_players", "Motion.Motion",
			"Motion.Motion_car", "Lobby_info.Lobby_info", "Lobby_info.Lobby_info_car", "Final_classification.Final_classification", "Final_classification.Final_classification_car", "Event.Event_FastestLap", "Event.Event_Penalty", 
			"Event.Event_RaceWinner", "Event.Event_Retirement", "Event.Event_SpeedTrap", "Event.Event_TeamMateInPits", "Event.Event", "Car_telemetry.Car_telemetry_car", "Car_telemetry.Car_telemetry"};
	
	public static void start() {
		
		Class[] loadedList = new Class[classes.length];
		Field[][] Names = new Field[classes.length][];
		for (int i = 0; i < classes.length; i++) {
			try {
				loadedList[i] = Class.forName("packet_struct." + classes[0]);
				Names[i] = loadedList[i].getDeclaredFields();
			} catch (ClassNotFoundException e) {
				//custom error message
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < Names.length; i++) {
			Methods[i] = new Method[Names[i].length];
			for (int o = 0; o < Names[i].length; o++) {
				try {
					//eerste letter van de var namen naar hoofdletter
					String Methode_name = Names[i][o].getName().substring(0, 1).toUpperCase() + Names[i][o].getName().substring(1);
					Methods[i][o] = loadedList[i].getMethod("get" + Methode_name, (Class[]) null);
				} catch (NoSuchMethodException e) {
					//custom error message
					e.printStackTrace();
				} catch (SecurityException e) {
					//custom error message
					e.printStackTrace();
				}
			}
		}
	}
}