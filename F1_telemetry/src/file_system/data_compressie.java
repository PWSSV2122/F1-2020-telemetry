package file_system;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;

import File_reader.Names;

public class data_compressie {

	public static void encode(String Packet, HashMap<Integer, HashMap<String, Object>> compression_data) {
		int time_start = (int) System.currentTimeMillis();
		String[] data_names_untrimmed = new String[80];
		HashMap<String, String> data_codes = new HashMap<String, String>();
		int amount_of_names = 0;
		int[] places = new int[80];
		for (int i = 0; i < Names.Needed_data_packet.size(); i++) {
			if (Names.Needed_data_packet.get(Names.Needed_data_names[i]).equals(Packet) || Names.Needed_data_packet.get(Names.Needed_data_names[i]).equals("Header")) {
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
		
		HashMap<String, Object> last_data = new HashMap<String, Object>();
		String[] data_names_temp = new String[data_names.length - 2];
		int l = 0;
		for (int i = 0; i < data_names.length ; i++) {
			if (data_names[i].equals("packetid") || data_names[i].equals("sessionTime")) {
			} else if (data_names[i].endsWith("_")) {
				data_names_temp[l] = data_names[i];
				l++;
				for (int o = 0; o < 22; o ++) {
					last_data.put(data_names[i] + o, null);
				}
			} else {
				data_names_temp[l] = data_names[i];
				l++;
				last_data.put(data_names[i], null);
			}
		}
		
		data_names = new String[data_names_temp.length];
		for (int i = 0; i < data_names_temp.length; i++) {
			data_names[i] = data_names_temp[i];
		}
		
		int time_end = (int) System.currentTimeMillis();
		System.out.println("name vars : " + (time_end - time_start));
		time_start = (int) System.currentTimeMillis();
		String output = "";
		for (int i = 0; i < compression_data.size(); i++) {
			output += data_codes.get("packetid");
			try {
				output += binary_data(ByteBuffer.allocate(4).putInt(i + L1.class.getField(Packet).getInt(Packet)).array());
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int o = 0; o < data_names.length; o++) {
				if (data_names[o].endsWith("_")) {
					Object data = compression_data.get(i+1).get(data_names[o] + 1);
					if (data.getClass().equals(Byte.class)) {
						byte[] data_byte = new byte[22];
						for (int p = 0; p < 22; p++) {
							data_byte[p] = (byte) compression_data.get(i + 1).get(data_names[o] + p);
						}
						byte[] data_byte_compaire = (byte[]) last_data.get(data_names[o]);
						
						int change = 0;
						for (int p = 0; p < 22; p++) {
							if (i != 0 && data_byte[p] == data_byte_compaire[p]) {
							} else {
								output += binary_num((byte) p);
								output += binary_data(new byte[] {data_byte[p]});
								change++;
							}
						}
						if(change > 0) {
							output += data_codes.get(data_names[o]);
							last_data.put(data_names[o], data_byte);
						}
					} else if (data.getClass().equals(Float.class)) {
						float[] data_float = new float[22];
						for (int p = 0; p < 22; p++) {
							data_float[p] = (float) compression_data.get(i + 1).get(data_names[o] + p);
						}
						float[] data_float_compaire = (float[]) last_data.get(data_names[o]);
					
						int change = 0;
						for (int p = 0; p < 22; p++) {
							if (i != 0 && data_float[p] == data_float_compaire[p]) {
							} else {
								output += binary_num((byte) p);
								output += binary_data(ByteBuffer.allocate(4).putFloat(data_float[p]).array());
								change++;
							}
						}
						if (change > 0) {
							output += data_codes.get(data_names[o]);
							last_data.put(data_names[o], data_float);
						}
					} else if (data.getClass().equals(Short.class)) {
						short[] data_short = new short[22];
						for (int p = 0; p < 22; p++) {
							data_short[p] = (short) compression_data.get(i + 1).get(data_names[o] + p);
						}
						short[] data_short_compaire = (short[]) last_data.get(data_names[o]);
						int change = 0;
						
						for (int p = 0; p < 22; p++) {
							if (i != 0 && data_short[p] == data_short_compaire[p]) {
							} else {
								output += binary_num((byte) p);
								output += binary_data(ByteBuffer.allocate(2).putShort(data_short[p]).array());
								change++;
							}
						}
						if (change > 0) {
							output += data_codes.get(data_names[o]);
							last_data.put(data_names[o], data_short);
						}	
					} else if (data.getClass().equals(String.class)) {
						String[] data_String = new String[22];
						for (int p = 0; p < 22; p++) {
							data_String[p] = (String) compression_data.get(i + 1).get(data_names[o] + p);
						}
						String[] data_String_compaire = (String[]) last_data.get(data_names[o]);
						int change = 0;
						
						for (int p = 0; p < 22; p++) {
							if (i != 0 && data_String[p] == data_String_compaire[p]) {
							} else {
								output += binary_num((byte) p);
								output += binary_data(ByteBuffer.allocate(48).put(data_String[p].getBytes()).array());
								change++;
							}
						}
						if (change > 0) {
							output += data_codes.get(data_names[o]);
							last_data.put(data_names[o], data_String);
						}		
					}
				} //else {
//					System.out.println(data_names[o]);
//					byte[] temp = (byte[])compression_data.get(i).get(data_names[o]);
//					for (int p = 0; p < temp.length; p++) {
//						output += String.format("%8s", Integer.toBinaryString(temp[p] & 0xFF).replace(' ', '0'));	
//					}
//				}
				// needs some work
			}
		}
		time_end = (int) System.currentTimeMillis();
		System.out.println("encode : " + (time_end - time_start));
		time_start = (int) System.currentTimeMillis();
		
		byte[] data = null;
		if (output.length() % 8 != 0) {
		  	System.out.println("error code #7"); //nog te bepalen error code
	    } else {
		    data = new byte[output.length() / 8];
		    for (int i = 0; i < output.length(); i++) {
		        char c = output.charAt(i);
		        if (c == '1') {
		            data[i >> 3] |= 0x80 >> (i & 0x7);
		        } else if (c != '0') {
		        	System.out.println(output);
		            throw new IllegalArgumentException("Invalid char in binary string : " + c + " : should be 1 or 0");
		        }
		    }
		}
		try (FileOutputStream write = new FileOutputStream("src/saves/temp/" + Packet + ".dec", true)) {
			write.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(data.length);
		//System.out.println(compression_data.size());
		time_end = (int) System.currentTimeMillis();
		System.out.println("to file : " + (time_end - time_start));
	}
	static private String binary_num(byte o) {
		String temp = "";
		for (int i = 0; i < 8 - String.format("%8s", Integer.toBinaryString(o & 0xFF)).replace(' ', '0').length(); o++) {
			temp += "0";
		}
		temp += String.format("%8s", Integer.toBinaryString(o & 0xFF)).replace(' ', '0');
		return temp;
	}
	
	static private String binary_data(byte[] data) {
		String temp = "";
		for (int i = 0; i < data.length; i++) {
			for (int o = 0; o < 8 - String.format("%8s", Integer.toBinaryString(data[i] & 0xFF)).replace(' ', '0').length(); o++) {
				temp += "0";
			}
			temp += String.format("%8s", Integer.toBinaryString(data[i] & 0xFF)).replace(' ', '0');
		}
		return temp;
	}
}