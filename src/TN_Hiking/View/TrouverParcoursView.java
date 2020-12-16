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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private GestionnaireParcours gParcours;
    private GestionnaireParcours gParcours2;

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
        loader.setControllerFactory(iC->new SearchingClass());
        Parent root1 = loader.load();


        Stage stage = new Stage();
        stage.setTitle("Critère possible pour affiner la recherche");
        stage.setScene(new Scene(root1, 487, 214));
        stage.show();
    }


    public void rechercherParcours() {
        String titre = this.titreRecherche.getText();
        System.out.println(titre);
        for (int k=0; k < gParcours.getSize(); k++) {
            if (gParcours.getParcours(k).getName() == titre) {
                this.gParcours2.addParcours(gParcours.getParcours(k));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.trouverParcours.setDisable(true);
    }
}
