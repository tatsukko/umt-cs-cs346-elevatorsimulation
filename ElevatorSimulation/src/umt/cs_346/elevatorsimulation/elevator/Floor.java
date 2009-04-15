package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Floor extends Polygon{
	
	private int xStart;
	private int yStart;
	private int floorNumber;
	
	public Floor(int x, int y, int floorNum){
		xStart = x;
		yStart = y;
		floorNumber = floorNum;
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;
		
		
	}
	
	public void draw(Graphics page){
		page.setColor(Color.RED);
		page.drawPolygon(this);
		drawFloorName(page);
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
				xStart
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
				yStart
		};
		return yCoord;
	}
	public int getXStart(){
		return xStart;
	}
	public int floorLimit(){
		return yStart + 15;
	}
	private void drawFloorName(Graphics page){
		page.drawString(Integer.toString(floorNumber + 1), xStart - 15, yStart - 3);
	}
}
