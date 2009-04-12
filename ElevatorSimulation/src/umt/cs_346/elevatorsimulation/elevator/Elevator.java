package umt.cs_346.elevatorsimulation.elevator;

import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Elevator extends JPanel {
	
	private int id;
	private Timer tTimer= null;
	private Dimension dPanelDimension;
	private int [] iXCoord = {50, 100, 115, 65};
	private int [] iYCoord = {175, 175, 190, 190};
	
	//Controls
	
	public Elevator(){
		
		createPanel();
	}
	public Elevator (Point pInitCoordinates, int iFloors){
		
		createPanel();
		
	}//end Constructor
	
	public void paintComponent(Graphics page){
		
		super.paintComponent(page);
		
		drawElevator(page);
		
	}//end paintComponent
	
	private void drawElevator(Graphics page){
		page.setColor(Color.red);
		
		page.draw3DRect(50, 50, 50, 125, true);
		page.draw3DRect(65, 65, 50, 125, true);
		
		//Top Left
		page.drawLine(50, 50, 65, 65);
		//Top Right
		page.drawLine(100, 50, 115, 65);
		//Bottom Left
		page.drawLine(50, 175, 65, 190);
		//Bottom Right
		page.drawLine(100, 175, 115, 190);
		
		
		
		page.setColor(Color.yellow);
		
		page.drawPolygon(iXCoord, iYCoord, 4);
		//page.drawPolygon(iXCoord, iYCoord, 4);
		
		
	}//end drawElevator
	
	private void createPanel(){
		dPanelDimension = new Dimension(150,300);
		setPreferredSize(dPanelDimension);
		setBackground(Color.black);
		tTimer = new Timer(25, new animatePanel());
		tTimer.start();
		
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
				
			
			if(iYCoord[0] >= 50){
				
				for(int i = 0; i < 4; i++){
					iYCoord[i] -= 1;
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
		id = i;
	}
	public int getID(){
		return id;
	}
	
}//end Elevator
