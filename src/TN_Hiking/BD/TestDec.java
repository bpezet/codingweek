package TN_Hiking.BD;


import TN_Hiking.Gestionnaires.GestionnaireParcours;

import java.io.*;

public class TestDec {

    public static void main(String[] args){

        //Writter wr = new Writter();
        //wr.WR();

        System.out.println("Init Decoder Test");


        Decoder dc = new Decoder();
        GestionnaireParcours newGP = new GestionnaireParcours();
        newGP = dc.decodeAction();

        //test de fin: on print la gueule du gestionnaire
        //newGP.showGestionnaire();
    }


}
