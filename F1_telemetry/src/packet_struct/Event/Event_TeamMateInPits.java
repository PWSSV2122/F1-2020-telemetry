package packet_struct.Event;

public class Event_TeamMateInPits {
	
	private byte vehicleIdx;
	
	public Event_TeamMateInPits(byte vehicleIdx) {
		this.vehicleIdx = vehicleIdx;
	}
	
	public byte getVehicleIdx() {
		return vehicleIdx;
	}
}
