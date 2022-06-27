package Saves.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class jsonTimeStamp {

	@SuppressWarnings("unchecked")
	public static Map<Integer, Integer>[] times = new HashMap[10];	
	
	@SuppressWarnings("deprecation")
	public static ObjectNode TimeStamp() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode TimeStampJson = mapper.createObjectNode();
		for(int i = 0; i < 9; i++) {
			for (Entry<Integer, Integer> entry : times[0].entrySet()) {
			    int key = entry.getKey();
			    int value = entry.getValue();
			    ArrayNode arrayNode = mapper.createArrayNode();
			    if (TimeStampJson.get(String.valueOf(key)) != null) {
			    	arrayNode.add(TimeStampJson.get(String.valueOf(key)));
			    }
			    arrayNode.add(value);
				TimeStampJson.put(String.valueOf(key), arrayNode);
			}
		}
		return TimeStampJson;
	}
}
