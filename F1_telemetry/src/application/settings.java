package application;

import data_compute.Historical_lap_data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class settings {

	public static void display(String title) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		//zorgt er voor dat je deze window moet sluiten voor dat je verder gaat
		
		Label Poort_label = new Label("Port:");
		TextField poort_textfield = new TextField ();
		HBox poort = new HBox();
		poort_textfield.setText(String.valueOf(Settings.Settings_var.Poort));
		poort_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
		    Settings.Settings_var.Poort = Integer.valueOf(newValue);
		});
		poort.getChildren().addAll(Poort_label, poort_textfield);
		poort.setSpacing(10);
		
		HBox Send_rate = new HBox();
		Label Send_rate_label = new Label("Send rate:");
		ObservableList<String> Send_rate_options = 
			    FXCollections.observableArrayList(
			        "10 per second",
			        "20 per second",
			        "60 per second"
			    );
		ComboBox<String> Send_rate_dropdown = new ComboBox<String>(Send_rate_options);
		if (Settings.Settings_var.send_rate == 10) {
			Send_rate_dropdown.setValue("10 per second");
		} else if (Settings.Settings_var.send_rate == 20) {
			Send_rate_dropdown.setValue("20 per second");
		} else if (Settings.Settings_var.send_rate == 60) {
			Send_rate_dropdown.setValue("60 per second");
		}
		Send_rate_dropdown.setOnAction(e -> {
			if (Send_rate_dropdown.getValue() == "10 per second") {
				Settings.Settings_var.send_rate = 10;
			} else if (Send_rate_dropdown.getValue() == "20 per second") {
				Settings.Settings_var.send_rate = 20;
			} else if (Send_rate_dropdown.getValue() == "60 per second") {
				Settings.Settings_var.send_rate = 60;
			}
		});
		
		Send_rate.setSpacing(10);
		Send_rate.getChildren().addAll(Send_rate_label, Send_rate_dropdown);
		
		HBox Save = new HBox();
		Label Save_label = new Label("Save:");
		ObservableList<String> Save_options = FXCollections.observableArrayList();
		String[] Save_names = File_reader.Saves.read();
		for (int i = 0; i < Save_names.length; i++) {
			Save_options.add(Save_names[i]);
		}
		final ComboBox Save_dropdown = new ComboBox(Save_options);
		Save.setSpacing(10);
		Save.getChildren().addAll(Save_label, Save_dropdown);
		
		HBox Save_control = new HBox();
		Button Save_load = new Button();
		Button Save_stop = new Button();
		Save_load.setText("Load");
		Save_stop.setText("Stop");
		Save_stop.setVisible(false);
		Save_load.setOnAction(e -> {
			if (Save_dropdown.getValue() != null) {
				Inkoming.Packet_recieve.recieve_on = false;
				new Thread(new Runnable() {
				    public void run() {
				    	decode.data_to_file_system.decoded_data_manager((String) Save_dropdown.getValue());
				    }
				}).start();
				Save_load.setVisible(false);
				Save_stop.setVisible(true);
			}
		});
		Save_stop.setOnAction(e -> {
			decode.data_to_file_system.play_save = false;
			Inkoming.Packet_recieve.recieve_on = true;
			Save_stop.setVisible(false);
			Save_load.setVisible(true);
		});
		Save_control.getChildren().addAll(Save_load, Save_stop);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(poort, Send_rate, Save, Save_control);
		
		Scene scene = new Scene(layout, 800, 1000);
		
		try {
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(scene);
			window.setTitle(title);
			window.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}