package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class settings {

	public static void display(String title) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		//zorgt er voor dat je deze window moet sluiten voor dat je verder gaat
		
		
		VBox layout = new VBox(10);
		
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
