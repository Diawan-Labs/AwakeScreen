package labs.diawan.Action;



import java.awt.AWTException;

import org.junit.Test;

public class KeyPressTest {


	
	@Test(expected=AWTException.class)
	public final void testDoKeyPressandRelease() {
		KeyPress kp = null;
		try {
			kp = new KeyPress();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kp.doKeyPressandRelease();
	}

}
