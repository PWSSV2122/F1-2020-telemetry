package file_editor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class file_viewer extends Application{
	Stage window;
	Scene scene1;
	HashMap<Integer, HashMap<String, Object>> Packet = new HashMap<Integer, HashMap<String, Object>>();
	public void start(Stage primaryStage) {
		read();
		window = primaryStage;
		VBox top_level = new VBox();
		scene1 = new Scene(top_level, 1500, 1000);
		top_level.setPrefHeight(1000);
		try {
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(scene1);
			window.setTitle("F1 Tracker");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		window.setOnCloseRequest(e -> PreferenceSave());
	}
	
	public void PreferenceSave() {
		
	}
	
//	public void Float() {
//		
//	}
//	
//	public void Short() {
//		
//	}
	
	static String[] file = new String[] {"src/Names/Motion_Packet.enc", "src/Names/Session_Packet", "src/Names/Lap_Data_Packet.enc", "src/Names/Event_Packet.enc", "src/Names/Participants_Packet.enc",
			"src/Names/Car_Setup_Packet.enc", "src/Names/Car_Telemetry.enc", "src/Names/Car_Status_Packet.enc", "src/Names/Final_Classification_Packet.enc", "src/Names/Lobby_Info_Packet.enc"};
	
	static String[] Names = new String[70];
	static byte[] Names_encode = new byte[70];
	static int[][] byte_codes = new int[70][];
	static int length;
	public static String[] Names(String File) {
		String Line;
		int i = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(File));
			while ((Line = reader.readLine()) != null) {
				String[] split = Line.split(" : ", 3);
				if (split.length >= 3) {
					length++;
					Names[i] = split[0];
					if (split[1].length() % 8 == 0) {
						for (int o = 0; o < split[1].length(); o++) {
							char c = split[1].charAt(o);
							if (c == '1') {
								Names_encode[i] |= (1 << (7 - o));
							} else if (c != '0') {
								System.out.println(c + "is geen geldig caracter (alleen 0 of 1)");
							}
						}
					} else {
						System.out.println(split[1] + "is niet 8 bits lang");
					}
					String[] split2 = split[2].split(", ", 48);
					int[] byte_array = new int[split2.length];
					for (int o = 0; o < split2.length; o++) {
						byte_array[o] = Integer.parseInt(split2[o] + 5);
					}
					byte_codes[i] = byte_array;
					i++;
				} else {
					System.out.println("Slechte regel: " + Line);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Het bestand " + File + " kon niet gevonden worden");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Names;
	}
	
	private void read() {
		try {
			byte[] input = Files.readAllBytes(Paths.get("src/test/test.data"));
//			String s2 = String.format("%8s", Integer.toBinaryString(input[10] & 0xFF)).replace(' ', '0');
//			System.out.println(s2);
			int input_counter = 29;
			int participant = 0;
			if (input[0] == -128) {
				if (input[10] == 00) {
					int id = (int)((input[1] & 0xFF) << 24) | ((input[2] & 0xFF) << 16) | ((input[3] & 0xFF) << 8) | ((input[4] & 0xFF) << 0);
					Names(file[0]);
					HashMap<Byte, Integer> Motion = new HashMap<Byte, Integer>();
					for (int i = 0; i < length; i++) {
						Motion.put(Names_encode[i], 1 * i);
					}
					HashMap<String, Object> temp_save = new HashMap<String, Object>();
					while (input_counter != input.length) {
						if (Motion.get(input[input_counter]) != null) {
							int num_list = Motion.get(input[input_counter]);
							int data_type = byte_codes[num_list].length;
							input_counter++;
							if (data_type == 1) {
								if (num_list < 18) {
									temp_save.put(Names[num_list] + participant, (byte) input[input_counter]);
								} else {
									temp_save.put(Names[num_list], (byte) input[input_counter]);
								}
								input_counter++;
							} else if (data_type == 2) {
								if (num_list < 18) {
									temp_save.put(Names[num_list] + participant, (short) ((input[input_counter + 1] & 0xFF) << 8) | (input[input_counter] & 0xFF));
								} else {
									temp_save.put(Names[num_list], (short) ((input[input_counter + 1] & 0xFF) << 8) | (input[input_counter] & 0xFF));
								}
								input_counter = input_counter + 2;
							} else if (data_type == 4) {
								if (num_list < 18) {
									temp_save.put(Names[num_list] + participant, ByteBuffer.wrap(new byte[] {input[input_counter], input[input_counter + 1], input[input_counter + 2], input[input_counter + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
								} else {
									temp_save.put(Names[num_list], ByteBuffer.wrap(new byte[] {input[input_counter], input[input_counter + 1], input[input_counter + 2], input[input_counter + 3]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
								}
								input_counter = input_counter + 4;
							} else if (data_type == 8) {
								if (num_list < 18) {
									temp_save.put(Names[num_list] + participant, (byte) input[input_counter]);
								} else {
									temp_save.put(Names[num_list], (byte) input[input_counter]);
								}
								input_counter = input_counter + 8;
							}
							if (num_list == 17) {
								participant++;
							}
						} else {
							if (input[input_counter] == -128) {
								Packet.put(id, temp_save);
								id = (int)((input[input_counter + 1] & 0xFF) << 24) | ((input[input_counter + 2] & 0xFF) << 16) | ((input[input_counter + 3] & 0xFF) << 8) | ((input[input_counter + 4] & 0xFF) << 0);
								input_counter = input_counter + 29;
								participant = 0;
								temp_save.clear();
							} else {
								System.out.println("Ongeldige byte in bestand");
								input_counter = input.length;
							}
						}
					}
//					for (int i = 0; i < 2803; i++) {
//						System.out.println(i + " : " + Packet.get(i));
//					}
				}
			} else {
				System.out.println("geen geldig bestand");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
