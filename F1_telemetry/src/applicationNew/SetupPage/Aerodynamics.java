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

public class Aerodynamics {
	
	public static ComboBox<String> people = new ComboBox<String>();
	public static Boolean NoChange = false;
	
	public static ProgressBar Front_Wing_bar = new ProgressBar(0);
	public static Text Front_Wing_waarde = new Text(null);
	
	public static ProgressBar Rear_Wing_bar = new ProgressBar(0);
	public static Text Rear_Wing_waarde = new Text(null);
	
	public static Text Track = new Text("");
	
	public static Scene Aerodynamics_scene() {
		Scene Aerodynamics;
		
		BorderPane top_level = new BorderPane();
		
		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		Aerodynamics = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
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
		Pagina.setPrefWidth(250);
		for (int i = 0; i < SetupUpdate.paginas.length; i++) {
			Pagina.getItems().add(SetupUpdate.paginas[i]);
		}
		Pagina.getStylesheets().add("application/css/Dropdown.css");
		Pagina.setValue(SetupUpdate.paginas[5]);
		Pagina.setOnAction(e -> {
			if (NoChange == false) {
				NoChange = true;
				SetupUpdate.Brakes_Boolean = false;
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
			Pagina.setValue(SetupUpdate.paginas[5]);
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
		
		HBox Front_Wing = new HBox();
		Text Front_Wing_text = new Text("Front Wing Aero");
		Front_Wing_text.setStyle("-fx-font: 24 arial;");
		Front_Wing_bar.setProgress(0.5);
		Front_Wing_bar.setPrefHeight(28);
		Front_Wing_bar.setPrefWidth(300);
		Front_Wing_waarde.setStyle("-fx-font: 24 arial;");
		Text Front_Wing_range = new Text("Min 1 - 11 Max");
		Front_Wing_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Front_Wing_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Front_Wing_heigth = new int[] {1, 1, 1};
		int[] Front_Wing_width = new int[] {100, 10, 100};
		for (int i = 0; i < Front_Wing_spacers.length; i++) {
			Front_Wing_spacers[i].setHeight(Front_Wing_heigth[i]);
			Front_Wing_spacers[i].setWidth(Front_Wing_width[i]);
			Front_Wing_spacers[i].setVisible(false);
		}
		Front_Wing.getChildren().addAll(Front_Wing_text, Front_Wing_spacers[0], Front_Wing_bar, Front_Wing_spacers[1], Front_Wing_waarde, Front_Wing_spacers[2], Front_Wing_range);
		Front_Wing.setTranslateX(10);
		
		HBox Rear_Wing = new HBox();
		Text Rear_Wing_text = new Text("Rear Wing Aero");
		Rear_Wing_text.setStyle("-fx-font: 24 arial;");
		Rear_Wing_bar.setProgress(0.5);
		Rear_Wing_bar.setPrefHeight(28);
		Rear_Wing_bar.setPrefWidth(300);
		Rear_Wing_waarde.setStyle("-fx-font: 24 arial;");
		Text Rear_Wing_range = new Text("Min 1 - 11 Max");
		Rear_Wing_range.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Rear_Wing_spacers = new Rectangle[] {new Rectangle(), new Rectangle(), new Rectangle()};
		int[] Rear_Wing_heigth = new int[] {1, 1, 1};
		int[] Rear_Wing_width = new int[] {104, 10, 100};
		for (int i = 0; i < Rear_Wing_spacers.length; i++) {
			Rear_Wing_spacers[i].setHeight(Rear_Wing_heigth[i]);
			Rear_Wing_spacers[i].setWidth(Rear_Wing_width[i]);
			Rear_Wing_spacers[i].setVisible(false);
		}
		Rear_Wing.getChildren().addAll(Rear_Wing_text, Rear_Wing_spacers[0], Rear_Wing_bar, Rear_Wing_spacers[1], Rear_Wing_waarde, Rear_Wing_spacers[2], Rear_Wing_range);
		Rear_Wing.setTranslateX(10);
		
		HBox Image = new HBox();
		Rectangle Image_spacer = new Rectangle();
		Image_spacer.setHeight(1);
		Image_spacer.setWidth(400);
		Image_spacer.setVisible(false);
		ImageView Brake_image = new ImageView("images/setup/Aerodynamics.png");
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
		Items.getChildren().addAll(menu_items, menu_items_underline ,spacers[0], Front_Wing, spacers[1], Rear_Wing, spacers[2], Image);

		Center.getChildren().add(Items);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   menu_items_underline.setWidth((double) newVal - 130);
	   });
	   
		return Aerodynamics;
	}
}