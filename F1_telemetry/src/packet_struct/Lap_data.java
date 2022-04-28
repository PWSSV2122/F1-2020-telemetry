package packet_struct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Lap_data {

	private float lastLapTime;
	private float currentLapTime;
	private short sector1TimeInMS;
	private short sector2TimeInMS;
	private float bestLapTime;
	private byte bestLapNum;
	private short bestLapSector1TimeInMS;
	private short bestLapSector2TimeInMS;
	private short bestLapSector3TimeInMS;
	private short bestOverallSector1TimeInMS;
	private byte bestOverallSector1LapNum;
	private short bestOverallSector2TimeInMS;
	private byte bestOverallSector2LapNum;
	private short bestOverallSector3TimeInMS;
	private byte bestOverallSector3LapNum;
	private float lapDistance;
	private float totalDistance;
	private float safetyCarDelta;
	private byte carPosition;
	private byte currentLapNum;
	private byte pitStatus;
	private byte sector;
	private byte currentLapInvalid;
	private byte penalties;
	private byte gridPosition;
	private byte driverStatus;
	private byte resultStatus;
	
	public Lap_data(float lastLapTime, float currentLapTime, short sector1TimeInMS, short sector2TimeInMS, float bestLapTime, byte bestLapNum, short bestLapSector1TimeInMS, short bestLapSector2TimeInMS,
			short bestLapSector3TimeInMS, short bestOverallSector1TimeInMS, byte bestOverallSector1LapNum, short bestOverallSector2TimeInMS, byte bestOverallSector2LapNum, short bestOverallSector3TimeInMS, byte bestOverallSector3LapNum,
			float lapDistance, float totalDistance, float safetyCarDelta, byte carPosition, byte currentLapNum, byte pitStatus, byte sector, byte currentLapInvalid, byte penalties, byte gridPosition, 
			byte driverStatus, byte resultStatus) {
	
		this.lastLapTime = lastLapTime;
		this.currentLapTime = currentLapTime;
		this.sector1TimeInMS = sector1TimeInMS;
		this.sector2TimeInMS = sector2TimeInMS;
		this.bestLapTime = bestLapTime;
		this.bestLapNum = bestLapNum;
		this.bestLapSector1TimeInMS = bestLapSector1TimeInMS;
		this.bestLapSector2TimeInMS = bestLapSector2TimeInMS;
		this.bestLapSector3TimeInMS = bestLapSector3TimeInMS;
		this.bestOverallSector1TimeInMS = bestOverallSector1TimeInMS;
		this.bestOverallSector1LapNum = bestOverallSector1LapNum;
		this.bestOverallSector2TimeInMS = bestOverallSector2TimeInMS;
		this.bestOverallSector2LapNum = bestOverallSector2LapNum;
		this.bestOverallSector3TimeInMS = bestOverallSector3TimeInMS;
		this.bestOverallSector3LapNum = bestOverallSector3LapNum;
		this.lapDistance = lapDistance;
		this.totalDistance = totalDistance;
		this.safetyCarDelta = safetyCarDelta;
		this.carPosition = carPosition;
		this.currentLapNum = currentLapNum;
		this.pitStatus = pitStatus;
		this.sector = sector;
		this.currentLapInvalid = currentLapInvalid;
		this.penalties = penalties;
		this.gridPosition = gridPosition;
		this.driverStatus = driverStatus;
		this.resultStatus = resultStatus;
	}

	public float getLastLapTime() {
		return lastLapTime;
	}
	
	public float getCurrentLapTime() {
		return currentLapTime;
	}
	
	public short getSector1TimeInMS() {
		return sector1TimeInMS;
	}
	
	public short getSector2TimeInMS() {
		return sector2TimeInMS;
	}
	
	public float getBestLapTime() {
		return bestLapTime;
	}
	
	public byte getBestLapNum() {
		return bestLapNum;
	}
	
	public short getBestLapSector1TimeInMS() {
		return bestLapSector1TimeInMS;
	}
	
	public short getBestLapSector2TimeInMS() {
		return bestLapSector2TimeInMS;
	}
	
	public short getBestLapSector3TimeInMS() {
		return bestLapSector3TimeInMS;
	}
	
	public short getBestOverallSector1TimeInMS() {
		return bestOverallSector1TimeInMS;
	}
	
	public byte getBestOverallSector1LapNum() {
		return bestOverallSector1LapNum;
	}
	
	public short getBestOverallSector2TimeInMS() {
		return bestOverallSector2TimeInMS;
	}
	
	public byte getBestOverallSector2LapNum() {
		return bestOverallSector2LapNum;
	}
	
	public short getBestOverallSector3TimeInMS() {
		return bestOverallSector3TimeInMS;
	}
	
	public byte getBestOverallSector3LapNum() {
		return bestOverallSector3LapNum;
	}
	
	public float getLapDistance() {
		return lapDistance;
	}
	
	public float getTotalDistance() {
		return totalDistance;
	}
	
	public float getSafetyCarDelta() {
		return safetyCarDelta;
	}
	
	public byte getCarPosition() {
		return carPosition;
	}
	
	public byte getCurrentLapNum() {
		return currentLapNum;
	}
	
	public byte getPitStatus() {
		return pitStatus;
	}
	
	public byte getSector() {
		return sector;
	}
	
	public byte getCurrentLapInvalid() {
		return currentLapInvalid;
	}
	
	public byte getPenalties() {
		return penalties;
	}
	
	public byte getGridPosition() {
		return gridPosition;
	}
	
	public byte getDriverStatus() {
		return driverStatus;
	}
	
	public byte getResultStatus() {
		return resultStatus;
	}
	
	private static String[] names = new String[] {"getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus", "getResultStatus"};
	public static Class loadedList = null;
	public static Queue<Byte> compaire(Lap_data one, Lap_data two) {
		long start = System.nanoTime();
		Queue<Byte> comp_result = new LinkedList<Byte>();
		
		try {
			loadedList = Class.forName("packet_struct.Lap_data");
		} catch (ClassNotFoundException e) {
			//custom error message
			e.printStackTrace();
		}
		//hoeft maar 1 keer uitgevoert te worden dus moet uit deze methoden gehaald worden
		
		for (int i = 0; i < 27; i++) {
			try {
				Method m = loadedList.getMethod(names[i], (Class[]) null);
				Object value_1 = m.invoke(one);
				Object value_2 = m.invoke(two);
				if (value_1 != value_2) {
					comp_result.add((byte)i);
					if (value_2.getClass().equals(Byte.class)) {
						comp_result.add((byte)value_2);
					} else if (value_2.getClass().equals(Short.class)) {
						for (int o = 0; o < 2; o++) {
							 comp_result.add(ByteBuffer.allocate(2).putShort((short)value_2).array()[o]);
						}
					} else if (value_2.getClass().equals(Float.class)) {
						for (int o = 0; o < 4; o++) {
							 comp_result.add(ByteBuffer.allocate(4).putFloat((float)value_2).array()[o]);
						}
					} else {
						//custom error message
					}
				}
			} catch (Exception e) {
				//custom error message
				e.printStackTrace();
			}
		}
	    System.out.println((float)(System.nanoTime() - start) / 1000000);
		return comp_result;
	}
}
