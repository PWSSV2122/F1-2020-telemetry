package packet_struct.Lobby_info;

public class Lobby_info_car {

	private byte aiControlled;
	private byte teamId;
	private byte nationality;
	private String name;
	
	public Lobby_info_car(byte aiControlled, byte teamId, byte nationality, String name) {
		this.aiControlled = aiControlled;
		this.teamId = teamId;
		this.nationality = nationality;
		this.name = name;
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
}
