package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.security.GeneralSecurityException;


public class CreateParcoursView {
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> difficulte;
    @FXML
    private TextField depart;


    private GestionnaireParcours gParcours ;



    public CreateParcoursView(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }

    public void tracerParcours() {
        Parcours parcours = new Parcours(this.name.getText(),Integer.parseInt(this.difficulte.getValue()),this.depart.getText());
        this.gParcours.addParcours(parcours);
        System.out.println(this.gParcours.getParcours().get(0).getName());
    }

}
