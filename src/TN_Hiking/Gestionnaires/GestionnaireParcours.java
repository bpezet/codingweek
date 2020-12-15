package TN_Hiking.Gestionnaires;

import TN_Hiking.Models.Parcours;



import java.util.ArrayList;
import java.util.Observable;

public class GestionnaireParcours {
    protected ArrayList<Parcours> parcours;

    public GestionnaireParcours() {
        this.parcours = new ArrayList<Parcours>();
    }
    public void addParcours(Parcours parcours) {
        this.parcours.add(parcours);
    }
    public void  deleteParcours() {
        this.parcours.remove(this.parcours.size()-1);
    }

    /**Getters and Setters*/


    public ArrayList<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }



}
