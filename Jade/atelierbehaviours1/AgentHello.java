package atelierbehaviours1;

import jade.core.Agent;
import static java.lang.System.out;
public class AgentHello extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void setup() {
		String texteHello = "Bonjour tout le monde";
		out.println("De l'agent " + getLocalName() + " : " + texteHello); 
		out.println("Mon adresse est " + getAID());
	}
	@Override
	protected void takeDown(){
		out.println("Moi, Agent " + getLocalName() + " je quitte la plateforme ! ");
	}
	public static void main(String[] args) {
		String[] jadeArgs = new String[2];
		StringBuilder sbAgents = new StringBuilder();
		sbAgents.append("a1:atelierbehaviours1.AgentHello").append(";");
		jadeArgs[0] ="-gui";
		jadeArgs[1] = sbAgents. toString();
		jade.Boot.main(jadeArgs);
}
}