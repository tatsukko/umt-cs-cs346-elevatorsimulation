package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.elevator.*;

import umt.cs_346.elevatorsimulation.GUI.*;
import umt.cs_346.elevatorsimulation.floorqueue.*;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.elevator.Elevator;

/**
 * 
 */
public class Controller{
	
	private FloorQueue queue;
	private ElevatorList elevators;
	
	private int iElevators = 12;
	private int iFloors = 12;
	
	private ElevatorSimulationGUI ESGUI;
	
	/**
	 * 
	 */
	public Controller(){
		
		elevators = new ElevatorList();
		
		populateElevators(iElevators);
		ESGUI = new ElevatorSimulationGUI(elevators);
		
	}
	
	/**
	 * 
	 * @param elevatorsNum
	 */
	private void populateElevators(int elevatorsNum){

		for(int i = 0; i < elevatorsNum; i++){
			Elevator elevator = new Elevator(i, iFloors);
			elevator.setNextFloor(i);
			elevators.add(elevator);
		}
	}
}
