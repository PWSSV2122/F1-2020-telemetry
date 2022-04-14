package Global_vars;

import packet_struct.Motion.Motion;
import packet_struct.Motion.Motion_car;

import packet_struct.Session.Session;
import packet_struct.Session.Session_Marshal;
import packet_struct.Session.Session_Weather;

import packet_struct.Lap_data;

import packet_struct.Event.Event;
import packet_struct.Event.Event_FastestLap;
import packet_struct.Event.Event_Retirement;
import packet_struct.Event.Event_TeamMateInPits;
import packet_struct.Event.Event_RaceWinner;
import packet_struct.Event.Event_Penalty;
import packet_struct.Event.Event_SpeedTrap;

import packet_struct.Participants.Participants;
import packet_struct.Participants.Participants_players;

import packet_struct.Car_setups;

import packet_struct.Car_telemetry.Car_telemetry;
import packet_struct.Car_telemetry.Car_telemetry_car;

import packet_struct.Car_status;

import packet_struct.Final_classification.Final_classification;
import packet_struct.Final_classification.Final_classification_car;

import packet_struct.Lobby_info.Lobby_info;
import packet_struct.Lobby_info.Lobby_info_car;

public class Data {
	public static Motion Motion;
	public static Motion_car[] Motion_car = new Motion_car[22];
	
	public static Session Session;
	public static Session_Marshal[] Session_marshal = new Session_Marshal[21];
	public static Session_Weather[] Session_weather = new Session_Weather[21];
	
	public static Lap_data[] Lap_data = new Lap_data[22];
	
	public static Event Event;
	public static Event_FastestLap Event_fastestLap;
	public static Event_Retirement Event_retirement;
	public static Event_TeamMateInPits Event_TeamMateInPits;
	public static Event_RaceWinner Event_raceWinner;
	public static Event_Penalty Event_penalty;
	public static Event_SpeedTrap Event_speedTrap;
	 
	public static Participants Participants;
	public static Participants_players[] Participants_players = new Participants_players[22];
	
	public static Car_setups[] Car_setups = new Car_setups[22];
	
	public static Car_telemetry Car_telemetry;
	public static Car_telemetry_car[] Car_telemetry_car = new Car_telemetry_car[22];
	
	public static Car_status[] Car_status = new Car_status[22];
	
	public static Final_classification Final_classification;
	public static Final_classification_car[] Final_classification_car = new Final_classification_car[22];
	
	public static Lobby_info Lobby_info;
	public static Lobby_info_car[] Lobby_info_car = new Lobby_info_car[22];
}