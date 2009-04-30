package umt.cs_346.elevatorsimulation.elevator;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;

import umt.cs_346.elevatorsimulation.buttons.FloorButton;
import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.floorqueue.*;

/**
 * Elevator Panel responsible for
 * 
 * @author Chris Hanshew
 */

@SuppressWarnings("serial")
public class Elevator extends JPanel {
  
	private int iFloors;
	private int iID;
	private int iNextFloor = 0;
	private int iTimeToCompletion = 0;
	private int iDistancePlaceHolder = 0;
	int pause = 200;
	
	private boolean bMaintenance;
	
	private Floor[] floors;
    private FloorQueue floorQueue;
    
	private ElevatorCar car;
	private ElevatorDoors doors;
	
	private Timer timer;
	
	/**
	 * Constructor
	 * @param id The Elevators unique identification number assigned by the controller.
	 * @param floors The number of floors this elevator will draw.
	 */
	public Elevator(int id, int floors){
		
		iID = id;
        iFloors = floors;
        bMaintenance = false;
        
        floorQueue = new FloorQueue();
        
        car = new ElevatorCar(Constants.XSTART, Constants.YSTART);
        doors = new ElevatorDoors(Constants.XSTART, Constants.YSTART);
        timer = new Timer(Constants.ELEVATOR_DELAY, new animate());
        stop();
        
		initializePanelComponents();
	}
	
	private void initializePanelComponents(){
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//setPreferredSize(new Dimension(150, 250));
		addFloors();
	}
	private void addFloors(){
    	floors = new Floor[iFloors];
    	for(int i = 0; i < iFloors; i++ ){
    		Floor floor = null;
    		if(i == 0){
    			floor = new Floor(Constants.XSTART, Constants.YSTART - 1, i);
    		}else{
    			floor = new Floor(Constants.XSTART, floors[i - 1].upperBoundary(), i);
    		}
    		floors[i] = floor;
    	}
	}
	
	public int getDestinationFloor(){
		return floors[iNextFloor].lowerBoundary();
	}
	
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);
    	
    	if(car.getLocation() == getDestinationFloor()){
    		if(pause != 0){
    			pause--;
    			doors.open();
    		}else if(floorQueue.size() >= 1){
    			floorQueue.remove(0);
    			
    			setNextFloor();
    		}
    			
    	}else if(car.getLocation() > getDestinationFloor()){
    		for(int i = 0; i < floors.length; i++){
    			floors[i].moveDown();
    		}
    		doors.close();
    		
    	}else if(car.getLocation() < getDestinationFloor()){
    		for(int i = 0; i < floors.length; i++){
    			floors[i].moveUp();
    		}
    		doors.close();
    	}
    	drawFloors(page);
    	drawCarriage(page);
    	drawDoors(page);
    	drawElevatorID(page);
    	
    	setNextFloor();
	}
	
	private void drawFloors(Graphics page){
		for(int i = 0; i < floors.length; i++){
			for(int c = 0; c < floorQueue.size(); c++){
				if(floorQueue.get(c) == floors[i].getFloorNumber()){
					floors[i].drawQueued();
				}else{
					floors[i].drawNormal();
				}
			}
			floors[i].draw(page);
		}
	}
	
    private void drawCarriage(Graphics page){
    	try{
    		car.draw(page, getDestinationFloor());
    	}catch(IndexOutOfBoundsException e){
    		e.printStackTrace();
    	}
    }
    private void drawDoors(Graphics page){
    	doors.draw(page);
    }
    private void drawElevatorID(Graphics page){
    	page.setColor(Color.GREEN);
    	page.drawString(Integer.valueOf(getID() + 1).toString(), 0, 10);
    }
    public void setMaintenance(){
    	if(timer.isRunning()){
	    	if(bMaintenance == false){
	    		bMaintenance = true;
	    	}else{
	    		bMaintenance = false;
	    	}
	    	floorQueue.add(0);
	    	setNextFloor();
    	}
    }
	public void start(){
		timer.start();
	}
	public void stop(){
		timer.stop();
	}
	
    private void setNextFloor(){
    
    	if(floorQueue.size() > 0){
	    	try{
	    		iNextFloor = floorQueue.get(0);
	 
	    	}catch(IndexOutOfBoundsException e){
	    		e.printStackTrace();
	    	}
    	}
    	
    	start();
    }
    
    public void addImmediateRequest(int request){
    	floorQueue.add(0, request);
    	setNextFloor();
    }
	public int calculateTimeToCompletion(Elevator elevator){
		iTimeToCompletion = 0;
    	
    	if(elevator.getCarLocation() <= getDestinationFloor()){
    		iTimeToCompletion += getDestinationFloor() - car.getLocation();
    	}else{
    		iTimeToCompletion += car.getLocation() - getDestinationFloor();
    	}
    	
    	if(floorQueue.size() >= 2){
	    	for(int i = 0; i < floorQueue.size() - 1; i++){
	    		if(floors[floorQueue.get(i)].lowerBoundary() <= 
	    		   floors[floorQueue.get(i + 1)].lowerBoundary()){
	    			
	    			iTimeToCompletion += floors[floorQueue.get(i + 1)].lowerBoundary() - floors[floorQueue.get(i)].lowerBoundary();
	    			
	    		}else{
	    			iTimeToCompletion += floors[floorQueue.get(i)].lowerBoundary() - floors[floorQueue.get(i + 1)].lowerBoundary();
	    		}
	    	}
    	}
		
		return iTimeToCompletion;
    }
    
    public int getNextFloor(){
    	return iNextFloor;
    }
    public int getCurrentFloor(){
    	int iCurrentFloor = 0;
    	for(int i = 0; i < floors.length; i++){
    		if(car.getLocation() <= floors[i].lowerBoundary() && car.getLocation() >= floors[i].upperBoundary()){
    			iCurrentFloor = floors[i].getFloorNumber();
    		}
    	}
		return iCurrentFloor;
    }
    
    public void addRequest(int requestedFloor){
    	floorQueue.add(requestedFloor);
    }
    public int getCarLocation(){
    	return car.getLocation();
    }
	public void setID(int i){
		iID = i;
	}
	
	public boolean getMaintenance(){
		return bMaintenance;
	}
	
	/**
	 * Returns an integer representing the unique ID of this elevator
	 * @category Getter/Setter
	 * @return The unique ID of this elevator
	 */
	public int getID(){
		return iID;
	}
	
	/**
	 * Sets the number of floors that this elevator shaft will display
	 * @category Getter/Setter
	 * @param i The number of floors this elevator will draw.
	 */
    public int getFloors(){
    	return iFloors;
    }
    public void setDistancePlaceHolder(int distance){
    	iDistancePlaceHolder = distance;
    }
    public int getDistancePlaceHolder(){
    	return iDistancePlaceHolder;
    }
    public class animate implements ActionListener{
	
    	public void actionPerformed(ActionEvent e) {
			repaint();
		} 	
    }
    public class buttonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(!bMaintenance && timer.isRunning()){
				FloorButton b = (FloorButton) e.getSource();
				floorQueue.add(b.getID());
				setNextFloor();
			}
		}
    }
}
