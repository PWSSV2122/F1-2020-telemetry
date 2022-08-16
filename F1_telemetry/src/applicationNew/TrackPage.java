package applicationNew;

import applicationNew.UIBlocks.CenterItems;
import applicationNew.UIBlocks.MenuBar;
import applicationNew.UIBlocks.TopBar;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TrackPage {

	public static StackPane center_pane = new StackPane();
	
	public static Scene TrackPage_scene() {
		Scene TrackPage;
		
		BorderPane top_level = new BorderPane();
		
		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		TrackPage = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
		Text UnderConstruction = new Text("Under Construction!");
		UnderConstruction.setTranslateX(10);
		UnderConstruction.setTranslateY(5);
		UnderConstruction.setStyle("-fx-font: 24 arial;");

		Center.getChildren().add(UnderConstruction);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

		return TrackPage;
	}
}