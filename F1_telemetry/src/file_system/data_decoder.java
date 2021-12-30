package file_system;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import File_reader.Names;

public class data_decoder {
	static public void decode(String save) {
		Names.data_decode();
		HashMap <String, HashMap<Integer, HashMap<String, Object>>> decoded_data = new HashMap <String, HashMap<Integer, HashMap<String, Object>>>();
		for (int i = 0; i < Names.needed_data_packets.length; i++) {
			String[] data_names = new String[80];
			HashMap<String, Byte> data_codes_string = new HashMap<String, Byte>();
			HashMap<Byte, String> data_codes_byte = new HashMap<Byte, String>();
			for (int o = 0; o < Names.Needed_data_packet.size(); o++) {
				if (Names.Needed_data_packet.get(Names.Needed_data_names[o]).equals(Names.needed_data_packets[i]) || Names.Needed_data_packet.get(Names.Needed_data_names[o]).equals("Header")) {
					data_names[data_names.length] = Names.Needed_data_names[o];
					
					byte[] data = null;
					if (Names.Needed_data_byte.get(data_names[o]).length() % 8 != 0) {
					  	System.out.println("error code #7"); //nog te bepalen error code
				    } else {
					    data = new byte[Names.Needed_data_byte.get(data_names[o]).length() / 8];
					    for (int p = 0; p < Names.Needed_data_byte.get(data_names[o]).length(); p++) {
					        char c = Names.Needed_data_byte.get(data_names[o]).charAt(p);
					        if (c == '1') {
					            data[p >> 3] |= 0x80 >> (p & 0x7);
					        } else if (c != '0') {
					            throw new IllegalArgumentException("Invalid char in binary string : " + c + " : should be 1 or 0");
					        }
					    }
					}
					data_codes_string.put(data_names[data_names.length - 1], data[0]);
					data_codes_byte.put(data[0], data_names[data_names.length - 1]);
				}
			}
			Path path = Paths.get("src/Saves/" + save + "/" + Names.needed_data_packets[i] + ".dec");
			HashMap<Integer, HashMap<String, Object>> Temp_packets = new HashMap<Integer, HashMap<String, Object>>();
			HashMap<String, Object> Temp_packet = new HashMap<String, Object>();
			try {
				int packetid = 0;
				byte[] data = Files.readAllBytes(path);
				for (int o = 0; o < data.length; o++) {
					if (data[o] == (byte) data_codes_string.get("packetid")) {
						if (data[o + 1] == 0) {	
						} else {
							Temp_packets.put(packetid, Temp_packet);
							Temp_packet.clear();
						}
						packetid = data[o + 1];
						o++;
					} else {
						String data_name = data_codes_byte.get(data[o]);
						int data_type = Names.Packet_byte_array.get(Names.needed_data_packets[o]).get("m_" + data_name).length;
						if (data_name.endsWith("_")) {
							for (int p = 0; p < 22; p++) {
								if (data_codes_byte.containsKey(data[o + 1 + (data_type + 1) * p])) {
									
								} else {
									byte[] decode = new byte[data_type];
									for (int l = 0; l < data_type; i++) {
										decode[l] = data[o + 2 + l + p * (data_type + 1)];
									}
									Temp_packet.put(data_name + data[o + 1 + (data_type)], decode(decode));
								}
							}
						} else {
							byte[] decode = new byte[data_type];
							for (int l = 0; l < data_type; i++) {
								decode[l] = data[o + 2 + l];
							}
							Temp_packet.put(data_name, decode(decode));
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	static private Object decode(byte[] decode) {
		Object data_return = null;
		if (decode.length == 1) {
			data_return = decode;
		} else if (decode.length == 2) {
			data_return = ByteBuffer.wrap(new byte[] {decode[0], decode[1]}).order(ByteOrder.LITTLE_ENDIAN).getShort();
		} else if (decode.length == 4) {
			data_return = ByteBuffer.wrap(new byte[] {decode[0], decode[1], decode[2], decode[3]}).order(ByteOrder.BIG_ENDIAN).getFloat();
		}  else if (decode.length == 48) {
			data_return = new String(decode, StandardCharsets.UTF_8);
		}
		return data_return;
	}
}