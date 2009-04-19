package umt.cs_346.elevatorsimulation.elevator;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Chris Hanshew
 * @created 4/12/09
 * 
 */

public class ElevatorCar extends Polygon{
	
	private int xStart;
	private int yStart;
	private int [] xValues;
	private int [] yValues;
	private Timer carTimer;
	private int floorCoordinate;
	
	public ElevatorCar(int x, int y){
		
		xStart = x;
		yStart = y - 15;
		xValues = createXArray();
		yValues = createYArray();
		
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;

	}
	
	public void draw(Graphics page, int coordinate){
		
				if(this.getLocation() > coordinate){
			
					for(int i = 0; i < this.npoints; i++ ){
						this.ypoints[i]--;
					}
				
				}else{
				
					for(int i = 0; i < this.npoints; i++ ){
							this.ypoints[i]++;
					}
				}
			
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
				yStart + 15,
				yStart,
				yStart - 15,
				yStart - 15,
				yStart,
				yStart,
				yStart - 15,
				yStart - 15,
				yStart,
				yStart + 15,
				yStart + 15,
				yStart,
				yStart,
				yStart + 15,
				yStart - 15,
				yStart,
		};
		return yCoord;
	}
	
	/**
	 * Gets the location 
	 * @return int The y-coordinate value 
	 */
	public int getLocation(){
		return this.ypoints[0];
	}	
}
