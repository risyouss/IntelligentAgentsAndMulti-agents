package application1;
import java.util.HashMap;
import java.util.Map;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class VendeurAgent extends Agent {
    private Map<String, Double> data = new HashMap<>();

    @Override
    protected void setup() {
        data.put("XML", 230.0);
        data.put("JAVA", 460.0);
        data.put("IOT", 540.0);

        System.out.println(".........Je suis le Vendeur " + this.getAID().getName());
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                try {
                    MessageTemplate messageTemplate = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.CFP),
                            MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
                    ACLMessage aclMessage = receive(messageTemplate);

                    if (aclMessage != null) {
                        switch (aclMessage.getPerformative()) {
                            case ACLMessage.CFP:
                                System.out.println("---------------------------");
                                System.out.println("Conversation ID : " + aclMessage.getConversationId());
                                String livre = aclMessage.getContent();
                                String compteur = aclMessage.getUserDefinedParameter("compteur");
                                System.out.println("Réception d'un message:" + compteur);
                                System.out.println("Expéditeur : " + aclMessage.getSender().getName());
                                System.out.println("Contenu: " + livre);
                                System.out.println("------------------");

                                Double prix = data.get(livre);
                                ACLMessage reply = aclMessage.createReply();
                                reply.setPerformative(ACLMessage.PROPOSE);
                                reply.setContent(prix.toString());

                                System.out.println(".....En cours");
                                Thread.sleep(5000);
                                send(reply);
                                break;

                            case ACLMessage.ACCEPT_PROPOSAL:
                                System.out.println("--------------------------------");
                                System.out.println("Conversation ID:" + aclMessage.getConversationId());
                                System.out.println("Validation de la transaction......");
                                ACLMessage reply2 = aclMessage.createReply();
                                reply2.setPerformative(ACLMessage.CONFIRM);
                                System.out.println(".....En cours");
                                Thread.sleep(5000);
                                send(reply2);
                                break;
                        }
                    }
                    else {
                    	System.out.println("Block");
                    	block();
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Use 'printStackTrace' to print the exception details
                }
			}
		});

	}
}
