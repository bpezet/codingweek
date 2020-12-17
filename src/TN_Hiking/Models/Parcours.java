package TN_Hiking.Models;

import javafx.scene.image.Image;
import java.lang.Math.*;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.ArrayList;

public class Parcours {
    protected String name;
    protected int difficulte;
    protected String descriptionCourte;
    protected String descriptionDetaillee;
    protected ArrayList<Etape> etapes;
    protected int note;
    protected String departName;
    protected Etape etapeDebut;
    protected String imageParcours;
    protected Etape etapeFin;


    //private String fichierExcelCoords;
    // j'ai supprime fichier ExcelCoors car on a GPX impport
    public Parcours(String name, int difficulte, String departName) {
        this.name = name;
        this.difficulte = difficulte;
        this.departName = departName;

        this.etapes = new ArrayList<>();


    }

    public void setEtapeDebut(){
        this.etapeDebut = new Etape(this.departName,0,0);
        this.etapes.set(0,this.etapeDebut);
    }

    public Parcours(String name, int difficulte, Etape etapeDebut, Etape etapeFin) {
        this.name = name;
        this.difficulte = difficulte;
        this.etapeDebut = etapeDebut;
        this.etapeFin = etapeFin;
        this.etapes = new ArrayList<>();
    }



    /** Getters and setters */
    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String depart) {
        this.departName = depart;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public String getDescriptionCourte() {
        return descriptionCourte;
    }

    public void setDescriptionCourte(String descriptionCourte) {
        this.descriptionCourte = descriptionCourte;
    }

    public String getDescriptionDetaillee() {
        return descriptionDetaillee;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setDescriptionDetaillee(String descriptionDetaillee) {
        this.descriptionDetaillee = descriptionDetaillee;
    }

    public ArrayList<Etape> getEtapes() {
        return etapes;
    }

    public void addEtape(Etape etape){
        this.etapes.add(etape);
    }

    public void setEtapes(ArrayList<Etape> etapes) {
        this.etapes = etapes;
    }

    public Etape getEtapeDebut() {
        return etapeDebut;
    }

    public void setEtapeDebut(Etape etapeDebut) {
        this.etapes.add(etapeDebut);
    }

    public Etape getEtapeFin() {
        return etapeFin;
    }

    public void setEtapeFin(Etape etapeFin) {
        this.etapes.add(etapeFin);
    }

    public String getImage(){return this.imageParcours;}
    public void setImage(String imageParcours){this.imageParcours = imageParcours;}

    public void showParcours(){
        System.out.println("ParcoursNom:"+this.name+" Difficulte:"+this.difficulte+" DepartName:"+this.departName);
        System.out.println("DescriptionCourte:"+this.descriptionCourte+" Note:"+this.note+" imageParcours:" +this.imageParcours);
        System.out.println("DescriptionDetaillee:"+this.descriptionDetaillee);
        for(int i = 0;i<etapes.size();i++) {
            etapes.get(i).etapeShow();
        }
        System.out.println("Fin du parcours "+this.name);
    }

    public Etape getSpecificEtape(int i){
        return this.etapes.get(i);
    }

    public void suppressionEtape (int i){this.etapes.remove(i);}

    public void addSpecificEtape(int i,Etape etape){
        this.etapes.add(i,etape);
    }

    public double getDistance(){
        double dist =0.0;
        if( this.etapes.size()<=1 ){
            System.out.println("error: pas assez d'etapes \n");
        } else {

            for(int i = 0; i < this.etapes.size()-1 ; i++){
                dist += getEcart(this.etapes.get(i),this.etapes.get(i+1));
            }
        }
        return dist;
    }
    public double getEcart(Etape e1,Etape e2){
        double ecart = 0.0;
        double lat1 = e1.getLatitude()*((Math.PI)/180);
        double lat2 = e2.getLatitude()*((Math.PI)/180);

        double long1 = e1.getLongitude()*((Math.PI)/180);
        double long2 = e2.getLongitude()*((Math.PI)/180);

        // Distance en Km= ACOS(SIN(lat1)SIN(lat2)+COS(lat1)COS(lat2)COS(lon2-lon1))6371
        // y a une conversion degrés radian a faire
        // 180° = 2pi rad donc
        // x°   = x*2pi/180
        ecart = 6371*Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(long2-long1));

        return ecart;
    }

    public Double getDuree()
    {
        Double vitesseMoyenne = 4.0; //  km.h-1
        Double distance = this.getDistance();
        return distance/vitesseMoyenne;
    }

}
