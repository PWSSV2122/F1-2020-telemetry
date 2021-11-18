package file_system;

import java.util.HashMap;

public class L2 {
	//car setup packet
	public static HashMap<Integer, HashMap<String, Object>> Car_setup_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//car status packet
	public static HashMap<Integer, HashMap<String, Object>> Car_status_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//car telemetry packet
	public static HashMap<Integer, HashMap<String, Object>> Car_telemetry_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//lap data packet
	public static HashMap<Integer, HashMap<String, Object>> lap_data_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//motion packet
	public static HashMap<Integer, HashMap<String, Object>> motion_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//participants packet
	public static HashMap<Integer, HashMap<String, Object>> participants_packet = new HashMap<Integer, HashMap<String, Object>>();
	
	//session packet
	public static HashMap<Integer, HashMap<String, Object>> session_packet = new HashMap<Integer, HashMap<String, Object>>();
}
