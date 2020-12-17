package TN_Hiking.View;

import TN_Hiking.BD.Decoder;
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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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
    public MenuItem generateParcours;

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
    public void trouverParcoursMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("trouverParcoursView.fxml"));
        loader.setControllerFactory(iC->new TrouverParcoursView(this.gestionnaireParcours));
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
    String BDD ="BDD";
    //for SaveAsButton
    FileChooser fileChooser;
    DirectoryChooser directoryChooser;

    @FXML
    public void setRefreshButton(){
        //cliquer sur ce boutton va vous faire charger
        // le gestionnaire de parcours enregistre en local sur votre pc
        // suppose qu'il y a deja eu une version locale enregistree auparavant
        // (ie: que le fichier localSave existe)
        Decoder dc = new Decoder();
        GestionnaireParcours neuGP;
        dc.setPathDirName(BDD);
        dc.setLocalSave(localSave);
        neuGP = dc.decodeAction();
        gestionnaireParcours = neuGP;
    }
    @FXML
    public void setSaveButton() {
        // permet d'ecrire dans notre base de donnes locale
        // elle est materialise ici par un  dossier de merde
        // nomme BDD a la racine, un projet = un fichier pour l'instant


        //try{System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));} catch(IOException e){e.printStackTrace();}

        // on utilise l'outil d'exportation writter :)
        Writter wr = new Writter();
        System.out.println(System.getProperty("os.name"));
        wr.setPathDirName(BDD);
        wr.writeAction(this.gestionnaireParcours);
    }

    @FXML
    public void setSaveAsButton() {
        // first step: find directory where you wanna your saveFile "localSave"
        directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) firstPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        System.out.println(file.getPath().toString()); // on verifie qu'on a bien le bon directory
        //second step: as always we need ton write in this directory: we call Writter man
        Writter wr = new Writter();
        wr.setPathDirName(file.getAbsolutePath());
        wr.writeAction(this.gestionnaireParcours); //done
    }
    @FXML
    public void setOpenFromButton()
    {
        GestionnaireParcours neuGP;
        //FIRST STEP: find file
        directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) firstPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        System.out.println("Le directory choisis est:"+file.getPath());

        Decoder dc = new Decoder();
        dc.setPathDirName(file.getPath());
        neuGP = dc.decodeAction();
        gestionnaireParcours = neuGP;
    }
    @FXML
    public void setAddGpxFromButton(){
        // aim : add a new parcours made of only those point from .csv file
        // we can only get coords for each etape
        // not photo/name ect so we are going to name it like parcours[nomFile]
        // etape1,etape2,etape3 ect..

        Parcours NewParcours = new Parcours("NewParcours",0,"Tera");
        NewParcours.setNote(0);
        NewParcours.setDescriptionDetaillee("DetailedDesc");
        NewParcours.setDescriptionCourte("ShortDesc");
        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            NewParcours.setImage("src\\TN_Hiking\\Ressources\\Import\\lapin.jpeg");
        } else { // then it is a mac/linux piece of shit
            NewParcours.setImage("src/TN_Hiking/Ressources/Import/lapin.jpeg");
        }



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
                //System.out.println(line);
                String[] split = line.split(";");
                //System.out.println(split.length+"");
                // for(int i = 0;i<split.length;i++) { System.out.println(split[i]); }
                if (split.length == 2) {
                    Etape etape = new Etape("Etape"+nbEtape,Double.parseDouble(split[0]),Double.parseDouble(split[1]));
                    etape.setName("Etape"+nbEtape);
                    etape.setLongitude(Double.parseDouble(split[0]));
                    etape.setLatitude(Double.parseDouble(split[1]));
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
    //######### debugg ######################
    //#####################################
    @FXML
    public void setPrintActiveButton(){
        // permet d'afficher le gestionnaire courant du array list
        this.gestionnaireParcours.showGestionnaire();
    }
    @FXML
    public void setPrintLocalButton(){
        // fonction qui est cense afficher le gestionnaire contenu dans BDD
        Decoder dc;
        dc = new Decoder();
        dc.setPathDirName(BDD);
        GestionnaireParcours gp;
        gp = dc.decodeAction();
        gp.showGestionnaire();

    }

    public void setGenerateParcours(){
        String name1 = "name1";
        String name2 = "name2";
        double lat1 = 49.008764;
        double lat2 = 53.0097;
        double long1 = 123.092;
        double long2 = 450.302;

        Etape etape1 = new Etape(name1,lat1,long1);
        Etape etape2 = new Etape(name2,lat2,long2);

        Parcours parcours = new Parcours("Mon Premier Parcours",3,"Strasbourg");
        parcours.setEtapeDebut(etape1);
        parcours.setEtapeFin(etape2);
        parcours.setNote(3);
        parcours.setDescriptionCourte("descCourte1");
        parcours.setDescriptionDetaillee("descdetaille1");


        Parcours parcours2 = new Parcours("Mon Deuxieme Parcours",2,"Nancy");
        parcours2.setEtapeDebut(etape2);
        parcours2.setEtapeFin(etape1);
        parcours2.setNote(20);
        parcours2.setDescriptionCourte("descCourte2");
        parcours2.setDescriptionDetaillee("descDetaille2");

        if(System.getProperty("os.name").startsWith("Windows")) //then it is a window ios lol
        {
            parcours.setImage("src\\TN_Hiking\\Ressources\\lapin.jpeg");
            parcours2.setImage("src\\TN_Hiking\\Ressources\\Logo.png");
        } else { // then it is a mac/linux piece of shit
            parcours.setImage("src/TN_Hiking/Ressources/lapin.jpeg");
            parcours2.setImage("src/TN_Hiking/Ressources/Logo.png");
        }

        gestionnaireParcours.addParcours(parcours);
        gestionnaireParcours.addParcours(parcours2);
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
