package packet_struct.Event;

public class Event_Retirement {

	private byte vehicleIdx;
	
	public Event_Retirement(byte vehicleIdx) {
		this.vehicleIdx = vehicleIdx;
	}
	
	public byte getVehicleIdx() {
		return vehicleIdx;
	}
}
