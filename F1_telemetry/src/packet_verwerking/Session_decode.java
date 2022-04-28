package packet_verwerking;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import Global_vars.Data;
import packet_verwerking.Packet_locations.Session;

public class Session_decode {

	private static packet_struct.Session.Session Session_data;
	private static packet_struct.Session.Session_Marshal[] Session_Marshal = new packet_struct.Session.Session_Marshal[21];
	private static packet_struct.Session.Session_Weather[] Session_Weather = new packet_struct.Session.Session_Weather[20];
	
	public static void decode(byte[] packet) {
		try {
			int offset = packet[Session.m_numMarshalZones[0]] * Session.MarshalZones;
			Session_data = new packet_struct.Session.Session(
					packet[Session.m_weather[0]],
					packet[Session.m_trackTemperature[0]],
					packet[Session.m_airTemperature[0]],
					packet[Session.m_totalLaps[0]],
					ByteBuffer.wrap(new byte[] {packet[Session.m_trackLength[0]], packet[Session.m_trackLength[1]]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					packet[Session.m_sessionType[0]],
					packet[Session.m_trackId[0]],
					packet[Session.m_formula[0]],
					ByteBuffer.wrap(new byte[] {packet[Session.m_sessionTimeLeft[0]], packet[Session.m_sessionTimeLeft[1]]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					ByteBuffer.wrap(new byte[] {packet[Session.m_sessionDuration[0]], packet[Session.m_sessionDuration[1]]}).order(ByteOrder.LITTLE_ENDIAN).getShort(),
					packet[Session.m_pitSpeedLimit[0]],
					packet[Session.m_gamePaused[0]],
					packet[Session.m_isSpectating[0]],
					packet[Session.m_spectatorCarIndex[0]],
					packet[Session.m_sliProNativeSupport[0]],
					packet[Session.m_numMarshalZones[0]],
					packet[Session.m_safetyCarStatus[0] + offset],
					packet[Session.m_networkGame[0] + offset],
					packet[Session.m_numWeatherForecastSamples[0] + offset]);
		} catch (Exception e) {
			e.printStackTrace();
			//custom error message
		}
		for (int i = 0; i < Session_data.getNumMarshalZones(); i++) {
			int offset = Session.MarshalZones * i;
			try {
				Session_Marshal[i] = new packet_struct.Session.Session_Marshal(
						ByteBuffer.wrap(new byte[] {packet[Session.m_zoneStart_[0] + offset], packet[Session.m_zoneStart_[1] + offset], packet[Session.m_zoneStart_[2] + offset], packet[Session.m_zoneStart_[3] + offset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat(),
						packet[Session.m_zoneFlag_[0] + offset]);	
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message 
			}
		}
		
		for (int i = 0; i < Session_data.getNumWeatherForcastSamples(); i++) {
			int offset = Session.WeatherForecastSamples * i;
			try {
				Session_Weather[i] = new packet_struct.Session.Session_Weather(
						packet[Session.m_sessionType_[0] + offset],
						packet[Session.m_timeOffset_[0] + offset],
						packet[Session.m_weather_[0] + offset],
						packet[Session.m_trackTemperature_[0] + offset],
						packet[Session.m_airTemperature_[0] + offset]);	
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message 
			}
		}
		
		Data.Session = Session_data;
		Data.Session_marshal = Session_Marshal;
		Data.Session_weather = Session_Weather;
	}
}
