package applicationNew;

import applicationNew.UIBlocks.MenuBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import file_system.L1;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
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
		
		StackPane top_pane = new StackPane();
		StackPane center_pane = new StackPane();
		
		LapTimePage = new Scene(top_level, Main.test[0], Main.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(LapTimePage.widthProperty());
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
		background_menu.fitHeightProperty().bind(LapTimePage.heightProperty());
		
		VBox Content = new VBox();
		HBox content_bar = new HBox();
		
		Text Timings = new Text("Lap Times");
		Timings.setTranslateX(10);
		Timings.setTranslateY(6);
		Timings.setStyle("-fx-font: 24 arial;");
		
		Rectangle[] Deviders = new Rectangle[] {new Rectangle(), new Rectangle()};
		int[] translate = new int[] {20, 40};
		for (int i = 0; i < Deviders.length; i++) {
			Deviders[i].setTranslateX(translate[i]);
			Deviders[i].setHeight(35);
			Deviders[i].setWidth(1);
			Deviders[i].setStroke(Color.RED);
			Deviders[i].setFill(Color.RED);
		}
		
		people.getStylesheets().add("application/css/Dropdown.css");
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
		});
		content_bar.getChildren().addAll(Timings, Deviders[0], people, Deviders[1]);
		Content.getChildren().add(content_bar);
		
		Rectangle H_line_Content = new Rectangle();
		H_line_Content.setHeight(1);
		H_line_Content.setWidth(1520);
		H_line_Content.setStroke(Color.RED);
		H_line_Content.setTranslateY(-1);
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
		
		top_level.setLeft(MenuBar.leftBox(Main.window));
		
		top_level.setCenter(center_pane);
		center_pane.getChildren().addAll(background_menu, center_background, Content);

	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - 130);
		   background_menu.setFitWidth((double) newVal - 130);
		   H_line_Content.setWidth((double) newVal - 130);
		   Tabel.setMaxWidth((double) newVal - 132);
	   });

	   Main.window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	       Tabel.setPrefHeight((double) newVal - 165);
	   });
	   
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
