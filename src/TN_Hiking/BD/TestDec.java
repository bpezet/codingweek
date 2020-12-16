package TN_Hiking.BD;


import TN_Hiking.Gestionnaires.GestionnaireParcours;

import java.io.*;

public class TestDec {

    public static void main(String[] args){

        String BDD = "BDD";
        String localSave = "localSave";

        System.out.println("Init Decoder Test");


        Decoder dc = new Decoder();
        GestionnaireParcours newGP = new GestionnaireParcours();

        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            dc.setPathName(BDD+"\\"+localSave);
        } else { // then it is a mac/linux piece of shit
            dc.setPathName(BDD + "/" + localSave);
        }

        newGP = dc.decodeAction();

        //test de fin: on print la gueule du gestionnaire
        newGP.showGestionnaire();
    }


}
