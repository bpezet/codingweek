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
    // -> does not save local//active or something -> it is a tool ;)
    // Guide: Decoder dc = new Decoder(); GestionnaireParcours newGP = dc.decodeAction(file); //done
    // Nouvelle options -> s'occupe des images :)


    GestionnaireParcours gestionnaireParcours;

    String pathDirName = "BDD";
    String localSave ="localSave";

    public void Decoder(){
        this.gestionnaireParcours = new GestionnaireParcours();
    }
    public void setLocalSave(String localSave){ this.localSave = localSave; }
    public String getLocalSave(){return this.localSave;}
    public void setPathDirName(String pathDirName){this.pathDirName=pathDirName;}
    public String getPathDirName(){return this.pathDirName;}

    public GestionnaireParcours decodeAction(){
        // pour ouvrir et lire un fichier ligne par ligne
        // Achtung schopswherrr $$$ On doit retrouve le fichier a partir du path encule

        //###########################
        // d'abord on import les images
        // #############################
        String pathAlienSave;
        String pathLocalDir;
        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            pathAlienSave = pathDirName+"\\"+localSave;
            pathLocalDir = "src\\TN_Hiking\\Ressources\\Import";
        } else { // then it is a mac/linux piece of shit
            pathAlienSave = pathDirName+"/"+localSave;
            pathLocalDir = "src/TN_Hiking/Ressources/Import";
        }
        File src = new File(pathDirName);
        File dest = new File(pathLocalDir);

        FileHandling fl = new FileHandling();
        try{
        fl.copydir(src,dest);}catch(Exception e){e.printStackTrace();}


        // #########################

        File file = new File(pathAlienSave);
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


        //POUR LA PREMIERE LIGNE name:difficulte:note:departName:
        //POUR LA DEUXIEME LIGNE :descriptionCourte:
        //POUR LA TROISIEME LIGNE :descriptonLongue:
        // #LDbegin# et #LDEND#

        try {   // bloc catchant la premiere ligne de description du parcours

            // premire ligne name:difficulte:note:departName:
            line = bufferedReader.readLine();
            String[] splitter = line.split(":");
            parcours.setName(splitter[0]);
            parcours.setDifficulte(Integer.parseInt(splitter[1]));
            parcours.setNote(Integer.parseInt(splitter[2]));
            parcours.setDepartName(splitter[3]);
            // petit travail en plus à faire pour recuperer le pointeur d'image
            // on a juste la teté mais on connait très bien le directory
            if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
            {
                parcours.setImage("src\\TN_Hiking\\Ressources\\Import\\"+splitter[4]);
            } else { // then it is a mac/linux piece of shit
                parcours.setImage("src/TN_Hiking/Ressources/Import/"+splitter[4]);
            }
            // on passe a la deuxieme ligne: la description courte
            line = bufferedReader.readLine();
            parcours.setDescriptionCourte(line);
            // on passe a la troisieme ligne: la description courte, alors la on a un catch à faire
            line = bufferedReader.readLine();
            String descriptionDet="";
            if( line.equals("#LDbegin#") ){ // En sortant de ce if on en aura finit avec la description longue
                while( !((line = bufferedReader.readLine()).equals("#LDEND#"))){
                    // on est dans une ligne courante de descriptionDet
                    descriptionDet=descriptionDet.concat(line+"\n");
                }
            } else {System.out.println("ERROR FILE FORMAT");}

            parcours.setDescriptionDetaillee(descriptionDet);
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
