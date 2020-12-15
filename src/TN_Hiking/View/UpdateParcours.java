package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;

public class UpdateParcours {

    GestionnaireParcours ges;
    Parcours parcours;

    public UpdateParcours(GestionnaireParcours gestionnaireParcours){
        this.ges = gestionnaireParcours;
        this.parcours = new Parcours("ParcoursTest",5, "Moncuq");
    }

    

}
