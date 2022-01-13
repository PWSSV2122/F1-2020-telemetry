package Inkoming;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import data_compute.delta;
import file_system.L1;
import file_system.data_manager;

import java.nio.ByteBuffer;

public class Packet_recieve {
	public static boolean recieve_on;
	public static String[] first_frameIdentifier_name = new String[] {"Car_status", "car_telemetry", "lap_data", "motion"};
	public static int Player_lap = 0;
	public static void recieve_class() {
		data_decode();
		boolean[] first_packet = new boolean[] {true, true, true, true};
		int[] packetid = new int[] {7, 6, 2, 0};
		try {
			DatagramSocket socket = new DatagramSocket(20777); //var
			while (recieve_on == true) {
				byte[] test = new byte[2000];
				DatagramPacket response = new DatagramPacket(test, test.length);
				socket.receive(response);
				
				String quote = new String(test, 0, response.getLength());
				byte[] e = quote.getBytes();
				
//		        for (byte b : e) {
//		            String st = String.format("%02X ", b);
//		            System.out.print(st);
//		        }
//		        System.out.println("\n");

		        HashMap<String, Object> Header = new HashMap<String, Object>();
				Header.put("packetFormat", (short)((e[1] & 0xFF) << 8) | (e[0] & 0xFF));
				Header.put("gameMajorVersion", (byte) e[2]);
				Header.put("gameMinorVersion", (byte) e[3]);
				Header.put("packetVersion", (byte) e[4]);
				Header.put("packetId", (byte) e[5]);
				Header.put("sessionUID", (long)((e[13] & 0xFFL) << 56) | ((e[12] & 0xFFL) << 48) | ((e[11] & 0xFFL) << 40) | ((e[10] & 0xFFL) << 32) | ((e[9] & 0xFFL) << 24) | ((e[8] & 0xFFL) << 16) | ((e[7] & 0xFFL) << 8) | ((e[6] & 0xFFL) << 0));
				Header.put("sessionTime", ByteBuffer.wrap(new byte[] {e[14], e[15], e[16], e[17]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
				Header.put("frameIdentifier", (int)(((e[21] & 0xFF) << 24) | ((e[20] & 0xFF) << 16) | ((e[19] & 0xFF) << 8) | ((e[18] & 0xFF) << 0)));
				Header.put("playerCarIndex", (byte) e[22]);
				Header.put("secondaryPlayerCarIndex", (byte) e[23]);
				
				for (int i = 0; i < 4; i++) {
					if (first_packet[i] == true && (byte) Header.get("packetId") == (byte) packetid[i]) {
						int frameIdentifier = (int) Header.get("frameIdentifier") - 1;
						L1.class.getField("frameIdentifier_" + first_frameIdentifier_name[i]).set(frameIdentifier, frameIdentifier);
						first_packet[i] = false;
					}
				}

				HashMap<String, Object> Data_decode = new HashMap<String, Object>();
				byte PacketId = (byte) Header.get("packetId");
				if (PacketId == 1) { //session packet 
					for (int i = 0; i < Packet_names.get(File_Path[PacketId]).size() ; i++) {
						byte ofset = 0;
						if (Data_decode.get("m_numMarshalZones") != null) {
							ofset = (byte) ((byte) Data_decode.get("m_numMarshalZones") * 5);
						}
						int[] array_places = Packet_byte_array.get(File_Path[PacketId]).get(i);
						if (Packet_names.get(File_Path[PacketId]).get(i).endsWith("_")) {
							if (i == 16 || i == 17) {
								for (int o = 0; o < (byte) Data_decode.get("m_numMarshalZones"); o++) {
									byte ofset2 = (byte) (5 * o);
									if (array_places.length == 1) {
										Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, (byte) e[array_places[0] + ofset2]);
									} else if (array_places.length == 4) {
										Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset2], e[array_places[1] + ofset2], e[array_places[2] + ofset2], e[array_places[3] + ofset2]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
									}
								}
							} else {
								for (int o = 0; o < (byte) Data_decode.get("m_numWeatherForecastSamples"); o++) {
									byte ofset2 = (byte) (5 * o + ofset);
									Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, (byte) e[array_places[0] + ofset2]);
								}
							}
						} else {
							if (array_places.length == 1) {
								Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i), (byte) e[array_places[0] + ofset]);
							} else if (array_places.length == 2) {
								Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i), ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset], e[array_places[1] + ofset]}).order(ByteOrder.LITTLE_ENDIAN).getShort());
							}
						}
					}
				} else if (PacketId == 3) { //event packet
					int[] array_places = Packet_byte_array.get(File_Path[PacketId]).get(0);
					Data_decode.put(Packet_names.get(File_Path[PacketId]).get(0), new String(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}));
					String Infringment = Packet_names.get(File_Path[PacketId]).get(0);
					if (Infringment == "PENA") {
						for (int i = 0; i < 7; i++) {
							Data_decode.put(Packet_names.get(File_Path[PacketId]).get(6 + i), (int) e[Packet_byte_array.get(File_Path[PacketId]).get(6 + i)[0]]);
						}
					} else if (Infringment == "FTLP") {
						Data_decode.put(Packet_names.get(File_Path[PacketId]).get(1), (int) e[Packet_byte_array.get(File_Path[PacketId]).get(2)[0]]);
						array_places = Packet_byte_array.get(File_Path[PacketId]).get(2);
						Data_decode.put(Packet_names.get(File_Path[PacketId]).get(2), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					} else if (Infringment == "SPTP") {
						Data_decode.put(Packet_names.get(File_Path[PacketId]).get(13), (int) e[Packet_byte_array.get(File_Path[PacketId]).get(13)[0]]);
						array_places = Packet_byte_array.get(File_Path[PacketId]).get(14);
						Data_decode.put(Packet_names.get(File_Path[PacketId]).get(14), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					}
					String[] Infringment_type = {"RTMT", "TMPT", "RCWN"};
					for (int i = 0; i < 3; i++) {
						if (Infringment == Infringment_type[i]) {
							Data_decode.put(Packet_names.get(File_Path[PacketId]).get(3 + i), (int) e[Packet_byte_array.get(File_Path[PacketId]).get(3 + i)[0]]);
						}
					}					
				} else if (PacketId < 10) {
					for (int i = 0; i < Packet_names.get(File_Path[PacketId]).size(); i++) {
						int Data_type = Packet_byte_array.get(File_Path[PacketId]).get(i).length;
						int[] array_places = Packet_byte_array.get(File_Path[PacketId]).get(i);
						if (Packet_names.get(File_Path[PacketId]).get(i).endsWith("_")) {
							for (int o = 0; o < 22; o++) {
								int ofset = repeats[PacketId] * o;
								if (Data_type == 1) {
									Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, (byte) e[array_places[0] + ofset]);
								} else if (Data_type == 2) {
									Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset], e[array_places[1] + ofset]}).order(ByteOrder.LITTLE_ENDIAN).getShort());
								} else if (Data_type == 4) {
									Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset], e[array_places[1] + ofset], e[array_places[2] + ofset], e[array_places[3] + ofset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
								} else if (Data_type == 48) {
									byte[] byte_temp_save = new byte[48];
									for (int p = 0; p < 48; p++) {
										byte_temp_save[p] = e[array_places[p] + ofset];
									}
									Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i) + o, new String(byte_temp_save, StandardCharsets.UTF_8));
								} else {
									System.out.println("error code #4"); //nog te bepalen error code
								}
							}
						} else {
							if (Data_type == 1) {
								Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i), (byte) e[array_places[0]]);
							} else if (Data_type == 2) {
								Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[0]]}).order(ByteOrder.LITTLE_ENDIAN).getShort());
							} else if (Data_type == 4) {
								Data_decode.put(Packet_names.get(File_Path[PacketId]).get(i), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
							} else {
								System.out.println("error code #5"); //nog te bepalen error code
							}
						}
					}
				} else {
					System.out.println("error code #6"); //nog te bepalen error code
				}
				
				HashMap<String, Object> Needed_data = new HashMap<String, Object>();
				for (int i = 0; i < Needed_data_names.length; i++) {
					if (Needed_data_names[i].endsWith("_")) {
						if (Data_decode.get("m_" + Needed_data_names[i] + "1") != null) {
							for (int o = 0; o < 22; o++) {
								Needed_data.put(Needed_data_names[i] + o, Data_decode.get("m_" + Needed_data_names[i] + o));
							}
						}
					} else {
						if (Data_decode.get("m_" + Needed_data_names[i]) != null) {
							Needed_data.put(Needed_data_names[i], Data_decode.get("m_" + Needed_data_names[i]));
						}
					}
				}
				data_manager.data(Needed_data, (byte) Header.get("packetId"), (float) Header.get("sessionTime"), (int) Header.get("frameIdentifier"));
				
				if (PacketId == 2) {
					for (int i = 0; i < 22; i++) {
						delta.speed_of_players((float) Data_decode.get("m_lapDistance_" + i), i);
						L1.position.put((byte) Data_decode.get("m_carPosition_" + i), i);
					}
					delta.delta_time();
				} else if (PacketId == 1) {
					delta.trackLength = (Short) Data_decode.get("m_trackLength");
				}
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static String[] File_Path = new String[] {"Motion_Packet", "Session_Packet", "Lap_Data_Packet", "Event_Packet", "Participants_Packet", "Car_Setups_Packet", "Car_Telemetry_Packet", "Car_Status_Packet", 
			"Final_Classification_Packet", "Lobby_Info_Packet", "Needed_data"};
	static HashMap<String, HashMap<Integer, String>> Packet_names = new HashMap<String, HashMap<Integer, String>>();
	static HashMap<String, HashMap<Integer, int[]>> Packet_byte_array = new HashMap<String, HashMap<Integer, int[]>>();;
	static String[] Needed_data_names = new String[74];
	static int[] repeats = new int[10];
	private static void data_decode() {
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
			while ((Line = reader.readLine()) != null) {
				String[] split = Line.split(" : ", 3);
				if (split.length >= 3) {
					Needed_data_names[i] = split[0];
					i++;
				} else {
					System.out.println("error code #3"); //nog te bepalen error code
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}