package contentUpdate.Pages;

import Global_vars.Data;
import application.TimingPage;

public class SectorTimes {

	public static String[] SectorTimesUpdate(int car) {
		short sector1TimeInMS = Data.Lap_data[car].getSector1TimeInMS();
		short sector2TimeInMS = Data.Lap_data[car].getSector2TimeInMS();
		float currentLapTime = Data.Lap_data[car].getCurrentLapTime();
		
		String[] Sector = new String[3];
    	if (sector1TimeInMS == 0) {
			Sector[0] = TimingPage.MsTo_min_sec_ms(Math.round(currentLapTime * 1000), 1);
			Sector[1] = "00:000";
			Sector[2] = "00:000";
		} else if (sector2TimeInMS == 0) {
			Sector[0] = TimingPage.MsTo_min_sec_ms(sector1TimeInMS, 1);
			if ((currentLapTime * 1000) - sector1TimeInMS < 0) {
				Sector[1] = "error";
			} else {
				Sector[1] = TimingPage.MsTo_min_sec_ms(Math.round(((currentLapTime * 1000) - sector2TimeInMS)), 0);
			}
			Sector[2] = "00:000";
		} else {
			Sector[0] = TimingPage.MsTo_min_sec_ms(sector1TimeInMS, 1);
			Sector[1] = TimingPage.MsTo_min_sec_ms(sector2TimeInMS, 1);
			if ((currentLapTime * 1000) - (sector1TimeInMS) - (sector2TimeInMS) < 0) {
				Sector[2] = "error";
			} else {
				Sector[2] = TimingPage.MsTo_min_sec_ms(Math.round(((currentLapTime * 1000) - (sector1TimeInMS) - (sector2TimeInMS))), 1);
			}
		}
		
		return Sector;	
	}
}