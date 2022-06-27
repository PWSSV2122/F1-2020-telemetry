package packet_struct.Motion;

public class Motion {
	
	private float suspensionPositionRL;
	private float suspensionPositionRR;
	private float suspensionPositionFL;
	private float suspensionPositionFR;
	private float suspensionVelocityRL;
	private float suspensionVelocityRR;
	private float suspensionVelocityFL;
	private float suspensionVelocityFR;
	private float suspensionAccelerationRL;
	private float suspensionAccelerationRR;
	private float suspensionAccelerationFL;
	private float suspensionAccelerationFR;
	private float wheelSpeedRL;
	private float wheelSpeedRR;
	private float wheelSpeedFL;
	private float wheelSpeedFR;
	private float wheelSlipRL;
	private float wheelSlipRR;
	private float wheelSlipFL;
	private float wheelSlipFR;
	private float localVelocityX;
	private float localVelocityY;
	private float localVelocityZ;
	private float angularAccelerationX;
	private float angularAccelerationY;
	private float angularAccelerationZ;
	private float frontWheelAngle;
	
	public Motion(float suspensionPositionRL, float suspensionPositionRR, float suspensionPositionFL, float suspensionPositionFR, float suspensionVelocityRL, float suspensionVelocityRR,
			float suspensionVelocityFL, float suspensionVelocityFR, float suspensionAccelerationRL, float suspensionAccelerationRR, float suspensionAccelerationFL, float suspensionAccelerationFR,
			float wheelSpeedRL, float wheelSpeedRR, float wheelSpeedFL, float wheelSpeedFR, float wheelSlipRL, float wheelSlipRR, float wheelSlipFL, float wheelSlipFR, float localVelocityX,
			float localVelocityY, float localVelocityZ, float angularAccelerationX, float angularAccelerationY, float angularAccelerationZ, float frontWheelAngle) {
		
		this.suspensionPositionRL = suspensionPositionRL;
		this.suspensionPositionRR = suspensionPositionRR;
		this.suspensionPositionFL = suspensionPositionFL;
		this.suspensionPositionFR = suspensionPositionFR;
		this.suspensionVelocityRL = suspensionVelocityRL;
		this.suspensionVelocityRR = suspensionVelocityRR;
		this.suspensionVelocityFL = suspensionVelocityFL;
		this.suspensionVelocityFR = suspensionVelocityFR;
		this.suspensionAccelerationRL = suspensionAccelerationRL;
		this.suspensionAccelerationRR = suspensionAccelerationRR;
		this.suspensionAccelerationFL = suspensionAccelerationFL;
		this.suspensionAccelerationFR = suspensionAccelerationFR;
		this.wheelSpeedRL = wheelSpeedRL;
		this.wheelSpeedRR = wheelSpeedRR;
		this.wheelSpeedFL = wheelSpeedFL;
		this.wheelSpeedFR = wheelSpeedFR;
		this.wheelSlipRL = wheelSlipRL;
		this.wheelSlipRR = wheelSlipRR;
		this.wheelSlipFL = wheelSlipFL;
		this.wheelSlipFR = wheelSlipFR;
		this.localVelocityX = localVelocityX;
		this.localVelocityY = localVelocityY;
		this.localVelocityZ = localVelocityZ;
		this.angularAccelerationX = angularAccelerationX;
		this.angularAccelerationY = angularAccelerationZ;
		this.angularAccelerationX = angularAccelerationY;
		this.frontWheelAngle = frontWheelAngle;
	}
	
	public float getSuspensionPositionRL() {
		return suspensionPositionRL;
	}
	
	public float getSuspensionPositionRR() {
		return suspensionPositionRR;
	}
	
	public float getSuspensionPositionFL() {
		return suspensionPositionFL;
	}
	
	public float getSuspensionPositionFR() {
		return suspensionPositionFR;
	}
	
	public float getSuspensionVelocityRL() {
		return suspensionVelocityRL;
	}
	
	public float getSuspensionVelocityRR() {
		return suspensionVelocityRR;
	}
	
	public float getSuspensionVelocityFL() {
		return suspensionVelocityFL;
	}
	
	public float getSuspensionVelocityFR() {
		return suspensionVelocityFR;
	}
	
	public float getSuspensionAccelerationRL() {
		return suspensionAccelerationRL;
	}
	
	public float getSuspensionAccelerationRR() {
		return suspensionAccelerationRR;
	}
	
	public float getSuspensionAccelerationFL() {
		return suspensionAccelerationFL;
	}
	
	public float getSuspensionAccelerationFR() {
		return suspensionAccelerationFR;
	}
	
	public float getWheelSpeedRL() {
		return wheelSpeedRL;
	}
	
	public float getWheelSpeedRR() {
		return wheelSpeedRR;
	}
	
	public float getWheelSpeedFL() {
		return wheelSpeedFL;
	}
	
	public float getWheelSpeedFR() {
		return wheelSpeedFR;
	}
	
	public float getWheelSlipRL() {
		return wheelSlipRL;
	}
	
	public float getWheelSlipRR() {
		return wheelSlipRR;
	}
	
	public float getWheelSlipFL() {
		return wheelSlipFL;
	}
	
	public float getWheelSlipFR() {
		return wheelSlipFR;
	}
	
	public float getLocalVelocityX() {
		return localVelocityX;
	}
	
	public float getLocalVelocityY() {
		return localVelocityY;
	}
	
	public float getLocalVelocityZ() {
		return localVelocityZ;
	}
	
	public float getAngularAccelerationX() {
		return angularAccelerationX;
	}
	
	public float getAngularAccelerationY() {
		return angularAccelerationY;
	}
	
	public float getAngularAccelerationZ() {
		return angularAccelerationZ;
	}
	
	public float getFrontWheelAngle() {
		return frontWheelAngle;
	}
}