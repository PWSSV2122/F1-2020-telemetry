package data_compute;

import application.ComparisonPage;
import application.GraphPage;
import contentUpdate.ContentUpdate;
import file_system.L1;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;

public class Historical_graph_data {
	public static float[] lap_percentage_float = new float[22];
	public static int[] lap_percentage_int = new int[22];
	public static void percentage() {
		if (delta.trackLength != null) {
			for (int i = 0; i < 22; i++) {
				lap_percentage_float[i] = (L1.lapDistance[i] / delta.trackLength) * 100;
				lap_percentage_int[i] = Math.round(lap_percentage_float[i] * 100);
			}
		}
	}
	public static int[] laatste_percentage = new int[22];
	public static boolean first = true;
	private static int packet_limiter = 0;
	private static float[][] last_data = new float[22][24];
	public static void data() {
		if (first == true) {
			for (int i = 0; i < 22; i++) {
				laatste_percentage[i] = lap_percentage_int[i];
			}
			first = false;
		}
		try {
			if (packet_limiter < 1) {
				final float[][] data = new float[22][24];
				final float[] lap_percentage = new float[22];
				for (int i = 0; i < L1.numActiveCars; i++) {
					lap_percentage[i] = lap_percentage_int[i];
					data[i][0] = (float)L1.tyresWear_RL[i];
					data[i][1] = (float)L1.tyresWear_RR[i];
					data[i][2] =(float) L1.tyresWear_FL[i];
					data[i][3] = (float)L1.tyresWear_FR[i];
					data[i][4] = (float)L1.throttle[i] * 50 + 10;
					data[i][5] = (float)L1.brake[i] * 50 + 10;
					data[i][6] = (float)L1.speed[i] / 350 * 100;
					if (L1.speed[i] == 63) {
						data[i][6] = last_data[i][6];
					} else {
						last_data[i][6] = data[i][6];
					}
					data[i][7] = ((float)L1.steer[i] + 1) / 2 * 100;
					data[i][8] = ((float)L1.fuelMix[i] + 1) * 24;
					data[i][9] = (float)L1.fuelInTank[i] / L1.fuelCapacity[i] * 100;
					data[i][10] = (float)L1.ersStoreEnergy[i] / 4000000 * 80 + 10;
					data[i][11] = ((float)L1.ersDeployMode[i] + 1) * 24;
					data[i][12] = ((float)L1.gear[i] + 2) * 9;
					data[i][13] = (float)L1.brakesTemperature_RL[i] / 1200 * 100;
					data[i][14] = (float)L1.brakesTemperature_RR[i] / 1200 * 100;
					data[i][15] = (float)L1.brakesTemperature_FL[i] / 1200 * 100;
					data[i][16] = (float)L1.brakesTemperature_FR[i] / 1200 * 100;
					for (int o = 0; o < 4; o++) {
						float braketemp = data[i][o + 13] / 100 * 1200;
						braketemp = Math.round(braketemp);
						if (braketemp == 319 || braketemp == 831 || braketemp == 575) {
							data[i][13 + o] = last_data[i][13 + o];
						} else {
							last_data[i][13 + o] = data[i][13 + o];
						}
					}
					data[i][17] = (float)L1.tyresInnerTemperature_RL[i] / 120 * 100;
					data[i][18] = (float)L1.tyresInnerTemperature_RR[i] / 120 * 100;
					data[i][19] = (float)L1.tyresInnerTemperature_FL[i] / 120 * 100;
					data[i][20] = (float)L1.tyresInnerTemperature_FR[i] / 120 * 100;
					data[i][21] = ((float)L1.roll[i] * (float)L1.roll[i] + (float)0.001) * 40000 - 20;
					data[i][22] = ((float)L1.pitch[i] * (float)L1.pitch[i] + (float)0.001) * 40000 - 20;
					data[i][23] = ((float)L1.yaw[i] * (float)L1.yaw[i]) * 10;
					packet_limiter++;
				}
				Compaire_Chart_update(data, lap_percentage);
			} else {
				packet_limiter = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Compaire_Chart_update(float[][] data, float[] percentage) {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	for (int i = 0; i < L1.numActiveCars; i++) {
		    		try {
		    			//System.out.println(percentage[i] + " : " + laatste_percentage[i]);
		    			if (percentage[i] > laatste_percentage[i] || laatste_percentage[i] == 2147483647) {
			    			for (int o = 0; o < data[i].length; o++) {
				    			ComparisonPage.series[i][o].getData().add(new XYChart.Data<Integer, Float>((int) percentage[i], data[i][o]));
							laatste_percentage[i] = (int) percentage[i];
			    			}
			    		}
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}
		    	}
		    }
		});
	}
}
