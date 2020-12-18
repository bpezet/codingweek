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
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModificationEtape {

    private static final int ZOOM_DEFAULT = 14;

    private Parcours parcours;

    @FXML
    private MenuBar my_bar;

    private Marker markerSet;

    private Double new_latitude =-1.0;

    private Double new_longitude=-1.0;

    private Marker newEtapeMarker;

    @FXML
    private TextField nomNewEtape;

    @FXML
    private TextField numEtape;

    @FXML
    private MapView mapView = new MapView();

    @FXML
    private ListView<String> listView;

    private CoordinateLine track;

    private GestionnaireParcours ges;

    /**Constructeur*/
    public ModificationEtape(GestionnaireParcours ges,Parcours parcours){

        this.ges = ges;

        this.parcours = parcours;
        Coordinate coords= new Coordinate(43.6, 1.43);
        this.markerSet = Marker.createProvided(Marker.Provided.BLUE).setVisible(false);

        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.parcours.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }

        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);

        this.newEtapeMarker = Marker.createProvided(Marker.Provided.GREEN).setVisible(true);

    }
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

    /** Bouton : fermer l'application*/
    public void closeApp() {
        Platform.exit();
    }

    public void initMapAndControls(){
        mapView.setZoom(ZOOM_DEFAULT);
        this.mapView.addMarker(this.markerSet);

        setupEventHandlers();
        this.track.setVisible(true);
        this.mapView.addCoordinateLine(this.track);


    }




    /**Les actions à faire dès lors que l'on clic sur un item de la liste*/
    @FXML
    public void eventHandlerMouseClickListView(){
        try {
            Etape etapeselected = this.parcours.getSpecificEtape(this.listView.getSelectionModel().getSelectedIndex());
            this.mapView.setCenter(new Coordinate(etapeselected.getLatitude(), etapeselected.getLongitude()));

            this.markerSet.setVisible(true);
            this.markerSet.setPosition(new Coordinate(etapeselected.getLatitude(),etapeselected.getLongitude()));
            this.mapView.addMarker(this.markerSet);


        }catch(Exception e){
            System.out.println("Fait gaffe bro");
        }
    }

    /**Mise à jour du tracer*/
    @FXML
    public void eventHandlerTracer(){
        this.track.setVisible(false);
        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.parcours.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }
        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);
        this.mapView.addCoordinateLine(this.track);
    }

    @FXML
    public void addEtape(){
        if (this.new_longitude !=-1){
            if (this.nomNewEtape.getText() != ""){
                if (this.numEtape.getText() != ""){
                    try{
                        int i = Integer.parseInt(this.numEtape.getText());
                        if (i<=this.parcours.getEtapes().size() && i>=0){
                            ArrayList<Etape> newEtapes = new ArrayList<>();
                            int k=0;
                            for (int j=0; j<=this.parcours.getEtapes().size();j++) {
                                if (j == i) {
                                    newEtapes.add(new Etape(this.nomNewEtape.getText(), this.new_latitude, this.new_longitude));
                                    k = 1;
                                } else {
                                    newEtapes.add(this.parcours.getSpecificEtape(j - k));
                                }
                            }
                            this.parcours.setEtapes(newEtapes);
                            this.updateList();
                            this.newEtapeMarker.setVisible(false);
                            this.numEtape.setText("");
                            this.nomNewEtape.setText("");
                        }
                    }

                    catch (Exception e){
                        System.out.println("Le numéro d'étape n'est pas un entier :(");
                    }

                }
            }
        }
    }

    /**Mise à jour de la liste*/
    public void updateList(){
        List<String> listEtape = new ArrayList<>();
        for (Etape etape : this.parcours.getEtapes()){
            listEtape.add(etape.getName());
        }
        this.listView.getItems().setAll(listEtape);
    }

    /**Suppression d'une étape sélectionné de la liste*/
    @FXML
    public void supprimeEtape(){
        try{
            int i = this.listView.getSelectionModel().getSelectedIndex();
            this.parcours.suppressionEtape(i);
            this.updateList();
            this.eventHandlerTracer();
        }catch (Exception e){
            System.out.println("Rien a été sélectionné dans la liste :(");
        }
    }

    /**SetUp afin de connaitre les coordonnées de là où l'on a cliqué sur la carte*/
    @FXML
    public void setupEventHandlers(){
        // add an event handler for singleclicks, set the click marker to the new position when it's visible
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            final Coordinate newPosition = event.getCoordinate().normalize();
            this.new_latitude = newPosition.getLatitude();
            this.new_longitude = newPosition.getLongitude();
            this.newEtapeMarker.setVisible(true);
            this.newEtapeMarker.setPosition(newPosition);
            this.mapView.addMarker(this.newEtapeMarker);
        });
    }


    @FXML
    public void eventHandlerBackButton() throws IOException {
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
    public void initialize(){
        this.mapView.initialize();
        try {
            this.mapView.setCenter(new Coordinate(this.parcours.getSpecificEtape(0).getLatitude(), this.parcours.getSpecificEtape(0).getLongitude()));
            this.updateList();
            this.listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            this.eventHandlerTracer();
        }catch(Exception e){
            this.mapView.setCenter(new Coordinate(1.0, 1.0));
            this.updateList();
            this.listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            this.eventHandlerTracer();
        }

    }

}