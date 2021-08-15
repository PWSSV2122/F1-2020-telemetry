package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application{
	
	Stage window;
	Scene scene1, scene2;
	
	Button button1;
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		Label label1 = new Label("scene1");
		Button button1 = new Button("naar scene2");
		button1.setOnAction(e -> window.setScene(scene2));
		
		VBox layout1 = new VBox(15);
		layout1.getChildren().addAll(label1, button1);
		
		
		Label label2 = new Label("scene2");
		Button button2 = new Button("naar scene1");
		button2.setOnAction(e -> window.setScene(scene1));
		
		ImageView image = new ImageView("images/Aerodynamics_outline.png");
		button2.setGraphic(image);
		
		VBox layout2 = new VBox(15);
		layout2.getChildren().addAll(label2, button2);
		scene2 = new Scene(layout2, 1000, 700);
		

		
		
		BorderPane top_level = new BorderPane();
		VBox top_box = new VBox();
		HBox top_box2 = new HBox();
		HBox left_box = new HBox();
		VBox left_box2 = new VBox();
		
		StackPane top_pane = new StackPane();
		StackPane left_pane = new StackPane();
		StackPane center_pane = new StackPane();
		
		ScrollPane left_scroll = new ScrollPane();
		//left_scroll.setMaxHeight(800);
		//left_scroll.setPannable(true);
		left_scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		left_scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
		left_scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
		
		scene1 = new Scene(top_level, 1500, 1000);
		
		Rectangle H_line = new Rectangle();
		H_line.setHeight(1);
		H_line.widthProperty().bind(scene1.widthProperty());
		H_line.setStroke(Color.RED);
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(scene1.heightProperty());
		V_line.setStroke(Color.RED);
		
		Pane top_spacer = new Pane();
		top_spacer.setPrefWidth(100000);
		
		Button logo = new Button();
		ImageView logo_image = new ImageView("images/LOGO_full.png");
		logo.setGraphic(logo_image);
		logo_image.setPreserveRatio(true);
		logo_image.setFitHeight(70);
		logo.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		logo.setOnAction(e -> window.setScene(scene1));
		
		Button Settings = new Button();
		ImageView Settings_image = new ImageView("images/settings.png");
		Settings.setGraphic(Settings_image);
		Settings_image.setPreserveRatio(true);
		Settings_image.setFitHeight(70);
		Settings.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		Settings.setOnAction(e -> settings.display("Settings"));
		
		Pane left_spacer = new Pane();
		left_spacer.setPrefWidth(10);
		left_spacer.setPrefHeight(90);
		
		int menubar_amount = 12;
		Button[] menubar_buttons = new Button[menubar_amount];
		String[] names = new String[menubar_amount];
		ImageView[] menubar_image = new ImageView[menubar_amount];
		for (int i = 0; i < (menubar_amount); i++) {
			menubar_buttons[i] = new Button(names[i]);
			menubar_image[i] = new ImageView("images/menu_placeholder.png");
			menubar_buttons[i].getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
			menubar_image[i].setPreserveRatio(true);
			menubar_image[i].setFitWidth(90);
			menubar_buttons[i].setGraphic(menubar_image[i]);
			menubar_buttons[i].setOnAction(e -> window.setScene(scene2));
		}
		
		Button menubar_1 = new Button();
		ImageView menubar_1_image = new ImageView("images/menu_placeholder.png");
		menubar_1.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_1_image.setPreserveRatio(true);
		menubar_1_image.setFitWidth(90);
		menubar_1.setGraphic(menubar_1_image);
		menubar_1.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_2 = new Button();
		ImageView menubar_2_image = new ImageView("images/menu_placeholder.png");
		menubar_2.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_2_image.setPreserveRatio(true);
		menubar_2_image.setFitWidth(90);
		menubar_2.setGraphic(menubar_2_image);
		menubar_2.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_3 = new Button();
		ImageView menubar_3_image = new ImageView("images/menu_placeholder.png");
		menubar_3.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_3_image.setPreserveRatio(true);
		menubar_3_image.setFitWidth(90);
		menubar_3.setGraphic(menubar_3_image);
		menubar_3.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_4 = new Button();
		ImageView menubar_4_image = new ImageView("images/menu_placeholder.png");
		menubar_4.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_4_image.setPreserveRatio(true);
		menubar_4_image.setFitWidth(90);
		menubar_4.setGraphic(menubar_4_image);
		menubar_4.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_5 = new Button();
		ImageView menubar_5_image = new ImageView("images/menu_placeholder.png");
		menubar_5.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_5_image.setPreserveRatio(true);
		menubar_5_image.setFitWidth(90);
		menubar_5.setGraphic(menubar_5_image);
		menubar_5.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_6 = new Button();
		ImageView menubar_6_image = new ImageView("images/menu_placeholder.png");
		menubar_6.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_6_image.setPreserveRatio(true);
		menubar_6_image.setFitWidth(90);
		menubar_6.setGraphic(menubar_6_image);
		menubar_6.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_7 = new Button();
		ImageView menubar_7_image = new ImageView("images/menu_placeholder.png");
		menubar_7.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_7_image.setPreserveRatio(true);
		menubar_7_image.setFitWidth(90);
		menubar_7.setGraphic(menubar_7_image);
		menubar_7.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_8 = new Button();
		ImageView menubar_8_image = new ImageView("images/menu_placeholder.png");
		menubar_8.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_8_image.setPreserveRatio(true);
		menubar_8_image.setFitWidth(90);
		menubar_8.setGraphic(menubar_8_image);
		menubar_8.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_9 = new Button();
		ImageView menubar_9_image = new ImageView("images/menu_placeholder.png");
		menubar_9.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_9_image.setPreserveRatio(true);
		menubar_9_image.setFitWidth(90);
		menubar_9.setGraphic(menubar_9_image);
		menubar_9.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_10 = new Button();
		ImageView menubar_10_image = new ImageView("images/menu_placeholder.png");
		menubar_10.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_10_image.setPreserveRatio(true);
		menubar_10_image.setFitWidth(90);
		menubar_10.setGraphic(menubar_10_image);
		menubar_10.setOnAction(e -> window.setScene(scene2));
		
		Button menubar_11 = new Button();
		ImageView menubar_11_image = new ImageView("images/menu_placeholder.png");
		menubar_11.getStylesheets().add(getClass().getResource("menu_button.css").toExternalForm());
		menubar_11_image.setPreserveRatio(true);
		menubar_11_image.setFitWidth(90);
		menubar_11.setGraphic(menubar_11_image);
		menubar_11.setOnAction(e -> window.setScene(scene2));
		
		
		Rectangle left_background = new Rectangle();
		left_background.setWidth(111);
		left_background.heightProperty().bind(top_level.heightProperty());
		Color left_menu_gray = Color.rgb(193, 193, 193, 0.85);
		left_background.setStroke(left_menu_gray);
		left_background.setFill(left_menu_gray);
		
		Rectangle top_background = new Rectangle();
		top_background.widthProperty().bind(top_level.widthProperty());
		top_background.setHeight(81);
		Color top_menu_gray = Color.rgb(63, 63, 63, 1);
		top_background.setStroke(top_menu_gray);
		top_background.setFill(top_menu_gray);
		
		Rectangle center_background = new Rectangle();
		
		Color center_menu_gray = Color.rgb(128, 128, 128, 0.9);
		center_background.setStroke(center_menu_gray);
		center_background.setFill(center_menu_gray);
		
		ImageView background_menu = new ImageView("images/background_menu.png"); 
		background_menu.fitHeightProperty().bind(scene1.heightProperty());
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(scene1.heightProperty());
				
		top_level.setTop(top_box);
		top_box.getChildren().addAll(top_pane, H_line);
		top_pane.getChildren().addAll(top_background, top_box2);
		top_box2.getChildren().addAll(logo, top_spacer, Settings);
		
		
		top_level.setLeft(left_box);
		left_box.getChildren().addAll(left_pane, V_line);
		left_pane.getChildren().addAll(imageView, left_background, left_scroll);
		left_scroll.setContent(left_box2);
		left_box2.getChildren().addAll(menubar_buttons);
		left_box2.getChildren().add(left_spacer);
		
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
				System.out.println(window.getX());
				System.out.println(window.getY());
	        }
	    });

	   window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - 130);
		   background_menu.setFitWidth((double) newVal - 130);
	   });

	   window.heightProperty().addListener((obs, oldVal, newVal) -> {
	       center_background.setHeight((double) newVal - 39);
	   });
	   
	   File_reader.Write_encoded.Main(null);
		
		try {
			//window.setY(257.0);
			//window.setX(2677.0);
			window.getIcons().add(new Image("images/LOGO_half.png"));
			window.setScene(scene1);
			window.setTitle("F1 Tracker");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		window.setOnCloseRequest(e -> PreferenceSave());

		/*
		button1 = new Button();
		button1.setText("1");
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(":)");
			}
		});
		 
		kan ook met een lambda expression
		e -> {
			System.out.println(":)");
		};   
		*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void PreferenceSave() {
		
	}
}