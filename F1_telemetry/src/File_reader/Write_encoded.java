package File_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Data_saves.Packet_store;

public class Write_encoded {
	static int send_rate = 60;
	static int[] Motion_Packet_int = new int[] {0,0};
	static int Session_Packet_int;
	static int[] Lap_Data_Packet_int = new int[] {0,0};
	static int Event_Packet_int;
	static int Participants_Packet_int;
	static int[] Car_Setups_Packet_int = new int[] {0,0};
	static int[] Car_Telemetry_Packet_int = new int[] {0,0};
	static int[] Car_Status_Packet_int = new int[] {0,0};
	static int Final_Classifiaction_Packet_int;
	static int Lobby_Info_Packet_int;
	
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
			reader.close();
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
		for (int i = 0; i < num_session_packets - Session_Packet_int; i++) {
			output_Session_packet += "id : " + (i + Session_Packet_int) + "\n";
			HashMap<String, Object> Session_Packet = new HashMap<String, Object>();
			Session_Packet.putAll(Packet_store.Session_Packet_store.get(String.valueOf(i + Session_Packet_int)));
			if ((i + Session_Packet_int) % 10 == 0 || (i + Session_Packet_int) == 0) {
				Session_Packet_full.putAll(Session_Packet);
				for (int o = 0; i < Names.length; i++) {
					output_Session_packet += Session_Packet_Encode.get(Names[o]) + Session_Packet.get(Names[o]) + "\n";
				}
			} else {
				for (int o = 0; i < Names.length; i++) {
					int change = 0;
					if (Session_Packet.get(Names[o]) != Session_Packet_full.get(Names[o])) {
						output_Session_packet += Session_Packet_Encode.get(Names[o]) + Session_Packet.get(Names[o]) + "\n";
						change++;
					}
					if (change >= 1) {
						output_Session_packet += "car id: " + o + "\n";
					}
				}
			}
			//marshal zones en wheater events worden niet opgenomen
		}
	}
	
	public static void Event_Packet(int Event_Packet_num) {
		get_encode();
		HashMap<String, Object> Event_Packet = new HashMap<String, Object>();
		String output_Event_Packet = "";
		for (int i =0; i < Event_Packet_num - Event_Packet_int; i++) {
			output_Event_Packet += "id : " + (i + Event_Packet_int) + "\n";
			Event_Packet.putAll(Packet_store.Event_Packet_store.get(String.valueOf(i + Event_Packet_int)));
			output_Event_Packet += Event_Packet_Encode.get("m_eventStringCode") + Event_Packet.get("m_eventStringCode") + "\n";
			if (Event_Packet.get("m_eventStringCode") == "FTLP") {
				output_Event_Packet += Event_Packet_Encode.get("vehicleIdx") + Event_Packet.get("vehicleIdx") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("lapTime") + Event_Packet.get("lapTime") + "\n";
			}
			if (Event_Packet.get("m_eventStringCode") == "RTMT") {
				output_Event_Packet += Event_Packet_Encode.get("vehicleIdx") + Event_Packet.get("vehicleIdx") + "\n";
			}
			if (Event_Packet.get("m_eventStringCode") == "TMPT") {
				output_Event_Packet += Event_Packet_Encode.get("vehicleIdx") + Event_Packet.get("vehicleIdx") + "\n";
			}
			if (Event_Packet.get("m_eventStringCode") == "RCWN") {
				output_Event_Packet += Event_Packet_Encode.get("vehicleIdx") + Event_Packet.get("vehicleIdx") + "\n";
			}
			if (Event_Packet.get("m_eventStringCode") == "PENA") {
				output_Event_Packet += Event_Packet_Encode.get("penaltyType") + Event_Packet.get("penaltyType") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("infringementType") + Event_Packet.get("infringementType") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("vehicleIdx") + Event_Packet.get("vehicleIdx") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("otherVehicleIdx") + Event_Packet.get("otherVehicleIdx") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("time") + Event_Packet.get("time") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("lapNum") + Event_Packet.get("lapNum") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("placesGained") + Event_Packet.get("placesGained") + "\n";
			}
			if (Event_Packet.get("m_eventStringCode") == "SPTP") {
				output_Event_Packet += Event_Packet_Encode.get("vehicleIdx") + Event_Packet.get("vehicleIdx") + "\n";
				output_Event_Packet += Event_Packet_Encode.get("speed") + Event_Packet.get("speed") + "\n";
			}
		}
	}
	
	public static void Participants(int Participants_Packet_num) {
		get_encode();
		String output_Participants_Packet = "";
		String[] Names = Names(null);
		HashMap<String, Object> Participants_full= new HashMap<String, Object>();
		for (int i = 0; i < Participants_Packet_num - Participants_Packet_int; i++) {
			output_Participants_Packet += "id : " + (i + Participants_Packet_int) + "\n";
			HashMap<String, Object> Participants_Packet = new HashMap<String, Object>();
			Participants_Packet.putAll(Packet_store.Participants_Packet_store.get(String.valueOf(i + Participants_Packet_int)));
			output_Participants_Packet += Participants_Packet_Encode.get(Names[0]) + Participants_Packet.get(Names[0]) + "\n";
			if ((i + Participants_Packet_int) % 10 == 0 || (i + Participants_Packet_int) == 0) {
				Participants_full.putAll(Participants_Packet);
				for (int o = 0; o < (int)Participants_Packet.get("m_numActiveCars"); i++) {
					for (int p = 0; i < 7; i++) {
						output_Participants_Packet += Participants_Packet_Encode.get(Names[p + 1]) + Participants_Packet.get(Names[p + 1]) + "\n";
					}
				}
			} else {
				for (int o = 0; o < (int)Participants_Packet.get("m_numActiveCars"); i++) {
					int change = 0;
					for (int p = 0; i < 7; i++) {
						output_Participants_Packet += Participants_Packet_Encode.get(Names[p + 1]) + Participants_Packet.get(Names[p + 1]) + "\n";
						change++;
					}
					if (change >= 1) {
						output_Participants_Packet += "car id: " + o + "\n";
					}
				}
			}
		}
	}
	
	public static void Setup(int Setups_int, HashMap<String, Integer> participants) {
		get_encode();
		String output_Car_Setups_Packet = "";
		String[] Names = Names(null);
		HashMap<String, Object> Car_Setup_full = new HashMap<String, Object>();
		for (int i = 0; i < Setups_int - Car_Setups_Packet_int[0]; i++) {
			output_Car_Setups_Packet += "id : " + (i + Car_Setups_Packet_int[0]) + "\n";
			output_Car_Setups_Packet += "Participants : " + participants.get(String.valueOf(Car_Setups_Packet_int[1])) + "\n";
			HashMap<String, Object> Car_setup = new HashMap<String, Object>();
			Car_setup.putAll(Packet_store.Car_Setups_Packet_store.get(String.valueOf(i + Car_Setups_Packet_int[0])));
			if ((i + Car_Setups_Packet_int[0]) % 10 == 0 || (i + Car_Setups_Packet_int[0]) == 0) {
				Car_Setup_full.putAll(Car_setup);
				for (int o = 0; o < participants.get(String.valueOf(Car_Setups_Packet_int[1])); o++) {
					for (int p = 0; p < 22; p++) {
						output_Car_Setups_Packet += Car_Setups_Packet_Encode.get(Names[p]) + Car_setup.get(Names[p]) + "\n";
					}
				}
			} else {
				for (int o = 0; o < participants.get(String.valueOf(Car_Setups_Packet_int[1])); o++) {
					for (int p = 0; p < 22; p++) {
						int change = 0;
						if (Car_setup.get(Names[p]) != Car_Setup_full.get(Names[p])) {
							output_Car_Setups_Packet += Car_Setups_Packet_Encode.get(Names[p]) + Car_setup.get(Names[p]) + "\n";
							change++;
						}
						if (change >= 1) {
							output_Car_Setups_Packet += "car id: " + o + "\n";
						}
					}
				}
			}
		}
	}
	
	public static void Final_Classification(int Final_Classification_int) {
		get_encode();
		String output_Final_Classification_Packet = "";
		String[] Names = Names(null);
		for (int i = 0; i < Final_Classification_int; i++) {
			output_Final_Classification_Packet += "id : " + (i + Final_Classifiaction_Packet_int) + "\n";
			HashMap<String, Object> Final_Classification = new HashMap<String, Object>();
			Final_Classification.putAll(Packet_store.Final_Classification_Packet_store.get(String.valueOf(i + Final_Classifiaction_Packet_int)));
			output_Final_Classification_Packet += Final_Classification_Packet_Encode.get(Names[0]) + Final_Classification.get(Names[0]) + "\n";
			for (int o = 0; o < (int) Final_Classification.get(Names[0]); o++) {
				for (int p = 0; p < Names.length; p++) {
					output_Final_Classification_Packet += Final_Classification_Packet_Encode.get(Names[p + 1]) + Final_Classification.get(Names[p + 1]) + "\n";
				}
			}
		}
	}
	
	public static void Main(int ids[], HashMap<String, Integer> participants) {
		get_encode();
		String output_Motion_Packet = "";
		String[] Names = Names(null);
		for (int i = 0; i < ids[0] - Motion_Packet_int[0]; i++) {
			HashMap<String, Object> Motion_Packet = new HashMap<String, Object>();
			if ((i + Motion_Packet_int[0]) % send_rate * 5 == 0) {
				Motion_Packet_int[1]++;
			}
			Motion_Packet.putAll(Packet_store.Motion_Packet_store.get(String.valueOf(i + Motion_Packet_int[0])));
			output_Motion_Packet += "id : " + (i + Motion_Packet_int[0]) + "\n";
			output_Motion_Packet += "participants" + participants.get(String.valueOf(Motion_Packet_int[1])) + "\n";
			for (int o = 0; o < (int) participants.get(String.valueOf(Motion_Packet_int[1])); o++) {
				for (int p = 0; 0 < 18; i++) {
					output_Motion_Packet += Motion_Packet_Encode.get(Names[p]) + Motion_Packet.get(Names[p] + "_" + o) + "\n";
				}
			}
			for (int o = 0; o < 30; o++) {
				output_Motion_Packet += Motion_Packet_Encode.get(Names[o + 17]) + Motion_Packet.get(Names[o + 17]);
			}
		}
		Motion_Packet_int[0] = ids[0] + Motion_Packet_int[0];
		
		String output_Lap_Data_Packet = "";
		Names = Names(null);
		for(int i = 0; i < ids[1] - Lap_Data_Packet_int[0]; i++) {
			HashMap<String, Object> Lap_Data_Packet = new HashMap<String, Object>();
			if ((i + Lap_Data_Packet_int[0]) % send_rate * 5 == 0) {
				Lap_Data_Packet_int[1]++;
			}
			Lap_Data_Packet.putAll(Packet_store.Lap_Data_Packet_store.get(String.valueOf(i + Lap_Data_Packet_int[0])));
			output_Lap_Data_Packet += "id : " + (i + Lap_Data_Packet_int[0]) + "\n";
			output_Lap_Data_Packet += "Participants :" + participants.get(String.valueOf(Lap_Data_Packet_int[1])) + "\n";
			for (int o = 0; o < (int) participants.get(String.valueOf(Lap_Data_Packet_int[1])); o++) {
				for (int p = 0; i < 27; i++) {
					output_Lap_Data_Packet += Lap_Data_Packet_Encode.get(Names[p]) + Lap_Data_Packet.get(Names[p]) + "\n"; 
				}
			}
		}
		Lap_Data_Packet_int[0] = ids[1] + Lap_Data_Packet_int[0];
		
		String output_Car_Telemetry_Packet = "";
		Names = Names(null);
		HashMap<String, Object> Car_Telemetry_full = new HashMap<String, Object>();
		for (int i = 0; i < ids[2] - Car_Telemetry_Packet_int[0]; i++) {
			output_Car_Telemetry_Packet += "id : " + (i + Car_Telemetry_Packet_int[0]) + "\n";
			output_Car_Telemetry_Packet += "Participants : " + participants.get(String.valueOf(Car_Telemetry_Packet_int[1])) + "\n";
			if ((i + Car_Telemetry_Packet_int[0]) % send_rate * 5 == 0) {
				Car_Telemetry_Packet_int[1]++;
			}
			HashMap<String, Object> Car_Telemetry = new HashMap<String, Object>();
			Car_Telemetry.putAll(Packet_store.Car_Telemetry_Packet_store.get(String.valueOf( + Car_Telemetry_Packet_int[0])));
			if ((i + Car_Telemetry_Packet_int[0]) % 10 == 0 || (i + Car_Telemetry_Packet_int[0] == 0)) {
				for (int o = 0; o < 4; o++) {
					output_Car_Telemetry_Packet += Car_Telemetry_Packet_Encode.get(Names[o]) + Car_Telemetry.get(Names[o]) + "\n";
				}
				Car_Telemetry_full.putAll(Car_Telemetry);
				for (int o = 0; o < participants.get(String.valueOf(Car_Telemetry_Packet_int[1])); o++) {
					for (int p = 0; p < (Names.length - 4); p++) {
						output_Car_Telemetry_Packet += Car_Telemetry_Packet_Encode.get(Names[p + 4]) + Car_Telemetry.get(Names[p + 4] + "_" + o) + "\n";
					}
				}
			} else {
				for (int o = 0; o < 4; o++) {
					if (Car_Telemetry.get(Names[o]) != Car_Telemetry_full.get(Names[o])) {
						output_Car_Telemetry_Packet += Car_Telemetry_Packet_Encode.get(Names[o]) + Car_Telemetry.get(Names[o]) + "\n";
					}
				}
				for (int o = 0; o < participants.get(String.valueOf(Car_Telemetry_Packet_int[1])); o++) {
					for (int p = 0; p < (Names.length - 4); p++) {
						int change = 0;
						if (Car_Telemetry.get(Names[p + 4] + "_" + o) != Car_Telemetry_full.get(Names[p + 4]) + "_" + o) {
							output_Car_Telemetry_Packet += Car_Telemetry_Packet_Encode.get(Names[p + 4]) + Car_Telemetry.get(Names[p+ 4] + "_" + o) + "\n";
							change++;
						}
						if (change >= 1) {
							output_Car_Telemetry_Packet += "car id: " + o + "\n";
						}
					}
				}
			}
		}
		
		String output_Car_Status_Packet = "";
		Names = Names(null);
		HashMap<String, Object> Car_Status_full = new HashMap<String, Object>();
		for (int i = 0; i < ids[3] - Car_Status_Packet_int[0]; i++) {
			output_Car_Status_Packet += "id : " + (i + Car_Status_Packet_int[0]) + "\n";
			output_Car_Status_Packet += "Participants : " + participants.get(String.valueOf(Car_Status_Packet_int[1])).byteValue() + "\n";
			if ((i + Car_Status_Packet_int[0]) % send_rate * 5 == 0) {
				Car_Status_Packet_int[1]++;
			}
			HashMap<String, Object> Car_Status = new HashMap<String, Object>();
			Car_Status.putAll(Packet_store.Car_Status_Packet_store.get(String.valueOf(i + Car_Status_Packet_int[0])));
			if ((i + Car_Status_Packet_int[0]) % 10 == 0 || (i + Car_Status_Packet_int[0]) == 0) {
				for (int o = 0; o < participants.get(String.valueOf(Car_Status_Packet_int[1])); o++) {
					for (int p = 0; p < Names.length; p++) {
						output_Car_Status_Packet += Car_Status_Packet_Encode.get(Names[p]) + Car_Status.get(Names[p] + "_" + o) + "\n";
					}
				}
			} else {
				for (int o = 0; o < participants.get(String.valueOf(Car_Status_Packet_int[1])); o++) {
					for (int p = 0; p < Names.length; p++) {
						int change = 0;
						if (Car_Status.get(Names[p] + "_" + o) != Car_Status_full.get(Names[o] + "_" + o)) {
							output_Car_Status_Packet += Car_Status_Packet_Encode.get(Names[p]) + Car_Status.get(Names[p] + "_" + o) + "\n";
							change++;
						}
						if (change >= 1) {
							output_Car_Status_Packet += "car id: " + o + "\n";
						}
					}
				}
			}
		}
	}
}
//alle woorden moeten nog worden geconvert met .byteValue() en de "\n" is voor nu alleen maar dat het goed te zien is in een .txt bestand

//todo
//testen van file of de string eruit komen zoals verwacht
//names file's maken
//encode file's maken
//.byteValue() achter alle data zetten
//"\n" weg halen
//string converten naar bytes
//bytes naar files write
//aanroepen van de methoden