package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;
import com.sothawo.mapjfx.offline.OfflineCache;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModificationEtape {

    private static final int ZOOM_DEFAULT = 14;

    private Parcours parcours;

    private Marker markerSet;

    @FXML
    private MapView mapView;

    @FXML
    private ListView<String> listView;

    /**Constructeur*/
    public ModificationEtape(Parcours parcours){

        this.parcours = parcours;
        System.out.println(parcours.getName());
        this.parcours.addEtape(new Etape("Toulouse", 43.6, 1.43));
        this.parcours.addEtape(new Etape("Montcuq", 44.3333, 1.21667));
        this.parcours.addEtape(new Etape("Carcassonne", 43.21667, 2.35));
    }


    public void initMapAndControls(){
        mapView.setZoom(ZOOM_DEFAULT);
        Coordinate coords= new Coordinate(43.6, 1.43);
        this.markerSet = Marker.createProvided(Marker.Provided.BLUE).setPosition(coords).setVisible(
                false);
        this.mapView.addMarker(this.markerSet);
        markerSet.setVisible(true);
    }

    /**Les actions à faire dès lors que l'on clic sur un item de la liste*/
    @FXML
    public void eventHandlerMouseClickListView(){
        try {
            System.out.println("Tu as cliquer sur : " + this.listView.getSelectionModel().getSelectedItem());
            Etape etapeselected = this.parcours.getSpecificEtape(this.listView.getSelectionModel().getSelectedIndex());
            this.mapView.setCenter(new Coordinate(etapeselected.getLatitude(), etapeselected.getLongitude()));
        }catch(Exception e){
            System.out.println("Fait gaffe bro");
        }
    }



    @FXML
    public void initialize(){
        this.mapView.initialize();
        this.mapView.setCenter(new Coordinate(43.6, 1.43));
        for (Etape etape : this.parcours.getEtapes()){
            this.listView.getItems().add(etape.getName());
            //this.mapView.addMarker(new Marker());
        }
        this.listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Coordinate coords= new Coordinate(43.6, 1.43);
        this.markerSet = Marker.createProvided(Marker.Provided.BLUE).setPosition(coords).setVisible(
                false);
        this.mapView.addMarker(this.markerSet);
        markerSet.setVisible(true);

    }

}