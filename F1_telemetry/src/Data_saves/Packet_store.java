package Data_saves;

import java.util.HashMap;

public class Packet_store {

	public static HashMap<String, Object> Motion_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Motion_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Motion_Packet_store = new HashMap<String, byte[]>();

	public static HashMap<String, Object> Session_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Session_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Session_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Lap_Data_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Lap_Data_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Lap_Data_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Event_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Event_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Event_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Participants_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Participants_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Participants_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Car_Setups_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Car_Setups_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Car_Setups_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Car_Telemetry_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Car_Telemetry_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Car_Telemetry_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Car_Status_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Car_Status_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Car_Status_Packet_store = new HashMap<String, byte[]>();
	
	public static HashMap<String, Object> Final_Classification_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Final_Classification_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Final_Classification_Packet_store = new HashMap<String, byte[]>();

	public static HashMap<String, Object> Lobby_Info_Packet_Header = new HashMap<String, Object>();
	public static HashMap<String, Object> Lobby_Info_Packet = new HashMap<String, Object>();
	public static HashMap<String, byte[]> Lobby_Info_Packet_store = new HashMap<String, byte[]>();
	
}