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
    private final int yStart = 25;
    private final int xOffset = xStart + 15;
    private final int floorHeight = Constants.FLOOR_HEIGHT;
   
    //private final int yEnd = iFloors + 25;

	private int iFloors;
	private int iID;
	
	private boolean isIdle;
	private Timer timer= null;
	private Dimension dPanelDimension;
	
	private int [] iXCoord;
	private int [] iYCoordBottom;
    private int [] iYCoordTop;
    private int [] y;
    
    
    private Polygon carriageTop;
    private Polygon carriageBottom;
   
    //private TestCar car;
	ElevatorCar car;
    public Elevator(){}
    
	public Elevator(int id, int floors){
		iID = id;
        iFloors = floors;
        car = new ElevatorCar(50, 187);
        createPanel();
      
        
	}
	
	private void createPanel(){
		dPanelDimension = new Dimension(50 ,300);
		setPreferredSize(dPanelDimension);
		setBackground(Color.GRAY);
		timer = new Timer(150, new animate());
		timer.start();
	}

	public void paintComponent(Graphics page){
		
		super.paintComponent(page);

		drawFloors(page);
    	drawCarriage(page);

	}//end paintComponent
	
    private void drawCarriage(Graphics page){
    	
    	car.draw(page);
   
    }

    private void drawFloors(Graphics page){
    	
    	int iYStart = Constants.YSTART;
    	
    	for(int i = 0; i < iFloors; i++ ){
    		Floor f = new Floor(page, 50, iYStart -= 15, i);
    	}
    }

	public void setID(int i){
		iID = i;
	}
	public int getID(){
		return iID;
	}
    private void setFloors(int i){
        iFloors = i;
    }
    public class animate implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			
			repaint();
			
		} 	
    }
}//end Elevator
