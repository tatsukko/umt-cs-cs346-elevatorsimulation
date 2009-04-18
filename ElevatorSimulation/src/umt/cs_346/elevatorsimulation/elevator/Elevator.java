package umt.cs_346.elevatorsimulation.elevator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.elevator.*;

@SuppressWarnings("serial")

public class Elevator extends JPanel {
    
    private final int xStart = 40;

	private int iFloors;
	private int iID;
	
	private Timer timer= null;
	private Dimension dPanelDimension;
    
    private Floor[] floors;
    
    private int nextFloor;
    
	ElevatorCar car;
    
    public Elevator(){}
    
	public Elevator(int id, int floors){
		iID = id;
        iFloors = floors;
        car = new ElevatorCar(xStart, 183);
        createPanel();
        initFloors();
        nextFloor = 0;
	}
	
	private void createPanel(){
		dPanelDimension = new Dimension(50 ,20);
		setPreferredSize(dPanelDimension);
		setBackground(Color.GRAY);
		timer = new Timer(150, new animate());
		
	}
	 private void initFloors(){
	    	
	    	int iYStart = Constants.YSTART;
	    	floors = new Floor[iFloors];
	    	for(int i = 0; i < iFloors; i++ ){
	    		Floor f = new Floor(xStart, iYStart -= 15, i);
	    		floors[i] = f;
	    	}
	}
	
	 private int destinationFloor(){
		 return floors[nextFloor].floorLimit();
	 }
	/****************************************************************
	 * Paint and Draw methods
	 ***************************************************************/
	 
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);

		drawFloors(page);
    	drawCarriage(page);
    	if(car.getY() == destinationFloor()){
    		stop();
    	}
	}
	public void start(){
		timer.start();
	}
	public void stop(){
		timer.stop();
	}
    private void drawCarriage(Graphics page){
    	car.draw(page, destinationFloor());
    }
    private void drawFloors(Graphics page){
    	for(int i = 0; i < floors.length; i++){
    		floors[i].draw(page);
    	}
    }
   
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
	 * @param i 
	 */
    private void setFloors(int i){
        iFloors = i;
    }
    
    public boolean isIdle(){
    	boolean idle;
    	if(timer.isRunning()){
    		idle = false;
    	}else{
    		idle = true;
    	}
    	return idle;
    }
    
    /****************************************************************
     *Action Events
     ***************************************************************/
    public class animate implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			repaint();
			
		} 	
    }
}//end Elevator
