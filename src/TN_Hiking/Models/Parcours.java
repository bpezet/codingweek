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
    protected String depart;
    protected Etape etapeDebut;
    protected Image imageParcours;
    protected Etape etapeFin;
    private String fichierExcelCoords;

    public Parcours(String name, int difficulte, String depart) {
        this.name = name;
        this.difficulte = difficulte;
        this.etapeDebut = new Etape();
        this.etapeDebut.setName(depart);
        this.etapeFin = new Etape();
        this.etapes = new ArrayList<>();
        this.etapes.add(this.etapeDebut);
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
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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

    public void showParcours(){
        System.out.println("Parcours "+name+" diff "+difficulte+" depart "+etapeDebut.getName());
        for(int i = 0;i<etapes.size();i++) {
            etapes.get(i).etapeShow();
        }
        System.out.println("Fin du parcours "+name);
    }

    public Etape getSpecificEtape(int i){
        return this.etapes.get(i);
    }

}
