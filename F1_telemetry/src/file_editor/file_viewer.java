package file_editor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class file_viewer extends Application{
	Stage window;
	Scene scene1;
	HashMap<Integer, HashMap<String, Object>> Packet = new HashMap<Integer, HashMap<String, Object>>();
	public void start(Stage primaryStage) {
		read();
		window = primaryStage;
		VBox top_level = new VBox();
		scene1 = new Scene(top_level, 1500, 1000);
		top_level.setPrefHeight(1000);
		try {
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(scene1);
			window.setTitle("F1 Tracker");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		window.setOnCloseRequest(e -> PreferenceSave());
	}
	
	public void PreferenceSave() {
		
	}
	private void read() {
		try {
			byte[] input = Files.readAllBytes(Paths.get("src/test/test.data"));
			String s2 = String.format("%8s", Integer.toBinaryString(input[0] & 0xFF)).replace(' ', '0');
			System.out.println(s2);
			if (input[0] == -128) {
				HashMap<String, Object> temp_save = new HashMap<String, Object>();
				Packet.put((int)(((input[4] & 0xFF) << 24) | ((input[3] & 0xFF) << 16) | ((input[2] & 0xFF) << 8) | ((input[1] & 0xFF) << 0)), temp_save);
			} else {
				System.out.println("geen geldig bestand");
				System.out.println(input.length);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
