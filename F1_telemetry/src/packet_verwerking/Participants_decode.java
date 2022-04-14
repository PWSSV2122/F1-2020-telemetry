package packet_verwerking;

import java.nio.charset.StandardCharsets;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Participants;

public class Participants_decode {

	private static packet_struct.Participants.Participants Participants_data;
	private static packet_struct.Participants.Participants_players[] Participants_players = new packet_struct.Participants.Participants_players[22];
	
	public static void decode(byte[] packet) {
		Participants_data = new packet_struct.Participants.Participants(
			packet[Participants.m_numActiveCars[0]]);
		
		for (int i = 0; i < 22; i++) {
			int offset = Participants.Participants * i;
			byte[] Name = new byte[48];
			for (int o = 0; o < 48; o++) {
				Name[i] = packet[Participants.m_name_[i] + offset];
			}
			Participants_players[i] = new packet_struct.Participants.Participants_players(
				packet[Participants.m_aiControlled_[0] + offset],
				packet[Participants.m_driverId_[0] + offset],
				packet[Participants.m_teamId_[0] + offset],
				packet[Participants.m_raceNumber_[0] + offset],
				packet[Participants.m_nationality_[0] + offset],
				new String(Name, StandardCharsets.UTF_8),						
				packet[Participants.m_yourTelemetry_[0] + offset]);
		}
		
		Data.Participants = Participants_data;
		Data.Participants_players = Participants_players;
	}
}
