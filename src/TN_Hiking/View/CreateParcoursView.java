package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CreateParcoursView  implements Initializable {

    @FXML
    private MenuBar my_bar;
    @FXML
    private MenuItem homeButton;
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> difficulte;
    @FXML
    private TextField depart;
    @FXML
    private MenuItem afficher;

    private GestionnaireParcours gParcours ;

    /** Constructeur */
    public CreateParcoursView(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }

    /** Méthodes */

    /** Boutton Annuler*/
    public void changeSceneWelcomeView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }
    @FXML

    /** Boutton Retour*/
    public void retourButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }

    /** Bouton : fermer l'application*/
    public void closeApp() {
        Platform.exit();
    }

    public void changeSceneCreateToTracer(ActionEvent actionEvent) throws IOException {
        Parcours parcours = new Parcours(this.name.getText(),Integer.parseInt(this.difficulte.getValue()),this.depart.getText());
        this.gParcours.addParcours(parcours);
        //System.out.println(this.gParcours.getParcours().get(0).getName());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("parcoursTracerView.fxml"));
        loader.setControllerFactory(iC->new ParcoursTarcerView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.afficher.setDisable(true);
        this.homeButton.setDisable(true);
    }
}
