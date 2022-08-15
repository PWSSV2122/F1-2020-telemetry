package applicationNew;
	
import java.io.File;

import File_reader.Names;
import applicationNew.UIBlocks.MenuBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import Global_vars.PageVars;

public class Main extends Application{
	public static double[] test = new double[] {1450, 800};
	
	public static Stage window;
	public static String dir = "src/";
	Button button1;
	
	public static void main(String[] args) {
		launch(args);	
	}
	
	@Override
	public void start(Stage primaryStage) {
		File_reader.settings.read();
		Names.data_decode();
		window = primaryStage;
			
		BorderPane top_level = new BorderPane();
		VBox top_box = new VBox();
		HBox top_box2 = new HBox();
		
		StackPane top_pane = new StackPane();
		StackPane center_pane = new StackPane();
		
		ScrollPane left_scroll = new ScrollPane();
		left_scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
		
		PageVars.Main_menu = new Scene(top_level, test[0], test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(PageVars.Main_menu.widthProperty());
		H_line.setStroke(Color.RED);
		
		Pane top_spacer = new Pane();
		top_spacer.setPrefWidth(10000000);
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add(getClass().getResource("css/menu_button.css").toExternalForm());
		logo.setOnAction(e -> {window.setScene(PageVars.Main_menu);
			window.setTitle("F1 Tracker : Main Menu");});
		
		Button Settings = new Button();
		ImageView Settings_image = new ImageView("images/settings.png");
		Settings.setGraphic(Settings_image);
		Settings_image.setPreserveRatio(true);
		Settings_image.setFitHeight(70);
		Settings.getStylesheets().add(getClass().getResource("css/menu_button.css").toExternalForm());
		Settings.setOnAction(e -> settings.display("Settings"));
		
		Rectangle top_background = new Rectangle();
		top_background.widthProperty().bind(top_level.widthProperty());
		top_background.setHeight(81);
		top_background.setStroke(Color.rgb(63, 63, 63, 1));
		top_background.setFill(Color.rgb(63, 63, 63, 1));
		
		Rectangle center_background = new Rectangle();
		
		Color center_menu_gray = Color.rgb(128, 128, 128, 0.9);
		center_background.setStroke(center_menu_gray);
		center_background.setFill(center_menu_gray);
		
		ImageView background_menu = new ImageView("images/background.png"); 
		background_menu.fitHeightProperty().bind(PageVars.Main_menu.heightProperty());
		
		GridPane content = new GridPane();
		content.setHgap(10);
		content.setVgap(10);
		
		Button[] Content_buttons = new Button[6];
		String[] Content_names = new String[] {"Track", "Suspension_page", "design_timings", "Graph_compaire", "GRAPH", "Times_driver"};
		ImageView[] Content_images = new ImageView[6];
		int[][] table = new int[][] {{1,1},{3,1},{5,1},{1,3},{3,3},{5,3}};
		for (int i = 0; i < 6; i++) {
			Content_buttons[i] = new Button();
			Content_images[i] = new ImageView("images/home_page/" + Content_names[i] + ".png");
			Content_buttons[i].getStylesheets().add(getClass().getResource("css/Content_button.css").toExternalForm());
			Content_images[i].setPreserveRatio(true);
			Content_images[i].setSmooth(true);
			Content_images[i].setFitWidth(500);
			Content_buttons[i].setGraphic(Content_images[i]);
			content.add(Content_buttons[i], table[i][0], table[i][1]);
			Content_buttons[i].setPadding(Insets.EMPTY);
		}	
		
		Content_buttons[0].setOnAction(e -> {window.setScene(PageVars.TrackPage_scene);
			window.setTitle("F1 Tracker : Track Page");
			ContentUpdate.Track_refresh = true;});
		Content_buttons[1].setOnAction(e -> {window.setScene(PageVars.SetupPage_Brakes_scene);
			window.setTitle("F1 Tracker : Setup Page Brakes");
			SetupUpdate.Brakes_Boolean = true;});
		Content_buttons[3].setOnAction(e -> {window.setScene(PageVars.ComparisonPage_scene);
			window.setTitle("F1 Tracker : Comparison Page");
			ContentUpdate.Comparison_refresh = true;});
		Content_buttons[4].setOnAction(e -> {window.setScene(PageVars.GraphPage_scene);
			window.setTitle("F1 Tracker : Graph Page");
			ContentUpdate.Graph_refresh = true;});
		Content_buttons[5].setOnAction(e -> {window.setScene(PageVars.LapTimePage_scene);
			window.setTitle("F1 Tracker : Lap Time Page");
			ContentUpdate.LapTime_refresh = true;});
		Content_buttons[2].setOnAction(e -> {window.setScene(PageVars.TimingPage_scene);
			window.setTitle("F1 Tracker : Timing Page");
			ContentUpdate.TimingPage_refresh = true;});
		
		Box box1 = new Box();
		box1.setHeight(120);
		box1.setWidth(0);
		content.add(box1, 0, 0);
		
		Box box2 = new Box();
		box2.setHeight(150);
		box2.setWidth(0);
		content.add(box2, 0, 2);
		
		top_level.setTop(top_box);
		top_box.getChildren().addAll(top_pane, H_line);
		top_pane.getChildren().addAll(top_background, top_box2);
		top_box2.getChildren().addAll(logo, top_spacer, Settings);
		
		
		top_level.setLeft(MenuBar.leftBox(window));
		
		top_level.setCenter(center_pane);
		center_pane.getChildren().addAll(background_menu, center_background, content);

	   window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - 130);
		   background_menu.setFitWidth((double) newVal - 130);
		   for(int i = 0; i < 6; i++) {
			   Content_buttons[i].setPrefWidth(((double) newVal - 40 - 150) / 3 - 1000);
			   Content_images[i].setFitWidth(((double) newVal - 40 - 150) / 3 - 2);
		   }
		   test[0] = (double) newVal;
	   });

	   window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	       box1.setHeight((double)newVal / 10);
	       for(int i = 0; i < 6; i++) {
	    	   Content_buttons[i].setPrefHeight(((double) newVal - 40 - 150) / 3 - 1000);
	    	   Content_images[i].setFitHeight(((double) newVal - 40 - 150) / 3 - 2);
		   }
		   box1.setHeight(((double) newVal - 90 - 100) / 7 - 20);
		   box2.setHeight(((double) newVal - 90 - 50) / 6 - 20);
	       test[1] = (double) newVal;
	   });
		
		try {
			//window.setY(257.0);
			//window.setX(2677.0);
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(PageVars.Main_menu);
			window.setTitle("F1 Tracker : Main Menu");
			window.show();
		} catch(Exception e) {
		}
		window.setOnCloseRequest(e -> PreferenceSave());
	}
	
	public void PreferenceSave() {
		File_reader.settings.write();
		File file = new File("Saves/temp/Lap_Data.dec");
		if (file.length() == 0) {
		} else {
			Save_file.display("Save File");
		}
		System.exit(0);
	}
}