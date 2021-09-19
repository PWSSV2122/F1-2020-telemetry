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
	public static String[] Names(String File) {
		String Line;
		int i = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(File));
			while ((Line = reader.readLine()) != null) {
				String[] split = Line.split(" : ", 3);
				if (split.length >= 3) {
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
			int input_counter = 0;
			if (input[0] == -128) {
				while (input_counter != input.length) {
					HashMap<String, Object> temp_save = new HashMap<String, Object>();
				}
				HashMap<String, Object> temp_save = new HashMap<String, Object>();
				Packet.put((int)(((input[4] & 0xFF) << 24) | ((input[3] & 0xFF) << 16) | ((input[2] & 0xFF) << 8) | ((input[1] & 0xFF) << 0)), temp_save);
				System.out.println(input[10]);
				if (input[10] == 00) {
					Names(file[0]);
					System.out.println("yes");
					for (int i = 0; i < 22; i++) {
						int y = i * 60;
						temp_save.put("m_worldPositionX_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[0][0]], input[byte_codes[0][1]], input[byte_codes[0][2]], input[byte_codes[0][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_worldPositionY_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[1][0]], input[byte_codes[1][1]], input[byte_codes[1][2]], input[byte_codes[1][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_worldPositionZ_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[2][0]], input[byte_codes[2][1]], input[byte_codes[2][2]], input[byte_codes[2][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_worldVelocityX_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[3][0]], input[byte_codes[3][1]], input[byte_codes[3][2]], input[byte_codes[3][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_worldVelocityY_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[4][0]], input[byte_codes[4][1]], input[byte_codes[4][2]], input[byte_codes[4][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_worldVelocityZ_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[5][0]], input[byte_codes[5][1]], input[byte_codes[5][2]], input[byte_codes[5][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_worldForwardDirX_" + i, (short)((input[byte_codes[6][1]] & 0xFF) << 8) | (input[byte_codes[6][0]] & 0xFF));
						temp_save.put("m_worldForwardDirY_" + i, (short)((input[byte_codes[7][1]] & 0xFF) << 8) | (input[byte_codes[7][0]] & 0xFF));
						temp_save.put("m_worldForwardDirZ_" + i, (short)((input[byte_codes[8][1]] & 0xFF) << 8) | (input[byte_codes[8][0]] & 0xFF));
						temp_save.put("m_worldRightDirX_" + i, (short)((input[byte_codes[9][1]] & 0xFF) << 8) | (input[byte_codes[9][0]] & 0xFF));
						temp_save.put("m_worldRightDirY_" + i, (short)((input[byte_codes[10][1]] & 0xFF) << 8) | (input[byte_codes[10][0]] & 0xFF));
						temp_save.put("m_worldRightDirZ_" + i, (short)((input[byte_codes[11][1]] & 0xFF) << 8) | (input[byte_codes[11][0]] & 0xFF));
						temp_save.put("m_gForceLateral_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[12][0]], input[byte_codes[12][1]], input[byte_codes[12][2]], input[byte_codes[12][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_gForceLongitudinal_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[13][0]], input[byte_codes[13][1]], input[byte_codes[13][2]], input[byte_codes[13][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_gForceVertical_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[14][0]], input[byte_codes[14][1]], input[byte_codes[14][2]], input[byte_codes[14][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_yaw_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[15][0]], input[byte_codes[15][1]], input[byte_codes[15][2]], input[byte_codes[15][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_pitch_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[16][0]], input[byte_codes[16][1]], input[byte_codes[16][2]], input[byte_codes[16][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						temp_save.put("m_roll_" + i, ByteBuffer.wrap(new byte[] {input[byte_codes[17][0]], input[byte_codes[17][1]], input[byte_codes[17][2]], input[byte_codes[17][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					
					}
					temp_save.put("m_suspensionPosition_RL", ByteBuffer.wrap(new byte[] {input[byte_codes[18][0]], input[byte_codes[18][1]], input[byte_codes[18][2]], input[byte_codes[18][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionPosition_RR", ByteBuffer.wrap(new byte[] {input[byte_codes[19][0]], input[byte_codes[19][1]], input[byte_codes[19][2]], input[byte_codes[19][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionPosition_FL", ByteBuffer.wrap(new byte[] {input[byte_codes[20][0]], input[byte_codes[20][1]], input[byte_codes[20][2]], input[byte_codes[20][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionPosition_FR", ByteBuffer.wrap(new byte[] {input[byte_codes[21][0]], input[byte_codes[21][1]], input[byte_codes[21][2]], input[byte_codes[21][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionVelocity_RL", ByteBuffer.wrap(new byte[] {input[byte_codes[22][0]], input[byte_codes[22][1]], input[byte_codes[22][2]], input[byte_codes[22][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionVelocity_RR", ByteBuffer.wrap(new byte[] {input[byte_codes[23][0]], input[byte_codes[23][1]], input[byte_codes[23][2]], input[byte_codes[23][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionVelocity_FF", ByteBuffer.wrap(new byte[] {input[byte_codes[24][0]], input[byte_codes[24][1]], input[byte_codes[24][2]], input[byte_codes[24][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionVelocity_FR", ByteBuffer.wrap(new byte[] {input[byte_codes[25][0]], input[byte_codes[25][1]], input[byte_codes[25][2]], input[byte_codes[25][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionAcceleration_RL", ByteBuffer.wrap(new byte[] {input[byte_codes[26][0]], input[byte_codes[26][1]], input[byte_codes[26][2]], input[byte_codes[26][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionAcceleration_RR", ByteBuffer.wrap(new byte[] {input[byte_codes[27][0]], input[byte_codes[27][1]], input[byte_codes[27][2]], input[byte_codes[27][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionAcceleration_FL", ByteBuffer.wrap(new byte[] {input[byte_codes[28][0]], input[byte_codes[28][1]], input[byte_codes[28][2]], input[byte_codes[28][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_suspensionAcceleration_FR", ByteBuffer.wrap(new byte[] {input[byte_codes[29][0]], input[byte_codes[29][1]], input[byte_codes[29][2]], input[byte_codes[29][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSpeed_RL", ByteBuffer.wrap(new byte[] {input[byte_codes[30][0]], input[byte_codes[30][1]], input[byte_codes[30][2]], input[byte_codes[30][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSpeed_RR", ByteBuffer.wrap(new byte[] {input[byte_codes[31][0]], input[byte_codes[31][1]], input[byte_codes[31][2]], input[byte_codes[31][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSpeed_FL", ByteBuffer.wrap(new byte[] {input[byte_codes[32][0]], input[byte_codes[32][1]], input[byte_codes[32][2]], input[byte_codes[32][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSpeed_FR", ByteBuffer.wrap(new byte[] {input[byte_codes[33][0]], input[byte_codes[33][1]], input[byte_codes[33][2]], input[byte_codes[33][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSlip_RL", ByteBuffer.wrap(new byte[] {input[byte_codes[34][0]], input[byte_codes[34][1]], input[byte_codes[34][2]], input[byte_codes[34][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSlip_RR", ByteBuffer.wrap(new byte[] {input[byte_codes[35][0]], input[byte_codes[35][1]], input[byte_codes[35][2]], input[byte_codes[35][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSlip_FL", ByteBuffer.wrap(new byte[] {input[byte_codes[36][0]], input[byte_codes[36][1]], input[byte_codes[36][2]], input[byte_codes[36][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_wheelSlip_FR", ByteBuffer.wrap(new byte[] {input[byte_codes[37][0]], input[byte_codes[37][1]], input[byte_codes[37][2]], input[byte_codes[37][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_localVelocityX", ByteBuffer.wrap(new byte[] {input[byte_codes[38][0]], input[byte_codes[38][1]], input[byte_codes[38][2]], input[byte_codes[38][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_localVelocityY", ByteBuffer.wrap(new byte[] {input[byte_codes[39][0]], input[byte_codes[39][1]], input[byte_codes[39][2]], input[byte_codes[39][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_localVelocityZ", ByteBuffer.wrap(new byte[] {input[byte_codes[40][0]], input[byte_codes[40][1]], input[byte_codes[40][2]], input[byte_codes[40][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_angularVelocityX", ByteBuffer.wrap(new byte[] {input[byte_codes[41][0]], input[byte_codes[41][1]], input[byte_codes[41][2]], input[byte_codes[41][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_angularVelocityY", ByteBuffer.wrap(new byte[] {input[byte_codes[42][0]], input[byte_codes[42][1]], input[byte_codes[42][2]], input[byte_codes[42][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_angularVelocityZ", ByteBuffer.wrap(new byte[] {input[byte_codes[43][0]], input[byte_codes[43][1]], input[byte_codes[43][2]], input[byte_codes[43][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_angularAccelerationX", ByteBuffer.wrap(new byte[] {input[byte_codes[44][0]], input[byte_codes[44][1]], input[byte_codes[44][2]], input[byte_codes[44][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_angularAccelerationY", ByteBuffer.wrap(new byte[] {input[byte_codes[45][0]], input[byte_codes[45][1]], input[byte_codes[45][2]], input[byte_codes[45][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_angularAccelerationZ", ByteBuffer.wrap(new byte[] {input[byte_codes[46][0]], input[byte_codes[46][1]], input[byte_codes[46][2]], input[byte_codes[46][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					temp_save.put("m_frontWheelsAngle", ByteBuffer.wrap(new byte[] {input[byte_codes[47][0]], input[byte_codes[47][1]], input[byte_codes[47][2]], input[byte_codes[47][3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
				
				}
				System.out.println(temp_save.get("m_worldPositionX_1"));
			} else {
				System.out.println("geen geldig bestand");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
