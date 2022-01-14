package contentUpdate;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.TimingPage;
import application.TimingPage.Tabel_object;
import file_system.L1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.skin.TableColumnHeader;

public class ContentUpdate {
	protected static final TableColumnHeader columnHeader = null;
	public static boolean TimingPage_refresh = true;
	
	public static void Update() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		        if (TimingPage_refresh == true) {
		        	final ObservableList<Tabel_object> data =
		    		        FXCollections.observableArrayList();
	    			HashMap<Byte, Byte> car_positions = new HashMap<>();
	    			for(int i = 0; i < L1.numActiveCars; i++) {
	    				car_positions.put((byte) (L1.carPosition[i] - 1), (byte)i);
	    			}

		    		for (int i = 0; i < L1.numActiveCars; i++) {	
		    			int position_index = 0;
		    			try {
		    				position_index = car_positions.get((byte)i);
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

		    			String delta = "0:000";
		    			if (String.valueOf(L1.Delta.get(position_index)) != "null") {
		    				delta = String.valueOf(L1.Delta.get(position_index));
		    			}
		    			data.add( new Tabel_object(
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
		        }
		    }
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateClass, 0, 250, TimeUnit.MILLISECONDS);
	}
}
