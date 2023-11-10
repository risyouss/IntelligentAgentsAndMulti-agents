package iosm;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.introspection.AddedBehaviour;


public class MyAgentBehaviour1 extends Agent{
	@Override
	protected void setup() {
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				System.out.println("CyclicBehaviour");
				
			}
		});
		super.setup();
	}
}
			
