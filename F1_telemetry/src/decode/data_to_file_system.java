package decode;

import java.util.HashMap;

public class data_to_file_system {
	static public void decoded_data_manager(String Save) {
		Boolean play_save = true;
		int[] PacketId = new int[] {};
		while (play_save == true) {
			
		}
		HashMap <String, HashMap<Integer, HashMap<String, Object>>> decoded_data = decode.data_decoder.decode(Save, new int[7]);
	}
}
