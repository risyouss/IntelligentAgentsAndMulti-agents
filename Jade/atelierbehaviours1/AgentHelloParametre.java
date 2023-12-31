package atelierbehaviours1;

import jade.core.Agent; 
import static java.lang.System.out;
public class AgentHelloParametre extends Agent{

	@Override
	protected void setup() {

	String texteHello = null;

	Object[]params = this.getArguments(); 
	if(params.length>0) texteHello = (String) params[0]; 
	else texteHello = "Bonjour à tout";

	out.println("De l'agent " + getLocalName() + ":"+texteHello);
	out.println("Mon adresse est " + getAID());
	//l'agent demande son retrait de la plateforme..

	doDelete();
	}
	//netoyage de l'agent
	@Override
	protected void takeDown() {
		out.println("Moi, Agent" + getLocalName() + "je quitte la plateforme ! ");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] jadeArgs = new String[2];
		StringBuilder sbAgents = new StringBuilder(); 
		sbAgents.append("agentA:atelierbehaviours1.AgentHelloParametre(coucou)").append(";"); 
		sbAgents.append("agentB:atelierbehaviours1.AgentHelloParametre(bonjour)").append(";");
		jadeArgs[0]="-gui";
		jadeArgs[1]= sbAgents.toString(); 
		jade.Boot.main(jadeArgs);

	}

}