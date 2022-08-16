package applicationNew.UIBlocks;

import Global_vars.PageVars;
import application.Main;
import applicationNew.settings;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TopBar {

	public static VBox TopBox(Stage window) {
		VBox top_box = new VBox();
		HBox top_box2 = new HBox();
		StackPane top_pane = new StackPane();
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(PageVars.Main_menu.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle top_background = new Rectangle();
		top_background.widthProperty().bind(window.widthProperty());
		top_background.setHeight(81);
		top_background.setStroke(Color.rgb(63, 63, 63, 1));
		top_background.setFill(Color.rgb(63, 63, 63, 1));
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add(Main.class.getResource("css/menu_button.css").toExternalForm());
		logo.setOnAction(e -> {window.setScene(PageVars.Main_menu);
			window.setTitle("F1 Tracker : Main Menu");});
		
		Button Settings = new Button();
		ImageView Settings_image = new ImageView("images/settings.png");
		Settings.setGraphic(Settings_image);
		Settings_image.setPreserveRatio(true);
		Settings_image.setFitHeight(70);
		Settings.getStylesheets().add(Main.class.getResource("css/menu_button.css").toExternalForm());
		Settings.setOnAction(e -> settings.display("Settings"));
		
	    Pane top_spacer = new Pane();
		
		top_box.getChildren().addAll(top_pane, H_line);
		top_pane.getChildren().addAll(top_background, top_box2);
		top_box2.getChildren().addAll(logo, top_spacer, Settings);

		window.widthProperty().addListener((obs, oldVal, newVal) -> {
			top_spacer.setPrefWidth((double)newVal - logo.getWidth() - Settings.getWidth() - 18);
		});
		
		return top_box;
	}
}