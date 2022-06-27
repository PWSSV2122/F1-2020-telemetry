package decode;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import File_reader.Names;
import Settings.Settings_var;
import application.ComparisonPage;
import data_compute.Historical_graph_data;
import data_compute.Historical_lap_data;
import data_compute.delta;
import file_system.L1;
import javafx.application.Platform;

public class data_to_file_system {
	
	public static Boolean play_save = false;
	public static Boolean end_of_file = false;
	final static int[] packets = new int[] {20, Settings_var.send_rate * 10, Settings_var.send_rate * 10,
			Settings_var.send_rate * 10, Settings_var.send_rate * 10, 2, 20};
	static int cycles = 0;
	
	private static HashMap <String, HashMap<Integer, HashMap<String, Object>>> data = new HashMap <String, HashMap<Integer, HashMap<String, Object>>>();
	static public void decoded_data_manager(String Save) {
	   data.putAll(data_decoder.decode(Save));
		while (play_save == true) {
			if (end_of_file == false) {
				HashMap <String, HashMap<Integer, HashMap<String, Object>>> data2 = new HashMap <String, HashMap<Integer, HashMap<String, Object>>>();
				for (int i = 0; i < 7; i++) {
					data_decoder.tot_packet[i] = data_decoder.tot_packet[i] + packets[i];
				}
				Thread getdata = new Thread(() -> {
			        data2.putAll(data_decoder.decode(Save));
			    });
				
			    Thread Car_Setups = new Thread(() -> {
			    	data_inject(5, "Car_Setups", 500, 0);
			    });

			    Thread Car_Status = new Thread(() -> {
			    	data_inject(7, "Car_Status", 1000 / Settings_var.send_rate, 1);
			    });
			    
			    Thread Car_Telemetry = new Thread(() -> {
			    	data_inject(6, "Car_Telemetry", 1000 / Settings_var.send_rate, 2);
			    });
			    
			    Thread Lap_Data = new Thread(() -> {
			    	data_inject(2, "Lap_Data", 1000 / Settings_var.send_rate, 3);
			    });
			    
			    Thread Motion = new Thread(() -> {
			    	data_inject(0, "Motion", 1000 / Settings_var.send_rate, 4);
			    });
			    
			    Thread Participants = new Thread(() -> {
			    	data_inject(4, "Participants", 500, 5);
			    });
			    
			    Thread Session = new Thread(() -> {
			    	data_inject(1, "Session", 500, 6);
			    });
			    
			    getdata.start();
			    Car_Setups.start();
			    Car_Status.start();
			    Car_Telemetry.start();
			    Lap_Data.start();
			    Motion.start();
			    Participants.start();
			    Session.start();
			    
			    try {
			    	getdata.join();
					Car_Setups.join();
				    Car_Status.join();
				    Car_Telemetry.join();
				    Lap_Data.join();
				    Motion.join();
				    Participants.join();
				    Session.join();
				} catch (InterruptedException e) {
				}
			    cycles++;
			    data.putAll(data2);
			}
		}
	}
	
	private static void data_inject(int pakket_int, String pakket_name, long wait, int a) {
		try {
			String[] PacketID_name = new String[] {"Motion", "Session", "Lap_Data", "Event", "Participants", "Car_Setups", "Car_Telemetry", "Car_Status", "Final_Classificationt", "Lobby_Info", "Needed"};
			String[] data_names = Names.Needed_data_packet.get(PacketID_name[pakket_int]);
			int stop = 0;
			for (int i = 0; i < 30; i++) {
				if (data_names[i] != null) {
					stop++;
				}
			}
			
			for (int i = cycles * packets[a]; i < (cycles + 1) * packets[a]; i++) {
				long start  = System.currentTimeMillis();
				if (data.get(PacketID_name[pakket_int]).get(i) != null) {
					for (int o = 0; o < stop; o++) {
						if (data_names[o].endsWith("_")) {
							String Name_l1 = data_names[o].substring(0, data_names[o].length() - 1);
							Object L1 = L1.class.getField(Name_l1).get(1);
							for (int p = 0 ; p < 22; p++) {
								if (L1 instanceof byte[]) {
									byte[] byte_type = (byte[]) L1;
									try {
										byte_type[p] = (byte) data.get(PacketID_name[pakket_int]).get(i).get(Name_l1 + "_" + p);
										L1.class.getField(Name_l1).set(byte_type, byte_type);
									} catch (Exception e) {
									}
								} else if (L1 instanceof float[]) {
									float[] float_type = (float[]) L1;
									try {
										float_type[p] = (float) data.get(PacketID_name[pakket_int]).get(i).get(Name_l1 + "_" + p);
										L1.class.getField(Name_l1).set(float_type, float_type);
									} catch (Exception e) {
									}
								} else if (L1 instanceof short[]) {
									short[] double_type = (short[]) L1;
									try {
										double_type[p] = (short) data.get(PacketID_name[pakket_int]).get(i).get(Name_l1 + "_" + p);
										L1.class.getField(Name_l1).set(double_type, double_type);
									} catch (Exception e) {
									}
								} else if (L1 instanceof String[]) {
									String[] String_type = (String[]) L1;
									try {
										String_type[p] = (String) data.get(PacketID_name[pakket_int]).get(i).get(Name_l1 + "_" + p);
										L1.class.getField(Name_l1).set(String_type, String_type);
									} catch (Exception e) {
									}
								}
							}
						} else {
							String Name_l1 =  data_names[o];
							Object L1 = L1.class.getField(Name_l1).get(1);
							if (L1 instanceof Byte) {
								L1.class.getField(Name_l1).set(Name_l1, (byte)data.get(PacketID_name[pakket_int]).get(i).get(Name_l1));
							} else if (L1 instanceof Short) {
								L1.class.getField(Name_l1).set(Name_l1, (Short)data.get(PacketID_name[pakket_int]).get(i).get(Name_l1));
							}
						}
					}
				}
				if (pakket_int == 2) {
					for(int o = 0; o < L1.numActiveCars; o++) {
	    				L1.car_positions.put((byte) (L1.carPosition[o] - 1), (byte)o);
	    			}
					for (int o = 0; o < 22; o++) {
						delta.speed_of_players((float) L1.lapDistance[o], o);
						if (Historical_lap_data.lap_num[o] != L1.currentLapNum[o] && (byte) L1.currentLapNum[o] != (byte)0) {
							Historical_lap_data.Lap_and_S3((byte) L1.currentLapNum[o], o);
							final int p = o;
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
						Historical_lap_data.lap_num[o] = (byte) L1.currentLapNum[o];
					}
					Historical_graph_data.percentage();
				} else if (pakket_int == 1) {
					delta.trackLength = L1.trackLength;
				} else if (pakket_int == 7) {
					Historical_graph_data.data();
				}
				long stop_time = System.currentTimeMillis();
		        long sleep_time = wait - (stop_time - start);
		        try {
					TimeUnit.MILLISECONDS.sleep(sleep_time);
				} catch (InterruptedException e) {
				}
			}
		} catch (Exception e) {
		}
	}
}
