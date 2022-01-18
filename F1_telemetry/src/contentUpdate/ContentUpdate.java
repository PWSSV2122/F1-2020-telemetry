package contentUpdate;

import java.security.KeyStore.Entry.Attribute;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.LapTimePage;
import application.LapTimePage.Tabel_object;
import application.TimingPage;
import data_compute.Historical_lap_data;
import data_compute.delta;
import file_system.L1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.util.Callback;

public class ContentUpdate {
	protected static final TableColumnHeader columnHeader = null;
	public static boolean TimingPage_refresh = false;
	public static boolean LapTime_refresh = false;
	public static boolean Graph_refresh = false;
	public static boolean Comparison_refresh = false;
	public static boolean SetupBrakes_refresh = false;
	public static boolean Track_refresh = false;
	static int delta_refresh = 0;
	public static int TimingPage_car = 0;
	
	public static void Update() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		        if (TimingPage_refresh == true) {
		        	final ObservableList<TimingPage.Tabel_object> data = FXCollections.observableArrayList();
	    			for(int i = 0; i < L1.numActiveCars; i++) {
	    				L1.car_positions.put((byte) (L1.carPosition[i] - 1), (byte)i);
	    			}

		    		for (int i = 0; i < L1.numActiveCars; i++) {	
		    			int position_index = 0;
		    			try {
		    				position_index = L1.car_positions.get((byte)i);
		    			} catch (Exception e) {
		    			}
		    			String[] Sector = new String[3];
		    			if (L1.sector1TimeInMS[position_index] == 0) {
		    				Sector[0] = TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[position_index] * 1000), 0);
		    				Sector[1] = "00:000";
		    				Sector[2] = "00:000";
		    			} else if (L1.sector2TimeInMS[position_index] == 0) {
		    				Sector[0] = TimingPage.MsTo_min_sec_ms(Math.round(L1.sector1TimeInMS[position_index]), 1);
		    				if ((L1.currentLapTime[position_index] * 1000) - L1.sector1TimeInMS[position_index] < 0) {
		    					Sector[1] = "error";
		    				} else {
		    					Sector[1] = TimingPage.MsTo_min_sec_ms(Math.round(((L1.currentLapTime[position_index] * 1000) - L1.sector1TimeInMS[position_index])), 0);
		    				}
		    				Sector[2] = "00:000";
		    			} else {
		    				Sector[0] = TimingPage.MsTo_min_sec_ms(Math.round(L1.sector1TimeInMS[position_index]), 1);
		    				Sector[1] = TimingPage.MsTo_min_sec_ms(Math.round(L1.sector2TimeInMS[position_index]), 1);
		    				if ((L1.currentLapTime[position_index] * 1000) - (L1.sector1TimeInMS[position_index]) - (L1.sector2TimeInMS[position_index]) < 0) {
		    					Sector[2] = "error";
		    				} else {
		    					Sector[2] = TimingPage.MsTo_min_sec_ms(Math.round(Math.round(((L1.currentLapTime[position_index] * 1000) - (L1.sector1TimeInMS[position_index]) - (L1.sector2TimeInMS[position_index])))), 1);
		    				}
		    			}
		    			delta.delta_time();

		    			String delta = "0:000";
		    			if (String.valueOf(L1.Delta.get(position_index)) != "null") {
		    				delta = String.valueOf(L1.Delta.get(i));
		    			}
		    			data.add( new TimingPage.Tabel_object(
		    					"P" + String.valueOf(i + 1),															//position
		    					L1.name[position_index],																//player name
		    					TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[position_index] * 1000), 0),	//current time
		    					Sector[0],																				//current S1 time
		    					Sector[1],																				//current S2 time
		    					Sector[2],																				//current S3 time
		    					TimingPage.MsTo_min_sec_ms(Math.round(L1.lastLapTime[position_index] * 1000), 0),		//last lap time
		    					TimingPage.Tyre(L1.actualTyreCompound[position_index]),									//witch tyre
		    					TimingPage.pit(L1.pitStatus[position_index]),											//pit status
		    					String.valueOf(L1.penalties[position_index]),											//penalties
		    					"+" + delta));																			//delta
		    		}
		    		TimingPage.Tabel.getItems().clear();
		    		TimingPage.Tabel.setItems(data);
		    		TimingPage.Tabel.refresh();
		    		//System.out.println(TimingPage.MsTo_min_sec_ms(Math.round(L1.lastLapTime[0] * 1000), 0));
		        } else if (LapTime_refresh == true) {
		        	try {
			        	final ObservableList<LapTimePage.Tabel_object> data = FXCollections.observableArrayList();
			        	String[] Sector = new String[3];
			        	if (L1.sector1TimeInMS[TimingPage_car] == 0) {
			    			Sector[0] = TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[TimingPage_car] * 1000), 1);
			    			Sector[1] = "00:000";
			    			Sector[2] = "00:000";
			    		} else if (L1.sector2TimeInMS[TimingPage_car] == 0) {
			    			Sector[0] = TimingPage.MsTo_min_sec_ms(L1.sector1TimeInMS[TimingPage_car], 1);
			    			if ((L1.currentLapTime[TimingPage_car] * 1000) - L1.sector1TimeInMS[TimingPage_car] < 0) {
			    				Sector[1] = "error";
			    			} else {
			    				Sector[1] = TimingPage.MsTo_min_sec_ms(Math.round(((L1.currentLapTime[TimingPage_car] * 1000) - L1.sector1TimeInMS[TimingPage_car])), 0);
			    			}
			    			Sector[2] = "00:000";
			    		} else {
			    			Sector[0] = TimingPage.MsTo_min_sec_ms(L1.sector1TimeInMS[TimingPage_car], 1);
			    			Sector[1] = TimingPage.MsTo_min_sec_ms(L1.sector2TimeInMS[TimingPage_car], 1);
			    			if ((L1.currentLapTime[TimingPage_car] * 1000) - (L1.sector1TimeInMS[TimingPage_car]) - (L1.sector2TimeInMS[TimingPage_car]) < 0) {
			    				Sector[2] = "error";
			    			} else {
			    				Sector[2] = TimingPage.MsTo_min_sec_ms(Math.round(((L1.currentLapTime[TimingPage_car] * 1000) - (L1.sector1TimeInMS[TimingPage_car]) - (L1.sector2TimeInMS[TimingPage_car]))), 1);
			    			}
			    		}
			        	//String test = TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[TimingPage_car] * 1000), 0);
		        		//System.out.println(TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[TimingPage_car] * 1000), 0));
			        	data.add(new LapTimePage.Tabel_object(
		        				String.valueOf(Historical_lap_data.lap_num[TimingPage_car]),
		        				TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[TimingPage_car] * 1000), 0),	//lap time
		        				Sector[0],
		        				Sector[1],
		        				Sector[2])); //s3 time
			        	
			        	LapTimePage.Tabel.getItems().clear();
			        	LapTimePage.Tabel.setItems(data);
			        	
			        	for (int i = Historical_lap_data.starting_lap; i < Historical_lap_data.lap_num[TimingPage_car]; i++) {
			        		System.out.println(i);
			        		data.add(new LapTimePage.Tabel_object(
			        				String.valueOf(Historical_lap_data.lap_num[TimingPage_car] - i),
			        				TimingPage.MsTo_min_sec_ms(Math.round(L1.Lap_Times.get(TimingPage_car).get(Historical_lap_data.lap_num[TimingPage_car] - i) * 1000), 0),	//lap time
			        				TimingPage.MsTo_min_sec_ms(L1.S1_Times.get(TimingPage_car).get(Historical_lap_data.lap_num[TimingPage_car] - i), 1),	//s1 time
			        				TimingPage.MsTo_min_sec_ms(L1.S2_Times.get(TimingPage_car).get(Historical_lap_data.lap_num[TimingPage_car] - i), 1),	//s2 time
			        				L1.S3_Times.get(TimingPage_car).get(i))); //s3 time
			        		LapTimePage.Tabel.setItems(data);
			        	}

			        	LapTimePage.Lap.setSortType(TableColumn.SortType.ASCENDING);
//			        	LapTimePage.Tabel.setItems(data);
//			        	LapTimePage.Tabel.refresh();
			        	//System.out.println("lap times updated");	
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	}
		        }
		    }
		};
		ScheduledExecutorService content_update = Executors.newScheduledThreadPool(1);
		content_update.scheduleAtFixedRate(updateClass, 0, 700, TimeUnit.MILLISECONDS);
	}
	
	public static void dropdown_update() {
	    String temp = LapTimePage.people.getValue();
	    LapTimePage.people.getItems().clear();
	    for (int i = 0; i < L1.numActiveCars; i++) {
	    	LapTimePage.people.getItems().add(L1.name[i]);

	    }
	    LapTimePage.people.setValue(temp);
	    System.out.println("update");
	}
	
	private static String[] SectorTimes(int car) {
		
		
		return null;
		
	}
}
