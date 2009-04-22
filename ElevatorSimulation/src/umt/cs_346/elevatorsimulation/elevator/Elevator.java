package umt.cs_346.elevatorsimulation.elevator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.elevator.*;

@SuppressWarnings("serial")

public class Elevator extends JPanel {
    

	private int iFloors;
	private int iID;
	
	private Timer timer= null;
	private Dimension panelDimension;
    
    private Floor[] floors;
    
    private int nextFloor;
    
	ElevatorCar car;
    
    public Elevator(){}
    
	public Elevator(int id, int floors){
		iID = id;
        iFloors = floors;
        car = new ElevatorCar(Constants.XSTART, Constants.YSTART - 1);
        createPanel();
        createFloors();
        nextFloor = 0;
        timer = new Timer(Constants.ELEVATOR_DELAY, new animate());
	}
	
	private void createPanel(){
		//panelDimension = new Dimension(100 , 0);
		//setPreferredSize(panelDimension);
		setBackground(Color.GRAY);
		
		
	}
	 private void createFloors(){
	    	
	    	floors = new Floor[iFloors];
	    	for(int i = 0; i < iFloors; i++ ){
	    		Floor f = null;
	    		if(i == 0){
	    			f = new Floor(Constants.XSTART, Constants.YSTART, i);
	    		}else{
	    			f = new Floor(Constants.XSTART, floors[i - 1].upperBoundary(), i);
	    		}
	    		floors[i] = f;
	    	}
	}
	
	 private int destinationFloor(){
		 return floors[nextFloor].lowerBoundary();
	 }
	/****************************************************************
	 * Paint and Draw methods
	 ***************************************************************/
	 
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);

		drawFloors(page);
    	drawCarriage(page);
    	if(car.getLocation() == destinationFloor()){
    		stop();
    	}
	}
    private void drawCarriage(Graphics page){
    	try{
    		car.draw(page, destinationFloor());
    	}catch(IndexOutOfBoundsException e){
    		System.out.println("Elevator: " + iID);
    		e.printStackTrace();
    	}
    }
    private void drawFloors(Graphics page){
    	for(int i = 0; i < floors.length; i++){
    		floors[i].draw(page);
    	}
    }
    
	public void start(){
		timer.start();
	}
	public void stop(){
		timer.stop();
	}
	/****************************************************************
	 * Buttons
	 ****************************************************************/
    /****************************************************************
     * Getters and Setters
     ***************************************************************/
    
    public void setNextFloor(int i){
    	nextFloor = i;
    }
    
	public void setID(int i){
		iID = i;
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
    private void setFloors(int i){
        iFloors = i;
    }
    
    /**
     * Determines the state of the elevator for the elevators timer.
     * @return boolean Representing whether or not the elevator is serving a request.
     */
    public boolean isIdle(){
    	boolean idle;
    	if(timer.isRunning()){
    		idle = false;
    	}else{
    		idle = true;
    	}
    	return idle;
    }
    
    /**
     * Event class for the elevator panel.
     * 
     *
     */
    public class animate implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			repaint();
			
		} 	
    }
}//end Elevator
