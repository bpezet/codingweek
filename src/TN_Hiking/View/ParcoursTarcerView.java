package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ParcoursTarcerView {
    @FXML
    Pane mainPane;

    GestionnaireParcours gParcours;

    public ParcoursTarcerView(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }

    public void finirParcours() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Félicitations");
        alert.setContentText("Votre parcours est crée avec succès!");

        alert.showAndWait();
    }
    public void changeSceneTracerToWelcomeView(ActionEvent actionEvent) throws IOException {
        this.gParcours.deleteParcours();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }


}
