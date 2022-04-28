package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Car_telemetry;

public class Car_telemetry_decode {

	private static packet_struct.Car_telemetry.Car_telemetry Car_telemetry_data;
	private static packet_struct.Car_telemetry.Car_telemetry_car[] Car_telemetry_car = new packet_struct.Car_telemetry.Car_telemetry_car[22];
	
	public static void decode(byte[] packet) {
		try {
			Car_telemetry_data = new packet_struct.Car_telemetry.Car_telemetry(
				ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_buttonStatus[0]], packet[Car_telemetry.m_buttonStatus[1]], packet[Car_telemetry.m_buttonStatus[2]], packet[Car_telemetry.m_buttonStatus[3]]}).order(ByteOrder.LITTLE_ENDIAN).getInt(),
				packet[Car_telemetry.m_mfdPanelIndex[0]],
				packet[Car_telemetry.m_mfdPanelIndexSecondaryPlayer[0]],
				packet[Car_telemetry.m_suggestedGear[0]]);	
		} catch (Exception e) {
			e.printStackTrace();
			//custom error message
		}
		
		for (int i = 0; i < 22; i++) {
			int offset = Car_telemetry.Car_Telemetry;
			try {
				Car_telemetry_car[i] = new packet_struct.Car_telemetry.Car_telemetry_car(
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_speed_[0] + offset], packet[Car_telemetry.m_speed_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_throttle_[0] + offset], packet[Car_telemetry.m_throttle_[1] + offset], packet[Car_telemetry.m_throttle_[2] + offset], packet[Car_telemetry.m_throttle_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_steer_[0] + offset], packet[Car_telemetry.m_steer_[1] + offset], packet[Car_telemetry.m_steer_[2] + offset], packet[Car_telemetry.m_steer_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_brake_[0] + offset], packet[Car_telemetry.m_brake_[1] + offset], packet[Car_telemetry.m_brake_[2] + offset], packet[Car_telemetry.m_brake_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					packet[Car_telemetry.m_clutch_[0] + offset],
					packet[Car_telemetry.m_gear_[0] + offset],
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_engineRPM_[0] + offset], packet[Car_telemetry.m_engineRPM_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					packet[Car_telemetry.m_drs_[0] + offset],
					packet[Car_telemetry.m_revLightsPercent_[0]],
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_brakesTemperature_RL_[0] + offset], packet[Car_telemetry.m_brakesTemperature_RL_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_brakesTemperature_RR_[0] + offset], packet[Car_telemetry.m_brakesTemperature_RR_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_brakesTemperature_FL_[0] + offset], packet[Car_telemetry.m_brakesTemperature_FL_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_brakesTemperature_FR_[0] + offset], packet[Car_telemetry.m_brakesTemperature_FR_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					packet[Car_telemetry.m_tyresSurfaceTemperature_RL_[0] + offset],
					packet[Car_telemetry.m_tyresSurfaceTemperature_RR_[0] + offset],
					packet[Car_telemetry.m_tyresSurfaceTemperature_FL_[0] + offset],
					packet[Car_telemetry.m_tyresSurfaceTemperature_FR_[0] + offset],
					packet[Car_telemetry.m_tyresInnerTemperature_RL_[0] + offset],
					packet[Car_telemetry.m_tyresInnerTemperature_RR_[0] + offset],
					packet[Car_telemetry.m_tyresInnerTemperature_FL_[0] + offset],
					packet[Car_telemetry.m_tyresInnerTemperature_FR_[0] + offset],
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_engineTemperature_[0] + offset], packet[Car_telemetry.m_engineTemperature_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),						
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_tyresPressure_RL_[0] + offset], packet[Car_telemetry.m_tyresPressure_RL_[1] + offset], packet[Car_telemetry.m_tyresPressure_RL_[2] + offset], packet[Car_telemetry.m_tyresPressure_RL_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_tyresPressure_RR_[0] + offset], packet[Car_telemetry.m_tyresPressure_RR_[1] + offset], packet[Car_telemetry.m_tyresPressure_RR_[2] + offset], packet[Car_telemetry.m_tyresPressure_RR_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_tyresPressure_FL_[0] + offset], packet[Car_telemetry.m_tyresPressure_FL_[1] + offset], packet[Car_telemetry.m_tyresPressure_FL_[2] + offset], packet[Car_telemetry.m_tyresPressure_FL_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Car_telemetry.m_tyresPressure_FR_[0] + offset], packet[Car_telemetry.m_tyresPressure_FR_[1] + offset], packet[Car_telemetry.m_tyresPressure_FR_[2] + offset], packet[Car_telemetry.m_tyresPressure_FR_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					packet[Car_telemetry.m_surfaceType_RL_[0] + offset],
					packet[Car_telemetry.m_surfaceType_RR_[0] + offset],
					packet[Car_telemetry.m_surfaceType_FL_[0] + offset],
					packet[Car_telemetry.m_surfaceType_FR_[0] + offset]);	
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message
			}
		}
		Data.Car_telemetry = Car_telemetry_data;
		Data.Car_telemetry_car = Car_telemetry_car;
	}
}
