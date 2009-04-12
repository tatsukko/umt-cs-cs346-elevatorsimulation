package umt.cs_346.elevatorsimulation.elevator;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.*;
import umt.cs_346.elevatorsimulation.constants.Constants;

@SuppressWarnings("serial")

public class Elevator extends JPanel {
    
    private final int xStart = 50;
    private final int yStart = 25;
    private final int xOffset = xStart + 15;


    //private final int yEnd = iFloors + 25;

	private int iFloors = 12;
	private int iID;
	private Timer timer= null;
	private Dimension dPanelDimension;
	private int [] iXCoord = {50, 100, 115, 65, 50, 100, 115, 65};
	private int [] iYCoord = {
        floorLength(iFloors),
        floorLength(iFloors),
        floorLength(iFloors) + 15,
        floorLength(iFloors) + 15,
        floorLength(iFloors) + 25,
        floorLength(iFloors) + 25,
        floorLength(iFloors) + 40,
        floorLength(iFloors) + 40};

    //Dimensions
   
    //Controls
	
	public Elevator(){
		
		createPanel();
	}
	public Elevator (int floors){
		setFloors(floors);
		createPanel();
		
	}//end Constructor
	
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);
		
		drawElevator(page);
		
	}//end paintComponent
	
	private void drawElevator(Graphics page){
		page.setColor(Color.red);

        //Rear
		page.draw3DRect(50, 25, 50, floorLength(iFloors), true);
        //Front
		page.draw3DRect(65, 40, 50, floorLength(iFloors), true);
		
		//Top Left
		page.drawLine(50, 25, 65, 40);
		//Top Right
		page.drawLine(100, 25, 115, 40);
        
		//Bottom Left
		page.drawLine(50, floorLength(iFloors) + 25, 65, floorLength(iFloors) + 40);
		//Bottom Right
		page.drawLine(100, floorLength(iFloors) + 25, 115, floorLength(iFloors) + 40);
		
		page.setColor(Color.yellow);
		
		page.drawPolygon(iXCoord, iYCoord, 4);
		//page.drawPolygon(iXCoord, iYCoord, 4);
		
		
	}//end drawElevator
	
	private void createPanel(){
		dPanelDimension = new Dimension(50 ,200);
		setPreferredSize(dPanelDimension);
		setBackground(Color.black);
		timer = new Timer(40, new animatePanel());
		timer.start();
	}
	
	public class animatePanel implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			/*
			while(iYCoord[0] >= 50 && iYCoord[0] <= 175){
				for(int i = 0; i < 4; i++){
					iYCoord[i] -= 1;
				}
			}
			*/
				
			
			if(iYCoord[0] >= 25){
				
				for(int i = 0; i < 4; i++){
					iYCoord[i] -= 1;
				}
				
			}
			/*
			if(iYCoord[0] <= 175){
					for(int i = 0; i < 4; i++){
						iYCoord[i] += 1;
				}
			}
			*/
				
			repaint();
		}
		
	}//end animatePanel
	public void setID(int i){
		iID = i;
	}
	public int getID(){
		return iID;
	}
    private int floorLength(int floors){
        return floors * Constants.FLOOR_HEIGHT;
    }
    private void setFloors(int i){
        iFloors = i;
    }
	
}//end Elevator
