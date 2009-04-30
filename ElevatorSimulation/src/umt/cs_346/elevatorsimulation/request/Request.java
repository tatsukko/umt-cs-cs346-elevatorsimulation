package umt.cs_346.elevatorsimulation.request;

/**
 * @author Chris Hanshew
 */

public class Request {
	
	private int iElevatorID;
	private int iFloorID;
	
	public Request(int elevatorID,int floorID){
		iElevatorID = elevatorID;
		iFloorID = floorID;
	}

	public int getElevatorID(){
		return iElevatorID;
	}
	public int getFloorID(){
		return iFloorID;
	}
}