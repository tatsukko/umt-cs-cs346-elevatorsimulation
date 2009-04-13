package umt.cs_346.elevatorsimulation.tabs;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import umt.cs_346.elevatorsimulation.elevator.*;

public class ElevatorTab extends JPanel{
    
	private ElevatorList elevatorList;
    
    public ElevatorTab(ElevatorList elevators){
        //layout = new BoxLayout(this, i);
        this.setLayout(new GridLayout(2, elevators.size()));
        elevatorList = elevators;
        addElevators(elevatorList);
        //layout.addLayoutComponent(e, this);
    }

    private void addElevators(ElevatorList elevators){
        for(int i = 0; i < elevators.size(); i++){
        	Elevator e = (Elevator)elevators.get(i);
            this.add(e, i);
        }
    }
}
