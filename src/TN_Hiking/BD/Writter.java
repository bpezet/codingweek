package TN_Hiking.BD;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class  Writter{

    private String pathDirName = "BDD";
    private String localSave = "localSave";
    public void Writter(){}
    // methode principale qui permet d'ecrire dans le fichier en ecrasant tout
    public void writeAction(GestionnaireParcours gestionnaireParcours){
        // on peut commencer par ajouter toutes les photos dans ressources/import
        // dans le dossier pathName
        String pathlocalSave;

        //#################################################################################################
        // deja on recupere la gueule du dossier ressources/import
            String pathRSR;
            if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            pathRSR = "src\\TN_Hiking\\Ressources\\Import";
            pathlocalSave = pathDirName+"\\"+localSave;
        } else { // then it is a mac/linux piece of shit
            pathRSR="src/TN_Hiking/Ressources/Import";
            pathlocalSave = pathDirName+"/"+localSave;
        }
        // on appel notre FileHandling
            FileHandling fl = new FileHandling();
            File fileSRC = new File(pathRSR);
            File fileDest = new File(pathDirName);
            try {
                fl.copydir(fileSRC, fileDest);
            } catch (IOException e){e.printStackTrace();}
        // ################################################################


        // pour ouvrir et ecrire un fichier ligne par ligne
        File file = new File(pathlocalSave);
        BufferedWriter bufferedWriter = null;
        // try exceptions obligatoire
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            String line = "#INIT#\n";
            bufferedWriter.write(line);
            WriteGestionnaireParcours(gestionnaireParcours , bufferedWriter);
        } catch ( IOException e) { e.printStackTrace(); }
        //on ferme le buffer apres usings
        try { assert bufferedWriter != null;  bufferedWriter.close();
        } catch (IOException e) { e.printStackTrace(); }

    }

    public void WriteGestionnaireParcours(GestionnaireParcours gestionnaireParcours,BufferedWriter bufferedWriter)
    {
        //on indique le debut du fichier
        try{bufferedWriter.write("#debutGestion\n");} catch (IOException e){ e.printStackTrace();}
        ArrayList<Parcours> arrayList = new ArrayList<Parcours>();
        arrayList = gestionnaireParcours.getParcours();
        for(int k = 0; k < arrayList.size();k++){
            WriteParcours(arrayList.get(k),bufferedWriter);
        }
        // a ce moment la on a tout printer
        try{bufferedWriter.write("#finGestion");} catch(IOException e){e.printStackTrace();}//dernière ligne, on ne print pas le \n

    }

    public void WriteParcours(Parcours parcours, BufferedWriter bufferedWriter)
    {
        //On commence par indiquer le debut du parcours
        try{bufferedWriter.write("#debutParcours\n");}catch(IOException e){e.printStackTrace();}
        //On commence par rajouter les donnees principales

        //POUR LA PREMIERE LIGNE name:difficulte:note:departName:
        String name = parcours.getName();
        int diff = parcours.getDifficulte();
        int note = parcours.getNote();
        String departName = parcours.getDepartName();
        //l'image est un peu special, on va recuperer que la tete
        String iP = parcours.getImage();
        File file = new File(iP);
        String imageParcours = file.getName(); // on recupere bien que la tete, le reste c'est pour dieux
        //POUR LA DEUXIEME LIGNE :descriptionCourte:
        String descriptionCourte = parcours.getDescriptionCourte();
        //POUR LA TROISIEME LIGNE :descriptonLongue:
        // ici on a un text sur plusieurs ligne, on va faire un fonctionnement en balise spéciale
        // sinon on s'en sortira pas
        String descriptionLongue = parcours.getDescriptionDetaillee();


        try{


            bufferedWriter.write(name+":"+diff+":"+note+":"+departName+":"+imageParcours+"\n"); //premiere ligne
            bufferedWriter.write(descriptionCourte + "\n"); //deuxieme ligne
            bufferedWriter.write("#LDbegin#\n");
            bufferedWriter.write(descriptionLongue );//troisieme ligne
            bufferedWriter.write("#LDEND#\n");

        } catch(IOException e){e.printStackTrace();}
        // on rajoute les etapes
        ArrayList<Etape> arrayList = new ArrayList<Etape>();
        arrayList = parcours.getEtapes();
        for(int k = 0;k < arrayList.size();k++){
            WriteEtape(arrayList.get(k),bufferedWriter);
        }
        //on a finis d'ajouter toutes les etapes, on a donc tout afficher, on print un caractere de fin de parcours
        try{bufferedWriter.write("#finParcours\n");} catch(IOException e){e.printStackTrace();} //indique la fin d'un parcours
    }

    public void WriteEtape(Etape etape,BufferedWriter bufferedWriter){
        String name = etape.getName();
        Double longitude = etape.getLongitude();
        Double latitude = etape.getLatitude();
        try{
        bufferedWriter.write(":"+name+":"+longitude+":"+latitude+":\n");} catch (IOException e){e.printStackTrace();}
    }



    public void setPathDirName(String pathDirName)
    {this.pathDirName=pathDirName;}
    public String getPathDirName(){return this.pathDirName;}

}
