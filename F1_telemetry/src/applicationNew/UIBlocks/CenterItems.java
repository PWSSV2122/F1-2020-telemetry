package applicationNew.UIBlocks;

import Global_vars.PageVars;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CenterItems {

	public static StackPane CenterItem(Stage window, HBox Left) {
		StackPane Center = new StackPane();
		
		Rectangle center_background = new Rectangle();
		
		Color center_menu_gray = Color.rgb(128, 128, 128, 0.9);
		center_background.setStroke(center_menu_gray);
		center_background.setFill(center_menu_gray);
		
		ImageView background_menu = new ImageView("images/background.png"); 
		background_menu.fitHeightProperty().bind(PageVars.Main_menu.heightProperty());

		window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setWidth((double) newVal - Left.getWidth());
		   background_menu.setFitWidth((double) newVal - Left.getWidth());
		});

		window.heightProperty().addListener((obs, oldVal, newVal) -> {
		   center_background.setHeight((double) newVal + 1);
		});
		
		Center.getChildren().addAll(background_menu, center_background);
		return Center;
	}
}