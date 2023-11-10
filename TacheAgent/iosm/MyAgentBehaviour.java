package iosm;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
public class MyAgentBehaviour extends Agent{
	
	@Override
	protected void setup() {
		System.out.println("Salut je suis un agent");
		addBehaviour(new Behaviour() {
			
			@Override
			public boolean done() {
			
				return true;
			}
			
			@Override
			public void action() {
				System.out.println("Execution de l'action");
			}
		});
		addBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				System.out.println("One-shot");
				
			}
		});
		addBehaviour(new WakerBehaviour(this,5000) { 
			public void onWake() {System.out.println("WakerBehaviour");
				
			}
		});
		super.setup();
	}

}
