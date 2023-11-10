// AcheteurAgent
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

            private AID[] vendeurs = {new AID("Vendeur1", AID.ISLOCALNAME), new AID("Vendeur2", AID.ISLOCALNAME)};
            private String clothes;
            private double prix;

            @Override
            public void action() {
                // Filtrer les messages
                try {
                    MessageTemplate template = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                            MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
                                    MessageTemplate.MatchPerformative(ACLMessage.REQUEST)));
                    ACLMessage aclMessage = receive(template);
                    if (aclMessage != null) {
                        switch (aclMessage.getPerformative()) {
                            case ACLMessage.REQUEST:
                                ++compteur;
                                System.out.println("#########################################");
                                System.out.println("Requete d'achat de vetement ");
                                System.out.println("From : " + aclMessage.getSender().getName());
                                clothes = aclMessage.getContent();
                                System.out.println("Vetement : " + clothes);
                                System.out.println("................................");
                                System.out.println("Envoi de la requete aux vendeurs");

                                for (AID vendeur : vendeurs) {
                                    ACLMessage msg = new ACLMessage(ACLMessage.CFP);
                                    msg.setContent(clothes);
                                    msg.setConversationId(clothes + " - " + compteur);
                                    msg.addUserDefinedParameter("compteur", String.valueOf(compteur));
                                    msg.addReceiver(vendeur);
                                    System.out.println("..........En cours vers " + vendeur.getLocalName());
                                    Thread.sleep(5000);
                                    send(msg);
                                }
                                break;

                            case ACLMessage.PROPOSE:
                                double receivedPrice = Double.parseDouble(aclMessage.getContent());
                                System.out.println("******************************");
                                System.out.println("Conversation ID : " + aclMessage.getConversationId());
                                System.out.println("Reception de l'offre");
                                System.out.println("From : " + aclMessage.getSender().getName());
                                System.out.println("Prix = " + receivedPrice);
                                System.out.println("................................");

                                // Choose the minimum price
                                if (receivedPrice < prix || prix == 0) {
                                    prix = receivedPrice;
                                }

                                break;

                            case ACLMessage.CONFIRM:
                                System.out.println("*........................");
                                System.out.println("Reçu de la confirmation...");
                                System.out.println("Conversation ID : " + aclMessage.getConversationId());

                                ACLMessage msg3 = new ACLMessage(aclMessage.INFORM);
                                msg3.addReceiver(aclMessage.getSender());
                                msg3.setConversationId(aclMessage.getConversationId());
                                msg3.setContent("<transaction>" + "<vetement>" + clothes + "</vetement>" + "<prix>"
                                        + prix + "</prix>" + "<fournisseur>" + aclMessage.getSender().getName()
                                        + "</fournisseur> + </transaction>");
                                send(msg3);
                                break;
                        }
                    } else {
                        block();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
