package application.SetupPage;

import application.Main_menu;
import application.settings;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import file_system.L1;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Transmission {
	
	public static ComboBox<String> people = new ComboBox<String>();
	public static Boolean NoChange = false;
	
	public static ProgressBar On_Throttle_bar = new ProgressBar(0);
	public static Text On_Throttle_waarde = new Text(null);
	
	public static ProgressBar Off_Throttle_bar = new ProgressBar(0);
	public static Text Off_Throttle_waarde = new Text(null);
	
	public static Scene Transmission_scene() {
		Scene Transmission;
		
		BorderPane top_level = new BorderPane();
		VBox top_box = new VBox();
		HBox top_box2 = new HBox();
		HBox left_box = new HBox();
		VBox left_box2 = new VBox();
		
		StackPane top_pane = new StackPane();
		StackPane left_pane = new StackPane();
		StackPane center_pane = new StackPane();
		
		ScrollPane left_scroll = new ScrollPane();
		left_scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
		
		Transmission = new Scene(top_level, Main_menu.test[0], Main_menu.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(Transmission.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(Transmission.heightProperty());
		V_line.setStroke(Color.RED);
		
		Pane top_spacer = new Pane();
		top_spacer.setPrefWidth(10000000);
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add("application/css/menu_button.css");
		logo.setOnAction(e -> {Main_menu.window.setScene(Main_menu.Main_menu);
			Main_menu.window.setTitle("F1 Tracker : Main Menu");});
		
		Button Settings = new Button();
		ImageView Settings_image = new ImageView("images/settings.png");
		Settings.setGraphic(Settings_image);
		Settings_image.setPreserveRatio(true);
		Settings_image.setFitHeight(70);
		Settings.getStylesheets().add("application/css/menu_button.css");
		Settings.setOnAction(e -> settings.display("Settings"));
		
		int menubar_amount = 6;
		Button[] menubar_buttons = new Button[menubar_amount];
		String[] names = new String[] {"TrackPage", "SetupPage", "ComparisonPage", "GraphPage", "LapTimePage", "TimingPage"};
		ImageView[] menubar_image = new ImageView[menubar_amount];
		for (int i = 0; i < (menubar_amount); i++) {
			menubar_buttons[i] = new Button();
			menubar_image[i] = new ImageView("images/icons/" + names[i] + ".png");
			menubar_buttons[i].getStylesheets().add("application/css/menu_button.css");
			menubar_image[i].setPreserveRatio(true);
			menubar_image[i].setFitWidth(90);
			menubar_buttons[i].setGraphic(menubar_image[i]);
		}
		menubar_buttons[0].setOnAction(e -> {Main_menu.window.setScene(Main_menu.TrackPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Track Page");
			SetupUpdate.Brakes_Boolean = false;
			ContentUpdate.Track_refresh = true;});
		menubar_buttons[1].setOnAction(e -> {Main_menu.window.setScene(Main_menu.SetupPage_Brakes_scene);
			Main_menu.window.setTitle("F1 Tracker : Setup Page Brakes");
			SetupUpdate.Brakes_Boolean = false;
			SetupUpdate.Brakes_Boolean = true;});
		menubar_buttons[2].setOnAction(e -> {Main_menu.window.setScene(Main_menu.ComparisonPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Comparison Page");
			SetupUpdate.Brakes_Boolean = false;
			ContentUpdate.Comparison_refresh = true;});
		menubar_buttons[3].setOnAction(e -> {Main_menu.window.setScene(Main_menu.GraphPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Graph Page");
			SetupUpdate.Brakes_Boolean = false;
			ContentUpdate.Graph_refresh = true;});
		menubar_buttons[4].setOnAction(e -> {Main_menu.window.setScene(Main_menu.LapTimePage_scene);
			Main_menu.window.setTitle("F1 Tracker : Lap Time Page");
			SetupUpdate.Brakes_Boolean = false;
			ContentUpdate.LapTime_refresh = true;});
		menubar_buttons[5].setOnAction(e -> {Main_menu.window.setScene(Main_menu.TimingPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Timing Page");
			SetupUpdate.Brakes_Boolean = false;
			ContentUpdate.TimingPage_refresh = true;});
		
		Rectangle left_background = new Rectangle();
		left_background.setWidth(111);
		left_background.heightProperty().bind(top_level.heightProperty());
		left_background.setStroke(Color.rgb(193, 193, 193, 0.85));
		left_background.setFill(Color.rgb(193, 193, 193, 0.85));
		
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
		background_menu.fitHeightProperty().bind(Transmission.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(Transmission.heightProperty());
		
		HBox menu_items = new HBox();
		
		Text Setup = new Text("Setup");
		Setup.setTranslateX(10);
		Setup.setTranslateY(6);
		Setup.setStyle("-fx-font: 24 arial;");
		
		Text Track = new Text("test");
		Track.setTranslateX(30);
		Track.setTranslateY(6);
		Track.setStyle("-fx-font: 24 arial;");
		
		ComboBox<String> Pagina = new ComboBox<String>();
		Pagina.setTranslateX(50);
		Pagina.setTranslateY(6);
		Pagina.setPrefWidth(150);
		for (int i = 0; i < SetupUpdate.paginas.length; i++) {
			Pagina.getItems().add(SetupUpdate.paginas[i]);
		}
		Pagina.setValue(SetupUpdate.paginas[3]);
		Pagina.setOnAction(e -> {
			if (NoChange == false) {
				NoChange = true;
				SetupUpdate.Transmission_Boolean = false;
				if (Pagina.getValue() == SetupUpdate.paginas[0]) {
					Main_menu.window.setScene(Main_menu.SetupPage_Brakes_scene);
					SetupUpdate.Brakes_Boolean = true;
					Main_menu.window.setTitle("F1 Tracker : Setup Brake");
				} else if (Pagina.getValue() == SetupUpdate.paginas[1]) {
					Main_menu.window.setScene(Main_menu.SetupPage_Suspension_Geometry_scene);
					SetupUpdate.Suspension_Geometry_Boolean = true;
					Main_menu.window.setTitle("F1 Tracker : Setup Suspension Geometry");
				} else if (Pagina.getValue() == SetupUpdate.paginas[2]) {
					Main_menu.window.setScene(Main_menu.SetupPage_Suspension_scene);
					SetupUpdate.Suspension_Boolean = true;
					Main_menu.window.setTitle("F1 Tracker : Setup Suspension");
				} else if (Pagina.getValue() == SetupUpdate.paginas[3]) {
					Main_menu.window.setScene(Main_menu.SetupPage_Transmission_scene);
					SetupUpdate.Transmission_Boolean = true;
					Main_menu.window.setTitle("F1 Tracker : Setup Transmission");
				} else if (Pagina.getValue() == SetupUpdate.paginas[4]) {
					Main_menu.window.setScene(Main_menu.SetupPage_Tyres_scene);
					SetupUpdate.Tyres_Boolean = true;
					Main_menu.window.setTitle("F1 Tracker : Setup Tyres");
				} else if (Pagina.getValue() == SetupUpdate.paginas[5]) {
					Main_menu.window.setScene(Main_menu.SetupPage_Aerodynamics_scene);
					SetupUpdate.Aerodynamics_Boolean = true;
					Main_menu.window.setTitle("F1 Tracker : Aerodynamics Tyres");
				}
			}
			Pagina.setValue(SetupUpdate.paginas[3]);
			NoChange = false;
		});
		
		people.setTranslateX(70);
		people.setTranslateY(6);
		people.setPrefWidth(150);
		people.setOnMouseClicked(e -> {
			SetupUpdate.dropdown_update();
		});
		people.setOnAction(e -> {
			String name = people.getValue();
			for (int i = 0; i < L1.name.length; i++) {
				if (L1.name[i] == name) {
					SetupUpdate.setup_car = i;
				}
			}
		});
		
		Rectangle[] Deviders = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};
		int[] translate = new int[] {20, 40, 60, 80};
		for (int i = 0; i < Deviders.length; i++) {
			Deviders[i].setTranslateX(translate[i]);
			Deviders[i].setHeight(35);
			Deviders[i].setWidth(1);
			Deviders[i].setStroke(Color.RED);
			Deviders[i].setFill(Color.RED);
		}
		menu_items.getChildren().addAll(Setup, Deviders[0], Track, Deviders[1], Pagina, Deviders[2], people, Deviders[3]);
		
		Rectangle menu_items_underline = new Rectangle();
		menu_items_underline.setWidth(Main_menu.test[0] - 115);
		menu_items_underline.setHeight(1);
		menu_items_underline.setStroke(Color.RED);
		menu_items_underline.setFill(Color.RED);
		
		HBox On_Throttle = new HBox();
		Text On_Throttle_text = new Text("Differential Adjustment On Throttle");
		On_Throttle_text.setStyle("-fx-font: 24 arial;");
		On_Throttle_bar.setProgress(0.5);
		On_Throttle_bar.setPrefHeight(28);
		On_Throttle_bar.setPrefWidth(300);
		On_Throttle_waarde.setStyle("-fx-font: 24 arial;");
		Text On_Throttle_range = new Text("Unlocked 50% - 100% Locked");
		On_Throttle_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] On_Throttle_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] On_Throttle_heigth = new int[] {1, 1, 1};
		int[] On_Throttle_width = new int[] {100, 10, 100};
		for (int i = 0; i < On_Throttle_spacers.length; i++) {
			On_Throttle_spacers[i].setHeight(On_Throttle_heigth[i]);
			On_Throttle_spacers[i].setWidth(On_Throttle_width[i]);
			On_Throttle_spacers[i].setVisible(false);
		}
		On_Throttle.getChildren().addAll(On_Throttle_text, On_Throttle_spacers[0], On_Throttle_bar, On_Throttle_spacers[1], On_Throttle_waarde, On_Throttle_spacers[2], On_Throttle_range);
		On_Throttle.setTranslateX(10);
		
		HBox Off_Throttle = new HBox();
		Text Off_Throttle_text = new Text("Differential Adjustment Off Throttle");
		Off_Throttle_text.setStyle("-fx-font: 24 arial;");
		Off_Throttle_bar.setProgress(0.5);
		Off_Throttle_bar.setPrefHeight(28);
		Off_Throttle_bar.setPrefWidth(300);
		Off_Throttle_waarde.setStyle("-fx-font: 24 arial;");
		Text Off_Throttle_range = new Text("Unlocked 50% - 100% Locked");
		Off_Throttle_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Off_Throttle_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Off_Throttle_heigth = new int[] {1, 1, 1};
		int[] Off_Throttle_width = new int[] {99, 10, 100};
		for (int i = 0; i < Off_Throttle_spacers.length; i++) {
			Off_Throttle_spacers[i].setHeight(Off_Throttle_heigth[i]);
			Off_Throttle_spacers[i].setWidth(Off_Throttle_width[i]);
			Off_Throttle_spacers[i].setVisible(false);
		}
		Off_Throttle.getChildren().addAll(Off_Throttle_text, Off_Throttle_spacers[0], Off_Throttle_bar, Off_Throttle_spacers[1], Off_Throttle_waarde, Off_Throttle_spacers[2], Off_Throttle_range);
		Off_Throttle.setTranslateX(10);
		
		HBox Image = new HBox();
		Rectangle Image_spacer = new Rectangle();
		Image_spacer.setHeight(1);
		Image_spacer.setWidth(400);
		Image_spacer.setVisible(false);
		ImageView Brake_image = new ImageView("images/setup/Transmission.png");
		Brake_image.setPreserveRatio(true);
		Brake_image.setFitWidth(1000);
		Image.getChildren().addAll(Image_spacer, Brake_image);
		
		VBox Items = new VBox();
		
		
		Rectangle[] spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] heigth = new int[] {10, 25, 400};
		int[] width = new int[] {1, 1, 1};
		for (int i = 0; i < spacers.length; i++) {
			spacers[i].setHeight(heigth[i]);
			spacers[i].setWidth(width[i]);
			spacers[i].setVisible(false);
		}
		Items.getChildren().addAll(menu_items, menu_items_underline ,spacers[0], On_Throttle, spacers[1], Off_Throttle, spacers[2], Image);
				
		top_level.setTop(top_box);
		top_box.getChildren().addAll(top_pane, H_line);
		top_pane.getChildren().addAll(top_background, top_box2);
		top_box2.getChildren().addAll(logo, top_spacer, Settings);
		
		
		top_level.setLeft(left_box);
		left_box.getChildren().addAll(left_pane, V_line);
		left_pane.getChildren().addAll(imageView, left_background, left_scroll);
		left_scroll.setContent(left_box2);
		left_box2.getChildren().addAll(menubar_buttons);
		
		top_level.setCenter(center_pane);
		center_pane.getChildren().addAll(background_menu, center_background, Items);
		
	    left_box2.setOnScroll(new EventHandler<ScrollEvent>() {
	        @Override
	        //https://stackoverflow.com/questions/32739269/how-do-i-change-the-amount-by-which-scrollpane-scrolls
	        public void handle(ScrollEvent event) {
	            double deltaY = event.getDeltaY() * 0.25 ; //hogere waarden dan scroll je sneller
	            double width = left_scroll.getContent().getBoundsInLocal().getWidth();
	            double vvalue = left_scroll.getVvalue();
	            left_scroll.setVvalue(vvalue + -deltaY/width);
	        }
	    });

	   Main_menu.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - 130);
		   background_menu.setFitWidth((double) newVal - 130);
	   });

	   Main_menu.window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	   });
	   
	   
//		BorderPane top_level = new BorderPane();
		top_level.setTop(top_box);
		top_level.setLeft(left_box);
		return Transmission;
		
	}
}