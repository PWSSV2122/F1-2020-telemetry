package packet_struct;

public class Car_status {

	private byte tractionControl;
	private byte antiLockBrakes;
	private byte fuelMix;
	private byte frontBrakeBias;
	private byte pitLimiterStatus;
	private float fuelInTank;
	private float fuelCapacity;
	private float fuelRemainingLaps;
	private short maxRPM;
	private short idleRPM;
	private byte maxGears;
	private byte drsAllowed;
	private short drsActivationDistance;
	private byte tyresWearRL;
	private byte tyresWearRR;
	private byte tyresWearFL;
	private byte tyresWearFR;
	private byte actualTyreCompound;
	private byte visualTyreCompound;
	private byte tyresAgeLaps;
	private byte tyresDamageRL;
	private byte tyresDamageRR;
	private byte tyresDamageFL;
	private byte tyresDamageFR;
	private byte frontLeftWingDamage;
	private byte frontRightWingDamage;
	private byte rearWingDamage;
	private byte drsFault;
	private byte engineDamage;
	private byte gearBoxDamage;
	private byte vehicleFiaFlages;
	private float ersStoreEnergy;
	private byte ersDeployMode;
	private float ersHarvestedThisLapMGUK;
	private float ersHarvestedThisLapMGUH;
	private float ersDeployedThisLap;
	
	public Car_status(byte tractionControl, byte antiLockBrakes, byte fuelMix, byte frontBrakeBias, byte pitLimiterStatus, float fuelInTank, float fuelCapacity, float fuelRemainingLaps, 
			short maxRPM, short idleRPM, byte maxGears, byte drsAllowed, short drsActivationDistance, byte tyresWearRL, byte tyresWearRR, byte tyresWearFL, byte tyresWearFR,
			byte actualTyreCompound, byte visualTyreCompound, byte tyresAgeLaps, byte tyresDamageRL, byte tyresDamageRR, byte tyresDamageFL, byte tyresDamageFR, byte frontLeftWingDamage, 
			byte frontRightWingDamage, byte rearWingDamage, byte drsFault, byte engineDamage, byte gearBoxDamage, byte vehicleFiaFlages, float ersStoreEnergy, byte ersDeployMode, 
			float ersHarvestedThisLapMGUK, float ersHarvestedThisLapMGUH, float ersDeployedThisLap) {
	
		this.tractionControl = tractionControl;
		this.antiLockBrakes = antiLockBrakes;
		this.fuelMix = fuelMix;
		this.frontBrakeBias = frontBrakeBias;
		this.pitLimiterStatus = pitLimiterStatus;
		this.fuelInTank = fuelInTank;
		this.fuelCapacity = fuelCapacity;
		this.maxRPM = maxRPM;
		this.idleRPM = idleRPM;
		this.maxGears = maxGears;
		this.drsAllowed = drsAllowed;
		this.drsActivationDistance = drsActivationDistance;
		this.tyresWearRL = tyresWearRL;
		this.tyresWearRR = tyresWearRR;
		this.tyresWearFL = tyresWearFL;
		this.tyresWearFR = tyresWearFR;
		this.actualTyreCompound = actualTyreCompound;
		this.visualTyreCompound = visualTyreCompound;
		this.tyresAgeLaps = tyresAgeLaps;
		this.tyresDamageRL = tyresDamageRL;
		this.tyresDamageRR = tyresDamageRR;
		this.tyresDamageFL = tyresDamageFL;
		this.tyresDamageFR = tyresDamageFR;
		this.frontLeftWingDamage = frontLeftWingDamage;
		this.frontRightWingDamage = frontRightWingDamage;
		this.rearWingDamage = rearWingDamage;
		this.drsFault = drsFault;
		this.engineDamage = engineDamage;
		this.gearBoxDamage = gearBoxDamage;
		this.vehicleFiaFlages = vehicleFiaFlages;
		this.ersDeployMode = ersDeployMode;
		this.ersHarvestedThisLapMGUK = ersHarvestedThisLapMGUK;
		this.ersHarvestedThisLapMGUH = ersHarvestedThisLapMGUH;
		this.ersDeployedThisLap = ersDeployedThisLap;
	}
	
	public byte getTractionControl() {
		return tractionControl;
	}
	
	public byte getAntiLockBrakes() {
		return antiLockBrakes ;
	}
	
	public byte getFuelMix() {
		return fuelMix;
	}
	
	public byte getFrontBrakeBias() {
		return frontBrakeBias;
	}
	
	public byte getPitLimiterStatus() {
		return pitLimiterStatus;
	}
	
	public float getFuelInTank() {
		return fuelInTank ;
	}
	
	public float getFuelCapacity() {
		return fuelCapacity;
	}
	
	public float getFuelRemainingLaps() {
		return fuelRemainingLaps;
	}
	
	public short getMaxRPM() {
		return maxRPM;
	}
	
	public short getIdleRPM() {
		return idleRPM;
	}
	
	public byte getMaxGears() {
		return maxGears;
	}
	
	public byte getDrsAllowed() {
		return drsAllowed;
	}
	
	public short getDrsActivationDistance() {
		return drsActivationDistance;
	}
	
	public byte getTyresWearRL() {
		return tyresWearRL;
	}
	
	public byte getTyresWearRR() {
		return tyresWearRR;
	}
	
	public byte getTyresWearFL() {
		return tyresWearFL;
	}
	
	public byte getTyresWearFR() {
		return tyresWearFR;
	}
	
	public byte getActualTyreCompound() {
		return actualTyreCompound;
	}
	
	public byte getVisualTyreCompound() {
		return visualTyreCompound;
	}
	
	public byte getTyresAgeLaps() {
		return tyresAgeLaps;
	}
	
	public byte getTyresDamageRL() {
		return tyresDamageRL;
	}
	
	public byte getTyresDamageRR() {
		return tyresDamageRR;
	}
	
	public byte getTyresDamageFL() {
		return tyresDamageFL;
	}
	
	public byte getTyresDamageFR() {
		return tyresDamageFR;
	}
	
	public byte getFrontLeftWingDamage() {
		return frontLeftWingDamage;
	}
	
	public byte getFrontRightWingDamage() {
		return frontRightWingDamage;
	}
	
	public byte getRearWingDamage() {
		return rearWingDamage;
	}
	
	public byte getDrsFault() {
		return drsFault;
	}
	
	public byte getEngineDamage() {
		return engineDamage;
	}
	
	public byte getGearBoxDamage() {
		return gearBoxDamage;
	}
	
	public byte getVehicleFiaFlages() {
		return vehicleFiaFlages;
	}
	
	public float getErsStoreEnergy() {
		return ersStoreEnergy;
	}
	
	public byte getErsDeployMode() {
		return ersDeployMode;
	}
	
	public float getErsHarvestedThisLapMGUK() {
		return ersHarvestedThisLapMGUK;
	}
	
	public float getErsHarvestedThisLapMGUH() {
		return ersHarvestedThisLapMGUH;
	}
	
	public float getErsDeployedThisLap() {
		return ersDeployedThisLap;
	}
}