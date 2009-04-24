package umt.cs_346.elevatorsimulation.elevator;

import java.awt.*;
import javax.swing.*;

import umt.cs_346.elevatorsimulation.constants.Constants;

/**
 * 
 * @author Chris Hanshew
 * @created 4/12/09
 * 
 */

public class ElevatorCar extends Polygon{
	
	private int xStart;
	private int yStart;

	private int floorCoordinate;
	
	
	public ElevatorCar(int x, int y){
		
		xStart = x;
		yStart = y;
		
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;

	}
	
	public void draw(Graphics page, int coordinate){
		
		if(this.getLocation() == coordinate){
			//this.opendoors
		}else{	
			if(this.getLocation() > coordinate){
		
				for(int i = 0; i < this.npoints; i++ ){
					this.ypoints[i]--;
				}
			
			}else{
			
				for(int i = 0; i < this.npoints; i++ ){
					this.ypoints[i]++;
				}
			}
		}
		
		page.setColor(Color.yellow);
		page.drawPolygon(this);
	}
	
	private void calculateTimeToCompletion(){
		
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
