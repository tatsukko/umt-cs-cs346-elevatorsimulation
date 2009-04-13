package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.elevator.*;

import umt.cs_346.elevatorsimulation.GUI.*;
import umt.cs_346.elevatorsimulation.floorqueue.*;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.elevator.Elevator;

public class Controller{
	
	private FloorQueue queue;
	private ElevatorList elevators = new ElevatorList();
	
	private int iElevators = 6;
	private int iFloors = 12;
	
	private ElevatorSimulationGUI ESGUI;
	
	public Controller(){
		
		populateElevators(iElevators);
		ESGUI = new ElevatorSimulationGUI(elevators);
		
	}
	
	private void populateElevators(int elevatorsNum){

		for(int i = 0; i < elevatorsNum; i++){
			elevators.add(new Elevator(i, iFloors));
		}
	}
}
