package application;

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
		HBox left_box = new HBox();
		VBox left_box2 = new VBox();
		
		StackPane top_pane = new StackPane();
		StackPane left_pane = new StackPane();
		
		ScrollPane left_scroll = new ScrollPane();
		left_scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
		
		TrackPage = new Scene(top_level, Main.test[0], Main.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(TrackPage.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(TrackPage.heightProperty());
		V_line.setStroke(Color.RED);
		
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
		
		Tooltip[] tooltip_menubuttons = new Tooltip[6];
		for (int i = 0; i <6; i++) {
				tooltip_menubuttons[i] = new Tooltip();
				tooltip_menubuttons[i].setText(names[i]);
				menubar_buttons[i].setTooltip(tooltip_menubuttons[i]);
				Tooltip.install(menubar_buttons[i], tooltip_menubuttons[i]);	
				tooltip_menubuttons[i].setStyle(
						"-fx-background-color: #3F3F3F;"
					+	"-fx-text-fill: white;"
					+	"-fx-border-width: 1px;"
					+	"-fx-border-color: red;"
					+ 	"-fx-font-size: 15px;"
				);	
		}
		
		menubar_buttons[0].setOnAction(e -> {Main.window.setScene(Main.TrackPage_scene);
			Main.window.setTitle("F1 Tracker : Track Page");
			ContentUpdate.Track_refresh = false;
			ContentUpdate.Track_refresh = true;});
		menubar_buttons[1].setOnAction(e -> {Main.window.setScene(Main.SetupPage_Brakes_scene);
			Main.window.setTitle("F1 Tracker : Setup Page Brakes");
			ContentUpdate.Track_refresh = false;
			SetupUpdate.Brakes_Boolean = true;});
		menubar_buttons[2].setOnAction(e -> {Main.window.setScene(Main.ComparisonPage_scene);
			Main.window.setTitle("F1 Tracker : Comparison Page");
			ContentUpdate.Track_refresh = false;
			ContentUpdate.Comparison_refresh = true;});
		menubar_buttons[3].setOnAction(e -> {Main.window.setScene(Main.GraphPage_scene);
			Main.window.setTitle("F1 Tracker : Graph Page");
			ContentUpdate.Track_refresh = false;
			ContentUpdate.Graph_refresh = true;});
		menubar_buttons[4].setOnAction(e -> {Main.window.setScene(Main.LapTimePage_scene);
			Main.window.setTitle("F1 Tracker : Lap Time Page");
			ContentUpdate.Track_refresh = false;
			ContentUpdate.LapTime_refresh = true;});
		menubar_buttons[5].setOnAction(e -> {Main.window.setScene(Main.TimingPage_scene);
			Main.window.setTitle("F1 Tracker : Timing Page");
			ContentUpdate.Track_refresh = false;
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
		background_menu.fitHeightProperty().bind(TrackPage.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(TrackPage.heightProperty());
		
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
		
		
		top_level.setLeft(left_box);
		left_box.getChildren().addAll(left_pane, V_line);
		left_pane.getChildren().addAll(imageView, left_background, left_scroll);
		left_scroll.setContent(left_box2);
		left_box2.getChildren().addAll(menubar_buttons);
		
		top_level.setCenter(center_pane);
		center_pane.getChildren().addAll(background_menu, center_background, content_top);
		for (int i = 0; i < 22; i++) {
//			Track_Players[i] = new ImageView("images/Track_icons/" + (i + 1) + ".png");
//			Track_Players[i].setPreserveRatio(true);
//			Track_Players[i].setFitHeight(30);
//			center_pane.getChildren().add(Track_Players[i]);
		}
		
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

//		BorderPane top_level = new BorderPane();
		top_level.setTop(top_box);
		top_level.setLeft(left_box);
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
