package packet_struct.Motion;

public class Motion_car {
	
	private float worldPositionX;
	private float worldPositionY;
	private float worldPositionZ;
	private float worldVelocityX;
	private float worldVelocityY;
	private float worldVelocityZ;
	private short worldForwardDirX;
	private short worldForwardDirY;
	private short worldForwardDirZ;
	private short worldRigthDirX;
	private short worldRigthDirY;
	private short worldRigthDirZ;
	private float gForceLateral;
	private float gForceLongitudinal;
	private float gForceVertical;
	private float yaw;
	private float pitch;
	private float roll;
	
	public Motion_car(float worldPositionX, float worldPositionY, float worldPositionZ, float worldVelocityX, float worldVelocityY, float worldVelocityZ,
			short worldForwardDirX, short worldForwardDirY, short worldForwardDirZ, short worldRigthDirX, short worldRigthDirY, short worldRigthDirZ,
			float gForceLateral, float gForceLongitudinal, float gForceVertical, float yaw, float pitch, float roll) {
		
		this.worldPositionX = worldPositionX;
		this.worldPositionY = worldPositionY;
		this.worldPositionZ = worldPositionZ;
		this.worldVelocityX = worldVelocityX;
		this.worldVelocityY = worldVelocityY;
		this.worldVelocityZ = worldVelocityZ;
		this.worldForwardDirX = worldForwardDirX;
		this.worldForwardDirY = worldForwardDirY;
		this.worldForwardDirZ = worldForwardDirZ;
		this.worldRigthDirX = worldRigthDirX;
		this.worldRigthDirY = worldRigthDirY;
		this.worldRigthDirZ = worldRigthDirZ;
		this.gForceLateral = gForceLateral;
		this.gForceLongitudinal = gForceLongitudinal;
		this.gForceVertical = gForceVertical;
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;
	}
	
	public float getWorldPositionX() {
		return worldPositionX;
	}
	
	public float getWorldPositionY() {
		return worldPositionY;
	}
	
	public float getWorldPositionZ() {
		return worldPositionZ;
	}
	
	public float getWorldVelocityX() {
		return worldVelocityX;
	}
	
	public float getWorldVelocityY() {
		return worldVelocityY;
	}
	
	public float getWorldVelocityZ() {
		return worldVelocityZ;
	}
	
	public short getWorldForwardDirX() {
		return worldForwardDirX;
	}
	
	public short getWorldForwardDirY() {
		return worldForwardDirY;
	}
	
	public short getWorldForwardDirZ() {
		return worldForwardDirZ;
	}
	
	public short getWorldRigthDirX() {
		return worldRigthDirX;
	}
	
	public short getWorldRigthDirY() {
		return worldRigthDirY;
	}
	
	public short getWorldRigthDirZ() {
		return worldRigthDirZ;
	}
	
	public float getGForceLateral() {
		return gForceLateral;
	}
	
	public float getGForceLongitudinal() {
		return gForceLongitudinal;
	}
	
	public float getGForceVertical() {
		return gForceVertical;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getRoll() {
		return roll;
	}
}
