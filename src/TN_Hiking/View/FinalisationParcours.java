package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FinalisationParcours implements Initializable {
    @FXML
    MenuItem borderPane;
    @FXML
    Slider note;
    @FXML
    TextField lieuFin;
    @FXML
    TextField descrptionCourte;
    @FXML
    TextArea descriptionDetaillee;

    GestionnaireParcours gParcours;

    public FinalisationParcours(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }
    public void confirmerCreationparcours(){
        int size = this.gParcours.getParcours().size();
        this.gParcours.getParcours().get(size-1).setNote((int)this.note.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Félicitations");
        alert.setContentText("Votre parcours est crée avec succès!");
        alert.showAndWait();


    }
    public void closeApp() {
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.borderPane.setDisable(true);
    }
}
