package packet_struct.Lobby_info;

public class Lobby_info {

	private byte numPlayers;
	
	public Lobby_info(byte numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public byte getNumPlayers() {
		return numPlayers;
	}
}
