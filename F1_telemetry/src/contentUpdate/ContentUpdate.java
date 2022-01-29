package contentUpdate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.GraphPage;
import application.ComparisonPage;
import application.LapTimePage;
import application.TimingPage;
import application.TrackPage;
import data_compute.Historical_lap_data;
import data_compute.delta;
import file_system.L1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ContentUpdate {
	protected static final TableColumnHeader columnHeader = null;
	public static boolean TimingPage_refresh = false;
	public static boolean LapTime_refresh = false;
	public static boolean Graph_refresh = false;
	public static boolean Comparison_refresh = false;
	public static boolean Track_refresh = false;
	
	public static boolean[] Graph_selection = new boolean[24];
	
	static int delta_refresh = 0;
	public static int TimingPage_car = 0;
	public static int GraphPage_car = 0;
	
	public static void Update() {
		Runnable updateClass = new Runnable() {
		    public void run() {
		        if (TimingPage_refresh == true) {
		        	final ObservableList<TimingPage.Tabel_object> data = FXCollections.observableArrayList();

		    		for (int i = 0; i < L1.numActiveCars; i++) {	
		    			int position_index = 0;
		    			try {
		    				position_index = L1.car_positions.get((byte)i);
		    			} catch (Exception e) {
		    			}
		    			String[] Sector = SectorTimes(position_index);

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
		        } else if (LapTime_refresh == true) {
		        	try {
			        	final ObservableList<LapTimePage.Tabel_object> data = FXCollections.observableArrayList();
			        	String[] Sector = SectorTimes(TimingPage_car);
			        	data.add(new LapTimePage.Tabel_object(
		        				String.valueOf(Historical_lap_data.lap_num[TimingPage_car]),
		        				TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[TimingPage_car] * 1000), 0),	//lap time
		        				Sector[0],
		        				Sector[1],
		        				Sector[2])); //s3 time
			        	
			        	LapTimePage.Tabel.getItems().clear();
			        	LapTimePage.Tabel.setItems(data);
			        	
			        	for (int i = Historical_lap_data.starting_lap; i < Historical_lap_data.lap_num[TimingPage_car]; i++) {
			        		data.add(new LapTimePage.Tabel_object(
			        				String.valueOf(Historical_lap_data.lap_num[TimingPage_car] - i),
			        				TimingPage.MsTo_min_sec_ms(Math.round(L1.Lap_Times.get(TimingPage_car).get(Historical_lap_data.lap_num[TimingPage_car] - i) * 1000), 0),	//lap time
			        				TimingPage.MsTo_min_sec_ms(L1.S1_Times.get(TimingPage_car).get(Historical_lap_data.lap_num[TimingPage_car] - i), 1),	//s1 time
			        				TimingPage.MsTo_min_sec_ms(L1.S2_Times.get(TimingPage_car).get(Historical_lap_data.lap_num[TimingPage_car] - i), 1),	//s2 time
			        				L1.S3_Times.get(TimingPage_car).get(i))); //s3 time
			        		LapTimePage.Tabel.setItems(data);
			        	}

			        	LapTimePage.Lap.setSortType(TableColumn.SortType.ASCENDING);
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	}
		        } else if (Track_refresh == true) {
		        	System.out.println(":) : " + L1.numActiveCars);
		        	final ObservableList<TrackPage.Tabel_object> data = FXCollections.observableArrayList();
		        	try {
		        		for (int i = 0; i < L1.numActiveCars; i++) {
		        			int position_index = 0;
			    			try {
			    				position_index = L1.car_positions.get((byte)i);
			    			} catch (Exception e) {
			    				e.printStackTrace();
			    			}
			    			ImageView Icon = new ImageView("images/Track_icons/" + (i + 1) + ".png");
			    			Icon.setFitHeight(20);
			    			Icon.setFitWidth(20);
		        			data.add(new TrackPage.Tabel_object(
		        					Icon,
			        				L1.name[position_index]));
		        		}
		        		TrackPage.players.getItems().clear();
		        		TrackPage.players.setItems(data);
		        		TrackPage.Track.setImage(new Image("images/Tracks/" + L1.trackId + ".png"));
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	}
		        	for (int i = 0; i < 22; i++) {
		        		if (i >= L1.numActiveCars) {
		        			TrackPage.Track_Players[i].setVisible(false);
		        		} else {
		        			TrackPage.Track_Players[i].setVisible(true);
		        		}
		        		
		    		}
		        	for (int i = 0; i < L1.numActiveCars; i++) {
		    			System.out.println("X: " + L1.worldPositionX[i] + " : " + "Y: " + L1.worldPositionY[i]);
		    			TrackPage.Track_Players[i].setX(L1.worldPositionX[i] + 200);
		    			TrackPage.Track_Players[i].setY(L1.worldPositionY[i] - 100);
		    			System.out.println("Done " + "X: " + L1.worldPositionX[i] + " : " + "Y: " + L1.worldPositionY[i]);
		    		}
		        }
		    }
		};
		
		Runnable TrackMap = new Runnable() {
		    public void run() {
		    	if (LapTime_refresh == true) {
		    		for (int i = 0; i < L1.numActiveCars; i++) {
		    			System.out.println("X: " + L1.worldPositionX[i] + " : " + "Y: " + L1.worldPositionY[i]);
		    			TrackPage.Track_Players[i].setTranslateX(L1.worldPositionX[i] + 10);
		    			TrackPage.Track_Players[i].setTranslateY(L1.worldPositionY[i] + 100);
		    		}
		    	}
		    }
		};
		ScheduledExecutorService content_update = Executors.newScheduledThreadPool(1);
		content_update.scheduleAtFixedRate(updateClass, 0, 700, TimeUnit.MILLISECONDS);
		
		ScheduledExecutorService TrackMap_update = Executors.newScheduledThreadPool(1);
		TrackMap_update.scheduleAtFixedRate(TrackMap, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	public static void dropdown_update() {
		if (LapTime_refresh == true) {
			String temp = LapTimePage.people.getValue();
		    LapTimePage.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	LapTimePage.people.getItems().add(L1.name[i]);
		    }
		    LapTimePage.people.setValue(temp);
		} else if (Graph_refresh == true) {
			String temp = GraphPage.people.getValue();
			GraphPage.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	GraphPage.people.getItems().add(L1.name[i]);
		    }
		    GraphPage.people.setValue(temp);
		} else if (Comparison_refresh) {
			String temp = ComparisonPage.people.getValue();
			ComparisonPage.people.getItems().clear();
		    for (int i = 0; i < L1.numActiveCars; i++) {
		    	ComparisonPage.people.getItems().add(L1.name[i]);
		    }
		    ComparisonPage.people.setValue(temp);
		}
	}
	
	private static String[] SectorTimes(int car) {
		String[] Sector = new String[3];
    	if (L1.sector1TimeInMS[car] == 0) {
			Sector[0] = TimingPage.MsTo_min_sec_ms(Math.round(L1.currentLapTime[car] * 1000), 1);
			Sector[1] = "00:000";
			Sector[2] = "00:000";
		} else if (L1.sector2TimeInMS[car] == 0) {
			Sector[0] = TimingPage.MsTo_min_sec_ms(L1.sector1TimeInMS[car], 1);
			if ((L1.currentLapTime[car] * 1000) - L1.sector1TimeInMS[car] < 0) {
				Sector[1] = "error";
			} else {
				Sector[1] = TimingPage.MsTo_min_sec_ms(Math.round(((L1.currentLapTime[car] * 1000) - L1.sector1TimeInMS[car])), 0);
			}
			Sector[2] = "00:000";
		} else {
			Sector[0] = TimingPage.MsTo_min_sec_ms(L1.sector1TimeInMS[car], 1);
			Sector[1] = TimingPage.MsTo_min_sec_ms(L1.sector2TimeInMS[car], 1);
			if ((L1.currentLapTime[car] * 1000) - (L1.sector1TimeInMS[car]) - (L1.sector2TimeInMS[car]) < 0) {
				Sector[2] = "error";
			} else {
				Sector[2] = TimingPage.MsTo_min_sec_ms(Math.round(((L1.currentLapTime[car] * 1000) - (L1.sector1TimeInMS[car]) - (L1.sector2TimeInMS[car]))), 1);
			}
		}
		
		return Sector;
		
	}
}
