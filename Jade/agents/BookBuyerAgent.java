package agents;
import jade.core.Agent;



public class BookBuyerAgent extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void setup() {
		System.out.println("Salut je suis L'acheteur");
		System.out.println("My name is "+this.getAID().getName());
		System.out.println("Je me prépare ....");

	}
	@Override
	protected void beforeMove() {
		System.out.println("Avant de migrer vers une nouvelle location ....");	
	}
	@Override
	protected void afterMove() {
		System.out.println("viens d'arriver ....");	
	}
	@Override
	protected void takeDown() {
		System.out.println("Aavant de mourir....");	
	}
}
