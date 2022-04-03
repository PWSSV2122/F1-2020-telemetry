package packet_struct.Participants;

public class Participants {
	
	private byte numActivePlayers;
	
	public Participants(byte numActivePlayers) {
		this.numActivePlayers = numActivePlayers;
	}
	
	public byte getNumActivePlayers() {
		return numActivePlayers;
	}
}
