package packet_struct.Car_telemetry;

public class Car_telemetry_car {

	private short speed;
	private float throttle;
	private float steer;
	private float brake;
	private byte clutch;
	private byte gear;
	private short engineRPM;
	private byte drs;
	private byte revLigthsPercent;
	private short brakesTempratureRL;
	private short brakesTempratureRR;
	private short brakesTempratureFL;
	private short brakesTempratureFR;
	private byte tyresSurfaceTemperatureRL;
	private byte tyresSurfaceTemperatureRR;
	private byte tyresSurfaceTemperatureFL;
	private byte tyresSurfaceTemperatureFR;
	private byte tyresInnerTemperatureRL;
	private byte tyresInnerTemperatureRR;
	private byte tyresInnerTemperatureFL;
	private byte tyresInnerTemperatureFR;
	private short engineTemperature;
	private float tyresPressureRL;
	private float tyresPressureRR;
	private float tyresPressureFL;
	private float tyresPressureFR;
	private byte surfaceTypeRL;
	private byte surfaceTypeRR;
	private byte surfaceTypeFL;
	private byte surfaceTypeFR;
	
	public Car_telemetry_car(short speed, float throttle, float steer, float brake, byte clutch, byte gear, short engineRPM, byte drs, byte revLigthsPercent,
			short brakesTempratureRL, short brakesTempratureRR, short brakesTempratureFL, short brakesTempratureFR, byte tyresSurfaceTemperatureRL,
			byte tyresSurfaceTemperatureRR, byte tyresSurfaceTemperatureFL, byte tyresSurfaceTemperatureFR, byte tyresInnerTemperatureRL, byte tyresInnerTemperatureRR, 
			byte tyresInnerTemperatureFL, byte tyresInnerTemperatureFR, short engineTemperature, float tyresPressureRL, float tyresPressureRR, float tyresPressureFL,
			float tyresPressureFR, byte surfaceTypeRL, byte surfaceTypeRR, byte surfaceTypeFL, byte surfaceTypeFR) {
	
		this.speed = speed;
		this.throttle = throttle;
		this.steer = steer;
		this.brake = brake;
		this.clutch = clutch;
		this.gear = gear;
		this.engineRPM = engineRPM;
		this.drs = drs;
		this.revLigthsPercent = revLigthsPercent;
		this.brakesTempratureRL = brakesTempratureRL;
		this.brakesTempratureRR = brakesTempratureRR;
		this.brakesTempratureFL = brakesTempratureFL;
		this.brakesTempratureFR = brakesTempratureFR;
		this.tyresSurfaceTemperatureRL = tyresSurfaceTemperatureRL;
		this.tyresSurfaceTemperatureRR = tyresSurfaceTemperatureRR;
		this.tyresSurfaceTemperatureFL = tyresSurfaceTemperatureFL;
		this.tyresSurfaceTemperatureFR = tyresSurfaceTemperatureFR;
		this.tyresInnerTemperatureRL = tyresInnerTemperatureRL;
		this.tyresInnerTemperatureRR = tyresInnerTemperatureRR;
		this.tyresInnerTemperatureFL = tyresInnerTemperatureFL;
		this.tyresInnerTemperatureFR = tyresInnerTemperatureFR;
		this.engineTemperature = engineTemperature;
		this.tyresPressureRL = tyresPressureRL;
		this.tyresPressureRR = tyresPressureRR;
		this.tyresPressureFL = tyresPressureFL;
		this.tyresPressureFR = tyresPressureFR;
		this.surfaceTypeRL = surfaceTypeRL;
		this.surfaceTypeRR = surfaceTypeRR;
		this.surfaceTypeFL = surfaceTypeFL;
		this.surfaceTypeFR = surfaceTypeFR;
	}
	
	public short getSpeed() {
		return speed;
	}
	
	public float getThrottle() {
		return throttle;
	}
	
	public float getSteer() {
		return steer;
	}
	
	public float getBrake() {
		return brake;
	}
	
	public byte getClutch() {
		return clutch;
	}
	
	public byte getGear() {
		return gear;
	}
	
	public float getEngineRPM() {
		return engineRPM;
	}
	
	public byte getDrs() {
		return drs;
	}
	
	public byte getRevLigthsPercent() {
		return revLigthsPercent;
	}
	
	public short getBrakesTempratureRL() {
		return brakesTempratureRL;
	}
	
	public short getBrakesTempratureRR() {
		return brakesTempratureRR;
	}
	
	public short getBrakesTempratureFL() {
		return brakesTempratureFL;
	}
	
	public short getBrakesTempratureFR() {
		return brakesTempratureFR;
	}
	
	public byte getTyresSurfaceTemperatureRL() {
		return tyresSurfaceTemperatureRL;
	}
	
	public byte getTyresSurfaceTemperatureRR() {
		return tyresSurfaceTemperatureRR;
	}
	
	public byte getTyresSurfaceTemperatureFL() {
		return tyresSurfaceTemperatureFL;
	}
	
	public byte getTyresSurfaceTemperatureFR() {
		return tyresSurfaceTemperatureFR;
	}
	
	public byte getTyresInnerTemperatureRL() {
		return tyresInnerTemperatureRL;
	}
	
	public byte getTyresInnerTemperatureRR() {
		return tyresInnerTemperatureRR;
	}
	
	public byte getTyresInnerTemperatureFL() {
		return tyresInnerTemperatureFL;
	}
	
	public byte getTyresInnerTemperatureFR() {
		return tyresInnerTemperatureFR;
	}
	
	public short getEngineTemperature() {
		return engineTemperature;
	}
	
	public float getTyresPressureRL() {
		return tyresPressureRL;
	}
	
	public float getTyresPressureRR() {
		return tyresPressureRR;
	}
	
	public float getTyresPressureFL() {
		return tyresPressureFL;
	}
	
	public float getTyresPressureFR() {
		return tyresPressureFR;
	}
	
	public byte getSurfaceTypeRL() {
		return surfaceTypeRL;
	}
	
	public byte getSurfaceTypeRR() {
		return surfaceTypeRR;
	}
	
	public byte getSurfaceTypeFL() {
		return surfaceTypeFL;
	}
	
	public byte getSurfaceTypeFR() {
		return surfaceTypeFR;
	}
}