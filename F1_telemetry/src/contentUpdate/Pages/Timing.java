package contentUpdate.Pages;

import Global_vars.Data;
import application.TimingPage;
import data_compute.delta;
import file_system.L1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Timing {

	public static void TimingUpdate() {
		int numActiveCars = Data.Participants.getNumActivePlayers();
		
		final ObservableList<TimingPage.Tabel_object> data = FXCollections.observableArrayList();

		for (int i = 0; i < numActiveCars; i++) {	
			int position_index = 0;
			try {
				position_index = L1.car_positions.get((byte)i);
			} catch (Exception e) {
			}
			
			float currentLapTime = Data.Lap_data[position_index].getCurrentLapTime();
			float lastLapTime = Data.Lap_data[position_index].getLastLapTime();
			byte actualTyreCompound = Data.Car_status[position_index].getActualTyreCompound();
			byte pitStatus = Data.Lap_data[position_index].getPitStatus();
			byte penalties = Data.Lap_data[position_index].getPenalties();
			String[] Sector = SectorTimes.SectorTimesUpdate(position_index);

			delta.delta_time();

			String delta = "0:000";
			if (String.valueOf(L1.Delta.get(position_index)) != "null") {
				delta = String.valueOf(L1.Delta.get(i));
			}
			data.add( new TimingPage.Tabel_object(
					"P" + String.valueOf(i + 1),															//position
					L1.name[position_index],																//player name
					TimingPage.MsTo_min_sec_ms(Math.round(currentLapTime * 1000), 0),						//current time
					Sector[0],																				//current S1 time
					Sector[1],																				//current S2 time
					Sector[2],																				//current S3 time
					TimingPage.MsTo_min_sec_ms(Math.round(lastLapTime * 1000), 0),							//last lap time
					TimingPage.Tyre(actualTyreCompound),													//witch tyre
					TimingPage.pit(pitStatus),																//pit status
					String.valueOf(penalties),																//penalties
					"+" + delta));																			//delta
		}
		TimingPage.Tabel.getItems().clear();
		TimingPage.Tabel.setItems(data);
		TimingPage.Tabel.refresh();
	}
}