package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeView implements Initializable {
    // FILE
    @FXML
    public MenuItem SaveButton;
    @FXML
    public MenuItem SaveAsButton;
    @FXML
    public MenuItem OpenFromButton;
    @FXML
    public MenuItem addGpxFromButton;
    @FXML
    public MenuItem RefreshButton;
    //DEBUGG
    @FXML
    public MenuItem printActiveButton;
    @FXML
    public MenuItem printLocalButton;

    @FXML
    protected BorderPane mainPane;
    @FXML
    private MenuBar my_bar;

    @FXML
    private MenuBar my_bar2;

    private GestionnaireParcours gestionnaireParcours;

    /** Constructeur */
    public WelcomeView(GestionnaireParcours gestionnaireParcours) {
        this.gestionnaireParcours = gestionnaireParcours;
    }

    /** Méthodes */

    /** Outils > Créer un parcours */
    public void changeSceneCreerParcours(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("createParcoursView.fxml"));
        loader.setControllerFactory(iC->new CreateParcoursView(this.gestionnaireParcours));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    @FXML
    public void eventHandlerUpdateBouton() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("updateParcours.fxml"));
        loader.setControllerFactory(iC->new UpdateParcours(this.gestionnaireParcours));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    @FXML
    public void testMap() throws IOException{
    }


    @FXML
    public void eventHandlerAfficheParcours(){
        FxmlLoaderScreen object = new FxmlLoaderScreen();
        Pane view = object.getPane("showParcoursView",this.gestionnaireParcours);
        mainPane.setCenter(view);
    }
    //#####################################
    //######### FILE ######################
    //#####################################
    @FXML
    public void setRefreshButton(){}
    @FXML
    public void setSaveButton() {}
    @FXML
    public void setSaveAsButton(){}
    @FXML
    public void setOpenFromButton(){}
    @FXML
    public void setAddGpxFromButton(){}

    //#####################################
    //######### FILE ######################
    //#####################################
    @FXML
    public void setPrintActiveButton(){
        // permet d'afficher le gestionnaire courant du array list
        // pour l'exemple on se créer un gestionnaire
        String name1 = "name1";
        String name2 = "name2";
        double lat1 = 49.008764;
        double lat2 = 53.0097;
        double long1 = 123.092;
        double long2 = 450.302;

        Etape etape1 = new Etape(name1,lat1,long1);
        Etape etape2 = new Etape(name2,lat2,long2);

        Parcours parcours = new Parcours("",0,"");
        parcours.setDifficulte(3);
        parcours.setDepart("Strasbourg");
        parcours.setName("Mon Premier Parcours");
        parcours.setEtapeDebut(etape1);
        parcours.setEtapeFin(etape2);

        Parcours parcours2 = new Parcours("",2,"");
        parcours2.setDepart("Nancy");
        parcours2.setDifficulte(4);
        parcours2.setName("Mon Deuxieme Parcours");
        parcours2.setEtapeDebut(etape2);
        parcours2.setEtapeFin(etape1);

        gestionnaireParcours.addParcours(parcours);
        gestionnaireParcours.addParcours(parcours2);

        gestionnaireParcours.showGestionnaire();

    }
    @FXML
    public void setPrintLocalButton(){}



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }






}
