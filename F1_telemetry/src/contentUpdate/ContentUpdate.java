package contentUpdate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.TimingPage;
import application.TimingPage.Tabel_object;
import file_system.L1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContentUpdate {
	public static boolean TimingPage_refresh = true;
	
	public static void Update() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		        if (TimingPage_refresh == true) {
		        	final ObservableList<Tabel_object> data =
		    		        FXCollections.observableArrayList();
		    		for (int i = 0; i < 22; i++) {
		    			long S3 = Math.round((L1.currentLapTime[i] * 1000) - (L1.sector1TimeInMS[i] * 1000) - (L1.sector2TimeInMS[i] * 1000));
		    			String position = "0";
		    			if (String.valueOf(L1.position.get((byte)i)) != "null") {
		    				position = String.valueOf(L1.position.get((byte)i));
		    			}
		    			String delta = "0:000";
		    			if (String.valueOf(L1.Delta.get(i)) != "null") {
		    				delta = String.valueOf(L1.Delta.get(i));
		    			}
		    			data.add( new Tabel_object(
		    					"P" + position,																//position
		    					L1.name[i],																	//player name
		    					TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[i] * 1000), 0),		//current time
		    					TimingPage.MsTo_min_sec_ms(Math.round(L1.sector1TimeInMS[i] * 1000), 1),	//current S1 time
		    					TimingPage.MsTo_min_sec_ms(Math.round(L1.sector2TimeInMS[i] * 1000), 1),	//current S2 time
		    					TimingPage.MsTo_min_sec_ms(Math.round(S3), 1),								//current S3 time
		    					TimingPage.MsTo_min_sec_ms(Math.round(L1.lastLapTime[i] * 1000), 0),		//last lap time
		    					TimingPage.Tyre(L1.actualTyreCompound[i]),									//witch tyre
		    					TimingPage.pit(L1.pitStatus[i]),											//pit status
		    					String.valueOf(L1.penalties[i]),											//penalties
		    					"+" + delta));																//delta
		    		}
		    		TimingPage.Tabel.getItems().clear();
		    		TimingPage.Tabel.setItems(data);
		    		TimingPage.Tabel.refresh();
		    		System.out.println(TimingPage.MsTo_min_sec_ms(Math.round(L1.lastLapTime[0] * 1000), 0));
		        }
		    }
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateClass, 0, 1000, TimeUnit.MILLISECONDS);
	}
}
