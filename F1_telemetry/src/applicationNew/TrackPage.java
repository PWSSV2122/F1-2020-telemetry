package applicationNew;

import applicationNew.UIBlocks.MenuBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import file_system.L1;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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

public class TrackPage {
	
//	public static TableView<Tabel_object> players = new TableView<Tabel_object>();
	public static StackPane center_pane = new StackPane();
//	public static ImageView Track = new ImageView("images/Tracks/" + L1.trackId + ".png");
//	public static ImageView[] Track_Players = new ImageView[22]; 
//	public static TableColumn<Tabel_object, String> Icon = new TableColumn<Tabel_object, String>("Icon");
//	public static TableColumn<Tabel_object, String> Player = new TableColumn<Tabel_object, String>("Player");
	
	public static Scene TrackPage_scene() {
		Scene TrackPage;
		
		BorderPane top_level = new BorderPane();
		VBox top_box = new VBox();
		HBox top_box2 = new HBox();
		
		StackPane top_pane = new StackPane();
				
		TrackPage = new Scene(top_level, Main.test[0], Main.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(TrackPage.widthProperty());
		H_line.setStroke(Color.RED);
		
		Pane top_spacer = new Pane();
		top_spacer.setPrefWidth(10000000);
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add("application/css/menu_button.css");
		logo.setOnAction(e -> {Main.window.setScene(Main.Main_menu);
			Main.window.setTitle("F1 Tracker : Main Menu");});
		
		Button Settings = new Button();
		ImageView Settings_image = new ImageView("images/settings.png");
		Settings.setGraphic(Settings_image);
		Settings_image.setPreserveRatio(true);
		Settings_image.setFitHeight(70);
		Settings.getStylesheets().add("application/css/menu_button.css");
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
		background_menu.fitHeightProperty().bind(TrackPage.heightProperty());
		
		HBox content_top = new HBox();
		
		Text UnderConstruction = new Text("Under Construction!");
		UnderConstruction.setTranslateX(10);
		UnderConstruction.setTranslateY(5);
		UnderConstruction.setStyle("-fx-font: 24 arial;");
		
//		Track.setPreserveRatio(true);
		content_top.getChildren().add( /*Track*/ UnderConstruction);
		
//		players.getStylesheets().add("application/css/TrackPage.css");
//		content_top.getChildren().add(players);
//		
//		Icon.setCellValueFactory(new PropertyValueFactory<Tabel_object, String>("Icon".replace(" ", "_")));
//		Icon.setPrefWidth(50);
//		players.getColumns().add(Icon);
//		
//		Player.setCellValueFactory(new PropertyValueFactory<Tabel_object, String>("Player".replace(" ", "_")));
//		players.getColumns().add(Player);
//		
//		players.setPrefWidth((double)Icon.getWidth() + Player.getWidth() + 2);
		
		
				
		top_level.setTop(top_box);
		top_box.getChildren().addAll(top_pane, H_line);
		top_pane.getChildren().addAll(top_background, top_box2);
		top_box2.getChildren().addAll(logo, top_spacer, Settings);
		
		top_level.setLeft(MenuBar.leftBox(Main.window));
		
		top_level.setCenter(center_pane);
		center_pane.getChildren().addAll(background_menu, center_background, content_top);
		for (int i = 0; i < 22; i++) {
//			Track_Players[i] = new ImageView("images/Track_icons/" + (i + 1) + ".png");
//			Track_Players[i].setPreserveRatio(true);
//			Track_Players[i].setFitHeight(30);
//			center_pane.getChildren().add(Track_Players[i]);
		}
		
	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   	center_background.setWidth((double) newVal - 130);
		   	background_menu.setFitWidth((double) newVal - 130);
//		   	players.setPrefWidth((double)newVal - 130 / 4);
//		   	Track.setFitWidth((double)newVal - 140 - 160);
	   });

	   Main.window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       	center_background.setHeight((double) newVal - 39);
//	       	players.setPrefHeight((double)newVal - 165);
//	       	Track.setFitHeight((double)newVal - 83);
	   });

		return TrackPage;
	}
	
//	public static class Tabel_object {
//        private final SimpleObjectProperty<ImageView> Icon;
//        private final SimpleObjectProperty<String> Player;
//
//        public Tabel_object(ImageView Icon, String Player) {
//            this.Icon = new SimpleObjectProperty<>(Icon);
//            this.Player = new SimpleObjectProperty<>(Player);
//        }
//        public ImageView getIcon() {
//            return Icon.get();
//        }        
//        public String getPlayer() {
//            return Player.get();
//        }               
//       
//    }
}
