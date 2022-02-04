package application;

import java.util.concurrent.TimeUnit;

import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class TimingPage {
	
	public static TableView<Tabel_object> Tabel = new TableView<Tabel_object>();
	
	public static Scene TimingPage_scene() {
		Scene TimingPage;
		
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
		
		TimingPage = new Scene(top_level, Main_menu.test[0], Main_menu.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(TimingPage.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(TimingPage.heightProperty());
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
			Main_menu.window.setTitle("F1 Tracker : Main Menu");
			ContentUpdate.TimingPage_refresh = false;});
		
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
			ContentUpdate.TimingPage_refresh = false;
			ContentUpdate.Track_refresh = true;});
		menubar_buttons[1].setOnAction(e -> {Main_menu.window.setScene(Main_menu.SetupPage_Brakes_scene);
			Main_menu.window.setTitle("F1 Tracker : Setup Page Brakes");
			ContentUpdate.TimingPage_refresh = false;
			SetupUpdate.Brakes_Boolean = true;});
		menubar_buttons[2].setOnAction(e -> {Main_menu.window.setScene(Main_menu.ComparisonPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Comparison Page");
			ContentUpdate.TimingPage_refresh = false;
			ContentUpdate.Comparison_refresh = true;});
		menubar_buttons[3].setOnAction(e -> {Main_menu.window.setScene(Main_menu.GraphPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Graph Page");
			ContentUpdate.TimingPage_refresh = false;
			ContentUpdate.Graph_refresh = true;});
		menubar_buttons[4].setOnAction(e -> {Main_menu.window.setScene(Main_menu.LapTimePage_scene);
			Main_menu.window.setTitle("F1 Tracker : Lap Time Page");
			ContentUpdate.TimingPage_refresh = false;
			ContentUpdate.LapTime_refresh = true;});
		menubar_buttons[5].setOnAction(e -> {Main_menu.window.setScene(Main_menu.TimingPage_scene);
			Main_menu.window.setTitle("F1 Tracker : Timing Page");});
		
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
		background_menu.fitHeightProperty().bind(TimingPage.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(TimingPage.heightProperty());
		
		VBox Content = new VBox();
		
		Text Timings = new Text("Timings");
		Timings.setTranslateX(10);
		Timings.setTranslateY(6);
		Timings.setStyle("-fx-font: 24 arial;");
		Content.getChildren().add(Timings);
		
		Rectangle H_line_Content = new Rectangle();
		H_line_Content.setHeight(1);
		H_line_Content.setWidth(1520);
		H_line_Content.setStroke(Color.RED);
		H_line_Content.setTranslateY(11);
		Content.getChildren().add(H_line_Content);
		
		Tabel.getStylesheets().add("application/css/TimingPageTabel.css");
		Tabel.setPrefHeight(2000);
		Tabel.setTranslateY(12);
		Tabel.setTranslateX(1);
		Content.getChildren().add(Tabel);
		
		String[] Colom_names = new String[] {"Position", "Name", "Current_Lap", "S1", "S2", "S3", "Last Lap", "Tyres", "In Pits", "Penalties", "Delta Car In Front"};
		for (int i = 0; i < 11; i++) {
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
		return TimingPage;
		
	}
	public static String MsTo_min_sec_ms(long time, int format) {
		long Minutes = TimeUnit.MILLISECONDS.toMinutes(time);
		long Seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
		long Miliseconds = time - Minutes * 60000- Seconds * 1000;
		String time_string = "";
		if (Seconds <= 9) {
			time_string += "0" + String.valueOf(Seconds);
		} else {
			time_string += String.valueOf(Seconds);
		}
		if (Miliseconds <= 9) {
			time_string += ":00" + String.valueOf(Miliseconds);
		} else if (Miliseconds <= 99) {
			time_string += ":0" + String.valueOf(Miliseconds);
		} else {
			time_string += ":" + String.valueOf(Miliseconds);
		}
		if (format == 1) {
			if (Minutes == 0) {
				return time_string;
			}
		}
		return String.valueOf(Minutes) + ":" + time_string;
	}
	
	public static String Tyre(byte actualTyreCompound) {
		if (actualTyreCompound == 9) {
			return "dry";
		} else if (actualTyreCompound == 10) {
			return "wet";
		} else if (actualTyreCompound == 11) {
			return "super soft";
		} else if (actualTyreCompound == 12) {
			return "soft";
		} else if (actualTyreCompound == 13) {
			return "medium";
		} else if (actualTyreCompound == 14) {
			return "hard";
		} else if (actualTyreCompound == 15) {
			return "wet";
		} else if (actualTyreCompound == 16) {
			return "C5";
		} else if (actualTyreCompound == 17) {
			return "C4";
		} else if (actualTyreCompound == 18) {
			return "C3";
		} else if (actualTyreCompound == 19) {
			return "C2";
		} else if (actualTyreCompound == 20) {
			return "C1";
		} else if (actualTyreCompound == 7) {
			return "inter";
		} else if (actualTyreCompound == 8) {
			return "wet";
		} else {
			return null;
		}
	}
	
	public static String pit(byte pitStatus) {
		if (pitStatus == 0) {
			return null;
		} else if (pitStatus == 1) {
			return "X";
		} else {
			return null;
		}
	}
	
	public static class Tabel_object {
        private final SimpleObjectProperty<String> Position;
        private final SimpleObjectProperty<String> Name;
        private final SimpleObjectProperty<String> Current_Lap;
        private final SimpleObjectProperty<String> S1;
        private final SimpleObjectProperty<String> S2;
        private final SimpleObjectProperty<String> S3;
        private final SimpleObjectProperty<String> Last_Lap;
        private final SimpleObjectProperty<String> Tyres;
        private final SimpleObjectProperty<String> In_Pits;
        private final SimpleObjectProperty<String> Penalties;
        private final SimpleObjectProperty<String> Delta_Car_In_Front;

        public Tabel_object(String Position, String Name, String Current_Lap, String S1, String S2, String S3, String Last_Lap, String Tyres, String In_Pits, String Penalties, String Delta_Car_In_Front) {
            this.Position = new SimpleObjectProperty<>(Position);
            this.Name = new SimpleObjectProperty<>(Name);
            this.Current_Lap = new SimpleObjectProperty<>(Current_Lap);
            this.S1 = new SimpleObjectProperty<>(S1);
            this.S2 = new SimpleObjectProperty<>(S2);
            this.S3 = new SimpleObjectProperty<>(S3);
            this.Last_Lap = new SimpleObjectProperty<>(Last_Lap);
            this.Tyres = new SimpleObjectProperty<>(Tyres);
            this.In_Pits = new SimpleObjectProperty<>(In_Pits);
            this.Penalties = new SimpleObjectProperty<>(Penalties);
            this.Delta_Car_In_Front = new SimpleObjectProperty<>(Delta_Car_In_Front);
        }
        public String getPosition() {
            return Position.get();
        }        
        public String getName() {
            return Name.get();
        }        
        public String getCurrent_Lap() {
            return Current_Lap.get();
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
        public String getLast_Lap() {
            return Last_Lap.get();
        }        
        public String getTyres() {
            return Tyres.get();
        }        
        public String getIn_Pits() {
            return In_Pits.get();
        }       
        public String getPenalties() {
            return Penalties.get();
        }       
        public String getDelta_Car_In_Front() {
            return Delta_Car_In_Front.get();
        }
    }
}