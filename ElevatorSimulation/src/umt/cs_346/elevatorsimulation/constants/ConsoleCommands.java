package umt.cs_346.elevatorsimulation.constants;

public class ConsoleCommands {
	
	public static final String INIT = "Simulation Intialized.  Welcome!  Type help for console commands.";
	public static final String UNKOWN = "Command not recognized.  Please type help for list of commands.";
	public static final String MAINTENANCE_REQUEST = "Maintenence Scheduled for Elevator ";
	public static final String MAINTENANCE_COMPLETED = "Maintenence Completed for Elevator "; 
	public static final String SIMULATION_START = "Simulation Started.";
	public static final String SIMULATION_STOP = "Simulation has been stopped by the user.  Press \"Start\" to resume the simulation.";
	public static final String ELEVATOR_REQUEST = "Request served to Elevator ";
	public static final String DRAW_ELEVATORS = "Values accepted.  Drawing Elevators.";
	public static final String DRAW_FLOORS = "Values accepted.  Drawing Floors";
	public static final String FLOOR_BOUNDS_ERROR = "The floor you have requested does not exist.  Please select a floor between 1 and ";
	public static final String FLOOR_CREATION_ERROR = "The floor value you have entered is not valid.  Please enter a number no less than 5 and no greater than 60.";
	public static final String ELEVATOR_CREATION_ERROR = "The elevator value you have entered is not valid.  Please enter a number no greater than 12";
	
	public static String confirmFloorRequest(int elevator, int floor){
		return new String("Floor " + floor + " added to Elevator " + elevator);
	}
}
