package iosm;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
public class MyAgentBehaviour2 extends Agent{
	@Override
	protected void setup() {
		addBehaviour(new TickerBehaviour(this,1000) {
			public void onTick() {
				System.out.println(myAgent.getLocalName()+"tictac");
				
				
			}
			
		});
		super.setup();
	}
}
