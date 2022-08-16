package applicationNew;

import applicationNew.SetupPage.Aerodynamics;
import applicationNew.SetupPage.Brakes;
import applicationNew.SetupPage.Suspension;
import applicationNew.SetupPage.Suspension_Geometry;
import applicationNew.SetupPage.Transmission;
import applicationNew.SetupPage.Tyres;
import applicationNew.UIBlocks.CenterItems;
import applicationNew.UIBlocks.MenuBar;
import applicationNew.UIBlocks.TopBar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import manager.OnClose;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import Global_vars.PageUpdate;
import Global_vars.PageVars;

public class Main extends Application{
	public static double[] windowSize = new double[] {1450, 800};
	
	public static Stage window;
	public static String dir = "src/";
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		window = primaryStage;
			
		BorderPane top_level = new BorderPane();
		
		PageVars.Main_menu = new Scene(top_level, windowSize[0], windowSize[1]);

		VBox Top = TopBar.TopBox(window);
		HBox Left = MenuBar.leftBox(window);
		StackPane Center = CenterItems.CenterItem(window, Left);
		
		GridPane Content = new GridPane();
		Content.setHgap(10);
		Content.setVgap(10);
		
		Button[] Content_buttons = new Button[6];
		String[] Content_names = new String[] {"Track", "Suspension_page", "design_timings", "Graph_compaire", "GRAPH", "Times_driver"};
		ImageView[] Content_images = new ImageView[6];
		int[][] table = new int[][] {{1,1},{3,1},{5,1},{1,3},{3,3},{5,3}};
		for (int i = 0; i < 6; i++) {
			Content_buttons[i] = new Button();
			Content_images[i] = new ImageView("images/home_page/" + Content_names[i] + ".png");
			Content_buttons[i].getStylesheets().add(getClass().getResource("css/Content_button.css").toExternalForm());
			Content_images[i].setPreserveRatio(true);
			Content_images[i].setSmooth(true);
			Content_images[i].setFitWidth(500);
			Content_buttons[i].setGraphic(Content_images[i]);
			Content.add(Content_buttons[i], table[i][0], table[i][1]);
			Content_buttons[i].setPadding(Insets.EMPTY);
			final int button = i + 6;
			Content_buttons[i].setOnAction(e -> {
				PageUpdate.SwitchPage(button);
			});
		}
		
		String[] names = new String[] {"TrackPage", "SetupPage", "ComparisonPage", "GraphPage", "LapTimePage", "TimingPage"};
		Tooltip[] TooltipContentButton = new Tooltip[6];
		for (int i = 0; i <6; i++) {
			TooltipContentButton[i] = new Tooltip();
			TooltipContentButton[i].setText(names[i]);
			Content_buttons[i].setTooltip(TooltipContentButton[i]);
			Tooltip.install(Content_buttons[i], TooltipContentButton[i]);	
			TooltipContentButton[i].setStyle(
					"-fx-background-color: #3F3F3F;"
				+	"-fx-text-fill: white;"
				+	"-fx-border-width: 1px;"
				+	"-fx-border-color: red;"
				+ 	"-fx-font-size: 15px;"
			);	
		}
	
		Box box1 = new Box();
		box1.setHeight(120);
		box1.setWidth(0);
		Content.add(box1, 0, 0);
	
		Box box2 = new Box();
		box2.setHeight(150);
		box2.setWidth(0);
		Content.add(box2, 0, 2);
		
		Center.getChildren().add(Content);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

		window.widthProperty().addListener((obs, oldVal, newVal) -> {
			for(int i = 0; i < 6; i++) {
				   Content_buttons[i].setPrefWidth(((double) newVal - 40 - 150) / 3 - 1000);
				   Content_images[i].setFitWidth(((double) newVal - 40 - 150) / 3 - 2);
			   }
			
			windowSize[0] = (double) newVal;
		});

		window.heightProperty().addListener((obs, oldVal, newVal) -> {
			box1.setHeight((double)newVal / 10);
		       for(int i = 0; i < 6; i++) {
		    	   Content_buttons[i].setPrefHeight(((double) newVal - 40 - 150) / 3 - 1000);
		    	   Content_images[i].setFitHeight(((double) newVal - 40 - 150) / 3 - 2);
			   }
			   box1.setHeight(((double) newVal - 90 - 100) / 7 - 20);
			   box2.setHeight(((double) newVal - 90 - 50) / 6 - 20);
			
			windowSize[1] = (double) newVal;
		});
		
		try {
			//window.setY(257.0);
			//window.setX(2677.0);
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(PageVars.Main_menu);
			window.setTitle("F1 Tracker : Main Menu");
			window.show();
		} catch(Exception e) {
			//custom error message
			e.printStackTrace();
		}
		window.setOnCloseRequest(e -> OnClose.ProgramClose());
		
		PageVars.TrackPage_scene = TrackPage.TrackPage_scene();
//		PageVars.ComparisonPage_scene = ComparisonPage.ComparisonPage_scene();
//		PageVars.GraphPage_scene = GraphPage.GraphPage_scene();
//		PageVars.LapTimePage_scene = LapTimePage.LapTimePage_scene();
//		PageVars.TimingPage_scene = TimingPage.TimingPage_scene();
//		   
//		PageVars.SetupPage_Brakes_scene = Brakes.Brakes_scene();
//		PageVars.SetupPage_Suspension_Geometry_scene = Suspension_Geometry.Suspension_Geometry_scene();
//		PageVars.SetupPage_Suspension_scene = Suspension.Suspension_scene();
//		PageVars.SetupPage_Transmission_scene = Transmission.Transmission_scene();
//		PageVars.SetupPage_Tyres_scene = Tyres.Tyres_scene();
//		PageVars.SetupPage_Aerodynamics_scene = Aerodynamics.Aerodynamics_scene();
		
		System.out.println("Test");
	}
}