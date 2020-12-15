package TN_Hiking.View;


import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;
import TN_Hiking.Models.Parcours;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.security.GeneralSecurityException;



public class showParcours {

    // Ce qu'on recupere d'un Parcours
    private String name = "Best Hiking";
    private int difficulte = 2;
    private String depart = "Strasbourg";

    // Ce qu'il est sur la viex
    @FXML
    private Label LabelParcours;
    @FXML
    private Label LabelDifficultee;
    @FXML
    private Label LabelDepart;
    @FXML ImageView image;

    @FXML
    private void initialize(){
        LabelParcours.setText(this.name);
        LabelDifficultee.setText(""+this.difficulte);
        LabelDepart.setText(this.depart);
        image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

    }

    public void afficherParcours() {
        Parcours parcours = new Parcours(this.name,this.difficulte,this.depart);
        //this.gParcours.addParcours(parcours);
        // -> pour l'instant on va juste afficher random pour voir
        //System.out.println(this.gParcours.getParcours().get(0).getName());
        // -> Pas besoin de debug pr l'instant
    }


}
