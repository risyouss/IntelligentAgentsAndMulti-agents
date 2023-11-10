package atelierPingPong;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours. WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.core.Runtime;
import static java.lang.System.out;

public class AgentPing extends Agent{
	// Initialisation de l'agent
	@Override
	protected void setup() {
	String texteHello = "Bonjour à toutes et tous";
	out.println("De l'agent " + getLocalName()+":"+texteHello);
	out.println("Mon adresse est " + getAID());
	// si l'agent s'appelle ping,
	// ajout d'un comportement qui enverra le texte 'balle' à l'agent pong dans 10 secondes
	if(getLocalName().equals("ping")) {
	addBehaviour(new WakerBehaviour(this, 10000) {
	protected void onWake() {
	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.addReceiver(new AID("pong", AID.ISLOCALNAME));
	msg.setContent("balle");
	myAgent.send(msg);
	out.println("moi, " +getLocalName()+"je lance la balle");
	}
	});
	}
	
		
	addBehaviour(new Behaviour(this) {
		int step=0;
		public void action() {
		ACLMessage msg = receive();
		if(msg != null) {
			step++;
			String content = msg.getContent();
			AID sender = msg.getSender();
			out.println("agent " + getLocalName() +" : j'ai reçu "+content+"de"+ sender);
			myAgent.doWait(300);
			ACLMessage reply = msg.createReply();
			reply.setContent("balle-"+step);
			myAgent.send(reply);
			} else block();
		}
		public boolean done() { return step == 30; }	
	});
	
	}	
	
	@Override
	protected void takeDown() {
	out.println("Moi, Agent " + getLocalName() + " je quitte la plateforme ! ");}
	public static void main(String[] args) {
	// preparer les arguments pout le conteneur JADE
	Properties prop = new ExtendedProperties();
	// demander la fenetre de controle
	prop.setProperty(Profile.GUI,"true");
	// nommer les agents
	prop.setProperty(Profile.AGENTS,
	"ping:atelierPingPong.AgentPingPong;pong:atelierPingPong.AgentPingPong");
	// creer le profile pour le conteneur principal
	ProfileImpl profMain = new ProfileImpl(prop);
	// lancer le conteneur principal
	Runtime rt = Runtime.instance();
	rt.createMainContainer(profMain);
	
	
	
	
	}
	







}
