package file_system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import Inkoming.Packet_recieve;

public class data_manager {
	public static void main(String[] args) {
		HashMap<String, Object> test = new HashMap<String, Object>();
		data(test, (byte)4, (float)0, (int)100);
	}
	
	public static void data(HashMap<String, Object> temp_save, byte packetID, float sessionTime, int m_frameIdentifier){
		HashMap<String, HashMap<Integer, String>> Names = new HashMap<String, HashMap<Integer, String>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Names/Needed_data.enc"));
			int i = 0;
			boolean first = true; 
			String Line;
			String name = null;
			HashMap<Integer, String> temp_save1 = new HashMap<Integer, String>();
			while ((Line = reader.readLine()) != null) {
				String[] split = Line.split(" : ", 3);
				if (split.length >= 3) {
					if (split[2].equals(name)) {
					} else if (first == true){						
						name = split[2];
						first = false;
					} else {
						Names.put(name, new HashMap() {{putAll(temp_save1);}});
						temp_save1.clear();
						i = 0;
						name = split[2];
					}
					temp_save1.put(i, split[0]);
					i++;
				} else {
					System.out.println("error code #3"); //nog te bepalen error code
				}
			}
			Names.put(name, new HashMap() {{putAll(temp_save1);}});
			temp_save1.clear();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] PacketID_name = new String[] {"Motion", "Session", "Lap_Data", "Event", "Participants", "Car_Setup", "Car_Telemetry", "Car_Status", "Final_Classificationt", "Lobby_Info", "Needed"};
		HashMap<String, Object> L2_data_temp_save = new HashMap<String, Object>();
		try {
			for (int i = 0; i < Names.get(PacketID_name[packetID]).size(); i++) {
				if (Names.get(PacketID_name[packetID]).get(i).endsWith("_")) {
					String Name_l1 = Names.get(PacketID_name[packetID]).get(i).substring(0, Names.get(PacketID_name[packetID]).get(i).length() - 1);
					Object L1 = L1.class.getField(Name_l1).get(1);
					for (int o = 0 ; o < 22; o++) {
						if (L1 instanceof byte[]) {
							byte[] byte_type = (byte[]) L1;
							L2_data_temp_save.put(Name_l1 + "_" + o, byte_type[o]);
							byte_type[o] = (byte) temp_save.get(Name_l1 + "_" + o);
							L1.class.getField(Name_l1).set(byte_type, byte_type);
						} else if (L1 instanceof float[]) {
							float[] float_type = (float[]) L1;
							L2_data_temp_save.put(Name_l1 + "_" + o, float_type[0]);
							float_type[o] = (float) temp_save.get(Name_l1 + "_" + o);
							L1.class.getField(Name_l1).set(float_type, float_type);
						} else if (L1 instanceof short[]) {
							short[] double_type = (short[]) L1;
							L2_data_temp_save.put(Name_l1 + "_" + o, double_type[o]);
							double_type[o] = (short) temp_save.get(Name_l1 + "_" + o);
							L1.class.getField(Name_l1).set(double_type, double_type);
						} else if (L1 instanceof String[]) {
							String[] String_type = (String[]) L1;
							L2_data_temp_save.put(Name_l1 + "_" + o, String_type[o]);
							String_type[o] = (String) temp_save.get(Name_l1 + "_" + o);
							L1.class.getField(Name_l1).set(String_type, String_type);
						}
					}
				} else {
					String Name_l1 = Names.get(PacketID_name[packetID]).get(i);
					byte L1 = (byte) L1.class.getField(Name_l1).get(1);
					L2_data_temp_save.put(Name_l1, L1);
					L1.class.getField(Name_l1).set(L1, temp_save.get(Name_l1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (packetID == 2) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(2, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = m_frameIdentifier - data_temp_save.size() + 1 + i;
				if (data_temp_save.get(packet_num) == null) {
					L2.lap_data_packet.put(packet_num, L2.lap_data_packet.get(L1.frameIdentifier_lap_data));
				} else {
					L2.lap_data_packet.put(packet_num, data_temp_save.get(packet_num));
				}
			}
		} else if (packetID == 0) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(3, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = m_frameIdentifier - data_temp_save.size() + 1 + i;
				if (data_temp_save.get(packet_num) == null) {
					L2.motion_packet.put(packet_num, L2.motion_packet.get(L1.frameIdentifier_motion));
				} else {
					L2.motion_packet.put(packet_num, data_temp_save.get(packet_num));
				}
			}
		} else if (packetID == 6) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(1, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = m_frameIdentifier - data_temp_save.size() + 1 + i;
				if (data_temp_save.get(packet_num) == null) {
					L2.Car_telemetry_packet.put(packet_num, L2.Car_telemetry_packet.get(L1.frameIdentifier_car_telemetry));
				} else {
					L2.Car_telemetry_packet.put(packet_num, data_temp_save.get(packet_num));
				}
			}
		} else if (packetID == 7) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(0, m_frameIdentifier, L2_data_temp_save);;
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = m_frameIdentifier - data_temp_save.size() + 1 + i;
				if (data_temp_save.get(packet_num) == null) {
					L2.Car_status_packet.put(packet_num, L2.Car_status_packet.get(L1.frameIdentifier_Car_status));
				} else {
					L2.Car_status_packet.put(packet_num, data_temp_save.get(packet_num));
				}
			}
		} else if (packetID == 5) {
			L2.Car_setup_packet.put(L2.Car_setup_packet.size(), L2_data_temp_save);
		} else if (packetID == 4) {
			L2.participants_packet.put(L2.participants_packet.size(), L2_data_temp_save);
		} else if (packetID == 1) {
			L2.session_packet.put(L2.session_packet.size(), L2_data_temp_save);
		}
		
		
	}
	private static HashMap<Integer, HashMap<String, Object>> dropped_Packet(int array, int m_frameIdentifier, HashMap<String, Object> L2_data_temp_save) {
		HashMap<Integer, HashMap<String, Object>> data_temp_save = new HashMap<Integer, HashMap<String, Object>>();
		try {
			String blabla = "frameIdentifier_" + Packet_recieve.first_frameIdentifier_name[array];
			int frame = (int) L1.class.getField(blabla).get(1);
			if (m_frameIdentifier - 1 == frame) {
				data_temp_save.put(m_frameIdentifier, new HashMap() {{putAll(L2_data_temp_save);}});
					L1.class.getField(blabla).set(m_frameIdentifier, m_frameIdentifier);
			} else if (m_frameIdentifier <= frame) {
				System.out.println("error code #7"); //nog te bepalen error code
			} else if (m_frameIdentifier >= frame + 2) {
				L1.class.getField(blabla).set(m_frameIdentifier, m_frameIdentifier);
				for (int i = 0; i < m_frameIdentifier - frame - 1; i++) {
					data_temp_save.put(frame + 1 + i, null);
					System.out.println("error code #8 : " + (frame + 1 + i) + " : " + Packet_recieve.first_frameIdentifier_name[array]); //nog te bepalen error code
				}
				data_temp_save.put(m_frameIdentifier, new HashMap() {{putAll(L2_data_temp_save);}});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data_temp_save;
	}
}