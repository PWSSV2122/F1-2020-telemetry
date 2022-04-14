package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Final_classification;

public class Final_classification_decode {

		private static packet_struct.Final_classification.Final_classification Final_classification_data;
		private static packet_struct.Final_classification.Final_classification_car[] Final_classification_car = new packet_struct.Final_classification.Final_classification_car[22];
		
		public static void decode(byte[] packet) {
			Final_classification_data = new packet_struct.Final_classification.Final_classification(
				packet[Final_classification.m_numCars[0]]);
			
			for (int i = 0; i < 22; i++) {
				int offset = Final_classification.Final_classification * i;
				Final_classification_car[i] = new packet_struct.Final_classification.Final_classification_car(
					packet[Final_classification.m_position_[0] + offset],
					packet[Final_classification.m_numLaps_[0] + offset],
					packet[Final_classification.m_gridPosition_[0] + offset],
					packet[Final_classification.m_points_[0] + offset],
					packet[Final_classification.m_numPitStops_[0] + offset],
					packet[Final_classification.m_resultStatus_[0] + offset],
					ByteBuffer.wrap(new byte[] {packet[Final_classification.m_bestLapTime_[0]], packet[Final_classification.m_bestLapTime_[1]], packet[Final_classification.m_bestLapTime_[2]], packet[Final_classification.m_bestLapTime_[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
					ByteBuffer.wrap(new byte[] {packet[Final_classification.m_totalRaceTime_[0]], packet[Final_classification.m_totalRaceTime_[1]], packet[Final_classification.m_totalRaceTime_[2]], packet[Final_classification.m_totalRaceTime_[3]],
					                            packet[Final_classification.m_totalRaceTime_[4]], packet[Final_classification.m_totalRaceTime_[5]], packet[Final_classification.m_totalRaceTime_[6]], packet[Final_classification.m_totalRaceTime_[7]]}).order(ByteOrder.LITTLE_ENDIAN).getLong(),	
					packet[Final_classification.m_penaltiesTime_[0] + offset],
					packet[Final_classification.m_numPenalties_[0] + offset],
					packet[Final_classification.m_numTyreStints_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_1_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_2_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_3_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_4_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_5_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_6_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_7_[0] + offset],
					packet[Final_classification.m_tyreStintsActual_8_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_1_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_2_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_3_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_4_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_5_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_6_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_7_[0] + offset],
					packet[Final_classification.m_tyreStintsVisual_8_[0] + offset]);
			}
			Data.Final_classification = Final_classification_data;
			Data.Final_classification_car = Final_classification_car;
		}
}