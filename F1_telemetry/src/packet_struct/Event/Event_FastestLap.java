package packet_struct.Event;

public class Event_FastestLap {

	private byte vehicleIdx;
	private float lapTime;
	
	public Event_FastestLap(byte vehicleIdx, float lapTime) {
		this.vehicleIdx = vehicleIdx;
		this.lapTime = lapTime;
	}
	
	public byte getVehicleIdx() {
		return vehicleIdx;
	}
	
	public float getLapTime() {
		return lapTime;
	}
}
