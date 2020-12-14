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


}
