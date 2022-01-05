package application;
	
import application.SetupPage.Brakes;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main_menu extends Application{
	public static double[] test = new double[] {1650, 1000};
	
	public static Stage window;
	public static Scene Main_menu, ComparisonPage_scene, TrackPage_scene, SetupPage_Brakes_scene, GraphPage_scene, LapTimePage_scene, TimingPage_scene;
	
	Button button1;
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
			
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
		
		Main_menu = new Scene(top_level, test[0], test[1]);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(Main_menu.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(Main_menu.heightProperty());
		V_line.setStroke(Color.RED);
		
		Pane top_spacer = new Pane();
		top_spacer.setPrefWidth(10000000);
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add(getClass().getResource("css/menu_button.css").toExternalForm());
		logo.setOnAction(e -> {window.setScene(Main_menu);
			window.setTitle("F1 Tracker : Main Menu");});
		
		Button Settings = new Button();
		ImageView Settings_image = new ImageView("images/settings.png");
		Settings.setGraphic(Settings_image);
		Settings_image.setPreserveRatio(true);
		Settings_image.setFitHeight(70);
		Settings.getStylesheets().add(getClass().getResource("css/menu_button.css").toExternalForm());
		Settings.setOnAction(e -> settings.display("Settings"));
		
		int menubar_amount = 6;
		Button[] menubar_buttons = new Button[menubar_amount];
		String[] names = new String[] {"TrackPage", "SetupPage", "ComparisonPage", "GraphPage", "LapTimePage", "TimingPage"};
		ImageView[] menubar_image = new ImageView[menubar_amount];
		for (int i = 0; i < (menubar_amount); i++) {
			menubar_buttons[i] = new Button();
			menubar_image[i] = new ImageView("images/icons/" + names[i] + ".png");
			menubar_buttons[i].getStylesheets().add(getClass().getResource("css/menu_button.css").toExternalForm());
			menubar_image[i].setPreserveRatio(true);
			menubar_image[i].setFitWidth(90);
			menubar_buttons[i].setGraphic(menubar_image[i]);
		}
		menubar_buttons[0].setOnAction(e -> {window.setScene(TrackPage_scene);
			window.setTitle("F1 Tracker : Track Page");});
		menubar_buttons[1].setOnAction(e -> {window.setScene(SetupPage_Brakes_scene);
			window.setTitle("F1 Tracker : Setup Page Brakes");});
		menubar_buttons[2].setOnAction(e -> {window.setScene(ComparisonPage_scene);
			window.setTitle("F1 Tracker : Comparison Page");});
		menubar_buttons[3].setOnAction(e -> {window.setScene(GraphPage_scene);
			window.setTitle("F1 Tracker : Graph Page");});
		menubar_buttons[4].setOnAction(e -> {window.setScene(LapTimePage_scene);
			window.setTitle("F1 Tracker : Lap Time Page");});
		menubar_buttons[5].setOnAction(e -> {window.setScene(TimingPage_scene);
			window.setTitle("F1 Tracker : Timing Page");});
		
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
		background_menu.fitHeightProperty().bind(Main_menu.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(Main_menu.heightProperty());
				
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
		center_pane.getChildren().addAll(background_menu, center_background);
		
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

	   window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - 130);
		   background_menu.setFitWidth((double) newVal - 130);
		   test[0] = (double) newVal;
	   });

	   window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	       test[1] = (double) newVal;
	   });

	   //Write_encoded.Names("src/Names/Motion_Packet.enc");
	   
	   TrackPage_scene = TrackPage.TrackPage_scene();
	   SetupPage_Brakes_scene = Brakes.Brakes_scene();
	   ComparisonPage_scene = ComparisonPage.ComparisonPage_scene();
	   GraphPage_scene = GraphPage.GraphPage_scene();
	   LapTimePage_scene = LapTimePage.LapTimePage_scene();
	   TimingPage_scene = TimingPage.TimingPage_scene();
		
		try {
			//window.setY(257.0);
			//window.setX(2677.0);
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(Main_menu);
			window.setTitle("F1 Tracker : Main Menu");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		window.setOnCloseRequest(e -> PreferenceSave());
	}
	
	public void PreferenceSave() {
		
	}
}