package File_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Write_encoded {
	static int send_rate = 60;
	static String[] file = new String[] {"src/Names/Motion_Packet.enc", "Names/Session_Packet", "Names/Lap_Data_Packet.enc", "Names/Event_Packet.enc", "Names/Participants_Packet.enc",
			"Names/Car_Setup_Packet.enc", "Names/Car_Telemetry.enc", "Names/Car_Status_Packet.enc", "Names/Final_Classification_Packet.enc", "Names/Lobby_Info_Packet.enc"};
	
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
						byte_array[o] = Integer.parseInt(split2[o]);
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
	
	private static void Write(String file, byte[] Write_content) {
		FileOutputStream output;
		try {
			output = new FileOutputStream(file, true);
			try {
				output.write(Write_content);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static int Motion = 0;
	public static void Motion(int Motion_int, byte[] e) {
		Names(file[0]);
		byte[] Output_Motion = new byte[1890 * (Motion_int - Motion)];
		int counter = 0;
		for (int i = 0; i < (Motion_int - Motion); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
					Output_Motion[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Motion[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
			for (int o = 0; o < 30; o++) {
				Output_Motion[counter] = Names_encode[o + 17];
				counter++;
				for (int p = 0; p < byte_codes[o + 17].length; p++) {
					Output_Motion[counter] = e[byte_codes[o + 17][p]];
					counter++;
				}
			}
		}
		Motion = Motion_int;
		Write("src/test/test.data", Output_Motion);
		System.out.println("write");
	}
	//testen + num car loop dynamisch maken + id en participants boven aan packet
	
	static int Session = 0;
	public static void Session(int Session_int, byte[] e) {
		Names(file[1]);
		byte[] Output_Session = null;
		int counter = 0;
		for (int i = 0; i < (Session_int - Session); i++) {
			int num = 0;
			for (int o = 0; o < 16; o++) {
				Output_Session[counter] = Names_encode[o];
				counter++;
				for (int p = 0; p < byte_codes[o].length; p++) {
					Output_Session[counter] = e[byte_codes[o][p]];
					counter++;
				}
			}
			for (int o = 0; o < e[byte_codes[15][0]]; o++) {
				num = 5 * o;
				for (int p = o; p < 2; p++) {
					Output_Session[counter] = Names_encode[16 + p];
					counter++;
					for (int l = 0; l < byte_codes[o + 16].length; l++) {
						Output_Session[counter] = e[num + byte_codes[o + 16][l]];
						counter++;
					}
				}				
			}
			Output_Session[counter] = Names_encode[18];
			counter++;
			Output_Session[counter] = e[num + byte_codes[18][0]];
			counter++;
			
			Output_Session[counter] = Names_encode[19];
			counter++;
			Output_Session[counter] = e[num + byte_codes[19][0]];
			counter++;
			
			Output_Session[counter] = Names_encode[20];
			counter++;
			Output_Session[counter] = e[num + byte_codes[20][0]];
			counter++;
			
			for (int o = 0; o < e[num + byte_codes[20][0]]; o++) {
				for (int p = 0; p < 5; p++) {
					Output_Session[counter] = Names_encode[21 + o];
					counter++;
					for (int l = 0; l < byte_codes[21 + o].length; l++) {
						Output_Session[counter] = e[num + byte_codes[21 + o][l]];
						counter++;
					}
				}
				num = num + 5;
			}
		}
		Session = Session_int;
	}
	//testen + interlacing toevoegen + id boven aan packet + groote output byte[]
	
	static int Lap_Data = 0;
	public static void Lap_Data(int Lap_Data_int, byte[] e) {
		Names(file[2]);
		byte[] Output_Lap_Data = null;
		int counter = 0;
		for (int i =0; i < (Lap_Data_int - Lap_Data); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 27; p++) {
					Output_Lap_Data[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Lap_Data[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Lap_Data = Lap_Data_int;
	}
	//testen + interlacing + num car loop dynamisch maken + id en participants boven aan packet + groote output byte[]
	
	static int Event = 0;
	public static void Event(int Event_int, byte[] e) {
		Names(file[3]);
		byte[] Output_Event = null;
		int counter = 0;
		for (int i = 0; i < (Event_int - Event); i++) {
			String Event_code = new String(new byte[] {e[byte_codes[0][0]], e[byte_codes[0][1]], e[byte_codes[0][2]], e[byte_codes[0][3]]});
			Output_Event[counter] = Names_encode[0];
			counter++;
			for (int o = 0; o < byte_codes[0].length; o++) {
				Output_Event[counter] = e[byte_codes[0][o]];
			}
			if (Event_code == "SSTA") {
				
			} else if (Event_code == "SEND") {
				
			} else if (Event_code == "FTLP") {
				for (int o = 0; o < 2; o++) {
					Output_Event[counter] = Names_encode[1 + o];
					counter++;
					for (int p = 0; p < byte_codes[1 + o].length; p++) {
						Output_Event[counter] = e[byte_codes[1 + o][p]];
						counter++;
					}
				}
			} else if (Event_code == "RTMT") {
				Output_Event[counter] = Names_encode[3];
				counter++;
				
				Output_Event[counter] = e[byte_codes[3][0]];
				counter++;
			} else if (Event_code == "DRSE") {
				
			} else if (Event_code == "DRSD") {
				
			} else if (Event_code == "TMPT") {
				Output_Event[counter] = Names_encode[4];
				counter++;
				
				Output_Event[counter] = e[byte_codes[4][0]];
				counter++;
			} else if (Event_code == "CHQF") {
				
			} else if (Event_code == "RCWN") {
				Output_Event[counter] = Names_encode[5];
				counter++;
				
				Output_Event[counter] = e[byte_codes[6][0]];
				counter++;
			} else if (Event_code == "PENA") {
				for (int o = 0; o < 7; o++) {
					Output_Event[counter] = Names_encode[6 + o];
					counter++;
					for (int p = 0; p < byte_codes[6 + o].length; p++) {
						Output_Event[counter] = e[byte_codes[6 + o][p]];
						counter++;
					}
				}
			} else if (Event_code == "SPTP") {
				for (int o = 0; o < 2; o++) {
					Output_Event[counter] = Names_encode[13 + o];
					counter++;
					for (int p = 0; p < byte_codes[13 + o].length; p++) {
						Output_Event[counter] = e[byte_codes[13 + o][p]];
						counter++;
					}
				}
			}
		}
	}
	//testen + id en participants boven aan packet + groote output byte[]

	static int Participants = 0;
	public static void Participants(int Participants_int, byte[] e) {
		Names(file[4]);
		byte[] Output_Participants = null;
		int counter = 0;
		for (int i = 0; i < (Participants_int - Participants); i++) {
			int num = 0;
			for (int o = 0; o < e[byte_codes[0][0]]; o++) {
				num = 54 * o;
				for (int p = 0; p < 7; p++) {
					Output_Participants[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Participants[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Participants = Participants_int;
	}
	//testen + interlacing + id en participants boven aan packet + groote output byte[]
	
	static int Car_Setups = 0;
	public static void Car_Setups(int Car_Setups_int, byte[] e) {
		Names(file[5]);
		byte[] Output_Car_Setups = null;
		int counter = 0;
		for (int i = 0; i < (Car_Setups_int - Car_Setups); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 46 * o;
				for (int p = 0; p < 22; p++) {
					Output_Car_Setups[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Car_Setups[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Car_Setups = Car_Setups_int;
	}
	//testen + interlacing + num car loop dynamisch maken + id en participants boven aan packet + groote output byte[]

	static int Car_Telemetry = 0;
	public static void Car_Telemetry(int Car_Telemetry_int, byte[] e) {
		Names(file[6]);
		byte[] Output_Car_Telemetry = null;
		int counter = 0;
		for (int i = 0; i < (Car_Telemetry_int - Car_Telemetry); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 58 * o;
				for (int p = 0; p < 30; p++) {
					Output_Car_Telemetry[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Car_Telemetry[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
			for (int o = 0; o < 4; o++) {
				Output_Car_Telemetry[counter] = Names_encode[30 + o];
				counter++;
				for (int p = 0; p < byte_codes[30 + o].length; p++) {
					Output_Car_Telemetry[counter] = e[byte_codes[30 + o][p]];
					counter++;
				}
			}
		}
		Car_Telemetry = Car_Telemetry_int;
	}
	//testen + interlacing + num car loop dynamisch maken + id en participants boven aan packet + groote output byte[]

	static int Car_Status = 0;
	public static void Car_Status(int Car_Status_int, byte[] e) {
		Names(file[7]);
		byte[] Output_Car_Status = null;
		int counter = 0;
		for (int i = 0; i < (Car_Status_int - Car_Status); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
					Output_Car_Status[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Car_Status[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Car_Status = Car_Status_int;
	}

	static int Final_Classification = 0;
	public static void Final_Classification(int Final_Classification_int, byte[] e) {
		Names(file[8]);
		byte[] Output_Final_Classsification = null;
		int counter = 0;
		for (int i = 0; i < (Final_Classification_int - Final_Classification); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
					Output_Final_Classsification[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Final_Classsification[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Final_Classification = Final_Classification_int;
	}

	static int Lobby_Info = 0;
	public static void Lobby_Info(int Lobby_Info_int, byte[] e) {
		Names(file[9]);
		byte[] Output_Lobby_Info = null;
		int counter = 0;
		for (int i = 0; i < (Lobby_Info_int - Lobby_Info); i++) {
			int num = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
					Output_Lobby_Info[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Lobby_Info[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Lobby_Info = Lobby_Info_int;
	}

	public static void file_write(byte[] output) {
		
	}
}
//todo
//id aan alle .enc file toevoegen
//bytes naar files write
//aanroepen van de methoden