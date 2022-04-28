package Inkoming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import Global_vars.Settings;
import Global_vars.Thread_Queue;
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
	
	private static int Packet_loss = 0;
	private static packet_struct.Header Header_Data;
	private static int count = 0;
	
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
				
				//package los detection
				//moet voor iedere packet appart
				if (Header_Data.getFrameIdentifier() <= Packet_loss) {
					//custom error message
//					System.out.println("wut");
//					System.out.println("Package lossed");
//					System.out.println("Package ID : " + Header_Data.getPacketId());
//					System.out.println("Packages lossed : " + (Header_Data.getFrameIdentifier() - Packet_loss - 1) + " : " + Packet_loss + " : " + Header_Data.getFrameIdentifier());
				} else if (Header_Data.getFrameIdentifier() - 1 == Packet_loss) {
					if (Header_Data.getPacketId() == 0) {
						count++;
						System.out.println(count);
					}
					decode(packet);
					Packet_loss++;
				} else {
					//custom error message
					//System.out.println("Package lossed");
					//System.out.println("Package ID : " + Header_Data.getPacketId());
					//System.out.println("Packages lossed : " + (Header_Data.getFrameIdentifier() - Packet_loss - 1) + " : " + Packet_loss + " : " + Header_Data.getFrameIdentifier());
					Packet_loss = Header_Data.getFrameIdentifier();
					decode(packet);
				}	
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
					//packet loss detection en integrity check
				}
			});	
		} else if (Header_Data.getPacketId() == 1) { //Session
			Thread_Queue.Session.submit(new Runnable() {
				public void run() {
					Session_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 2) { //Lap Data
			Thread_Queue.Lap_data.submit(new Runnable() {
				public void run() {
					Lap_data_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 3) { //Event Needs testing
			Thread_Queue.Event.submit(new Runnable() {
				public void run() {
					Event_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 4) { //Participants
			Thread_Queue.Participants.submit(new Runnable() {
				public void run() {
					Participants_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 5) { //Car Setups Needs testing
			Thread_Queue.Car_setups.submit(new Runnable() {
				public void run() {
					Car_setups_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 6) { //Car Telemetry
			Thread_Queue.Car_telemetry.submit(new Runnable() {
				public void run() {
					Car_telemetry_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 7) { //Car Status
			Thread_Queue.Car_status.submit(new Runnable() {
				public void run() {
					Car_status_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 8) { //Final Classification Needs testing
			Thread_Queue.Final_classification.submit(new Runnable() {
				public void run() {
					Final_classification_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		} else if (Header_Data.getPacketId() == 9) { //Lobby Info Needs testing
			Thread_Queue.Lobby_info.submit(new Runnable() {
				public void run() {
					Lobby_info_decode.decode(packet);
					//packet loss detection en integrity check
				}
			});
		}
	}
}