package umt.cs_346.elevatorsimulation.tabs;

import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.elevator.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Swing component to display elevator objects.
 * Displayed as a tab in the GUI's tabbed pane.
 * @author Chris Hanshew
 *
 */

@SuppressWarnings("serial")
public class ElevatorTab extends JPanel{
    
	
    public ElevatorTab(ElevatorList elevators){
    	//setBackground(Color.GRAY);
    	
    	addElevators(elevators);
    }
    
    /**
     * Adds elevators to the panel 
     * 
     * @param elevators ElevatorList containing all instances of the elevators to be displayed 
     */
    private void addElevators(ElevatorList elevators){
    	if(elevators.size() <= 5){
    		setLayout(new GridLayout(1, elevators.size(), Constants.LAYOUT_HORIZONAL_GAP, Constants.LAYOUT_VERITCAL_GAP));
    		setPreferredSize(new Dimension(Constants.TAB_WIDTH * elevators.size(), Constants.TAB_HEIGHT));
    	}else if(elevators.size() <= 9){
    		setLayout(new GridLayout(3, elevators.size(), Constants.LAYOUT_HORIZONAL_GAP, Constants.LAYOUT_VERITCAL_GAP));
    		setPreferredSize(new Dimension(Constants.TAB_WIDTH * 3, Constants.TAB_HEIGHT * 3));
    	}else if(elevators.size() <= 12){
    		setLayout(new GridLayout(3, elevators.size(), Constants.LAYOUT_HORIZONAL_GAP, Constants.LAYOUT_VERITCAL_GAP));
    		setPreferredSize(new Dimension(Constants.TAB_WIDTH * 4, Constants.TAB_HEIGHT * 3));
    	}
 
        for(int i = 0; i < elevators.size(); i++){
        	Elevator e = (Elevator)elevators.get(i);
            add(e);
        }
    }

}
