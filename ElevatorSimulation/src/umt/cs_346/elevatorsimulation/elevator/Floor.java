package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import umt.cs_346.elevatorsimulation.constants.Constants;


/**
 * A floor representation drawn on the elevator panel.
 * @author Chris Hanshew
 */

@SuppressWarnings("serial")
public class Floor extends Polygon{
	
	private int xStart;
	private int yStart;
	private int iFloorNumber;
	private boolean bDrawQueued;
	
	/**
	 * Floor constructor
	 * 
	 * @param x The floor's beginning value on the x plane.
	 * @param y The floor's beginning value on the y plane
	 * @param floorNum Value to determine the floors 
	 */
	public Floor(int x, int y, int floorNum){
		xStart = x;
		yStart = y;
		iFloorNumber = floorNum;
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;
		
		bDrawQueued = false;
	}
	
	/**
	 * Draws this floor on the Elevator class's panel
	 * 
	 * @param page Graphics object passed from Elevator objects
	 */
	
	public void draw(Graphics page){
		page.setColor(Color.RED);
		page.drawPolygon(this);
		
		if(bDrawQueued){
			page.setColor(Color.GREEN);
		}
		
		drawFloorName(page);
	}
	public void drawQueued(){
		bDrawQueued = true;
	}
	public void drawNormal(){
		bDrawQueued = false;
	}
	
	public void moveUp(){
		for(int i = 0; i < this.npoints; i++ ){
			this.ypoints[i]--;
		}
		
	}
	public void moveDown(){
		for(int i = 0; i < this.npoints; i++ ){
			this.ypoints[i]++;
		}
	}
	
	/**
	 * Draws a string representing the unique number of the floor.
	 * The string is drawn to the left (-x) of the floor.
	 * 
	 * @param page Graphics object passed from Elevator objects
	 */
	private void drawFloorName(Graphics page){
		int iHeightOffSet = 3;
		
		int iSingleDigitOffSet = 15;
		int iDoubleDigitOffSet = 18;
		
		if(iFloorNumber + 1 < 10){
			page.drawString(Integer.toString(iFloorNumber + 1), xStart - iSingleDigitOffSet, this.ypoints[0] - iHeightOffSet);
		}else{
			page.drawString(Integer.toString(iFloorNumber + 1), xStart - iDoubleDigitOffSet, this.ypoints[0] - iHeightOffSet);
		}
	}
	
	/**
	 * Values for the x points of the polygon
	 * @return int array 
	 */
	private int [] createXArray(){
		int [] xCoord = {
				xStart,
				xStart + 50,
				xStart + 65,
				xStart + 65,
				xStart + 50, 
				xStart,
				xStart,
				xStart + 50,
				xStart + 50,
				xStart,
				xStart
		};
		return xCoord;
	}
	
	/**
	 * Values for the y points of the polygon
	 * @return int array 
	 */
	private int [] createYArray(){
		int [] yCoord = {
				yStart,
				yStart,
				yStart + Constants.FLOOR_HEIGHT,
				yStart,
				yStart - Constants.FLOOR_HEIGHT,
				yStart - Constants.FLOOR_HEIGHT,
				yStart,
				yStart,
				yStart - Constants.FLOOR_HEIGHT,
				yStart - Constants.FLOOR_HEIGHT,
				yStart
		};
		return yCoord;
	}
	
	/**
	 * Returns a y-coordinate value that is checked against the current
	 * location of the car
	 * @return int the y-coordinate of this floors bottom-left corner
	 */
	public int lowerBoundary(){
		return this.ypoints[0];
	}
	/**
	 * Returns a y-coordinate value that is used in the drawing of other
	 * floors
	 * @return int the y-coordinate of this floors top-left corner
	 */
	public int upperBoundary(){
		return yStart - Constants.FLOOR_HEIGHT;
	}
	/**
	 * The "ID" of the floor.  
	 * @return int
	 */
	public int getFloorNumber(){
		return iFloorNumber;
	}
}
