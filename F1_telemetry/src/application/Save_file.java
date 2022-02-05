package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import File_reader.Names;
import Inkoming.Packet_recieve;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Save_file {
	private static Text Processing = new Text("Processing");
	private static Text Done = new Text("Done");

	public static void display(String title) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		//zorgt er voor dat je deze window moet sluiten voor dat je verder gaat
		
		Text already_exists = new Text("Save already exists");
		already_exists.setVisible(false);
		
		Done.setVisible(false);
		Processing.setVisible(false);
		
		Label save_label = new Label("Save Name:");
		HBox save_name_box = new HBox();
		TextField save_name = new TextField ();
		save_name_box.getChildren().addAll(save_label, save_name);
		
		Button Save = new Button();
		Save.setText("Save");
		Save.setOnAction(e -> {
			if (!Files.isDirectory(Paths.get("src/Saves/" + save_name.getText()))) {
				System.out.println(save_name.getText());
				save(save_name.getText());
			} else {
				already_exists.setVisible(true);
			} 
		});
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(already_exists, save_name_box, Save, Done, Processing);
		
		Scene scene = new Scene(layout, 300, 300);
		
		try {
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(scene);
			window.setTitle(title);
			window.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void save(String save) {
		new Thread(new Runnable() {
		    public void run() {
		    	Processing.setVisible(true);
		    	Packet_recieve.recieve_on = false;
				Packet_recieve.service.shutdown();
				File Temp = new File("src/Saves/temp");
				File Rename = new File("src/Saves/" + save);
				Temp.renameTo(Rename);
					
					
				File theDir = new File("src/Saves/temp");
				if (!theDir.exists()){
					theDir.mkdirs();
				}
					
				File Car_Setups = new File("src/Saves/temp/Car_Setups.dec");
				File Car_Status = new File("src/Saves/temp/Car_Status.dec");
				File Car_Telemetry = new File("src/Saves/temp/Car_Telemetry.dec");
				File Lap_Data = new File("src/Saves/temp/Lap_Data.dec");
				File Motion = new File("src/Saves/temp/Motion.dec");
				File Participants = new File("src/Saves/temp/Participants.dec");
				File Session = new File("src/Saves/temp/Session.dec");
				try {
					Car_Setups.createNewFile();
					Car_Status.createNewFile();
					Car_Telemetry.createNewFile();
					Lap_Data.createNewFile();
					Motion.createNewFile();
					Participants.createNewFile();
					Session.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				File_reader.Saves.write(new String[] {save});
					
				Packet_recieve.recieve_on = true;
//				new Thread(new Runnable() {
//				    public void run() {
//				    	Names.data_decode();
//				    	Packet_recieve.recieve_class();
//				    }
//				}).start();
				Temp = new File("src/Saves/temp");
				Processing.setVisible(false);
				Done.setVisible(true);
		    }
	   }).start();
	}
}
