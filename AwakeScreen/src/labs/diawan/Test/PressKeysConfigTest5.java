package labs.diawan.Test;

import static org.junit.Assert.*;

import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import labs.diawan.PropertiesConfigClasses.PressKeysConfig;

public class PressKeysConfigTest5 {

	  static PressKeysConfig pkc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 pkc=ConfigFactory.create(PressKeysConfig.class);
		System.out.println("Task Started");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(" Task completed");
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	@Test
	public final void testKeys() {
		assertEquals(1,pkc.Keys().size());
	}

	@Test
	public final void testRepeatationCount() {
		assertEquals(999, pkc.repeatationCount());
	}

	@Test
	public final void testRepeatation() {
		assertEquals(999, pkc.repeatationCount());
	}

	@Test
	public final void testStartDateandTime() {
		assertEquals(999, pkc.repeatationCount());
	}

	@Test
	public final void testEndDateandTime() {
		assertEquals(999, pkc.repeatationCount());
	}

	@Test
	public final void testList() {
		assertEquals(999, pkc.repeatationCount());
	}

}
