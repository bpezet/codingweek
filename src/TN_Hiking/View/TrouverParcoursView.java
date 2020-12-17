package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TrouverParcoursView implements Initializable {

    @FXML
    private MenuBar my_bar;
    @FXML
    private MenuItem trouverParcours;
    @FXML
    private ScrollPane rechercheParcours;
    @FXML
    private TextField titreRecherche;
    @FXML
    private Slider distance;
    @FXML
    private Slider duree;
    @FXML
    private Slider difficulte;
    @FXML
    private TextField depart;
    @FXML
    private CheckBox distanceCheck;
    @FXML
    private CheckBox dureeCheck;
    @FXML
    private CheckBox difficulteCheck;
    @FXML
    private CheckBox departCheck;
    @FXML
    private Button valider;
    @FXML
    private ListView affichageResultats;
    @FXML
    private MenuItem homeButton;

    private GestionnaireParcours gParcours;
    private GestionnaireParcours resultatRecherche ;
    private int difficultep = 6;
    private int distancep = 21;
    private int dureep = 11;
    private String departp;

    private List<String> listResultat;
    private Parcours selectedParcours;

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

    @FXML
    public void eventHadlerBackBouton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC -> new WelcomeView(this.gParcours));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    public void eventHandlerUpdateBouton(javafx.event.ActionEvent actionEvent) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateParcours.fxml"));
            loader.setControllerFactory(iC -> new UpdateParcours(this.gParcours, this.gParcours.getParcours(0)));
            Parent createParcoursParent = loader.load();

            Scene createParcoursScene = new Scene(createParcoursParent);

            Stage window = (Stage) my_bar.getScene().getWindow();

            window.setScene(createParcoursScene);
            window.show();
        }catch(Exception e){
        }
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
        if (this.distanceCheck.isSelected() == false) {
            System.out.print("je rentre Distance");
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
    public void setResultatRechercheDistanceAjout(GestionnaireParcours resultatRecherche) {

            for (int k = 0; k < this.gParcours.getSize(); k++) {
                if (this.gParcours.getParcours(k).getDistance() <= distancep) {
                    resultatRecherche.addParcours(this.gParcours.getParcours(k));
                }
            }

    }
    public void setResultatRechercheDureeAjout(GestionnaireParcours resultatRecherche) {

            for (int k = 0; k < this.gParcours.getSize(); k++) {
                if (this.gParcours.getParcours(k).getDuree() <= dureep) {
                    resultatRecherche.addParcours(this.gParcours.getParcours(k));
                }
            }

    }
    public void setResultatRechercheDifficulteAjout(GestionnaireParcours resultatRecherche){

            for (int k = 0; k < this.gParcours.getSize(); k++) {

                if (this.gParcours.getParcours(k).getDifficulte() <= difficultep) {
                    resultatRecherche.addParcours(this.gParcours.getParcours(k));
                }
            }

    }
    public void setResultatRechercheDistanceSupp(GestionnaireParcours resultatRecherche) {

        for (int k = 0; k < resultatRecherche.getSize(); k++) {
            if (resultatRecherche.getParcours(k).getDistance() > distancep) {
                resultatRecherche.deleteParcoursIndex(k);
            }
        }

    }
    public void setResultatRechercheDureeSupp(GestionnaireParcours resultatRecherche) {

        for (int k = 0; k < resultatRecherche.getSize(); k++) {
            if (resultatRecherche.getParcours(k).getDuree() > dureep) {
                resultatRecherche.deleteParcoursIndex(k);
            }
        }

    }
    public void setResultatRechercheDifficulteSupp(GestionnaireParcours resultatRecherche){

        for (int k = 0; k < resultatRecherche.getSize(); k++) {

            if (resultatRecherche.getParcours(k).getDifficulte() > difficultep) {
                resultatRecherche.deleteParcoursIndex(k);
            }
        }

    }


    public void rechercherParcours() {
        this.resultatRecherche = new GestionnaireParcours();
        String titre = this.titreRecherche.getText();
        for (int k = 0; k < gParcours.getSize(); k++) {
            if (gParcours.getParcours(k).getName().equals(titre)) {
                this.resultatRecherche.addParcours(gParcours.getParcours(k));
            }
        }

        if (this.resultatRecherche.getSize() >= 1) {
            System.out.println("cas1");
            if (distancep < 21) {
                System.out.println("distance1");
                setResultatRechercheDistanceSupp(this.resultatRecherche);
            }

            if (dureep < 11) {
                System.out.println("duree1");
                setResultatRechercheDureeSupp(this.resultatRecherche);
            }
            if (difficultep <6) {
                System.out.println("difficulte1");
                setResultatRechercheDifficulteSupp(this.resultatRecherche);
            }
            /**if (!departp.equals(null)) {
                for (int k = 0; k < resultatRecherche.getSize(); k++) {
                    if (!resultatRecherche.getParcours(k).getDepartName().equals(departp)) {
                        this.resultatRecherche.deleteParcours();
                    }
                }
            }*/

        }

        else { //au moins distance est modifée
            System.out.println("cas2");
            if (distancep < 21 ) {
                System.out.println("distance2");
                setResultatRechercheDistanceAjout(this.resultatRecherche);
                if (dureep < 11) {
                    setResultatRechercheDureeSupp(this.resultatRecherche);
                }
                if (difficultep < 6) {
                    setResultatRechercheDifficulteSupp(this.resultatRecherche);
                }
            }
            //au moins duree est mofifiée
            if (dureep < 6) {
                System.out.println("duree2");
                setResultatRechercheDureeAjout(this.resultatRecherche);
                if (distancep < 21) {
                    setResultatRechercheDistanceSupp(this.resultatRecherche);
                }
                if (difficultep < 6) {
                    setResultatRechercheDifficulteSupp(this.resultatRecherche);
                }
            }
            //au moins diff est modifiée
            if (difficultep <6) {
                System.out.println("difficulte2");
                setResultatRechercheDifficulteAjout(this.resultatRecherche);
                if (distancep < 21) {
                    setResultatRechercheDistanceSupp(this.resultatRecherche);
                }
                if (dureep < 11) {
                    setResultatRechercheDureeSupp(this.resultatRecherche);
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
            System.out.println(resultatRecherche.getParcours().get(k).getDepartName());
            System.out.println(resultatRecherche.getParcours().get(k).getDistance());
            System.out.println(resultatRecherche.getParcours().get(k).getDuree());
        }

        /** affichage dans la ListView */

        this.listResultat = new ArrayList<>();
        for(Parcours p : this.resultatRecherche.getParcours()){
            listResultat.add(p.getName());
        }
        this.affichageResultats.getItems().setAll(listResultat);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.trouverParcours.setDisable(true);
    }

    public void parcoursSelectionned(MouseEvent mouseEvent) {
        this.selectedParcours = this.resultatRecherche.getParcours(this.affichageResultats.getSelectionModel().getSelectedIndex());
    }

    public void visualiserParcours(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("visualiserParcours.fxml"));
        loader.setControllerFactory(iC->new VisualiserParcours(this.selectedParcours));
        Parent createParcoursParent = loader.load();

        VisualiserParcours visualiserParcours = loader.getController();
        visualiserParcours.initMapAndControls();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }
}
