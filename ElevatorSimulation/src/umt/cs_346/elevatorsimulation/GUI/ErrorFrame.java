package umt.cs_346.elevatorsimulation.GUI;

import javax.swing.JFrame;

import umt.cs_346.elevatorsimulation.constants.Constants;

public class ErrorFrame extends JFrame {

	public ErrorFrame(String error){
		setTitle("Error");
		setPreferredSize(Constants.ERROR_FRAME_DIMENSION);
		pack();
		
	}
}
