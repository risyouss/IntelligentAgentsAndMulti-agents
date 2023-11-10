package application1;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class VendeurContainer {
	public static void main(String[] args) {
		try {
			Runtime runtime = Runtime.instance();
			ProfileImpl profilrImpl = new ProfileImpl(false);
			profilrImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
			AgentContainer agentContainer = runtime.createAgentContainer(profilrImpl);
			AgentController agentController = agentContainer.createNewAgent("Vendeur1", "com.VendeurAgent", new Object[] {});
			agentController.start();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

}