package umt.cs_346.elevatorsimulation.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import umt.cs_346.elevatorsimulation.constants.Constants;

public class FloorButton extends JButton{
	
	private int iID;
	
	public FloorButton(int id){
		iID = id;
		this.setText(" ");
		this.setMaximumSize(new Dimension(20, 15));
		this.setForeground(Color.lightGray);
		this.setBackground(Color.lightGray);
	}
	
	public int getID(){
		return iID;
	}
}
