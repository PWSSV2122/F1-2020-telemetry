package file_system;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import File_reader.Names;

public class data_compressie {

	public static void encode(String Packet) {
		Names.data_decode();
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

		HashMap<Integer, HashMap<String, Object>> compression_data = new HashMap<Integer, HashMap<String, Object>>();
		try {
			compression_data.putAll((HashMap<Integer, HashMap<String, Object>>) L2.class.getField(Packet + "_packet").get(Packet + "_packet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HashMap<String, Object> last_data = new HashMap<String, Object>();
		String output = "";
		for (int i = 0; i < compression_data.size(); i++) {
			output += data_codes.get("packetid");
			output += binary_data(ByteBuffer.allocate(4).putInt(i).array());
			for (int o = 0; o < data_codes.size(); o++) {
				output += data_codes.get(data_names[o]);
				if (data_names[o].equals("packetid") || data_names[o].equals("sessionTime")) {
					output = output.substring(0, output.length() - 8);
				} else if (data_names[o].endsWith("_")) {
					Object data = compression_data.get(i).get(data_names[o] + 1);
					if (data.getClass().equals(Byte.class)) {
						System.out.println("byte");
						byte[] data_byte = new byte[22];
						for (int p = 0; p < 22; p++) {
							data_byte[p] = (byte) compression_data.get(i).get(data_names[o] + p);
						}
						byte[] data_byte_compaire = new byte[22];
						try {
							data_byte_compaire = (byte[]) last_data.get(data_names[o]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						int change = 0;
						for (int p = 0; p < 22; p++) {
							if (data_byte[p] == data_byte_compaire[p]) {
								System.out.println(":(" + " : " + p);
							} else {
								output += binary_num((byte) p);
								output += binary_data(new byte[] {data_byte[p]});
								change++;
							}
						}
						if(change == 0) {
							System.out.println(data_names[o] + " no change");
							output = output.substring(0, output.length() - 8);
						} else if(change > 0) {
							last_data.put(data_names[o], data_byte);
						}
					} else if (data.getClass().equals(Float.class)) {
						System.out.println("float");
						float[] data_float = (float[])data;
						float[] data_float_compaire = new float[22];
						try {
							data_float_compaire = (float[]) last_data.get(data_names[o]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						int change = 0;
						for (int p = 0; p < 22; p++) {
							if (data_float[p] == data_float_compaire[p]) {
								System.out.println(":(" + " : " + p);
							} else {
								output += binary_num((byte) p);
								output += binary_data(ByteBuffer.allocate(4).putFloat(data_float[p]).array());
								change++;
							}
						}
						if (change == 0) {
							System.out.println(data_names[o] + " no change");
							output = output.substring(0, output.length() - 8);
						} else if (change > 0) {
							last_data.put(data_names[o], data_float);
						}
					} else if (data.getClass().equals(Short.class)) {
						System.out.println("short");
						short[] data_short = (short[])data;
						short[] data_short_compaire = new short[22];
						int change = 0;
						try {
							data_short_compaire = (short[]) last_data.get(data_names[o]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						for (int p = 0; p < 22; p++) {
							if (data_short[p] == data_short_compaire[p]) {
								System.out.println(":(" + " : " + p);
							} else {
								output += binary_num((byte) p);
								output += binary_data(ByteBuffer.allocate(2).putShort(data_short[p]).array());
								change++;
							}
						}
						if (change == 0) {
							System.out.println(data_names[o] + " no change");
							output = output.substring(0, output.length() - 8);
						} else if (change > 0) {
							last_data.put(data_names[o], data_short);
						}	
					} else if (data.getClass().equals(String.class)) {
						System.out.println("String");
						String[] data_String = (String[])data;
						String[] data_String_compaire = new String[22];
						int change = 0;
						try {
							data_String_compaire = (String[]) last_data.get(data_names[o]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						for (int p = 0; p < 22; p++) {
							if (data_String[p] == data_String_compaire[p]) {
								System.out.println(":(" + " : " + p);
							} else {
								output += binary_num((byte) p);
								output += binary_data(ByteBuffer.allocate(10).put(data_String[p].getBytes()).array());
								change++;
							}
						}
						if (change == 0) {
							System.out.println(data_names[o] + " no change");
							output = output.substring(0, output.length() - 8);
						} else if (change > 0) {
							last_data.put(data_names[o], data_String);
						}		
					}
				} else {
					System.out.println(data_names[o]);
					byte[] temp = (byte[])compression_data.get(i).get(data_names[o]);
					for (int p = 0; p < temp.length; p++) {
						output += String.format("%8s", Integer.toBinaryString(temp[p] & 0xFF).replace(' ', '0'));	
					}
				}
			}
		}
		
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
		try (FileOutputStream write = new FileOutputStream("src/saves/temp/" + Packet + ".dec")) {
			write.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
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