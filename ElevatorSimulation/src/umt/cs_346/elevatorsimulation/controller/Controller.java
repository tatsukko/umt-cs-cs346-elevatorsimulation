package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.elevator.*;

import umt.cs_346.elevatorsimulation.GUI.*;
import umt.cs_346.elevatorsimulation.floorqueue.*;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.elevator.Elevator;
import umt.cs_346.elevatorsimulation.constants.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Timer;

/**
 * 
 */
public class Controller{
	
	private FloorQueue queue;
	private ElevatorList elevators;
	
	private int iElevators = 0;
	private int iFloors = 0;
	
	private Timer timer;

	private ElevatorSimulationUI ESGUI;
	
	/**
	 * 
	 */
	public Controller(){
		
		timer = new Timer(50, new updateControl());
		
		elevators = new ElevatorList();
		
		ESGUI = new ElevatorSimulationUI(elevators);
		ESGUI.consoleAppend("Simulation Intialized.  Welcome!  Type help for console commands.");
		start();
	}
	
	/**
	 * 
	 * @param elevatorsNum
	 */
	private void populateElevators(){

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
	/**
	 * 
	 */
	private void update(){
		String action = ESGUI.getAction();
		
		if(action != null){
			action = action.toLowerCase();
			ESGUI.resetAction();
			System.out.println(action);
			if(action.equals("start")){
				if(!elevators.isEmpty()){
					startSimulation();
					consoleOut("Simulation start");
				}
			}//End START
			else{
				if(action.startsWith("request -")){
					String [] param = action.split("-");
					serveRequest(param);
					consoleOut("Request");
				}//End REQUEST
			else{
				if(action.startsWith("help")){

					openURL(Constants.COMMAND_URL);
					consoleOut("help");
				}//End HELP
			else{
				if(action.startsWith("set -")){
					
					String [] param = action.split("-");
					setSimulationParamaters(param);
					
					populateElevators();
					ESGUI.addElevatorTab(elevators);
					ESGUI.addFloorButtons(iFloors);
					ESGUI.addMaintenceButtons(iFloors);
					consoleOut("Drawing Elevators");
				}//End SET
			else{
				if(action.startsWith("stop")){
					stopSimulation();
					consoleOut("Execution has been stopped by the user.  Press \"Start\" to resume the simulation.");
				}
			else{
				if(action.startsWith("maintenence -")){
					String [] param = action.split("-");
					consoleOut("Maintence Scheduled for Elevator " + scheduleMaintence(param));
				}
			else{
				consoleOut(ConsoleCommand.UNKOWN);
			}
			}
			}
			}
			}	
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
	private int scheduleMaintence(String[] param){
		int iParsedValue = 0;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
			}catch(NumberFormatException e){
				
			}
		}
		for(int i = 0; i < elevators.size(); i++){
			if((iParsedValue) == elevators.get(i).getID()){
				elevators.get(i).setMaintenence();
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
	private int serveRequest(String[] param){
		int iParsedValue = 0;
		for(int i = 1; i < param.length; i++){
			try{
				iParsedValue = Integer.parseInt(param[i]) - Constants.BUTTON_VALUE_OFFSET;
			}catch(NumberFormatException e){
				
			}
		}
		for(int i = 0; i < elevators.size(); i++){
			
		}
		return iParsedValue;
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
	private static void openURL(String url) {
		String errMsg = "Error attempting to launch web browser";
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
	        	  //showMessageDialog(null, errMsg + ":\n" + e.getLocalizedMessage());
	         }
	  }
	
	
	public class updateControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
	}
}
