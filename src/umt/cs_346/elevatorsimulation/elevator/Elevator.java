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
	
	private boolean bMaintenance;
	
	private Floor[] floors;
    private FloorButton[] buttons;
    private FloorQueue floorQueue;
    
	private ElevatorCar car;
	
	private MigLayout layout;
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
        
        car = new ElevatorCar(Constants.XSTART, Constants.YSTART - 1);
        timer = new Timer(Constants.ELEVATOR_DELAY, new animate());
        
		initializePanelComponents();
	}
	
	private void initializePanelComponents(){
		setBackground(Color.GRAY);
		layout = new MigLayout("wrap");
		setLayout(layout);
		addFloors();
	    addButtons();
	}
	private void addFloors(){
    	floors = new Floor[iFloors];
    	for(int i = 0; i < iFloors; i++ ){
    		Floor floor = null;
    		if(i == 0){
    			floor = new Floor(Constants.XSTART, Constants.YSTART, i);
    		}else{
    			floor = new Floor(Constants.XSTART, floors[i - 1].upperBoundary(), i);
    		}
    		floors[i] = floor;
    	}
	}
	private void addButtons(){
		buttons = new FloorButton[iFloors];
		FloorButton button = null;
		
		for(int i = 0; i < iFloors; i++){
			button = new FloorButton(i);
			button.addActionListener(new buttonListener());
			buttons[i] = button;
			
			add(buttons[i]);
		}
	}
	private int destinationFloor(){
		return floors[iNextFloor].lowerBoundary();
	}
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);
		calculateTimeToCompletion();
		drawFloors(page);
    	drawCarriage(page);
    	if(car.getLocation() != destinationFloor()){
    	}else{
    		if(floorQueue.size() >= 1){
    			floorQueue.remove(0);
    		}
    		
    	}
    	setNextFloor();
	}
    private void drawCarriage(Graphics page){
    	try{
    		car.draw(page, destinationFloor());
    	}catch(IndexOutOfBoundsException e){
    		e.printStackTrace();
    	}
    }
    private void drawFloors(Graphics page){
    	for(int i = 0; i < floors.length; i++){
    		floors[i].draw(page);
    		buttons[i].setLocation(10, floors[i].upperBoundary());
    	}
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
    }
    
    private int calculateTimeToCompletion(){
		iTimeToCompletion = 0;
    	
    	if(car.getLocation() <= destinationFloor()){
    		iTimeToCompletion += destinationFloor() - car.getLocation();
    	}else{
    		iTimeToCompletion += car.getLocation() - destinationFloor();
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
    public void addRequest(int requestedFloor){
    	floorQueue.add(requestedFloor);
    	setNextFloor();
    }
    public int getTimeToCompletion(){
    	return iTimeToCompletion;
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
