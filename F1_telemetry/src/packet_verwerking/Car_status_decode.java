package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Car_status;
import packet_verwerking.Packet_locations.Session;

public class Car_status_decode {

	private static packet_struct.Car_status[] Car_status_data= new packet_struct.Car_status[22];
	
	public static void decode(byte[] packet) {
		for (int i = 0; i < 22; i++) {
			int offset = Car_status.Car_status * i;
			try {
				Car_status_data[i] = new packet_struct.Car_status(
						packet[Car_status.m_tractionControl_[0] + offset],
						packet[Car_status.m_antiLockBrakes_[0] + offset],
						packet[Car_status.m_fuelMix_[0] + offset],
						packet[Car_status.m_frontBrakeBias_[0] + offset],
						packet[Car_status.m_pitLimiterStatus_[0] + offset],
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_fuelInTank_[0] + offset], packet[Car_status.m_fuelInTank_[1] + offset], packet[Car_status.m_fuelInTank_[2] + offset], packet[Car_status.m_fuelInTank_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_fuelCapacity_[0] + offset], packet[Car_status.m_fuelCapacity_[1] + offset], packet[Car_status.m_fuelCapacity_[2] + offset], packet[Car_status.m_fuelCapacity_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_fuelRemainingLaps_[0] + offset], packet[Car_status.m_fuelRemainingLaps_[1] + offset], packet[Car_status.m_fuelRemainingLaps_[2] + offset], packet[Car_status.m_fuelRemainingLaps_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_maxRPM_[0] + offset], packet[Car_status.m_maxRPM_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_idleRPM_[0] + offset], packet[Car_status.m_idleRPM_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						packet[Car_status.m_maxGears_[0] + offset],
						packet[Car_status.m_drsAllowed_[0] + offset],
						ByteBuffer.wrap(new byte[] {packet[Session.m_zoneStart_[0] + offset], packet[Session.m_zoneStart_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						packet[Car_status.m_tyresWear_RL_[0] + offset],
						packet[Car_status.m_tyresWear_RR_[0] + offset],
						packet[Car_status.m_tyresWear_FL_[0] + offset],
						packet[Car_status.m_tyresWear_FR_[0] + offset],
						packet[Car_status.m_actualTyreCompound_[0] + offset],
						packet[Car_status.m_visualTyreCompound_[0] + offset],
						packet[Car_status.m_tyresAgeLaps_[0] + offset],
						packet[Car_status.m_tyresDamage_RL_[0] + offset],
						packet[Car_status.m_tyresDamage_RR_[0] + offset],
						packet[Car_status.m_tyresDamage_FL_[0] + offset],
						packet[Car_status.m_tyresDamage_FR_[0] + offset],
						packet[Car_status.m_frontLeftWingDamage_[0] + offset],
						packet[Car_status.m_frontRightWingDamage_[0] + offset],
						packet[Car_status.m_rearWingDamage_[0] + offset],
						packet[Car_status.m_drsFault_[0] + offset],
						packet[Car_status.m_engineDamage_[0] + offset],
						packet[Car_status.m_gearBoxDamage_[0] + offset],
						packet[Car_status.m_ersStoreEnergy_[0] + offset],
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_ersStoreEnergy_[0] + offset], packet[Car_status.m_ersStoreEnergy_[1] + offset], packet[Car_status.m_ersStoreEnergy_[2] + offset], packet[Car_status.m_ersStoreEnergy_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						packet[Car_status.m_ersDeployMode_[0] + offset],
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_ersHarvestedThisLapMGUK_[0] + offset], packet[Car_status.m_ersHarvestedThisLapMGUK_[1] + offset], packet[Car_status.m_ersHarvestedThisLapMGUK_[2] + offset], packet[Car_status.m_ersHarvestedThisLapMGUK_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_ersHarvestedThisLapMGUH_[0] + offset], packet[Car_status.m_ersHarvestedThisLapMGUH_[1] + offset], packet[Car_status.m_ersHarvestedThisLapMGUH_[2] + offset], packet[Car_status.m_ersHarvestedThisLapMGUH_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Car_status.m_ersDeployedThisLap_[0] + offset], packet[Car_status.m_ersDeployedThisLap_[1] + offset], packet[Car_status.m_ersDeployedThisLap_[2] + offset], packet[Car_status.m_ersDeployedThisLap_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());	
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message
			}
		}
		Data.Car_status = Car_status_data;
	}
}
