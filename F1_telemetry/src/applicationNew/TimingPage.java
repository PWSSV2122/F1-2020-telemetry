package applicationNew;

import java.util.concurrent.TimeUnit;

import applicationNew.UIBlocks.CenterItems;
import applicationNew.UIBlocks.MenuBar;
import applicationNew.UIBlocks.TopBar;
import contentUpdate.ContentUpdate;
import contentUpdate.SetupUpdate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class TimingPage {
	
	public static TableView<Tabel_object> Tabel = new TableView<Tabel_object>();
	
	public static Scene TimingPage_scene() {
		Scene TimingPage;
		
		BorderPane top_level = new BorderPane();
		
		VBox Top = TopBar.TopBox(Main.window);
		HBox Left = MenuBar.leftBox(Main.window);
		StackPane Center = CenterItems.CenterItem(Main.window, Left);
				
		TimingPage = new Scene(top_level, Main.windowSize[0], Main.windowSize[1]);
		
		VBox Content = new VBox();
		
		Text Timings = new Text("Timings");
		Timings.setTranslateX(10);
		Timings.setTranslateY(6);
		Timings.setStyle("-fx-font: 24 arial;");
		
		Rectangle Deviders = new Rectangle();
		Deviders.setTranslateX(20);
		Deviders.setHeight(35);
		Deviders.setWidth(1);
		Deviders.setStroke(Color.RED);
		Deviders.setFill(Color.RED);
		
		HBox Items = new HBox();
		Items.getChildren().addAll(Timings, Deviders);
		Content.getChildren().add(Items);
		
		Rectangle H_line_Content = new Rectangle();
		H_line_Content.setHeight(1);
		H_line_Content.setWidth(1520);
		H_line_Content.setStroke(Color.RED);
		Content.getChildren().add(H_line_Content);
		
		Tabel.getStylesheets().add("application/css/TimingPageTabel.css");
		Tabel.setPrefHeight(2000);
		Tabel.setTranslateX(1);
		Content.getChildren().add(Tabel);
		
		String[] Colom_names = new String[] {"Position", "Name", "Current_Lap", "S1", "S2", "S3", "Last Lap", "Tyres", "In Pits", "Penalties", "Delta Car In Front"};
		for (int i = 0; i < 11; i++) {
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

		return TimingPage;
		
	}
	public static String MsTo_min_sec_ms(long time, int format) {
		long Minutes = TimeUnit.MILLISECONDS.toMinutes(time);
		long Seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60;
		long Miliseconds = time - Minutes * 60000- Seconds * 1000;
		String time_string = "";
		if (Seconds <= 9) {
			time_string += "0" + String.valueOf(Seconds);
		} else {
			time_string += String.valueOf(Seconds);
		}
		if (Miliseconds <= 9) {
			time_string += ":00" + String.valueOf(Miliseconds);
		} else if (Miliseconds <= 99) {
			time_string += ":0" + String.valueOf(Miliseconds);
		} else {
			time_string += ":" + String.valueOf(Miliseconds);
		}
		if (format == 1) {
			if (Minutes == 0) {
				return time_string;
			}
		}
		return String.valueOf(Minutes) + ":" + time_string;
	}
	
	public static String Tyre(byte actualTyreCompound) {
		if (actualTyreCompound == 9) {
			return "dry";
		} else if (actualTyreCompound == 10) {
			return "wet";
		} else if (actualTyreCompound == 11) {
			return "super soft";
		} else if (actualTyreCompound == 12) {
			return "soft";
		} else if (actualTyreCompound == 13) {
			return "medium";
		} else if (actualTyreCompound == 14) {
			return "hard";
		} else if (actualTyreCompound == 15) {
			return "wet";
		} else if (actualTyreCompound == 16) {
			return "C5";
		} else if (actualTyreCompound == 17) {
			return "C4";
		} else if (actualTyreCompound == 18) {
			return "C3";
		} else if (actualTyreCompound == 19) {
			return "C2";
		} else if (actualTyreCompound == 20) {
			return "C1";
		} else if (actualTyreCompound == 7) {
			return "inter";
		} else if (actualTyreCompound == 8) {
			return "wet";
		} else {
			return null;
		}
	}
	
	public static String pit(byte pitStatus) {
		if (pitStatus == 0) {
			return null;
		} else if (pitStatus == 1) {
			return "X";
		} else {
			return null;
		}
	}
	
	public static class Tabel_object {
        private final SimpleObjectProperty<String> Position;
        private final SimpleObjectProperty<String> Name;
        private final SimpleObjectProperty<String> Current_Lap;
        private final SimpleObjectProperty<String> S1;
        private final SimpleObjectProperty<String> S2;
        private final SimpleObjectProperty<String> S3;
        private final SimpleObjectProperty<String> Last_Lap;
        private final SimpleObjectProperty<String> Tyres;
        private final SimpleObjectProperty<String> In_Pits;
        private final SimpleObjectProperty<String> Penalties;
        private final SimpleObjectProperty<String> Delta_Car_In_Front;

        public Tabel_object(String Position, String Name, String Current_Lap, String S1, String S2, String S3, String Last_Lap, String Tyres, String In_Pits, String Penalties, String Delta_Car_In_Front) {
            this.Position = new SimpleObjectProperty<>(Position);
            this.Name = new SimpleObjectProperty<>(Name);
            this.Current_Lap = new SimpleObjectProperty<>(Current_Lap);
            this.S1 = new SimpleObjectProperty<>(S1);
            this.S2 = new SimpleObjectProperty<>(S2);
            this.S3 = new SimpleObjectProperty<>(S3);
            this.Last_Lap = new SimpleObjectProperty<>(Last_Lap);
            this.Tyres = new SimpleObjectProperty<>(Tyres);
            this.In_Pits = new SimpleObjectProperty<>(In_Pits);
            this.Penalties = new SimpleObjectProperty<>(Penalties);
            this.Delta_Car_In_Front = new SimpleObjectProperty<>(Delta_Car_In_Front);
        }
        public String getPosition() {
            return Position.get();
        }        
        public String getName() {
            return Name.get();
        }        
        public String getCurrent_Lap() {
            return Current_Lap.get();
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
        public String getLast_Lap() {
            return Last_Lap.get();
        }        
        public String getTyres() {
            return Tyres.get();
        }        
        public String getIn_Pits() {
            return In_Pits.get();
        }       
        public String getPenalties() {
            return Penalties.get();
        }       
        public String getDelta_Car_In_Front() {
            return Delta_Car_In_Front.get();
        }
    }
}