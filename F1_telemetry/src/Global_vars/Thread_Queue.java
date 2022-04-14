package Global_vars;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Thread_Queue {
	public static ExecutorService Motion = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Session = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Lap_data = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Event = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Participants = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Car_setups = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Car_telemetry = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Car_status = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Final_classification = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
	public static ExecutorService Lobby_info = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
}
