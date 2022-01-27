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

public class Tyres {
	
	public static ComboBox<String> people = new ComboBox<String>();
	public static Boolean NoChange = false;
	
	public static ProgressBar Front_Right_bar = new ProgressBar(0);
	public static Text Front_Right_waarde = new Text(null);
	
	public static ProgressBar Front_Left_bar = new ProgressBar(0);
	public static Text Front_Left_waarde = new Text(null);
	
	public static ProgressBar Rear_Right_bar = new ProgressBar(0);
	public static Text Rear_Right_waarde = new Text(null);
	
	public static ProgressBar Rear_Left_bar = new ProgressBar(0);
	public static Text Rear_Left_waarde = new Text(null);
	
	public static Scene Tyres_scene() {
		Scene Tyres;
		
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
		
		Tyres = new Scene(top_level, Main_menu.test[0], Main_menu.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(Tyres.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(Tyres.heightProperty());
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
		background_menu.fitHeightProperty().bind(Tyres.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(Tyres.heightProperty());
		
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
		Pagina.setValue(SetupUpdate.paginas[1]);
		Pagina.setOnAction(e -> {
			if (NoChange == false) {
				NoChange = true;
				SetupUpdate.Tyres_Boolean = false;
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
			Pagina.setValue(SetupUpdate.paginas[1]);
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
		
		HBox Front_Right = new HBox();
		Text Front_Right_text = new Text("Front Right Tyre Pressure");
		Front_Right_text.setStyle("-fx-font: 24 arial;");
		Front_Right_bar.setProgress(0.5);
		Front_Right_bar.setPrefHeight(28);
		Front_Right_bar.setPrefWidth(300);
		Front_Right_waarde.setStyle("-fx-font: 24 arial;");
		Text Front_Right_range = new Text("21,0 - 25,0 PSI");
		Front_Right_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Front_Right_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Front_Right_heigth = new int[] {1, 1, 1};
		int[] Front_Right_width = new int[] {100, 10, 100};
		for (int i = 0; i < Front_Right_spacers.length; i++) {
			Front_Right_spacers[i].setHeight(Front_Right_heigth[i]);
			Front_Right_spacers[i].setWidth(Front_Right_width[i]);
			Front_Right_spacers[i].setVisible(false);
		}
		Front_Right.getChildren().addAll(Front_Right_text, Front_Right_spacers[0], Front_Right_bar, Front_Right_spacers[1], Front_Right_waarde, Front_Right_spacers[2], Front_Right_range);
		Front_Right.setTranslateX(10);
		
		HBox Front_Left = new HBox();
		Text Front_Left_text = new Text("Front Left Tyre Pressure");
		Front_Left_text.setStyle("-fx-font: 24 arial;");
		Front_Left_bar.setProgress(0.5);
		Front_Left_bar.setPrefHeight(28);
		Front_Left_bar.setPrefWidth(300);
		Front_Left_waarde.setStyle("-fx-font: 24 arial;");
		Text Front_Left_range = new Text("21,0 - 25,0 PSI");
		Front_Left_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Front_Left_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Front_Left_heigth = new int[] {1, 1, 1};
		int[] Front_Left_width = new int[] {116, 10, 100};
		for (int i = 0; i < Front_Left_spacers.length; i++) {
			Front_Left_spacers[i].setHeight(Front_Left_heigth[i]);
			Front_Left_spacers[i].setWidth(Front_Left_width[i]);
			Front_Left_spacers[i].setVisible(false);
		}
		Front_Left.getChildren().addAll(Front_Left_text, Front_Left_spacers[0], Front_Left_bar, Front_Left_spacers[1], Front_Left_waarde, Front_Left_spacers[2], Front_Left_range);
		Front_Left.setTranslateX(10);
		
		HBox Rear_Right = new HBox();
		Text Rear_Right_text = new Text("Rear Right Tyre Pressure");
		Rear_Right_text.setStyle("-fx-font: 24 arial;");
		Rear_Right_bar.setProgress(0.5);
		Rear_Right_bar.setPrefHeight(28);
		Rear_Right_bar.setPrefWidth(300);
		Rear_Right_waarde.setStyle("-fx-font: 24 arial;");
		Text Rear_Right_range = new Text("19,5 - 23,5 PSI");
		Rear_Right_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Rear_Right_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Rear_Right_heigth = new int[] {1, 1, 1};
		int[] Rear_Right_width = new int[] {104, 10, 100};
		for (int i = 0; i < Rear_Right_spacers.length; i++) {
			Rear_Right_spacers[i].setHeight(Rear_Right_heigth[i]);
			Rear_Right_spacers[i].setWidth(Rear_Right_width[i]);
			Rear_Right_spacers[i].setVisible(false);
		}
		Rear_Right.getChildren().addAll(Rear_Right_text, Rear_Right_spacers[0], Rear_Right_bar, Rear_Right_spacers[1], Rear_Right_waarde, Rear_Right_spacers[2], Rear_Right_range);
		Rear_Right.setTranslateX(10);
		
		HBox Rear_Left = new HBox();
		Text Rear_Left_text = new Text("Rear Left Tyre Pressure");
		Rear_Left_text.setStyle("-fx-font: 24 arial;");
		Rear_Left_bar.setProgress(0.5);
		Rear_Left_bar.setPrefHeight(28);
		Rear_Left_bar.setPrefWidth(300);
		Rear_Left_waarde.setStyle("-fx-font: 24 arial;");
		Text Rear_Left_range = new Text("19,5 - 23,5 PSI");
		Rear_Left_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Rear_Left_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Rear_Left_heigth = new int[] {1, 1, 1};
		int[] Rear_Left_width = new int[] {120, 10, 100};
		for (int i = 0; i < Rear_Left_spacers.length; i++) {
			Rear_Left_spacers[i].setHeight(Rear_Left_heigth[i]);
			Rear_Left_spacers[i].setWidth(Rear_Left_width[i]);
			Rear_Left_spacers[i].setVisible(false);
		}
		Rear_Left.getChildren().addAll(Rear_Left_text, Rear_Left_spacers[0], Rear_Left_bar, Rear_Left_spacers[1], Rear_Left_waarde, Rear_Left_spacers[2], Rear_Left_range);
		Rear_Left.setTranslateX(10);
		
		HBox Image = new HBox();
		Rectangle Image_spacer = new Rectangle();
		Image_spacer.setHeight(1);
		Image_spacer.setWidth(400);
		Image_spacer.setVisible(false);
		ImageView Brake_image = new ImageView("images/setup/Tyres.png");
		Brake_image.setPreserveRatio(true);
		Brake_image.setFitWidth(1010);
		Image.getChildren().addAll(Image_spacer, Brake_image);
		
		VBox Items = new VBox();
		
		
		Rectangle[] spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};
		int[] heigth = new int[] {10, 25, 25, 25, 290};
		int[] width = new int[] {1, 1, 1, 1, 1};
		for (int i = 0; i < spacers.length; i++) {
			spacers[i].setHeight(heigth[i]);
			spacers[i].setWidth(width[i]);
			spacers[i].setVisible(false);
		}
		Items.getChildren().addAll(menu_items, menu_items_underline ,spacers[0], Front_Right, spacers[1], Front_Left, spacers[2], Rear_Right, spacers[3], Rear_Left, spacers[4], Image);
				
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
		return Tyres;
		
	}
}