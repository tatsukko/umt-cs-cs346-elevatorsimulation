package umt.cs_346.elevatorsimulation.tabs;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import umt.cs_346.elevatorsimulation.elevator.*;

public class ElevatorTab extends JPanel{
    
    BoxLayout layout;

    public ElevatorTab(int i){
        //layout = new BoxLayout(this, i);
        this.setLayout(new GridLayout(2, i));
        addElevators(i);
        //layout.addLayoutComponent(e, this);
    }

    private void addElevators(int elevators){
        for(int i = 0; i < elevators; i++){
            Elevator e = new Elevator();
            e.setID(i);
            this.add(e, i);
        }
    }
}
