package TN_Hiking.View;

import TN_Hiking.Models.Parcours;

import java.util.ArrayList;

public class WelcomeView {
    private ArrayList<Parcours> parcours;
    private int i=0;
    public WelcomeView() {
        this.parcours = new ArrayList<Parcours>();
    }
    public void parcoursCreate() {
        i++;
        System.out.println(i);
    }

}
