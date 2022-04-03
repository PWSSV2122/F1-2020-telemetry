package packet_struct.Event;

public class Event_SpeedTrap {

	private byte vehicleIdx;
	private float speed;
	
	public Event_SpeedTrap(byte vehicleIdx, float speed) {
		this.vehicleIdx = vehicleIdx;
		this.speed = speed;
	}
	
	public byte getVehicleIdx() {
		return vehicleIdx;
	}
	
	public float getSpeed() {
		return speed;
	}
}
