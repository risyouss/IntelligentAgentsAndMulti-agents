package iosm;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
public class ExerciceParallel extends Agent {

	@Override
	protected void setup() {
		System.out.println("Exercice d'xecution des comportement parallel)");
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				System.out.println("Cette action est pour le comportement Oneshot");
				
			}
		});
		parallelBehaviour.addSubBehaviour(new WakerBehaviour(this,5000) {
			public void onWake() {System.out.println("Cette action est pour le Comportement Waker");}
		});
		parallelBehaviour.addSubBehaviour(new TickerBehaviour(this ,1000) {
			
			private int c;
			@Override
			
			protected void onTick() {
				System.out.println("Cette action est pour le comportement TickerBehaviour");
				if(c<6) {
				System.out.println("Tick");
				System.out.println("Tack");
				c++;
				}else {stop();}
			}
			
		});
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			private int a;
			@Override
			public void action() {
				if(a<12) {
				System.out.println("Cette action est pour le comportement cyclic");
				a++;
				if(a>=12) {myAgent.removeBehaviour(this);
				}
				}
			}
			
		});
		super.setup();
	}
	
}