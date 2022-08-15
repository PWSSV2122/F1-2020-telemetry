package Inkoming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import Global_vars.Settings;
import Global_vars.Thread_Queue;
import Global_vars.structClassNames;
import Global_vars.Delta.CreateDeltaClass;
import Saves.toSave;
import Saves.json.jsonTimeStamp;
import errors.Error;
import packet_verwerking.Packet_locations.Header;
import packet_verwerking.Car_setups_decode;
import packet_verwerking.Car_status_decode;
import packet_verwerking.Car_telemetry_decode;
import packet_verwerking.Event_decode;
import packet_verwerking.Final_classification_decode;
import packet_verwerking.Lap_data_decode;
import packet_verwerking.Lobby_info_decode;
import packet_verwerking.Motion_decode;
import packet_verwerking.Participants_decode;
import packet_verwerking.Session_decode;

public class Inkoming {
	
	private static int Packet_loss[] = new int[10];
	private static packet_struct.Header Header_Data;
	private static boolean first[] = new boolean[] {true, true, true, true, true, true, true, true, true, true};
	private static int PlayerCarIndex = 0;
	public static boolean SaveData = true;
	
	public static void recieve() {
		try {
			DatagramSocket socket = new DatagramSocket(Settings.poort);
			while (Settings.recieve == true) {
				byte[] packet_temp = new byte[1465]; //max package length 
				DatagramPacket response = new DatagramPacket(packet_temp, packet_temp.length);
				socket.receive(response);
				
				byte[] packet = new byte[response.getLength()];
				for (int i = 0; i < response.getLength(); i++) {
					packet[i] = packet_temp[i];
				}
				
//			    for (byte b : packet) {
//		            String st = String.format("%02X ", b);
//		            System.out.print(st);
//		        }
//		        System.out.println("\n");
					
					Header_Data  = new packet_struct.Header(
							ByteBuffer.wrap(new byte[] {packet[Header.m_packetFormat[0]], packet[Header.m_packetFormat[1]]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
							packet[Header.m_gameMajorVersion[0]],
							packet[Header.m_gameMinorVersion[0]],
							packet[Header.m_packetVersion[0]],
							packet[Header.m_packetId[0]],
							ByteBuffer.wrap(new byte[] {packet[Header.m_sessionUID[0]], packet[Header.m_sessionUID[1]], packet[Header.m_sessionUID[2]], packet[Header.m_sessionUID[3]], packet[Header.m_sessionUID[4]], packet[Header.m_sessionUID[5]], packet[Header.m_sessionUID[6]], packet[Header.m_sessionUID[7]]}).order(ByteOrder.LITTLE_ENDIAN).getLong(),
							ByteBuffer.wrap(new byte[] {packet[Header.m_sessionTime[0]], packet[Header.m_sessionTime[1]], packet[Header.m_sessionTime[2]], packet[Header.m_sessionTime[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
							ByteBuffer.wrap(new byte[] {packet[Header.m_frameIdentifier[0]], packet[Header.m_frameIdentifier[1]], packet[Header.m_frameIdentifier[2]], packet[Header.m_frameIdentifier[3]]}).order(ByteOrder.LITTLE_ENDIAN).getInt(),
							packet[Header.m_playerCarIndex[0]],
							packet[Header.m_secondaryPlayerCarIndex[0]]);
					
					if (first[Header_Data.getPacketId()]) {
						Packet_loss[Header_Data.getPacketId()] = Header_Data.getFrameIdentifier() - 1;
						first[Header_Data.getPacketId()] = false;
						Data.PlayerCarIndex = Header_Data.getPlayerCarIndex();
					}
					decode(packet);
			}	
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			//error message
		}
	}
	
	private static void decode(byte[] packet) {
		if (Header_Data.getPacketId() == 0) { //Motion
			Thread_Queue.Motion.submit(new Runnable() {
				public void run() {
					Motion_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[0].put((int)Header_Data.getSessionTime(), 0);
						
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Motion_car_comp[i], Data.Motion_car[i], "Motion/" + i, structClassNames.Motion_car, "Motion.Motion_car");
						}
						toSave.ToFile(Data.Motion_comp, Data.Motion, "Motion/Motion", structClassNames.Motion, "Motion.Motion");
						
						Data.Motion_comp = Data.Motion;
						Data.Motion_car_comp = Data.Motion_car;
						//integrity check
					}
				}
			});	
		} else if (Header_Data.getPacketId() == 1) { //Session
			Thread_Queue.Session.submit(new Runnable() {
				public void run() {
					Session_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[1].put((int)Header_Data.getSessionTime(), 1);
						
						toSave.ToFile(Data.Session_comp, Data.Session, "Session/Session", structClassNames.Session, "Session.Session");
						for(int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Session_marshal_comp[i], Data.Session_marshal[i], "Session/Session", structClassNames.Session_Marshal, "Session.Session_Marshal");
						}
						for(int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Session_weather_comp[i], Data.Session_weather[i], "Session/Session", structClassNames.Session_Weather, "Session.Session_Weather");
						}
						
						Data.Session_comp = Data.Session;
						Data.Session_marshal_comp = Data.Session_marshal;
						Data.Session_weather_comp = Data.Session_weather;
						//integrity check
					}
				}
			});
		} else if (Header_Data.getPacketId() == 2) { //Lap Data
			Thread_Queue.Lap_data.submit(new Runnable() {
				public void run() {
					Lap_data_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[2].put((int)Header_Data.getSessionTime(), 2);
						
						for(int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Lap_data_comp[i], Data.Lap_data[i], "Lap_data/" + i, structClassNames.Lap_data, "Lap_data");
						}
						
						PlayerCarIndex = Header_Data.getPlayerCarIndex();
						if (Data.Lap_data[PlayerCarIndex].getCurrentLapNum() != Data.Lap_data_comp[PlayerCarIndex].getCurrentLapNum()) {
							if (Data.Lap_data[PlayerCarIndex].getCurrentLapNum() != 1) {
								Saves.json.jsonLap.LapTimeStamp(Header_Data.getSessionTime(), Data.Lap_data[PlayerCarIndex].getCurrentLapNum());
							}
						}
						
						Data.Lap_data_comp = Data.Lap_data;
						//integrity check
					} else if (CreateDeltaClass.CustomDelta) {
						CreateDeltaClass.AddDataPoint();
					}
				}
			});
		} else if (Header_Data.getPacketId() == 3) { //Event Needs testing
			Thread_Queue.Event.submit(new Runnable() {
				public void run() {
					Event_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[3].put((int)Header_Data.getSessionTime(), 3);
						
						toSave.ToFile(Data.Event_comp, Data.Event, "Event/Event", structClassNames.Event, "Event.Event");
						if (Data.Event.getEventStringCode().equals("FTLP")) {
							toSave.ToFile(Data.Event_fastestLap_comp, Data.Event_fastestLap, "Event/Event", structClassNames.Event_FastestLap, "Event.Event_FastestLap");
						} else if (Data.Event.getEventStringCode().equals("RCWN")) {
							toSave.ToFile(Data.Event_raceWinner_comp, Data.Event_raceWinner, "Event/Event", structClassNames.Event_RaceWinner, "Event.Event_RaceWinner");					
						} else if (Data.Event.getEventStringCode().equals("RTMT")) {
							toSave.ToFile(Data.Event_retirement_comp, Data.Event_retirement, "Event/Event", structClassNames.Event_Retirement, "Event.Event_Retirement");
						} else if (Data.Event.getEventStringCode().equals("SPTP")) {
							toSave.ToFile(Data.Event_speedTrap_comp, Data.Event_speedTrap, "Event/Event", structClassNames.Event_SpeedTrap, "Event.Event_SpeedTrap");
						} else if (Data.Event.getEventStringCode().equals("TMPT")) {
							toSave.ToFile(Data.Event_TeamMateInPits_comp, Data.Event_TeamMateInPits, "Event/Event", structClassNames.Event_TeamMateInPits, "Event.Event_TeamMateInPits");
						} else if (Data.Event.getEventStringCode().equals("PENA")) {
							toSave.ToFile(Data.Event_penalty_comp, Data.Event_penalty, "Event/Event", structClassNames.Event_Penalty, "Event.Event_Penalty");
						}

						Data.Event_comp = Data.Event;
						Data.Event_fastestLap_comp  = Data.Event_fastestLap;
						Data.Event_raceWinner_comp  = Data.Event_raceWinner;
						Data.Event_retirement_comp  = Data.Event_retirement;
						Data.Event_speedTrap_comp  = Data.Event_speedTrap;
						Data.Event_TeamMateInPits_comp  = Data.Event_TeamMateInPits;
						Data.Event_penalty_comp  = Data.Event_penalty;
						//integrity check
					}
				}
			});
		} else if (Header_Data.getPacketId() == 4) { //Participants
			Thread_Queue.Participants.submit(new Runnable() {
				public void run() {
					Participants_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[4].put((int)Header_Data.getSessionTime(), 4);
							
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Participants_players_comp[i], Data.Participants_players[i], "Participants/" + i, structClassNames.Participants_players, "Participants.Participants_players");
						}
						toSave.ToFile(Data.Participants_comp, Data.Participants, "Participants/Participants" , structClassNames.Participants, "Participants.Participants");
						
						Data.Participants_comp = Data.Participants;
						Data.Participants_players_comp = Data.Participants_players;
						//integrity check
					}
				}
			});
		} else if (Header_Data.getPacketId() == 5) { //Car Setups Needs testing
			Thread_Queue.Car_setups.submit(new Runnable() {
				public void run() {
					Car_setups_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[5].put((int)Header_Data.getSessionTime(), 5);
						
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Car_setups_comp[i], Data.Car_setups[i], "Car_setups/" + i, structClassNames.Car_setups, "Car_setups");
						}
						
						Data.Car_setups_comp = Data.Car_setups;
						//integrity check
					}
				}
			});
		} else if (Header_Data.getPacketId() == 6) { //Car Telemetry
			Thread_Queue.Car_telemetry.submit(new Runnable() {
				public void run() {
					Car_telemetry_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[6].put((int)Header_Data.getSessionTime(), 6);
							
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Car_telemetry_car_comp[i], Data.Car_telemetry_car[i], "Car_telemetry/" + i, structClassNames.Car_telemetry_car, "Car_telemetry.Car_telemetry_car");
						}
						toSave.ToFile(Data.Car_telemetry_comp, Data.Car_telemetry, "Car_telemetry/Car_telemetry" , structClassNames.Car_telemetry, "Car_telemetry.Car_telemetry");
							
						Data.Car_telemetry_comp = Data.Car_telemetry;
						Data.Car_telemetry_car_comp = Data.Car_telemetry_car;
						//integrity check	
					}
				}
			});
		} else if (Header_Data.getPacketId() == 7) { //Car Status
			Thread_Queue.Car_status.submit(new Runnable() {
				public void run() {
					Car_status_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[7].put((int)Header_Data.getSessionTime(), 7);
							
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Car_status_comp[i], Data.Car_status[i], "Car_status/" + i, structClassNames.Car_status, "Car_status");	
						}

						Data.Car_status_comp = Data.Car_status;
						//integrity check
					}	
				}
			});
		} else if (Header_Data.getPacketId() == 8) { //Final Classification Needs testing
			Thread_Queue.Final_classification.submit(new Runnable() {
				public void run() {
					Final_classification_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[8].put((int)Header_Data.getSessionTime(), 8);
						
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Final_classification_car_comp[i], Data.Final_classification_car_comp[i], "Final_classification/" + i, structClassNames.Lobby_info_car, "Final_classification.Final_classification_car");
						}
						toSave.ToFile(Data.Final_classification_comp, Data.Final_classification_comp, "Final_classification/Final_classification", structClassNames.Final_classification, "Final_classification.Final_classification");	
						
						Data.Final_classification_comp = Data.Final_classification;
						Data.Final_classification_car_comp = Data.Final_classification_car;
						//integrity check
					}
				}
			});
		} else if (Header_Data.getPacketId() == 9) { //Lobby Info Needs testing
			Thread_Queue.Lobby_info.submit(new Runnable() {
				public void run() {
					Lobby_info_decode.decode(packet);
					if (SaveData) {
						jsonTimeStamp.times[9].put((int)Header_Data.getSessionTime(), 9);
						
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Lobby_info_car_comp[i], Data.Lobby_info_car[i], "Lobby_info/" + i, structClassNames.Lobby_info_car, "Lobby_info.Lobby_info_car");
						}
						toSave.ToFile(Data.Lobby_info_comp, Data.Lobby_info, "Lobby_info/Lobby_info", structClassNames.Lobby_info, "Lobby_info.Lobby_info");	
						
						Data.Lobby_info_comp = Data.Lobby_info;
						Data.Lobby_info_car_comp = Data.Lobby_info_car;
						//integrity check
					}
				}
			});
		} else {
			Error.error("PacketID out of range should be less then 9 but it is: " + Header_Data.getPacketId(), 1);
		}
	}
}