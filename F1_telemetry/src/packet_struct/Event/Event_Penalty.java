package packet_struct.Event;

public class Event_Penalty {

	private byte penaltyType;
	private byte infringementType;
	private byte vehicleIdx;
	private byte ontherVehicleIdx;
	private byte time;
	private byte lapNum;
	private byte placesGained;
	
	public Event_Penalty(byte penaltyType, byte infringementType, byte vehicleIdx, byte ontherVehicleIdx, byte time, byte lapNum, byte placesGained) {
		this.penaltyType = penaltyType;
		this.infringementType = infringementType;
		this.vehicleIdx = vehicleIdx;
		this.ontherVehicleIdx = ontherVehicleIdx;
		this.time = time;
		this.lapNum = lapNum;
		this.placesGained = placesGained;
	}
	
	public byte getPenaltyType() {
		return penaltyType;
	}
	
	public byte getInfringementType() {
		return infringementType;
	}
	
	public byte getVehicleIdx() {
		return vehicleIdx;
	}
	
	public byte getOntherVehicleIdx() {
		return ontherVehicleIdx;
	}
	
	public byte getTime() {
		return time;
	}
	
	public byte getLapNum() {
		return lapNum;
	}
	
	public byte getPlacesGained() {
		return placesGained;
	}
}
