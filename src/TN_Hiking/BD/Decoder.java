package TN_Hiking.BD;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;

import java.io.*;

public class Decoder {
    //aim of this function
    // -> analyse a file saveFile
    // -> create a gestionnaireParcours
    // -> fill the parcours and etapes as it should
    // -> does not save local//active or somethind -> it is a tool ;)
    // Guide: Decoder dc = new Decoder(); GestionnaireParcours newGP = dc.decodeAction(file); //done

    GestionnaireParcours gestionnaireParcours;
    String pathName ="localSave";

    public void Decoder(){
        this.gestionnaireParcours = new GestionnaireParcours();
    }
    public void setPathName(String pathName){
        this.pathName = pathName;
    }
    public String getPathName(){return this.pathName;}

    public GestionnaireParcours decodeAction(){
        // pour ouvrir et lire un fichier ligne par ligne

        File file = new File(pathName);
        BufferedReader bufferedReader = null; // for file reading

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int nbline = 0;
            int nbParcours = 0;
            int nbEtape = 0;
            while ((line = bufferedReader.readLine()) != null) {
                //Thirs step: write in the new parcors
                //System.out.println(line);
                if((line.equals("#INIT#"))&&(nbline==0)){
                    //System.out.println("Catchinit");
                    //Le fichier est a priori du bon format, on init le gestionnaire
                    gestionnaireParcours = new GestionnaireParcours();
                }
                else if ( nbline==0 ) { System.out.println("ERROR_FILE_FORMAT_GOOD:that is not an Hiking save file");break;}
                // on catch le debut d'un parcours pour la gestion
                else if ( (nbline >=1) &&( line.equals("#debutParcours" ) )){
                    //System.out.println("CATCH debutParcours"); // on peut signaler un debut de parcours
                    // on a un Debut Parcours catched,
                    // on va transferer le bufferedReader a un ParcoursHandler
                    // on va lire un peu dans le bufferedReader jusqu'au catch #finParcours
                    // a ce moment on rend le bufferedReader, et on aura un beau parcours a rajouter dans le gestionnaire

                    Parcours parcours;
                    parcours = catchParcours(bufferedReader);
                    // on le rajoute au gestionnaire
                    gestionnaireParcours.addParcours(parcours);

                }

                else if ((line.equals("#finGestion"))){
                    //System.out.println("catch finGestion");
                }
                nbline++;
            }/*whileBalise*/ } catch(IOException e) { e.printStackTrace(); }
        // on vide le bufferReader

        try { assert bufferedReader != null; bufferedReader.close(); } catch (IOException e){  e.printStackTrace(); }
        //gestionnaireParcours.showGestionnaire();
        return gestionnaireParcours;
    }


    public Parcours catchParcours(BufferedReader bufferedReader){
        Parcours parcours;
        parcours = new Parcours("",0,"");
        String line;
        // on a un bufferedWriter qui vaut ce qu'il vaut
        // on a dans l'etat actuel un catch debutParcours,
        // on va lire jusqu'a un catch finParcours

        //First step: normalement on a une ligne du genre :name1:123.092:49.008764:
        // on va deja decrypter les donners n'est ce pas
        // pour ce faire on lit la ligne suivante ) #debutParcours
        try {   // bloc catchant la premiere ligne de description du parcours
            line = bufferedReader.readLine();
                //System.out.println(line);
                String[] spliter = line.split(":");
                //System.out.println(spliter.length + "");
                //System.out.println(spliter[1]);
                //System.out.println(spliter[0]);
                parcours.setName(spliter[1]);
                parcours.setDifficulte(Integer.parseInt(spliter[2]));
                parcours.setDepart(spliter[3]);

        } catch(IOException e){ e.printStackTrace();}

        try{ // bloc catchant les etapes
            Etape etape;
            while(  !((line = bufferedReader.readLine() ).equals("#finParcours"))  )
            { // tant qu'on a pas catcher la fin d'un parcours, alors la ligne actuelle est une ligne d'etape
                //System.out.println(line);
                etape = etapeLineHandler(line); // on recupere une etape bien formee
                parcours.addEtape(etape);
            }
        } catch(IOException e){e.printStackTrace();}
        //parcours.showParcours();
        return parcours;
    }

    public Etape etapeLineHandler(String line){
        // reçoit une ligne d'un saveFile et genere notre etape bien formee
        Etape etape;
        etape = new Etape();
        // line doit ressembler a un truc du genre :name2:450.302:53.0097:, on sait parser ça :)
        String[] spliter = line.split(":");
        etape.setName(spliter[1]);
        etape.setLongitude(Double.parseDouble(spliter[2]));
        etape.setLatitude(Double.parseDouble(spliter[3]));
        //etape.etapeShow();
        return etape;
    }

}
