package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import packet_verwerking.Packet_locations.Motion;
import Global_vars.Data;

public class Motion_decode {
	
	private static packet_struct.Motion.Motion Motion_data;
	private static packet_struct.Motion.Motion_car[] Motion_car = new packet_struct.Motion.Motion_car[22];

	public static void decode(byte[] packet) {
		for (int i = 0; i < 22; i++) {
			int offset = Motion.CarMotionData * i;
			try {
				Motion_car[i] = new packet_struct.Motion.Motion_car(
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldPositionX_[0] + offset], packet[Motion.m_worldPositionX_[1] + offset], packet[Motion.m_worldPositionX_[2] + offset], packet[Motion.m_worldPositionX_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldPositionY_[0] + offset], packet[Motion.m_worldPositionY_[1] + offset], packet[Motion.m_worldPositionY_[2] + offset], packet[Motion.m_worldPositionY_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldPositionZ_[0] + offset], packet[Motion.m_worldPositionZ_[1] + offset], packet[Motion.m_worldPositionZ_[2] + offset], packet[Motion.m_worldPositionZ_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldVelocityX_[0] + offset], packet[Motion.m_worldVelocityX_[1] + offset], packet[Motion.m_worldVelocityX_[2] + offset], packet[Motion.m_worldVelocityX_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldVelocityY_[0] + offset], packet[Motion.m_worldVelocityY_[1] + offset], packet[Motion.m_worldVelocityY_[2] + offset], packet[Motion.m_worldVelocityY_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldVelocityZ_[0] + offset], packet[Motion.m_worldVelocityZ_[1] + offset], packet[Motion.m_worldVelocityZ_[2] + offset], packet[Motion.m_worldVelocityZ_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldForwardDirX_[0] + offset], packet[Motion.m_worldForwardDirX_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldForwardDirY_[0] + offset], packet[Motion.m_worldForwardDirY_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldForwardDirZ_[0] + offset], packet[Motion.m_worldForwardDirZ_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldRightDirX_[0] + offset], packet[Motion.m_worldRightDirX_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldRightDirY_[0] + offset], packet[Motion.m_worldRightDirY_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_worldRightDirZ_[0] + offset], packet[Motion.m_worldRightDirZ_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_gForceLateral_[0] + offset], packet[Motion.m_gForceLateral_[1] + offset], packet[Motion.m_gForceLateral_[2] + offset], packet[Motion.m_gForceLateral_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_gForceLongitudinal_[0] + offset], packet[Motion.m_gForceLongitudinal_[1] + offset], packet[Motion.m_gForceLongitudinal_[2] + offset], packet[Motion.m_gForceLongitudinal_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_gForceVertical_[0] + offset], packet[Motion.m_gForceVertical_[1] + offset], packet[Motion.m_gForceVertical_[2] + offset], packet[Motion.m_gForceVertical_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_yaw_[0] + offset], packet[Motion.m_yaw_[1] + offset], packet[Motion.m_yaw_[2] + offset], packet[Motion.m_yaw_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_pitch_[0] + offset], packet[Motion.m_pitch_[1] + offset], packet[Motion.m_pitch_[2] + offset], packet[Motion.m_pitch_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Motion.m_roll_[0] + offset], packet[Motion.m_roll_[1] + offset], packet[Motion.m_roll_[2] + offset], packet[Motion.m_roll_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());				
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message
			}
		}
		try {
			Motion_data = new packet_struct.Motion.Motion(
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionPosition_RL[0]], packet[Motion.m_suspensionPosition_RL[1]], packet[Motion.m_suspensionPosition_RL[2]], packet[Motion.m_suspensionPosition_RL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionPosition_RR[0]], packet[Motion.m_suspensionPosition_RR[1]], packet[Motion.m_suspensionPosition_RR[2]], packet[Motion.m_suspensionPosition_RR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionPosition_FL[0]], packet[Motion.m_suspensionPosition_FL[1]], packet[Motion.m_suspensionPosition_FL[2]], packet[Motion.m_suspensionPosition_FL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionPosition_FR[0]], packet[Motion.m_suspensionPosition_FR[1]], packet[Motion.m_suspensionPosition_FR[2]], packet[Motion.m_suspensionPosition_FR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionVelocity_RL[0]], packet[Motion.m_suspensionVelocity_RL[1]], packet[Motion.m_suspensionVelocity_RL[2]], packet[Motion.m_suspensionVelocity_RL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionVelocity_RR[0]], packet[Motion.m_suspensionVelocity_RR[1]], packet[Motion.m_suspensionVelocity_RR[2]], packet[Motion.m_suspensionVelocity_RR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionVelocity_FL[0]], packet[Motion.m_suspensionVelocity_FL[1]], packet[Motion.m_suspensionVelocity_FL[2]], packet[Motion.m_suspensionVelocity_FL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionVelocity_FR[0]], packet[Motion.m_suspensionVelocity_FR[1]], packet[Motion.m_suspensionVelocity_FR[2]], packet[Motion.m_suspensionVelocity_FR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionAcceleration_RL[0]], packet[Motion.m_suspensionAcceleration_RL[1]], packet[Motion.m_suspensionAcceleration_RL[2]], packet[Motion.m_suspensionAcceleration_RL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionAcceleration_RR[0]], packet[Motion.m_suspensionAcceleration_RR[1]], packet[Motion.m_suspensionAcceleration_RR[2]], packet[Motion.m_suspensionAcceleration_RR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionAcceleration_FL[0]], packet[Motion.m_suspensionAcceleration_FL[1]], packet[Motion.m_suspensionAcceleration_FL[2]], packet[Motion.m_suspensionAcceleration_FL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_suspensionAcceleration_FR[0]], packet[Motion.m_suspensionAcceleration_FR[1]], packet[Motion.m_suspensionAcceleration_FR[2]], packet[Motion.m_suspensionAcceleration_FR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSpeed_RL[0]], packet[Motion.m_wheelSpeed_RL[1]], packet[Motion.m_wheelSpeed_RL[2]], packet[Motion.m_wheelSpeed_RL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSpeed_RR[0]], packet[Motion.m_wheelSpeed_RR[1]], packet[Motion.m_wheelSpeed_RR[2]], packet[Motion.m_wheelSpeed_RR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSpeed_FL[0]], packet[Motion.m_wheelSpeed_FL[1]], packet[Motion.m_wheelSpeed_FL[2]], packet[Motion.m_wheelSpeed_FL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSpeed_FR[0]], packet[Motion.m_wheelSpeed_FR[1]], packet[Motion.m_wheelSpeed_FR[2]], packet[Motion.m_wheelSpeed_FR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSlip_RL[0]], packet[Motion.m_wheelSlip_RL[1]], packet[Motion.m_wheelSlip_RL[2]], packet[Motion.m_wheelSlip_RL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSlip_RR[0]], packet[Motion.m_wheelSlip_RR[1]], packet[Motion.m_wheelSlip_RR[2]], packet[Motion.m_wheelSlip_RR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSlip_FL[0]], packet[Motion.m_wheelSlip_FL[1]], packet[Motion.m_wheelSlip_FL[2]], packet[Motion.m_wheelSlip_FL[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_wheelSlip_FR[0]], packet[Motion.m_wheelSlip_FR[1]], packet[Motion.m_wheelSlip_FR[2]], packet[Motion.m_wheelSlip_FR[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_localVelocityX[0]], packet[Motion.m_localVelocityX[1]], packet[Motion.m_localVelocityX[2]], packet[Motion.m_localVelocityX[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_localVelocityY[0]], packet[Motion.m_localVelocityY[1]], packet[Motion.m_localVelocityY[2]], packet[Motion.m_localVelocityY[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_localVelocityZ[0]], packet[Motion.m_localVelocityZ[1]], packet[Motion.m_localVelocityZ[2]], packet[Motion.m_localVelocityZ[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_angularAccelerationX[0]], packet[Motion.m_angularAccelerationX[1]], packet[Motion.m_angularAccelerationX[2]], packet[Motion.m_angularAccelerationX[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_angularAccelerationY[0]], packet[Motion.m_angularAccelerationY[1]], packet[Motion.m_angularAccelerationY[2]], packet[Motion.m_angularAccelerationY[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_angularAccelerationZ[0]], packet[Motion.m_angularAccelerationZ[1]], packet[Motion.m_angularAccelerationZ[2]], packet[Motion.m_angularAccelerationZ[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Motion.m_frontWheelsAngle[0]], packet[Motion.m_frontWheelsAngle[1]], packet[Motion.m_frontWheelsAngle[2]], packet[Motion.m_frontWheelsAngle[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());	
		} catch (Exception e) {
			e.printStackTrace();
			//custom error message
		}
		Data.Motion = Motion_data;
		Data.Motion_car = Motion_car;
	}
}