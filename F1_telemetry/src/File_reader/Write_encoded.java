package File_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Data_saves.Packet_store;

public class Write_encoded {
	
//	public static void Main(int ids[]) {
//		HashMap<String, String> Motion_Packet_encode = new HashMap<String, String>();
//		Motion_Packet_encode.put("m_worldPositionX_", "0");
//		Motion_Packet_encode.put("", "1");
//		Motion_Packet_encode.put("", "2");
//		Motion_Packet_encode.put("", "3");
//		Motion_Packet_encode.put("", "4");
//		Motion_Packet_encode.put("", "5");
//		Motion_Packet_encode.put("", "6");
//		Motion_Packet_encode.put("", "7");
//		Motion_Packet_encode.put("", "8");
//		Motion_Packet_encode.put("", "9");
//		Motion_Packet_encode.put("", "10");
//		Motion_Packet_encode.put("", "11");
//		Motion_Packet_encode.put("", "12");
//		
//		String Motion_Packet = "";
//		
////		for (int i = 0; i < ids[0]; i++) {
////			HashMap<String, Object> Motion_Packet_temp = new HashMap<String, Object>();
////			Motion_Packet_temp.putAll(Packet_store.Motion_Packet_store.get(String.valueOf(i)));
////			Motion_Packet += Motion_Packet_encode.get("m_worldPositionX_") + ;
////			Motion_Packet_temp.get("");
////		}
//		
//		int num=1020;
//
//		String Motion_Packet_Num = "";
//		while (num > 0) {
//			Motion_Packet_Num = Motion_Packet_encode.get(String.valueOf(num % 10)) + Motion_Packet_Num;
//		    num = num / 10;
//		}	
//	}
//	
//	//de namen opslaan als een 7 bit (of klijner) en dan de nummer als de bits van hun data type
//	private static String Motion_Packet_Num(long num) {
//		String Motion_Packet_num = "";
//		while (num > 0) {
//			Motion_Packet_Num = Motion_Packet_encode.get(String.valueOf(num % 10)) + Motion_Packet_Num;
//		    num = num / 10;
//		}
//		return Motion_Packet_num;
//	}
	
	
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
	
	private static void test(int ids[]) {
		get_encode();
		String output_Motion_Packet = ""; 
		int Participants_packet_num = 1;
		int send_rate = 60;
		for (int i = 0; i < ids[0]; i++) {
			HashMap<String, Object> paraticipants = new HashMap<String, Object>();
			HashMap<String, Object> Motion_Packet = new HashMap<String, Object>();
			if (i % send_rate * 5 == 0) {
				Participants_packet_num++;
			}
			try {
				paraticipants.putAll(Packet_store.Participants_Packet_store.get(String.valueOf(Participants_packet_num)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Motion_Packet.putAll(Packet_store.Motion_Packet_store.get(String.valueOf(i)));
			output_Motion_Packet += "id : " + ids[0] + "\n";
			output_Motion_Packet += "paraticipants" + paraticipants.get("m_numActiveCars") + "\n";
//			for (int o = 0; o < (int) paraticipants.get("m_numActiveCars"); o++) {
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldPositionX") + Motion_Packet.get("m_worldPositionX_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldPositionY") + Motion_Packet.get("m_worldPositionY_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldPositionZ") + Motion_Packet.get("m_worldPositionZ_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldVelocityX") + Motion_Packet.get("m_worldVelocityX_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldVelocityY") + Motion_Packet.get("m_worldVelocityY_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldVelocityZ") + Motion_Packet.get("m_worldVelocityZ_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldForwardDirX") + Motion_Packet.get("m_worldForwardDirX_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldForwardDirY") + Motion_Packet.get("m_worldForwardDirY_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldForwardDirZ") + Motion_Packet.get("m_worldForwardDirZ_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldRightDirX") + Motion_Packet.get("m_worldRightDirX_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldRightDirY") + Motion_Packet.get("m_worldRightDirY_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_worldRightDirZ") + Motion_Packet.get("m_worldRightDirZ_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_gForceLateral") + Motion_Packet.get("m_gForceLateral_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_gForceLongitudinal") + Motion_Packet.get("m_gForceLongitudinal_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_gForceVertical") + Motion_Packet.get("m_gForceVertical_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_yaw") + Motion_Packet.get("m_yaw_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_pitch") + Motion_Packet.get("m_pitch_" + o) + "\n"; 
//				output_Motion_Packet += Motion_Packet_Encode.get("m_roll") + Motion_Packet.get("m_roll_" + o) + "\n"; 
//			}
			String[] Names = Names(null);
			for (int o = 0; o < (int) paraticipants.get("m_numActiveCars"); o++) {
				for (int p = 0; 0 < 18; i++) {
					output_Motion_Packet += Motion_Packet_Encode.get(Names[p]) + Motion_Packet.get(Names[p] + "_" + o) + "\n";
				}
			}
			for (int o = 0; o < 30; o++) {
				output_Motion_Packet += Motion_Packet_Encode.get(Names[o + 17]) + Motion_Packet.get(Names[o + 17]);
			}
			
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionPosition_RL") + Motion_Packet.get("m_suspensionPosition_RL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionPosition_RR") + Motion_Packet.get("m_suspensionPosition_RR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionPosition_FL") + Motion_Packet.get("m_suspensionPosition_FL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionPosition_FR") + Motion_Packet.get("m_suspensionPosition_FR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionVelocity_RL") + Motion_Packet.get("m_suspensionVelocity_RL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionVelocity_RR") + Motion_Packet.get("m_suspensionVelocity_RR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionVelocity_FL") + Motion_Packet.get("m_suspensionVelocity_FL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionVelocity_FR") + Motion_Packet.get("m_suspensionVelocity_FR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionAcceleration_RL") + Motion_Packet.get("m_suspensionAcceleration_RL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionAcceleration_RR") + Motion_Packet.get("m_suspensionAcceleration_RR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionAcceleration_FL") + Motion_Packet.get("m_suspensionAcceleration_FL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_suspensionAcceleration_FR") + Motion_Packet.get("m_suspensionAcceleration_FR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSpeed_RL") + Motion_Packet.get("m_wheelSpeed_RL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSpeed_RR") + Motion_Packet.get("m_wheelSpeed_RR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSpeed_FL") + Motion_Packet.get("m_wheelSpeed_FL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSpeed_FR") + Motion_Packet.get("m_wheelSpeed_FR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSlip_RL") + Motion_Packet.get("m_wheelSlip_RL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSlip_RR") + Motion_Packet.get("m_wheelSlip_RR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSlip_FL") + Motion_Packet.get("m_wheelSlip_FL") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_wheelSlip_FR") + Motion_Packet.get("m_wheelSlip_FR") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_localVelocityX") + Motion_Packet.get("m_localVelocityX") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_localVelocityY") + Motion_Packet.get("m_localVelocityY") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_localVelocityZ") + Motion_Packet.get("m_localVelocityZ") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_angularVelocityX") + Motion_Packet.get("m_angularVelocityX") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_angularVelocityY") + Motion_Packet.get("m_angularVelocityY") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_angularVelocityZ") + Motion_Packet.get("m_angularVelocityZ") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_angularAccelerationX") + Motion_Packet.get("m_angularAccelerationX") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_angularAccelerationY") + Motion_Packet.get("m_angularAccelerationY") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_angularAccelerationZ") + Motion_Packet.get("m_angularAccelerationZ") + "\n";
//			output_Motion_Packet += Motion_Packet_Encode.get("m_frontWheelsAngle") + Motion_Packet.get("m_frontWheelsAngle") + "\n";
		}
	}


	public static void Main(Object object) {
		test(null);
	}
	
	
	
	
	
	
	
	
	
} 
