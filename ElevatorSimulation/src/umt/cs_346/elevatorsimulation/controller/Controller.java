package umt.cs_346.elevatorsimulation.controller;

import umt.cs_346.elevatorsimulation.elevator.*;

import javax.swing.*;

public class Controller extends JFrame{
	
	public Controller(){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add(new Elevator());
		
		pack();
		setVisible(true);
	}
}
