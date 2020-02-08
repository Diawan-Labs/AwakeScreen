package labs.diawan.PropertiesConfigClasses;

import java.util.ArrayList;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({"file:resources/conf/ScreenshotKeys.properties"})
public interface ScreenshotKeys extends Config {

	@Key("screenShot_Keys")
	@DefaultValue("524,154")
	ArrayList<Integer> screenShot_Keys();
	
	@Key("repeatation_Count")
	@DefaultValue("1")
	int repeatation_Count();
	
	@Key("repetition")
	@DefaultValue("1000")
	int repeatation();
	
	@Key("StartDate")
	String StartDate();
	
	@Key("StartTime")
	String StartTime();
	
	@Key("EndDate")
	String EndDate();
	
	@Key("EndTime")
	String EndTime();
	
}
