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
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParcoursTarcerView implements Initializable {
    @FXML
    private MenuBar my_bar;
    @FXML
    private MenuItem homeButton;
    @FXML
    private MenuItem borderPane;


    private GestionnaireParcours gParcours;

    /** Constructeur */
    public ParcoursTarcerView(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }

    /** Méthodes */

    /** Boutton Annuler le parcours*/
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

    /** Boutton Finir le parcours*/
    public void finirParcours(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("finalisationParcours.fxml"));
        loader.setControllerFactory(iC->new FinalisationParcours(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }

    /** Boutton Créer une étape*/
    public void changeSceneCreerParcours(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("parcoursViewEtape.fxml"));
        loader.setControllerFactory(iC->new ParcoursViewEtape(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }

    /** App close*/
    public void closeApp() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.borderPane.setDisable(true);
        this.homeButton.setDisable(true);
    }
}
