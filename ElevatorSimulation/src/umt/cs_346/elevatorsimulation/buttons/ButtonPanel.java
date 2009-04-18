package umt.cs_346.elevatorsimulation.buttons;


import java.awt.GridLayout;

import javax.swing.*;


public class ButtonPanel extends JPanel
{
	public ButtonPanel(int buttonCount){
		
		switch(buttonCount){
			case(1):
				this.setLayout(new GridLayout(1, 1));
			case(2):
				this.setLayout(new GridLayout(1, 2));
			case(3):
				this.setLayout(new GridLayout(1, 3));
			case(4):
				this.setLayout(new GridLayout(2, 2));
			case(5):
				this.setLayout(new GridLayout(2, 3));
			case(6):
				this.setLayout(new GridLayout(2, 3));
			case(7):
				this.setLayout(new GridLayout(2, 3));
			case(8):
				this.setLayout(new GridLayout(3, 3));
			case(9):
				this.setLayout(new GridLayout(3, 3));
			case(10):
				this.setLayout(new GridLayout(4, 3));
			case(11):
				this.setLayout(new GridLayout(4, 3));
			case(12):
				this.setLayout(new GridLayout(4, 3));
		}
		
		for(int i = 0; i < buttonCount; i++){
			this.add(new FloorButton(i + 1));
		}
		this.setVisible(true);
	}
}
