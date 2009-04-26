package umt.cs_346.elevatorsimulation.tabs;

import umt.cs_346.elevatorsimulation.constants.Constants;
import umt.cs_346.elevatorsimulation.elevator.*;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

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
        setLayout(new GridLayout(Constants.LAYOUT_ROWS, Constants.LAYOUT_COLUMNS,
        						 Constants.LAYOUT_HORIZONAL_GAP, Constants.LAYOUT_VERITCAL_GAP));
        addElevators(elevators);
    }
    
    /**
     * Adds elevators to the panel 
     * 
     * @param elevators ElevatorList containing all instances of the elevators to be displayed 
     */
    private void addElevators(ElevatorList elevators){
    
        for(int i = 0; i < elevators.size(); i++){
        	Elevator e = (Elevator)elevators.get(i);
            add(e);
        }
    }
}
