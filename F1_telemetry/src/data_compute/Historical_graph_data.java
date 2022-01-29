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
		for (int i = 0; i < 22; i++) {
			if (delta.trackLength != null) {
				lap_percentage_float[i] = (L1.lapDistance[i] / delta.trackLength) * 100;
				lap_percentage_int[i] = Math.round(lap_percentage_float[i] * 100);
			}
		}
	}
	public static int[] laatste_percentage = new int[22];
	public static boolean first = true;
	public static void data() {
		
		if (first == true) {
			for (int i = 0; i < 22; i++) {
				laatste_percentage[i] = lap_percentage_int[i];
			}
			first = false;
		}
		try {
			if (ContentUpdate.Graph_refresh == true) {
				final int lap_percentage = lap_percentage_int[ContentUpdate.GraphPage_car];
				if (GraphPage.series[0].getNode().isVisible() == true) {
					final float Tyre_wear = (float)L1.tyresWear_RL[ContentUpdate.GraphPage_car];
					Chart_update(Tyre_wear, lap_percentage, 0);
				}
				if (GraphPage.series[1].getNode().isVisible() == true) {
					final float Tyre_wear = (float)L1.tyresWear_RR[ContentUpdate.GraphPage_car];
					Chart_update(Tyre_wear, lap_percentage, 1);
				}
				if (GraphPage.series[2].getNode().isVisible() == true) {
					final float Tyre_wear =(float) L1.tyresWear_FL[ContentUpdate.GraphPage_car];
					Chart_update(Tyre_wear, lap_percentage, 2);
				}
				if (GraphPage.series[3].getNode().isVisible() == true) {
					final float Tyre_wear = (float)L1.tyresWear_FR[ContentUpdate.GraphPage_car];
					System.out.println(Tyre_wear);
					Chart_update(Tyre_wear, lap_percentage, 3);
				}
				if (GraphPage.series[4].getNode().isVisible() == true) {
					final float throttle = (float)L1.throttle[ContentUpdate.GraphPage_car] * 100;
					Chart_update(throttle, lap_percentage, 4);
				}
				if (GraphPage.series[5].getNode().isVisible() == true) {
					final float Brake = (float)L1.brake[ContentUpdate.GraphPage_car] * 100;
					Chart_update(Brake, lap_percentage, 5);
				}
				if (GraphPage.series[6].getNode().isVisible() == true) {
					final float speed = (float)L1.speed[ContentUpdate.GraphPage_car] / 350 * 100;
					if (L1.speed[ContentUpdate.GraphPage_car] == 63) {
					} else {
						Chart_update(speed, lap_percentage, 6);
					}
					//System.out.println(speed + " : " + L1.speed[ContentUpdate.GraphPage_car] + " : " + (float)L1.speed[ContentUpdate.GraphPage_car] / 350);
				}
				if (GraphPage.series[7].getNode().isVisible() == true) {
					final float steer = (float)L1.steer[ContentUpdate.GraphPage_car] + 1 / 2 * 100;
					Chart_update(steer, lap_percentage, 7);
				}
				if (GraphPage.series[8].getNode().isVisible() == true) {
					final float fuelMix = (float)L1.fuelMix[ContentUpdate.GraphPage_car] + 1 * 24;
					Chart_update(fuelMix, lap_percentage, 8);
				}
				if (GraphPage.series[9].getNode().isVisible() == true) {
					final float fuelInTank = (float)L1.fuelInTank[ContentUpdate.GraphPage_car] / L1.fuelCapacity[ContentUpdate.GraphPage_car] * 100;
					Chart_update(fuelInTank, lap_percentage, 9);
				}
				if (GraphPage.series[10].getNode().isVisible() == true) {
					final float ersStoreEnergy = (float)L1.ersStoreEnergy[ContentUpdate.GraphPage_car]; //test max capacity
					Chart_update(ersStoreEnergy, lap_percentage, 10);
				}
				if (GraphPage.series[11].getNode().isVisible() == true) {
					final float ersDeployMode = (float)L1.ersDeployMode[ContentUpdate.GraphPage_car] + 1 * 24;
					Chart_update(ersDeployMode, lap_percentage, 11);
				}
				if (GraphPage.series[12].getNode().isVisible() == true) {
					final float gear = (float)L1.gear[ContentUpdate.GraphPage_car] + 2 * 9;
					Chart_update(gear, lap_percentage, 12);
				}
				if (GraphPage.series[13].getNode().isVisible() == true) {
					final float brakesTemperature_RL = (float)L1.brakesTemperature_RL[ContentUpdate.GraphPage_car] / 1200 * 100;
					Chart_update(brakesTemperature_RL, lap_percentage, 13);
				}
				if (GraphPage.series[14].getNode().isVisible() == true) {
					final float brakesTemperature_RR = (float)L1.brakesTemperature_RR[ContentUpdate.GraphPage_car] / 1200 * 100;
					Chart_update(brakesTemperature_RR, lap_percentage, 14);
				}
				if (GraphPage.series[15].getNode().isVisible() == true) {
					final float brakesTemperature_FL = (float)L1.brakesTemperature_FL[ContentUpdate.GraphPage_car] / 1200 * 100;
					Chart_update(brakesTemperature_FL, lap_percentage, 15);
				}
				if (GraphPage.series[16].getNode().isVisible() == true) {
					final float brakesTemperature_FR = (float)L1.brakesTemperature_FR[ContentUpdate.GraphPage_car] / 1200 * 100;
					Chart_update(brakesTemperature_FR, lap_percentage, 16);
				}
				if (GraphPage.series[17].getNode().isVisible() == true) {
					final float tyresInnerTemperature_RL = (float)L1.tyresInnerTemperature_RL[ContentUpdate.GraphPage_car] / 120 * 100;
					Chart_update(tyresInnerTemperature_RL, lap_percentage, 17);
				}
				if (GraphPage.series[18].getNode().isVisible() == true) {
					final float tyresInnerTemperature_RR = (float)L1.tyresInnerTemperature_RR[ContentUpdate.GraphPage_car] / 120 * 100;
					Chart_update(tyresInnerTemperature_RR, lap_percentage, 18);
				}
				if (GraphPage.series[19].getNode().isVisible() == true) {
					final float tyresInnerTemperature_FL = (float)L1.tyresInnerTemperature_FL[ContentUpdate.GraphPage_car] / 120 * 100;
					Chart_update(tyresInnerTemperature_FL, lap_percentage, 19);
				}
				if (GraphPage.series[20].getNode().isVisible() == true) {
					final float tyresInnerTemperature_FR = (float)L1.tyresInnerTemperature_FR[ContentUpdate.GraphPage_car] / 120 * 100;
					Chart_update(tyresInnerTemperature_FR, lap_percentage, 20);
				}
				if (GraphPage.series[21].getNode().isVisible() == true) {
					final float roll = (float)L1.roll[ContentUpdate.GraphPage_car];//test max capacity
					Chart_update(roll, lap_percentage, 21);
				}
				if (GraphPage.series[22].getNode().isVisible() == true) {
					final float pitch = (float)L1.pitch[ContentUpdate.GraphPage_car]; //test max capacity
					Chart_update(pitch, lap_percentage, 22);
				}
				if (GraphPage.series[23].getNode().isVisible() == true) {
					final float yaw = (float)L1.yaw[ContentUpdate.GraphPage_car]; //test max capacity
					Chart_update(yaw, lap_percentage, 23);
				}
			}
			for (int i = 0; i < 22; i++) {
				final int lap_percentage = lap_percentage_int[i];
				final float tyresWear_RL = (float)L1.tyresWear_RL[i];
				Compaire_Chart_update(tyresWear_RL, lap_percentage, 0, i);
				final float tyresWear_RR = (float)L1.tyresWear_RR[i];
				Compaire_Chart_update(tyresWear_RR, lap_percentage, 1, i);
				final float tyresWear_FL =(float) L1.tyresWear_FL[i];
				Compaire_Chart_update(tyresWear_FL, lap_percentage, 2, i);
				final float tyresWear_FR = (float)L1.tyresWear_FR[i];
				Compaire_Chart_update(tyresWear_FR, lap_percentage, 3, i);
				final float throttle = (float)L1.throttle[i] * 100;
				Compaire_Chart_update(throttle, lap_percentage, 4, i);
				final float Brake = (float)L1.brake[i] * 100;
				Compaire_Chart_update(Brake, lap_percentage, 5, i);
				final float speed = (float)L1.speed[i] / 350 * 100;
				if (L1.speed[i] == 63) {
				} else {
					Compaire_Chart_update(speed, lap_percentage, 6, i);
				}
				final float steer = (float)L1.steer[i] + 1 / 2 * 100;
				Compaire_Chart_update(steer, lap_percentage, 7, i);
				final float fuelMix = (float)L1.fuelMix[i] + 1 * 24;
				Compaire_Chart_update(fuelMix, lap_percentage, 8, i);
				final float fuelInTank = (float)L1.fuelInTank[i] / L1.fuelCapacity[i] * 100;
				Compaire_Chart_update(fuelInTank, lap_percentage, 9, i);
				final float ersStoreEnergy = (float)L1.ersStoreEnergy[i]; //test max capacity
				Compaire_Chart_update(ersStoreEnergy, lap_percentage, 10, i);
				final float ersDeployMode = (float)L1.ersDeployMode[i] + 1 * 24;
				Compaire_Chart_update(ersDeployMode, lap_percentage, 11, i);
				final float gear = (float)L1.gear[i] + 2 * 9;
				Compaire_Chart_update(gear, lap_percentage, 12, i);
				final float brakesTemperature_RL = (float)L1.brakesTemperature_RL[i] / 1200 * 100;
				Compaire_Chart_update(brakesTemperature_RL, lap_percentage, 13, i);
				final float brakesTemperature_RR = (float)L1.brakesTemperature_RR[i] / 1200 * 100;
				Compaire_Chart_update(brakesTemperature_RR, lap_percentage, 14, i);
				final float brakesTemperature_FL = (float)L1.brakesTemperature_FL[i] / 1200 * 100;
				Compaire_Chart_update(brakesTemperature_FL, lap_percentage, 15, i);
				final float brakesTemperature_FR = (float)L1.brakesTemperature_FR[i] / 1200 * 100;
				Compaire_Chart_update(brakesTemperature_FR, lap_percentage, 16, i);
				final float tyresInnerTemperature_RL = (float)L1.tyresInnerTemperature_RL[i] / 120 * 100;
				Compaire_Chart_update(tyresInnerTemperature_RL, lap_percentage, 17, i);
				final float tyresInnerTemperature_RR = (float)L1.tyresInnerTemperature_RR[i] / 120 * 100;
				Compaire_Chart_update(tyresInnerTemperature_RR, lap_percentage, 18, i);
				final float tyresInnerTemperature_FL = (float)L1.tyresInnerTemperature_FL[i] / 120 * 100;
				Compaire_Chart_update(tyresInnerTemperature_FL, lap_percentage, 19, i);
				final float tyresInnerTemperature_FR = (float)L1.tyresInnerTemperature_FR[i] / 120 * 100;
				Compaire_Chart_update(tyresInnerTemperature_FR, lap_percentage, 20, i);
				final float roll = (float)L1.roll[i];//test max capacity
				Compaire_Chart_update(roll, lap_percentage, 21, i);
				final float pitch = (float)L1.pitch[i]; //test max capacity
				Compaire_Chart_update(pitch, lap_percentage, 22, i);
				final float yaw = (float)L1.yaw[i]; //test max capacity
				Compaire_Chart_update(yaw, lap_percentage, 23, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void Chart_update(float data, int percentage, int chart) {
		if (laatste_percentage[ContentUpdate.GraphPage_car] <= percentage) {
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    		GraphPage.series[chart].getData().add(new XYChart.Data<Integer, Float>(percentage, data));
			    }
			});
			for (int i = 0; i < 22; i++) {
				laatste_percentage[i] = lap_percentage_int[i];
			}
		}
	}
	
	private static void Compaire_Chart_update(float data, int percentage, int chart, int player) {
		if (laatste_percentage[ContentUpdate.GraphPage_car] <= percentage) {
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	ComparisonPage.series[player][chart].getData().add(new XYChart.Data<Integer, Float>(percentage, data));
			    }
			});
			for (int i = 0; i < 22; i++) {
				laatste_percentage[i] = lap_percentage_int[i];
			}
		}
	}
}
