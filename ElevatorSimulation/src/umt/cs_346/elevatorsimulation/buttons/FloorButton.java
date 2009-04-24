package umt.cs_346.elevatorsimulation.buttons;

import umt.cs_346.elevatorsimulation.constants.Constants;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FloorButton extends JButton{
	
	private int iID;
	
	public FloorButton(int id){
		iID = id;
		this.setText(" ");
		this.setMaximumSize(Constants.FLOOR_BUTTON_DIMENSION);
		this.setForeground(Constants.FLOOR_BUTTON_COLOR);
		this.setBackground(Constants.FLOOR_BUTTON_COLOR);
	}
	
	public int getID(){
		return iID;
	}
}
