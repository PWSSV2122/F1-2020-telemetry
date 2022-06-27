package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Car_setups;

public class Car_setups_decode {

	public static packet_struct.Car_setups[] Car_setups_data = new packet_struct.Car_setups[22];

	public static void decode(byte[] packet) {
		for (int i = 0; i < 22; i++) {
			int offset = Car_setups.Car_setups * i;
			Car_setups_data[i] = new packet_struct.Car_setups(
				packet[Car_setups.m_frontWing_[0] + offset],
				packet[Car_setups.m_rearWing_[0] + offset],
				packet[Car_setups.m_onThrottle_[0] + offset],
				packet[Car_setups.m_offThrottle_[0] + offset],
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_frontCamber_[0] + offset], packet[Car_setups.m_frontCamber_[1] + offset], packet[Car_setups.m_frontCamber_[2] + offset], packet[Car_setups.m_frontCamber_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_rearCamber_[0] + offset], packet[Car_setups.m_rearCamber_[1] + offset], packet[Car_setups.m_rearCamber_[2] + offset], packet[Car_setups.m_rearCamber_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),	
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_frontToe_[0] + offset], packet[Car_setups.m_frontToe_[1] + offset], packet[Car_setups.m_frontToe_[2] + offset], packet[Car_setups.m_frontToe_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_rearToe_[0] + offset], packet[Car_setups.m_rearToe_[1] + offset], packet[Car_setups.m_rearToe_[2] + offset], packet[Car_setups.m_rearToe_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				packet[Car_setups.m_frontSuspension_[0] + offset],
				packet[Car_setups.m_rearSuspension_[0] + offset],
				packet[Car_setups.m_frontAntiRollBar_[0] + offset],
				packet[Car_setups.m_rearAntiRollBar_[0] + offset],
				packet[Car_setups.m_frontSuspensionHeight_[0] + offset],
				packet[Car_setups.m_rearSuspensionHeight_[0] + offset],
				packet[Car_setups.m_brakePressure_[0] + offset],
				packet[Car_setups.m_brakeBias_[0] + offset],
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_rearLeftTyrePressure_[0] + offset], packet[Car_setups.m_rearLeftTyrePressure_[1] + offset], packet[Car_setups.m_rearLeftTyrePressure_[2] + offset], packet[Car_setups.m_rearLeftTyrePressure_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_rearRightTyrePressure_[0] + offset], packet[Car_setups.m_rearRightTyrePressure_[1] + offset], packet[Car_setups.m_rearRightTyrePressure_[2] + offset], packet[Car_setups.m_rearRightTyrePressure_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_frontLeftTyrePressure_[0] + offset], packet[Car_setups.m_frontLeftTyrePressure_[1] + offset], packet[Car_setups.m_frontLeftTyrePressure_[2] + offset], packet[Car_setups.m_frontLeftTyrePressure_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_frontRightTyrePressure_[0] + offset], packet[Car_setups.m_frontRightTyrePressure_[1] + offset], packet[Car_setups.m_frontRightTyrePressure_[2] + offset], packet[Car_setups.m_frontRightTyrePressure_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
				packet[Car_setups.m_ballast_[0] + offset],
				ByteBuffer.wrap(new byte[] {packet[Car_setups.m_fuelLoad_[0] + offset], packet[Car_setups.m_fuelLoad_[1] + offset], packet[Car_setups.m_fuelLoad_[2] + offset], packet[Car_setups.m_fuelLoad_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
		}
	Data.Car_setups = Car_setups_data;
	}
}