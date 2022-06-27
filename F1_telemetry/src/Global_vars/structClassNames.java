package Global_vars;

public class structClassNames {
	public static String[] Lap_data = new String[] {"getLastLapTime", "getCurrentLapTime", "getSector1TimeInMS", "getSector2TimeInMS", "getBestLapTime", "getBestLapNum", "getBestLapSector1TimeInMS", "getBestLapSector2TimeInMS", 
			"getBestLapSector3TimeInMS", "getBestOverallSector1TimeInMS", "getBestOverallSector1LapNum", "getBestOverallSector2TimeInMS", "getBestOverallSector2LapNum", "getBestOverallSector3TimeInMS",
			"getBestOverallSector3LapNum", "getLapDistance", "getTotalDistance", "getSafetyCarDelta", "getCarPosition", "getCurrentLapNum", "getPitStatus", "getSector", "getCurrentLapInvalid", "getPenalties",
			"getGridPosition", "getDriverStatus", "getResultStatus"};
	
	public static String[] Car_status = new String[] {"getTractionControl", "getAntiLockBrakes", "getFuelMix", "getFrontBrakeBias", "getPitLimiterStatus", "getFuelInTank", "getFuelCapacity", "getFuelRemainingLaps",
			"getMaxRPM", "getIdleRPM", "getMaxGears", "getDrsAllowed", "getDrsActivationDistance", "getTyresWearRL", "getTyresWearRR", "getTyresWearFL", "getTyresWearFR", "getActualTyreCompound",
			"getVisualTyreCompound", "getTyresAgeLaps", "getTyresDamageRL", "getTyresDamageRR", "getTyresDamageFL", "getTyresDamageFR", "getFrontLeftWingDamage", "getFrontRightWingDamage", "getRearWingDamage",
			"getDrsFault", "getEngineDamage", "getGearBoxDamage", "getVehicleFiaFlages", "getErsStoreEnergy", "getErsDeployMode", "getErsHarvestedThisLapMGUK", "getErsHarvestedThisLapMGUH", "getErsDeployedThisLap"};
	
	public static String[] Car_setups = new String[] {"getFrontWing", "getRearWing", "getOnThrottle", "getOffThrottle", "getFrontCamber", "getRearCamber", "getFrontToe", "getRearToe", "getFrontSuspension", "getRearSuspsnesion", 
			"getFrontAntiRollBar", "getRearAntiRollBar", "getFrontSuspnesionHeight", "getRearSuspensionHeight", "getBrakePressure", "getBrakeBias", "getRearLeftTyrePressure", "getRearRightTyrePressue",
			"getFrontLeftTyrePressure", "getFrontRightTyrePressure", "getBallast", "getFuelLoad"};
	
	public static String[] Session = new String[] {"getWeather", "getTrackTemperature", "getAirTemperature", "getTotalLaps", "getTrackLength", "getSessionType", "getTrackId", "getFormula", "getSessionTimeLeft", "getSessionDuration",
			"getPitSpeedLimit", "getGamePaused", "getIsSpectating", "getSpectatorCarIndex", "getSliProNativeSupport", "getNumMarshalZones", "getSafetyCarStatus", "getNetworkGame", "getNumWeatherForcastSamples"};

	public static String[] Session_Marshal = new String[] {"getZoneStart", "getZoneFLag"};
	
	public static String[] Session_Weather = new String[] {"getSessionType", "getTimeOffset", "getWeather", "getTrackTemerature", "getAirTemperature"};
	
	public static String[] Participants = new String[] {"getNumActivePlayers"};
	
	public static String[] Participants_players = new String[] {"getAiControlled", "getDriverId", "getTeamId", "getRaceNumber", "getNationality", "getName", "getYourTelemetry"};

	public static String[] Motion = new String[] {"getSuspensionPositionRL", "getSuspensionPositionRR", "getSuspensionPositionFL", "getSuspensionPositionFR", "getSuspensionVelocityRL", "getSuspensionVelocityRR", "getSuspensionVelocityFL", 
			"getSuspensionVelocityFR", "getSuspensionAccelerationRL", "getSuspensionAccelerationRR", "getSuspensionAccelerationFL", "getSuspensionAccelerationFR", "getWheelSpeedRL", "getWheelSpeedRR", "getWheelSpeedFL",
			"getWheelSpeedFR", "getWheelSlipRL", "getWheelSlipRR", "getWheelSlipFL", "getWheelSlipFR", "getLocalVelocityX", "getLocalVelocityY", "getLocalVelocityZ", "getAngularAccelerationX", "getAngularAccelerationY",
			"getAngularAccelerationZ", "getFrontWheelAngle"};
	
	public static String[] Motion_car = new String[] {"getWorldPositionX", "getWorldPositionY", "getWorldPositionZ", "getWorldVelocityX", "getWorldVelocityY", "getWorldVelocityZ", "getWorldForwardDirX", "getWorldForwardDirY", 
			"getWorldForwardDirZ", "getWorldRigthDirX", "getWorldRigthDirY", "getWorldRigthDirZ", "getGForceLateral", "getGForceLongitudinal", "getGForceVertical", "getYaw", "getPitch", "getRoll"};
	
	public static String[] Lobby_info = new String[] {"getNumPlayers"};
	
	public static String[] Lobby_info_car = new String[] {"getAiControlled", "getTeamId", "getNationality", "getName", "getReadyStatus"};

	public static String[] Final_classification = new String[] {"getNumCars"};
	
	public static String[] Final_classification_car = new String[] {"getPosition", "getNumLaps", "getGridPosition", "getPoints", "getNumPitStops", "getResultStatus", "getBestLapTime", "getTotalRaceTime", "getPenaltiesTime",
			"getNumPenalties", "getNumTyresStints", "getTyresStintsActual1", "getTyresStintsActual2", "getTyresStintsActual3", "getTyresStintsActual4", "getTyresStintsActual5", "getTyresStintsActual6",
			"getTyresStintsActual7", "getTyresStintsActual8", "getTyresStintsVisual1" ,"getTyresStintsVisual2", "getTyresStintsVisual3", "getTyresStintsVisual4", "getTyresStintsVisual5", "getTyresStintsVisual6",
			"getTyresStintsVisual7", "getTyresStintsVisual8"};
	
	public static String[] Event = new String[] {"getEventStringCode"};
	
	public static String[] Event_FastestLap = new String[] {"getVehicleIdx", "getLapTime"};
	
	public static String[] Event_Penalty = new String[] {"getPenaltyType", "getInfringementType", "getVehicleIdx", "getOntherVehicleIdx", "getTime", "getLapNum", "getPlacesGained"};

	public static String[] Event_RaceWinner = new String[] {"getVehicleIdx"};
	
	public static String[] Event_Retirement = new String[] {"getVehicleIdx"};
	
	public static String[] Event_SpeedTrap = new String[] {"getVehicleIdx", "getSpeed"};
	
	public static String[] Event_TeamMateInPits = new String[] {"getVehicleIdx"};
	
	public static String[] Car_telemetry = new String[] {"getButtonStatus", "getMfdPanelIndex", "getMfdPanelIndexSecondaryPlayer", "getSuggestedGear"};
	
	public static String[] Car_telemetry_car = new String[] {"getSpeed", "getThrottle", "getSteer", "getBrake", "getClutch", "getGear", "getEngineRPM", "getDrs", "getRevLigthsPercent", "getBrakesTempratureRL", "getBrakesTempratureRR", 
			"getBrakesTempratureFL", "getBrakesTempratureFR", "getTyresSurfaceTemperatureRL", "getTyresSurfaceTemperatureRR", "getTyresSurfaceTemperatureFL", "getTyresSurfaceTemperatureFR", "getTyresInnerTemperatureRL", 
			"getTyresInnerTemperatureRR", "getTyresInnerTemperatureFL", "getTyresInnerTemperatureFR", "getEngineTemperature", "getTyresPressureRL", "getTyresPressureRR", "getTyresPressureFL", "getTyresPressureFR",
			"getSurfaceTypeRL", "getSurfaceTypeRR", "getSurfaceTypeFL", "getSurfaceTypeFR"};
}