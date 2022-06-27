package Saves.json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import application.Main;
import errors.Error;
import packet_struct.Header;

public class jsonMain {
	
	public static void jsonToSave(Header header, int sendRate) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode Json = mapper.createObjectNode();
		ObjectNode JsonLap = jsonLap.returnLapTimeStamp();
		ObjectNode JsonTimeStamp = jsonTimeStamp.TimeStamp();
		ObjectNode JsonSettings = mapper.createObjectNode();
		
		Json.set("lapRefference", JsonLap);
		Json.set("packetTimeStamp", JsonTimeStamp);
		
		JsonSettings.put("sendRate", sendRate);
		JsonSettings.put("packetFormat", header.getPacketFormat());
		JsonSettings.put("playerCarIndex", header.getPlayerCarIndex());
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		
		String date = dateFormat.format(now);
		String time = timeFormat.format(now);
		
		JsonSettings.put("date", date);
		JsonSettings.put("time", time);
		
		Json.set("Settings", JsonSettings);
		
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Json);
		} catch (JsonProcessingException e) {
			Error.error("could not convert json object to String. This falure makes the save useles.", 2);
		}
		System.out.println(json);
		
		FileOutputStream write;
		try {
			write = new FileOutputStream(Main.dir + "Saves/temp/temp.json");
			byte[] jsonOutput = json.getBytes();
			write.write(jsonOutput);
			write.close();
		} catch (FileNotFoundException e) {
			Error.error("Could not found temp.json in the folder saves/temp", 1);
		} catch (IOException e) {
			Error.error("Json string could not be saved. This falure makes the save useles.", 2);
		}
	}
}