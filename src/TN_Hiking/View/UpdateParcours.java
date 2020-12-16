package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class UpdateParcours {

    @FXML
    private MenuBar my_bar;

    @FXML
    private TextField titre= new TextField();

    @FXML
    private TextField note;

    @FXML
    private TextField debut = new TextField();

    @FXML
    private TextField fin;

    @FXML
    private TextArea resume;

    @FXML
    private TextArea description;

    @FXML
    private MenuItem menuItemUpdate = new MenuItem();


    GestionnaireParcours ges;
    Parcours parcours;

    public UpdateParcours(GestionnaireParcours gestionnaireParcours){
        this.ges = gestionnaireParcours;
        this.parcours = new Parcours("ParcoursTest",5, new Etape("Moncuq",1,1),new Etape("Moncuq",2,2));

    }

    public void eventHandlerModificationEtape() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modificationEtape.fxml"));
        loader.setControllerFactory(iC->new ModificationEtape(this.parcours));
        Parent createParcoursParent = loader.load();

        ModificationEtape modificationEtape = loader.getController();
        modificationEtape.initMapAndControls();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }



    /** Outils > Créer un parcours */
    public void changeSceneCreerParcours(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("createParcoursView.fxml"));
        loader.setControllerFactory(iC->new CreateParcoursView(this.ges));
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
        loader.setControllerFactory(iC->new UpdateParcours(this.ges));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    @FXML
    public void eventHandlerEnregistrerParcours(){
        if (this.titre.getText()!="") {
            this.parcours.setName(this.titre.getText());
        }
        if (this.debut.getText()!="") {
            //Coords `a modifier
            this.parcours.setEtapeDebut(new Etape(this.fin.getText(), 1.00,1.00));
        }
        if (this.note.getText()!=""){
            //A mettre//
        }
        if (this.fin.getText() != ""){
            //Coords `a modifier
            this.parcours.setEtapeFin(new Etape(this.fin.getText(), 1.00,1.00));
        }
        if (this.resume.getText()!=""){
            this.parcours.setDescriptionCourte(this.resume.getText());
        }
        if(this.description.getText()!=""){
            this.parcours.setDescriptionDetaillee(this.description.getText());
        }
    }

    @FXML
    public void initialize(){
        this.titre.setPromptText(this.parcours.getName());
        this.debut.setPromptText(this.parcours.getEtapeDebut().getName());
        this.menuItemUpdate.setDisable(false);
    }


}
