package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.BD.FileHandling;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinalisationParcours implements Initializable {
    // First Main pane
    @FXML
    private  MenuBar my_bar;
    @FXML
    Slider note;

    @FXML
    public Button Image;
    @FXML
    MenuItem borderPane;

    @FXML
    TextField lieuFin;
    @FXML
    TextField descrptionCourte;
    @FXML
    TextArea descriptionDetaillee;

    GestionnaireParcours gParcours;

    public FinalisationParcours(GestionnaireParcours gParcours) {
        this.gParcours = gParcours;
    }

    public void retourEnArriere(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("parcoursTracerView.fxml"));
            loader.setControllerFactory(iC->new ParcoursTarcerView(this.gParcours));
            Parent createWelcomeParent = loader.load();

            Scene createWelcomeScene = new Scene(createWelcomeParent);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            window.setScene(createWelcomeScene);
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
    public void retourAuDebut(ActionEvent actionEvent) throws IOException {
        this.gParcours.deleteParcours();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();
    }
    public void confirmerCreationparcours(ActionEvent actionEvent) throws IOException{
        int size = this.gParcours.getParcours().size();
        this.gParcours.getParcours().get(size-1).setNote((int)this.note.getValue());
        this.gParcours.getParcours().get(size-1).setName(this.lieuFin.getText());
        this.gParcours.getParcours().get(size-1).setDescriptionCourte(this.descrptionCourte.getText());
        this.gParcours.getParcours().get(size-1).setDescriptionDetaillee(this.descriptionDetaillee.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Félicitations");
        alert.setContentText("Votre parcours est crée avec succès!");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView(this.gParcours));
        Parent createWelcomeParent = loader.load();

        Scene createWelcomeScene = new Scene(createWelcomeParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(createWelcomeScene);
        window.show();

    }
    // #############################
    /// Image things
    // #############################
    public void ajoutImage() {
        //System.out.println("hello");
        String pathnameSRC;
        String pathnameDEST;
        String demarcor="";
        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            demarcor = "\\";
            pathnameDEST = "src\\TN_Hiking\\Ressources\\Import\\";
        } else { // then it is a mac/linux piece of shit
            pathnameDEST = "src/TN_Hiking/Ressources/import/";
            demarcor="/";
        }

        FileChooser fileChooser;
        fileChooser = new FileChooser();
        //FIRST STEP: find file

        FileHandling fl = new FileHandling();

        Stage stage = (Stage) note.getScene().getWindow();
        //File file = fileChooser.showOpenDialog(stage);
        pathnameSRC = fl.getFilePath(stage);

        //pour recuperer la tete du fichier
        File file = new File(pathnameSRC);
        System.out.println("Le fichier choisis est:"+pathnameSRC);

        //Second Step: copie/move into Ressources
        // + rename it if needed
        fl.fileCopieColle(pathnameSRC,pathnameDEST+file.getName());

        //TroisiemeEtape: Lier cette image a un parcours
        int size = this.gParcours.getParcours().size();
        this.gParcours.getParcours().get(size-1).setImage(pathnameDEST+file.getName());

    }
    public void closeApp() {
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.borderPane.setDisable(true);
    }
}
