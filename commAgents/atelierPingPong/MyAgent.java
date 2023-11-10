package atelierPingPong;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours. WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.core.Runtime;
import static java.lang.System.out;

public class MyAgent extends Agent {
		protected void setup() {
		System.out.println("Salut je suis un agent!");
		System.out.println("Mon nom est "+this.getAID().getName());
		System.out.println("Je me prépare ..... ");
		//Ajouter un comportement a cet agent en utilisant une classe anonyme
		addBehaviour(new CyclicBehaviour() {
		public void action() {
			// prendre un message de la file
			ACLMessage msg = receive() ;
			if (msg != null) {
			System.out.println("Réception d'un nouveau message");
			// récuperation du contenu :
			String contenu = msg.getContent();
			System.out.println("contenu de message "+contenu);
			// récuperation de l'adresse de l'emetteur
			AID adresseEmetteur = msg.getSender();
			System.out.println("recuperation de l'adresse de l'emetteur "+adresseEmetteur);
			String nomEmetteur = adresseEmetteur.getLocalName();
			System.out.println("Nom local de Emetteur "+nomEmetteur);	
	
		//Acte de communication
		System.out.println("Acte de communication "+ACLMessage.getPerformative(msg.getPerformative()));
		//Langagae
		System.out.println("langage "+msg.getLanguage());
		//Ontology
		System.out.println("Ontology "+msg.getOntology());
		
		//Repondre
		if(msg.getOntology().equals("cours")) {
		System.out.println("Ontology cours");
		ACLMessage reponse=msg.createReply();
		reponse.setContent("Message bien recu");
		send(reponse);}
		else if(msg.getOntology().equals("exam")) {
		System.out.println("Ontology exam");
		ACLMessage reponse=msg.createReply();
		reponse.setContent("je vais me preparer");
		send(reponse);
		}}
		else {
		block();}}
		});}}