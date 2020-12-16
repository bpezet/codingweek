package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeView implements Initializable {

    @FXML
    protected BorderPane mainPane;
    @FXML
    private MenuBar my_bar;

    @FXML
    private Label titre1;
    @FXML
    private Label titre2;
    @FXML
    private Label duree1;
    @FXML
    private Label duree2;
    @FXML
    private Label difficulte1;
    @FXML
    private Label difficulte2;
    @FXML
    private Label distance1;
    @FXML
    private Label distance2;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;

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

    /** Bouton : fermer l'application*/
    public void closeApp() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img1 = new Image("TN_Hiking/Ressources/lapin.jpeg");
        image1.setImage(img1);
        Image img2 = new Image("TN_Hiking/Ressources/lapin.jpeg");
        image2.setImage(img2);

    }


}
