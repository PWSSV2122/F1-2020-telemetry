package File_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Names {
	public static String[] File_Path = new String[] {"Motion_Packet", "Session_Packet", "Lap_Data_Packet", "Event_Packet", "Participants_Packet", "Car_Setups_Packet", "Car_Telemetry_Packet", "Car_Status_Packet", 
			"Final_Classification_Packet", "Lobby_Info_Packet", "Needed_data"};
	public static HashMap<String, HashMap<Integer, String>> Packet_names = new HashMap<String, HashMap<Integer, String>>();
	public static HashMap<String, HashMap<Integer, int[]>> Packet_byte_array = new HashMap<String, HashMap<Integer, int[]>>();;
	public static String[] Needed_data_names = new String[77];
	public static String[] needed_data_packets = new String[10];
	public static HashMap<String, String[]> Needed_data_packet = new HashMap<String, String[]>();
	public static HashMap<String, String[]> Needed_data_byte = new HashMap<String, String[]>();
	public static int[] repeats = new int[10];
	public static void data_decode() {
		String Line;
		for (int i = 0; i < 10; i++) {
			String Name;
			int id = 0;
			int repeat = 0;
			HashMap<Integer, int[]> data_decode_temp_int = new HashMap<Integer, int[]>();
			HashMap<Integer, String> data_decode_temp_String = new HashMap<Integer, String>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader("src/Names/" + File_Path[i] + ".enc"));
				while ((Line = reader.readLine()) != null) {
					String[] split = Line.split(" : ", 2);
					if (split.length >= 2) {
						Name = split[0];
						String[] split2 = split[1].split(", ", 48);
						int[] Byte_array = new int[split2.length];
						for (int o = 0; o < split2.length; o++) {
							Byte_array[o] = Integer.parseInt(split2[o]);
						}
						if (Name.endsWith("_")) {
							repeat = repeat + split2.length;
						}
						data_decode_temp_int.put(id, Byte_array);
						data_decode_temp_String.put(id, Name);
						id++;
					} else {
						System.out.println("error code #3"); //nog te bepalen error code
					}
				}
				reader.close();
				Packet_names.put(File_Path[i], data_decode_temp_String);
				Packet_byte_array.put(File_Path[i], data_decode_temp_int);
				repeats[i] = repeat;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Names/" + File_Path[10] + ".enc"));
			int i = 0;
			int o = 0;
			int[] array_places = new int[8];
			String[][] Name = new String[11][30];
			String[][] Code = new String[11][30];
			while ((Line = reader.readLine()) != null) {
				String[] split = Line.split(" : ", 3);
				if (split.length >= 3) {
					Needed_data_names[i] = split[0];
					if (split[2].equals("Car_Setups")) {
						Name[5][array_places[0]] = split[0];
						Code[5][array_places[0]] = split[1];
						array_places[0]++;
					} else if (split[2].equals("Car_Status")) {
						Name[7][array_places[1]] = split[0];
						Code[7][array_places[1]] = split[1];
						array_places[1]++;
					} else if (split[2].equals("Car_Telemetry")) {
						Name[6][array_places[2]] = split[0];
						Code[6][array_places[2]] = split[1];
						array_places[2]++;
					} else if (split[2].equals( "Header")) {
						Name[10][array_places[3]] = split[0];
						Code[10][array_places[3]] = split[1];
						array_places[3]++;
					} else if (split[2].equals("Lap_Data")) {
						Name[2][array_places[4]] = split[0];
						Code[2][array_places[4]] = split[1];
						array_places[4]++;
					} else if (split[2].equals("Motion")) {
						Name[0][array_places[5]] = split[0];
						Code[0][array_places[5]] = split[1];
						array_places[5]++;
					} else if (split[2].equals( "Participants")) {
						Name[4][array_places[6]] = split[0];
						Code[4][array_places[6]] = split[1];
						array_places[6]++;
					} else if (split[2].equals("Session")) {
						Name[1][array_places[7]] = split[0];
						Code[1][array_places[7]] = split[1];
						array_places[7]++;
					}
					List<String> list = Arrays.asList(needed_data_packets);
					if (list.contains(split[2]) || split[2].equals("Header")) {	
					} else {
						needed_data_packets[o] = split[2];
						o++;
					}
					i++;
				} else {
					System.out.println("error code #3"); //nog te bepalen error code
				}
			} 
			String[] packetid = new String[] {"Car_Setups", "Car_Status", "Car_Telemetry", "Header", "Lap_Data", "Motion", "Participants", "Session"};
			int[] ids = new int[] {5, 7, 6, 10, 2, 0, 4, 1};
			for(int p = 0; p < 8; p++) {
				Needed_data_packet.put(packetid[p], Name[ids[p]]);
				Needed_data_byte.put(packetid[p], Code[ids[p]]);
			}
			System.out.println("Ran");
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
