package TN_Hiking.View;

import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import com.sothawo.mapjfx.MapView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class VisualiserParcours {

    private Parcours p;
    private int expandClick = 0;
    private CoordinateLine track;

    @FXML
    MapView myMap;
    @FXML
    private Label short_desc;
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

    /** Constructeur */
    public VisualiserParcours(){
        Etape deb = new Etape("Telecom Nancy", 54.0,600.0);
        Etape fin = new Etape("Place Stanislas", 54.54,654.0);
        this.p = new Parcours("Balade en centre-ville",1,deb,fin);
        this.p.setDescriptionCourte("Petit parcours commençant à l'école Telecom menant à la place Stanislas.");
        this.p.setDifficulte(1);
        this.p.setNote(5);
        this.p.getEtapes().add(deb);
        this.p.getEtapes().add(fin);
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
        this.short_desc.setText(this.p.getDescriptionCourte());
        this.difficulty.setText(String.valueOf(this.p.getDifficulte()));
        this.note.setText(String.valueOf(this.p.getNote()));
        this.begin.setText(this.p.getEtapeDebut().getName());
        this.end.setText(this.p.getEtapeFin().getName());

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

    @FXML
    public void eventHandlerTracer(){
        this.track.setVisible(false);
        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.p.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }
        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);
        this.myMap.addCoordinateLine(this.track);
    }

    public void extendInfo(MouseEvent mouseEvent) {
        /**
        this.expandClick++;
        //Click impair : more info
        if (this.expandClick%2 != 0){
            this.imageParcours.setVisible(false);

        }
        //Click pair : less info
        else{
            this.imageParcours.setVisible(true);
        }
         */
    }
}
