package applicationNew.SetupPage;

import applicationNew.Main;
import applicationNew.settings;
import applicationNew.UIBlocks.CenterItems;
import applicationNew.UIBlocks.MenuBar;
import applicationNew.UIBlocks.TopBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import file_system.L1;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
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

public class Suspension {
	
	public static ComboBox<String> people = new ComboBox<String>();
	public static Boolean NoChange = false;
	
	public static ProgressBar Front_Suspension_bar = new ProgressBar(0);
	public static Text Front_Suspension_waarde = new Text(null);
	
	public static ProgressBar Rear_Suspension_bar = new ProgressBar(0);
	public static Text Rear_Suspension_waarde = new Text(null);
	
	public static ProgressBar Front_Anti_Roll_bar_bar = new ProgressBar(0);
	public static Text Front_Anti_Roll_bar_waarde = new Text(null);
	
	public static ProgressBar Rear_Anti_Roll_bar_bar = new ProgressBar(0);
	public static Text Rear_Anti_Roll_bar_waarde = new Text(null);
	
	public static ProgressBar Front_Ride_Height_bar = new ProgressBar(0);
	public static Text Front_Ride_Height_waarde = new Text(null);
	
	public static ProgressBar Rear_Ride_Height_bar = new ProgressBar(0);
	public static Text Rear_Ride_Height_waarde = new Text(null);
	
	public static Text Track = new Text("");
	
	public static Scene Suspension_scene() {
		Scene Suspension;
		
		BorderPane top_level = new BorderPane();
		
		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		Suspension = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
		HBox menu_items = new HBox();
		
		Text Setup = new Text("Setup");
		Setup.setTranslateX(10);
		Setup.setTranslateY(6);
		Setup.setStyle("-fx-font: 24 arial;");
		
		Track.setTranslateX(30);
		Track.setTranslateY(6);
		Track.setStyle("-fx-font: 24 arial;");
		
		ComboBox<String> Pagina = new ComboBox<String>();
		Pagina.setTranslateX(50);
		Pagina.setPrefWidth(200);
		for (int i = 0; i < SetupUpdate.paginas.length; i++) {
			Pagina.getItems().add(SetupUpdate.paginas[i]);
		}
		Pagina.setValue(SetupUpdate.paginas[2]);
		Pagina.getStylesheets().add("application/css/Dropdown.css");
		Pagina.setOnAction(e -> {
			if (NoChange == false) {
				NoChange = true;
				SetupUpdate.Suspension_Boolean = false;
				if (Pagina.getValue() == SetupUpdate.paginas[0]) {
					Main.window.setScene(Main.SetupPage_Brakes_scene);
					SetupUpdate.Brakes_Boolean = true;
					Main.window.setTitle("F1 Tracker : Setup Brake");
				} else if (Pagina.getValue() == SetupUpdate.paginas[1]) {
					Main.window.setScene(Main.SetupPage_Suspension_Geometry_scene);
					SetupUpdate.Suspension_Geometry_Boolean = true;
					Main.window.setTitle("F1 Tracker : Setup Suspension Geometry");
				} else if (Pagina.getValue() == SetupUpdate.paginas[2]) {
					Main.window.setScene(Main.SetupPage_Suspension_scene);
					SetupUpdate.Suspension_Boolean = true;
					Main.window.setTitle("F1 Tracker : Setup Suspension");
				} else if (Pagina.getValue() == SetupUpdate.paginas[3]) {
					Main.window.setScene(Main.SetupPage_Transmission_scene);
					SetupUpdate.Transmission_Boolean = true;
					Main.window.setTitle("F1 Tracker : Setup Transmission");
				} else if (Pagina.getValue() == SetupUpdate.paginas[4]) {
					Main.window.setScene(Main.SetupPage_Tyres_scene);
					SetupUpdate.Tyres_Boolean = true;
					Main.window.setTitle("F1 Tracker : Setup Tyres");
				} else if (Pagina.getValue() == SetupUpdate.paginas[5]) {
					Main.window.setScene(Main.SetupPage_Aerodynamics_scene);
					SetupUpdate.Aerodynamics_Boolean = true;
					Main.window.setTitle("F1 Tracker : Aerodynamics Tyres");
				}
			}
			Pagina.setValue(SetupUpdate.paginas[2]);
			NoChange = false;
		});
		people.getStylesheets().add("application/css/Dropdown.css");
		people.setTranslateX(70);
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
			Deviders[i].setHeight(36);
			Deviders[i].setWidth(1);
			Deviders[i].setStroke(Color.RED);
			Deviders[i].setFill(Color.RED);
		}
		menu_items.getChildren().addAll(Setup, Deviders[0], Track, Deviders[1], Pagina, Deviders[2], people, Deviders[3]);
		
		Rectangle menu_items_underline = new Rectangle();
//		menu_items_underline.setWidth(Main.test[0] - 115);
		menu_items_underline.setHeight(1);
		menu_items_underline.setStroke(Color.RED);
		menu_items_underline.setFill(Color.RED);
		
		HBox Front_Suspension = new HBox();
		Text Front_Suspension_text = new Text("Front Suspension");
		Front_Suspension_text.setStyle("-fx-font: 24 arial;");
		Front_Suspension_bar.setProgress(0.5);
		Front_Suspension_bar.setPrefHeight(28);
		Front_Suspension_bar.setPrefWidth(300);
		Front_Suspension_waarde.setStyle("-fx-font: 24 arial;");
		Text Front_Suspension_range = new Text("Soft 1 - 11 Firm");
		Front_Suspension_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Front_Suspension_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Front_Suspension_heigth = new int[] {1, 1, 1};
		int[] Front_Suspension_width = new int[] {140, 10, 100};
		for (int i = 0; i < Front_Suspension_spacers.length; i++) {
			Front_Suspension_spacers[i].setHeight(Front_Suspension_heigth[i]);
			Front_Suspension_spacers[i].setWidth(Front_Suspension_width[i]);
			Front_Suspension_spacers[i].setVisible(false);
		}
		Front_Suspension.getChildren().addAll(Front_Suspension_text, Front_Suspension_spacers[0], Front_Suspension_bar, Front_Suspension_spacers[1], Front_Suspension_waarde, Front_Suspension_spacers[2], Front_Suspension_range);
		Front_Suspension.setTranslateX(10);
		
		HBox Rear_Suspension = new HBox();
		Text Rear_Suspension_text = new Text("Rear Suspension");
		Rear_Suspension_text.setStyle("-fx-font: 24 arial;");
		Rear_Suspension_bar.setProgress(0.5);
		Rear_Suspension_bar.setPrefHeight(28);
		Rear_Suspension_bar.setPrefWidth(300);
		Rear_Suspension_waarde.setStyle("-fx-font: 24 arial;");
		Text Rear_Suspension_range = new Text("Soft 1 - 11 Firm");
		Rear_Suspension_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Rear_Suspension_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Rear_Suspension_heigth = new int[] {1, 1, 1};
		int[] Rear_Suspension_width = new int[] {144, 10, 100};
		for (int i = 0; i < Rear_Suspension_spacers.length; i++) {
			Rear_Suspension_spacers[i].setHeight(Rear_Suspension_heigth[i]);
			Rear_Suspension_spacers[i].setWidth(Rear_Suspension_width[i]);
			Rear_Suspension_spacers[i].setVisible(false);
		}
		Rear_Suspension.getChildren().addAll(Rear_Suspension_text, Rear_Suspension_spacers[0], Rear_Suspension_bar, Rear_Suspension_spacers[1], Rear_Suspension_waarde, Rear_Suspension_spacers[2], Rear_Suspension_range);
		Rear_Suspension.setTranslateX(10);
		
		HBox Front_Anti_Roll_bar = new HBox();
		Text Front_Anti_Roll_bar_text = new Text("Front Anti Roll Bar");
		Front_Anti_Roll_bar_text.setStyle("-fx-font: 24 arial;");
		Front_Anti_Roll_bar_bar.setProgress(0.5);
		Front_Anti_Roll_bar_bar.setPrefHeight(28);
		Front_Anti_Roll_bar_bar.setPrefWidth(300);
		Front_Anti_Roll_bar_waarde.setStyle("-fx-font: 24 arial;");
		Text Front_Anti_Roll_bar_range = new Text("Soft 1 - 11 Firm");
		Front_Anti_Roll_bar_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Front_Anti_Roll_bar_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Front_Anti_Roll_bar_heigth = new int[] {1, 1, 1};
		int[] Front_Anti_Roll_bar_width = new int[] {132, 10, 100};
		for (int i = 0; i < Front_Anti_Roll_bar_spacers.length; i++) {
			Front_Anti_Roll_bar_spacers[i].setHeight(Front_Anti_Roll_bar_heigth[i]);
			Front_Anti_Roll_bar_spacers[i].setWidth(Front_Anti_Roll_bar_width[i]);
			Front_Anti_Roll_bar_spacers[i].setVisible(false);
		}
		Front_Anti_Roll_bar.getChildren().addAll(Front_Anti_Roll_bar_text, Front_Anti_Roll_bar_spacers[0], Front_Anti_Roll_bar_bar, Front_Anti_Roll_bar_spacers[1], Front_Anti_Roll_bar_waarde, Front_Anti_Roll_bar_spacers[2], Front_Anti_Roll_bar_range);
		Front_Anti_Roll_bar.setTranslateX(10);
		
		HBox Rear_Anti_Roll_bar = new HBox();
		Text Rear_Anti_Roll_bar_text = new Text("Rear Anti Roll Bar");
		Rear_Anti_Roll_bar_text.setStyle("-fx-font: 24 arial;");
		Rear_Anti_Roll_bar_bar.setProgress(0.5);
		Rear_Anti_Roll_bar_bar.setPrefHeight(28);
		Rear_Anti_Roll_bar_bar.setPrefWidth(300);
		Rear_Anti_Roll_bar_waarde.setStyle("-fx-font: 24 arial;");
		Text Rear_Anti_Roll_bar_range = new Text("Soft 1 - 11 Firm");
		Rear_Anti_Roll_bar_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Rear_Anti_Roll_bar_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Rear_Anti_Roll_bar_heigth = new int[] {1, 1, 1};
		int[] Rear_Anti_Roll_bar_width = new int[] {136, 10, 100};
		for (int i = 0; i < Rear_Anti_Roll_bar_spacers.length; i++) {
			Rear_Anti_Roll_bar_spacers[i].setHeight(Rear_Anti_Roll_bar_heigth[i]);
			Rear_Anti_Roll_bar_spacers[i].setWidth(Rear_Anti_Roll_bar_width[i]);
			Rear_Anti_Roll_bar_spacers[i].setVisible(false);
		}
		Rear_Anti_Roll_bar.getChildren().addAll(Rear_Anti_Roll_bar_text, Rear_Anti_Roll_bar_spacers[0], Rear_Anti_Roll_bar_bar, Rear_Anti_Roll_bar_spacers[1], Rear_Anti_Roll_bar_waarde, Rear_Anti_Roll_bar_spacers[2], Rear_Anti_Roll_bar_range);
		Rear_Anti_Roll_bar.setTranslateX(10);
		
		HBox Front_Ride_Height = new HBox();
		Text Front_Ride_Height_text = new Text("Front Ride Height");
		Front_Ride_Height_text.setStyle("-fx-font: 24 arial;");
		Front_Ride_Height_bar.setProgress(0.5);
		Front_Ride_Height_bar.setPrefHeight(28);
		Front_Ride_Height_bar.setPrefWidth(300);
		Front_Ride_Height_waarde.setStyle("-fx-font: 24 arial;");
		Text Front_Ride_Height_range = new Text("Min 1 - 11 Max");
		Front_Ride_Height_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Front_Ride_Height_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Front_Ride_Height_heigth = new int[] {1, 1, 1};
		int[] Front_Ride_Height_width = new int[] {139, 10, 100};
		for (int i = 0; i < Front_Ride_Height_spacers.length; i++) {
			Front_Ride_Height_spacers[i].setHeight(Front_Ride_Height_heigth[i]);
			Front_Ride_Height_spacers[i].setWidth(Front_Ride_Height_width[i]);
			Front_Ride_Height_spacers[i].setVisible(false);
		}
		Front_Ride_Height.getChildren().addAll(Front_Ride_Height_text, Front_Ride_Height_spacers[0], Front_Ride_Height_bar, Front_Ride_Height_spacers[1], Front_Ride_Height_waarde, Front_Ride_Height_spacers[2], Front_Ride_Height_range);
		Front_Ride_Height.setTranslateX(10);
		
		HBox Rear_Ride_Height = new HBox();
		Text Rear_Ride_Height_text = new Text("Rear Ride Height");
		Rear_Ride_Height_text.setStyle("-fx-font: 24 arial;");
		Rear_Ride_Height_bar.setProgress(0.5);
		Rear_Ride_Height_bar.setPrefHeight(28);
		Rear_Ride_Height_bar.setPrefWidth(300);
		Rear_Ride_Height_waarde.setStyle("-fx-font: 24 arial;");
		Text Rear_Ride_Height_range = new Text("Min 1 - 11 Max");
		Rear_Ride_Height_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Rear_Ride_Height_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Rear_Ride_Height_heigth = new int[] {1, 1, 1};
		int[] Rear_Ride_Height_width = new int[] {143, 10, 100};
		for (int i = 0; i < Rear_Ride_Height_spacers.length; i++) {
			Rear_Ride_Height_spacers[i].setHeight(Rear_Ride_Height_heigth[i]);
			Rear_Ride_Height_spacers[i].setWidth(Rear_Ride_Height_width[i]);
			Rear_Ride_Height_spacers[i].setVisible(false);
		}
		Rear_Ride_Height.getChildren().addAll(Rear_Ride_Height_text, Rear_Ride_Height_spacers[0], Rear_Ride_Height_bar, Rear_Ride_Height_spacers[1], Rear_Ride_Height_waarde, Rear_Ride_Height_spacers[2], Rear_Ride_Height_range);
		Rear_Ride_Height.setTranslateX(10);
		
		HBox Image = new HBox();
		Rectangle Image_spacer = new Rectangle();
		Image_spacer.setHeight(1);
		Image_spacer.setWidth(400);
		Image_spacer.setVisible(false);
		ImageView Brake_image = new ImageView("images/setup/Suspension.png");
		Brake_image.setPreserveRatio(true);
		Brake_image.setFitWidth(1010);
		Image.getChildren().addAll(Image_spacer, Brake_image);
		
		VBox Items = new VBox();
		
		
		Rectangle[] spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};
		int[] heigth = new int[] {10, 25, 25, 25, 25, 25, 190};
		int[] width = new int[] {1, 1, 1, 1, 1, 1, 1};
		for (int i = 0; i < spacers.length; i++) {
			spacers[i].setHeight(heigth[i]);
			spacers[i].setWidth(width[i]);
			spacers[i].setVisible(false);
		}
		Items.getChildren().addAll(menu_items, menu_items_underline ,spacers[0], Front_Suspension, spacers[1], Rear_Suspension, spacers[2], Front_Anti_Roll_bar, spacers[3], Rear_Anti_Roll_bar, spacers[4], Front_Ride_Height, spacers[5], Rear_Ride_Height, spacers[6], Image);
				
		Center.getChildren().add(Items);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   menu_items_underline.setWidth((double) newVal - 130);
	   });

	   return Suspension;
	}
}