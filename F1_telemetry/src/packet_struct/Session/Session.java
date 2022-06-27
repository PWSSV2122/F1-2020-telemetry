package packet_struct.Session;

public class Session {
	private byte weather;
	private byte trackTemperature;
	private byte airTemperature;
	private byte totalLaps;
	private short trackLength;
	private byte sessionType;
	private byte trackId;
	private byte formula;
	private short sessionTimeLeft;
	private short sessionDuration;
	private byte pitSpeedLimit;
	private byte gamePaused;
	private byte isSpectating;
	private byte spectatorCarIndex;
	private byte sliProNativeSupport;
	private byte numMarshalZones;
	private byte safetyCarStatus;
	private byte networkGame;
	private byte numWeatherForcastSamples;
	
	public Session(byte weather, byte trackTemperature, byte airTemperature, byte totalLaps, short trackLength, byte sessionType, byte trackId, byte formula,
			short sessionTimeLeft, short sessionDuration, byte pitSpeedLimit, byte gamePaused, byte isSpectating, byte spectatorCarIndex, byte sliProNativeSupport,
			byte numMarshalZones, byte safetyCarStatus, byte networkGame, byte numWeatherForcastSamples) {
		
		this.weather = weather;
		this.trackTemperature = trackTemperature;
		this.airTemperature = airTemperature;
		this.totalLaps = totalLaps;
		this.trackLength = trackLength;
		this.sessionType = sessionType;
		this.trackId = trackId;
		this.formula = formula;
		this.sessionTimeLeft = sessionTimeLeft;
		this.sessionDuration = sessionDuration;
		this.pitSpeedLimit = pitSpeedLimit;
		this.gamePaused = gamePaused;
		this.isSpectating = isSpectating;
		this.spectatorCarIndex = spectatorCarIndex;
		this.sliProNativeSupport = sliProNativeSupport;
		this.numMarshalZones = numMarshalZones;
		this.safetyCarStatus = safetyCarStatus;
		this.networkGame = networkGame;
		this.numWeatherForcastSamples = numWeatherForcastSamples;
	}
	
	public byte getWeather() {
		return weather;
	}

	public byte getTrackTemperature() {
		return trackTemperature;
	}
	
	public byte getAirTemperature() {
		return airTemperature;
	}
	
	public byte getTotalLaps() {
		return totalLaps;
	}
	
	public short getTrackLength() {
		return trackLength;
	}
	
	public byte getSessionType() {
		return sessionType;
	}
	
	public byte getTrackId() {
		return trackId;
	}
	
	public byte getFormula() {
		return formula;
	}
	
	public short getSessionTimeLeft() {
		return sessionTimeLeft;
	}
	
	public short getSessionDuration() {
		return sessionDuration;
	}
	
	public byte getPitSpeedLimit() {
		return pitSpeedLimit;
	}
	
	public byte getGamePaused() {
		return gamePaused;
	}
	
	public byte getIsSpectating() {
		return isSpectating;
	}
	
	public byte getSpectatorCarIndex() {
		return spectatorCarIndex;
	}
	
	public byte getSliProNativeSupport() {
		return sliProNativeSupport;
	}
	
	public byte getNumMarshalZones() {
		return numMarshalZones;
	}
	
	public byte getSafetyCarStatus() {
		return safetyCarStatus;
	}
	
	public byte getNetworkGame() {
		return networkGame;
	}
	
	public byte getNumWeatherForcastSamples() {
		return numWeatherForcastSamples;
	}
}
