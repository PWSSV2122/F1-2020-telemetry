package Saves;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import application.Main;

public class toSave {
	//A = last received packet
	//B = newest packet
	public static void ToFile(Object A, Object B, int PacketIDA, int PacketIDB, String car, String[] names, String PacketName) {
		if (PacketIDA >= PacketIDB) { return; }
		LinkedList<byte[]> NewData = new LinkedList<byte[]>();

		if (PacketIDA + 1 != PacketIDB) {
			//zet de lost packets leeg in de file system
			try {
				FileOutputStream write = new FileOutputStream(Main.dir + "Saves/temp/" + car + ".dec", true);
				for (int i = 1; i < (PacketIDA - PacketIDB); i++) {
					write.write((byte) 1);
					int ID = PacketIDA + i;
					write.write(ByteBuffer.allocate(4).putInt(ID).array());
				}
				write.close();
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message
			}	
		}

		for (int i = 0; i < names.length; i++) {
			try {
				Class<?> classRef = Class.forName("packet_struct." + PacketName);
				if (classRef.getMethod(names[i]).invoke(A) != classRef.getMethod(names[i]).invoke(B)) {
					Object Data = classRef.getMethod(names[i]).invoke(B);
					NewData.add(new byte[] {(byte)(i + 2)});
					if (Data.getClass().equals(Byte.class)) {
						NewData.add(new byte[] {(byte)Data});
					}
					if (Data.getClass().equals(Float.class)) {
						NewData.add(ByteBuffer.allocate(4).putFloat((float)Data).array());
					}
					if (Data.getClass().equals(Short.class)) {
						NewData.add(ByteBuffer.allocate(4).putShort((short)Data).array());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream write = new FileOutputStream(Main.dir + "Saves/temp/" + car + ".dec", true);
			write.write((byte) 1);
			int ID = PacketIDB;
			write.write(ByteBuffer.allocate(4).putInt(ID).array());
			for (int i = 0; i < NewData.size(); i++) {
				write.write(NewData.pollFirst());
			}
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
			//custom error message
		}
	}
}