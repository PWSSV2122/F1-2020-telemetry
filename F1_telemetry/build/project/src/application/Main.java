package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
		scene1 = new Scene(layout1, 200, 200);
		
		Label label2 = new Label("scene2");
		Button button2 = new Button("naar scene1");
		button2.setOnAction(e -> window.setScene(scene1));
		
		VBox layout2 = new VBox(15);
		layout2.getChildren().addAll(label2, button2);
		scene2 = new Scene(layout2, 300, 300);
		
		try {
			window.setScene(scene1);
			window.setTitle("F1 Tracker");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

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
}