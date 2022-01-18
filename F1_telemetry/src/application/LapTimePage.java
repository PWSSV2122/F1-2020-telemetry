package application;

import contentUpdate.ContentUpdate;
import file_system.L1;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class LapTimePage {
	
	public static TableView<Tabel_object> Tabel = new TableView<Tabel_object>();
	public static ComboBox<String> people = new ComboBox<String>();
	public static TableColumn<Tabel_object, String> Lap = new TableColumn<Tabel_object, String>("Lap");
	
	public static Scene LapTimePage_scene() {
		Scene LapTimePage;
		
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
		
		LapTimePage = new Scene(top_level, Main_menu.test[0], Main_menu.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(LapTimePage.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(LapTimePage.heightProperty());
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
			ContentUpdate.LapTime_refresh = false;
			ContentUpdate.Track_refresh = true;});
		menubar_buttons[1].setOnAction(e -> {Main_menu.window.setScene(Main_menu.SetupPage_Brakes_scene);
			Main_menu.window.setTitle("F1 Tracker : Setup Page Brakes");
			ContentUpdate.LapTime_refresh = false;
			ContentUpdate.SetupBrakes_refresh = true;});
		menubar_buttons[2].setOnAction(e -> {Main_menu.window.setScene(Main_menu.ComparisonPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Comparison Page");
			ContentUpdate.LapTime_refresh = false;
			ContentUpdate.Comparison_refresh = true;});
		menubar_buttons[3].setOnAction(e -> {Main_menu.window.setScene(Main_menu.GraphPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Graph Page");
			ContentUpdate.LapTime_refresh = false;
			ContentUpdate.Graph_refresh = true;});
		menubar_buttons[4].setOnAction(e -> {Main_menu.window.setScene(Main_menu.LapTimePage_scene);
			Main_menu.window.setTitle("F1 Tracker : Lap Time Page");});
		menubar_buttons[5].setOnAction(e -> {Main_menu.window.setScene(Main_menu.TimingPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Timing Page");
			ContentUpdate.LapTime_refresh = false;
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
		background_menu.fitHeightProperty().bind(LapTimePage.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(LapTimePage.heightProperty());
		
		VBox Content = new VBox();
		HBox content_bar = new HBox();
		
		Text Timings = new Text("Lap Times");
		Timings.setTranslateX(10);
		Timings.setTranslateY(6);
		Timings.setStyle("-fx-font: 24 arial;");
		content_bar.getChildren().add(Timings);
		
		people.setTranslateX(20);
		people.setTranslateY(6);
		people.setPrefWidth(150);
		people.setOnMouseClicked(e -> {
			ContentUpdate.dropdown_update();
		});
		people.setOnAction(e -> {
			String name = people.getValue();
			for (int i = 0; i < L1.name.length; i++) {
				if (L1.name[i] == name) {
					ContentUpdate.TimingPage_car = i;
				}
			}
			System.out.println(ContentUpdate.TimingPage_car);
			System.out.println(L1.name[ContentUpdate.TimingPage_car]);
		});
		content_bar.getChildren().add(people);
		Content.getChildren().add(content_bar);
		
		Rectangle H_line_Content = new Rectangle();
		H_line_Content.setHeight(1);
		H_line_Content.setWidth(1520);
		H_line_Content.setStroke(Color.RED);
		H_line_Content.setTranslateY(11);
		Content.getChildren().add(H_line_Content);
		
		Tabel.getStylesheets().add("application/css/LapTimeTabel.css");
		Tabel.setPrefHeight(2000);
		Tabel.setTranslateY(12);
		Tabel.setTranslateX(1);
		Content.getChildren().add(Tabel);
		
		Lap.setCellValueFactory(new PropertyValueFactory<Tabel_object, String>("Lap".replace(" ", "_")));
		Tabel.getColumns().add(Lap);
		Lap.setSortType(TableColumn.SortType.DESCENDING);
		
		String[] Colom_names = new String[] {"Time", "S1", "S2", "S3"};
		for (int i = 0; i < Colom_names.length; i++) {
			TableColumn<Tabel_object, String> test = new TableColumn<Tabel_object, String>(Colom_names[i]);
			test.setCellValueFactory(new PropertyValueFactory<Tabel_object, String>(Colom_names[i].replace(" ", "_")));
			Tabel.getColumns().addAll(test);
		}
				
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
		center_pane.getChildren().addAll(background_menu, center_background, Content);
		
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
		   H_line_Content.setWidth((double) newVal - 130);
		   Tabel.setMaxWidth((double) newVal - 132);
	   });

	   Main_menu.window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	       Tabel.setPrefHeight((double) newVal - 165);
	   });
	   
	   
//		BorderPane top_level = new BorderPane();
		top_level.setTop(top_box);
		top_level.setLeft(left_box);
		return LapTimePage;
		
	}
	
	public static class Tabel_object {
        private final SimpleObjectProperty<String> Lap;
        private final SimpleObjectProperty<String> Time;
        private final SimpleObjectProperty<String> S1;
        private final SimpleObjectProperty<String> S2;
        private final SimpleObjectProperty<String> S3;

        public Tabel_object(String Lap, String Time, String S1, String S2, String S3) {
            this.Lap = new SimpleObjectProperty<>(Lap);
            this.Time = new SimpleObjectProperty<>(Time);
            this.S1 = new SimpleObjectProperty<>(S1);
            this.S2 = new SimpleObjectProperty<>(S2);
            this.S3 = new SimpleObjectProperty<>(S3);
        }
        public String getLap() {
            return Lap.get();
        }        
        public String getTime() {
            return Time.get();
        }               
        public String getS1() {
            return S1.get();
        }        
        public String getS2() {
            return S2.get();
        }        
        public String getS3() {
            return S3.get();
        }        
    }
}
