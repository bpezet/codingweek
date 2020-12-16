package TN_Hiking.View;

import TN_Hiking.BD.Writter;
import TN_Hiking.Gestionnaires.GestionnaireParcours;

import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

import javafx.scene.control.MenuItem;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // First Main pane
    @FXML
    public Pane firstPane;

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
    //#####################################
    //######### FILE ######################
    //#####################################
    String localSave = "localSave";
    @FXML
    public void setRefreshButton(){}
    @FXML
    public void setSaveButton() {
        // permet d'ecrire dans notre base de donnes locale
        // elle est materialise ici par un  dossier de merde
        // nomme BDD a la racine, un projet = un fichier pour l'instant


        //Path path = Paths.get(".gitignore");
        //try{System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));} catch(IOException e){e.printStackTrace();}

        // on utilise l'outil d'exportation writter :)
        Writter wr = new Writter();
        wr.setPathName(localSave);
        wr.writeAction(this.gestionnaireParcours);
    }
    //for SaveAsButton
    FileChooser fileChooser;
    @FXML
    public void setSaveAsButton() {
        // first step: find directory where you wanna your saveFile "localSave"
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) firstPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        System.out.println(file.getPath().toString()); // on verifie qu'on a bien le bon directory
        //second step: as always we need ton write in this directory: we call Writter man
        Writter wr = new Writter();
        //quete annexe: trouver le delimiteur approprié (windows ou linux ?)
        System.out.print("Operating System: ");
        System.out.println(System.getProperty("os.name"));
        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            wr.setPathName(file.getPath().toString()+"\\"+localSave);
        } else { // then it is a mac/linux piece of shit
            wr.setPathName(file.getPath().toString()+"/"+localSave);
        }
        wr.writeAction(this.gestionnaireParcours); //done
    }
    @FXML
    public void setOpenFromButton(){}
    @FXML
    public void setAddGpxFromButton(){
        // aim : add a new parcours made of only those point from .csv file
        // we can only get coords for each etape
        // not photo/name ect so we are going to name it like parcours[nomFile]
        // etape1,etape2,etape3 ect..

        Parcours NewParcours = new Parcours("NewParcours",0,"Tera");
        int nbEtape = 0;
        //FIRST STEP: find file
        fileChooser = new FileChooser();
        Stage stage = (Stage) firstPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println("Le fichier choisis est:"+file.getPath().toString());
        //System.out.println(file.getName().toString());
        NewParcours.setName("ExportParcours_"+file.getName().toString());
        // Second Step: check if we can have access to it
        BufferedReader bufferedReader = null; // for file reading
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //Thirs step: write in the new parcors
                System.out.println(line);
                String[] split = line.split(";");
                //System.out.println(split.length+"");
                // for(int i = 0;i<split.length;i++) { System.out.println(split[i]); }
                if (split.length == 2) {
                    Etape etape = new Etape("Etape"+nbEtape,Double.parseDouble(split[0]),Double.parseDouble(split[1]));
                    NewParcours.addEtape(etape);

                } else { System.out.println("Error au splitage ligne: FILE NOT CONVENTIONAL"); } // faut verifier la gueule du fichier
                nbEtape++; // pour nommer les etapes comme il se faut
            }/*whileBalise*/ } catch(IOException e) { e.printStackTrace(); }
        // on vide le bufferReader
        try { assert bufferedReader != null; bufferedReader.close(); } catch (IOException e){  e.printStackTrace(); }
        //4° etape on ajoute le parcours au gestionnaire
        gestionnaireParcours.addParcours(NewParcours);


    }

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
    public void setPrintLocalButton(){
        // fonction qui est cense afficher le gestionnaire contenu dans BDD
        // pour l'instant on va print le local lol
        this.gestionnaireParcours.showGestionnaire();}


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
