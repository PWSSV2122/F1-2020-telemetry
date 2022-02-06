package decode;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import File_reader.Names;

public class data_decoder {
	
	public static int[] tot_packet = new int[] {20, Settings.Settings_var.send_rate * 10, Settings.Settings_var.send_rate * 10,
			Settings.Settings_var.send_rate * 10, Settings.Settings_var.send_rate * 10, 2, 20};
	
	//public static int[] tot_packet = new int[] {10000, 10000, 10000, 10000, 10000, 10000, 10000};
	
	public static String[] packetid = new String[] {"Car_Setups", "Car_Status", "Car_Telemetry", "Lap_Data", "Motion", "Participants", "Session"};
	public static int packetcounter = 0;
	private static int[] starting_point = new int[8];
	public static HashMap <String, HashMap<Integer, HashMap<String, Object>>> decode(String save) {
		HashMap <String, HashMap<Integer, HashMap<String, Object>>> decoded_data = new HashMap <String, HashMap<Integer, HashMap<String, Object>>>();
		try {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 7; i++) {
				
				HashMap<String, Byte> data_codes_string = new HashMap<String, Byte>();
				HashMap<Byte, String> data_codes_byte = new HashMap<Byte, String>();

				byte Code = 0;
				for (int o = 0; o < Names.Needed_data_packet.get(packetid[i]).length; o++) {
					if (Names.Needed_data_packet.get(packetid[i])[o] != null) {
						if (Names.Needed_data_byte.get(packetid[i])[o].length() % 8 != 0) {
						  	System.out.println("error code #7"); //nog te bepalen error code
					    } else {
						    for (int p = 0; p < Names.Needed_data_byte.get(packetid[i])[o].length(); p++) {
						        char c = Names.Needed_data_byte.get(packetid[i])[o].charAt(p);
						        if (c == '1') {
						        	Code |= 0x80 >> (p & 0x7);
						        } else if (c != '0') {
						            throw new IllegalArgumentException("Invalid char in binary string : " + c + " : should be 1 or 0");
						        }
						    }
						}
						data_codes_string.put(Names.Needed_data_packet.get(packetid[i])[o], Code);
						data_codes_byte.put(Code, Names.Needed_data_packet.get(packetid[i])[o]);
						Code = 0;
					} else {
					}
				}
				
				byte Header = 0;
				for (int p = 0; p < Names.Needed_data_byte.get("Header")[0].length(); p++) {
					char c = Names.Needed_data_byte.get("Header")[0].charAt(p);
				    if (c == '1') {
				    	Header |= 0x80 >> (p & 0x7);
				    } else if (c != '0') {
				        throw new IllegalArgumentException("Invalid char in binary string : " + c + " : should be 1 or 0");
				    }
				}
				data_codes_string.put("packetid", Header);
				data_codes_byte.put(Header, "packetid");
			
				HashMap<String, Integer> key = new HashMap<String, Integer>();
				for (int o = 0 ; o < Names.Packet_names.get(Names.needed_data_packets[i] + "_Packet").size(); o++) {
					key.put(Names.Packet_names.get(Names.needed_data_packets[i] + "_Packet").get(o), o);
				}
				
				Path path = Paths.get("src/Saves/" + save + "/" + packetid[i] + ".dec");
				HashMap<Integer, HashMap<String, Object>> Temp_packets = new HashMap<Integer, HashMap<String, Object>>();
				HashMap<String, Object> Temp_packet = new HashMap<String, Object>();
				try {
					byte[] data = Files.readAllBytes(path);
					int o;
					for (o = 0 + starting_point[i]; o < data.length; o++) {
						//System.out.println(o);
						if (data[o] == Header) {
							if (ByteBuffer.wrap(new byte[] {data[o + 1] , data[o + 2], data[o + 3], data[o + 4]}).order(ByteOrder.BIG_ENDIAN).getInt() == 0) {
							} else {
								Temp_packets.put(packetcounter, new HashMap<String, Object>() {{putAll(Temp_packet);}});
								Temp_packet.clear();
								if (i == 2) {
									System.out.println(Temp_packets.get(packetcounter).get("speed_0"));
								}
							}
							packetcounter = ByteBuffer.wrap(new byte[] {data[o + 1] , data[o + 2], data[o + 3], data[o + 4]}).order(ByteOrder.BIG_ENDIAN).getInt();
							o = o + 4;
							if (packetcounter == tot_packet[i]) {
								//System.out.println("wel");
								starting_point[i] = o - 4;
								o = data.length;
							}
						} else {
							String data_name = data_codes_byte.get(data[o]);
							if (key.get("m_" + data_name) == null) {
								System.out.println("BAD");
							}
							int int_key = key.get("m_" + data_name);
							int data_type = Names.Packet_byte_array.get(Names.needed_data_packets[i] + "_Packet").get(int_key).length;
							if (data_name.endsWith("_")) {
								for (int p = 0; p < 22; p++) {
									if (o + 1 == data.length) {
										p = 22;
									} else if (data_codes_byte.containsKey(data[o + 1])) {
										p = 22;
									} else {
										byte[] decode = new byte[data_type];
										for (int l = 0; l < data_type; l++) {
											decode[l] = data[o + 2 + l];
										}
										Temp_packet.put(data_name + data[o + 1], decode(decode));
//										if (data_name.equals("speed_")) {
//											System.out.println(data_name + 0 + " : " + Temp_packet.get(data_name + 0));
//										}
										o = o + data_type + 1;
									}
								}
							} else {
								byte[] decode = new byte[data_type];
								if (!(o + data_type >= data.length)) {
									for (int l = 1; l < data_type + 1; l++) {
										decode[l - 1] = data[o + l];
									}
								}
								Temp_packet.put(data_name, decode(decode));
								o = o + data_type;
							}
						}
					}
					if (o == data.length - 1) {
						data_to_file_system.end_of_file = true;
					}
					decoded_data.put(packetid[i], new HashMap<Integer, HashMap<String, Object>>() {{putAll(Temp_packets);}});
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println(packetcounter);
			}
			long Stop = System.currentTimeMillis();
			//System.out.println(Stop - start);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decoded_data;
	}
	static private Object decode(byte[] decode) {
		Object data_return = null;
		if (decode.length == 1) {
			data_return = decode[0];
		} else if (decode.length == 2) {
			data_return = ByteBuffer.wrap(new byte[] {decode[0], decode[1]}).order(ByteOrder.BIG_ENDIAN).getShort();
		} else if (decode.length == 4) {
			data_return = ByteBuffer.wrap(new byte[] {decode[0], decode[1], decode[2], decode[3]}).order(ByteOrder.BIG_ENDIAN).getFloat();
		}  else if (decode.length == 48) {
			data_return = new String(decode, StandardCharsets.UTF_8);
		}
		return data_return;
	}
}