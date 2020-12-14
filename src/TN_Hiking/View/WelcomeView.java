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

    @FXML
    private BorderPane mainPane;


    private GestionnaireParcours gestionnaireParcours;
    private int i=0;
    public WelcomeView(GestionnaireParcours gestionnaireParcours) {
        this.gestionnaireParcours = new GestionnaireParcours();
    }
    public void parcoursCreate() {
        i++;
        System.out.println(i);
    }




    @FXML
    public void eventHandlerCreationParcours(){
        FxmlLoaderScreen object = new FxmlLoaderScreen();
        Pane view = object.getPane("CreateParcoursView");
        mainPane.setCenter(view);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
