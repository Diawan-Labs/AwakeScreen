package labs.diawan.PropertiesConfigClasses;

import java.io.PrintStream;
import java.util.ArrayList;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({"file:resources/conf/PressKeys.properties"})
public interface PressKeysConfig extends Config {

	@Key("Keys")
	@DefaultValue("1")
	ArrayList<Integer> Keys();
	
	@Key("IteraionCount")
	@DefaultValue("1")
	int repeatationCount();
	
	@Key("IterationSeconds")
	@DefaultValue("10")
	int repeatation();
	
	@Key("StartDateandTime")
	String startDateandTime();
	
	@Key("EndDateandTime")
	String endDateandTime();
	
	void list(PrintStream out);
	
}
