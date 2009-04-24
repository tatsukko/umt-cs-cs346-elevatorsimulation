package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.GUI.*;
import umt.cs_346.elevatorsimulation.request.Request;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.elevator.Elevator;
import umt.cs_346.elevatorsimulation.constants.*;

import java.util.Arrays;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * 
 */
public class Controller{
	
	private ElevatorList elevators;
	
	private int iElevators = 0;
	private int iFloors = 0;
	
	private Timer timer;

	private ElevatorSimulationUI ESGUI;
	private Request shortestRequest = null;
	
	public Controller(){
		
		timer = new Timer(50, new updateControl());
		
		elevators = new ElevatorList();
		
		ESGUI = new ElevatorSimulationUI(elevators);
		ESGUI.consoleAppend("Simulation Intialized.  Welcome!  Type help for console commands.");
		
		start();
	}
	
	private void populateElevatorList(){

		for(int i = 0; i < iElevators; i++){
			Elevator elevator = new Elevator(i, iFloors);
			elevator.setID(i);
			elevators.add(elevator);
		}
	}
	private void start(){
		timer.start();
	}
	
	private void startSimulation(){
		for(int i = 0; i < iElevators; i++){
			elevators.get(i).start();
		}
	}
	private void stopSimulation(){
		for(int i = 0; i < iElevators; i++){
			elevators.get(i).stop();
		}
	}

	private void update(){
		String action = ESGUI.getAction();
		
		if(action != null){
			action = action.toLowerCase();
			ESGUI.resetAction();
			if(action.equals("start")){
				if(!elevators.isEmpty()){
					startSimulation();
					consoleOut(ConsoleCommands.SIMULATION_START);
				}
			}//End START
			else if(action.startsWith("request -")){
					String [] param = action.split("-");
					serveRequest(param);
					consoleOut(ConsoleCommands.FLOOR_REQUEST + shortestRequest.getID());
				}//End Request
			else if(action.startsWith("help")){

					openURL(Constants.COMMAND_URL);
					consoleOut("help");
				}//End Help
			else if(action.startsWith("set -")){
					
					String [] param = action.split("-");
					setSimulationParamaters(param);
					populateElevatorList();
					ESGUI.addGUIComponents(elevators);
					consoleOut(ConsoleCommands.INITIALIZE_COMPONENTS);
				}//End Parameter Set
			else if(action.startsWith("stop")){
					stopSimulation();
					consoleOut(ConsoleCommands.SIMULATION_STOP);
				}//End Stop
			else if(action.startsWith("maintenance -")){
					String [] param = action.split("-");
					int iMaintenanceRequest = scheduleMaintenance(param);
					if(elevators.get(iMaintenanceRequest).getMaintenance()){
						consoleOut(ConsoleCommands.MAINTENANCE_REQUEST + iMaintenanceRequest);
					}else{
						consoleOut(ConsoleCommands.MAINTENANCE_COMPLETED + iMaintenanceRequest);
					}
				}//End Maintenance
			else{
				consoleOut(ConsoleCommands.UNKOWN);
			}
		}
	}
	
	private void setSimulationParamaters(String[] param){
		int iParsedValue = 0;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]);
			}catch(NumberFormatException e){
				
			}
			if(i == 1){
				iElevators = iParsedValue; 
			}else{
				iFloors = iParsedValue;
			}
		}
	}
	private int scheduleMaintenance(String[] param){
		int iParsedValue = 0;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
			}catch(NumberFormatException e){
				
			}
		}
		for(int i = 0; i < elevators.size(); i++){
			if((iParsedValue) == elevators.get(i).getID()){
				elevators.get(i).setMaintenance();
			}
		}
		return iParsedValue;
	}
	/**
	 * Request control logic.  Determines state of of every elevator and attempts to serve requests
	 * efficiently.  Requests are scheduled based on the time the next elevator will be in service.
	 * 
	 * Time-to-completion is based on the delay of the elevators timed action event.
	 * 
	 * If the elevator has passengers and is moving to deliver them:
	 * Time += (ElevatorDelay * DistanceToDestination) + (DoorManipulation * 2)
	 * 
	 * If the elevator has no passengers and is enroute to pick them up
	 * Time += (ElevatorDelay * DistanceToRequest) + (ElevatorDelay * DistaceToDestination + (DoorManipulation *4)
	 * 
	 * All requests that are computed 
	 * 
	 * Elevators moving to a floor to pick up passengers will not get preference for unserved
	 * requests.  Elevators that have passengers and are moving to their destination are eligible for 
	 * requests if they fulfill several requirements
	 * 
	 * @param nextFloor
	 */
	private Request serveRequest(String[] param){
		int iParsedValue = 0;
	
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
				System.out.println(iParsedValue);
			}catch(NumberFormatException e){
				
			}
		}
		
		int [] elevatorTimeToCompletion = new int [elevators.size()];
		Request [] elevatorRequests = new Request[elevators.size()];
		
		for(int i = 0; i < elevators.size(); i++){
			Request request = new Request(elevators.get(i).getID() ,elevators.get(i).getTimeToCompletion());
			elevatorRequests[i] = request;
			elevatorTimeToCompletion[i] = request.getTime();
		}
		
		Arrays.sort(elevatorTimeToCompletion);
		
		for(int i = 0; i < elevatorRequests.length; i++){
			if(elevatorRequests[i].getTime() == (int)elevatorTimeToCompletion[0]){
				if(!elevators.get(elevatorRequests[i].getID()).getMaintenance()){
					elevators.get(elevatorRequests[i].getID()).addRequest(iParsedValue);
					shortestRequest = elevatorRequests[i];
					break;
				}
			}
		}
		return shortestRequest;
	}
	
	private void consoleOut(String s){
		ESGUI.consoleAppend(s);
	}
	/////////////////////////////////////////////////////////
	//  Bare Bones Browser Launch                          //
	//  Version 1.5 (December 10, 2005)                    //
	//  By Dem Pilafian                                    //
	//  Supports: Mac OS X, GNU/Linux, Unix, Windows XP    //
	//  Example Usage:                                     //
	//     String url = "http://www.centerkey.com/";       //
	//     BareBonesBrowserLaunch.openURL(url);            //
	//  Public Domain Software -- Free to Use as You Like  //
	/////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	private static void openURL(String url) {
		String osName = System.getProperty("os.name");
	    try {
	    	if (osName.startsWith("Mac OS")) {
	    		Class fileMgr = Class.forName("com.apple.eio.FileManager");
	            java.lang.reflect.Method openURL = fileMgr.getDeclaredMethod("openURL",
	            new Class[] {String.class});
	            openURL.invoke(null, new Object[] {url});
	            }
	         else if (osName.startsWith("Windows"))
	            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + url);
	         else { //assume Unix or Linux
	            String[] browsers = {
	               "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
	            String browser = null;
	            for (int count = 0; count < browsers.length && browser == null; count++)
	               if (Runtime.getRuntime().exec(
	                     new String[] {"which", browsers[count]}).waitFor() == 0)
	                  browser = browsers[count];
	            if (browser == null)
	               throw new Exception("Could not find web browser");
	            else
	               Runtime.getRuntime().exec(new String[] {browser, url});
	            }
	         }catch (Exception e) {
	        	 e.printStackTrace();
	         }
	  }
	
	
	public class updateControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
	}
}
