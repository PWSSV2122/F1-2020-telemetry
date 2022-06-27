package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Event_packet;

public class Event_decode {
	
	private static packet_struct.Event.Event Event_data;
	private static packet_struct.Event.Event_FastestLap Event_FastestLap;
	private static packet_struct.Event.Event_RaceWinner Event_RaceWinner;
	private static packet_struct.Event.Event_Retirement Event_Retirement;
	private static packet_struct.Event.Event_SpeedTrap Event_SpeedTrap;
	private static packet_struct.Event.Event_TeamMateInPits Event_TeamMateInPits;
	private static packet_struct.Event.Event_Penalty Event_Penalty;
	
	public static void decode(byte[] packet) {
		Event_data = new packet_struct.Event.Event(
				new String(new byte[] {packet[Event_packet.m_eventStringCode[0]], packet[Event_packet.m_eventStringCode[1]], packet[Event_packet.m_eventStringCode[2]], packet[Event_packet.m_eventStringCode[3]],}));
		
		if (Event_data.getEventStringCode() == "FTLP") {
			Event_FastestLap = new packet_struct.Event.Event_FastestLap(
				packet[Event_packet.m_vehicleIdx_FTLP[0]],
				ByteBuffer.wrap(new byte[] {packet[Event_packet.m_lapTime[0]], packet[Event_packet.m_lapTime[1]], packet[Event_packet.m_lapTime[2]], packet[Event_packet.m_lapTime[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
		} else if (Event_data.getEventStringCode() == "RCWN") {
			Event_RaceWinner = new packet_struct.Event.Event_RaceWinner(
				packet[Event_packet.m_vehicleIdx_RCWN[0]]);
		} else if (Event_data.getEventStringCode() == "RTMT") {
			Event_Retirement = new packet_struct.Event.Event_Retirement(
				packet[Event_packet.m_vehicleIdx_RTMT[0]]);
		} else if (Event_data.getEventStringCode() == "SPTP") {
			Event_SpeedTrap = new packet_struct.Event.Event_SpeedTrap(
				packet[Event_packet.m_vehicleIdx_SPTP[0]],
				ByteBuffer.wrap(new byte[] {packet[Event_packet.m_speed[0]], packet[Event_packet.m_speed[1]], packet[Event_packet.m_speed[2]], packet[Event_packet.m_speed[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
		} else if (Event_data.getEventStringCode() == "TMPT") {
			Event_TeamMateInPits = new packet_struct.Event.Event_TeamMateInPits(
				packet[Event_packet.m_vehicleIdx_TMPT[0]]);
		} else if (Event_data.getEventStringCode() == "PENA") {
			Event_Penalty = new packet_struct.Event.Event_Penalty(
				packet[Event_packet.m_penaltyType[0]],
				packet[Event_packet.m_infringementType[0]],
				packet[Event_packet.m_vehicleIdx_PENA[0]],
				packet[Event_packet.m_otherVehicleIdx[0]],
				packet[Event_packet.m_time[0]],
				packet[Event_packet.m_lapNum[0]],
				packet[Event_packet.m_placesGained[0]]);
		}
		
		Data.Event = Event_data;
		Data.Event_fastestLap = Event_FastestLap;
		Data.Event_raceWinner = Event_RaceWinner;
		Data.Event_retirement = Event_Retirement;
		Data.Event_speedTrap = Event_SpeedTrap;
		Data.Event_TeamMateInPits = Event_TeamMateInPits;
		Data.Event_penalty = Event_Penalty;
	}
}
