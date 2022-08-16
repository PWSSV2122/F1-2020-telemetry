package applicationNew;

import applicationNew.UIBlocks.CenterItems;
import applicationNew.UIBlocks.MenuBar;
import applicationNew.UIBlocks.TopBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
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

public class GraphPage {
	
	public static ComboBox<String> people = new ComboBox<String>();
	
	public static String[] Names = new String[] {"Tyre wear Left Rear", "Tyre wear Right Rear", "Tyre wear Left Front", "Tyre wear Right Front", "Throttle Input", "Brake Input",
			"Speed", "Steering Input", "Fuel Mix", "Fuel In Tank", "Ers Storage", "Ers Mode", "Gear", "Brake Temprature Left Rear", "Brake Temprature Right Rear",
			"Brake Temprature Left Front", "Brake Temprature Right Front", "Tyre Temprature Left Rear", "Tyre Temprature Rigth Rear", "Tyre Temprature Left Front",
			"Tyre Temprature Right Front", "Roll", "Pitch", "Yaw"};
	
	public static NumberAxis xAxis = new NumberAxis(0, 10000, 100);
	public static NumberAxis yAxis = new NumberAxis(0, 100, 2);
	public static LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
	//public static XYChart.Series[] series = new XYChart.Series[24];
	
	public static Scene GraphPage_scene() {
		Scene GraphPage;
		
		BorderPane top_level = new BorderPane();
		
		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		GraphPage = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
		HBox Items = new HBox();
		Text Graph_Text = new Text("Graph");
		Graph_Text.setTranslateX(10);
		Graph_Text.setTranslateY(5);
		Graph_Text.setStyle("-fx-font: 24 arial;");
		
		people.getStylesheets().add("application/css/Dropdown.css");
		people.setTranslateX(30);
		people.setPrefWidth(150);
		people.setOnMouseClicked(e -> {
			ContentUpdate.dropdown_update();
		});
		people.setOnAction(e -> {
			String name = people.getValue();
			for (int i = 0; i < L1.name.length; i++) {
				if (L1.name[i] == name) {
					ContentUpdate.GraphPage_car = i;
					update_Graph();
				}
			}
//			delete_graph_data();
		});
		
		Rectangle[] Deviders = new Rectangle[] {new Rectangle(), new Rectangle()};
		int[] translate = new int[] {20, 40};
		for (int i = 0; i < Deviders.length; i++) {
			Deviders[i].setTranslateX(translate[i]);
			Deviders[i].setHeight(35);
			Deviders[i].setWidth(1);
			Deviders[i].setStroke(Color.RED);
			Deviders[i].setFill(Color.RED);
		}
		Items.getChildren().addAll(Graph_Text, Deviders[0], people, Deviders[1]);
		
		Rectangle menu_items_underline = new Rectangle();
//		menu_items_underline.setWidth(Main.test[0] - 115);
		menu_items_underline.setHeight(1);
		menu_items_underline.setStroke(Color.RED);
		menu_items_underline.setFill(Color.RED);
		
		HBox Selection = new HBox();
		Selection.setVisible(false);
		Rectangle spacer_selection = new Rectangle();
		spacer_selection.setHeight(1);
//		spacer_selection.setWidth(1220);
		spacer_selection.setVisible(false);
		
		StackPane Selection_checkbox = new StackPane();
		Rectangle Selection_background = new Rectangle();
		Selection_background.setHeight(1000 - 82);
		Selection_background.setWidth(250);
		Selection_background.setStroke(Color.rgb(128, 128, 128, 0.9));
		Selection_background.setFill(Color.rgb(128, 128, 128, 0.9));
		
		Button Selectionmenu_popdown = new Button();
		Selectionmenu_popdown.getStylesheets().add("application/css/menu_button.css");
		ImageView Selectionmenu_image_popdown = new ImageView("images/ArrowRight.png");
		Selectionmenu_image_popdown.setPreserveRatio(true);
		Selectionmenu_image_popdown.setFitWidth(45);
		Selectionmenu_popdown.setGraphic(Selectionmenu_image_popdown);
		Selectionmenu_popdown.setTranslateY(380);
		
		VBox CheckBox_stack = new VBox();
		CheckBox_stack.setTranslateX(10);
		CheckBox_stack.setTranslateY(220);
		CheckBox[] CheckBox = new CheckBox[24];
		for (int i = 0; i < Names.length; i++) {
			CheckBox[i] = new CheckBox(Names[i]);
			CheckBox[i].getStylesheets().add("application/css/CheckBox.css");
			CheckBox_stack.getChildren().add(CheckBox[i]);
		}
		CheckBox[0].setOnAction(e -> {
			ContentUpdate.Graph_selection[0] = CheckBox[0].isSelected();
			update_Graph();
		});
		CheckBox[1].setOnAction(e -> {
			ContentUpdate.Graph_selection[1] = CheckBox[1].isSelected();
			update_Graph();
		});
		CheckBox[2].setOnAction(e -> {
			ContentUpdate.Graph_selection[2] = CheckBox[2].isSelected();
			update_Graph();
		});
		CheckBox[3].setOnAction(e -> {
			ContentUpdate.Graph_selection[3] = CheckBox[3].isSelected();
			update_Graph();
		});
		CheckBox[4].setOnAction(e -> {
			ContentUpdate.Graph_selection[4] = CheckBox[4].isSelected();
			update_Graph();
		});
		CheckBox[5].setOnAction(e -> {
			ContentUpdate.Graph_selection[5] = CheckBox[5].isSelected();
			update_Graph();
		});
		CheckBox[6].setOnAction(e -> {
			ContentUpdate.Graph_selection[6] = CheckBox[6].isSelected();
			update_Graph();
		});
		CheckBox[7].setOnAction(e -> {
			ContentUpdate.Graph_selection[7] = CheckBox[7].isSelected();
			update_Graph();
		});
		CheckBox[8].setOnAction(e -> {
			ContentUpdate.Graph_selection[8] = CheckBox[8].isSelected();
			update_Graph();
		});
		CheckBox[9].setOnAction(e -> {
			ContentUpdate.Graph_selection[9] = CheckBox[9].isSelected();
			update_Graph();
		});
		CheckBox[10].setOnAction(e -> {
			ContentUpdate.Graph_selection[10] = CheckBox[10].isSelected();
			update_Graph();
		});
		CheckBox[11].setOnAction(e -> {
			ContentUpdate.Graph_selection[11] = CheckBox[11].isSelected();
			update_Graph();
		});
		CheckBox[12].setOnAction(e -> {
			ContentUpdate.Graph_selection[12] = CheckBox[12].isSelected();
			update_Graph();
		});
		CheckBox[13].setOnAction(e -> {
			ContentUpdate.Graph_selection[13] = CheckBox[13].isSelected();
			update_Graph();
		});
		CheckBox[14].setOnAction(e -> {
			ContentUpdate.Graph_selection[14] = CheckBox[14].isSelected();
			update_Graph();
		});
		CheckBox[15].setOnAction(e -> {
			ContentUpdate.Graph_selection[15] = CheckBox[15].isSelected();
			update_Graph();
		});
		CheckBox[16].setOnAction(e -> {
			ContentUpdate.Graph_selection[16] = CheckBox[16].isSelected();
			update_Graph();
		});
		CheckBox[17].setOnAction(e -> {
			ContentUpdate.Graph_selection[17] = CheckBox[17].isSelected();
			update_Graph();
		});
		CheckBox[18].setOnAction(e -> {
			ContentUpdate.Graph_selection[18] = CheckBox[18].isSelected();
			update_Graph();
		});
		CheckBox[19].setOnAction(e -> {
			ContentUpdate.Graph_selection[19] = CheckBox[19].isSelected();
			update_Graph();
		});
		CheckBox[20].setOnAction(e -> {
			ContentUpdate.Graph_selection[20] = CheckBox[20].isSelected();
			update_Graph();
		});
		CheckBox[21].setOnAction(e -> {
			ContentUpdate.Graph_selection[21] = CheckBox[21].isSelected();
			update_Graph();
		});
		CheckBox[22].setOnAction(e -> {
			ContentUpdate.Graph_selection[22] = CheckBox[22].isSelected();
			update_Graph();
		});
		CheckBox[23].setOnAction(e -> {
			ContentUpdate.Graph_selection[23] = CheckBox[23].isSelected();
			update_Graph();
		});
		
		Selection_checkbox.getChildren().addAll(Selection_background, CheckBox_stack);
		Selection.getChildren().addAll(spacer_selection, Selectionmenu_popdown, Selection_checkbox);
		
		HBox Graph = new HBox();
		Button Selectionmenu_popup = new Button();
		Selectionmenu_popup.getStylesheets().add("application/css/menu_button.css");
		ImageView Selectionmenu_image_popup = new ImageView("images/ArrowLeft.png");
		Selectionmenu_image_popup.setPreserveRatio(true);
		Selectionmenu_image_popup.setFitWidth(45);
		Selectionmenu_popup.setGraphic(Selectionmenu_image_popup);
		Selectionmenu_popup.setOnAction(e -> {
			Selection.setVisible(true);
			Selectionmenu_popup.setVisible(false);
		});
		Selectionmenu_popdown.setOnAction(e -> {
			Selection.setVisible(false);
			Selectionmenu_popup.setVisible(true);
		});
		Selectionmenu_popup.setTranslateX(0);
		Selectionmenu_popup.setTranslateY(380);
		
        lineChart.setCreateSymbols(false);
        lineChart.getXAxis().setTickLabelsVisible(false);
        lineChart.getXAxis().setOpacity(0);
        lineChart.getYAxis().setTickLabelsVisible(false);
        lineChart.getYAxis().setOpacity(0);
//      lineChart.setPrefWidth(1540);
        lineChart.setMinHeight(870);
        lineChart.setMaxHeight(870);
        lineChart.setTranslateX(-20);
        lineChart.setTranslateY(-8);
        lineChart.getStylesheets().add("application/css/Graph.css");
        lineChart.setAnimated(false);

		Graph.getChildren().addAll(lineChart, Selectionmenu_popup);
		
		
		StackPane Graph_and_selection = new StackPane();
		Graph_and_selection.getChildren().addAll(Graph, Selection);
		
		VBox content = new VBox();
		content.getChildren().addAll(Items, menu_items_underline, Graph_and_selection);
		
		Center.getChildren().add(content);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

		Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   menu_items_underline.setWidth((double) newVal - 130);
		   lineChart.setPrefWidth((double) newVal - 130); 
		   spacer_selection.setWidth((double) newVal - 450);
		   
	   });

		return GraphPage;		
	}
	
	private static void update_Graph() {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	lineChart.getData().clear();
		    	int o = 0;
		    	for (int i = 0; i < 24; i++) {
					if (ContentUpdate.Graph_selection[i] == true) {
						lineChart.getData().add(ComparisonPage.series[ContentUpdate.GraphPage_car][i]);
						lineChart.getData().get(o).setName(Names[i]);;
						o++;
					}
				}
		    }
		});
	}
}