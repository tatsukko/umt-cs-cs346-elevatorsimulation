package umt.cs_346.elevatorsimulation.buttons;


import java.awt.GridLayout;

import javax.swing.*;


public class ButtonPanel extends JPanel
{
	public ButtonPanel(int buttonCount){
		
		this.setLayout(new GridLayout(12, 1));
		for(int i = 0; i < buttonCount; i++){
			this.add(new FloorButton(i + 1));
		}
		this.setVisible(true);
	}
}
