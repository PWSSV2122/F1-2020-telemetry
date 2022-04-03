package packet_struct.Session;

public class Session_Marshal {

	private float zoneStart;
	private byte zoneFLag;
	
	public Session_Marshal(float zoneStart, byte zoneFlag) {
		this.zoneStart = zoneStart;
		this.zoneFLag = zoneFlag;
	}
	
	public float getZoneStart() {
		return zoneStart;
	}
	
	public byte getZoneFLag() {
		return zoneFLag;
	}
}
