package umt.cs_346.elevatorsimulation.elevator;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.*;

import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.elevator.*;

@SuppressWarnings("serial")

public class Elevator extends JPanel {
    
    private final int xStart = 50;

	private int iFloors;
	private int iID;
	
	private boolean isIdle;
	private Timer timer= null;
	private Dimension dPanelDimension;
    
    private Floor[] floors;
    
    private int nextFloor;
    
	ElevatorCar car;
    
    public Elevator(){}
    
	public Elevator(int id, int floors){
		iID = id;
        iFloors = floors;
        car = new ElevatorCar(50, 187);
        createPanel();
        initFloors();
        nextFloor = 0;
	}
	
	private void createPanel(){
		dPanelDimension = new Dimension(50 ,300);
		setPreferredSize(dPanelDimension);
		setBackground(Color.GRAY);
		timer = new Timer(150, new animate());
		timer.start();
	}
	 private void initFloors(){
	    	
	    	int iYStart = Constants.YSTART;
	    	floors = new Floor[iFloors];
	    	for(int i = 0; i < iFloors; i++ ){
	    		Floor f = new Floor(50, iYStart -= 15, i);
	    		floors[i] = f;
	    	}
	}
	
	 private int moveToFloor(){
		 return floors[nextFloor].floorLimit();
	 }
	/****************************************************************
	 * Paint and Draw methods
	 ***************************************************************/
	 
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);

		drawFloors(page);
    	drawCarriage(page);
    	if(car.getY() == moveToFloor()){
    		stop();
    	}
	}
	private void stop(){
		timer.stop();
	}
    private void drawCarriage(Graphics page){
    	
    	car.draw(page, moveToFloor());
   
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
    
    /****************************************************************
     *Action Events
     ***************************************************************/
    public class animate implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			repaint();
			
		} 	
    }
}//end Elevator
