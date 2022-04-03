package packet_struct.Event;

public class Event_RaceWinner {

	private byte vehicleIdx;
	
	public Event_RaceWinner(byte vehicleIdx) {
		this.vehicleIdx = vehicleIdx;
	}
	
	public byte getVehicleIdx() {
		return vehicleIdx;
	}
}
