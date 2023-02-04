package main;

import main.GlassFrame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;



public class MyCanvas extends Canvas {

	 
	private GlassFrame glassFrame=null;

	public MyCanvas(GlassFrame glassFrame) {
		
		super();
		
		this.glassFrame = glassFrame;
		
		
		setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
		
	}

	
	
	public void paint(Graphics g)
	{

		
		g.setColor(new Color(1.0f,1.0f,1.0f,0.5f));
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		
		
		
	}
	
}
