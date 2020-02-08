package labs.diawan.Action;

import java.awt.AWTException;
import java.awt.Robot;


import org.aeonbits.owner.ConfigFactory;

import labs.diawan.PropertiesConfigClasses.ScreenshotKeys;

public class ScreenShot {

	ScreenshotKeys mk;
	Robot robot;
	public ScreenShot() throws AWTException
	{
		mk=ConfigFactory.create(ScreenshotKeys.class);
		if(mk.screenShot_Keys().size()<=0)
		{
			System.out.println("No keys list provided");
			System.exit(0);
		}
		robot = new Robot();
	}
	
	public void take_Screenshot() 
	{
		for(int key : mk.screenShot_Keys())
		{
			robot.keyPress(key);
			//System.out.print(" Key Pressed: "+key);
		}
		System.out.println();
		for(int key : mk.screenShot_Keys())
		{
			robot.keyRelease(key);
			//System.out.print(" Key Released: "+key);
		}
		System.out.println();
	}
}
