/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package umt.cs_346.elevatorsimulation.elevator;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Hanshew
 */
public class ElevatorPanel extends JPanel {

    private int id;

    public ElevatorPanel(){
        buildPanel();
    }

    private void buildPanel(){
        this.setLayout(new GridLayout(1, 2));
       
    }

    public void setID(int i){
        id = i;
    }
    public int getID(){
        return id;
    }
}
