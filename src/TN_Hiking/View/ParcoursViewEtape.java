package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParcoursViewEtape implements Initializable {

    private GestionnaireParcours gestionnaireParcours;
    @FXML
    private MenuBar my_bar;
    @FXML
    private TextField name;
    @FXML
    private TextField latitude;
    @FXML
    private TextField longitude;
    @FXML
    private MenuItem borderPane;

    /** Constructeur */
    public ParcoursViewEtape(GestionnaireParcours gestionnaireParcours){
        this.gestionnaireParcours = gestionnaireParcours;
    }

    /** Méthodes */
    @FXML
    public void eventHadlerBackBouton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC -> new WelcomeView(this.gestionnaireParcours));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }
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

    /** Boutton Annuler*/
    public void cancelEtapeCreate(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("parcoursTracerView.fxml"));
        loader.setControllerFactory(iC->new ParcoursTarcerView(this.gestionnaireParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }

    /** Menu */

    /** Creer parcours dans le menu quand on est entrain de creer un parcours*/
    public void changeSceneCreerParcours(javafx.event.ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText("Attention !");
        alert.setContentText("Vous êtes deja entrai de créer un parcours : Action impossible");
        alert.show();
    }
    /** Bouton : fermer l'application*/
    public void closeApp() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.borderPane.setDisable(true);
    }

    public void chooseCoordMap(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("coordMapView.fxml"));
        loader.setControllerFactory(iC->new CoordMapView(this));
        Parent root1 = loader.load();

        CoordMapView coordMapView = loader.getController();
        coordMapView.initMapAndControls();

        Stage stage = new Stage();
        stage.setTitle("Map");
        stage.setScene(new Scene(root1, 600, 400));
        stage.show();
    }

    /** Setters */
    public TextField getLatitude() {
        return latitude;
    }
    public TextField getLongitude() {
        return longitude;
    }
}
