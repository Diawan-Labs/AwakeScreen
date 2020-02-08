package labs.diawan.utilities;

import java.awt.AWTException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

import org.aeonbits.owner.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import labs.diawan.PropertiesConfigClasses.PressKeysConfig;

public class PropertiesValidation {

	static PressKeysConfig pk;
	static HashMap<String, Integer> KeyMap;
	static String StartFormatterStr="";
	static Logger logger;
	public PropertiesValidation() throws AWTException
	{
	 pk=ConfigFactory.create(PressKeysConfig.class);
	 StartFormatterStr="dd/MM/yyyy HH:mm:ss";
	  logger = LogManager.getLogger(PropertiesValidation.class);
	}
	public static void validateKeyList() throws IllegalArgumentException, IllegalAccessException
	{
		if(pk.Keys().size()<=0)
		{
		logger.error("no keys list provided");
		System.exit(0);
		}
		
		int KEY_EXISTS_FLAG=0;
		for(int keysval:pk.Keys())
		{
			if(checkifValueexists(keysval))
				KEY_EXISTS_FLAG++;
		}
		
		if(KEY_EXISTS_FLAG!=pk.Keys().size())
		System.exit(0);
	
	}
	public void validateRep()
	{
		
		if(pk.repeatation()<=0)
		{
			System.out.println("Repitation value error:"+pk.repeatation());
			System.exit(0);
		}
		
	}
	
	public void validateRepCount()
	{
		
		if(pk.repeatationCount()<=0)
		{
			System.out.println("Repitation count error:"+pk.repeatationCount());
			System.exit(0);
		}
		
	}
	public static void  validateDateandTime() throws ParseException
	{	
		if(pk.startDateandTime().length()==0)
		{
			Properties p1=new Properties();
			p1.setProperty("StartDateandTime",PropertiesValidation.getCurrentDateandTime());
			pk=ConfigFactory.create(PressKeysConfig.class,p1);
			System.out.println("StartDateandTime="+pk.startDateandTime());
		}
		if(pk.endDateandTime().length()==0)
		{
			Properties p2=new Properties();
			p2.setProperty("EndDateandTime","31/12/3019 00:00:00");
			pk=ConfigFactory.create(PressKeysConfig.class,p2);
			//System.out.println("EndDateandTime="+pk.endDateandTime());
		}
		if((pk.startDateandTime().length()>19))
		{
			System.out.println("Invalid startDateandTime date format");
			System.exit(0);
		}
		if(pk.endDateandTime().length()>19)
		{
			System.out.println("Invalid endDateandTime date format");
			System.exit(0);
		}		
	}
	public static boolean checkifValueexists(int val) throws IllegalArgumentException, IllegalAccessException
	{
		 doMapping();
		for(int Keyvalue : KeyMap.values())
		{
			if(Keyvalue==val)	
			  return true;
		}
		System.out.println(val+" Key not present in the deafult key list");
		return false;
		
	}
	public static void doMapping() throws IllegalArgumentException, IllegalAccessException
	{
		KeyMap=new HashMap<>();
		Field[] fields = java.awt.event.KeyEvent.class.getDeclaredFields();
		for (Field f : fields) {
		    if (Modifier.isStatic(f.getModifiers())&&Modifier.isPublic(f.getModifiers())) {
		       // System.out.println(f.getName()+"\t"+f.getInt(f.getName()));
		        KeyMap.put(f.getName(), f.getInt(f.getName()));
		    } 
		}
		
		Map<String, Integer> sortedIdNameMap 
	        = KeyMap.entrySet()
	                   .stream()
	                   .sorted(Entry.comparingByValue())
	                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap ::new));
		//sortedIdNameMap.forEach((k,v) -> System.out.println( k + "=" + v)); 
	}
	
	public static String getCurrentDateandTime() {
		DateFormat df = new SimpleDateFormat(StartFormatterStr);
		Date dateobj = new Date();
		String mydate = df.format(dateobj);
		return mydate;
	}

	public  static  Date getStartDateandTime() throws ParseException {
		
		
		Date StartDateandTime = new SimpleDateFormat(StartFormatterStr).parse(pk.startDateandTime());
		return StartDateandTime;
	}

	public static Date getEndDateandTime() throws ParseException {
	
	
		Date EndtDateandTime = new SimpleDateFormat(StartFormatterStr).parse(pk.endDateandTime());
		return EndtDateandTime;
	}

	 public static  boolean checkStartDateandTime() throws ParseException {
	
		
		Date CurrentDateandTime = new Date();
		Date StartDateandTime = new SimpleDateFormat(StartFormatterStr).parse(pk.startDateandTime());
		return CurrentDateandTime.after(StartDateandTime);
	}

	public  static  boolean checkEndDateandTime() throws ParseException {
	
		
		Date CurrentDateandTime = new Date();
		return CurrentDateandTime.after(getEndDateandTime());
	}
	public void performValidation() throws IllegalArgumentException, IllegalAccessException, ParseException
	{
		validateKeyList();
		validateRep();
		validateRepCount();
		validateDateandTime();
	}
	
}
