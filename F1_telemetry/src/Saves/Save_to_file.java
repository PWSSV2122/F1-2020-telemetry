package Saves;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import application.Main;

public class Save_to_file {

	public static void save(String save, int PacketNummer, boolean special, Object[] data, Object[] data_comp) {
		int save_id = Arrays.asList(Save_system_startup.classes).indexOf(save);
		ArrayList<Byte>[] comp_result = Save_comp.comp(save_id, data.length, data, data_comp);
		
		byte[][] comp_result_byte = new byte[comp_result.length][];
		for (int i = 0; i < comp_result.length; i++) {
			comp_result_byte[i] = new byte[comp_result[i].size()];
			for (int o = 0; o < comp_result[i].size(); i++) {
				comp_result_byte[i][o] = comp_result[i].get(o);
			}
		}
		
		if (data.length == 1) {
			String[] folder = save.split(".");
			try (FileOutputStream write = new FileOutputStream(Main.dir + "Saves/temp/" + folder[0] + "/" + folder[1] + ".dec", true)) {
				write.write((byte) 0);
				write.write(ByteBuffer.allocate(4).putInt(PacketNummer).array());
				write.write(comp_result_byte[0]);
			} catch (Exception e) {
				//custom error message
			}	
		} else if (data.length > 1) {
			if (special) {
				String[] folder = save.split(".");
				try (FileOutputStream write = new FileOutputStream(Main.dir + "Saves/temp/" + folder[0] + "/" + folder[1] + ".dec", true)) {
					for (int i = 0; i < data.length; i++) {
						write.write(comp_result_byte[i]);
						write.close();
					}
				} catch (Exception e) {
					//custom error message
				}
			} else {
				try {
					for (int i = 0; i < data.length; i++) {
						FileOutputStream write = new FileOutputStream(Main.dir + "Saves/temp/" + save + "/" + i + ".dec", true);
						write.write((byte) 0);
						write.write(ByteBuffer.allocate(4).putInt(PacketNummer).array());
						write.write(comp_result_byte[i]);
						write.close();
					}
				} catch (Exception e) {
					//custom error message
				}
			}
		} else {
			//custom error message
		}
	}
}