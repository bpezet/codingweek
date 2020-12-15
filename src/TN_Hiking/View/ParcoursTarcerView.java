package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ParcoursTarcerView {
    @FXML
    MenuBar my_bar;


    GestionnaireParcours gParcours;

    public ParcoursTarcerView(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }

    public void changeSceneCreerParcours(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("createParcoursView.fxml"));
        loader.setControllerFactory(iC->new CreateParcoursView(this.gParcours));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
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


    public void finirParcours(ActionEvent actionEvent) throws IOException {
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
}
