package packet_struct;

public class Lap_data {

	private float lastLapTime;
	private float currentLapTime;
	private short sector1TimeInMS;
	private short sector2TimeInMS;
	private float bestLapTime;
	private byte bestLapNum;
	private short bestLapSector1TimeInMS;
	private short bestLapSector2TimeInMS;
	private short bestLapSector3TimeInMS;
	private short bestOverallSector1TimeInMS;
	private byte bestOverallSector1LapNum;
	private short bestOverallSector2TimeInMS;
	private byte bestOverallSector2LapNum;
	private short bestOverallSector3TimeInMS;
	private byte bestOverallSector3LapNum;
	private float lapDistance;
	private float totalDistance;
	private float safetyCarDelta;
	private byte carPosition;
	private byte currentLapNum;
	private byte pitStatus;
	private byte sector;
	private byte currentLapInvalid;
	private byte penalties;
	private byte gridPosition;
	private byte driverStatus;
	private byte resultStatus;
	
	public Lap_data(float lastLapTime, float currentLapTime, short sector1TimeInMS, short sector2TimeInMS, float bestLapTime, byte bestLapNum, short bestLapSector1TimeInMS, short bestLapSector2TimeInMS,
			short bestLapSector3TimeInMS, short bestOverallSector1TimeInMS, byte bestOverallSector1LapNum, short bestOverallSector2TimeInMS, byte bestOverallSector2LapNum, short bestOverallSector3TimeInMS, byte bestOverallSector3LapNum,
			float lapDistance, float totalDistance, float safetyCarDelta, byte carPosition, byte currentLapNum, byte pitStatus, byte sector, byte currentLapInvalid, byte penalties, byte gridPosition, 
			byte driverStatus, byte resultStatus) {
	
		this.lastLapTime = lastLapTime;
		this.currentLapTime = currentLapTime;
		this.sector1TimeInMS = sector1TimeInMS;
		this.sector2TimeInMS = sector2TimeInMS;
		this.bestLapTime = bestLapTime;
		this.bestLapNum = bestLapNum;
		this.bestLapSector1TimeInMS = bestLapSector1TimeInMS;
		this.bestLapSector2TimeInMS = bestLapSector2TimeInMS;
		this.bestLapSector3TimeInMS = bestLapSector3TimeInMS;
		this.bestOverallSector1TimeInMS = bestOverallSector1TimeInMS;
		this.bestOverallSector1LapNum = bestOverallSector1LapNum;
		this.bestOverallSector2TimeInMS = bestOverallSector2TimeInMS;
		this.bestOverallSector2LapNum = bestOverallSector2LapNum;
		this.bestOverallSector3TimeInMS = bestOverallSector3TimeInMS;
		this.bestOverallSector3LapNum = bestOverallSector3LapNum;
		this.lapDistance = lapDistance;
		this.totalDistance = totalDistance;
		this.safetyCarDelta = safetyCarDelta;
		this.carPosition = carPosition;
		this.currentLapNum = currentLapNum;
		this.pitStatus = pitStatus;
		this.sector = sector;
		this.currentLapInvalid = currentLapInvalid;
		this.penalties = penalties;
		this.gridPosition = gridPosition;
		this.driverStatus = driverStatus;
		this.resultStatus = resultStatus;
	}

	public float getLastLapTime() {
		return lastLapTime;
	}
	
	public float getCurrentLapTime() {
		return currentLapTime;
	}
	
	public short getSector1TimeInMS() {
		return sector1TimeInMS;
	}
	
	public short getSector2TimeInMS() {
		return sector2TimeInMS;
	}
	
	public float getBestLapTime() {
		return bestLapTime;
	}
	
	public byte getBestLapNum() {
		return bestLapNum;
	}
	
	public short getBestLapSector1TimeInMS() {
		return bestLapSector1TimeInMS;
	}
	
	public short getBestLapSector2TimeInMS() {
		return bestLapSector2TimeInMS;
	}
	
	public short getBestLapSector3TimeInMS() {
		return bestLapSector3TimeInMS;
	}
	
	public short getBestOverallSector1TimeInMS() {
		return bestOverallSector1TimeInMS;
	}
	
	public byte getBestOverallSector1LapNum() {
		return bestOverallSector1LapNum;
	}
	
	public short getBestOverallSector2TimeInMS() {
		return bestOverallSector2TimeInMS;
	}
	
	public byte getBestOverallSector2LapNum() {
		return bestOverallSector2LapNum;
	}
	
	public short getBestOverallSector3TimeInMS() {
		return bestOverallSector3TimeInMS;
	}
	
	public byte getBestOverallSector3LapNum() {
		return bestOverallSector3LapNum;
	}
	
	public float getLapDistance() {
		return lapDistance;
	}
	
	public float getTotalDistance() {
		return totalDistance;
	}
	
	public float getSafetyCarDelta() {
		return safetyCarDelta;
	}
	
	public byte getCarPosition() {
		return carPosition;
	}
	
	public byte getCurrentLapNum() {
		return currentLapNum;
	}
	
	public byte getPitStatus() {
		return pitStatus;
	}
	
	public byte getSector() {
		return sector;
	}
	
	public byte getCurrentLapInvalid() {
		return currentLapInvalid;
	}
	
	public byte getPenalties() {
		return penalties;
	}
	
	public byte getGridPosition() {
		return gridPosition;
	}
	
	public byte getDriverStatus() {
		return driverStatus;
	}
	
	public byte getResultStatus() {
		return resultStatus;
	}
}
