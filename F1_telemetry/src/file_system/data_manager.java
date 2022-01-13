package file_system;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import File_reader.Names;
import Inkoming.Packet_recieve;

public class data_manager {
	public static void main(String[] args) {
		HashMap<String, Object> test = new HashMap<String, Object>();
		data(test, (byte)4, (float)0, (int)100);
	}
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public static void data(HashMap<String, Object> temp_save, byte packetID, float sessionTime, int m_frameIdentifier){
		String[] PacketID_name = new String[] {"Motion", "Session", "Lap_Data", "Event", "Participants", "Car_Setup", "Car_Telemetry", "Car_Status", "Final_Classificationt", "Lobby_Info", "Needed"};
		String[] data_names_untrimmed = new String[80];
		HashMap<String, String> data_codes = new HashMap<String, String>();
		int amount_of_names = 0;
		int[] places = new int[80];
		for (int i = 0; i < Names.Needed_data_packet.size(); i++) {
			if (Names.Needed_data_packet.get(Names.Needed_data_names[i]).equals(PacketID_name[packetID])) {
				data_names_untrimmed[i] = Names.Needed_data_names[i];
				data_codes.put(data_names_untrimmed[i], Names.Needed_data_byte.get(data_names_untrimmed[i]));
				places[amount_of_names] = i;
				amount_of_names++;
			}
		}
		String[] data_names = new String[amount_of_names];
		for (int i = 0; i < amount_of_names; i++) {
			data_names[i] = data_names_untrimmed[places[i]];
		}
		
		HashMap<String, Object> L2_data_temp_save = new HashMap<String, Object>();
		try {
			for (int i = 0; i < data_names.length; i++) {
				if (data_names[i].endsWith("_")) {
					String Name_l1 = data_names[i].substring(0, data_names[i].length() - 1);
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
					String Name_l1 = data_names[i];
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
			//System.out.println(data_temp_save.size());
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = L2.Lap_Data_packet.size() + 1;
				if (data_temp_save.get(i) == null) {
					//System.out.println(":(");
					L2.Lap_Data_packet.put(packet_num, L2.Lap_Data_packet.get(L2.Lap_Data_packet.size() - 1));
					//System.out.println(L2.Lap_Data_packet.get(packet_num));
				} else {
					L2.Lap_Data_packet.put(packet_num, data_temp_save.get(i));
				}
			}
			if (L2.Lap_Data_packet.size() >= 2000) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Lap_Data_packet);
				   new Thread(new Runnable() {
					    public void run() {
							data_compressie.encode("Lap_Data", encode_data);
							L1.Lap_Data = L1.Lap_Data + L2.Lap_Data_packet.size();
					    }
					}).start();
				   L2.Lap_Data_packet.clear();
			}
		} else if (packetID == 0) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(3, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = L2.Motion_packet.size() + 1;
				if (data_temp_save.get(i) == null) {
					L2.Motion_packet.put(packet_num, L2.Motion_packet.get(L2.Motion_packet.size() - 1));
				} else {
					L2.Motion_packet.put(packet_num, data_temp_save.get(i));
				}
			}
			if (L2.Motion_packet.size() >= 2000) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Motion_packet);
				new Thread(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Motion", encode_data);
						L1.Motion = L1.Motion + encode_data.size();
				    }
				}).start();
				L2.Motion_packet.clear();
			}
		} else if (packetID == 6) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(1, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = L2.Car_Telemetry_packet.size() + 1;
				if (data_temp_save.get(i) == null) {
					L2.Car_Telemetry_packet.put(packet_num, L2.Car_Telemetry_packet.get(L2.Car_Telemetry_packet.size() - 1));
				} else {
					L2.Car_Telemetry_packet.put(packet_num, data_temp_save.get(i));
				}
			}
			if (L2.Car_Telemetry_packet.size() >= 2000) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Car_Telemetry_packet);
				new Thread(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Car_Telemetry", encode_data);
						L1.Car_Telemetry = L1.Car_Telemetry + encode_data.size();
				    }
				}).start();
				L2.Car_Telemetry_packet.clear();
			}
		} else if (packetID == 7) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(0, m_frameIdentifier, L2_data_temp_save);;
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = L2.Car_Status_packet.size() + 1;
				if (data_temp_save.get(i) == null) {
					L2.Car_Status_packet.put(packet_num, L2.Car_Status_packet.get(L2.Car_Status_packet.size() - 1));
				} else {
					L2.Car_Status_packet.put(packet_num, data_temp_save.get(i));
				}
			}
			if (L2.Car_Status_packet.size() >= 2000) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Car_Status_packet);
				new Thread(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Car_Status", L2.Car_Status_packet);
						L1.Car_Status = L1.Car_Status + encode_data.size();
				    }
				}).start();
				L2.Car_Status_packet.clear();
			}
		} else if (packetID == 5) {
			L2_data_temp_save.put("sessionTime", sessionTime);
			L2.Car_Setup_packet.put(L2.Car_Setup_packet.size() + 1, L2_data_temp_save);
			if (L2.Car_Setup_packet.size() >= 400) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Car_Setup_packet);
				new Thread(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Car_Setups", encode_data);
						L1.Car_Setups = L1.Car_Setups + encode_data.size();
				    }
				}).start();
				L2.Car_Setup_packet.clear();
			}
		} else if (packetID == 4) {
			L2_data_temp_save.put("sessionTime", sessionTime);
			L2.Participants_packet.put(L2.Participants_packet.size() + 1, L2_data_temp_save);
			if (L2.Participants_packet.size() >= 400) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Participants_packet);
				new Thread(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Participants", encode_data);
						L1.Participants = L1.Participants + encode_data.size();
				    }
				}).start();
				L2.Participants_packet.clear();
			}
		} else if (packetID == 1) {
			L2_data_temp_save.put("sessionTime", sessionTime);
			L2.Session_packet.put(L2.Session_packet.size() + 1, L2_data_temp_save);
			if (L2.Session_packet.size() >= 400) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Session_packet);
				new Thread(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Session", encode_data);
						L1.Session = L1.Session + encode_data.size();
				    }
				}).start();
				L2.Session_packet.clear();
			}
		}
		
		
	}
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private static HashMap<Integer, HashMap<String, Object>> dropped_Packet(int array, int m_frameIdentifier, HashMap<String, Object> L2_data_temp_save) {
		HashMap<Integer, HashMap<String, Object>> data_temp_save = new HashMap<Integer, HashMap<String, Object>>();
		try {
			String blabla = "frameIdentifier_" + Packet_recieve.first_frameIdentifier_name[array];
			int frame = (int) L1.class.getField(blabla).get(1);
			if (m_frameIdentifier - 1 == frame) {
				data_temp_save.put(0, new HashMap() {{putAll(L2_data_temp_save);}});
					L1.class.getField(blabla).set(m_frameIdentifier, m_frameIdentifier);
			} else if (m_frameIdentifier <= frame) {
				//System.out.println("error code #7"); //nog te bepalen error code
			} else if (m_frameIdentifier >= frame + 2) {
				L1.class.getField(blabla).set(m_frameIdentifier, m_frameIdentifier);
				int o = 0;
				for (int i = 0; i < m_frameIdentifier - frame - 1; i++) {
					data_temp_save.put(o, null);
					o++;
					//System.out.println("error code #8 : " + (frame + 1 + i) + " : " + Packet_recieve.first_frameIdentifier_name[array]); //nog te bepalen error code
					if (Packet_recieve.first_frameIdentifier_name[array] == "lap_data") {
						System.out.println(L2.Lap_Data_packet.size());
					}
				}
				data_temp_save.put(o + 1, new HashMap() {{putAll(L2_data_temp_save);}});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data_temp_save;
	}
}