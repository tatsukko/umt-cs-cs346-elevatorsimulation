/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package umt.cs_346.elevatorsimulation.elevator;

import java.awt.GridLayout;
import javax.swing.JPanel;

import umt.cs_346.elevatorsimulation.buttons.*;
import umt.cs_346.elevatorsimulation.elevator.*;

/**
 *
 * @author Hanshew
 */
public class ElevatorPanel extends JPanel {
	
    public ElevatorPanel(Elevator elevator){
    	this.setLayout(new GridLayout(1, 2));
    	this.add(elevator);
    	this.add(new ButtonPanel(elevator.getFloors()));
    }
}
