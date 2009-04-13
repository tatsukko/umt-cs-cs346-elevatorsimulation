package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Graphics;
import java.awt.Polygon;

public class Floor extends Polygon{
	
	private int xStart;
	private int yStart;
	public Floor(Graphics page, int x, int y){
		xStart = x;
		yStart = y;
		this.xpoints = createXArray();
		this.ypoints = createYArray();
	}
	private int [] createXArray(){
		int [] xCoord = {xStart, xStart + 100, xStart + 115, xStart + 115 };
		return xCoord;
	}
	private int [] createYArray(){
		int [] yCoord = {yStart, yStart, yStart - 15, yStart };
		return yCoord;
	}
	
}
