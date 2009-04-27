package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Color;
import java.awt.Graphics;

public class ElevatorDoors {
	
	private int iDoorOneXStart;
	private int iDoorTwoXStart;
	private int iDoorOneX;
	private int iDoorTwoX;
	private int iYStart;
	private int iWidth = 10;
	private int iHeight = 10;
	private int iDoorOneBoundary;
	private int iDoorTwoBoundary;
	
	public ElevatorDoors(int xStart, int yStart){
		iDoorOneXStart = xStart + 30;
		iDoorTwoXStart = xStart + 40;
		iYStart = yStart + 5;
		
		iDoorOneX = iDoorOneXStart;
		iDoorTwoX = iDoorTwoXStart;
		
		iDoorOneBoundary = iDoorOneXStart - 10;
		iDoorTwoBoundary = iDoorTwoXStart + 10;
	}
	public void draw(Graphics page){
		page.setColor(Color.BLUE);
		page.drawRect(iDoorOneX, iYStart, iWidth, iHeight);
		page.drawRect(iDoorTwoX, iYStart, iWidth, iHeight);
		page.drawLine(iDoorOneX, iYStart, iDoorTwoX + 10, iYStart);
		page.drawLine(iDoorOneX, iYStart + 10, iDoorTwoX + 10, iYStart + 10);
	}
	public void open(){
		
		if(iDoorOneX > iDoorOneBoundary){
			iDoorOneX --;
		}
		if(iDoorTwoX < iDoorTwoBoundary){
			iDoorTwoX ++;
		}
			
	}
	public void close(){
		if(iDoorOneX < iDoorOneBoundary + 10){
			iDoorOneX ++;
		}
		if(iDoorTwoX > iDoorTwoBoundary - 10){
			iDoorTwoX --;
		}
	}
	public boolean getDoorState(){
		boolean bDoorState = true;
		if(iDoorOneX != iDoorOneXStart){
			bDoorState = false;
		}
		if(iDoorTwoX != iDoorTwoXStart){
			bDoorState = false;
		}
		return bDoorState;
	}
}
