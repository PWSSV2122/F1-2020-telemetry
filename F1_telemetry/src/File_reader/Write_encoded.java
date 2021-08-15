package File_reader;

import java.util.HashMap;

import Data_saves.Packet_store;

public class Write_encoded {
	
	public static void Main(int ids[]) {
		HashMap<String, String> Motion_Packet_encode = new HashMap<String, String>();
		Motion_Packet_encode.put("m_worldPositionX_", "0");
		Motion_Packet_encode.put("", "1");
		Motion_Packet_encode.put("", "2");
		Motion_Packet_encode.put("", "3");
		Motion_Packet_encode.put("", "4");
		Motion_Packet_encode.put("", "5");
		Motion_Packet_encode.put("", "6");
		Motion_Packet_encode.put("", "7");
		Motion_Packet_encode.put("", "8");
		Motion_Packet_encode.put("", "9");
		Motion_Packet_encode.put("", "10");
		Motion_Packet_encode.put("", "11");
		Motion_Packet_encode.put("", "12");
		
		String Motion_Packet = "";
		
//		for (int i = 0; i < ids[0]; i++) {
//			HashMap<String, Object> Motion_Packet_temp = new HashMap<String, Object>();
//			Motion_Packet_temp.putAll(Packet_store.Motion_Packet_store.get(String.valueOf(i)));
//			Motion_Packet += Motion_Packet_encode.get("m_worldPositionX_") + ;
//			Motion_Packet_temp.get("");
//		}
		
		int num=1020;

		String Motion_Packet_Num = "";
		while (num > 0) {
			Motion_Packet_Num = Motion_Packet_encode.get(String.valueOf(num % 10)) + Motion_Packet_Num;
		    num = num / 10;
		}	
	}
	
	//de namen opslaan als een 7 bit (of klijner) en dan de nummer als de bits van hun data type
	private static String Motion_Packet_Num(long num) {
		String Motion_Packet_num = "";
		while (num > 0) {
			Motion_Packet_Num = Motion_Packet_encode.get(String.valueOf(num % 10)) + Motion_Packet_Num;
		    num = num / 10;
		}
		return Motion_Packet_num;
	}
} 
