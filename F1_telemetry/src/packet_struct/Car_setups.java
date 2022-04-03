package packet_struct;

public class Car_setups {

	private byte frontWing;
	private byte rearWing;
	private byte onThrottle;
	private byte offThrottle;
	private float frontCamber;
	private float rearCamber;
	private float frontToe;
	private float rearToe;
	private byte frontSuspnesionHeight;
	private byte rearSuspensionHeight;
	private byte brakePressure;
	private byte brakeBias;
	private float rearLeftTyrePressure;
	private float rearRightTyrePressue;
	private float frontLeftTyrePressure;
	private float frontRightTyrePressure;
	private byte ballast;
	private float fuelLoad;
	
	public Car_setups(byte frontWing, byte rearWing, byte onThrottle, byte offThrottle, float frontCamber, float rearCamber, float frontToe, float rearToe, byte frontSuspnesionHeight,
			byte rearSuspensionHeight, byte brakePressure, byte brakeBias, float rearLeftTyrePressure, float rearRightTyrePressue, float frontLeftTyrePressure, float frontRightTyrePressure, 
			byte ballast, float fuelLoad) {
		
		this.frontWing = frontWing;
		this.rearWing = rearWing;
		this.onThrottle = onThrottle;
		this.offThrottle = offThrottle;
		this.frontCamber = frontCamber;
		this.rearCamber = rearCamber;
		this.frontToe = frontToe;
		this.rearToe = rearToe;
		this.frontSuspnesionHeight = frontSuspnesionHeight;
		this.rearSuspensionHeight = rearSuspensionHeight;
		this.brakePressure = brakePressure;
		this.brakeBias = brakeBias;
		this.rearLeftTyrePressure = rearLeftTyrePressure;
		this.rearRightTyrePressue = rearRightTyrePressue;
		this.frontLeftTyrePressure = frontLeftTyrePressure;
		this.frontRightTyrePressure = frontRightTyrePressure;
		this.ballast = ballast;
		this.fuelLoad = fuelLoad;
	}
	
	public byte getFrontWing() {
		return frontWing;
	}
	
	public byte getRearWing() {
		return rearWing;
	}
	
	public byte getOnThrottle() {
		return onThrottle;
	}
	
	public byte getOffThrottle() {
		return offThrottle;
	}
	
	public float getFrontCamber() {
		return frontCamber;
	}
	
	public float getRearCamber() {
		return rearCamber;
	}
	
	public float getFrontToe() {
		return frontToe;
	}
	
	public float getRearToe() {
		return rearToe;
	}
	
	public byte getFrontSuspnesionHeight() {
		return frontSuspnesionHeight;
	}
	
	public byte getRearSuspensionHeight() {
		return rearSuspensionHeight;
	}
	
	public byte getBrakePressure() {
		return brakePressure;
	}
	
	public byte getBrakeBias() {
		return brakeBias;
	}
	
	public float getRearLeftTyrePressure() {
		return rearLeftTyrePressure;
	}
	
	public float getRearRightTyrePressue() {
		return rearRightTyrePressue;
	}
	
	public float getFrontLeftTyrePressure() {
		return frontLeftTyrePressure;
	}
	
	public float getFrontRightTyrePressure() {
		return frontRightTyrePressure;
	}
	
	public byte getBallast() {
		return ballast;
	}
	
	public float getFuelLoad() {
		return fuelLoad;
	}
}
