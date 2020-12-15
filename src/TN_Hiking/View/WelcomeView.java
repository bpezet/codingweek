package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WelcomeView implements Initializable {
//Ceci est un commentaire
    @FXML
    protected BorderPane mainPane;

    private GestionnaireParcours gestionnaireParcours;

    public WelcomeView(GestionnaireParcours gestionnaireParcours) {
        this.gestionnaireParcours = gestionnaireParcours;
    }
    public void parcoursCreate() {

    }




    @FXML
    public void eventHandlerCreationParcours(){
        FxmlLoaderScreen object = new FxmlLoaderScreen();
        Pane view = object.getPane("createParcoursView", this.gestionnaireParcours);
        mainPane.setCenter(view);

    }

    @FXML
    public void eventHandlerUpdateBouton(){
        FxmlLoaderScreen object = new FxmlLoaderScreen();
        Pane view = object.getPane("updateParcours", this.gestionnaireParcours);
        mainPane.setCenter(view);
    }



    @FXML
    public void eventHandlerAfficheParcours(){
        FxmlLoaderScreen object = new FxmlLoaderScreen();
        Pane view = object.getPane("showParcoursView",this.gestionnaireParcours);
        mainPane.setCenter(view);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
