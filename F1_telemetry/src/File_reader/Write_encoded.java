package File_reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Write_encoded {
	static int send_rate = 60;
	static String[] file = new String[] {"Names/Motion_Packet.enc", "Names/Session_Packet", "Names/Lap_Data_Packet.enc", "Names/Event_Packet.enc", "Names/Participants_Packet.enc",
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
						byte test;
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
	
	static int Motion = 0;
	public static void Motion(int Motion_int, byte[] e) {
		Names(file[0]);
		byte[] Output_Motion = null;
		for (int i = 0; i < (Motion_int - Motion); i++) {
			int num = 0;
			int counter = 0;
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
		}
		Motion = Motion_int;
	}
	
	static int Session = 0;
	public static void Session(int Session_int, byte[] e) {
		Names(file[1]);
		byte[] Output_Session = null;
		for (int i = 0; i < (Session_int - Session); i++) {
			int num = 0;
			int counter = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
					Output_Session[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Session[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Session = Session_int;
	}
	
	static int Lap_Data = 0;
	public static void Lap_Data(int Lap_Data_int, byte[] e) {
		Names(file[2]);
		byte[] Output_Lap_Data = null;
		for (int i =0; i < (Lap_Data_int - Lap_Data); i++) {
			int num = 0;
			int counter = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
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
	
	static int Event = 0;
	public static void Event(int Event_int, byte[] e) {
		Names(file[3]);
		byte[] Output_Event = null;
		for (int i = 0; i < (Event_int - Event); i++) {
			
		}
	}

	static int Participants = 0;
	public static void Participants(int Participants_int, byte[] e) {
		Names(file[4]);
		byte[] Output_Participants = null;
		for (int i = 0; i < (Participants_int - Participants); i++) {
			int num = 0;
			int counter = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
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
	
	static int Car_Setups = 0;
	public static void Car_Setups(int Car_Setups_int, byte[] e) {
		Names(file[5]);
		byte[] Output_Car_Setups = null;
		for (int i = 0; i < (Car_Setups_int - Car_Setups); i++) {
			int num = 0;
			int counter = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
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

	static int Car_Telemetry = 0;
	public static void Car_Telemetry(int Car_Telemetry_int, byte[] e) {
		Names(file[6]);
		byte[] Output_Car_Telemetry = null;
		for (int i = 0; i < (Car_Telemetry_int - Car_Telemetry); i++) {
			int num = 0;
			int counter = 0;
			for (int o = 0; o < 22; o++) {
				num = 18 * o;
				for (int p = 0; p < 18; p++) {
					Output_Car_Telemetry[counter] = Names_encode[p];
					counter++;
					for (int l = 0; l < byte_codes[p].length; l++) {
						Output_Car_Telemetry[counter] = e[byte_codes[p][l] + num];
						counter++;
					}
				}
			}
		}
		Car_Telemetry = Car_Telemetry_int;
	}

	static int Car_Status = 0;
	public static void Car_Status(int Car_Status_int, byte[] e) {
		Names(file[7]);
		byte[] Output_Car_Status = null;
		for (int i = 0; i < (Car_Status_int - Car_Status); i++) {
			int num = 0;
			int counter = 0;
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
		for (int i = 0; i < (Final_Classification_int - Final_Classification); i++) {
			int num = 0;
			int counter = 0;
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
		for (int i = 0; i < (Lobby_Info_int - Lobby_Info); i++) {
			int num = 0;
			int counter = 0;
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
//alle woorden moeten nog worden geconvert met .byteValue() en de "\n" is voor nu alleen maar dat het goed te zien is in een .txt bestand

//todo
//id aan alle .enc file toevoegen
//alle bit coodes in .enc files naar 8 zetten
//testen van file of de string eruit komen zoals verwacht
//.byteValue() achter alle data zetten
//"\n" weg halen
//string converten naar bytes
//bytes naar files write
//aanroepen van de methoden