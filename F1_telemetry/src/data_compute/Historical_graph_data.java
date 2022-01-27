package data_compute;

import java.util.HashMap;

import file_system.L1;

public class Historical_graph_data {
	public static float[] lap_percentage = new float[22];
	public static void percentage() {
		for (int i = 0; i < 22; i++) {
			lap_percentage[i] = L1.lapDistance[i] / delta.trackLength * 100;
			lap_percentage[i] = Math.round(lap_percentage[i] * 1000) / 1000;
		}
	}
	
	static HashMap<String, Object> temp1 = new HashMap<String, Object>();
	static HashMap<Float, HashMap<String, Object>> temp2 = new HashMap<Float, HashMap<String, Object>>();
	static HashMap<Integer, HashMap<Float, HashMap<String, Object>>> Graph_data = new HashMap<Integer, HashMap<Float, HashMap<String, Object>>>();
	public static void data() {
		for (int i = 0; i < 22; i++) {
			temp1.put("Tyre_wear_RL", L1.tyresWear_RL[i]);
			temp1.put("Tyre_wear_RR", L1.tyresWear_RR[i]);
			temp1.put("Tyre_wear_FL", L1.tyresWear_FL[i]);
			temp1.put("Tyre_wear_FR", L1.tyresWear_FR[i]);
			temp1.put("Throttle", L1.throttle[i]);
			temp1.put("Brake", L1.brake[i]);
			temp1.put("Speed", L1.speed[i]);
			temp1.put("Steering", L1.steer[i]);
			temp1.put("Fuel_mix", L1.fuelMix[i]);
			temp1.put("Fuel_use", L1.fuelInTank[i]);
			temp1.put("Ers_perecentage", L1.ersStoreEnergy[i]);
			temp1.put("Ers_use", L1.ersDeployMode[i]);
			temp1.put("Gear", L1.gear[i]);
			temp1.put("Roll", L1.roll[i]);
			temp1.put("Pitch", L1.pitch[i]);
			temp1.put("Yaw", L1.yaw[i]);
			temp1.put("Break_temp_RL", L1.brakesTemperature_RL[i]);
			temp1.put("Break_temp_RR", L1.brakesTemperature_RR[i]);
			temp1.put("Break_temp_FL", L1.brakesTemperature_FL[i]);
			temp1.put("Break_temp_FR", L1.brakesTemperature_FR[i]);
			temp1.put("Tyre_temp_RL", L1.tyresInnerTemperature_RL[i]);
			temp1.put("Tyre_temp_RR", L1.tyresInnerTemperature_RR[i]);
			temp1.put("Tyre_temp_FL", L1.tyresInnerTemperature_FL[i]);
			temp1.put("Tyre_temp_FR", L1.tyresInnerTemperature_FR[i]);
		
			if (L1.Graph_data.get(i).equals(null)) {
				temp2.put(lap_percentage[i], temp1);
				L1.Graph_data.put(i, temp2);
			} else {
				L1.Graph_data.get(i).put(lap_percentage[i], temp1);
			}
			temp1.clear();
			temp2.clear();
		}
	}
}
