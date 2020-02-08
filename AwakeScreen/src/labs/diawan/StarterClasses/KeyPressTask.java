package labs.diawan.StarterClasses;

import java.awt.AWTException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import labs.diawan.Action.KeyPress;

import labs.diawan.PropertiesConfigClasses.PressKeysConfig;
import labs.diawan.utilities.PropertiesValidation;

public class KeyPressTask extends TimerTask {

	static int count = 0;
	static PressKeysConfig pk;
	static Timer timer;
	static TimerTask timerTask;
	static PropertiesValidation pv;
	static Logger logger;

	public static void main(String[] args)
			throws AWTException, ParseException, IllegalArgumentException, IllegalAccessException {
		pk = ConfigFactory.create(PressKeysConfig.class);
		logger = LogManager.getLogger(KeyPressTask.class);

		System.out.println();
		if(System.getProperty("log4j.configurationFile")==null)
		{
			System.out.println("No Log4j configuration file found, Hence no output will be seen");
		}
		pv = new PropertiesValidation();
		pv.performValidation();

		timer = new Timer();
		timerTask = new KeyPressTask();
		if (PropertiesValidation.checkStartDateandTime()) {
			timer.schedule(timerTask, PropertiesValidation.getStartDateandTime(), (pk.repeatation() * 1000));
			logger.info("Task started.");
			logger.info("Max Ieration count=" + pk.repeatationCount());
		} else {
			logger.info("Please Wait \n Task Yet to be started at :" + PropertiesValidation.getStartDateandTime());
			timer.schedule(timerTask, pk.repeatation());
		}
	}

	@Override
	public void run() {

		try {
			stopTask();
			KeyPress kp = new KeyPress();
			kp.doKeyPressandRelease();
			count++;
			logger.debug("key event at " + PropertiesValidation.getCurrentDateandTime());
			logger.info("Count: " + count);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void stopTask() throws ParseException {
		if (count >= pk.repeatationCount()) {
			timer.cancel();
			logger.info("Task finished due to repeatation count: " + pk.repeatationCount() + " excceded.");
			System.exit(0);
		} else if (PropertiesValidation.checkEndDateandTime()) {
			timer.cancel();
			logger.info("Task finished due to end date and time passed." + PropertiesValidation.getEndDateandTime());
			System.exit(0);
		}
	}
}
