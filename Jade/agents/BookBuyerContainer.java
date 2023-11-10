package agents;
import jade.wrapper.AgentContainer;
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
public class BookBuyerContainer {
	public static void main(String[] args) {
		try {
			
			Runtime runtime=Runtime.instance();
			ProfileImpl profileImpl=new ProfileImpl(false);
			profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
			AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
			AgentController agentController=agentContainer.createNewAgent("acheteur1","agents.BookBuyerAgent",new Object[]{});
			agentController.start();
		}catch(Exception e) {e.printStackTrace();}
		
		
	}
}
