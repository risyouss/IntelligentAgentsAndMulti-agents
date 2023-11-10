package application2;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class VendeurContainer {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            ProfileImpl profileImpl = new ProfileImpl(false);
            profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");

            AgentContainer agentContainer = runtime.createAgentContainer(profileImpl);

            // Launch the first Vendeur
            AgentController agentController1 = agentContainer.createNewAgent("Vendeur1", "application2.Vendeur", new Object[]{});
            agentController1.start();

            // Launch the second Vendeur
            AgentController agentController2 = agentContainer.createNewAgent("Vendeur2", "application2.Vendeur", new Object[]{});
            agentController2.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
