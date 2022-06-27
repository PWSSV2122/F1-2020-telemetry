package packet_struct.Lobby_info;

public class Lobby_info_car {

	private byte aiControlled;
	private byte teamId;
	private byte nationality;
	private String name;
	private byte readyStatus;
	
	public Lobby_info_car(byte aiControlled, byte teamId, byte nationality, String name, byte readyStatus) {
		this.aiControlled = aiControlled;
		this.teamId = teamId;
		this.nationality = nationality;
		this.name = name;
		this.readyStatus = readyStatus;
	}
	
	public byte getAiControlled() {
		return aiControlled;
	}
	
	public byte getTeamId() {
		return teamId;
	}
	
	public byte getNationality() {
		return nationality;
	}
	
	public String getName() {
		return name;
	}
	
	public byte getReadyStatus() {
		return readyStatus;
	}
}
