package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ParcoursViewEtape {

    private GestionnaireParcours gestionnaireParcours;

    @FXML
    private TextField name;
    @FXML
    private TextField latitude;
    @FXML
    private TextField longitude;

    /** Constructeur */
    public ParcoursViewEtape(GestionnaireParcours gestionnaireParcours){
        this.gestionnaireParcours = gestionnaireParcours;
    }

    /** Méthodes */

    /** Boutton Créer ! */
    public void changeSceneParcoursTracer(ActionEvent actionEvent) throws IOException {
        /** Créer et ajouter l'étape */
        Etape etape = new Etape(this.name.getText(),
                                Double.parseDouble(this.latitude.getText()),
                                Double.parseDouble(this.longitude.getText()));
        System.out.println(etape.getName()+" "+etape.getLatitude()+" "+etape.getLongitude());

        int size = this.gestionnaireParcours.getParcours().size();
        this.gestionnaireParcours.getParcours().get(size-1).getEtapes().add(etape);
        System.out.println(this.gestionnaireParcours.getParcours().get(size-1).getEtapes().get(0).getName());

        /** Dialogue "étape créée" */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Félicitations");
        alert.setContentText("Votre étape a été créée avec succès!");
        alert.showAndWait();

        /** Changement scène > parcoursTracerView */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("parcoursTracerView.fxml"));
        loader.setControllerFactory(iC->new ParcoursTarcerView(this.gestionnaireParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }
}
