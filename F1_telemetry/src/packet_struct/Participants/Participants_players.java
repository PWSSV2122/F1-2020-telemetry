package packet_struct.Participants;

public class Participants_players {

	private byte aiControlled;
	private byte driverId;
	private byte teamId;
	private byte raceNumber;
	private byte nationality;
	private String name;
	private byte yourTelemetry;
	
	public Participants_players (byte aiControlled, byte driverId, byte teamId, byte raceNumber, byte nationality, String name, byte yourTelemetry) {
		this.aiControlled = aiControlled;
		this.driverId = driverId;
		this.teamId = teamId;
		this.raceNumber = raceNumber;
		this.nationality = nationality;
		this.name = name;
		this.yourTelemetry = yourTelemetry;
	}
	
	public byte getAiControlled() {
		return aiControlled;
	}
	
	public byte getDriverId() {
		return driverId;
	}
	
	public byte getTeamId() {
		return teamId;
	}
	
	public byte getRaceNumber() {
		return raceNumber;
	}
	
	public byte getNationality() {
		return nationality;
	}
	
	public String getName() {
		return name;
	}
	
	public byte getYourTelemetry() {
		return yourTelemetry;
	}
}
