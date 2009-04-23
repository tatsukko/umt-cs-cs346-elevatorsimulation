package umt.cs_346.elevatorsimulation.tabs;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import umt.cs_346.elevatorsimulation.constants.SpringUtilities;
import umt.cs_346.elevatorsimulation.elevator.*;

/**
 * Swing component to display elevator objects.
 * Displayed as a tab in the GUI's tabbed pane.
 * @author chanshew
 *
 */

public class ElevatorTab extends JPanel{
    
	private ElevatorList elevatorList;
    
    public ElevatorTab(ElevatorList elevators){

        elevatorList = elevators;
        addElevators(elevatorList);
        this.setBackground(Color.GRAY);
        this.setLayout(new GridLayout(2, 6, 5, 5));
       
        //this.setLayout(layout);
        //SpringUtilities.makeGrid(this, 1, 1, 0, 0, 0, 0);
    }
    
    /**
     * Adds elevators to the panel 
     * 
     * @param elevators ElevatorList containing all instances of the elevators to be displayed 
     */
    private void addElevators(ElevatorList elevators){
    	/*
		switch(elevators.size()){
	
			case 1:
				this.setLayout(new GridLayout(1, 1));
				break;
			case 2:
				this.setLayout(new GridLayout(1, 2));
				break;
			case 3:
				this.setLayout(new GridLayout(1, 3));
				break;
			case 4:
				this.setLayout(new GridLayout(1, 4));
				break;
			case 5:
				this.setLayout(new GridLayout(1, 5));
				break;
			case 6:
				this.setLayout(new GridLayout(1, 6));
				break;
			case 7:
				this.setLayout(new GridLayout(2, 6));
				break;
			case 8:
				this.setLayout(new GridLayout(2, 6));
				break;
			case 9:
				this.setLayout(new GridLayout(2, 6));
				break;
			case 10:
				this.setLayout(new GridLayout(2, 6));
				break;
			case 11:
				this.setLayout(new GridLayout(2, 6));
				break;
			case 12:
				this.setLayout(new GridLayout(2, 6));
				break;
		}
		*/
        for(int i = 0; i < elevators.size(); i++){
        	Elevator e = (Elevator)elevators.get(i);
            add(e);
        }
    }
}
