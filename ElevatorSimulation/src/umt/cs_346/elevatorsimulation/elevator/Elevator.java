package umt.cs_346.elevatorsimulation.elevator;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;

import umt.cs_346.elevatorsimulation.buttons.FloorButton;
import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.constants.SpringUtilities;
import umt.cs_346.elevatorsimulation.floorqueue.*;

@SuppressWarnings("serial")

public class Elevator extends JPanel {
    

	private int iFloors;
	private int iID;
	private int iNextFloor = 0;
	private int iTimeToCompletion = 0;
	
	private boolean bMaintenence;
	
	private Floor[] floors;
    private FloorButton[] buttons;
    
    private Timer timer;
  
	private ElevatorCar car;
	
	private MigLayout layout;
	
	private FloorQueue floorQueue;
	
	public Elevator(int id, int floors){
		
		iID = id;
        iFloors = floors;
        car = new ElevatorCar(Constants.XSTART, Constants.YSTART - 1);
       
        bMaintenence = false;
       
        timer = new Timer(Constants.ELEVATOR_DELAY, new animate());
        
        floorQueue = new FloorQueue();
        
        layout = new MigLayout("wrap");
		this.setLayout(layout);
		this.createPanel();
	    this.createFloors();
	    this.createButtons();
	}
	
	private void createPanel(){
		Dimension panelDimension = new Dimension(150 , 300);
		setPreferredSize(panelDimension);
		setBackground(Color.GRAY);
	}
	private void createFloors(){
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
	private void createButtons(){
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
	/****************************************************************
	 * Paint and Draw methods
	 ***************************************************************/
	 
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);
		calculateTimeToCompletion();
		drawFloors(page);
    	drawCarriage(page);
    	if(car.getLocation() != destinationFloor()){
    		//if(car.getLocation() > destinationFloor()){
    			
    		//}else{
    			//iTimeToCompletion++;
    		//}
    	}else{
    		if(floorQueue.size() >= 1){
    			floorQueue.remove(0);
    		}
    		
    	}
    	System.out.println("SIZE " + floorQueue.size());
    	setNextFloor();
    	//iTimeToCompletion = destinationFloor() - car.getLocation();
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
    public void setMaintenence(){
    	if(timer.isRunning()){
	    	if(bMaintenence == false){
	    		bMaintenence = true;
	    	}else{
	    		bMaintenence = false;
	    	}
	    	System.out.println(bMaintenence);
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
	/****************************************************************
	 * Buttons
	 ****************************************************************/
    /****************************************************************
     * Getters and Setters
     ***************************************************************/
    
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
    public int getTimeToCompletion(){
    	return iTimeToCompletion;
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
    public int getFloors(){
    	return iFloors;
    }
    /**
     * Determines the state of the elevator for the elevators timer.
     * @return boolean Representing whether or not the elevator is serving a request.
     */
   
    
    /**
     * Event class for the elevator panel.
     * 
     *
     */
    public class animate implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			
			repaint();
			System.out.println(getTimeToCompletion());
		} 	
    }
    public class buttonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(!bMaintenence && timer.isRunning()){
				FloorButton b = (FloorButton) e.getSource();
				floorQueue.add(b.getID());
				setNextFloor();
				
			}
		}
    }
}//end Elevator
