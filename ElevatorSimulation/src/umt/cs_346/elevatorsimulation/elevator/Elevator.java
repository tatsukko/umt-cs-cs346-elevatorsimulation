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
    
    private Polygon carriageTop;
    private Polygon carriageBottom;
   

	
	public Elevator(int id, int floors){
		iID = id;
        iFloors = floors;
        iXCoord = setXCoordValues();
        iYCoordBottom = setYBottomValues();
        iYCoordTop = setYTopValues();
        createPanel();
      
        timer.start();
	}

	public void paintComponent(Graphics page){
		
		super.paintComponent(page);
        
		drawShaft(page);
        
        drawFloorDelimeter(page);
		drawCarriage(page);
		
	}//end paintComponent
	
	private void drawShaft(Graphics page){
		page.setColor(Color.red);

        //Rear Rect
		page.draw3DRect(50, 25, 50, floorLength(iFloors), true);
        //Front Rect
		//page.draw3DRect(65, 40, 50, floorLength(iFloors), true);
		page.drawLine(115, 40, 115, floorLength(iFloors) + 40);
		//Top Lefte
		//page.drawLine(50, 25, 65, 40);
		//Top Right
		page.drawLine(100, 25, 115, 40);
		//Bottom Left
		//page.drawLine(50, floorLength(iFloors) + 25, 65, floorLength(iFloors) + 40);
		//Bottom Right
		page.drawLine(100, floorLength(iFloors) + 25, 115, floorLength(iFloors) + 40);

        //Floor indicator lines
        drawFloorDelimeter(page);
		
		//page.drawPolygon(iXCoord, iYCoord, 4);
		
		
	}//end drawElevator

    private void drawCarriage(Graphics page){
        page.setColor(Color.yellow);
        page.drawPolygon(iXCoord, iYCoordBottom, 4);
        page.drawPolygon(iXCoord, iYCoordTop, 4);

    }
    private void drawFloorDelimeter(Graphics page){
        page.setColor(Color.RED);
        for(int i = 0; i < iFloors; i++){
            page.drawLine(50, (floorHeight * i)  + 40, 100, (floorHeight * i)  + 40);
            page.drawLine(100, (floorHeight * i) + 25, 115, (floorHeight * i) + 40);

        }
    }
	
	private void createPanel(){
		dPanelDimension = new Dimension(50 ,200);
		setPreferredSize(dPanelDimension);
		setBackground(Color.black);
		timer = new Timer(150, new animatePanel());
	}
	
	public void moveToFloor(){
		
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
				
			
			if(iYCoordBottom[0] >= 40){
				
				for(int i = 0; i < 4; i++){
					iYCoordBottom[i] -= 1;
				}
				
			}
            if(iYCoordTop[0] >= 25){

				for(int i = 0; i < 4; i++){
					iYCoordTop[i] -= 1;
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
    private int[] setXCoordValues(){
    	final int [] xTemp = {50, 100, 115, 65};
    	return xTemp;
    }
    private int[] setYTopValues(){
    	final int [] yTopTemp = {
                floorLength(iFloors) + 10,
                floorLength(iFloors) + 10,
                floorLength(iFloors) + 25,
                floorLength(iFloors) + 25
            };
    	return yTopTemp;
    }
    private int[] setYBottomValues(){
    	final int [] yBottomTemp = {
                floorLength(iFloors) + 25,
                floorLength(iFloors) + 25,
                floorLength(iFloors) + 40,
                floorLength(iFloors) + 40
            };
        return yBottomTemp;
    }
	
}//end Elevator
