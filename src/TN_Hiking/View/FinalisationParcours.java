package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void retourEnArriere(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("parcoursTracerView.fxml"));
            loader.setControllerFactory(iC->new ParcoursTarcerView(this.gParcours));
            Parent createWelcomeParent = loader.load();

            Scene createWelcomeScene = new Scene(createWelcomeParent);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            window.setScene(createWelcomeScene);
            window.show();
    }
    public void retourAuDebut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }
    public void confirmerCreationparcours(ActionEvent actionEvent) throws IOException{
        int size = this.gParcours.getParcours().size();
        this.gParcours.getParcours().get(size-1).setNote((int)this.note.getValue());
        this.gParcours.getParcours().get(size-1).setName(this.lieuFin.getText());
        this.gParcours.getParcours().get(size-1).setDescriptionCourte(this.descrptionCourte.getText());
        this.gParcours.getParcours().get(size-1).setDescriptionCourte(this.descriptionDetaillee.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Félicitations");
        alert.setContentText("Votre parcours est crée avec succès!");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();

    }
    public void closeApp() {
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.borderPane.setDisable(true);
    }
}
