package Saves.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class jsonLap {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static ObjectNode Lap = mapper.createObjectNode();
	private static int start = 0;
	
	public static void LapTimeStamp(float sessionTime, int lapNum) {
		ObjectNode temp = mapper.createObjectNode();
		temp.put("Start", start);
		temp.put("End", (int)sessionTime);
		Lap.set(String.valueOf(lapNum - 1), temp);
		start = (int)sessionTime;
	}
	
	public static ObjectNode returnLapTimeStamp() {
		return Lap;
	}
}