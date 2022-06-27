package Inkoming;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import File_reader.Names;
import application.ComparisonPage;
import application.Save_file;
import data_compute.Historical_graph_data;
import data_compute.Historical_lap_data;
import data_compute.delta;
import file_system.L1;
import file_system.data_manager;
import javafx.application.Platform;
import java.nio.ByteBuffer;

public class Packet_recieve {
	public static boolean recieve_on;
	public static String[] first_frameIdentifier_name = new String[] {"Car_status", "car_telemetry", "lap_data", "motion"};
	public static int Player_lap = 0;
	public static ExecutorService service = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
	public static int queue_size = 0;
	public static void recieve_class() {
		boolean[] first_packet = new boolean[] {true, true, true, true};
		int[] packetid = new int[] {7, 6, 2, 0};
		try {
			DatagramSocket socket = new DatagramSocket(Settings.Settings_var.Poort); //var
			while (recieve_on == true) {
				byte[] test = new byte[2000];
				DatagramPacket response = new DatagramPacket(test, test.length);
				socket.receive(response);
				
				byte[] e = new byte[response.getLength()];
				for (int i = 0; i < response.getLength(); i++) {
					e[i] = test[i];
				}
				
//		        for (byte b : e) {
//		            String st = String.format("%02X ", b);
//		            System.out.print(st);
//		        }
//		        System.out.println("\n");

		        HashMap<String, Object> Header = new HashMap<String, Object>();
				Header.put("packetFormat", (short)((e[1] & 0xFF) << 8) | (e[0] & 0xFF));
				Header.put("gameMajorVersion", (byte) e[2]);
				Header.put("gameMinorVersion", (byte) e[3]);
				Header.put("packetVersion", (byte) e[4]);
				Header.put("packetId", (byte) e[5]);
				Header.put("sessionUID", (long)((e[13] & 0xFFL) << 56) | ((e[12] & 0xFFL) << 48) | ((e[11] & 0xFFL) << 40) | ((e[10] & 0xFFL) << 32) | ((e[9] & 0xFFL) << 24) | ((e[8] & 0xFFL) << 16) | ((e[7] & 0xFFL) << 8) | ((e[6] & 0xFFL) << 0));
				Header.put("sessionTime", ByteBuffer.wrap(new byte[] {e[14], e[15], e[16], e[17]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
				Header.put("frameIdentifier", (int)(((e[21] & 0xFF) << 24) | ((e[20] & 0xFF) << 16) | ((e[19] & 0xFF) << 8) | ((e[18] & 0xFF) << 0)));
				Header.put("playerCarIndex", (byte) e[22]);
				Header.put("secondaryPlayerCarIndex", (byte) e[23]);
				
				for (int i = 0; i < 4; i++) {
					if (first_packet[i] == true && (byte) Header.get("packetId") == (byte) packetid[i]) {
						int frameIdentifier = (int) Header.get("frameIdentifier") - 1;
						L1.class.getField("frameIdentifier_" + first_frameIdentifier_name[i]).set(frameIdentifier, frameIdentifier);
						first_packet[i] = false;
					}
				}

				HashMap<String, Object> Data_decode = new HashMap<String, Object>();
				byte PacketId = (byte) Header.get("packetId");
				if (PacketId == 1) { //session packet 
					for (int i = 0; i < Names.Packet_names.get(Names.File_Path[PacketId]).size() ; i++) {
						byte ofset = 0;
						if (Data_decode.get("m_numMarshalZones") != null) {
							ofset = (byte) ((byte) Data_decode.get("m_numMarshalZones") * 5);
						}
						int[] array_places = Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(i);
						if (Names.Packet_names.get(Names.File_Path[PacketId]).get(i).endsWith("_")) {
							if (i == 16 || i == 17) {
								for (int o = 0; o < (byte) Data_decode.get("m_numMarshalZones"); o++) {
									byte ofset2 = (byte) (5 * o);
									if (array_places.length == 1) {
										Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, (byte) e[array_places[0] + ofset2]);
									} else if (array_places.length == 4) {
										Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset2], e[array_places[1] + ofset2], e[array_places[2] + ofset2], e[array_places[3] + ofset2]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
									}
								}
							} else {
								for (int o = 0; o < (byte) Data_decode.get("m_numWeatherForecastSamples"); o++) {
									byte ofset2 = (byte) (5 * o + ofset);
									Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, (byte) e[array_places[0] + ofset2]);
								}
							}
						} else {
							if (array_places.length == 1) {
								Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i), (byte) e[array_places[0] + ofset]);
							} else if (array_places.length == 2) {
								Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i), ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset], e[array_places[1] + ofset]}).order(ByteOrder.LITTLE_ENDIAN).getShort());
							}
						}
					}
				} else if (PacketId == 3) { //event packet
					int[] array_places = Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(0);
					Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(0), new String(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}));
					String Infringment = Names.Packet_names.get(Names.File_Path[PacketId]).get(0);
					if (Infringment == "PENA") {
						for (int i = 0; i < 7; i++) {
							Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(6 + i), (int) e[Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(6 + i)[0]]);
						}
					} else if (Infringment == "FTLP") {
						Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(1), (int) e[Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(2)[0]]);
						array_places = Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(2);
						Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(2), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					} else if (Infringment == "SPTP") {
						Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(13), (int) e[Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(13)[0]]);
						array_places = Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(14);
						Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(14), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
					}
					String[] Infringment_type = {"RTMT", "TMPT", "RCWN"};
					for (int i = 0; i < 3; i++) {
						if (Infringment == Infringment_type[i]) {
							Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(3 + i), (int) e[Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(3 + i)[0]]);
						}
					}					
				} else if (PacketId < 10) {
					for (int i = 0; i <Names. Packet_names.get(Names.File_Path[PacketId]).size(); i++) {
						int Data_type = Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(i).length;
						int[] array_places = Names.Packet_byte_array.get(Names.File_Path[PacketId]).get(i);
						if (Names.Packet_names.get(Names.File_Path[PacketId]).get(i).endsWith("_")) {
							for (int o = 0; o < 22; o++) {
								int ofset = Names.repeats[PacketId] * o;
								if (Data_type == 1) {
									Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, (byte) e[array_places[0] + ofset]);
								} else if (Data_type == 2) {
									Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset], e[array_places[1] + ofset]}).order(ByteOrder.LITTLE_ENDIAN).getShort());
								} else if (Data_type == 4) {
									Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, ByteBuffer.wrap(new byte[] {e[array_places[0] + ofset], e[array_places[1] + ofset], e[array_places[2] + ofset], e[array_places[3] + ofset]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
								} else if (Data_type == 48) {
									byte[] byte_temp_save = new byte[48];
									for (int p = 0; p < 48; p++) {
										byte_temp_save[p] = e[array_places[p] + ofset];
									}
									Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i) + o, new String(byte_temp_save, StandardCharsets.UTF_8));
								}
							}
						} else {
							if (Data_type == 1) {
								Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i), (byte) e[array_places[0]]);
							} else if (Data_type == 2) {
								Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[0]]}).order(ByteOrder.LITTLE_ENDIAN).getShort());
							} else if (Data_type == 4) {
								Data_decode.put(Names.Packet_names.get(Names.File_Path[PacketId]).get(i), ByteBuffer.wrap(new byte[] {e[array_places[0]], e[array_places[1]], e[array_places[2]], e[array_places[3]]}).order(ByteOrder.LITTLE_ENDIAN).getFloat());
							}
						}
					}
				}
				HashMap<String, Object> Needed_data = new HashMap<String, Object>();
				for (int i = 0; i < Names.Needed_data_names.length; i++) {
					if (Names.Needed_data_names[i].endsWith("_")) {
						if (Data_decode.get("m_" + Names.Needed_data_names[i] + "1") != null) {
							for (int o = 0; o < 22; o++) {
								Needed_data.put(Names.Needed_data_names[i] + o, Data_decode.get("m_" + Names.Needed_data_names[i] + o));
							}
						}
					} else {
						if (Data_decode.get("m_" + Names.Needed_data_names[i]) != null) {
							Needed_data.put(Names.Needed_data_names[i], Data_decode.get("m_" + Names.Needed_data_names[i]));
						}
					}
				}
				data_manager.data(Needed_data, PacketId, (float) Header.get("sessionTime"), (int) Header.get("frameIdentifier"));
				
				if (PacketId == 2) {
					for(int i = 0; i < L1.numActiveCars; i++) {
	    				L1.car_positions.put((byte) (L1.carPosition[i] - 1), (byte)i);
	    			}
					for (int i = 0; i < 22; i++) {
						delta.speed_of_players((float) Data_decode.get("m_lapDistance_" + i), i);
						if (Historical_lap_data.lap_num[i] != (byte)Data_decode.get("m_currentLapNum_" + i) && (byte) Data_decode.get("m_currentLapNum_" + i) != (byte)0) {
							Historical_lap_data.Lap_and_S3((byte) Data_decode.get("m_currentLapNum_" + i), i);
							final int p = i;
							Platform.runLater(new Runnable() {
							    @Override
							    public void run() {
							    	for (int o = 0; o < 24; o++) {
										try {
											ComparisonPage.series[p][o].getData().clear();
										} catch (Exception t) {
											t.printStackTrace();
										}
									}
							    	Historical_graph_data.laatste_percentage[p] = 0;
							    }
							});
						}
						Historical_lap_data.lap_num[i] = (byte) Data_decode.get("m_currentLapNum_" + i);
					}
					Historical_graph_data.percentage();
				} else if (PacketId == 1) {
					delta.trackLength = (Short) Data_decode.get("m_trackLength");
				} else if (PacketId == 7) {
					Historical_graph_data.data();
				} else if (PacketId == 3) {
					if (Data_decode.get("m_eventStringCode").equals("SEND")) {
						Save_file.display("Save File");
					}
				}
			}
			socket.close();
		} catch (Exception e) {
		}
	}
}