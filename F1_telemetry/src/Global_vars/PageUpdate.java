package Global_vars;

import java.lang.reflect.Field;

import applicationNew.Main;
import javafx.scene.Scene;

public class PageUpdate {
	public static String[] pageNames;
	public static String[] PageSceneNames;
	
	public static void getPageNames() {
		Field[] temp = PageUpdate.class.getDeclaredFields();
		pageNames = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			pageNames[i] = temp[i].getName();
		}
	}
	
	public static void getSceneNames() {
		Field[] temp = PageVars.class.getDeclaredFields();
		PageSceneNames = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			PageSceneNames[i] = temp[i].getName();
		}
	}
	
	public static void SwitchPage(int button) {
		Field[] temp = PageUpdate.class.getDeclaredFields();
		for (int i = 0; i < temp.length; i++) {
			try {
				temp[i].set(false, false);
			} catch (Exception e) {
				//custom error message
			}
		}
		try {
			PageUpdate.class.getField(pageNames[button]).set(true, true);
			Field temp2 = PageVars.class.getField(PageSceneNames[button]);
			Scene temp3 = (Scene) temp2.get(temp2);
			Main.window.setScene(temp3);
		} catch (Exception e) {
			//custom error message
		}
		
	}

	//setup pages
	public static boolean Aerodynamic = false;
	public static boolean SetupPage = false;			//Brakes
	public static boolean SuspensionGeometry = false;
	public static boolean SuspensionContent = false;
	public static boolean TransmissionContent = false;
	public static boolean Tyre = false;

	//other pages
	public static boolean ComparisonPage = false;
	public static boolean GraphPage = false;
	public static boolean LapTimePage = false;
	public static boolean SectorTimes = false;
	public static boolean TimingPage = false;
	public static boolean TrackPage = false;
}
