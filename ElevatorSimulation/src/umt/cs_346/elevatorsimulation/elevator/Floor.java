package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * A floor representation drawn on the elevator panel.
 * @author chanshew
 * @
 */

public class Floor extends Polygon{
	
	private int xStart;
	private int yStart;
	private int floorNumber;
	
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
		floorNumber = floorNum;
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;
	}
	
	/**
	 * Draws this floor on the Elevator object's panel
	 * 
	 * @param page Graphics object passed from Elevator objects
	 */
	public void draw(Graphics page){
		page.setColor(Color.RED);
		page.drawPolygon(this);
		drawFloorName(page);
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
		
		if(floorNumber + 1 < 10){
			page.drawString(Integer.toString(floorNumber + 1), xStart - iSingleDigitOffSet, yStart - iHeightOffSet);
		}else{
			page.drawString(Integer.toString(floorNumber + 1), xStart - iDoubleDigitOffSet, yStart - iHeightOffSet);
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
				yStart + 15,
				yStart,
				yStart - 15,
				yStart - 15,
				yStart,
				yStart,
				yStart - 15,
				yStart - 15,
				yStart
		};
		return yCoord;
	}
	/**
	 * Unimplemented Getter
	 * @return 
	 */
	public int getXStart(){
		return xStart;
	}
	/**
	 * Returns a y-coordinate value that is checked against the current
	 * location of the car
	 * @return int the y-coordinate of this floor for bottom-left corner
	 */
	public int floorLimit(){
		return yStart;
	}

}
