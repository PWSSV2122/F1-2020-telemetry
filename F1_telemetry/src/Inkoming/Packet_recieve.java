package Inkoming;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

public class Packet_recieve {
	static boolean test2;
	public static void main(String[] args) {
		test2 = true;
		int Motion_Packet_int = 0;
		int Session_Packet_int = 0;
		int Lap_Data_Packet_int = 0;
		int Event_Packet_int = 0;
		int Participants_Packet_int = 0;
		int Car_Setups_Packet_int = 0;
		int Car_Telemetry_Packet_int = 0;
		int Car_Status_Packet_int = 0;
		int Final_Classification_Packet_int = 0;
		int Lobby_Info_Packet_int = 0;
		int first_car = 0;
		int current_lap = 0;
		HashMap<String, Integer> participants = new HashMap<String, Integer>();
		try {
			DatagramSocket socket = new DatagramSocket(20777);
			while (test2 == true) {
				byte[] test = new byte[2000];
				DatagramPacket response = new DatagramPacket(test, test.length);
				socket.receive(response);
				
				String quote = new String(test, 0, response.getLength());
				byte[] e = quote.getBytes();
//				if (e[5] == 0) {
//					System.out.println(0);
//				}
				
//		        for (byte b : e) {
//		            String st = String.format("%02X ", b);
//		            System.out.print(st);
//		        }

		        HashMap<String, Object> Header = new HashMap<String, Object>();
				Header.put("packetFormat", (short)((e[1] & 0xFF) << 8) | (e[0] & 0xFF));
				Header.put("gameMajorVersion", (byte) e[2]);
				Header.put("gameMinorVersion", (byte) e[3]);
				Header.put("packetVersion", (byte) e[4]);
				Header.put("packetId", (byte) e[5]);
				Header.put("sessionUID", (long)((e[13] & 0xFFL) << 56) | ((e[12] & 0xFFL) << 48) | ((e[11] & 0xFFL) << 40) | ((e[10] & 0xFFL) << 32) | ((e[9] & 0xFFL) << 24) | ((e[8] & 0xFFL) << 16) | ((e[7] & 0xFFL) << 8) | ((e[6] & 0xFFL) << 0));
				Header.put("sessionTime", (int)(((e[17] & 0xFF) << 24) | ((e[16] & 0xFF) << 16) | ((e[15] & 0xFF) << 8 ) | ((e[14] & 0xFF) << 0)));
				Header.put("frameIdentifier", (int)(((e[21] & 0xFF) << 24) | ((e[20] & 0xFF) << 16) | ((e[19] & 0xFF) << 8) | ((e[18] & 0xFF) << 0)));
				Header.put("playerCarIndex", (byte) e[22]);
				Header.put("secondaryPlayerCarIndex", (byte) e[23]);
				
				
//				long y = (long)((e[13] & 0xFFL) << 56) | ((e[12] & 0xFFL) << 48) | ((e[11] & 0xFFL) << 40) | ((e[10] & 0xFFL) << 32) | ((e[9] & 0xFFL) << 24) | ((e[8] & 0xFFL) << 16) | ((e[7] & 0xFFL) << 8) | ((e[6] & 0xFFL) << 0);
//				long test2 = Long.MAX_VALUE - 4994820059938609088L;
//				test2 = test2 + Long.MAX_VALUE;
//				System.out.println(test2);
//				we zitten tegen de 64bit limit aan van java dus rolt hij over naar een negatief nummer bovenstaand algorithme zou het weer goed zetten als er een data type is dat groter dan 64 bit is

				if (e[5] == 00) {
					HashMap<String, Object> Motion_Packet = new HashMap<String, Object>();
					for (int i = 0; i < 22; i++) {
						int y = i * 60;
						Motion_Packet.put("m_worldPositionX_" + i, ByteBuffer.wrap(new byte[] {e[24 + y], e[25 + y], e[26 + y], e[27 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_worldPositionY_" + i, ByteBuffer.wrap(new byte[] {e[28 + y], e[29 + y], e[30 + y], e[31 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_worldPositionZ_" + i, ByteBuffer.wrap(new byte[] {e[32 + y], e[33 + y], e[34 + y], e[35 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_worldVelocityX_" + i, ByteBuffer.wrap(new byte[] {e[36 + y], e[37 + y], e[38 + y], e[39 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_worldVelocityY_" + i, ByteBuffer.wrap(new byte[] {e[40 + y], e[41 + y], e[42 + y], e[43 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_worldVelocityZ_" + i, ByteBuffer.wrap(new byte[] {e[44 + y], e[45 + y], e[46 + y], e[47 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_worldForwardDirX_" + i, (short)((e[49 + y] & 0xFF) << 8) | (e[48 + y] & 0xFF));
						Motion_Packet.put("m_worldForwardDirY_" + i, (short)((e[51 + y] & 0xFF) << 8) | (e[50 + y] & 0xFF));
						Motion_Packet.put("m_worldForwardDirZ_" + i, (short)((e[53 + y] & 0xFF) << 8) | (e[52 + y] & 0xFF));
						Motion_Packet.put("m_worldRightDirX_" + i, (short)((e[55 + y] & 0xFF) << 8) | (e[54 + y] & 0xFF));
						Motion_Packet.put("m_worldRightDirY_" + i, (short)((e[57 + y] & 0xFF) << 8) | (e[56 + y] & 0xFF));
						Motion_Packet.put("m_worldRightDirZ_" + i, (short)((e[59 + y] & 0xFF) << 8) | (e[58 + y] & 0xFF));
						Motion_Packet.put("m_gForceLateral_" + i, ByteBuffer.wrap(new byte[] {e[60 + y], e[61 + y], e[62 + y], e[63 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_gForceLongitudinal_" + i, ByteBuffer.wrap(new byte[] {e[64 + y], e[65 + y], e[66 + y], e[67 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_gForceVertical_" + i, ByteBuffer.wrap(new byte[] {e[68 + y], e[69 + y], e[70 + y], e[71 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_yaw_" + i, ByteBuffer.wrap(new byte[] {e[72 + y], e[73 + y], e[74 + y], e[75 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_pitch_" + i, ByteBuffer.wrap(new byte[] {e[76 + y], e[77 + y], e[78 + y], e[79 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Motion_Packet.put("m_roll_" + i, ByteBuffer.wrap(new byte[] {e[80 + y], e[81 + y], e[82 + y], e[83 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					}
					Motion_Packet.put("m_suspensionPosition_RL", ByteBuffer.wrap(new byte[] {e[1344], e[1345], e[1346], e[1347]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionPosition_RR", ByteBuffer.wrap(new byte[] {e[1348], e[1349], e[1350], e[1351]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionPosition_FL", ByteBuffer.wrap(new byte[] {e[1352], e[1353], e[1354], e[1355]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionPosition_FR", ByteBuffer.wrap(new byte[] {e[1356], e[1357], e[1358], e[1359]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionVelocity_RL", ByteBuffer.wrap(new byte[] {e[1360], e[1361], e[1362], e[1363]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionVelocity_RR", ByteBuffer.wrap(new byte[] {e[1364], e[1365], e[1366], e[1367]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionVelocity_FF", ByteBuffer.wrap(new byte[] {e[1368], e[1369], e[1370], e[1371]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionVelocity_FR", ByteBuffer.wrap(new byte[] {e[1372], e[1373], e[1374], e[1375]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionAcceleration_RL", ByteBuffer.wrap(new byte[] {e[1376], e[1377], e[1378], e[1379]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionAcceleration_RR", ByteBuffer.wrap(new byte[] {e[1380], e[1381], e[1382], e[1383]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionAcceleration_FL", ByteBuffer.wrap(new byte[] {e[1384], e[1385], e[1386], e[1387]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_suspensionAcceleration_FR", ByteBuffer.wrap(new byte[] {e[1388], e[1389], e[1390], e[1391]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSpeed_RL", ByteBuffer.wrap(new byte[] {e[1392], e[1393], e[1394], e[1395]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSpeed_RR", ByteBuffer.wrap(new byte[] {e[1396], e[1397], e[1398], e[1399]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSpeed_FL", ByteBuffer.wrap(new byte[] {e[1400], e[1401], e[1402], e[1403]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSpeed_FR", ByteBuffer.wrap(new byte[] {e[1404], e[1405], e[1406], e[1407]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSlip_RL", ByteBuffer.wrap(new byte[] {e[1408], e[1409], e[1410], e[1411]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSlip_RR", ByteBuffer.wrap(new byte[] {e[1412], e[1413], e[1414], e[1415]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSlip_FL", ByteBuffer.wrap(new byte[] {e[1416], e[1417], e[1418], e[1419]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_wheelSlip_FR", ByteBuffer.wrap(new byte[] {e[1420], e[1421], e[1422], e[1423]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_localVelocityX", ByteBuffer.wrap(new byte[] {e[1424], e[1425], e[1426], e[1427]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_localVelocityY", ByteBuffer.wrap(new byte[] {e[1428], e[1429], e[1430], e[1431]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_localVelocityZ", ByteBuffer.wrap(new byte[] {e[1432], e[1433], e[1434], e[1435]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_angularVelocityX", ByteBuffer.wrap(new byte[] {e[1436], e[1437], e[1438], e[1439]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_angularVelocityY", ByteBuffer.wrap(new byte[] {e[1440], e[1441], e[1442], e[1443]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_angularVelocityZ", ByteBuffer.wrap(new byte[] {e[1444], e[1445], e[1446], e[1447]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_angularAccelerationX", ByteBuffer.wrap(new byte[] {e[1448], e[1449], e[1450], e[1451]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_angularAccelerationY", ByteBuffer.wrap(new byte[] {e[1452], e[1453], e[1454], e[1455]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_angularAccelerationZ", ByteBuffer.wrap(new byte[] {e[1456], e[1457], e[1458], e[1459]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					Motion_Packet.put("m_frontWheelsAngle", ByteBuffer.wrap(new byte[] {e[1460], e[1461], e[1462], e[1463]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
				
					Data_saves.Packet_store.Motion_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Motion_Packet.putAll(Motion_Packet);
					Data_saves.Packet_store.Motion_Packet_store.put(String.valueOf(Motion_Packet_int), Motion_Packet);
					Motion_Packet_int = Motion_Packet_int + 1;
				}
				
				
				if (e[5] == 01) {
					HashMap<String, Object> Session_Packet = new HashMap<String, Object>();
			        
			        Session_Packet.put("m_weather", (int) e[24]);
			        Session_Packet.put("m_trackTemperature", (int) e[25]);
			        Session_Packet.put("m_airTemperature", (int) e[26]);
			        Session_Packet.put("m_totalLaps", (int) e[27]);
			        Session_Packet.put("m_trackLength", (int) ((e[29] & 0xFF) << 8) | (e[28] & 0xFF));
			        Session_Packet.put("m_sessionType", (int) e[30]);
			        Session_Packet.put("m_trackId", (int) e[31]);
			        Session_Packet.put("m_formula", (int) e[32]);
			        Session_Packet.put("m_sessionTimeLeft", (int) ((e[34] & 0xFF) << 8) | (e[33] & 0xFF));
			        Session_Packet.put("m_sessionDuration", (int) ((e[36] & 0xFF) << 8) | (e[35] & 0xFF));
			        Session_Packet.put("m_pitSpeedLimit", (int) e[37]);
			        Session_Packet.put("m_gamePaused", (int) e[38]);
			        Session_Packet.put("m_isSpectating", (int) e[39]);
			        Session_Packet.put("m_spectatorCarIndex", (int) e[40]);
			        Session_Packet.put("m_sliProNativeSupport", (int) e[41]);
			        Session_Packet.put("m_numMarshalZones", (int) e[42]);
			        for (int i = 0; i < (int) Session_Packet.get("m_numMarshalZones"); i++ ) {
			        	int y = i * 2;
			        	Session_Packet.put("m_zoneStart_" + i, (int) e[43 + y]);
			        	Session_Packet.put("m_zoneFlag_" + i, (int) e[44 + y]);
			        }
			        int u = (int) Session_Packet.get("m_numMarshalZones") * 2;
			        Session_Packet.put("m_safetyCarStatus", (int) e[45 + u]);
			        Session_Packet.put("m_networkGame", (int) e[46 + u]);
			        Session_Packet.put("m_numWeatherForecastSamples", (int) e[47 + u]);
			        for (int i = 0; i < (int) Session_Packet.get("m_numWeatherForecastSamples"); i++) {
			        	int y = i * 5 + u;
			        	Session_Packet.put("m_sessionType_" + i, (int) e[48 + y]);
			        	Session_Packet.put("m_timeOffset_" + i, (int) e[49 + y]);
			        	Session_Packet.put("m_weather_" + i, (int) e[50 + y]);
			        	Session_Packet.put("m_trackTemperature_" + i, (int) e[51 + y]);
			        	Session_Packet.put("m_airTemperature_" + i, (int) e[52 + y]);
			        }
			        
			        Data_saves.Packet_store.Session_Packet_Header.putAll(Header);
			        Data_saves.Packet_store.Session_Packet.putAll(Session_Packet);
			        Data_saves.Packet_store.Session_Packet_store.put(String.valueOf(Session_Packet_int), Session_Packet);
			        Session_Packet_int = Session_Packet_int + 1;
			        //nog checken
			        
			        if (Session_Packet_int == 750) {
				    	final int session_test = Session_Packet_int;
						Thread Session = new Thread(() -> {
							File_reader.Write_encoded.Session(session_test);
						});
						Session.start();
			        }
				}
				
				if (e[5] == 02) {
					HashMap<String, Object> Lap_Data_Packet = new HashMap<String, Object>();
					for (int i = 0; i < 22; i++) {
						int y = i * 52;
						Lap_Data_Packet.put("m_lastLapTime_" + i, ByteBuffer.wrap(new byte[] {e[24 + y], e[25 + y], e[26 + y], e[27 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Lap_Data_Packet.put("m_currentLapTime_" + i, ByteBuffer.wrap(new byte[] {e[28 + y], e[29 + y], e[30 + y], e[31 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Lap_Data_Packet.put("m_sector1TimeInMS_" + i, (short)((e[33 + y] & 0xFF) << 8) | ((e[32 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_sector2TimeInMS_" + i, (short)((e[35 + y] & 0xFF) << 8) | ((e[34 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestLapTime_" + i, ByteBuffer.wrap(new byte[] {e[36 + y], e[37 + y], e[38 + y], e[39 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Lap_Data_Packet.put("m_bestLapNum_" + i, (int) e[40 + y]);
						Lap_Data_Packet.put("m_bestLapSector1TimeInMS_" + i, (short)((e[42 + y] & 0xFF) << 8) | ((e[41 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestLapSector2TimeInMS_" + i, (short)((e[44 + y] & 0xFF) << 8) | ((e[43 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestLapSector3TimeInMS_" + i, (short)((e[46 + y] & 0xFF) << 8) | ((e[45 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestOverallSector1TimeInMS_" + i, (short)((e[48 + y] & 0xFF) << 8) | ((e[47 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestOverallSector1LapNum_" + i, (int) e[49 + y]);
						Lap_Data_Packet.put("m_bestOverallSector2TimeInMS_" + i, (short)((e[51 + y] & 0xFF) << 8) | ((e[50 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestOverallSector2LapNum_" + i, (int) e[52 + y]);
						Lap_Data_Packet.put("m_bestOverallSector3TimeInMS_" + i, (short)((e[54 + y] & 0xFF) << 8) | ((e[53 + y] & 0xFF) << 0));
						Lap_Data_Packet.put("m_bestOverallSector3LapNum_" + i, (int) e[55 + y]);
						Lap_Data_Packet.put("m_lapDistance_" + i, ByteBuffer.wrap(new byte[] {e[56 + y], e[57 + y], e[58 + y], e[59 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Lap_Data_Packet.put("m_totalDistance_" + i, ByteBuffer.wrap(new byte[] {e[60 + y], e[61 + y], e[62 + y], e[63 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Lap_Data_Packet.put("m_safetyCarDelta_" + i, ByteBuffer.wrap(new byte[] {e[64 + y], e[65 + y], e[66 + y], e[67 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						//moet nog in data gekeken worden welke nummering ze voor de posities gebruiken
						if (e[69 + y] == 00) {
							first_car = i;
						}
						Lap_Data_Packet.put("m_carPosition_" + i, (int) e[68 + y]);
						Lap_Data_Packet.put("m_currentLapNum_" + i, (int) e[69 + y]);
						Lap_Data_Packet.put("m_pitStatus_" + i, (int) e[70 + y]);
						Lap_Data_Packet.put("m_sector_" + i, (int) e[71 + y]);
						Lap_Data_Packet.put("m_currentLapInvalid_" + i, (int) e[72 + y]);
						Lap_Data_Packet.put("m_penalties_" + i, (int) e[73 + y]);
						Lap_Data_Packet.put("m_gridPosition_" + i, (int) e[74 + y]);
						Lap_Data_Packet.put("m_driverStatus_" + i, (int) e[75 + y]);
						Lap_Data_Packet.put("m_resultStatus_" + i, (int) e[76 + y]);
						//nog checken
					}
					
					Data_saves.Packet_store.Lap_Data_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Lap_Data_Packet.putAll(Lap_Data_Packet);
					Data_saves.Packet_store.Lap_Data_Packet_store.put(String.valueOf(Lap_Data_Packet_int), Lap_Data_Packet);
					Lap_Data_Packet_int = Lap_Data_Packet_int + 1;
					
					if (current_lap == (int) Lap_Data_Packet.get("m_currentLapNum_" + first_car)) {
					} else {
						current_lap = (int) Lap_Data_Packet.get("m_currentLapNum_" + first_car);
						int[] Write_encode_int = new int[] {Motion_Packet_int, Lap_Data_Packet_int, Car_Telemetry_Packet_int, Car_Status_Packet_int,};
						Thread Write_encode = new Thread(() -> {
							File_reader.Write_encoded.Main(Write_encode_int, participants);
						});
						Write_encode.start();
					}
				}
				
				if (e[5] == 03) {
					HashMap<String, Object> Event_Packet = new HashMap<String, Object>();
					Event_Packet.put("m_eventStringCode", new String(new byte[] {e[24], e[25], e[26], e[27]}));
					if (Event_Packet.get("m_eventStringCode") == "SSTA") {
						
					}
					if (Event_Packet.get("m_eventStringCode") == "SEND") {
											
										}
					if (Event_Packet.get("m_eventStringCode") == "FTLP") {
						Event_Packet.put("vehicleIdx_FTLP", (int) e[28]);
						Event_Packet.put("lapTime", ByteBuffer.wrap(new byte[] {e[29], e[30], e[31], e[32]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					}
					if (Event_Packet.get("m_eventStringCode") == "RTMT") {
						Event_Packet.put("vehicleIdx_RTMT", (int) e[28]);
					}
					if (Event_Packet.get("m_eventStringCode") == "DRSE") {
						
					}
					if (Event_Packet.get("m_eventStringCode") == "DRSD") {
						
					}
					if (Event_Packet.get("m_eventStringCode") == "TMPT") {
						Event_Packet.put("vehicleIdx_TMPT", (int) e[28]);
					}
					if (Event_Packet.get("m_eventStringCode") == "CHQF") {
						
					}
					if (Event_Packet.get("m_eventStringCode") == "RCWN") {
						Event_Packet.put("vehicleIdx_RCWN", (int) e[28]);
					}
					if (Event_Packet.get("m_eventStringCode") == "PENA") {
						Event_Packet.put("penaltyType", (int) e[28]);
						Event_Packet.put("infringementType", (int) e[29]);
						Event_Packet.put("vehicleIdx_PENA", (int) e[30]);
						Event_Packet.put("otherVehicleIdx", (int) e[31]);
						Event_Packet.put("time", (int) e[32]);
						Event_Packet.put("lapNum", (int) e[33]);
						Event_Packet.put("placesGained", (int) e[34]);
					}
					if (Event_Packet.get("m_eventStringCode") == "SPTP") {
						Event_Packet.put("vehicleIdx_SPTP", (int) e[28]);
						Event_Packet.put("speed", ByteBuffer.wrap(new byte[] {e[29], e[30], e[31], e[32]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					}
					
					Data_saves.Packet_store.Event_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Event_Packet.putAll(Event_Packet);
					Data_saves.Packet_store.Event_Packet_store.put(String.valueOf(Event_Packet_int), Event_Packet);
					Event_Packet_int = Event_Packet_int + 1;
					//nog een mechanisme maken voor opslaan
					//nog checken
				}
				
				if (e[5] == 04) {
					HashMap<String, Object> Participants_Packet = new HashMap<String, Object>();
					Participants_Packet.put("m_numActiveCars", (int) e[24]);
					for (int i = 0; i < (int) Participants_Packet.get("m_numActiveCars"); i++) {
						int y = i * 54;
						Participants_Packet.put("m_aiControlled_" + i, (int) e[25 + y]);
						Participants_Packet.put("m_driverId_" + i, (int) e[26 + y]);
						Participants_Packet.put("m_teamId_" + i, (int) e[27 + y]);
						Participants_Packet.put("m_raceNumber_" + i, (int) e[28 + y]);
						Participants_Packet.put("m_nationality_" + i, (int) e[29 + y]);
						Participants_Packet.put("m_name_" + i, new String(new byte[] {e[30 + y], e[31 + y], e[32 + y], e[33 + y], e[34 + y], e[35 + y], e[36 + y], e[37 + y], e[38 + y], e[39 + y], e[40 + y], e[41 + y], e[42 + y], 
								e[43 + y], e[44 + y], e[45 + y], e[46 + y], e[47 + y], e[48 + y], e[49 + y], e[50 + y], e[51 + y], e[52 + y], e[53 + y], e[54 + y], e[55 + y], e[56 + y], e[57 + y], e[58 + y], e[59 + y], e[60 + y], 
								e[61 + y], e[62 + y], e[63 + y], e[64 + y], e[65 + y], e[66 + y], e[67 + y], e[68 + y], e[69 + y], e[70 + y], e[71 + y], e[72 + y], e[73 + y], e[74 + y], e[75 + y], e[76 + y], e[77 + y]}));
						Participants_Packet.put("m_yourTelemetry_" + i, (int) e[78 + y]);
					}
					
					Data_saves.Packet_store.Participants_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Participants_Packet.putAll(Participants_Packet);
					Data_saves.Packet_store.Participants_Packet_store.put(String.valueOf(Participants_Packet_int), Participants_Packet);
					Participants_Packet_int = Participants_Packet_int + 1;
					//nog checken
			        if (Participants_Packet_int == 300) {
				    	final int participants_packet = Participants_Packet_int;
						Thread Session = new Thread(() -> {
							File_reader.Write_encoded.Participants(participants_packet);
						});
						Session.start();
			        }
			        
			        participants.put(String.valueOf(Participants_Packet_int), (int)e[24]);
				}
				
				if (e[5] == 05) {
					HashMap<String, Object> Car_Setups_Packet = new HashMap<String, Object>();
					for (int i = 0; i < 22; i++) {
						int y = i * 22;
						Car_Setups_Packet.put("m_frontWing_" + i, (int) e[24 + y]);
						Car_Setups_Packet.put("m_rearWing_" + i, (int) e[25 + y]);
						Car_Setups_Packet.put("m_onThrottle_" + i, (int) e[26 + y]);
						Car_Setups_Packet.put("m_offThrottle_" + i, (int) e[27 + y]);
						Car_Setups_Packet.put("m_frontCamber_", ByteBuffer.wrap(new byte[] {e[28 + y], e[29 + y], e[30 + y], e[31 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_rearCamber_", ByteBuffer.wrap(new byte[] {e[32 + y], e[33 + y], e[34 + y], e[35 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_frontToe_", ByteBuffer.wrap(new byte[] {e[36 + y], e[37 + y], e[38 + y], e[39 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_rearToe_", ByteBuffer.wrap(new byte[] {e[40 + y], e[41 + y], e[42 + y], e[43 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_frontSuspension_" + i, (int)e[44 + y]);
						Car_Setups_Packet.put("m_rearSuspension_" + i, (int) e[45 + y]);
						Car_Setups_Packet.put("m_frontAntiRollBar_" + i, (int) e[46 + y]);
						Car_Setups_Packet.put("m_rearAntiRollBar_" + i, (int) e[47 + y]);
						Car_Setups_Packet.put("m_frontSuspensionHeight_" + i, (int) e[48 + y]);
						Car_Setups_Packet.put("m_rearSuspensionHeight_" + i, (int) e[49 + y]);
						Car_Setups_Packet.put("m_brakePressure_" + i, (int) e[50 + y]);
						Car_Setups_Packet.put("m_brakeBias_" + i, (int) e[51 + y]);
						Car_Setups_Packet.put("m_rearLeftTyrePressure_", ByteBuffer.wrap(new byte[] {e[52 + y], e[53 + y], e[54 + y], e[55 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_rearRightTyrePressure_", ByteBuffer.wrap(new byte[] {e[56 + y], e[57 + y], e[58 + y], e[59 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_frontLeftTyrePressure_", ByteBuffer.wrap(new byte[] {e[60 + y], e[61 + y], e[62 + y], e[63 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_frontRightTyrePressure_", ByteBuffer.wrap(new byte[] {e[64 + y], e[65 + y], e[66 + y], e[67 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Setups_Packet.put("m_ballast_" + i, (int) e[68 + y]);
						Car_Setups_Packet.put("m_fuelLoad_" + i, (int)e[69 + y]);
					}
					
					Data_saves.Packet_store.Car_Setups_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Car_Setups_Packet.putAll(Car_Setups_Packet);
					Data_saves.Packet_store.Car_Setups_Packet_store.put(String.valueOf(Car_Setups_Packet_int), Car_Setups_Packet);
					Car_Setups_Packet_int = Car_Setups_Packet_int + 1;
					//nog checken
				}
				
				if (e[5] == 06) {
					HashMap<String, Object> Car_Telemetry_Packet = new HashMap<String, Object>();
					for (int i = 0; i < 22; i++) {
						int y = i * 58;
						Car_Telemetry_Packet.put("m_speed_" + i, (short)((e[25] & 0xFF) << 8) | (e[24] & 0xFF));
						Car_Telemetry_Packet.put("m_throttle_" + i, ByteBuffer.wrap(new byte[] {e[26 + y], e[27 + y], e[28 + y], e[29 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_steer_" + i, ByteBuffer.wrap(new byte[] {e[30 + y], e[31 + y], e[32 + y], e[33 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_brake_" + i, ByteBuffer.wrap(new byte[] {e[34 + y], e[35 + y], e[36 + y], e[37 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_clutch_" + i, (int) e[38 + y]);
						Car_Telemetry_Packet.put("m_gear_" + i, (int) e[39 + y]);
						Car_Telemetry_Packet.put("m_engineRPM_" + i, (short)((e[41] & 0xFF) << 8) | (e[40] & 0xFF));
						Car_Telemetry_Packet.put("m_drs_" + i, (int) e[42 + y]);
						Car_Telemetry_Packet.put("m_revLightsPercent_" + i, (int) e[43 + y]);
						Car_Telemetry_Packet.put("m_brakesTemperature_RL_" + i, (short)((e[45] & 0xFF) << 8) | (e[44] & 0xFF));
						Car_Telemetry_Packet.put("m_brakesTemperature_RR_" + i, (short)((e[47] & 0xFF) << 8) | (e[46] & 0xFF));
						Car_Telemetry_Packet.put("m_brakesTemperature_FL_" + i, (short)((e[49] & 0xFF) << 8) | (e[48] & 0xFF));
						Car_Telemetry_Packet.put("m_brakesTemperature_FR_" + i, (short)((e[51] & 0xFF) << 8) | (e[50] & 0xFF));
						Car_Telemetry_Packet.put("m_tyresSurfaceTemperature_RL_" + i, (int) e[52 + y]);
						Car_Telemetry_Packet.put("m_tyresSurfaceTemperature_RR_" + i, (int) e[53 + y]);
						Car_Telemetry_Packet.put("m_tyresSurfaceTemperature_FL_" + i, (int) e[54 + y]);
						Car_Telemetry_Packet.put("m_tyresSurfaceTemperature_FR_" + i, (int) e[55 + y]);
						Car_Telemetry_Packet.put("m_tyresInnerTemperature_RL_" + i, (int) e[56 + y]);
						Car_Telemetry_Packet.put("m_tyresInnerTemperature_RR_" + i, (int) e[57 + y]);
						Car_Telemetry_Packet.put("m_tyresInnerTemperature_FL_" + i, (int) e[58 + y]);
						Car_Telemetry_Packet.put("m_tyresInnerTemperature_FR_" + i, (int) e[59 + y]);
						Car_Telemetry_Packet.put("m_engineTemperature" + i, (short)((e[61] & 0xFF) << 8) | (e[60] & 0xFF));
						Car_Telemetry_Packet.put("m_tyresPressure_RL_" + i, ByteBuffer.wrap(new byte[] {e[62 + y], e[63 + y], e[64 + y], e[65 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_tyresPressure_RR_" + i, ByteBuffer.wrap(new byte[] {e[66 + y], e[67 + y], e[68 + y], e[69 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_tyresPressure_FL_" + i, ByteBuffer.wrap(new byte[] {e[70 + y], e[71 + y], e[72 + y], e[73 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_tyresPressure_FR_" + i, ByteBuffer.wrap(new byte[] {e[74 + y], e[75 + y], e[76 + y], e[77 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Telemetry_Packet.put("m_surfaceType_RL_" + i, (int) e[78 + y]);
						Car_Telemetry_Packet.put("m_surfaceType_RR_" + i, (int) e[79 + y]);
						Car_Telemetry_Packet.put("m_surfaceType_FL_" + i, (int) e[80 + y]);
						Car_Telemetry_Packet.put("m_surfaceType_FR_" + i, (int) e[81 + y]);
					}
					Car_Telemetry_Packet.put("m_buttonStatus", (int)((e[1300] & 0xFF) << 24) | ((e[1301] & 0xFF) << 16) | ((e[1302] & 0xFF) << 8) | ((e[1303] & 0xFF) << 0));
					Car_Telemetry_Packet.put("m_mfdPanelIndex", (int) e[1304]);
					Car_Telemetry_Packet.put("m_mfdPanelIndexSecondaryPlayer", (int) e[1305]);
					Car_Telemetry_Packet.put("m_suggestedGear", (int) e[1306]);
					
					Data_saves.Packet_store.Car_Telemetry_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Car_Telemetry_Packet.putAll(Car_Telemetry_Packet);
					Data_saves.Packet_store.Car_Telemetry_Packet_store.put(String.valueOf(Car_Telemetry_Packet_int), Car_Telemetry_Packet);
					Car_Telemetry_Packet_int = Car_Telemetry_Packet_int + 1;
					//nog checken
				}
				
				if (e[5] == 07) {
					HashMap<String, Object> Car_Status_Packet= new HashMap<String, Object>();
					for (int i = 0; i < 22; i++) {
						int y = i * 60;
						Car_Status_Packet.put("m_tractionControl_" + i, (int) e[24 + y]);
						Car_Status_Packet.put("m_antiLockBrakes_" + i, (int) e[25 + y]);
						Car_Status_Packet.put("m_fuelMix_" + i, (int) e[26 + y]);
						Car_Status_Packet.put("m_frontBrakeBias_" + i, (int) e[27 + y]);
						Car_Status_Packet.put("m_pitLimiterStatus_" + i, (int) e[28 + y]);
						Car_Status_Packet.put("m_fuelInTank_" + i, ByteBuffer.wrap(new byte[] {e[29 + y], e[30 + y], e[31 + y], e[32 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Status_Packet.put("m_fuelCapacity_" + i, ByteBuffer.wrap(new byte[] {e[33 + y], e[34 + y], e[35 + y], e[36 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Status_Packet.put("m_fuelRemainingLaps_" + i, ByteBuffer.wrap(new byte[] {e[37 + y], e[38 + y], e[39 + y], e[40 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Status_Packet.put("m_maxRPM_" + i, (short) ((e[42 + y] & 0xFF) << 8) | ((e[41] & 0xFF) << 0));
						Car_Status_Packet.put("m_idleRPM_" + i, (short) ((e[44 + y] & 0xFF) << 8) | ((e[43] & 0xFF) << 0));
						Car_Status_Packet.put("m_maxGears_" + i, (int) e[45 + y]);
						Car_Status_Packet.put("m_drsAllowed_" + i, (int) e[46 + y]);
						Car_Status_Packet.put("m_drsActivationDistance_" + i, ((e[48 + y] & 0xFF) << 8) | ((e[47] & 0xFF) << 0));
						Car_Status_Packet.put("m_tyresWear_RL_" + i, (int) e[49 + y]);
						Car_Status_Packet.put("m_tyresWear_RR_" + i, (int) e[50 + y]);
						Car_Status_Packet.put("m_tyresWear_FL_" + i, (int) e[51 + y]);
						Car_Status_Packet.put("m_tyresWear_FR_" + i, (int) e[52 + y]);
						Car_Status_Packet.put("m_actualTyreCompound_" + i, (int) e[53 + y]);
						Car_Status_Packet.put("m_visualTyreCompound_" + i, (int) e[54 + y]);
						Car_Status_Packet.put("m_tyresAgeLaps_" + i, (int) e[55 + y]);
						Car_Status_Packet.put("m_tyresDamage_RL_" + i, (int) e[56 + y]);
						Car_Status_Packet.put("m_tyresDamage_RR_" + i, (int) e[57 + y]);
						Car_Status_Packet.put("m_tyresDamage_FL_" + i, (int) e[58 + y]);
						Car_Status_Packet.put("m_tyresDamage_FR_" + i, (int) e[59 + y]);
						Car_Status_Packet.put("m_frontLeftWingDamage_" + i, (int) e[60 + y]);
						Car_Status_Packet.put("m_frontRightWingDamage_" + i, (int) e[61 + y]);
						Car_Status_Packet.put("m_rearWingDamage_" + i, (int) e[62 + y]);
						Car_Status_Packet.put("m_drsFault_" + i, (int) e[63 + y]);
						Car_Status_Packet.put("m_engineDamage_" + i, (int) e[64 + y]);
						Car_Status_Packet.put("m_gearBoxDamage_" + i, (int) e[65 + y]);
						Car_Status_Packet.put("m_vehicleFiaFlags_" + i, (int) e[66 + y]);
						Car_Status_Packet.put("m_ersStoreEnergy_" + i, ByteBuffer.wrap(new byte[] {e[67 + y], e[68 + y], e[69 + y], e[70 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Status_Packet.put("m_ersDeployMode_" + i, (int) e[71 + y]);
						Car_Status_Packet.put("m_ersHarvestedThisLapMGUK_" + i, ByteBuffer.wrap(new byte[] {e[72 + y], e[73 + y], e[74 + y], e[75 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Status_Packet.put("m_ersHarvestedThisLapMGUH_" + i, ByteBuffer.wrap(new byte[] {e[76 + y], e[77 + y], e[78 + y], e[79 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Car_Status_Packet.put("m_ersDeployedThisLap_" + i, ByteBuffer.wrap(new byte[] {e[80 + y], e[81 + y], e[82 + y], e[83 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					}
					
					Data_saves.Packet_store.Car_Status_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Car_Status_Packet.putAll(Car_Status_Packet);
					Data_saves.Packet_store.Car_Status_Packet_store.put(String.valueOf(Car_Status_Packet_int), Car_Status_Packet);
					Car_Status_Packet_int = Car_Status_Packet_int + 1;
					//nog checken
				}
				
				if (e[5] == 8) {
					HashMap<String, Object> Final_Classification_Packet = new HashMap<String, Object>();
					Final_Classification_Packet.put("m_numCars", (int) e[24]);
					for (int i = 0; i < (int)Final_Classification_Packet.get("m_numCars"); i++) {
						int y = i * 37;
						Final_Classification_Packet.put("m_position_" + i, (int) e[25 + y]);
						Final_Classification_Packet.put("m_numLaps_" + i, (int) e[26 + y]);
						Final_Classification_Packet.put("m_gridPosition_" + i, (int) e[27 + y]);
						Final_Classification_Packet.put("m_points_" + i, (int) e[28 + y]);
						Final_Classification_Packet.put("m_numPitStops_" + i, (int) e[29 + y]);
						Final_Classification_Packet.put("m_resultStatus_" + i, (int) e[30 + y]);
						Final_Classification_Packet.put("m_bestLapTime_" + i, ByteBuffer.wrap(new byte[] {e[31 + y], e[32 + y], e[33 + y], e[34 + y]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
						Final_Classification_Packet.put("m_totalRaceTime_", ByteBuffer.wrap(new byte[] {e[35 + y], e[36 + y], e[37 + y], e[38 + y], e[39 + y], e[40 + y], e[41 + y], e[42 + y]}).order(ByteOrder.LITTLE_ENDIAN).getDouble());
						Final_Classification_Packet.put("m_penaltiesTime_" + i, (int) e[43 + y]);
						Final_Classification_Packet.put("m_numPenalties_" + i, (int) e[44 + y]);
						Final_Classification_Packet.put("m_numTyreStints_" + i, (int) e[45 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_1_" + i, (int) e[46 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_2_" + i, (int) e[47 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_3_" + i, (int) e[48 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_4_" + i, (int) e[49 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_5_" + i, (int) e[50 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_6_" + i, (int) e[51 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_7_" + i, (int) e[52 + y]);
						Final_Classification_Packet.put("m_tyreStintsActual_8_" + i, (int) e[53 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_1_" + i, (int) e[54 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_2_" + i, (int) e[55 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_3_" + i, (int) e[56 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_4_" + i, (int) e[57 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_5_" + i, (int) e[58 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_6_" + i, (int) e[59 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_7_" + i, (int) e[60 + y]);
						Final_Classification_Packet.put("m_tyreStintsVisual_8_" + i, (int) e[61 + y]);
					}
					
					Data_saves.Packet_store.Final_Classification_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Final_Classification_Packet.putAll(Final_Classification_Packet);
					Data_saves.Packet_store.Final_Classification_Packet_store.put(String.valueOf(Final_Classification_Packet_int), Final_Classification_Packet);
					Final_Classification_Packet_int = Final_Classification_Packet_int + 1;
					//nog checken
				}
				
				if (e[5] == 9) {
					HashMap<String, Object> Lobby_Info_Packet = new HashMap<String, Object>();
					Lobby_Info_Packet.put("m_numPlayers", (int) e[24]);
					for (int i = 0; i < (int) Lobby_Info_Packet.get("m_numPlayers"); i++) {
						int y = i * 52;
						Lobby_Info_Packet.put("m_aiControlled_" + i, (int) e[25 + y]);
						Lobby_Info_Packet.put("m_teamId_" + i, (int) e[26 + y]);
						Lobby_Info_Packet.put("m_nationality_" + i, (int) e[27 + y]);
						Lobby_Info_Packet.put("m_name_" + i, new String(new byte[] {e[28 + y], e[29 + y], e[30 + y], e[31 + y], e[32 + y], e[33 + y], e[34 + y], e[35 + y], e[36 + y], e[37 + y], e[38 + y], e[39 + y], e[40 + y], 
								e[41 + y], e[42 + y], e[43 + y], e[44 + y], e[45 + y], e[46 + y], e[47 + y], e[48 + y], e[49 + y], e[50 + y], e[51 + y], e[52 + y], e[53 + y], e[54 + y], e[55 + y], e[56 + y], e[57 + y], e[58 + y], 
								e[59 + y], e[60 + y], e[61 + y], e[62 + y], e[63 + y], e[64 + y], e[65 + y], e[66 + y], e[67 + y], e[68 + y], e[69 + y], e[70 + y], e[71 + y], e[72 + y], e[73 + y], e[74 + y], e[75 + y]}));
						Lobby_Info_Packet.put("m_readyStatus_" + i, (int) e[76 + y]);
					}
					
					Data_saves.Packet_store.Lobby_Info_Packet_Header.putAll(Header);
					Data_saves.Packet_store.Lobby_Info_Packet.putAll(Lobby_Info_Packet);
					Data_saves.Packet_store.Lobby_Info_Packet_store.put(String.valueOf(Lobby_Info_Packet_int), Lobby_Info_Packet);
					Lobby_Info_Packet_int = Lobby_Info_Packet_int + 1;
					//nog checken
				}
			}
		socket.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}