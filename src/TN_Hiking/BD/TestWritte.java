package TN_Hiking.BD;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;

import java.io.*;

public class TestWritte {

    public static void main(String[] args) {
        String BDD ="BDD";
        String localSave = "localSave";

        Writter wr = new Writter();


        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            wr.setPathName(BDD+"\\"+localSave);
        } else { // then it is a mac/linux piece of shit
            wr.setPathName(BDD + "/" + localSave);
        }







        String name1 = "name1";
        String name2 = "name2";
        double lat1 = 49.008764;
        double lat2 = 53.0097;
        double long1 = 123.092;
        double long2 = 450.302;

        Etape etape1 = new Etape(name1,lat1,long1);
        Etape etape2 = new Etape(name2,lat2,long2);

        Parcours parcours = new Parcours("Mon Premier Parcours",3,"Strasbourg");
        parcours.setEtapeDebut(etape1);
        parcours.setEtapeFin(etape2);

        Parcours parcours2 = new Parcours("Mon Deuxieme Parcours",4,"Nancy");
        parcours2.setEtapeDebut(etape2);
        parcours2.setEtapeFin(etape1);

        GestionnaireParcours gestionnaireParcoursTest;
        gestionnaireParcoursTest = new GestionnaireParcours();
        gestionnaireParcoursTest.addParcours(parcours);
        gestionnaireParcoursTest.addParcours(parcours2);

        wr.writeAction(gestionnaireParcoursTest);


        gestionnaireParcoursTest.showGestionnaire();

    }
}
