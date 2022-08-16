package applicationNew;

import applicationNew.UIBlocks.CenterItems;
import applicationNew.UIBlocks.MenuBar;
import applicationNew.UIBlocks.TopBar;
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

		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		LapTimePage = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
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
				
		Center.getChildren().add(Content);
		
		top_level.setTop(Top);
		top_level.setLeft(Left);
		top_level.setCenter(Center);

	   Main.window.widthProperty().addListener((obs, oldVal, newVal) -> {
		   H_line_Content.setWidth((double) newVal - 130);
		   Tabel.setMaxWidth((double) newVal - 132);
	   });

	   Main.window.heightProperty().addListener((obs, oldVal, newVal) -> {
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
