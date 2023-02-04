package main;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotActionWrapper 
{
	private static Robot robot = null;
			
	public static void initRobot() throws AWTException
	{
		robot = new Robot();
	}

	public  static  boolean mouseLeftClick(Point p) 
	{
		robot.mouseMove(p.x, p.y);
		int button = InputEvent.BUTTON1_MASK;
		robot.mousePress(button);
		robot.delay(50);
		robot.mouseRelease(button);
		
		return true;
	}
	
	public  static   boolean mouseRightClick(Point p) 
	{
		robot.mouseMove(p.x, p.y);
		int button = InputEvent.BUTTON3_MASK;
		robot.mousePress(button);
		robot.delay(50);
		robot.mouseRelease(button);
		
		return true;
	}
	
	public  static  void pressKey(int keycode) 
	{
		robot.keyPress(keycode);
	 	robot.delay(50);
		robot.keyRelease(keycode);
	}

	public  static   void pressTwoKeys(int keycode1, int keycode2) 
	{
		robot.keyPress(keycode1);
		robot.delay(20);
		robot.keyPress(keycode2);
		robot.delay(50);
		robot.keyRelease(keycode2);
		robot.delay(20);
		robot.keyRelease(keycode1);
	}
	

	public static void pause(int delayValue) 
	{
		robot.delay(delayValue);
	}
	

	
	public  static  boolean mouseMove(Point p) throws Exception 
	{
		
		robot.mouseMove(p.x, p.y);
		
		return true;
	}

	public static void keyPress(int vk) 
	{
		robot.keyPress(vk);
		
	}

	public static void keyRelease(int vk) 
	{
		robot.keyPress(vk);
		
	}

	///////////////

	public static  int getKeyCode(String scode)  throws Exception
	{
		if(scode.equals("WIN")) return KeyEvent.VK_WINDOWS;
		else if(scode.equals("ENTER")) return KeyEvent.VK_ENTER;
		else if(scode.equals("PRINTSCREEN")) return KeyEvent.VK_PRINTSCREEN;
		else if(scode.equals("ALT")) return KeyEvent.VK_ALT;
		else if(scode.equals("TAB")) return KeyEvent.VK_TAB;
		else if(scode.equals("CONTROL")) return KeyEvent.VK_CONTROL;
		else if(scode.equals("DELETE")) return KeyEvent.VK_DELETE;
		else if(scode.equals("SPACE")) return KeyEvent.VK_SPACE;
		else if(scode.equals("DOWN")) return KeyEvent.VK_DOWN;
		else if(scode.equals("PAGE_DOWN")) return KeyEvent.VK_PAGE_DOWN;
		else if(scode.equals("PAGE_UP")) return KeyEvent.VK_PAGE_UP;
		else if(scode.equals("HOME")) return KeyEvent.VK_HOME;
		else if(scode.equals("UP")) return KeyEvent.VK_UP;
			//else if(scode.equals("ESCAPE")) return KeyEvent.VK_ESCAPE;
		else if(scode.equals("F4")) return KeyEvent.VK_F4;
		else if(scode.equals("A")) return KeyEvent.VK_A;
		else if(scode.equals("S")) return KeyEvent.VK_S;
		else if(scode.equals("H")) return KeyEvent.VK_H;
		else if(scode.equals("M")) return KeyEvent.VK_M;
		else if(scode.equals("C")) return KeyEvent.VK_C;
		else if(scode.equals("N")) return KeyEvent.VK_N;
		else if(scode.equals("V")) return KeyEvent.VK_V;
		else if(scode.equals("W")) return KeyEvent.VK_W;
		else if(scode.equals("R")) return KeyEvent.VK_R;
		else if(scode.equals("CONTEXT_MENU")) return KeyEvent.VK_CONTEXT_MENU;
		else throw new Exception("Undefined code ["+scode+"]");
	}

	//clear
	public  static  boolean executeClear(String cmd) throws Exception
	{
		RobotActionWrapper.pressTwoKeys(getKeyCode("CONTROL"), getKeyCode("A"));
		RobotActionWrapper.pause(500);
		RobotActionWrapper.pressKey(getKeyCode("DELETE"));
		RobotActionWrapper.pause(500);

		return true;
	}

}
