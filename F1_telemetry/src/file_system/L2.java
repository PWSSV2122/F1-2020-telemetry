package file_system;

import java.util.HashMap;

public class L2 {
	//car setup packet
	public static HashMap<Integer, HashMap<String, Object>> Car_Setup_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//car status packet
	public static HashMap<Integer, HashMap<String, Object>> Car_Status_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//car telemetry packet
	public static HashMap<Integer, HashMap<String, Object>> Car_Telemetry_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//lap data packet
	public static HashMap<Integer, HashMap<String, Object>> Lap_Data_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//motion packet
	public static HashMap<Integer, HashMap<String, Object>> Motion_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//participants packet
	public static HashMap<Integer, HashMap<String, Object>> Participants_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//session packet
	public static HashMap<Integer, HashMap<String, Object>> Session_packet = new HashMap<Integer, HashMap<String, Object>>();
}