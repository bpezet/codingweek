package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

    @FXML
    private MenuItem homeButton;

    GestionnaireParcours ges;
    Parcours parcours;

    public UpdateParcours(GestionnaireParcours gestionnaireParcours,Parcours parcours){
        this.ges = gestionnaireParcours;
        this.parcours = parcours;

    }

    public void eventHandlerModificationEtape() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modificationEtape.fxml"));
        loader.setControllerFactory(iC->new ModificationEtape(this.ges,this.parcours));
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
    public void eventHadlerBackBouton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC -> new WelcomeView(this.ges));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    public void eventHandlersfav(ActionEvent actionEvent) throws IOException{
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("listeParcoursView.fxml"));
            loader.setControllerFactory(iC -> new ListeParcoursView(this.ges));
            Parent createParcoursParent = loader.load();

            Scene createParcoursScene = new Scene(createParcoursParent);

            Stage window = (Stage) my_bar.getScene().getWindow();

            window.setScene(createParcoursScene);
            window.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void trouverParcoursMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("trouverParcoursView.fxml"));
        loader.setControllerFactory(iC->new TrouverParcoursView(this.ges));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }


    @FXML
    public void eventHandlerUpdateBouton() throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateParcours.fxml"));
            loader.setControllerFactory(iC -> new UpdateParcours(this.ges, this.ges.getParcours(0)));
            Parent createParcoursParent = loader.load();

            Scene createParcoursScene = new Scene(createParcoursParent);

            Stage window = (Stage) my_bar.getScene().getWindow();

            window.setScene(createParcoursScene);
            window.show();
        }catch(Exception e){
        }
    }

    @FXML
    public void eventHandlerEnregistrerParcours() throws IOException {
        if (!this.titre.getText().isEmpty()) {
            this.parcours.setName(this.titre.getText());
        }
        if (!this.debut.getText().isEmpty()) {
            this.parcours.getSpecificEtape(0).setName(this.debut.getText());
        }
        if (!this.note.getText().isEmpty()){
            //A mettre//
        }
        if (!this.fin.getText().isEmpty()){
            //Coords `a modifier
            this.parcours.setEtapeFin(new Etape(this.fin.getText(), 1.00,1.00));
        }
        if (!this.resume.getText().isEmpty()){
            this.parcours.setDescriptionCourte(this.resume.getText());
        }
        if(!this.description.getText().isEmpty()){
            this.parcours.setDescriptionDetaillee(this.description.getText());
        }

        this.eventHadlerBackBouton();

    }
    public void closeButton(){
        Platform.exit();

    }


    @FXML
    public void initialize(){
        this.titre.setPromptText(this.parcours.getName());
        this.debut.setPromptText(this.parcours.getSpecificEtape(0).getName());
        this.note.setPromptText(String.valueOf(this.parcours.getNote()));
        this.fin.setPromptText(this.parcours.getSpecificEtape(this.parcours.getEtapes().size()-1).getName());
        this.resume.setPromptText(this.parcours.getDescriptionCourte());
        this.description.setPromptText(this.parcours.getDescriptionDetaillee());
        this.menuItemUpdate.setDisable(false);
    }


}
