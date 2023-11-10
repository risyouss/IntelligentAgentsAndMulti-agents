package iosm;
import jade.core.Agent;
public class TroisEtapesAgent extends Agent {
	protected void setup() {
		System.out.println("De l'agent"+getLocalName()+": Hello!");
		TroisEtapes comp3 =new TroisEtapes();
		addBehaviour(comp3);
		
		
	}
	protected void takeDown() {
		System.out.println("Agent" +getLocalName()+"Quitte la platforme");
		
		
	}

}
