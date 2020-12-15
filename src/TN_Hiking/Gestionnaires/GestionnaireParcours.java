package TN_Hiking.Gestionnaires;

import TN_Hiking.Models.Parcours;

import java.util.ArrayList;

public class GestionnaireParcours {

    protected ArrayList<Parcours> parcours;

    public GestionnaireParcours() {
        this.parcours = new ArrayList<Parcours>();
    }
    public void addParcours(Parcours parcours) {
        this.parcours.add(parcours);
    }


    /**Getters and Setters*/


    public ArrayList<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }

    public void showGestionnaire(){



       System.out.println( "Gestionnaire de taille"+ this.parcours.size()+"");
       for(int i = 0; i<parcours.size();i++)
       {
           parcours.get(i).showParcours();
       }
       System.out.println("Fin du Gestionnaire");
    }

}
