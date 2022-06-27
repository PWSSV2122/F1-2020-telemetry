package packet_struct.Session;

public class Session_Weather {

	private byte sessionType;
	private byte timeOffset;
	private byte weather;
	private byte trackTemerature;
	private byte airTemperature;
	
	public Session_Weather(byte sessionType, byte timeOffset, byte weather, byte trackTemerature, byte airTemperature) {
		this.sessionType = sessionType;
		this.timeOffset = timeOffset;
		this.weather = weather;
		this.trackTemerature = trackTemerature;
		this.airTemperature = airTemperature;
	}
	
	public byte getSessionType() {
		return sessionType;
	}
	
	public byte getTimeOffset() {
		return timeOffset;
	}
	
	public byte getWeather() {
		return weather;
	}
	
	public byte getTrackTemerature() {
		return trackTemerature;
	}
	
	public byte getAirTemperature() {
		return airTemperature;
	}
}
