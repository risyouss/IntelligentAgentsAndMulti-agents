import jade.core.*;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
public class LaunchAgents {
public static void main(String[] args) {
	Properties prop = new ExtendedProperties();
	prop.setProperty(Profile.GUI, "true");
	prop.setProperty(Profile.AGENTS, "a1:Test(hello);a2:Test(salut)");
	ProfileImpl profMain = new ProfileImpl(prop);
	Runtime rt = Runtime.instance();
	rt.createMainContainer(profMain);	
}
}
 