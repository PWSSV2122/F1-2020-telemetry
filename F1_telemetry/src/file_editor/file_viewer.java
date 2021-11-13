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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class file_viewer extends Application{
	@FXML
	Stage window;
	@FXML
	Scene scene1;
	HashMap<Integer, HashMap<String, Object>> Packet = new HashMap<Integer, HashMap<String, Object>>();
	@FXML
	ScrollPane root = new ScrollPane();
	@FXML
	Text T = new Text();
	String newLine = System.getProperty("line.separator");
	int packet = 0;
	public void start(Stage primaryStage) {
		read();
		text();
		window = primaryStage;
		String test = text_overig;
    	T.setText(text_overig);
		VBox top_level = new VBox();
		root.setContent(T);
		top_level.getChildren().add(root);
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
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(textUpdate, 0, (1000 / 60), TimeUnit.MILLISECONDS);
	}
	
	Runnable textUpdate = new Runnable() {
	    public void run() {
	    	try {
		    	text_overig = "";
		    	text();
		    	String test = text_overig;
		    	if (text_overig.length() <= 0) {
		    		System.out.println("nooooooo!!!!!");
		    	}
		    	try {
			    	T.setText(text_overig);
		    	} catch (Exception e) {
		    		
		    	}
		    	packet++;
		        System.out.println(packet + "\n" + packet);
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	} 
	    }
	};
	
	public void PreferenceSave() {
		
	}
	
	String text_overig = "";
	public void text() {
		for (int i = 0; i < 21; i ++) {
			text_overig += "Car : " + i + "\n";
			for (int o = 0; o < 18; o++) {
				text_overig += Names[o];
				text_overig += " : ";
				text_overig += String.valueOf(Packet.get(packet + 1).get(Names[o] + i));
				text_overig += newLine;
			}
			text_overig +="\n";
		}
		for(int i = 0; i < 30; i++) {
			text_overig += Names[i + 18];
			text_overig += " : " + String.valueOf(Packet.get(packet + 1).get(Names[i + 18]));
			text_overig += "\n";
		}
		System.out.println(Packet.get(packet + 1).get(Names[2] + 2));
	}
	
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
	int id = 0;
	private void read() {
		try {
			byte[] input = Files.readAllBytes(Paths.get("src/test/test.data"));
//			String s2 = String.format("%8s", Integer.toBinaryString(input[10] & 0xFF)).replace(' ', '0');
//			System.out.println(s2);
			int input_counter = 29;
			int participant = 0;
			if (input[0] == -128) {
				if (input[10] == 00) {
					id = (int)((input[1] & 0xFF) << 24) | ((input[2] & 0xFF) << 16) | ((input[3] & 0xFF) << 8) | ((input[4] & 0xFF) << 0);
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
//								if (Packet.get(id) == null) {
//									Packet.put(id, temp_save);
//								} else {
//									Packet.get(id).putAll(temp_save);
//									System.out.println("wut");
//								}
								//System.out.println(Packet.get(id));
								Packet.put(id, new HashMap<String, Object>(temp_save));
								//Packet.get(id).put("1", 1);
								id = (int)((input[input_counter + 1] & 0xFF) << 24) | ((input[input_counter + 2] & 0xFF) << 16) | ((input[input_counter + 3] & 0xFF) << 8) | ((input[input_counter + 4] & 0xFF) << 0);
								input_counter = input_counter + 29;
								participant = 0;
							} else {
								System.out.println("Ongeldige byte in bestand");
								input_counter = input.length;
							}
						}
					}
//					for (int i = 0; i < id + 10; i++) {
//						System.out.println(i + " : " + Packet.get(i));
//					}
//					System.out.println(Packet.size());
//					System.out.println(Packet.get(0));
//					System.out.println(packet);
				}
			} else {
				System.out.println("geen geldig bestand");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
