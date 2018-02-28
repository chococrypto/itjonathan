import java.util.*;
/**
 * Module 633.1-Programmation - TP P04 - Parking
 *
 * Application parking silo
 *
 * Modélisation d'un "étage" contenant les box.
 * Mémorisation du n° de l'étage et de la liste des références sur des objets de type classe Box
 *
 * @author VOTRE NOM
*/
public class Etage {
		
	/* Attributs */ 
	private int no; /* Identifiant */
	private ArrayList boxes;
		
	/* Constructeur	*/ 
	public Etage (int no) {
		this.no = no;
		boxes = new ArrayList(); // Création effective de la liste des box
	}
		 
	/* Accesseurs */ 
	public int getNo() {return no;}
	public ArrayList getBoxes () {return boxes;}
	public Box getBox (int noBox) {
		int pos = boxes.indexOf(new Box(noBox));
		return pos < 0 ? null : (Box)boxes.get(pos);
	}

	/* Ajoute un box */
	public void add (Box box) {boxes.add(box);}	
	 
	/* Retourne le nombre de véhicules (somme de tous les types de véhicules) */ 
	public int getNbVehicules () {
		return boxes.size();
	}
	 
	/* Retourne le montant total (sommes des montants de tous les types de véhicules) */ 
	public double getMontant () {
		Box box;
		double montant = 0;
		for(int i = 0;i < boxes.size();i++){
			box = (Box)boxes.get(i);
			//on va chercher le montant par type
			montant = montantParType(box,montant);
		}
		return montant;
	}

	private double montantParType(Box box, double montant){
		if(box.getTypeVehicule() == 0){
			//velo
			montant = montant + (Parking.PRIX_VELO * box.getNbQuartHeures());
		}else if(box.getTypeVehicule() == 1){
			//moto
			 montant = montant + (Parking.PRIX_MOTO * box.getNbQuartHeures());
		}else{
			//voiture
			montant = montant + (Parking.PRIX_VOITURE * box.getNbQuartHeures());
		}
		return montant;
	}
		 
	
	public int nbTypeVehicule(int typeVehicule){
		int compt = 0;
		for(int i = 0;i < boxes.size();i++){
			if(((Box)(boxes.get(i))).getTypeVehicule() == typeVehicule){
				compt++;
			}
		}
		return compt;
	}



	

	
	public double montantParType(int typeVehicule){

		double montant = 0;
		Box box;

		for(int i = 0 ;i < boxes.size();i++){
			if(((Box)boxes.get(i)).getTypeVehicule() == typeVehicule){
				box = (Box)boxes.get(i);
				montant = montantParType(box,montant);
			}
		}
		return montant;
	}

	/* Supprime le box contenant le véhicule designé par son IdVehicule
	 * Retourne : vrai si l'opération s'est bien déroulée, faux sinon
	*/	
	public boolean removeVehicule (String idVehicule) {
		int i = 0;
		int pos = boxes.size() - 1;

		Vehicule vehicule = new Voiture(idVehicule);
		
		Box box = new Box(0,vehicule);
		boxes.add(pos,box);

		while(!vehicule.equals(((Box)boxes.get(i)).getVehicule())){
			i++;
		}

		boxes.remove(pos);

		if(i < pos){
			boxes.remove(i);
			return true;
		}
		return false;
	}

	public void affichage(){
		for(int i = 0;i < boxes.size();i++){
			System.out.println(((Box)boxes.get(i)).getVehicule());
		}
	}

	 
	/* Retourne vrai s'il n'y a pas de véhicule sur l'étage */
	public boolean estVide () {return boxes.isEmpty();}
	 
	public String toString () {return "Etage n° :"+no;}
	 
	/* Deux étages sont égaux si leur numéro est identique */
	public boolean equals (Object obj) {return ((Etage)obj).no == no;}
}