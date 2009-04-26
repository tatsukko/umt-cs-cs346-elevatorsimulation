package umt.cs_346.elevatorsimulation.elevator;

import java.awt.*;

import umt.cs_346.elevatorsimulation.constants.Constants;

/**
 * Elevator car that is drawn on the Elevator panel.
 * 
 * @author Chris Hanshew
 */

@SuppressWarnings("serial")
public class ElevatorCar extends Polygon{
	
	private int xStart;
	private int yStart;

	public ElevatorCar(int x, int y){
		
		xStart = x;
		yStart = y;
		
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;
		
	}
	
	public void draw(Graphics page, int coordinate){
		page.setColor(Color.yellow);
		page.drawPolygon(this);
	}
	
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
				xStart,
				xStart + 15,
				xStart + 65,
				xStart + 65,
				xStart + 15,
				xStart + 15,
				xStart,
				xStart + 15
		};
		return xCoord;
	}
	
	private int [] createYArray(){
		int [] yCoord = {
				yStart,
				yStart,
				yStart + Constants.CAR_HEIGHT,
				yStart,
				yStart - Constants.CAR_HEIGHT,
				yStart - Constants.CAR_HEIGHT,
				yStart,
				yStart,
				yStart - Constants.CAR_HEIGHT,
				yStart - Constants.CAR_HEIGHT,
				yStart,
				yStart + Constants.CAR_HEIGHT,
				yStart + Constants.CAR_HEIGHT,
				yStart,
				yStart,
				yStart + Constants.CAR_HEIGHT,
				yStart - Constants.CAR_HEIGHT,
				yStart
		};
		return yCoord;
	}
	
	/**
	 * Gets the location of the cars lower left corner
	 * @return int The y-coordinate value 
	 */
	public int getLocation(){
		return this.ypoints[0];
	}	
}
