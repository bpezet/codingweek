package TN_Hiking.BD;


import TN_Hiking.Gestionnaires.GestionnaireParcours;

public class TestDec {

    public static void main(String[] args){

        String BDD = "BDD";
        String localSave = "localSave";

        System.out.println("Init Decoder Test");


        Decoder dc = new Decoder();
        GestionnaireParcours newGP = new GestionnaireParcours();
        dc.setPathDirName("BDD");

        newGP = dc.decodeAction();

        //test de fin: on print la gueule du gestionnaire
        newGP.showGestionnaire();
    }


}
