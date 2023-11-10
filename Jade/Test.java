import jade.core.Agent;
import jade.util.leap.Iterator;

public class Test extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("Hello world!");
		System.out.println("i'm agent");
		System.out.println("My local-name is "+getAID().getLocalName());
		System.out.println("My gauide" +getAID().getName());
		System.out.println("My adresses are:");
		Iterator it = getAID().getAllAddresses();
		while(it.hasNext()) {
			System.out.println("-" +it.next());
		}
		System.out.println("My Args are :");
		Object[] args = getArguments();
		if(args!= null) {
			for(int i=0;i<args.length;++i) {
				System.out.println("-"+args[i]);}}
			doDelete();
		}
		protected void takeDown() {System.out.println("bye...");}
	
	
}
