package atelierPingPong;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
public class AgentPingPong extends Agent {

	@Override
	protected void setup() {
		String texteHello = "Bonjour à toutes et tous";
		System.out.println("De l'agent"+getLocalName()+":"+texteHello);
		System.out.println("Mon adresse est"+getAID());
		if(getLocalName().equals("Ping")) {
			
			addBehaviour(new WakerBehaviour(this,10000) {
				protected void onWake() {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				}
			});
			
		}
		super.setup();
	}
	
}
