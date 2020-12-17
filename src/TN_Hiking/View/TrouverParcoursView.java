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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrouverParcoursView implements Initializable {

    @FXML
    MenuBar my_bar;
    @FXML
    MenuItem trouverParcours;
    @FXML
    ScrollPane rechercheParcours;
    @FXML
    TextField titreRecherche;
    @FXML
    Slider distance;
    @FXML
    Slider duree;
    @FXML
    Slider difficulte;
    @FXML
    TextField depart;
    @FXML
    CheckBox distanceCheck;
    @FXML
    CheckBox dureeCheck;
    @FXML
    CheckBox difficulteCheck;
    @FXML
    CheckBox departCheck;
    @FXML
    Button valider;

    private GestionnaireParcours gParcours;
    private GestionnaireParcours resultatRecherche ;
    private int difficultep = 6;
    private int distancep = 21;
    private int dureep = 11;
    private String departp;

    public void closeApp() {
        Platform.exit();
    }


    public TrouverParcoursView(GestionnaireParcours gParcours) {
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


    public void eventHandlerUpdateBouton(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("updateParcours.fxml"));
        loader.setControllerFactory(iC->new UpdateParcours(this.gParcours));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    public void plusDeCritère(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchingClass.fxml"));
        loader.setControllerFactory(iC->this);
        Parent root1 = loader.load();


        Stage stage = new Stage();
        stage.setTitle("Critère possible pour affiner la recherche");
        stage.setScene(new Scene(root1, 603, 266));
        stage.show();

    }

    public void validationCriteres() {
        if (this.difficulteCheck.isSelected() == false) {
            difficultep = (int)this.difficulte.getValue();
        }
        if (this.dureeCheck.isSelected() == false) {
            distancep = (int)this.distance.getValue();
        }
        if (this.dureeCheck.isSelected() == false) {
            dureep = (int)this.duree.getValue();
        }
        if (this.departCheck.isSelected() == false) {
            departp = this.depart.getText();
        }
        ((Stage) valider.getScene().getWindow()).close();
    }
    public void rechercherParcours() {
        this.resultatRecherche = new GestionnaireParcours();
        String titre = this.titreRecherche.getText();
        for (int k = 0; k < gParcours.getSize(); k++) {
            if (gParcours.getParcours(k).getName().equals(titre)) {
                this.resultatRecherche.addParcours(gParcours.getParcours(k));
            }
        }

        if (resultatRecherche.getSize() > 1) {
            if (distancep < 21) {
                System.out.println("distance");
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (resultatRecherche.getParcours(k).getDistance() > distancep) {
                        this.resultatRecherche.deleteParcours();
                    }
                }
            }

            if (dureep < 11) {
                System.out.println("duree");
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (resultatRecherche.getParcours(k).getDuree() > distancep) {
                        this.resultatRecherche.deleteParcours();
                    }
                }
            }
            if (difficultep <6) {
                System.out.println("difficulte");
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    System.out.println(resultatRecherche.getParcours(k).getDifficulte());
                    if (resultatRecherche.getParcours(k).getDifficulte() > distancep) {

                        this.resultatRecherche.deleteParcours();
                    }
                }
            }
            /**if (!departp.equals(null)) {
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (!resultatRecherche.getParcours(k).getDepartName().equals(departp)) {
                        this.resultatRecherche.deleteParcours();
                    }
                }
            }*/

        }

        else { //seulemnt distance est modifée
            if (distancep < 21 && dureep == 6 && difficultep == 11 ) {
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (resultatRecherche.getParcours(k).getDistance() < distancep) {
                        this.resultatRecherche.addParcours(this.gParcours.getParcours(k));
                    }
                }
            }
            //seulement duree est mofifiée
            if (distancep ==21 && dureep < 6 && difficultep == 11 ) {
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (resultatRecherche.getParcours(k).getDuree() > dureep) {
                        this.resultatRecherche.addParcours(this.gParcours.getParcours(k));
                    }
                }
            }
            if (difficultep <6) {
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (resultatRecherche.getParcours(k).getDifficulte() > distancep) {
                        this.resultatRecherche.addParcours(this.gParcours.getParcours(k));
                    }
                }
            }
           /** if (!departp.equals(null)) {
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (resultatRecherche.getParcours(k).getDepartName().equals(departp)) {
                        this.resultatRecherche.addParcours(this.gParcours.getParcours(k));
                    }
                }
            }*/
        }
        for (int k = 0; k < resultatRecherche.getSize(); k++) {
            System.out.println(resultatRecherche.getParcours().get(k).getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.trouverParcours.setDisable(true);
    }
}
