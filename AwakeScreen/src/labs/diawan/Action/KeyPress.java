package labs.diawan.Action;


import java.awt.AWTException;
import java.awt.Robot;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import labs.diawan.PropertiesConfigClasses.PressKeysConfig;


public class KeyPress {
	PressKeysConfig pk;
	Robot robot;
	Logger logger;
	public KeyPress() throws AWTException
	{
		pk=ConfigFactory.create(PressKeysConfig.class);
		logger=LogManager.getLogger(KeyPress.class);
		robot = new Robot();
	}
	public void doKeyPressandRelease() 
	{
		for(int key : pk.Keys())
		{
			robot.keyPress(key);
			logger.debug("Key Pressed: "+key);
		}
		System.out.println();
		for(int key :  pk.Keys())
		{
			robot.keyRelease(key);
			logger.debug("Key Released: "+key);
		}
	}
}
