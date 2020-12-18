package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import com.sothawo.mapjfx.MapView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisualiserParcours {

    private Parcours p;
    private CoordinateLine track;

    @FXML
    MapView myMap;
    @FXML
    private Label name;
    @FXML
    private Label short_desc;
    @FXML
    private Label long_desc;
    @FXML
    private Label difficulty;
    @FXML
    private Label note;
    @FXML
    private Label begin;
    @FXML
    private Label end;
    @FXML
    private Label length;
    @FXML
    private Label duration;
    @FXML
    private ImageView imageParcours = new ImageView();
    @FXML
    private Button fermerButton;

    private GestionnaireParcours gestionnaireGlobale;

    @FXML
    private MenuBar my_bar;

    /** Constructeur */
    public VisualiserParcours(GestionnaireParcours gestionnaireGlobale, Parcours parcours){
        /*
        Etape deb = new Etape("Telecom Nancy", 48.669679,6.154803);
        Etape fin = new Etape("Place Stanislas", 48.693829,6.182534);
        this.p = new Parcours("Balade en centre-ville",1,deb,fin);
        this.p.setDescriptionCourte("Petit parcours commençant à l'école Telecom menant à la place Stanislas.");
        this.p.setDifficulte(1);
        this.p.setNote(5);
        this.p.getEtapes().add(deb);
        Etape e2 = new Etape("Chez Yassin",48.666889,6.166486);
        this.p.addEtape(e2);
        Etape e3 = new Etape("Mac Carthy",48.692264,6.177400);
        this.p.addEtape(e3);
        this.p.getEtapes().add(fin);
        this.p.setDescriptionDetaillee("Parcours sympa départ de l'école d'ingénieur Telecom Nancy, " +
                "qui nous mène vers l'authentique kebab 'Chez Yassin'. Le repas peut se poursuivre par une bière " +
                "au MacCarthy. La balade se terminera par la visite de la place Stanislas.");
                */

        this.p= parcours;
        this.gestionnaireGlobale = gestionnaireGlobale;
    }

    /** Pour rechercher parcours*/
    public VisualiserParcours(Parcours selectedParcours){
        this.p=selectedParcours;
    }

    /** Méthodes */

    /** Bouton : fermer l'application*/
    public void closeApp() {
        Platform.exit();
    }

    @FXML
    public void initialize(){
        this.myMap.initialize();
        this.myMap.setCenter(new Coordinate(this.p.getSpecificEtape(0).getLatitude(),this.p.getSpecificEtape(0).getLongitude()));
        this.name.setText(this.p.getName());
        this.short_desc.setText(this.p.getDescriptionCourte());
        this.difficulty.setText(String.valueOf(this.p.getDifficulte()));
        this.note.setText(String.valueOf(this.p.getNote()));
        this.begin.setText(this.p.getSpecificEtape(0).getName());
        this.end.setText(this.p.getSpecificEtape(this.p.getEtapes().size()-1).getName());
        this.long_desc.setText(this.p.getDescriptionDetaillee());

        int pos = String.valueOf(p.getDistance()).indexOf(".");    // position du "." dans le string distance
        this.length.setText(String.valueOf(p.getDistance()).substring(0,pos+2)+" km");

        String heure = String.valueOf((int)(((this.p.getDuree()*60)/60)));
        String minutes = String.valueOf((int)(((this.p.getDuree()*60)%60)));
        if(minutes.length()==1){ minutes = "0"+minutes; }
        this.duration.setText(heure+"h"+minutes+"min");

        /** tracer le parcours */
        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.p.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }
        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);
        this.myMap.addCoordinateLine(this.track);
        try {
            FileInputStream inputstream = new FileInputStream(this.p.getImage());
            Image image = new Image(inputstream);
            this.imageParcours.setImage(image);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(this.p.getImage());
            System.out.println("Ca marche pas");
        }
    }

    public void initMapAndControls(){
        this.track.setVisible(true);
        this.myMap.addCoordinateLine(this.track);
    }

    /** Lorsqu'on affiche plus d'informations, le tracé du parcours est affiché (pas avant...) */
    public void extendInfo(MouseEvent mouseEvent) {
        this.track.setVisible(false);
        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.p.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }
        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);
        this.myMap.addCoordinateLine(this.track);
    }

    public void showEtapesParcours(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showEtapeView.fxml"));
        loader.setControllerFactory(iC->new ShowEtapeView(this.p));
        Parent root1 = loader.load();

        ShowEtapeView showEtapeView = loader.getController();
        showEtapeView.initMapAndControls();

        Stage stage = new Stage();
        stage.setTitle("Étapes du parcours");
        stage.setScene(new Scene(root1, 500, 300));
        stage.show();
    }

    /** Home > Home */
    @FXML
    public void eventHadlerBackBouton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("welcomeView.fxml"));
        loader.setControllerFactory(iC -> new WelcomeView(this.gestionnaireGlobale));
        Parent createParcoursParent = loader.load();

        Scene createParcoursScene = new Scene(createParcoursParent);

        Stage window = (Stage) my_bar.getScene().getWindow();

        window.setScene(createParcoursScene);
        window.show();
    }

    public void closeWindow(ActionEvent actionEvent) {
        ((Stage)this.fermerButton.getScene().getWindow()).close();
    }
}
