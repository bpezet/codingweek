package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import com.sothawo.mapjfx.Coordinate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeParcoursView implements Initializable {

    @FXML
    private ListView<String> list;

    @FXML
    private MenuBar my_bar;

    @FXML
    private MenuItem afficher;

    @FXML
    private MenuItem visualiser;

    @FXML
    private MenuItem close;

    private GestionnaireParcours gestionnaireGlobale;

    public ListeParcoursView(GestionnaireParcours gestionnaireParcours){
        this.gestionnaireGlobale=gestionnaireParcours;
    }

    @FXML
    public void eventHandlerBouton(){
        try {
            Parcours etapeselected = this.gestionnaireGlobale.getParcours(this.list.getSelectionModel().getSelectedIndex());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("visualiserParcours.fxml"));
            loader.setControllerFactory(iC-> new VisualiserParcours(etapeselected));
            Parent root1 = loader.load();

            VisualiserParcours visualiserParcours = loader.getController();
            visualiserParcours.initMapAndControls();

            Stage stage = new Stage();
            stage.setTitle("Vue du parcours");
            stage.setScene(new Scene(root1, 600, 370));
            stage.show();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void retourButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gestionnaireGlobale));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }
    public void trouverParcoursMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("trouverParcoursView.fxml"));
        loader.setControllerFactory(iC->new TrouverParcoursView(this.gestionnaireGlobale));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }
    public void changeSceneCreerParcours(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("createParcoursView.fxml"));
        loader.setControllerFactory(iC->new CreateParcoursView(this.gestionnaireGlobale));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }
    public void closeButton(ActionEvent actionEvent) throws IOException {
        Platform.exit();
    }
    @FXML
    public void initialize(){
        ArrayList<Parcours> listParcours = this.gestionnaireGlobale.getParcours();
        ArrayList<String> listNom =new ArrayList<>();
        for(Parcours parcours : listParcours){
            listNom.add(parcours.getName());
        }
        this.list.getItems().setAll(listNom);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.afficher.setDisable(true);
        ArrayList<Parcours> listParcours = this.gestionnaireGlobale.getParcours();
        ArrayList<String> listNom =new ArrayList<>();
        for(Parcours parcours : listParcours){
            listNom.add(parcours.getName());
        }
        this.list.getItems().setAll(listNom);
    }
}
