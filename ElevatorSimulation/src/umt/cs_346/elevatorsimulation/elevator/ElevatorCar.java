package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import sun.awt.Graphics2Delegate;
import sun.awt.RepaintArea;

public class ElevatorCar extends Polygon{
	
	private int xStart;
	private int yStart;
	private int [] xValues;
	private int [] yValues;
	private Timer carTimer;
	
	public ElevatorCar(int x, int y){
		
		xStart = x;
		yStart = y;
		xValues = createXArray();
		yValues = createYArray();
		
		this.xpoints = createXArray();
		this.ypoints = createYArray();
		this.npoints = this.ypoints.length;

	}
	
	public void draw(Graphics page){
		for(int i = 0; i < this.npoints; i++){
			this.ypoints[i]--;
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

	
	public void increment(){

		for(int i = 0; i < yValues.length; i++){
			yValues[i] ++;
		}
		
	}
	
}
