package TN_Hiking.View;

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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private ImageView imageParcours;

    /** Constructeurs */

    /** Pour Niels */
    public VisualiserParcours(){
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

    public void changeSceneCreerParcours(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize(){
        this.myMap.initialize();
        this.myMap.setCenter(new Coordinate(48.693829,6.182534));
        this.name.setText(this.p.getName());
        this.short_desc.setText(this.p.getDescriptionCourte());
        this.difficulty.setText(String.valueOf(this.p.getDifficulte()));
        this.note.setText(String.valueOf(this.p.getNote()));
        //this.begin.setText(this.p.getEtapeDebut().getName());
        //this.end.setText(this.p.getEtapeFin().getName());
        this.long_desc.setText(this.p.getDescriptionDetaillee());

        /** tracer le parcours */
        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.p.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }
        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);
        this.myMap.addCoordinateLine(this.track);
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
}
