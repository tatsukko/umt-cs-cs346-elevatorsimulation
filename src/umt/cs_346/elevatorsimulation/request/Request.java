package umt.cs_346.elevatorsimulation.request;

public class Request {
	
	private int iElevatorID;
	private int iTime;
	
	public Request(int elevatorID, int time){
		iElevatorID = elevatorID;
		iTime = time;
	}
	
	public int getID(){
		return iElevatorID;
	}
	public int getTime(){
		return iTime;
	}
}
