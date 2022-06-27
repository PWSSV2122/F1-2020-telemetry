package Saves.decode;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.Main;

public class decode {

	public static boolean runSave = false;
	
	public static void decode_save(String saveName) {
		String test = null;
		try {
			test = Files.readString(Path.of(Main.dir + "Saves/" + saveName + "/" + saveName + ".json"));
		} catch (IOException e) {
			errors.Error.error(saveName + ".json not found", 2);
		}
		final ObjectMapper objectMapper = new ObjectMapper();
		JsonNode array = null;
		try {
			array = objectMapper.readValue(test, JsonNode.class);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace();
			errors.Error.error("stst", 2);
			//todo error message maken
			System.out.println("no");
		}
		System.out.println(array);
	    JsonNode object = array.get("settings");
	    int sendRate = object.get("sendRate").asInt();
	    float waitTime = 1000 / sendRate;
	    Thread thread = new Thread(){
			public void run(){
				while(runSave) {
					long timeStart = System.nanoTime();
				    long timeStop = System.nanoTime();
				    try {
						TimeUnit.MILLISECONDS.sleep((long) (waitTime - (timeStop - timeStart) / 1000000));
					} catch (InterruptedException e) {
						errors.Error.error("Wait delay not working properly", 1);
					}
				}
		    }
		};
		thread.start();
	}
}