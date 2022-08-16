package Global_vars;

import java.lang.reflect.Field;

import applicationNew.Main;
import javafx.scene.Scene;

public class PageUpdate {
	
	public static void getPageNames() {
		Field[] temp = PageUpdate.class.getDeclaredFields();
		PageVars.pageNames = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			PageVars.pageNames[i] = temp[i].getName();
		}
	}
	
	public static void getSceneNames() {
		Field[] temp = PageVars.class.getDeclaredFields();
		PageVars.PageSceneNames = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			PageVars.PageSceneNames[i] = temp[i].getName();
		}
	}
	
	public static void SwitchPage(int button) {
		Field[] temp = PageUpdate.class.getDeclaredFields();
		for (int i = 0; i < temp.length; i++) {
			try {
				temp[i].set(false, false);
			} catch (Exception e) {
				e.printStackTrace();
				//custom error message
			}
		}
		try {
			PageUpdate.class.getField(PageVars.pageNames[button]).set(true, true);
			Field temp2 = PageVars.class.getField(PageVars.PageSceneNames[button]);
			System.out.println(PageVars.class.getDeclaredField(PageVars.PageSceneNames[button]));
			Main.window.setScene((Scene)PageVars.class.getDeclaredField(PageVars.PageSceneNames[button]).get(temp2));
			Main.window.setTitle("F1 Tracker : " + PageVars.PageScreenName[button]);
		} catch (Exception e) {
			e.printStackTrace();
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