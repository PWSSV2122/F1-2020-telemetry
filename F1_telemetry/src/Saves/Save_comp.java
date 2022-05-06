package Saves;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Save_comp {
	
	public static ArrayList<Byte>[] comp(int Class, int repeat, Object[] One, Object[] Two) {
		//get Class number from Save_system_startup.classes
		//numbers of times the comp must repeat
			ArrayList<Byte>[] comp_result = null; // new LinkedList<Byte>();
			
			for (int i = 0; i < Save_system_startup.Methods[Class].length; i++) {
				for (int o = 0; o < repeat; o++) {
					try {
						Object value_1 = Save_system_startup.Methods[Class][i].invoke(One[o]);
						Object value_2 = Save_system_startup.Methods[Class][i].invoke(Two[o]);
						if (value_1 != value_2) {
							comp_result[o].add((byte)i);
							if (value_2.getClass().equals(Byte.class)) {
								comp_result[o].add((byte)value_2);
							} else if (value_2.getClass().equals(Short.class)) {
								for (int p = 0; p < 2; p++) {
									 comp_result[o].add(ByteBuffer.allocate(2).putShort((short)value_2).array()[p]);
								}
							} else if (value_2.getClass().equals(Float.class)) {
								for (int p = 0; p < 4; p++) {
									 comp_result[o].add(ByteBuffer.allocate(4).putFloat((float)value_2).array()[p]);
								}
							} else {
								//custom error message
							}
						}
					} catch (Exception e) {
						//custom error message
						e.printStackTrace();
					}
				}
			}
		return comp_result;
	}
}
