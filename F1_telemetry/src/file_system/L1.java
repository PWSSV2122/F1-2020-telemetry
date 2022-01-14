package file_system;

import java.util.HashMap;

public class L1 {
	//compression data
	public static int Lap_Data = 0;
	public static int Motion = 0;
	public static int Car_Telemetry = 0;
	public static int Car_Status = 0;
	public static int Car_Setups = 0;
	public static int Participants = 0;
	public static int Session = 0;
	
	//packet loss detection system vars
	public static int frameIdentifier_Car_status = 0;
	public static int frameIdentifier_car_telemetry = 0;
	public static int frameIdentifier_lap_data = 0;
	public static int frameIdentifier_motion = 0;
	
	public static int time_car_setup = 0;
	public static int time_participants = 0;
	public static int time_session = 0;
	
	//car setup packet header data
	public static byte[] car_setup_packetid = new byte[22];
	public static float[] car_setup_sessionTime = new float[22];
	//car setup packet data
	public static byte[] frontWing = new byte[22];
	public static byte[] rearWing = new byte[22];
	public static byte[] onThrottle = new byte[22];
	public static byte[] offThrottle = new byte[22];
	public static float[] frontCamber = new float[22];
	public static float[] rearCamber = new float[22];
	public static float[] frontToe = new float[22];
	public static float[] rearToe = new float[22];
	public static byte[] frontSuspension = new byte[22];
	public static byte[] rearSuspension = new byte[22];
	public static byte[] frontAntiRollBar = new byte[22];
	public static byte[] rearAntiRollBar = new byte[22];
	public static byte[] frontSuspensionHeight = new byte[22];
	public static byte[] rearSuspensionHeight = new byte[22];
	public static byte[] brakePressure = new byte[22];
	public static byte[] brakeBias = new byte[22];
	public static float[] rearLeftTyrePressure = new float[22];
	public static float[] rearRightTyrePressure = new float[22];
	public static float[] frontLeftTyrePressure = new float[22];
	public static float[] frontRightTyrePressure = new float[22];
	
	//car status packet header data
	public static byte[] car_status_packetid = new byte[22];
	public static float[] car_status_sessionTime = new float[22];
	//car status packet data
	public static byte[] actualTyreCompound = new byte[22];
	public static byte[] visualTyreCompound = new byte[22];
	public static byte[] fuelMix = new byte[22];
	public static float[] fuelInTank = new float[22];
	public static float[] ersStoreEnergy = new float[22];
	public static byte[] ersDeployMode = new byte[22];
	public static byte[] tyresWear_RL = new byte[22];
	public static byte[] tyresWear_RR = new byte[22];
	public static byte[] tyresWear_FL = new byte[22];
	public static byte[] tyresWear_FR = new byte[22];
	
	//car telemetry packet header data
	public static byte[] car_telemetry_packetid = new byte[22];
	public static float[] car_telemetry_sessionTime = new float[22];
	//car telemetry packet data
	public static short[] speed = new short[22];
	public static float[] throttle = new float[22];
	public static float[] steer = new float[22];
	public static float[] brake = new float[22];
	public static byte[] gear = new byte[22];
	public static short[] engineRPM = new short[22];
	public static short[] brakesTemperature_RL = new short[22];
	public static short[] brakesTemperature_RR = new short[22];
	public static short[] brakesTemperature_FL = new short[22];
	public static short[] brakesTemperature_FR = new short[22];
	public static byte[] tyresInnerTemperature_RL = new byte[22];
	public static byte[] tyresInnerTemperature_RR = new byte[22];
	public static byte[] tyresInnerTemperature_FL = new byte[22];
	public static byte[] tyresInnerTemperature_FR = new byte[22];
	
	//lap data packet header data
	public static byte[] lap_data_packetid = new byte[22];
	public static float[] lap_data_sessionTime = new float[22];
	//lap data packet data
	public static byte[] carPosition = new byte[22];
	public static float[] lastLapTime = new float[22];
	public static float[] currentLapTime = new float[22];
	public static short[] sector1TimeInMS = new short[22];
	public static short[] sector2TimeInMS = new short[22];
	public static byte[] penalties = new byte[22];
	public static byte[] pitStatus = new byte[22];
	public static float[] bestLapTime = new float[22];
	public static byte[] bestLapNum = new byte[22];
	public static short[] bestOverallSector1TimeInMS = new short[22];
	public static byte[] bestOverallSector1LapNum = new byte[22];
	public static short[] bestOverallSector2TimeInMS = new short[22];
	public static byte[] bestOverallSector2LapNum = new byte[22];
	public static short[] bestOverallSector3TimeInMS = new short[22];
	public static byte[] bestOverallSector3LapNum = new byte[22];
	public static float[] lapDistance = new float[22];
	
	//motion packet header data
	public static byte[] motion_packetid = new byte[22];
	public static float[] motion_sessionTime = new float[22];
	//motion packet data
	public static float[] worldPositionX = new float[22];
	public static float[] worldPositionY = new float[22];
	public static float[] yaw = new float[22];
	public static float[] pitch = new float[22];
	public static float[] roll = new float[22];
	
	//participants packet header data
	public static byte[] participants_packetid = new byte[22];
	public static float[] participants_sessionTime = new float[22];
	//participants packet data
	public static byte[] driverId = new byte[22];
	public static byte[] teamId = new byte[22];
	public static String[] name = new String[22];
	public static byte numActiveCars = 0;
	
	//session packet header data
	public static byte[] session_packetid = new byte[22];
	public static float[] session_sessionTime = new float[22];
	//session packet data
	public static byte trackId = 0;
	public static byte sessionType = 0;
	public static byte formula = 0;
	
	//postion
	public static HashMap<Byte, Byte> car_positions = new HashMap<>();
	
	//delta
	public static HashMap<Integer, String> Delta = new HashMap<>();
}