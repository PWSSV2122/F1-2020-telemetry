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

public class Transmission {
	
	public static ComboBox<String> people = new ComboBox<String>();
	public static Boolean NoChange = false;
	
	public static ProgressBar On_Throttle_bar = new ProgressBar(0);
	public static Text On_Throttle_waarde = new Text(null);
	
	public static ProgressBar Off_Throttle_bar = new ProgressBar(0);
	public static Text Off_Throttle_waarde = new Text(null);
	
	public static Text Track = new Text("");
	
	public static Scene Transmission_scene() {
		Scene Transmission;
		
		BorderPane top_level = new BorderPane();

		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		Transmission = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
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
		Pagina.setPrefWidth(220);
		for (int i = 0; i < SetupUpdate.paginas.length; i++) {
			Pagina.getItems().add(SetupUpdate.paginas[i]);
		}
		Pagina.getStylesheets().add("application/css/Dropdown.css");
		Pagina.setValue(SetupUpdate.paginas[3]);
		Pagina.setOnAction(e -> {
			if (NoChange == false) {
				NoChange = true;
				SetupUpdate.Transmission_Boolean = false;
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
			Pagina.setValue(SetupUpdate.paginas[3]);
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
				
		Center.getChildren().add(Items);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   menu_items_underline.setWidth((double) newVal - 130);
	   });

		return Transmission;		
	}
}