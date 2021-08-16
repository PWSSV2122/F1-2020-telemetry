package File_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Data_saves.Packet_store;

public class Write_encoded {
	
	static HashMap<String, String> Motion_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Session_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Lap_Data_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Event_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Participants_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Car_Setups_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Car_Telemetry_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Car_Status_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Final_Classification_Packet_Encode = new HashMap<String, String>();
	static HashMap<String, String> Lobby_Info_Packet_Encode = new HashMap<String, String>();
	static String[] packets = new String[] {"Motion_Packet", "Session_Packet", "Lap_Data_Packet", "Event_Packet", "Participants_Packet", "Car_Setups_Packet",
			"Car_Telemetry_Packet", "Car_Status_Packet", "Final_Classification_Packet", "Lobby_Info_Packet"}; 
	private static void get_encode() {
		HashMap<String, String> temp_save = new HashMap<String, String>();
		HashMap<String, HashMap<String, String>> temp_save2 = new HashMap<String, HashMap<String, String>>();
		String[] file = new String[] {};
		
		String Line;
		for (int i = 0; i < file.length; i++) {	
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file[i]));
				while ((Line = reader.readLine()) != null) {
					String[] split = Line.split(":" , 2);
					if (split.length >= 2) {
						temp_save.put(split[0], split[1]);
					} else {
						System.out.println("slechte regel: " + Line);
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				System.out.println("Het bestand " + file[i] + " kon niet gevonden worden");
			} catch (IOException e) {
				e.printStackTrace();
			}
			temp_save2.put(packets[i], temp_save);
		}
		Motion_Packet_Encode.putAll(temp_save2.get(packets[0]));
		Session_Packet_Encode.putAll(temp_save2.get(packets[1]));
		Lap_Data_Packet_Encode.putAll(temp_save2.get(packets[2]));
		Event_Packet_Encode.putAll(temp_save2.get(packets[3]));
		Participants_Packet_Encode.putAll(temp_save2.get(packets[4]));
		Car_Setups_Packet_Encode.putAll(temp_save2.get(packets[5]));
		Car_Telemetry_Packet_Encode.putAll(temp_save2.get(packets[6]));
		Car_Status_Packet_Encode.putAll(temp_save2.get(packets[7]));
		Final_Classification_Packet_Encode.putAll(temp_save2.get(packets[8]));
		Lobby_Info_Packet_Encode.putAll(temp_save2.get(packets[9]));
	}
	
	private static String[] Names(String File) {
		String[] Names = new String[200];
		String Line;
		int i = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(File));
			while ((Line = reader.readLine()) != null) {
				String[] split = Line.split(":", 2);
				if (split.length >= 2) {
					Names[i] = split[0];
					i++;
				} else {
					System.out.println("Slechte regel: " + Line);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Het bestand " + File + " kon niet gevonden worden");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Names;
	}
	
	public static void Session(int num_session_packets) {
		get_encode();
		String[] Names = Names(null);
		String output_Session_packet = "";
		HashMap<String, Object> Session_Packet_full = new HashMap<String, Object>();
		for (int i = 0; i < num_session_packets; i++) {
			HashMap<String, Object> Session_Packet = new HashMap<String, Object>();
			Session_Packet.putAll(Packet_store.Session_Packet_store.get(String.valueOf(i)));
			if (i % 10 == 0 || i == 0) {
				Session_Packet_full.putAll(Session_Packet);
				for (int o = 0; i < Names.length; i++) {
					output_Session_packet += Session_Packet_Encode.get(Names[o]) + Session_Packet.get(Names[o]) + "\n";
				}
			} else {
				for (int o = 0; i < Names.length; i++) {
					if (Session_Packet.get(Names[o]) != Session_Packet_full.get(Names[o])) {
						output_Session_packet += Session_Packet_Encode.get(Names[o]) + Session_Packet.get(Names[o]) + "\n";
					}
				}
			}
		}
	}
	
	public static void Event_Packet() {
		
	}
	
	private static void test(int ids[]) {
		get_encode();
		String output_Motion_Packet = ""; 
		int Participants_packet_num = 0;
		int send_rate = 60;
		for (int i = 0; i < ids[0]; i++) {
			HashMap<String, Object> participants = new HashMap<String, Object>();
			HashMap<String, Object> Motion_Packet = new HashMap<String, Object>();
			if (i % send_rate * 5 == 0) {
				Participants_packet_num++;
			}
			try {
				participants.putAll(Packet_store.Participants_Packet_store.get(String.valueOf(Participants_packet_num)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Motion_Packet.putAll(Packet_store.Motion_Packet_store.get(String.valueOf(i)));
			output_Motion_Packet += "id : " + i + "\n";
			output_Motion_Packet += "participants" + participants.get("m_numActiveCars") + "\n";
			String[] Names = Names(null);
			for (int o = 0; o < (int) participants.get("m_numActiveCars"); o++) {
				for (int p = 0; 0 < 18; i++) {
					output_Motion_Packet += Motion_Packet_Encode.get(Names[p]) + Motion_Packet.get(Names[p] + "_" + o) + "\n";
				}
			}
			for (int o = 0; o < 30; o++) {
				output_Motion_Packet += Motion_Packet_Encode.get(Names[o + 17]) + Motion_Packet.get(Names[o + 17]);
			}
		}
		
		String output_Lap_Data_Packet = "";
		Participants_packet_num = 0;
		for(int i = 0; i < ids[1]; i++) {
			HashMap<String, Object> participants = new HashMap<String, Object>();
			HashMap<String, Object> Lap_Data_Packet = new HashMap<String, Object>();
			if (i % send_rate * 5 == 0) {
				Participants_packet_num++;
			}
			try {
				participants.putAll(Packet_store.Participants_Packet_store.get(String.valueOf(Participants_packet_num)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Lap_Data_Packet.putAll(Packet_store.Lap_Data_Packet_store.get(String.valueOf(i)));
			output_Lap_Data_Packet += "id : " + i + "\n";
			output_Lap_Data_Packet += "Participants :" + participants.get("m_numActiveCars") + "\n";
			String[] Names = Names(null);
			for (int o = 0; i < (int) participants.get("m_numActiveCars"); i++) {
				for (int p = 0; i < 27; i++) {
					output_Lap_Data_Packet += Lap_Data_Packet_Encode.get(Names[p]) + Lap_Data_Packet.get(Names[p]) + "\n"; 
				}
			}
		}
	}
	public static void Main(Object object) {
		test(null);
	}
} 