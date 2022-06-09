package Inkoming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import Global_vars.PacketIDs;
import Global_vars.Settings;
import Global_vars.Thread_Queue;
import Global_vars.structClassNames;
import Saves.toSave;
import packet_struct.Motion.Motion;
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
	
	public static void recieve() {
		try {
			DatagramSocket socket = new DatagramSocket(Settings.poort);
			while (Settings.recieve == true) {
				byte[] packet_temp = new byte[1465]; //max package length 
				DatagramPacket response = new DatagramPacket(packet_temp, packet_temp.length);
				socket.receive(response);
				
				String quote = new String(packet_temp, 0, response.getLength());
				byte[] packet = quote.getBytes();
				
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
					
					for (int i = 0; i < 22; i++) {
						toSave.ToFile(Data.Motion_car_comp[i], Data.Motion_car[i], PacketIDs.MotionCarA, Header_Data.getFrameIdentifier(), "Motion/" + i, structClassNames.Motion_car, "Motion.Motion_car");
					}
					toSave.ToFile(Data.Motion_comp, Data.Motion, PacketIDs.MotionA, Header_Data.getFrameIdentifier(), "Motion/Motion", structClassNames.Motion, "Motion.Motion");
					
					PacketIDs.MotionA = Header_Data.getFrameIdentifier();
					PacketIDs.MotionCarA = Header_Data.getFrameIdentifier();
					Data.Motion_comp = Data.Motion;
					Data.Motion_car_comp = Data.Motion_car;
					//integrity check
				}
			});	
//				else if (Header_Data.getPacketId() == 1) { //Session
//				Thread_Queue.Session.submit(new Runnable() {
//					public void run() {
//						Session_decode.decode(packet);
//						
//						Save_to_file.save("Session.Session", Data.SessionID, false, new Object[] {Data.Session}, new Object[] {Data.Session_comp});
//						Save_to_file.save("Session.Session", Data.SessionID, true, new Object[] {Data.Session_marshal}, new Object[] {Data.Session_marshal_comp});
//						Save_to_file.save("Session.Session", Data.SessionID, true, new Object[] {Data.Session_weather}, new Object[] {Data.Session_weather_comp});
//						Data.SessionID++;
//						
//						Data.Session_comp = Data.Session;
//						Data.Session_marshal_comp = Data.Session_marshal;
//						Data.Session_weather_comp = Data.Session_weather;
//						//integrity check
//					}
//				});
			} else if (Header_Data.getPacketId() == 2) { //Lap Data
				Thread_Queue.Lap_data.submit(new Runnable() {
					public void run() {
						Lap_data_decode.decode(packet);
						
						for(int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Lap_data_comp[i], Data.Lap_data[i], PacketIDs.LapDataA, Header_Data.getFrameIdentifier(), "Lap_data/" + i, structClassNames.Lap_data, "Lap_data");
						}
						
						PacketIDs.LapDataA = Header_Data.getFrameIdentifier();
						Data.Lap_data_comp = Data.Lap_data;
						//integrity check
					}
				});
			} 
			//else if (Header_Data.getPacketId() == 3) { //Event Needs testing
//				Thread_Queue.Event.submit(new Runnable() {
//					public void run() {
//						Event_decode.decode(packet);
//						
//						//to save
//						
//						Data.Event_comp = Data.Event;
//						Data.Event_fastestLap_comp  = Data.Event_fastestLap;
//						Data.Event_raceWinner_comp  = Data.Event_raceWinner;
//						Data.Event_retirement_comp  = Data.Event_retirement;
//						Data.Event_speedTrap_comp  = Data.Event_speedTrap;
//						Data.Event_TeamMateInPits_comp  = Data.Event_TeamMateInPits;
//						Data.Event_penalty_comp  = Data.Event_penalty;
//						//integrity check
//					}
//				});
//			} else if (Header_Data.getPacketId() == 4) { //Participants
//				Thread_Queue.Participants.submit(new Runnable() {
//					public void run() {
//						Participants_decode.decode(packet);
//						
//						//to save
//						
//						Data.Participants_comp = Data.Participants;
//						Data.Participants_players_comp = Data.Participants_players;
//						//integrity check
//					}
//				});
//			} else if (Header_Data.getPacketId() == 5) { //Car Setups Needs testing
//				Thread_Queue.Car_setups.submit(new Runnable() {
//					public void run() {
//						Car_setups_decode.decode(packet);
//						
//						//to save
//						
//						Data.Car_setups_comp = Data.Car_setups;
//						//integrity check
//					}
//				});
			 else if (Header_Data.getPacketId() == 6) { //Car Telemetry
				Thread_Queue.Car_telemetry.submit(new Runnable() {
					public void run() {
						Car_telemetry_decode.decode(packet);
						
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Car_telemetry_car_comp[i], Data.Car_telemetry_car[i], PacketIDs.CarTelemetryCarA, Header_Data.getFrameIdentifier(), "Car_telemetry/" + i, structClassNames.Car_telemetry_car, "Car_telemetry.Car_telemetry_car");
						}
						toSave.ToFile(Data.Car_telemetry_comp, Data.Car_telemetry, PacketIDs.CarTelemetryA, Header_Data.getFrameIdentifier(), "Car_telemetry/Car_telemetry" , structClassNames.Car_telemetry, "Car_telemetry.Car_telemetry");
						
						PacketIDs.CarTelemetryA = Header_Data.getFrameIdentifier();
						PacketIDs.CarTelemetryCarA = Header_Data.getFrameIdentifier();
						Data.Car_telemetry_comp = Data.Car_telemetry;
						Data.Car_telemetry_car_comp = Data.Car_telemetry_car;
						//integrity check
					}
				});
			} else if (Header_Data.getPacketId() == 7) { //Car Status
				Thread_Queue.Car_status.submit(new Runnable() {
					public void run() {
						Car_status_decode.decode(packet);
						
						for (int i = 0; i < 22; i++) {
							toSave.ToFile(Data.Car_status_comp[i], Data.Car_status[i], PacketIDs.CarStatusA, Header_Data.getFrameIdentifier(), "Car_status/" + i, structClassNames.Car_status, "Car_status");	
						}

						PacketIDs.CarStatusA = Header_Data.getFrameIdentifier();
						Data.Car_status_comp = Data.Car_status;
						//integrity check
					}
				});
			} 
//				else if (Header_Data.getPacketId() == 8) { //Final Classification Needs testing
//				Thread_Queue.Final_classification.submit(new Runnable() {
//					public void run() {
//						Final_classification_decode.decode(packet);
//						
//						//to save
//						
//						Data.Final_classification_comp = Data.Final_classification;
//						Data.Final_classification_car_comp = Data.Final_classification_car;
//						//integrity check
//					}
//				});
//			} else if (Header_Data.getPacketId() == 9) { //Lobby Info Needs testing
//				Thread_Queue.Lobby_info.submit(new Runnable() {
//					public void run() {
//						Lobby_info_decode.decode(packet);
//						
//						//to save
//						
//						Data.Lobby_info_comp = Data.Lobby_info;
//						Data.Lobby_info_car_comp = Data.Lobby_info_car;
//						//integrity check
//					}
//				});
//			}
//		} else {
//			//custom error message
//		}
	}
}