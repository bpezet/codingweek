package TN_Hiking.BD;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class Writter{

    private String pathName = "src\\TN_Hiking\\BD\\INTEL";
    public void Writter(){}

    // methode principale qui permet d'ecrire dans le fichier en ecrasant tout
    public void writeAction(GestionnaireParcours gestionnaireParcours){
        // pour ouvrir et lire un fichier ligne par ligne
        File file = new File(pathName);
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
        String name = parcours.getName();
        int diff = parcours.getDifficulte();
        String debut = parcours.getDepart();

        try{ bufferedWriter.write(";"+name+";"+diff+";"+debut+";");} catch(IOException e){e.printStackTrace();}
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
        bufferedWriter.write(":"+name+":"+longitude+":"+latitude+":");} catch (IOException e){e.printStackTrace();}
    }



    public void setPathName(String pathName)
    {this.pathName=pathName;}
    public String getPathName(){return this.pathName;}

}
