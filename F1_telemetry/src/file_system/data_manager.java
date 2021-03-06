package file_system;

import java.util.HashMap;

import File_reader.Names;
import Inkoming.Packet_recieve;

public class data_manager {
	public static void data(HashMap<String, Object> temp_save, byte packetID, float sessionTime, int m_frameIdentifier){
		String[] PacketID_name = new String[] {"Motion", "Session", "Lap_Data", "Event", "Participants", "Car_Setups", "Car_Telemetry", "Car_Status", "Final_Classificationt", "Lobby_Info", "Needed"};
		String[] data_names = Names.Needed_data_packet.get(PacketID_name[packetID]);
		int stop = 0;
		try {
			for (int i = 0; i < 30; i++) {
				if (data_names[i] != null) {
					stop++;
				}
			}
		} catch (Exception e) {
		}
		HashMap<String, Object> L2_data_temp_save = new HashMap<String, Object>();
		try {
			for (int i = 0; i < stop; i++) {
				if (data_names[i].endsWith("_")) {
					String Name_l1 = data_names[i].substring(0, data_names[i].length() - 1);
					Object L1 = L1.class.getField(Name_l1).get(1);
					for (int o = 0 ; o < 22; o++) {
						if (L1 instanceof byte[]) {
							byte[] byte_type = (byte[]) L1;
							byte_type[o] = (byte) temp_save.get(Name_l1 + "_" + o);
							L2_data_temp_save.put(data_names[i] + o, byte_type[o]);
							L1.class.getField(Name_l1).set(byte_type, byte_type);
						} else if (L1 instanceof float[]) {
							float[] float_type = (float[]) L1;
							float_type[o] = (float) temp_save.get(Name_l1 + "_" + o);
							L2_data_temp_save.put(data_names[i] + o, float_type[o]);
							L1.class.getField(Name_l1).set(float_type, float_type);
						} else if (L1 instanceof short[]) {
							short[] double_type = (short[]) L1;
							double_type[o] = (short) temp_save.get(Name_l1 + "_" + o);
							L2_data_temp_save.put(data_names[i] + o, double_type[o]);
							L1.class.getField(Name_l1).set(double_type, double_type);
						} else if (L1 instanceof String[]) {
							String[] String_type = (String[]) L1;
							String_type[o] = (String) temp_save.get(Name_l1 + "_" + o);
							L2_data_temp_save.put(data_names[i] + o, String_type[o]);
							L1.class.getField(Name_l1).set(String_type, String_type);
						}
					}
				} else {
					Object L1 = L1.class.getField(data_names[i]).get(1);
					if (L1 instanceof Byte) {
						L2_data_temp_save.put(data_names[i], (byte)L1);
						L1.class.getField(data_names[i]).set(L1, (byte)temp_save.get(data_names[i]));
					} else if (L1 instanceof Short) {
						L2_data_temp_save.put(data_names[i], (Short)L1);
						L1.class.getField(data_names[i]).set(L1, (Short)temp_save.get(data_names[i]));
					}
				}
			}
		} catch (Exception e) {
		}
		if (packetID == 2) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(2, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = L2.Lap_Data_packet.size() + 1;
				if (data_temp_save.get(i) == null) {
					L2.Lap_Data_packet.put(packet_num, L2.Lap_Data_packet.get(L2.Lap_Data_packet.size() - 1));
				} else {
					L2.Lap_Data_packet.put(packet_num, data_temp_save.get(i));
				}
			}
			if (L2.Lap_Data_packet.size() >= 10) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Lap_Data_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
						data_compressie.encode("Lap_Data", encode_data);
						L1.Lap_Data = L1.Lap_Data + encode_data.size();
				    }
				});
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
			if (L2.Motion_packet.size() >= 10) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Motion_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Motion", encode_data);
						L1.Motion = L1.Motion + encode_data.size();
				    }
				});
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
			if (L2.Car_Telemetry_packet.size() >= 10) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Car_Telemetry_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Car_Telemetry", encode_data);
						L1.Car_Telemetry = L1.Car_Telemetry + encode_data.size();
				    }
				});
				L2.Car_Telemetry_packet.clear();
			}
		} else if (packetID == 7) {
			HashMap<Integer, HashMap<String, Object>> data_temp_save = dropped_Packet(0, m_frameIdentifier, L2_data_temp_save);
			for (int i = 0; i < data_temp_save.size(); i++) {
				int packet_num = L2.Car_Status_packet.size() + 1;
				if (data_temp_save.get(i) == null) {
					L2.Car_Status_packet.put(packet_num, L2.Car_Status_packet.get(L2.Car_Status_packet.size() - 1));
				} else {
					L2.Car_Status_packet.put(packet_num, data_temp_save.get(i));
				}
			}
			if (L2.Car_Status_packet.size() >= 10) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Car_Status_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Car_Status", encode_data);
						L1.Car_Status = L1.Car_Status + encode_data.size();
				    }
				});
				L2.Car_Status_packet.clear();
			}
		} else if (packetID == 5) {
			L2.Car_Setup_packet.put(L2.Car_Setup_packet.size() + 1, L2_data_temp_save);
			if (L2.Car_Setup_packet.size() >= 10) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Car_Setup_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Car_Setups", encode_data);
						L1.Car_Setups = L1.Car_Setups + encode_data.size();
				    }
				});
				L2.Car_Setup_packet.clear();
			}
		} else if (packetID == 4) {
			L2.Participants_packet.put(L2.Participants_packet.size() + 1, L2_data_temp_save);
			if (L2.Participants_packet.size() >= 1) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Participants_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Participants", encode_data);
						L1.Participants = L1.Participants + encode_data.size();
				    }
				});
				L2.Participants_packet.clear();
			}
		} else if (packetID == 1) {
			L2.Session_packet.put(L2.Session_packet.size() + 1, L2_data_temp_save);
			if (L2.Session_packet.size() >= 10) {
				final HashMap<Integer, HashMap<String, Object>> encode_data = new HashMap<Integer, HashMap<String, Object>>();
				encode_data.putAll(L2.Session_packet);
				Packet_recieve.service.submit(new Runnable() {
				    public void run() {
				    	data_compressie.encode("Session", encode_data);
						L1.Session = L1.Session + encode_data.size();
				    }
				});
				L2.Session_packet.clear();
			}
		}
		
		
	}
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private static HashMap<Integer, HashMap<String, Object>> dropped_Packet(int array, int m_frameIdentifier, HashMap<String, Object> L2_data_temp_save) {
		HashMap<Integer, HashMap<String, Object>> data_temp_save = new HashMap<Integer, HashMap<String, Object>>();
		try {
			String Frame_name = "frameIdentifier_" + Packet_recieve.first_frameIdentifier_name[array];
			int frame = (int) L1.class.getField(Frame_name).get(1);
			if (m_frameIdentifier - 1 == frame) {
				data_temp_save.put(0, new HashMap() {{putAll(L2_data_temp_save);}});
					L1.class.getField(Frame_name).set(m_frameIdentifier, m_frameIdentifier);
			} else if (m_frameIdentifier <= frame) {
			} else if (m_frameIdentifier >= frame + 2) {
				L1.class.getField(Frame_name).set(m_frameIdentifier, m_frameIdentifier);
				int i = 0;
				for (i = 0; i < m_frameIdentifier - frame - 1; i++) {
					data_temp_save.put(i, null);
					i++;
				}
				data_temp_save.put(i + 1, new HashMap() {{putAll(L2_data_temp_save);}});
			}
		} catch (Exception e) {
		}
		return data_temp_save;
	}
}