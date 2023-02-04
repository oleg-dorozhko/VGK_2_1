package main;

import java.awt.event.MouseEvent;

public interface AppEventer 
{
	public void leftClickEvent(MouseEvent ev) throws Exception;
	public boolean rightClickEvent(MouseEvent ev)  throws Exception;
}
