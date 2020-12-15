package TN_Hiking.View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

public class ParcoursTarcerView {
    @FXML
    Pane mainPane;

    public ParcoursTarcerView() {

    }

    public void finirParcours() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Félicitations");
        alert.setContentText("Votre parcours est crée avec succès!");

        alert.showAndWait();
    }

}
