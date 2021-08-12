package File_reader;
import java.io.File;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Write {
	static String file_data = "";
	static JSONObject k = null;
	public static void Read(String file_name) {
		file_data = "";
		try {
    		File myObj = new File(file_name);
  	      	Scanner myReader = new Scanner(myObj);
  	      	while (myReader.hasNextLine()) {
  	      		String data = myReader.nextLine();
  	      		file_data = file_data + data;
  	      	}
  	      	myReader.close();
  	    } catch (FileNotFoundException e) {
  	    	e.printStackTrace();
  	    }
    	JSONParser parser = new JSONParser(); 
    	
    	if (file_data == "") {
    		file_data = "{}";
    	}
    	
		try {
			k = (JSONObject) parser.parse(file_data);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void Write(String file_name) {
		ObjectMapper mapper = new ObjectMapper();
    	
    	try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(k);
			try (FileWriter file = new FileWriter(new File(file_name))) {
	            //We can write any JSONArray or JSONObject instance to the file
	            file.write(json); 
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
	}
	
    
    @SuppressWarnings("unchecked")
	public static void buttons(String Vooruit, String Rechts, String Links, String Achteruit, String Terug, String Custom, String Groote1, String Groote2, String save, String tickbox, String wall, String path, String start, String end, String Menu) {
    	Read(data_return.filename_settings);
    	JSONObject j = (JSONObject) k.get("Buttons");
    	
    	j.put("Forward", Vooruit);
    	j.put("Right", Rechts);
    	j.put("Left", Links);
    	j.put("Backwards", Achteruit);
    	j.put("Back", Terug);
    	j.put("Custom", Custom);
    	j.put("Groote1", Groote1);
    	j.put("Groote2", Groote2);
    	j.put("save", save);
    	j.put("tickbox", tickbox);
    	j.put("wall", wall);
    	j.put("path", path);
    	j.put("start", start);
    	j.put("end", end);
    	j.put("Menu", Menu);
    	
    	k.remove("Buttons");
    	k.put("Buttons", j);
    	
    	Write(data_return.filename_settings);
    }
    
    @SuppressWarnings("unchecked")
	public static void puzzel(String puzzel_naam, int puzzel_length, int[] puzzel_data, int start, int einde) {
    	Read(data_return.filename_puzzels);
    	
    	int puzzel_rijen = puzzel_data.length / puzzel_length;
    	JSONArray jsArray = new JSONArray();
    	for (int i = 0; i < puzzel_data.length; i++) {
            jsArray.add(puzzel_data[i]);
        }
    	
    	JSONObject puzzel1 = new JSONObject();
    	JSONObject p = new JSONObject();
    	
    	int[] puzzel_1 = new int[puzzel_length];
    	int o = 0;
    	
    	for (int i = 0; i < puzzel_length; i++) {
    		puzzel_1[i] = puzzel_data[i + o * puzzel_length];
    		
    		if (i == puzzel_length - 1) {
    			p.put(o, puzzel_1);	
    			if (o < puzzel_data.length / puzzel_length - 1) {
    				i = -1;
    			}
    			puzzel_1 = new int[puzzel_length];
    			o++;
    		}
    	}
    	
    	puzzel1.put("row_length", puzzel_length);
    	puzzel1.put("row_amount", puzzel_rijen);
    	puzzel1.put("puzzel_data", p);
    	puzzel1.put("start", start);
    	puzzel1.put("einde", einde);
    	
    	if (k.get(puzzel_naam) == null) {
    		k.put(puzzel_naam, puzzel1);
    		
    	} else {
    		k.remove(puzzel_naam);
    		k.put(puzzel_naam, puzzel1);	
    	}
    	
    	Write(data_return.filename_puzzels);
    }
    
    @SuppressWarnings("unchecked")
	public static void time_start(long Start) {
    	Read(data_return.filename_score);
    	
    	JSONObject j = (JSONObject) k.get("time");
    	
    	j.put("start", Start);
    	
    	k.remove("time");
    	k.put("time", j);
    	
    	Write(data_return.filename_score);
    }
    
    @SuppressWarnings("unchecked")
	public static void time_stop(long Stop) {
    	Read(data_return.filename_score);
    	
    	JSONObject j = (JSONObject) k.get("time");
    	
    	j.put("stop", Stop);
    	
    	k.remove("time");
    	k.put("time", j);
    	
    	Write(data_return.filename_score);
    }
    
    @SuppressWarnings("unchecked")
	public static void time_total(String time) {
    	Read(data_return.filename_score);
    	
    	JSONObject j = (JSONObject) k.get("time");
    	
    	j.put("total", time);
    	
    	k.remove("time");
    	k.put("time", j);
    	
    	Write(data_return.filename_score);
    }
    
    @SuppressWarnings("unchecked")
	public static void time_puzzel(int puzzel) {
    	Read(data_return.filename_score);
    	
    	JSONObject j = (JSONObject) k.get("time");
    	
    	j.put("puzzel", puzzel);
    	
    	k.remove("time");
    	k.put("time", j);
    	
    	Write(data_return.filename_score);
    }
    
    @SuppressWarnings("unchecked")
	public static void high_score(long high_score, String high_score_time, String puzzel) {
    	Read(data_return.filename_score);
    	
    	JSONObject j = (JSONObject) k.get("score");
    	JSONObject h = (JSONObject) k.get("score_time");
    	
    	j.put(puzzel, high_score);
    	h.put(puzzel, high_score_time);
    	
    	k.remove("score");
    	k.remove("score_time");
    	k.put("score", j);
    	k.put("score_time", h);
    	
    	Write(data_return.filename_score);
    }
}
