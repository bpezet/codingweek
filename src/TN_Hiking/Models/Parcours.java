package TN_Hiking.Models;

import javafx.scene.image.Image;

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
    private String fichierExcelCoords;

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
    public String getDepart() {
        return departName;
    }

    public void setDepart(String depart) {
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
        System.out.println("Parcours "+name+" diff "+difficulte+" depart "+departName);
        for(int i = 0;i<etapes.size();i++) {
            etapes.get(i).etapeShow();
        }
        System.out.println("Fin du parcours "+name);
    }

    public Etape getSpecificEtape(int i){
        return this.etapes.get(i);
    }

    public void suppressionEtape (int i){this.etapes.remove(i);}

    public void addSpecificEtape(int i,Etape etape){
        this.etapes.add(i,etape);
    }

}
