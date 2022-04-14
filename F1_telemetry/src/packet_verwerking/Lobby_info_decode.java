package packet_verwerking;

import java.nio.charset.StandardCharsets;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Lobby_info;

public class Lobby_info_decode {

	private static packet_struct.Lobby_info.Lobby_info Lobby_info_data;
	private static packet_struct.Lobby_info.Lobby_info_car[] Lobby_info_car = new packet_struct.Lobby_info.Lobby_info_car[22];
	
	public static void decode(byte[] packet) {
		Lobby_info_data = new packet_struct.Lobby_info.Lobby_info(
			packet[Lobby_info.m_numPlayers[0]]);
		
		for (int i = 0; i < 22; i++) {
			int offset = Lobby_info.Lobby_info * i;
			byte[] Name = new byte[48];
			for (int o = 0; o < 48; o++) {
				Name[i] = packet[Lobby_info.m_name_[i] + offset];
			}
			Lobby_info_car[i] = new packet_struct.Lobby_info.Lobby_info_car(
				packet[Lobby_info.m_aiControlled_[0] + offset],
				packet[Lobby_info.m_teamId_[0] + offset],
				packet[Lobby_info.m_nationality_[0] + offset],
				new String(Name, StandardCharsets.UTF_8),						
				packet[Lobby_info.m_readyStatus_[0] + offset]);
		}
		
		Data.Lobby_info = Lobby_info_data;
		Data.Lobby_info_car = Lobby_info_car;
	}
}
