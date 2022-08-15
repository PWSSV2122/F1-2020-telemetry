package Global_vars.Delta;

import java.io.File;
import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import application.Main;

public class DeltaJsonLoad {

	public static void LoadDeltaJson() {
		ObjectMapper mapper = new ObjectMapper();
		String filePath = Main.dir + "Global_vars/Delta/";
		
		File from = new File(filePath + "CustomOrDefault.Json");
		try {
			Delta.CustomOrDefault = (ObjectNode) mapper.readTree(from);
		} catch(Exception e) {
			//custom error message
		}
			
		Field[] fields = Delta.class.getFields();
		String[] VariableNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			VariableNames[i] = fields[i].getName();
		}
		
		for(int i = 0; i < 27; i++) {
			try {
				from = new File(filePath + Delta.CustomOrDefault.get(String.valueOf(i)).asText() + "/" + VariableNames[i] + ".Json");
				Delta.class.getField(VariableNames[i]).set(mapper.readTree(from), mapper.readTree(from));
			} catch (Exception e) {
				//custom error message
			}
		}
	}
}