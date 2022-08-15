package applicationNew.UIBlocks;

import Global_vars.PageUpdate;
import Global_vars.PageVars;
import applicationNew.Main;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MenuBar {

	
	public static HBox leftBox(Stage window) {
		HBox left_box = new HBox();
		
		Rectangle V_line = new Rectangle();
		V_line.setWidth(1);
		V_line.heightProperty().bind(window.heightProperty());
		V_line.setStroke(Color.RED);
		
		StackPane left_pane = new StackPane();
		
		ImageView imageView = new ImageView("images/menubar_img.png"); 
		imageView.setFitWidth(111);
		imageView.fitHeightProperty().bind(window.heightProperty());
		
		Rectangle left_background = new Rectangle();
		left_background.setWidth(111);
		left_background.heightProperty().bind(window.heightProperty());
		left_background.setStroke(Color.rgb(193, 193, 193, 0.85));
		left_background.setFill(Color.rgb(193, 193, 193, 0.85));
		
		ScrollPane left_scroll = new ScrollPane();
		left_scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
		
		VBox left_box2 = new VBox();
		
		Button[] menubar_buttons = new Button[6];
		String[] names = new String[] {"TrackPage", "SetupPage", "ComparisonPage", "GraphPage", "LapTimePage", "TimingPage"};
		ImageView[] menubar_image = new ImageView[6];
		for (int i = 0; i < 6; i++) {
			menubar_buttons[i] = new Button();
			menubar_image[i] = new ImageView("images/icons/" + names[i] + ".png");
			menubar_buttons[i].getStylesheets().add(Main.class.getResource("css/menu_button.css").toExternalForm());
			menubar_image[i].setPreserveRatio(true);
			menubar_image[i].setFitWidth(90);
			menubar_buttons[i].setGraphic(menubar_image[i]);
			final int temp = i + 6;
			menubar_buttons[i].setOnAction(e -> {
				PageUpdate.SwitchPage(temp);
			});
		}
		
		Tooltip[] tooltip_menubuttons = new Tooltip[6];
		for (int i = 0; i <6; i++) {
				tooltip_menubuttons[i] = new Tooltip();
				tooltip_menubuttons[i].setText(names[i]);
				menubar_buttons[i].setTooltip(tooltip_menubuttons[i]);
				Tooltip.install(menubar_buttons[i], tooltip_menubuttons[i]);	
				tooltip_menubuttons[i].setStyle(
						"-fx-background-color: #3F3F3F;"
					+	"-fx-text-fill: white;"
					+	"-fx-border-width: 1px;"
					+	"-fx-border-color: red;"
					+ 	"-fx-font-size: 15px;"
				);	
		}
		
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
		
		left_box.getChildren().addAll(left_pane, V_line);
		left_pane.getChildren().addAll(imageView, left_background, left_scroll);
		left_scroll.setContent(left_box2);
		left_box2.getChildren().addAll(menubar_buttons);

		return left_box;
	}
}