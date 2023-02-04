package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class GlassFrame extends JFrame {

	
	private AppEventer eventer = null;
	

	private Point clickedPoint;

	public GlassFrame(AppEventer eventer) throws Exception
	{
		super();
		setUndecorated(true);
		setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
		setAlwaysOnTop(true);
		
		this.eventer = eventer;

		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		Rectangle screenRect = new Rectangle(0, 0, 0, 0);
		for (GraphicsDevice gd1 : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
		    screenRect = screenRect.union(gd1.getDefaultConfiguration().getBounds());
		}

		System.out.println(gd);
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight()-10;

		int sizeWidth = width;
		int sizeHeight = height;
		/////////////////////////
		int locationX =  0;
		int locationY =  0;

			
		setBounds(new Rectangle(locationX, locationY, sizeWidth,sizeHeight));
		setCursor(AppFrame.getCustomCursor());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyCanvas c = new MyCanvas(this);
		
		c.addMouseListener(new MouseAdapter() {
			
			
			public void mouseClicked(MouseEvent ev)
			{
				try 
				{
					
					if(ev.getButton() == 1)
					{

						eventer.leftClickEvent(ev);
						System.out.println("exit left click area definer app");
						setVisible(false);

						
					}
					else if(ev.getButton() == 3)
					{
						if(eventer.rightClickEvent(ev)==false)
						{
							System.out.println("exit area definer app");
							setVisible(false);

						}
						
					}
				
				} 
				catch (Exception e) 
				{
				
					e.printStackTrace();
				}
			}
			
			/****

		      @Override
		      public void mousePressed(MouseEvent e) 
		      {
		        screenX = e.getXOnScreen();
		        screenY = e.getYOnScreen();

		        myX = getX();
		        myY = getY();
		        //myX = parent.getX();
		        //myY = parent.getY();
		      }
		      
			   ****/
			
		});
		
		add(c);
		
		setVisible(true);

	}

}
