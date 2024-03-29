package applicationNew;

import Global_vars.PageVars;
import applicationNew.UIBlocks.MenuBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import data_compute.Historical_graph_data;
import file_system.L1;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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

public class ComparisonPage {
	
	public static ComboBox<String> people = new ComboBox<String>();
	public static ComboBox<String> people2 = new ComboBox<String>();
	
	public static String[] Names = new String[] {"Tyre wear Left Rear", "Tyre wear Right Rear", "Tyre wear Left Front", "Tyre wear Right Front", "Throttle Input", "Brake Input",
			"Speed", "Steering Input", "Fuel Mix", "Fuel In Tank", "Ers Storage", "Ers Mode", "Gear", "Brake Temprature Left Rear", "Brake Temprature Right Rear",
			"Brake Temprature Left Front", "Brake Temprature Right Front", "Tyre Temprature Left Rear", "Tyre Temprature Rigth Rear", "Tyre Temprature Left Front",
			"Tyre Temprature Right Front", "Roll", "Pitch", "Yaw"};
	
	public static NumberAxis xAxis = new NumberAxis(0, 10000, 100);
	public static NumberAxis yAxis = new NumberAxis(0, 100, 2);
	public static LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
	public static XYChart.Series[][] series = new XYChart.Series[22][24];
	public static XYChart.Series player_1 = new XYChart.Series();
	public static XYChart.Series player_2 = new XYChart.Series();
	
	public static void ComparisonPage_scene() {
		Scene ComparisonPage;
		
		BorderPane top_level = new BorderPane();
		VBox top_box = new VBox();
		HBox top_box2 = new HBox();
		
		StackPane top_pane = new StackPane();
		StackPane center_pane = new StackPane();
				
		ComparisonPage = new Scene(top_level, Main.test[0], Main.test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(ComparisonPage.widthProperty());
		H_line.setStroke(Color.RED);
		
		Pane top_spacer = new Pane();
		top_spacer.setPrefWidth(10000000);
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add("application/css/menu_button.css");
		logo.setOnAction(e -> {Main.window.setScene(PageVars.Main_menu);
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
		background_menu.fitHeightProperty().bind(ComparisonPage.heightProperty());
		
		HBox Items = new HBox();
		Text Graph_Text = new Text("Graph");
		Graph_Text.setTranslateX(10);
		Graph_Text.setTranslateY(5);
		Graph_Text.setStyle("-fx-font: 24 arial;");
		
		ComboBox<String> Compair_select = new ComboBox<String>();
		Compair_select.getStylesheets().add("application/css/Dropdown.css");
		Compair_select.setTranslateX(30);
		Compair_select.setPrefWidth(190);
		Compair_select.setValue(Names[0]);
		for (int i = 0; i < Names.length; i++) {
	    	Compair_select.getItems().add(Names[i]);
	    }
		Compair_select.setOnAction(e -> {
			for (int i = 0; i < Names.length; i++) {
				if (Names[i] == Compair_select.getValue()) {
					ContentUpdate.GraphCompair_graph = i;
					final int p = i;
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
					    	lineChart.getData().clear();
							lineChart.getData().add(series[ContentUpdate.GraphCompair_car1][p]);
							lineChart.getData().add(series[ContentUpdate.GraphCompair_car2][p]);
							lineChart.getData().get(0).setName(L1.name[ContentUpdate.GraphCompair_car1]);
							lineChart.getData().get(1).setName(L1.name[ContentUpdate.GraphCompair_car2]);
					    }
					});
				}
			}
		});
		
		people.getStylesheets().add("application/css/Dropdown.css");
		people.setTranslateX(50);
		people.setPrefWidth(150);
		people.setOnMouseClicked(e -> {
			ContentUpdate.dropdown_update();
		});
		people.setOnAction(e -> {
			String name = people.getValue();
			for (int i = 0; i < L1.name.length; i++) {
				if (L1.name[i] == name) {
					ContentUpdate.GraphCompair_car1 = i;
					final int p = i;
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
					    	lineChart.getData().clear();
							lineChart.getData().add(series[p][ContentUpdate.GraphCompair_graph]);
							lineChart.getData().add(series[ContentUpdate.GraphCompair_car2][ContentUpdate.GraphCompair_graph]);
							lineChart.getData().get(0).setName(L1.name[ContentUpdate.GraphCompair_car1]);
							lineChart.getData().get(1).setName(L1.name[ContentUpdate.GraphCompair_car2]);
					    }
					});
				}
			}
		});
		
		people2.getStylesheets().add("application/css/Dropdown.css");
		people2.setTranslateX(70);
		people2.setPrefWidth(150);
		people2.setOnMouseClicked(e -> {
			ContentUpdate.dropdown_update();
		});
		people2.setOnAction(e -> {
			String name = people2.getValue();
			for (int i = 0; i < L1.name.length; i++) {
				if (L1.name[i] == name) {
					ContentUpdate.GraphCompair_car2 = i;
					final int p = i;
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
					    	lineChart.getData().clear();
							lineChart.getData().add(series[ContentUpdate.GraphCompair_car1][ContentUpdate.GraphCompair_graph]);
							lineChart.getData().add(series[p][ContentUpdate.GraphCompair_graph]);
							lineChart.getData().get(0).setName(L1.name[ContentUpdate.GraphCompair_car1]);
							lineChart.getData().get(1).setName(L1.name[ContentUpdate.GraphCompair_car2]);
					    }
					});
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
		Items.getChildren().addAll(Graph_Text, Deviders[0], Compair_select, Deviders[1], people, Deviders[2], people2, Deviders[3]);
		
		Rectangle menu_items_underline = new Rectangle();
//		menu_items_underline.setWidth(Main.test[0] - 115);
		menu_items_underline.setHeight(1);
		menu_items_underline.setStroke(Color.RED);
		menu_items_underline.setFill(Color.RED);
		
		VBox CheckBox_stack = new VBox();
		CheckBox_stack.setTranslateX(10);
		CheckBox_stack.setTranslateY(220);
		CheckBox[] CheckBox = new CheckBox[24];
		for (int i = 0; i < Names.length; i++) {
			CheckBox[i] = new CheckBox(Names[i]);
			CheckBox[i].getStylesheets().add("application/css/CheckBox.css");
			CheckBox_stack.getChildren().add(CheckBox[i]);
		}
		
        lineChart.setCreateSymbols(false);
        lineChart.getXAxis().setTickLabelsVisible(false);
        lineChart.getXAxis().setOpacity(0);
        lineChart.getYAxis().setTickLabelsVisible(false);
        lineChart.getYAxis().setOpacity(0);
        lineChart.setPrefWidth(1540);
        lineChart.setMinHeight(870);
        lineChart.setMaxHeight(870);
        lineChart.setTranslateX(-10);
        lineChart.setTranslateY(-8);
        lineChart.getStylesheets().add("application/css/CompairGraph.css");
        lineChart.setAnimated(false);
        
        for (int o = 0; o < 22; o++) {
        	for (int i = 0; i< 24; i++) {
    			series[o][i] = new XYChart.Series();
    			series[o][i].setName(Names[i]);
    		}
        }
        lineChart.getData().add(series[0][0]);
        lineChart.getData().add(series[1][0]);
        //lineChart.getData().get(0).setName(L1.name[ContentUpdate.GraphCompair_car1]);
		//lineChart.getData().get(1).setName(L1.name[ContentUpdate.GraphCompair_car2]);
		VBox content = new VBox();
		content.getChildren().addAll(Items, menu_items_underline, lineChart);
				
		top_level.setTop(top_box);
		top_box.getChildren().addAll(top_pane, H_line);
		top_pane.getChildren().addAll(top_background, top_box2);
		top_box2.getChildren().addAll(logo, top_spacer, Settings);
		
		
		top_level.setLeft(MenuBar.leftBox(Main.window));
		
		top_level.setCenter(center_pane);
		center_pane.getChildren().addAll(background_menu, center_background, content);

	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - 130);
		   background_menu.setFitWidth((double) newVal - 130);
		   menu_items_underline.setWidth((double) newVal - 130);
	   });

	   Main.window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	   });
	   
	   
//		BorderPane top_level = new BorderPane();
		top_level.setTop(top_box);
	}
}
