package TN_Hiking.Models;

import java.util.ArrayList;

public class Parcours {
    protected String name;
    protected int difficulte;
    protected String descriptionCourte;
    protected String descriptionDetaillee;
    protected ArrayList<Etape> etapes;
    protected Etape etapeDebut;
    protected String depart;
    protected Etape etapeFin;

    public Parcours(String name, int difficulte, String depart) {
        this.name = name;
        this.difficulte = difficulte;
        this.depart = depart;
        this.etapes = new ArrayList<>();
    }

    public Parcours(String name, int difficulte, Etape etapeDebut, Etape etapeFin) {
        this.name = name;
        this.difficulte = difficulte;
        this.etapeDebut = etapeDebut;
        this.etapeFin = etapeFin;
        this.etapes = new ArrayList<>();
    }
                /** Getters and setters */

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
        this.etapeDebut = etapeDebut;
    }

    public Etape getEtapeFin() {
        return etapeFin;
    }

    public void setEtapeFin(Etape etapeFin) {
        this.etapeFin = etapeFin;
    }
}
