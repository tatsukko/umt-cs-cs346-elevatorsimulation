package umt.cs_346.elevatorsimulation.elevator;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCar extends JComponent{
	
	private Timer timer;
	int x;
	int y;
	int x1;
	int y1;
	
	public TestCar(Graphics page){
		
		x = 50;
		y = 150; 
		x1 = 100;
		y1 = 150;
		
		page.setColor(Color.yellow);
		page.drawLine(x, y, x1, y1);
		timer = new Timer(150, new animate());
		timer.start();
	}
	
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		
	}
	
	public class animate implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			
			paintCar();
		}
	}
	
	public void paintCar(){
		System.out.println(x);
		x += 1;
		y+=1;
		x1+=1;
		y1+=1;
		this.repaint();
		
	}
}
