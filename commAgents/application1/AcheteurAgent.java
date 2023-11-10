package application1;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AcheteurAgent extends Agent {
	private int compteur;
	protected void setup() {
		System.out.println("Salut je suis l'agent Acheteur mon nom est : " + this.getAID().getName());
		addBehaviour(new CyclicBehaviour() {
			
			private AID request;
			private String livre;
			private double prix;
			@Override
			public void action() {
				//Filtrer les messages 
				try {
					MessageTemplate template = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM), MessageTemplate.MatchPerformative(ACLMessage.REQUEST)));
					ACLMessage aclMessage = receive(template);
					if(aclMessage != null) {
						switch(aclMessage.getPerformative()) {
						case ACLMessage.REQUEST:
							++compteur;
							System.out.println("#########################################");
							System.out.println("Requete d'achat de livre ");
							System.out.println("From : " + aclMessage.getSender().getName());
							livre = aclMessage.getContent();
							request = aclMessage.getSender();
							System.out.println("Livre : " + livre);
							System.out.println("................................");
							System.out.println("Envoi de la requete");
							
							ACLMessage msg = new ACLMessage(ACLMessage.CFP);
							msg.setContent(livre);
							msg.setConversationId(livre + " - " + compteur);
							msg.addUserDefinedParameter("compteur ", String.valueOf(compteur));
							msg.addReceiver(new AID("Vendeur1", AID.ISLOCALNAME));
							System.out.println("..........En cours");
							Thread.sleep(5000);
							send(msg);
							break;
							
						case ACLMessage.PROPOSE:
							prix = Double.parseDouble(aclMessage.getContent());
							System.out.println("******************************");
							System.out.println("Conversation ID : " + aclMessage.getConversationId());
							System.out.println("Reception de l'offre");
							System.out.println("From : " + aclMessage.getSender().getName());
							System.out.println("Prix = " + prix);
							System.out.println("................................");
							System.out.println("Conclusion de la transaction......");
							
							ACLMessage aclMessage2 = aclMessage.createReply();
							aclMessage2.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
							System.out.println("......En cours");
							Thread.sleep(5000);
							send(aclMessage2);
							break;
							
						case ACLMessage.CONFIRM:
							System.out.println("*........................");
							System.out.println("Reçu de la confirmation...");
							System.out.println("Conversation ID : " + aclMessage.getConversationId());
							
							ACLMessage msg3 = new ACLMessage(aclMessage.INFORM);
							msg3.addReceiver(request);
							msg3.setConversationId(aclMessage.getConversationId());
							msg3.setContent("<transaction>" + "<livre>"+livre+"</livre>" + "<prix>"+prix+"</prix>" + 
									"<fourniseur>"+aclMessage.getSender().getName()+"</fourniseur> + </transaction>" );
							send(msg3);
							break;
							
						
						}
					}
					else {
						block();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
