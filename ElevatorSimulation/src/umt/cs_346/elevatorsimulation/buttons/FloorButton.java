package umt.cs_346.elevatorsimulation.buttons;

import umt.cs_346.elevatorsimulation.constants.Constants;
import javax.swing.JButton;

/**
 * Custom button used by the Elevator class to represent floor buttons.
 * 
 * @author Chris Hanshew
 */

@SuppressWarnings("serial")
public class FloorButton extends JButton{
	
	private int iID;
	
	public FloorButton(int id){
		iID = id;
		setText(" ");
		setMaximumSize(Constants.FLOOR_BUTTON_DIMENSION);
		setForeground(Constants.FLOOR_BUTTON_COLOR);
		setBackground(Constants.FLOOR_BUTTON_COLOR);
	}
	
	public int getID(){
		return iID;
	}
}
