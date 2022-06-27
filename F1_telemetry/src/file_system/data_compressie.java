package file_system;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;

import File_reader.Names;
import application.Main;

public class data_compressie {

	public static void encode(String Packet, HashMap<Integer, HashMap<String, Object>> compression_data) {
		HashMap<String, String> data_codes = new HashMap<String, String>();
		for (int i = 0; i < Names.Needed_data_byte.get(Packet).length; i++) {
			data_codes.put(Names.Needed_data_packet.get(Packet)[i], Names.Needed_data_byte.get(Packet)[i]);
		}
		data_codes.put(Names.Needed_data_packet.get("Header")[0], Names.Needed_data_byte.get("Header")[0]);
		int stop = 0;
		String data_names[] = Names.Needed_data_packet.get(Packet);
		for (int i = 0; i < data_names.length; i++) {
			if (data_names[i] != null) {
				stop++;
			}
		}
		
		HashMap<String, Object> last_data = new HashMap<String, Object>();

		String output = "";
		try {
			for (int i = 0; i < compression_data.size(); i++) {
				output += data_codes.get("packetid");
				try {
					output += binary_data(ByteBuffer.allocate(4).putInt(i + L1.class.getField(Packet).getInt(Packet)).array());
				} catch (Exception e) {
				}
				for (int o = 0; o < stop; o++) {
					if (data_names[o].endsWith("_")) {
						Object data = null;
						try {
							data = compression_data.get(i+1).get(data_names[o] + 1);
						} catch (Exception e2) {
							data = (long)1;
						}
						if (data.getClass().equals(Byte.class)) {
							byte[] data_byte = new byte[22];
							byte[] data_byte_compaire = (byte[]) last_data.get(data_names[o]);
							
							int change = 0;
							String temp = "";
							for (int p = 0; p < 22; p++) {
								data_byte[p] = (byte) compression_data.get(i + 1).get(data_names[o] + p);
								if (i != 0 && data_byte[p] == data_byte_compaire[p]) {
								} else {
									temp += binary_num((byte) p);
									temp += binary_data(new byte[] {data_byte[p]});
									change++;
								}
							}
							if(change > 0) {
								output += data_codes.get(data_names[o]);
								output += temp;
								last_data.put(data_names[o], data_byte);
							}
						} else if (data.getClass().equals(Float.class)) {
							float[] data_float = new float[22];
							float[] data_float_compaire = (float[]) last_data.get(data_names[o]);
						
							int change = 0;
							String temp = "";
							for (int p = 0; p < 22; p++) {
								data_float[p] = (float) compression_data.get(i + 1).get(data_names[o] + p);
								if (i != 0 && data_float[p] == data_float_compaire[p]) {
								} else {
									temp += binary_num((byte) p);
									temp += binary_data(ByteBuffer.allocate(4).putFloat(data_float[p]).array());
									change++;
								}
							}
							if (change > 0) {
								output += data_codes.get(data_names[o]);
								output += temp;
								last_data.put(data_names[o], data_float);
							}
						} else if (data.getClass().equals(Short.class)) {
							short[] data_short = new short[22];
							short[] data_short_compaire = (short[]) last_data.get(data_names[o]);
							
							int change = 0;
							String temp = "";
							for (int p = 0; p < 22; p++) {
								data_short[p] = (short) compression_data.get(i + 1).get(data_names[o] + p);
								if (i != 0 && data_short[p] == data_short_compaire[p]) {
								} else {
									temp += binary_num((byte) p);
									temp += binary_data(ByteBuffer.allocate(2).putShort(data_short[p]).array());
									change++;
								}
							}
							if (change > 0) {							
								output += data_codes.get(data_names[o]);
								output += temp;
								last_data.put(data_names[o], data_short);
							}	
						} else if (data.getClass().equals(String.class)) {
							String[] data_String = new String[22];
							String[] data_String_compaire = (String[]) last_data.get(data_names[o]);
							
							int change = 0;
							String temp = "";
							for (int p = 0; p < 22; p++) {
								data_String[p] = (String) compression_data.get(i + 1).get(data_names[o] + p);
								if (i != 0 && data_String[p] == data_String_compaire[p]) {
								} else {
									temp += binary_num((byte) p);
									temp += binary_data(ByteBuffer.allocate(48).put(data_String[p].getBytes()).array());
									change++;
								}
							}
							if (change > 0) {
								output += data_codes.get(data_names[o]);
								output += temp;
								last_data.put(data_names[o], data_String);
							}		
						}
					} else {
						if (compression_data.get(i + 1).get(data_names[o]) instanceof Byte) {
							byte temp = (byte)compression_data.get(i + 1).get(data_names[o]);
							output += data_codes.get(data_names[o]);
							output += binary_num(temp);
							
						} else if (compression_data.get(i + 1).get(data_names[o]) instanceof Short) {
							Short temp = (Short)compression_data.get(i + 1).get(data_names[o]);
							output += data_codes.get(data_names[o]);
							output += binary_data(ByteBuffer.allocate(2).putShort(temp).array());
						}
					}
				}
			}
		} catch (Exception e) {
		}
		byte[] data = new BigInteger(output, 2).toByteArray();
		try (FileOutputStream write = new FileOutputStream(Main.dir + "Saves/temp/" + Packet + ".dec", true)) {
			write.write(data);
		} catch (Exception e) {
		}
	}
	static private String binary_num(byte o) {
		StringBuilder sb = new StringBuilder("00000000");
		for (int bit = 0; bit < 8; bit++) {
			if (((o >> bit) & 1) > 0) {
				sb.setCharAt(7 - bit, '1');
			}
		}
		return sb.toString();
	}
	
	static private String binary_data(byte[] data) {
		String temp = "";
		for (int i = 0; i < data.length; i++) {
			StringBuilder sb = new StringBuilder("00000000");
			for (int bit = 0; bit < 8; bit++) {
				if (((data[i] >> bit) & 1) > 0) {
					sb.setCharAt(7 - bit, '1');
				}
			}
			temp += sb.toString();
		}
		return temp;
	}
}