package TN_Hiking.BD;

import TN_Hiking.Models.Parcours;
import TN_Hiking.Gestionnaires.GestionnaireParcours;


import java.util.ArrayList;

public class BaseDonnee {

    // BaseDonne est l'interface entre le support physique: dossier BDD/sous dossier parcours
    // avec le gestionnaireParcours qui est le support app

    protected ArrayList<Parcours> CurrentVersion;

    public BaseDonnee(){}






}
