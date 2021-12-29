package file_system;

import java.math.BigInteger;
import java.util.HashMap;

import File_reader.Names;

public class data_compressie {
	public static void main(String[] args) {
		encode("Car_Setup");
	}
	
	public static void encode(String Packet) {
		Names.data_decode();
		String[] data_names = new String[50];
		HashMap<String, Byte> data_codes = new HashMap<String, Byte>();
		for (int i = 0; i < Names.Needed_data_packet.size(); i++) {
			if (Names.Needed_data_packet.get(Names.Needed_data_names[i]).equals(Packet)) {
				data_names[i] = Names.Needed_data_names[i];
				data_codes.put(data_names[i], Names.Needed_data_byte.get(data_names[i]));
//				byte b1 = data_codes.get(data_names[i]);
//				String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
//				System.out.println(data_names[i] + " : " + s1);
			}
		}
		L2.Car_Setup_packet.put(1, null);	//temp
		L2.Car_Setup_packet.put(2, null);	//temp
		L2.Car_Setup_packet.put(3, null);	//temp
		
		HashMap<Integer, HashMap<String, Object>> compression_data = new HashMap<Integer, HashMap<String, Object>>();
			try {
				compression_data.putAll((HashMap<Integer, HashMap<String, Object>>) L2.class.getField(Packet + "_packet").get(Packet + "_packet"));
			} catch (Exception e) {
				e.printStackTrace();
			}
//		byte[] packet = new byte[0];
//		packet = new byte[] {00000000};
//		packet = new byte[] {packet, 00000001};
//		packet[0] = 0000000;
//		System.out.println(packet[0]);
		
		String b = "0110100001101001";
		byte[] bval = new BigInteger(b, 2).toByteArray();
		for(int i = 0 ; i < bval.length; i++) {
			System.out.println(bval[i]);
		}
//		int test = 1;
//		Object test2 = test;
//		if (test2 instanceof int[]) {
//			int[] test3 = (int[])test2;
//			for(int i = 0; i < 6; i++) {
//				System.out.println(test[i] + " : " + test3[i]);
//			}
//		}
//		byte[], float[], short[], string[]
		
//		final Class<int[]> intArrayType = int[].class;
//		final Object someObject = new int[]{1,2,3};
//		final int[] instance = convertInstanceOfObject(someObject, intArrayType);
//		
//		
//		int[] test = new int[] {100, 111};
//		byte[] temp = Integer.toBinaryString(test);
//		String output = Integer.toBinaryString(test);
//		output += String.format("%8s", Integer.toBinaryString(data_codes.get(data_names[o]) & 0xFF).replace(' ', '0'));
//
		HashMap<String, Object> last_data = new HashMap<String, Object>();
		String output = "";
		for (int i = 0; i < compression_data.size(); i++) {
			for (int o = 0; o < data_codes.size(); i++) {
				output += String.format("%8s", Integer.toBinaryString(data_codes.get(data_names[o]) & 0xFF).replace(' ', '0'));
				if (data_names[o].endsWith("_")) {
					Object data = compression_data.get(i).get(data_names[o]);
					byte[] data_byte = new byte[22];
					float[] data_float = new float[22];
					short[] data_short = new short[22];
					String[] data_String = new String[22];
					if (data instanceof byte[]) {
						data_byte = (byte[])data;
						for (int p = 0; p < 22; p++) {
							if (last_data.get(data_names[o] + o) == data_byte[o]) {
								
							} else 
							output += String.format("%8s", Integer.toBinaryString(o & 0xFF).replace(' ', '0'));
							output += String.format("%8s", Integer.toBinaryString(data_byte[p] & 0xFF).replace(' ', '0'));	
						}
					} else if (data instanceof float[]) {
						data_float = (float[])data;
					} else if (data instanceof short[]) {
						data_short = (short[])data;
					} else if (data instanceof String[]) {
						data_String = (String[])data;
					}
				} else {
					byte[] temp = (byte[])compression_data.get(i).get(data_names[o]);
					for (int p = 0; p < temp.length; p++) {
						output += String.format("%8s", Integer.toBinaryString(temp[p] & 0xFF).replace(' ', '0'));	
					}
				}
			}
		}
	}	
}
