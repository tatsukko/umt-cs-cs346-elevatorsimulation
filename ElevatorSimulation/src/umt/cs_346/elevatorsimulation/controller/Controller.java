

package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.GUI.*;
import umt.cs_346.elevatorsimulation.request.Request;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.elevator.Elevator;
import umt.cs_346.elevatorsimulation.constants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * Elevator Control 
 * 
 * @author Chris Hanshew
 */

public class Controller{
	
	private ElevatorList elevators;
	
	private int iElevators = 0;
	private int iFloors = 0;
	
	private boolean bRandomize = false;
	
	private Timer timer;

	private ElevatorSimulationUI ESGUI;
	private Request shortestRequest = null;
	
	private Random randomGenerator; 
	
	public Controller(){
		
		timer = new Timer(Constants.UPDATE_DELAY, new updateControl());
		
		elevators = new ElevatorList();
		
		ESGUI = new ElevatorSimulationUI(elevators);
		ESGUI.consoleAppend(ConsoleCommands.INIT);
		
		randomGenerator = new Random();
		
		timer.start();
	}
	
	private void populateElevatorList(){

		for(int i = 0; i < iElevators; i++){
			Elevator elevator = new Elevator(i, iFloors);
			elevator.setID(i);
			elevators.add(elevator);
		}
	}
	
	/**
	 * Starts the timers of all of the elevators in the ElevatorList
	 */
	private void startSimulation(){
		for(int i = 0; i < iElevators; i++){
			elevators.get(i).start();
		}
	}
	
	/**
	 * Stops the times of all of the elevators in the ElevatorList
	 */
	private void stopSimulation(){
		for(int i = 0; i < iElevators; i++){
			elevators.get(i).stop();
		}
	}
	
	/**
	 * Called by the action event to get user commands from the GUI.
	 */
	private void update(){
		String action = ESGUI.getAction();
		
		if(action != null){
			action = action.toLowerCase();

			if(action.equals("start")){
				if(!elevators.isEmpty()){
					startSimulation();
					consoleOut(ConsoleCommands.SIMULATION_START);
				}
			}//End START
			else if(action.startsWith("request -")){
					String [] param = action.split("-");
					serveRequest(param);

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
				}//End Parameter Set
			else if(action.startsWith("stop")){
					stopSimulation();
					consoleOut(ConsoleCommands.SIMULATION_STOP);
				}//End Stop
			else if(action.startsWith("maintenance -")){
					String [] param = action.split("-");
					int iMaintenanceRequest = scheduleMaintenance(param);
					if(elevators.get(iMaintenanceRequest).getMaintenance()){
						consoleOut(ConsoleCommands.MAINTENANCE_REQUEST + iMaintenanceRequest + 1);
					}else{
						consoleOut(ConsoleCommands.MAINTENANCE_COMPLETED + iMaintenanceRequest + 1);
					}
				}//End Maintenance
			else if(action.startsWith("requestfloor -")){
				String [] param = action.split("-");
				serveFloorRequest(param);
				//Request tempRequest = serveFloorRequest(param);
				//consoleOut(ConsoleCommands.confirmFloorRequest(tempRequest.getElevatorID(), tempRequest.getFloorID()));
			}else if(bRandomize){ 
				//if(randomGenerator.nextInt(300) % 2 == 0){
					int iRandomElevator = randomGenerator.nextInt(iElevators) + 1;
					int iRandomFloor = randomGenerator.nextInt(iFloors) + 1;
					elevators.get(iRandomElevator).addRequest(iRandomFloor);
					consoleOut("Random Floor Queued");
				//}
			}
			else{
				consoleOut(ConsoleCommands.UNKOWN);
			}
		}
		ESGUI.resetAction();
	}
	
	/**
	 * Parses the values required to set the number of elevators and number of floors
	 */
	private void setSimulationParamaters(String[] param){
		int iParsedValue = 0;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]);
			}catch(NumberFormatException e){
				
			}
			if(i == 1){
				if(iParsedValue < 1 || iParsedValue > 12){
					consoleOut(ConsoleCommands.ELEVATOR_CREATION_ERROR);
				}else{
					iElevators = iParsedValue;
					consoleOut(ConsoleCommands.DRAW_ELEVATORS);
				}
			
			}else if(i == 2){
				if(iParsedValue < 5 || iParsedValue > 60){
					consoleOut(ConsoleCommands.FLOOR_CREATION_ERROR);
				}else{
					iFloors = iParsedValue;
					consoleOut(ConsoleCommands.DRAW_FLOORS);
				}
			}else if(i == 3){
				if(iParsedValue == 0){
					bRandomize = false;
				}else{
					bRandomize = true;
				}
			}
		}
	}
	
	/**
	 * Parses the value of the maintenance button click and sends the request to the proper elevator. 
	 * 
	 * @return int Value representing the elevator that maintenance has been requested for
	 */
	private int scheduleMaintenance(String[] param){
		int iParsedValue = 0;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		for(int i = 0; i < elevators.size(); i++){
			if(iParsedValue == elevators.get(i).getID()){
				elevators.get(i).setMaintenance();
			}
		}
		return iParsedValue;
	}
	
	/**
	 * Request control logic.  Determines state of of every elevator and attempts to serve requests
	 * efficiently.  Requests are scheduled based on the time the next each elevator has to complete
	 * its scheduled requests.
	 * 
	 * Elevators moving to a floor to pick up passengers will not get preference for unserved
	 * requests.  Elevators that have passengers and are moving to their destination are eligible for 
	 * requests if they have the lowest time to completion
	 * 
	 * This could be made more efficient by calculating the distance of the request from the
	 * last request in an elevators queue.
	 * 
	 * @param nextFloor The floor request to be sent to an elevator.
	 */
	
	private Request serveRequest(String[] param){
		int iRequestedFloor = 0;
		ArrayList<Integer> distanceList = new ArrayList<Integer>();
		
		for(int i = 1; i < param.length; i++){
			try{
				iRequestedFloor = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
			}catch(NumberFormatException e){
				
			}
		}
		
		
		//Check the requested value to make sure it is within bounds.
		if(iRequestedFloor > iFloors - 1){
			consoleOut(ConsoleCommands.FLOOR_BOUNDS_ERROR + iFloors);
			
		}else{

			ElevatorList candidateList = new ElevatorList();
			for(int i = 0; i < elevators.size(); i++){
				if(!elevators.get(i).getMaintenance()){	
					int iCurrentFloor = elevators.get(i).getCurrentFloor();
					int iDestinationFloor = elevators.get(i).getNextFloor();
					int distance = 0;
					
					if(iCurrentFloor >= iRequestedFloor && iDestinationFloor <= iRequestedFloor ){
						candidateList.add(elevators.get(i));
						distance = iCurrentFloor - iRequestedFloor;
						elevators.get(i).setDistancePlaceHolder(distance);
						distanceList.add(distance);
					}else if(iCurrentFloor <= iRequestedFloor && iDestinationFloor >= iRequestedFloor){
						candidateList.add(elevators.get(i));
						distance = iRequestedFloor - iCurrentFloor;
						elevators.get(i).setDistancePlaceHolder(distance);
						distanceList.add(distance);
					}else if(iCurrentFloor >= iRequestedFloor){
						candidateList.add(elevators.get(i));
						distance = iCurrentFloor - iRequestedFloor;
						elevators.get(i).setDistancePlaceHolder(distance);
						distanceList.add(distance);
					}else if(iCurrentFloor <= iRequestedFloor){
						candidateList.add(elevators.get(i));
						distance = iRequestedFloor - iCurrentFloor;
						elevators.get(i).setDistancePlaceHolder(distance);
						distanceList.add(distance);
					}
				}
			}

			Collections.sort(distanceList);

			for(int i = 0; i < candidateList.size(); i++){
				if(distanceList.get(0) == elevators.get(i).getDistancePlaceHolder()){
					
					elevators.get(i).addImmediateRequest(iRequestedFloor);
					break;
				}
			}
		}
	
		return shortestRequest;
	}
	
	private Request serveFloorRequest(String [] param){
		int iParsedValue = 0;
		int iElevatorID = 0;
		int iFloorID = 0;
		Request floorRequest = null;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			if(i == 1){
				iElevatorID = iParsedValue;
			}else{
				iFloorID = iParsedValue;
			}
		}
		for(int i = 0; i < elevators.size(); i++){
			if(iElevatorID == elevators.get(i).getID()){
				elevators.get(i).addRequest(iFloorID);
				//floorRequest = new Request(elevators.get(i).getID(), iFloorID, elevators.get(i).getTi);
			}
		}
		return floorRequest;
		
	}
	/*

    */
	/**
	 * Sends output to the console after controller state has been updated.
	 * 
	 * @param output The string to be written to the console.
	 */
	private void consoleOut(String output){
		ESGUI.consoleAppend(output);
	}

	/**
	 * Action event called every 50ms to update the state of the controller from GUI input.
	 */
	public class updateControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
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
}
