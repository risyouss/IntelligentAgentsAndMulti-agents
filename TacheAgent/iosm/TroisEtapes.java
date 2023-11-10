package iosm;
import jade.core.behaviours.Behaviour;
public class TroisEtapes extends Behaviour{
	private int step = 0;
	public void action() {
		switch(step) {
		case 0 : 
			affiche("Etape 1");
			step++;
			break;
		case 1 : 
			affiche("Etape 2");
			step++;
			break;
		case 2 :
			affiche("Etape 3");
			step++;
			break;
		
		
		
		
		}
	}
	private void affiche(String texte) {
		System.out.println(myAgent.getLocalName()+":"+texte);
	}
	public boolean done() {
		boolean end = (step==3);
		if(end)
			myAgent.doDelete();
		return end;
		
		
	}
	




}
