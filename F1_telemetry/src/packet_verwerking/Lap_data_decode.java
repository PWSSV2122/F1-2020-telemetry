package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Lap_data;

public class Lap_data_decode {

	private static packet_struct.Lap_data[] Lap_data_data = new packet_struct.Lap_data[22];
	
	public static void decode(byte[] packet) {
		for (int i = 0; i < 22; i++) {
			int offset = Lap_data.Lap_data * i;
			try {
				Lap_data_data[i] = new packet_struct.Lap_data(
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_lastLapTime_[0] + offset], packet[Lap_data.m_lastLapTime_[1] + offset], packet[Lap_data.m_lastLapTime_[2] + offset], packet[Lap_data.m_lastLapTime_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_currentLapTime_[0] + offset], packet[Lap_data.m_currentLapTime_[1] + offset], packet[Lap_data.m_currentLapTime_[2] + offset], packet[Lap_data.m_currentLapTime_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_sector1TimeInMS_[0] + offset], packet[Lap_data.m_sector1TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_sector2TimeInMS_[0] + offset], packet[Lap_data.m_sector2TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestLapTime_[0] + offset], packet[Lap_data.m_bestLapTime_[1] + offset], packet[Lap_data.m_bestLapTime_[2] + offset], packet[Lap_data.m_bestLapTime_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						packet[Lap_data.m_bestLapNum_[0]],
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestLapSector1TimeInMS_[0] + offset], packet[Lap_data.m_bestLapSector1TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestLapSector2TimeInMS_[0] + offset], packet[Lap_data.m_bestLapSector2TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestLapSector3TimeInMS_[0] + offset], packet[Lap_data.m_bestLapSector3TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestOverallSector1TimeInMS_[0] + offset], packet[Lap_data.m_bestOverallSector1TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						packet[Lap_data.m_bestOverallSector1LapNum_[0]],
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestOverallSector2TimeInMS_[0] + offset], packet[Lap_data.m_bestOverallSector2TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						packet[Lap_data.m_bestOverallSector2LapNum_[0]],
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_bestOverallSector3TimeInMS_[0] + offset], packet[Lap_data.m_bestOverallSector3TimeInMS_[1] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
						packet[Lap_data.m_bestOverallSector3LapNum_[0]],
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_lapDistance_[0] + offset], packet[Lap_data.m_lapDistance_[1] + offset], packet[Lap_data.m_lapDistance_[2] + offset], packet[Lap_data.m_lapDistance_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_totalDistance_[0] + offset], packet[Lap_data.m_totalDistance_[1] + offset], packet[Lap_data.m_totalDistance_[2] + offset], packet[Lap_data.m_totalDistance_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						ByteBuffer.wrap(new byte[] {packet[Lap_data.m_safetyCarDelta_[0] + offset], packet[Lap_data.m_safetyCarDelta_[1] + offset], packet[Lap_data.m_safetyCarDelta_[2] + offset], packet[Lap_data.m_safetyCarDelta_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						packet[Lap_data.m_carPosition_[0]],
						packet[Lap_data.m_currentLapNum_[0]],
						packet[Lap_data.m_pitStatus_[0]],
						packet[Lap_data.m_sector_[0]],
						packet[Lap_data.m_currentLapInvalid_[0]],
						packet[Lap_data.m_penalties_[0]],
						packet[Lap_data.m_gridPosition_[0]],
						packet[Lap_data.m_driverStatus_[0]],
						packet[Lap_data.m_resultStatus_[0]]);
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message
			}
		}
		Data.Lap_data = Lap_data_data;
	}
}
